package com.example.startdemo.business.service.impl;

import com.example.startdemo.business.dao.UserDAO;
import com.example.startdemo.business.service.TestService;
import com.example.startdemo.common.base.ApiResponse;
import com.example.startdemo.common.utils.redis.RedisUtils;
import com.example.startdemo.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

/**
 * @author zhangboqing
 * @date 2019/4/1
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public ApiResponse test2() {
        RedisUtils.set("zbq2","zbq2");

        System.out.println(RedisUtils.get("zbq2",String.class));
        return null;
    }

    @Override
    public ApiResponse<User> test3(User user) {
        userDAO.insertSelective(user);

        return ApiResponse.builder().setData(user);
    }

    @Override
    @Transactional
    public ApiResponse test() {

        User user1 = userDAO.selectByPrimaryKey(1);
        System.out.println(user1);




        User user = new User();
        user.setUserName("a");
        user.setPhone("12121");
        user.setPassword("2121");
        userDAO.insertSelective(user);

        int a = 1/0;

        return ApiResponse.builder().setData(null);
    }

    @Override
    public ApiResponse test3(HttpSession session) {
        /**校验输入的验证码是否正确*/
        String saveRand = (String) session.getAttribute("rand");

        User user1 = userDAO.selectByPrimaryKey(1);
        System.out.println(user1);




        User user = new User();

        user.setUserName("a");
        user.setPhone("12121");
        user.setPassword("2121");
        userDAO.insertSelective(user);

        int a = 1/0;

        return ApiResponse.builder().setData(null);
    }



    public static void main(String[] args) {
        System.out.println(1);
    }

}
