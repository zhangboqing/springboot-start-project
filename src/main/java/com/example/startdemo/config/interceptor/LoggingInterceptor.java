package com.example.startdemo.config.interceptor;

import com.alibaba.fastjson.JSON;
import com.example.startdemo.common.utils.JodaTimeUtils;
import com.example.startdemo.common.utils.RandomCodeUtils;
import com.example.startdemo.config.filter.HttpHelperUtils;
import com.example.startdemo.config.interceptor.model.PostRequestLogInfoModel;
import com.example.startdemo.config.interceptor.model.PreRequestLogInfoModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author zhangboqing
 * @date 2018/10/13
 * <p>
 * 日志打印拦截
 */
@Slf4j
public class LoggingInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //生成这次请求的唯一标识
        String requestUUID = RandomCodeUtils.getUUID();
        long logStartTime = System.currentTimeMillis();

        //记录开始时间
        request.setAttribute("logStartTime", logStartTime);
        request.setAttribute("requestUUID",requestUUID);

        //请求日志记录
        preRequestLoggin(request,requestUUID);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long logEndTime = System.currentTimeMillis();
        long logStartTime = (Long)request.getAttribute("logStartTime");
        String requestUUID = (String)request.getAttribute("requestUUID");

        //记录整个请求的执行时间
        loggingHandleTime(requestUUID,logStartTime,logEndTime);
    }

    private void loggingHandleTime(String requestUUID, long logStartTime, long logEndTime) {
        String logInfo = getLoggingHandleTime(requestUUID,logStartTime,logEndTime);
        log.info("[请求拦截日志信息]:{}", logInfo);
    }

    private String getLoggingHandleTime(String requestUUID, long logStartTime, long logEndTime) {
        PostRequestLogInfoModel build = PostRequestLogInfoModel.builder()
                .requestUUID(requestUUID)
                .requestTime(JodaTimeUtils.timestampToString(logStartTime / 1000, JodaTimeUtils.DateFormat.DATETIME_FORMAT))
                .responseTime(JodaTimeUtils.timestampToString(logEndTime / 1000, JodaTimeUtils.DateFormat.DATETIME_FORMAT))
                .handleTime((logEndTime - logStartTime) + "ms").build();

        return JSON.toJSONString(build);
    }

    /**
     * 请求日志记录
     *
     * @param request
     */
    private void preRequestLoggin(HttpServletRequest request,String requestUUID) {
        //获取相关参数
        //请求地址
        String requestURI = request.getRequestURI();
        //请求方法
        String method = request.getMethod();
        //请求参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        String bodyString = "";
        try {
            bodyString = HttpHelperUtils.getBodyString(request);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String reqestLogInfo = getRequestLogInfo(requestURI, method, parameterMap, bodyString,requestUUID);
        log.info("[请求拦截日志信息]:{}", reqestLogInfo);
    }

    private String getRequestLogInfo(String requestURI, String method, Map<String, String[]> getParameterMap, String postBodyString,String requestUUID) {

        PreRequestLogInfoModel build = PreRequestLogInfoModel.builder()
                .requestUUID(requestUUID)
                .requestURI(requestURI)
                .method(method)
                .getParameter(getParameterMap)
                .postParameter(postBodyString).build();

        return JSON.toJSONString(build);
    }

}
