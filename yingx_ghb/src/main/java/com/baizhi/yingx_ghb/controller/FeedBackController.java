package com.baizhi.yingx_ghb.controller;

import com.baizhi.yingx_ghb.entity.FeedBack;
import com.baizhi.yingx_ghb.service.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("feedback")
public class FeedBackController {

    //注入service
    @Autowired
    private FeedBackService feedBackService;

    @RequestMapping("queryAll")
    @ResponseBody
    public Map<String,Object> queryAll(Integer page, Integer rows){
        Map<String, Object> map = feedBackService.queryAll(page,rows);

        return map;
    }
}
