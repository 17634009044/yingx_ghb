package com.baizhi.yingx_ghb.service;

import com.baizhi.yingx_ghb.entity.Video;
import com.baizhi.yingx_ghb.entity.VideoVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface VideoService {
    Map<String,Object> queryAll(Integer page, Integer rows);

    String add(Video video);

    void delete(String id);

    String update(Video video);

    Video queryById(String id);

    HashMap<String, Object> fileUpload(MultipartFile video_path, String id);

    List<VideoVO> queryByReleaseTimes();
}
