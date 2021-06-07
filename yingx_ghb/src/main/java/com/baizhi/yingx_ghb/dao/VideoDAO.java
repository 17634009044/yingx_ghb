package com.baizhi.yingx_ghb.dao;

import com.baizhi.yingx_ghb.entity.Video;
import com.baizhi.yingx_ghb.entity.VideoVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoDAO {

    List<Video> queryAll(@Param("begin") Integer begin,@Param("end") Integer end);

    void add(Video video);

    void delete(String id);

    void update(Video video);

    Integer queryCount();

    Video queryById(String id);

    List<VideoVO> queryByReleaseTimes();
}
