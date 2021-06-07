package com.baizhi.yingx_ghb.serviceimpl;

import com.baizhi.yingx_ghb.dao.LogDAO;
import com.baizhi.yingx_ghb.entity.Log;
import com.baizhi.yingx_ghb.service.LogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class LogServiceImpl implements LogService {


    @Resource
    private LogDAO logDAO;


    @Override
    public Map<String,Object> queryPage(Integer page, Integer rows) {
        Map<String,Object> map=new HashMap<>();
        //设置当前页
        map.put("page",page);
        //总条数
        Integer count = logDAO.queryCount();
        map.put("records",count);
        //总页数 总页数=总条数/每页展示条数 
        int total = count % rows == 0 ? count / rows : count / rows + 1;
        map.put("total",total);

        List<Log> logs = logDAO.queryPage((page - 1) * rows, rows);
        map.put("rows",logs);

        return map;
    }

    @Override
    public void add(Log log) {
        logDAO.add(log);
    }

    @Override
    public List<Log> queryAll() {
        List<Log> logs = logDAO.queryAll();

        return logs;
    }
}
