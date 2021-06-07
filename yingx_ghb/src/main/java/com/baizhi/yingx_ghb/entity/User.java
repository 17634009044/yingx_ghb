package com.baizhi.yingx_ghb.entity;


import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Excel(name = "ID")
    private String id;
    @Excel(name = "手机号")
    private String phone;
    @Excel(name = "头像")
    private String head_img;
    @Excel(name = "姓名")
    private String name;
    @Excel(name = "个人签名")
    private String sign;
    @Excel(name = "绑定微信")
    private String wechat;
    @Excel(name = "用户状态")
    private String status;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "注册时间")
    private Date regist_time;
}
