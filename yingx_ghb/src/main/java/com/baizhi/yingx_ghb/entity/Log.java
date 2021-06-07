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
public class Log {
    @Excel(name = "ID",width = 38)
    private String id;
    @Excel(name = "管理员名字",width = 38)
    private String admin_name;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "操作时间",width = 38)
    private Date option_time;
    @Excel(name = "操作方法",width = 38)
    private String method;
    @Excel(name = "操作状态",width = 38)
    private String status;

}
