package com.example.startdemo.common.constant.enums;

import com.example.startdemo.common.constant.exception.IApiResponseCode;
import lombok.Getter;

/**
 * @author zhangboqing
 * @date 2019/4/1
 */
public enum ApiResponseCodeEnum implements IApiResponseCode {
    SUCCESS(0, "成功"),
    FAILED(1, "失败"),
    COMMON_FAILED_CODE(100002, "通用错误码");


    @Getter
    public Integer code;
    @Getter
    public String message;


    private ApiResponseCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "ApiResponseCodeEnum(code=" + this.getCode() + ", message=" + this.getMessage() + ")";
    }
}
