package com.baizhi.yingx_ghb.dao;

import com.baizhi.yingx_ghb.entity.FeedBack;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FeedBackDAO {



    List<FeedBack> queryAll(@Param("begin") Integer begin,@Param("end") Integer end);

    Integer queryCount();
}
