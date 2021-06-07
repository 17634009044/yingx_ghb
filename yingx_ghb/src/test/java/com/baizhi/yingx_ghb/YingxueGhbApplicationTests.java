package com.baizhi.yingx_ghb;

import com.baizhi.yingx_ghb.dao.*;
import com.baizhi.yingx_ghb.entity.*;
import com.baizhi.yingx_ghb.service.AdminService;
import com.baizhi.yingx_ghb.service.CategoryService;
import com.baizhi.yingx_ghb.service.UserService;
import com.baizhi.yingx_ghb.util.Md5Utils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@SpringBootTest(classes = YingxueGhbApplication.class)
@RunWith(SpringRunner.class)
 class YingxueGhbApplicationTests {

    @Autowired
    private AdminDAO adminDAO;

    @Autowired
    private FeedBackDAO feedBackDAO;

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private CategoryDAO categoryDAO;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private VideoDAO videoDAO;

   @Test
    void contextLoads() {
        Admin admin = adminDAO.queryName("admin");
        System.out.println(admin);

      List<Category> categories = categoryDAO.queryTwo("2");
      for (Category category : categories) {
         System.out.println(category);
      }


   }
/*
    @Test
    void queryAll(){
        List<FeedBack> backs = feedBackDAO.queryAll(0,1);
        for (FeedBack back : backs) {
            System.out.println(back);
        }
    }

    @Test
    void queryUser(){
        List<User> users = userDAO.queryAll(0, 20);
        System.out.println(userDAO.queryCount());
        for (User user : users) {
            System.out.println(user);
        }*/
    //}


   /* @Test
    void queryAdmin2(){
        adminService.add(new Admin(null,"huxz","123456","1","2","4sda"));
    }*/
/*
    @Test
    public void show(){
        String salt = Md5Utils.getSalt(6);
        String md5Code = Md5Utils.getMd5Code(salt + "123456");
        System.out.println(salt);
        System.out.println(md5Code);
    }

    @Test
    public void update(){
        User user = new User("0c145696-b918-4665-b73c-44b49503bf12","123","ssss","wwww","sss","111","1",new Date());

        userDAO.update(user);
    }*/



    @Test
    public void cd(){
        Category queryOne = categoryService.queryOne("5");
        System.out.println(queryOne);
    }





  /*  @Test
    public void cate(){
       *//* List<Category> categories = categoryDAO.queryAllPage("2", 0, 10);
        for (Category category : categories) {
            System.out.println(category);
        }*//*
    }*/

  /*  @Test
    public void queryAllVideo(){
      *//* List<Video> videos = videoDAO.queryByReleaseTimes();
       for (Video video : videos) {
            System.out.println(video);
       }
*//*
      *//*  Video video = new Video();
        video.setId("767b196a-34fe-4820-8547-6f309f60d65b");
        video.setVideo_path("http://gbyingx-2010.oss-cn-beijing.aliyuncs.com/"); //http://gbyingx-2010.oss-cn-beijing.aliyuncs.com/video/1622209958997-3.png?OSSAccessKeyId=LTAI5tHwPZ8oNK3N7FSbvKHy&Expires=1622213953&Signature=Rw1XqZwEA%2BPVJ9gCr3qpfy5a920%3D
        videoDAO.update(video);*//*
      *//*  Video video = videoDAO.queryById("9e958858-a483-4758-bc75-b1f35d2d54ca");
        System.out.println(video);
*//*

    }
*/

















}
