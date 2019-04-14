package com.example.startdemo.config.web;

import com.alibaba.fastjson.JSON;
import com.example.startdemo.common.constant.enums.ApiResponseCodeEnum;
import com.example.startdemo.common.constant.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;


/**
 * @author zhangboqing
 * @date 2018/8/3
 */
@Slf4j
public class ParamsValidHandler {


    public static void  handle(Object form, BindingResult br)  {

        checkFieldError(br);
    }



    public static String checkFieldError(BindingResult br)  {
        log.info(br.getTarget().getClass().getName());
        log.info(JSON.toJSONString(br.getTarget()));
        if (br.hasFieldErrors()) {
            String error = br.getFieldError().getDefaultMessage();
            throw new ApiException(ApiResponseCodeEnum.COMMON_FAILED_CODE, error);
        }
        return null;
    }
}
