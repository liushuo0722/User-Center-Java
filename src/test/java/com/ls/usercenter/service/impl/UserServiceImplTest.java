package com.ls.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ls.usercenter.mapper.UserMapper;
import com.ls.usercenter.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Resource
    private UserService userService ;
    
    @Resource
    private UserMapper userMapper ; 


    @Test
    void userRegister() {
        String userAccount = "yupi";
        String userPassword = "";
        String checkPassword = "12345678";
        String planetCode = "1";
        //校验非空
        long result = userService.userRegister(userAccount, userPassword,
                checkPassword, planetCode);
        Assertions.assertEquals(-1, result);

        //校验账号长度超过4个字符
        userAccount = "yu";
        userPassword = "123456";
        result = userService.userRegister(userAccount, userPassword,
                checkPassword, planetCode);
        Assertions.assertEquals(-1, result);

        //校验密码长度不超过8个字符
        userAccount = "yupi";
        userPassword = "123456";
        result = userService.userRegister(userAccount, userPassword,
                checkPassword, planetCode);
        Assertions.assertEquals(-1, result);

        //校验 密码和校验密码是否一致
        userPassword = "123456789";
        result = userService.userRegister(userAccount, userPassword,
                checkPassword, planetCode);
        Assertions.assertEquals(-1, result);

        //校验是否有特殊字符
        userAccount = "yu pi";
        checkPassword = "123456789";
        result = userService.userRegister(userAccount, userPassword,
                checkPassword, planetCode);
        Assertions.assertEquals(-1, result);

        //校验是否有重复数据
        userAccount = "dogyupi";
        userPassword = "123456789";
        result = userService.userRegister(userAccount, userPassword,
                checkPassword, planetCode);
        Assertions.assertEquals(-1, result);

        //这条可以插进数据库
        userAccount = "yupi";
        result = userService.userRegister(userAccount, userPassword,
                checkPassword, planetCode);
        QueryWrapper queryWrapper = new QueryWrapper() ; 
        queryWrapper.eq("userAccount",userAccount) ;
        List list = userMapper.selectList(queryWrapper);
        Assertions.assertEquals(-1, result);
    }
}