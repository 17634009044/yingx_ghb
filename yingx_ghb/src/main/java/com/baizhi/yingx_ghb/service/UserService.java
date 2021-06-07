package com.baizhi.yingx_ghb.service;

import com.baizhi.yingx_ghb.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UserService {

    Map<String,Object> queryAll(Integer page, Integer rows);

    void update(String id,String status);


    String add(User user);

    void update(User user);

    void delete(String id);

    HashMap<String, Object> fileUpload(MultipartFile head_img, String userId);

    List<User> queryAlls();
}
