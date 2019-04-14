package com.example.startdemo.business.service;

import com.example.startdemo.common.base.ApiResponse;
import com.example.startdemo.domain.entity.User;

import javax.servlet.http.HttpSession;

/**
 * @author zhangboqing
 * @date 2019/4/1
 */
public interface TestService {
    ApiResponse test();
    ApiResponse test2();

    ApiResponse<User> test3(User user);
    ApiResponse test3(HttpSession session) ;
}
