package com.baizhi.yingx_ghb.serviceimpl;

import com.baizhi.yingx_ghb.annotation.AddLog;
import com.baizhi.yingx_ghb.dao.UserDAO;
import com.baizhi.yingx_ghb.entity.User;
import com.baizhi.yingx_ghb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
@Transactional
public class UserServiceimpl implements UserService {

    //注入DAO
    @Resource
    UserDAO userDAO;

    @Autowired
    HttpServletRequest request;
    @Override
    public Map<String, Object> queryAll(Integer page, Integer rows) {

        HashMap<String, Object> map = new HashMap<>();
        //设置当前页
        map.put("page",page);
        //查询总条数
        Integer count = userDAO.queryCount();
        map.put("records",count);

        //计算总页数 总页数=总条数/每页展示的条数
        int total = count % rows == 0 ? count / rows : count / rows + 1;
        map.put("total",total);

        List<User> users = userDAO.queryAll((page - 1) * rows, rows);
        map.put("rows",users);

        return map;
    }
    @AddLog("修改用户状态")
    @Override
    public void update(String id,String status) {
        userDAO.updateStatus(id,status);
    }
    @AddLog("添加用户")
    @Override
    public String add(User user) {
        user.setId(UUID.randomUUID().toString());
        user.setStatus("1");
        user.setWechat(user.getPhone());
        user.setRegist_time(new Date());
        userDAO.add(user);
        return user.getId();
    }
    @AddLog("修改用户")
    @Override
    public void update(User user) {
        userDAO.update(user);
    }
    @AddLog("删除用户")
    @Override
    public void delete(String id) {
        userDAO.delete(id);
    }

    @Override
    public HashMap<String, Object> fileUpload(MultipartFile head_img, String userId) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            //获取文件名
            String filename = head_img.getOriginalFilename();
            System.out.println(filename);
            //文件名拼接时间戳
            String newName=new Date().getTime()+"-"+filename;
            //根据相对路径获取绝对路径
            String realPath = request.getSession().getServletContext().getRealPath("/upload/headImg");
            //判断文件夹
            File file = new File(realPath);
            if (!file.exists()){
                file.mkdirs();
            }
            //文件上传
            head_img.transferTo(new File(realPath,newName));
            map.put("message","文件上传成功");

            System.out.println(newName);


            System.out.println("userId++++++++++++++++++++++++++："+userId);
            User user = new User();
            user.setId(userId);
            user.setHead_img(newName);
            System.out.println(user.getId());
            //修改文件的信息
            userDAO.update(user);



        } catch (IOException e) {
            e.printStackTrace();
            map.put("message","文件上传失败");
        }
        return map;
    }

    @Override
    public List<User> queryAlls() {

        List<User> users = userDAO.queryAlls();

        return users;
    }
}
