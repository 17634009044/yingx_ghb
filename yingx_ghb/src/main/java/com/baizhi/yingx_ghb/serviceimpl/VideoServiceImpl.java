package com.baizhi.yingx_ghb.serviceimpl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.baizhi.yingx_ghb.annotation.AddLog;
import com.baizhi.yingx_ghb.dao.VideoDAO;
import com.baizhi.yingx_ghb.entity.Video;
import com.baizhi.yingx_ghb.entity.VideoVO;
import com.baizhi.yingx_ghb.service.VideoService;
import com.baizhi.yingx_ghb.util.AliyunOSSUtil;
import com.baizhi.yingx_ghb.util.VideoInterceptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
@Transactional
public class VideoServiceImpl implements VideoService {

    @Resource
    private VideoDAO videoDAO;

    @Resource
    HttpServletRequest request;

    @Override
    public Map<String, Object> queryAll(Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();
        //设置当前页
        map.put("page",page);
        //总条数
        Integer count = videoDAO.queryCount();
        map.put("records",count);
        //总页数  总页数=总条数/每页展示的条数
        int total = count / rows == 0 ? count / rows : count / rows + 1;
        map.put("total",total);

        List<Video> videos = videoDAO.queryAll((page - 1) * rows, rows);
        map.put("rows",videos);

        return map;
    }
    @AddLog("添加视频")
    @Override
    public String add(Video video) {
        video.setId(UUID.randomUUID().toString());
        video.setUpload_time(new Date());
        videoDAO.add(video);

        return video.getId();
    }
    @AddLog("删除视频")
    @Override
    public void delete(String id) {
        videoDAO.delete(id);
    }

    @AddLog("修改视频")
    @Override
    public String update(Video video) {
        if (video.getVideo_path().equals("")||video.getVideo_path().contains("fakepath")){
            video.setVideo_path(null);
        }
        videoDAO.update(video);
        return video.getId();
    }

    @Override
    public Video queryById(String id) {

        Video byId = videoDAO.queryById(id);
        return byId;
    }

    @Override
    public HashMap<String, Object> fileUpload(MultipartFile video_path, String id) {

        HashMap<String, Object> map = new HashMap<>();
       /* try {
            //获取文件名
            String originalFilename = video_path.getOriginalFilename();
            if (!originalFilename.isEmpty()||!originalFilename.equals("")){
                //生成新文件名+时间戳
                String newName=new Date().getTime()+"-"+originalFilename;
                //文件名拼接
                String objectName="video/"+newName;
                //调用阿里云上传方法
                AliyunOSSUtil.uploadBytesFile("gbyingx-2010",objectName,video_path);

                Video video1 = videoDAO.queryById(id);
                System.out.println(video1);
                String objectName1 = video1.getVideo_path().replace("http://gbyingx-2010.oss-cn-beijing.aliyuncs.com/","");
                AliyunOSSUtil.deleteFileAliyun("gbyingx-2010",objectName1);

                //修改文件信息
                Video video = new Video();
                video.setId(id);
                video.setVideo_path("http://gbyingx-2010.oss-cn-beijing.aliyuncs.com/"+objectName); //http://gbyingx-2010.oss-cn-beijing.aliyuncs.com/video/1622209958997-3.png?OSSAccessKeyId=LTAI5tHwPZ8oNK3N7FSbvKHy&Expires=1622213953&Signature=Rw1XqZwEA%2BPVJ9gCr3qpfy5a920%3D
                System.out.println(video.getVideo_path());
                videoDAO.update(video);
                map.put("message","文件上传成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message","文件上传失败");
        }*/

        //截取视频图片
        //1.获取文件名
        try {
            String originalFilename = video_path.getOriginalFilename();
            if (!originalFilename.isEmpty()||!originalFilename.equals("")) {
                //生成新文件名+时间戳
                String newName = new Date().getTime() + "-" + originalFilename;
                //文件名拼接
                String objectName = "video/" + newName;
                //2.文件上传至阿里云
                AliyunOSSUtil.uploadBytesFile("gbyingx-2010", objectName, video_path);


                String realPath = request.getSession().getServletContext().getRealPath("/upload/cover/");
                File file = new File(realPath);
                if(!file.exists()){
                    file.mkdirs();
                }
                //视频截取并拼接
                //拆分
                String[] split = newName.split("\\.");
                String names=split[0];
                String coverName=names+".jpg";
                //拼接视频封面的本地路径
                String coverPath=realPath+"/"+coverName;
                //3.根据阿里云的视频截取封面
                VideoInterceptUtil.fetchFrame("http://gbyingx-2010.oss-cn-beijing.aliyuncs.com/"+objectName,coverPath);
                //4.将封面上传至阿里云  文件上传
                AliyunOSSUtil.uploadLocalFileAliyun("gbyingx-2010",coverName,coverPath);

                //删除本地封面
                File file1 = new File(realPath, coverName);
                if (file1.exists()&&file1.isFile()){
                    file1.delete();
                }

                //5.修改视频封面路径
                //修改文件信息
                Video video = new Video();
                video.setId(id);
                video.setVideo_path("http://gbyingx-2010.oss-cn-beijing.aliyuncs.com/"+objectName); //http://gbyingx-2010.oss-cn-beijing.aliyuncs.com/video/1622209958997-3.png?OSSAccessKeyId=LTAI5tHwPZ8oNK3N7FSbvKHy&Expires=1622213953&Signature=Rw1XqZwEA%2BPVJ9gCr3qpfy5a920%3D
                video.setCover_path("http://gbyingx-2010.oss-cn-beijing.aliyuncs.com/"+coverName);
                System.out.println(video.getVideo_path());
                videoDAO.update(video);
                map.put("message","文件上传成功");

            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message","文件上传失败");
        }
        return map;
    }

    @Override
    public  List<VideoVO> queryByReleaseTimes() {
        List<VideoVO> videos = videoDAO.queryByReleaseTimes();
        return videos;
    }

  /*  @Override
    public List<Video> queryCateVideo(String cate_id) {
        List<Video> videos = videoDAO.queryCateVideo(cate_id);
        return videos;
    }*/

    /*public void videoUpload(MultipartFile video_path, String videoId){
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = "https://oss-cn-beijing.aliyuncs.com。";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAI5tHwPZ8oNK3N7FSbvKHy";
        String accessKeySecret = "bgxHrfpEwPEXcEal36rtcVed2veNUO";
        String bucketName="gbyingx-2010";
        String objectName="";
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId,accessKeySecret);

        // 填写Byte数组。
       // byte[] content = "Hello OSS".getBytes();
        byte[] videoPathBytes = new byte[0];
        try {
            videoPathBytes = video_path.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 填写Bucket名称和Object完整路径。Object完整路径中不能包含Bucket名称。
        ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(videoPathBytes));

        // 关闭OSSClient。
        ossClient.shutdown();
    }*/
}
