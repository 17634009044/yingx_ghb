package com.baizhi.yingx_ghb.serviceimpl;

import com.baizhi.yingx_ghb.dao.AdminDAO;
import com.baizhi.yingx_ghb.entity.Admin;
import com.baizhi.yingx_ghb.service.AdminService;
import com.baizhi.yingx_ghb.util.Md5Utils;
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
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminDAO adminDAO;

    @Override
    public Admin login(String username) {

        Admin admin = adminDAO.queryName(username);

        return admin;
    }

    @Override
    public void add(Admin admin) {
        admin.setId(UUID.randomUUID().toString());
        String salt = Md5Utils.getSalt(6);
        admin.setSalt(salt);
        String md5Code = Md5Utils.getMd5Code(salt + admin.getPassword());
        admin.setPassword(md5Code);
        adminDAO.add(admin);
    }

    @Override
    public void update(Admin admin) {
        adminDAO.update(admin);
    }

    @Override
    public void delete(String id) {
        adminDAO.delete(id);
    }

    @Override
    public Map<String, Object> queryBylevels(Integer page, Integer rows) {

        HashMap<String , Object> map = new HashMap<>();
        //设置当前页
        map.put("page",page);
        //查询总条数
        Integer count = adminDAO.queryCount();
        map.put("records",count);
        //总页数 总页数=总条数/每页展示条数
        int total = count % rows == 0 ? count / rows : count / rows + 1;
        map.put("total",total);

        List<Admin> list = adminDAO.queryBylevels("2",(page - 1) * rows, rows);
        map.put("rows",list);
        return map;
    }


}
