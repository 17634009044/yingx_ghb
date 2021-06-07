package com.baizhi.yingx_ghb.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Video {
    private String id;
    private String title;
    private String description;
    private String video_path;
    private String cover_path;
    @DateTimeFormat(pattern = "yyyy_MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date upload_time;
    private String cate_id;
    private String group_id;
    private String user_id;

    private Category category;
    private User user;
}
