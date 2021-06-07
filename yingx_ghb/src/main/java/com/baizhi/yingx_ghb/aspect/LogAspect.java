package com.baizhi.yingx_ghb.aspect;

import com.baizhi.yingx_ghb.annotation.AddLog;
import com.baizhi.yingx_ghb.dao.LogDAO;
import com.baizhi.yingx_ghb.entity.Admin;
import com.baizhi.yingx_ghb.entity.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

@Aspect  //这是一个切面的类
@Configuration  //交给Spring工厂去管理
public class LogAspect {

    @Autowired
    HttpSession session;

    @Resource
    LogDAO logDAO;

    @Around("@annotation(com.baizhi.yingx_ghb.annotation.AddLog)")
    public Object addLog(ProceedingJoinPoint proceedingJoinPoint){
        System.out.println("=====进去环绕通知=======");

        //获取当前登陆人信息  在什么时间做了什么操作  是否成功
        Admin admin = (Admin) session.getAttribute("login");
        //获取管理员名
        String username = admin.getUsername();

        //获取操作的当前时间
        Date date = new Date();

        //获取操作的方法名
        String methodName = proceedingJoinPoint.getSignature().getName();
        System.out.println("方法名:"+methodName);

        //获取方法的签名
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        //获取方法
        Method method = signature.getMethod();
        //获取方法上的指定注解
        AddLog annotation = method.getAnnotation(AddLog.class);
        //获取注解上的属性值
        String s = annotation.value();

        String message=null;
        Object returnResult=null;
        //环绕放行 执行目标方法
        try {
            returnResult=proceedingJoinPoint.proceed();
            message="success(执行成功)";
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            message="error(执行失败)";

        }
        Log log = new Log();
        log.setId(UUID.randomUUID().toString());
        log.setAdmin_name(username);
        log.setOption_time(date);
        log.setMethod("("+s+")"+methodName);
        log.setStatus(message);
        logDAO.add(log);
        return returnResult;
    }
}



