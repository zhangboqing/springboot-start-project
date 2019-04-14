package com.example.startdemo.config.interceptor;

import com.example.startdemo.common.constant.annotation.AccessLimit;
import com.example.startdemo.common.constant.enums.ApiResponseCodeEnum;
import com.example.startdemo.common.constant.enums.RedisKeyEnum;
import com.example.startdemo.common.constant.exception.ApiException;
import com.example.startdemo.common.utils.redis.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhangboqing
 * @date 2019/4/14
 */
@Slf4j
public class AccessLimitInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //判断请求是否属于方法的请求
        if(handler instanceof HandlerMethod){

            HandlerMethod hm = (HandlerMethod) handler;

            //获取方法中的注解,看是否有该注解
            AccessLimit accessLimit = hm.getMethodAnnotation(AccessLimit.class);
            if(accessLimit == null){
                return true;
            }
            int seconds = accessLimit.seconds();
            int maxCount = accessLimit.maxCount();
            String requestURI = request.getRequestURI();

            //从redis中获取用户访问的次数
            Integer count = RedisUtils.get(RedisKeyEnum.ACCESS_LIMIT_KEY.addToKey(seconds),requestURI,Integer.class);
            if(count == null){
                //第一次访问
                RedisUtils.set(RedisKeyEnum.ACCESS_LIMIT_KEY.addToKey(seconds),requestURI,1);
            }else if(count < maxCount){
                //加1
                RedisUtils.incr(RedisKeyEnum.ACCESS_LIMIT_KEY.addToKey(seconds),requestURI);
            }else{
                throw new ApiException(ApiResponseCodeEnum.COMMON_FAILED_CODE,"访问超出限制了");
            }
        }

        return true;
    }
}
