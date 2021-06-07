package com.baizhi.yingx_ghb.controller;

import com.baizhi.yingx_ghb.entity.User;
import com.baizhi.yingx_ghb.service.UserService;
import com.baizhi.yingx_ghb.util.Sample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController {

    //注入service
    @Autowired
    private UserService userService;

    @RequestMapping("queryAll")
    @ResponseBody
    public  Map<String, Object> queryAll(Integer page,Integer rows){
        Map<String, Object> all = userService.queryAll(page, rows);
        return all;

    }

    @RequestMapping("updateStatus")
    @ResponseBody
    public String updateStatus(String id,String status){
        userService.update(id,status);
        return null;
    }


    @ResponseBody
    @RequestMapping("edits")
    public String add(User user,String id,String oper){
        String idd =null;
        if(oper.equals("add")){
            idd = userService.add(user);
        }
        if (oper.equals("edit")){
            userService.update(user);
        }
        if (oper.equals("del")){
            userService.delete(id);
        }
        return idd;
    }


    @RequestMapping("fileUpload")
    @ResponseBody
    public  HashMap<String, Object> fileUpload(MultipartFile head_img,String userId){
        System.out.println(head_img.getOriginalFilename());
        System.out.println(head_img.getSize());

        HashMap<String, Object> map = userService.fileUpload(head_img, userId);
        return map;
    }


    @RequestMapping("sendPhoneCode")
    @ResponseBody
    public  HashMap<String, Object> sendPhoneCode(String phone){
        HashMap<String, Object> map = new HashMap<>();
        //1.调用生成验证码随机数的方法
        String random = Sample.getRandom(6);
        String message=null;
        //2.调用发送验证码的方法
        try {
             message=Sample.sendPhoneCode(phone,random);
            if (message.equals("发送成功")){
                map.put("message",message);
                map.put("status",100);
            }else {
                map.put("message",message);
                map.put("status",104);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message",message);
            map.put("status",104);
        }
        return map;
    }
}
