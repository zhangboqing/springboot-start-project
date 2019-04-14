package com.example.startdemo.common.constant.exception;

import com.example.startdemo.common.constant.enums.ApiResponseCodeEnum;

public class ApiException extends RuntimeException {

    private ApiResponseCodeEnum responseCode;
    private IApiResponseCode iApiResponseCode;
    private Integer code;

    public ApiException() {
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ApiException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ApiException(ApiResponseCodeEnum responseCode) {
        super(responseCode.message);
        this.responseCode = responseCode;
        this.code = responseCode.getCode();
    }

    public ApiException(ApiResponseCodeEnum responseCode, String message) {
        super(message);
        this.responseCode = responseCode;
        this.code = responseCode.getCode();
    }

    public ApiException(ApiResponseCodeEnum responseCode, Throwable cause) {
        super(responseCode.message, cause);
        this.responseCode = responseCode;
        this.code = responseCode.getCode();
    }

    public ApiException(ApiResponseCodeEnum responseCode, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(responseCode.message, cause, enableSuppression, writableStackTrace);
        this.responseCode = responseCode;
        this.code = responseCode.getCode();
    }

    public ApiException(IApiResponseCode iApiResponseCode) {
        super(iApiResponseCode.getMessage());
        this.iApiResponseCode = iApiResponseCode;
        this.code = iApiResponseCode.getCode();
    }

    public ApiException(IApiResponseCode iApiResponseCode, String message) {
        super(message);
        this.iApiResponseCode = iApiResponseCode;
        this.code = iApiResponseCode.getCode();
    }

    public ApiException(IApiResponseCode iApiResponseCode, Throwable cause) {
        super(iApiResponseCode.getMessage(), cause);
        this.iApiResponseCode = iApiResponseCode;
        this.code = iApiResponseCode.getCode();
    }

    public ApiException(IApiResponseCode iApiResponseCode, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(iApiResponseCode.getMessage(), cause, enableSuppression, writableStackTrace);
        this.iApiResponseCode = iApiResponseCode;
        this.code = iApiResponseCode.getCode();
    }

    public ApiResponseCodeEnum getResponseCode() {
        return this.responseCode;
    }

    public IApiResponseCode getIApiResponseCode() {
        return this.iApiResponseCode;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setResponseCode(ApiResponseCodeEnum responseCode) {
        this.responseCode = responseCode;
    }

    public void setIApiResponseCode(IApiResponseCode iApiResponseCode) {
        this.iApiResponseCode = iApiResponseCode;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}