package com.baizhi.yingx_ghb.controller;

import com.alibaba.fastjson.JSON;
import com.baizhi.yingx_ghb.entity.Admin;
import com.baizhi.yingx_ghb.service.AdminService;
import com.baizhi.yingx_ghb.util.ImageCodeUtil;
import com.baizhi.yingx_ghb.util.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("login")
    public String login(String username, String password, String codess, HttpServletRequest request, HttpServletResponse response){

        //调用service的方法
        Admin login = adminService.login(username);
        Map<String,Object> map=new HashMap<String,Object>();

        //获取session中的验证码
        String code1 = (String) request.getSession().getAttribute("code");

        //设置响应输出流的编码格式
        response.setCharacterEncoding("UTF-8");
        //获取响应输出流
        PrintWriter writer=null;
        try {

            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //判断验证码是否正确
        if(codess.equals(code1)){
            map.put("message","验证码正确");
            map.put("status",100);
            //判断用户是否存在
            if (login!=null){
                String salt = login.getSalt();
                String md5Code = Md5Utils.getMd5Code(salt + password);
                //存在判断密码是否正确
                if(md5Code.equals(login.getPassword())){
                    map.put("message","登录成功");
                    map.put("status",100);
                    //存进session作用域
                    request.getSession().setAttribute("login",login);
                }else{
                    //错误
                    map.put("message","密码错误");
                    map.put("status",104);
                }
            }else {
                //用户不存在
                map.put("message","用户不存在");
                map.put("status",104);
            }

        }else{
            //验证码错误
            map.put("message","验证码错误");
            map.put("status",104);
        }
        //将map转换为json字符串
        String toJSONString = JSON.toJSONString(map);
        //输出json字符串
        writer.println(toJSONString);
        return null;
    }

    @RequestMapping("loginOut")
    public String loginOut(HttpServletRequest request){
        request.getSession().removeAttribute("login");
        return "redirect:/login/login.jsp";
    }


    //验证码
    @RequestMapping("codes")
    public String queryCode(HttpServletRequest request,HttpServletResponse response){
        //生成验证码
        String code = ImageCodeUtil.getSecurityCode();
        request.getSession().setAttribute("code",code);
        //生成验证码图片
        BufferedImage image = ImageCodeUtil.createImage(code);
        //将验证码输出到浏览器客户端
        ServletOutputStream stream = null;
        try {
            stream = response.getOutputStream();
            ImageIO.write(image,"png",stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @RequestMapping("queryAll")
    @ResponseBody
    public Map<String, Object> queryAll(Integer page,Integer rows){
        Map<String, Object> map = adminService.queryBylevels(page, rows);
        return map;
    }

    @RequestMapping("edits")
    @ResponseBody
    public Object queryEdits(String oper,Admin admin,String id){

        if(oper.equals("add")){
            //调用add方法
            adminService.add(admin);
        }
        if (oper.equals("edit")){
            //调用修改的方法
            adminService.update(admin);
        }
        if (oper.equals("del")){
            //调用删除
            adminService.delete(id);
        }
        return null;
    }


}
