package com.baizhi.yingx_ghb.excelcontroller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.yingx_ghb.entity.Log;
import com.baizhi.yingx_ghb.entity.User;
import com.baizhi.yingx_ghb.service.LogService;
import com.baizhi.yingx_ghb.service.UserService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RestController
@RequestMapping("excel")
public class ExcelAllController {

    @Autowired
    private LogService logService;
    @Autowired
    private UserService userService;

    @RequestMapping("exportLog")
    public String exportLogFile(){
        List<Log> logs = logService.queryAll();
        //创建导出参数对象,设置导出的参数  参数:大标题名,二级标题,目录名
        ExportParams exportParams = new ExportParams("应学系统日志信息","管理员日志", "管理员操作日志");

        //创建Excel文档对象 并设置导出参数     参数:设置导出的参数,要导出的实体类对象,要导出的数据集合
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, Log.class, logs);

        try {
            //导出
            workbook.write(new FileOutputStream(new File("D:\\Excel\\LogEasyPoi.xls")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "main/main";
    }

    @RequestMapping("exportUser")
    public String exportUser() {
        //从后台查询所有数据
        List<User> users = userService.queryAlls();
        //创建导出参数对象,设置导出的参数  参数:大标题名,二级标题,目录名
        ExportParams exportParams = new ExportParams("图片信息导出测试", "图片信息");

        //创建Excel文档对象 并设置导出参数     参数:设置导出的参数,要导出的实体类对象,要导出的数据集合
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, User.class, users);

        try {
            //导出
            workbook.write(new FileOutputStream(new File("D:\\Excel\\UserEasyPoi.xls")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "main/main";
    }


}
