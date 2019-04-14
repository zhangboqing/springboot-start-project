package com.example.startdemo.common.constant.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhangboqing
 * @date 2019/4/14
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AccessLimit {
    /**
     * 多少秒内
     */
    int seconds();

    /**
     * 最大请求次数
     */
    int maxCount();
}
