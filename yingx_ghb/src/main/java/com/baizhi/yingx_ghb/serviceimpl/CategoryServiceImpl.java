package com.baizhi.yingx_ghb.serviceimpl;

import com.baizhi.yingx_ghb.annotation.AddLog;
import com.baizhi.yingx_ghb.dao.CategoryDAO;
import com.baizhi.yingx_ghb.entity.Category;
import com.baizhi.yingx_ghb.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    //注入DAO
    @Resource
    private CategoryDAO categoryDAO;

    @AddLog("添加类别")
    @Override
    public void add(Category category) {
        category.setId(UUID.randomUUID().toString());

        categoryDAO.add(category);
    }
    @AddLog("修改类别")
    @Override
    public void update(Category category) {
        categoryDAO.update(category);
    }
    @AddLog("删除类别")
    @Override
    public void delete(String id) {
        categoryDAO.delete(id);
    }


    @Override
    public Map<String, Object> queryAllPage(String levels,Integer page, Integer rows) {

        HashMap<String, Object> map = new HashMap<>();
        //设置当前页
        map.put("page",page);
        //总条数
        Integer count = categoryDAO.queryCount();
        map.put("records",count);
        //总页数 总页数=总条数/每页展示的条数
        int total = count % rows == 0 ? count / rows : count / rows + 1;
        map.put("total",total);

        List<Category> categories = categoryDAO.queryAllPage(levels, (page - 1) * rows, rows);
        map.put("rows",categories);


        return map;
    }

    @Override
    public Category queryOne(String id) {

        Category queryOne = categoryDAO.queryOne(id);
        return queryOne;
    }

    @Override
    public List<Category> queryTwo(String parent_id) {
        List<Category> queryTwo = categoryDAO.queryTwo(parent_id);
        return queryTwo;
    }

    @Override
    public List<Category> queryAll() {
        List<Category> list = categoryDAO.queryAll("1");
        return list;
    }
}
