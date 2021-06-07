package com.baizhi.yingx_ghb.serviceimpl;

import com.baizhi.yingx_ghb.dao.FeedBackDAO;
import com.baizhi.yingx_ghb.entity.FeedBack;
import com.baizhi.yingx_ghb.service.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class FeedBackServiceImpl implements FeedBackService {

    //注入DAO
    @Resource
    private FeedBackDAO feedBackDAO;


    @Override
    public Map<String,Object> queryAll(Integer page, Integer rows) {

        HashMap<String, Object> map = new HashMap<>();
        //设置当前页
        map.put("page",page);

        //查询总条数
        Integer count = feedBackDAO.queryCount();
        map.put("records",count);
        //计算总页数 总页数=总条数/每页展示的条数
        int total = count % rows == 0 ? count / rows : count / rows + 1;
        map.put("total",total);

        List<FeedBack> backs = feedBackDAO.queryAll((page-1)*rows,rows);
        map.put("rows",backs);

        return map;
    }
}



 /*   HashMap<String, Object> map = new HashMap<>();
//设置当前页
        map.put("page",page);

                //查询总条数
                Integer count = empDao.queryCount();
                map.put("records",count);

                //计算总页数 总页数=总条数/每页展示的条数
                Integer total = count%rows==0 ? count/rows : count/rows+1;
                map.put("total",total);

                List<Emp> emps = empDao.selectAll((page - 1) * rows, rows);

        map.put("rows",emps);
        return map;
*/