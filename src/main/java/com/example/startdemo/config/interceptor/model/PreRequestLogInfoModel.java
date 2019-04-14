package com.example.startdemo.config.interceptor.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author zhangboqing
 * @date 2018/10/30
 */
@Data
@Builder
public class PreRequestLogInfoModel {
    private Object requestUUID;
    private Object requestURI;
    private Object method;
    private Object getParameter;
    private Object postParameter;
}
