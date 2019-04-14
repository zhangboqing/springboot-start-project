package com.example.startdemo.config.interceptor.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author zhangboqing
 * @date 2018/10/30
 */
@Data
@Builder
public class PostRequestLogInfoModel {

    private Object requestUUID;
    private Object requestTime;
    private Object responseTime;
    private Object handleTime;
}
