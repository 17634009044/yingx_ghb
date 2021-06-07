package com.baizhi.yingx_ghb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedBack {
    private String id;
    private String title;
    private String content;
    private Date feed_time;

    private User user;
}
