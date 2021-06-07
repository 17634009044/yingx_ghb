package com.baizhi.yingx_ghb.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)  //设置注解使用位置
@Retention(RetentionPolicy.RUNTIME)  //运行时生效
public @interface AddLog {

    String value();
}
