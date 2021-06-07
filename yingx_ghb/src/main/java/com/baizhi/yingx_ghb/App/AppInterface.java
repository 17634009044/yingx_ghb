package com.baizhi.yingx_ghb.App;


import com.baizhi.yingx_ghb.entity.CateVO;
import com.baizhi.yingx_ghb.entity.Category;
import com.baizhi.yingx_ghb.entity.Video;
import com.baizhi.yingx_ghb.entity.VideoVO;
import com.baizhi.yingx_ghb.service.CategoryService;
import com.baizhi.yingx_ghb.service.VideoService;
import com.baizhi.yingx_ghb.util.Sample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Controller
@RequestMapping("app")
public class AppInterface {

    @Autowired
    private VideoService videoService;

    @Autowired
    private CategoryService categoryService;

   //方式验证码的接口
    @RequestMapping("getPhoneCode")
    public HashMap<String, Object> getPhoneCode(String phone){

        HashMap<String, Object> map = new HashMap<>();
        //1.调用生成验证码随机数的方法
        String random = Sample.getRandom(6);
        String message=null;
        //2.调用发送验证码的方法
        try {
            message=Sample.sendPhoneCode(phone,random);
            if (message.equals("发送成功")){
                map.put("data",phone);
                map.put("message",message);
                map.put("status",100);
            }else {
                map.put("data",null);
                map.put("message",message);
                map.put("status",104);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("data",null);
            map.put("message",message);
            map.put("status",104);
        }

        return map;
    }

    @RequestMapping("queryByReleaseTime")
    public HashMap<String, Object> queryByReleaseTime(){
        HashMap<String, Object> map = new HashMap<>();
        try {
            List<VideoVO> videoPOS = videoService.queryByReleaseTimes();
            map.put("data",videoPOS);
            map.put("message","查询成功");
            map.put("status",100);

        } catch (Exception exception) {
            exception.printStackTrace();
            map.put("data",null);
            map.put("message","查询失败");
            map.put("status",104);
        }

        return map;
    }

    @RequestMapping("queryAllCategory")
    public HashMap<String, Object> queryAllCategory(){
        HashMap<String, Object> map = new HashMap<>();

        try{
            //查询所有一级类别
            List<Category> categoryList = categoryService.queryAll();
            //创建集合
            List<CateVO> cateVOS = new ArrayList<>();
            //遍历的同时查询二级
            for (Category category : categoryList) {
                //查询二级类别
                List<Category> queryTwo = categoryService.queryTwo(category.getId());
                //创建集合
                List<CateVO> cateVOss = new ArrayList<>();
                //遍历并赋值
                for (Category cc : queryTwo) {
                       CateVO cateVO1=new CateVO(cc.getId(),cc.getCate_name(),cc.getLevels(),cc.getParent_id(),null);
                       cateVOss.add(cateVO1);
                }
                //创建对象并赋值给集合
                CateVO cateVO = new CateVO();
                cateVO.setId(category.getId());
                cateVO.setCateName(category.getCate_name());
                cateVO.setLevels(category.getLevels());
                cateVO.setParentId(category.getParent_id());
                cateVO.setCategoryList(cateVOss);
                cateVOS.add(cateVO);
            }

            map.put("data",cateVOS);
            map.put("message","查询成功");
            map.put("status",100);

        }catch (Exception exception) {
            exception.printStackTrace();
            map.put("data",null);
            map.put("message","查询失败");
            map.put("status",104);
        }

        return map;
    }
}
