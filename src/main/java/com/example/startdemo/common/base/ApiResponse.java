package com.example.startdemo.common.base;

import com.example.startdemo.common.constant.enums.ApiResponseCodeEnum;
import com.example.startdemo.common.constant.exception.IApiResponseCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("返回值Model")
public class ApiResponse<T> implements Serializable {
    @ApiModelProperty("返回码")
    private int code;
    @ApiModelProperty("返回信息")
    private String message;
    @ApiModelProperty("返回值")
    private T data;

    public static ApiResponse builder() {
        return new ApiResponse();
    }

    public ApiResponse() {
        this.code = ApiResponseCodeEnum.SUCCESS.code;
        this.message = ApiResponseCodeEnum.SUCCESS.message;
    }

    public ApiResponse(ApiResponseCodeEnum responseCode) {
        this.code = responseCode.code;
        this.message = responseCode.message;
    }

    public ApiResponse(ApiResponseCodeEnum responseCode, String message) {
        this.code = responseCode.code;
        this.message = message;
    }

    public ApiResponse(IApiResponseCode responseCode, String message) {
        this.code = responseCode.getCode();
        this.message = message;
    }

    public ApiResponse(IApiResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
    }

    public ApiResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ApiResponse setResponseCodeAndMessage(ApiResponseCodeEnum responseCode) {
        this.code = responseCode.code;
        this.message = responseCode.message;
        return this;
    }

    public ApiResponse setResponseCodeAndMessage(ApiResponseCodeEnum responseCode, String message) {
        this.code = responseCode.code;
        this.message = message;
        return this;
    }

    public ApiResponse setResponseCodeAndMessage(IApiResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
        return this;
    }

    public ApiResponse setResponseCodeAndMessage(IApiResponseCode responseCode, String message) {
        this.code = responseCode.getCode();
        this.message = message;
        return this;
    }

    public ApiResponse setResponseCodeAndMessage(int code, String message) {
        this.code = code;
        this.message = message;
        return this;
    }

    public ApiResponse setCode(int code) {
        this.code = code;
        return this;
    }

    public ApiResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public ApiResponse setData(T data) {
        this.data = data;
        return this;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public T getData() {
        return this.data;
    }

    @Override
    public String toString() {
        return "ApiResponse(code=" + this.getCode() + ", message=" + this.getMessage() + ", data=" + this.getData() + ")";
    }
}