package com.baizhi.yingx_ghb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.baizhi.yingx_ghb.dao")
@SpringBootApplication
public class YingxueGhbApplication {

    public static void main(String[] args) {
        SpringApplication.run(YingxueGhbApplication.class, args);
    }

}
