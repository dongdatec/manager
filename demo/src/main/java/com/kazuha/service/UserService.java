package com.kazuha.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kazuha.controller.utils.ResultsForFront;
import com.kazuha.pojo.User;

public interface UserService extends IService<User> {
    User selectByUsername(User user);

    ResultsForFront registerUser(User user);
}
