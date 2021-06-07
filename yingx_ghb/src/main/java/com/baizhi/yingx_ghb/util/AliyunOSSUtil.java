package com.baizhi.yingx_ghb.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class AliyunOSSUtil {

    // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
    private static String endpoint = "https://oss-cn-beijing.aliyuncs.com";
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
    private static String accessKeyId = "LTAI5tHwPZ8oNK3N7FSbvKHy";
    private static String accessKeySecret = "bgxHrfpEwPEXcEal36rtcVed2veNUO";


    /*将本地文件上传至阿里云
         参数：bucketName：指定存储空间名
              fileName:文件名
              localPath：本地文件路径
    * */
    public static void uploadLocalFileAliyun(String bucketName,String fileName,String localPath){
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId,accessKeySecret);

        // 创建PutObjectRequest对象。
        // 填写Bucket名称和Object完整路径。Object完整路径中不能包含Bucket名称。
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, new File(localPath));

        // 如果需要上传时设置存储类型和访问权限，请参考以下示例代码。
        // ObjectMetadata metadata = new ObjectMetadata();
        // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
        // metadata.setObjectAcl(CannedAccessControlList.Private);
        // putObjectRequest.setMetadata(metadata);

        // 上传字符串。
        ossClient.putObject(putObjectRequest);

        // 关闭OSSClient。
        ossClient.shutdown();
    }


    /*将文件转为字节数组上传至阿里云
       参数：bucketName：指定存储空间名
            fileName:文件名
            video_path：文件
  * */
    public static void uploadBytesFile(String bucketName, String objectName, MultipartFile video_path){
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId,accessKeySecret);

        // 填写Byte数组。
        // byte[] content = "Hello OSS".getBytes();
        byte[] bytes = new byte[0];
        try {
            bytes = video_path.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 填写Bucket名称和Object完整路径。Object完整路径中不能包含Bucket名称。
        ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(bytes));

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    public static void deleteFileAliyun(String bucketName, String objectName){
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId,accessKeySecret);

        //删除文件
        ossClient.deleteObject(bucketName,objectName);
        // 关闭OSSClient。
        ossClient.shutdown();
    }





    //测试
    public static void main(String[] args) {
        String bucketName="gbyingx-2010";
        String fileName="video/hello.jpg";
        String localPath="C:\\Users\\郭彬961018\\Desktop\\菜谱项目img\\1.png";
        uploadLocalFileAliyun(bucketName,fileName,localPath);
    }
}
