package com.baizhi.yingx_ghb.controller;

import com.baizhi.yingx_ghb.entity.Category;
import com.baizhi.yingx_ghb.entity.Video;
import com.baizhi.yingx_ghb.service.CategoryService;
import com.baizhi.yingx_ghb.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private VideoService videoService;
    

    @RequestMapping("queryPageOne")
    @ResponseBody
    public Map<String,Object> queryPageOne(Integer page,Integer rows){
        Map<String, Object> map = categoryService.queryAllPage("1", page, rows);
        return map;
    }

    @ResponseBody
    @RequestMapping("queryPageTwo")
    public Map<String,Object> queryPageTwo(String id,Integer page,Integer rows){
        Map<String, Object> map = categoryService.queryAllPage("2", page, rows);
        //
        List<Category> categories = (List<Category>) map.get("rows");

       //遍历集合
        Iterator<Category> iterator = categories.iterator();
        //hasNext：没有指针下移操作，只是判断是否存在下一个元素
        while(iterator.hasNext()){
            //next：指针下移，返回该指针所指向的元素
            Category next = iterator.next();
            if (!next.getParent_id().equals(id)){
                //删除当前指针所指向的元素
                iterator.remove();
            }
           /* if(next.getId().equals(cate_id)){
                List<Video> videos = videoService.queryCateVideo(cate_id);
            }*/
        }
        return map;
    }

    @ResponseBody
    @RequestMapping("edits")
    public String add(Category category,String oper,String id,String parentId){

        System.out.println(id);
        //HashMap<String, Object> map = new HashMap<>();
        if (oper.equals("add")){
            //1.判断添加的是一级类别还是二级类别
            if(parentId!=null){
                category.setLevels("2");
                category.setParent_id(parentId);
            }else{
                category.setLevels("1");
                category.setParent_id(null);
            }
            categoryService.add(category);
        }
        if (oper.equals("edit")){
            categoryService.update(category);
        }


        if (oper.equals("del")){
           /* String[] split = id.split(",");
            System.out.println(split[0]);*/
            //根据id查询
            Category queryOne = categoryService.queryOne(id);
            System.out.println(queryOne);
            //判断1级别下是否有二级类别
            if (queryOne.getLevels().equals("1")&&queryOne.getCate().isEmpty()){

                categoryService.delete(id);
            }
            if (queryOne.getLevels().equals("2")&&queryOne.getVideos()==null){
                categoryService.delete(id);
            }

        }
        return null;
    }
}
