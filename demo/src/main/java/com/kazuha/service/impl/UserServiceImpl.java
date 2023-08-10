package com.kazuha.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kazuha.controller.utils.ResultsForFront;
import com.kazuha.mapper.UserMapper;
import com.kazuha.pojo.User;
import com.kazuha.service.UserService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectByUsername(User user) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Strings.isNotEmpty(user.getUsername()),User::getUsername,user.getUsername());
        User one = userMapper.selectOne(lambdaQueryWrapper);
//        boolean flag = one == null ? false:true;
        return one;
    }

    //TODO:注册用户
    @Override
    public ResultsForFront registerUser(User user) {
        if (selectByUsername(user) != null) {
            return new ResultsForFront(false, "用户名 " + user.getUsername() + " 已存在");
        }
        if (this.save(user)) {
            return new ResultsForFront(true, "注册成功");
        }
        return new ResultsForFront(false, "注册失败");
    }

}
