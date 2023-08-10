package com.kazuha.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kazuha.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
