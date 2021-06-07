package com.baizhi.yingx_ghb.controller;

import com.baizhi.yingx_ghb.entity.Video;
import com.baizhi.yingx_ghb.service.VideoService;
import com.baizhi.yingx_ghb.util.AliyunOSSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @RequestMapping("queryPage")
    @ResponseBody
    public Map<String,Object> queryPage(Integer page,Integer rows){
        Map<String, Object> map = videoService.queryAll(page, rows);

        return map;
    }

    @RequestMapping("fileUpload")
    @ResponseBody
    public Map<String,Object> fileUpload(MultipartFile video_path,String id){
        HashMap<String, Object> map = videoService.fileUpload(video_path, id);
        return map;
    }

    @RequestMapping("edits")
    @ResponseBody
    public Map<String,Object> edits(String oper, String id, Video video){
        HashMap<String, Object> map = new HashMap<>();
        if (oper.equals("add")){
            String id1= videoService.add(video);
            map.put("message","添加成功");
            map.put("id",id1);
        }
        if (oper.equals("edit")){

            String update = videoService.update(video);
            map.put("id",update);
        }
        if (oper.equals("del")){
            Video video1 = videoService.queryById(id);
            System.out.println(video1.getVideo_path());

            String objectName = video1.getVideo_path().replace("http://gbyingx-2010.oss-cn-beijing.aliyuncs.com/", "");

            System.out.println(objectName);

            String coverName = video1.getCover_path().replace("http://gbyingx-2010.oss-cn-beijing.aliyuncs.com/", "");
            AliyunOSSUtil.deleteFileAliyun("gbyingx-2010",coverName);
            AliyunOSSUtil.deleteFileAliyun("gbyingx-2010",objectName);
            videoService.delete(id);
            map.put("message","删除成功");
        }
        return map;
    }
}
