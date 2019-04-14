package com.example.startdemo.business.controller;

import com.alibaba.fastjson.JSON;
import com.example.startdemo.business.service.TestService;
import com.example.startdemo.common.base.ApiResponse;
import com.example.startdemo.domain.entity.User;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangboqing
 * @date 2019/4/1
 */
@RestController
@RequestMapping(value = "/")
@Slf4j
public class TestController {

    @Autowired
    private TestService testService;

    @ApiOperation(value = "用户管理", notes = "插入用户")
    @PostMapping(value = "/test")
    public ApiResponse<User> test(User user) {
        log.debug("debug log test");
        log.info("info log test");
        log.warn("warn log test");
        log.error("error log test");
//        testService.test2();
        return testService.test3(user);
    }



}
