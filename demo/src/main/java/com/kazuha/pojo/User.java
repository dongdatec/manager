package com.kazuha.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class User {
    private Integer id;
    private String username;
    private String password;

    private String level;

    //用户等级设置
    public enum Level{
        SuperAdmin("超级管理员","高"),
        MidAdmin("中级管理员","中"),
        LowAdmin("低级管理员","低");

        private final String levelName;
        private final String levelDesc;

        private Level(String levelName,String levelDesc){
            this.levelName = levelName;
            this.levelDesc = levelDesc;
        }
    }
}
