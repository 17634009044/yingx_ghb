package com.baizhi.yingx_ghb.controller;

import com.baizhi.yingx_ghb.entity.Log;
import com.baizhi.yingx_ghb.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("log")
public class LogController {

    @Autowired
    private LogService logService;

    @RequestMapping("queryPage")
    @ResponseBody
    public Map<String,Object> queryPage(Integer page, Integer rows, Log log){

        Map<String, Object> map = logService.queryPage(page, rows);
        return  map;
    }

    @RequestMapping("add")
    @ResponseBody
    public void add(Log log){
        logService.add(log);
    }
}
