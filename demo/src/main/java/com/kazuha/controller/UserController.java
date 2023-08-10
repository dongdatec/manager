package com.kazuha.controller;

import com.kazuha.controller.utils.ResultsForFront;
import com.kazuha.pojo.User;
import com.kazuha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    //TODO：增加（已完成）
    @PostMapping
    public ResultsForFront save(@RequestBody User user){
        boolean flag = userService.save(user);
        return new ResultsForFront(flag,flag?"添加成功=v=":"用户名重复，添加失败=-=]、");
    }
    //TODO：删除（已完成）
    @DeleteMapping("{id}")
    public ResultsForFront delete(@PathVariable Integer id){
        boolean flag = userService.removeById(id);
        return new ResultsForFront(flag,flag?"删除成功=v=":"删除失败=-=]、");
    }
    //TODO：修改（已完成）
    @PutMapping
    public ResultsForFront modify(@RequestBody User user){
        boolean flag =  userService.updateById(user);
        return new ResultsForFront(flag,flag?"修改成功=v=":"修改失败=-=]、");
    }
    //TODO：查询全部（已完成）
    @GetMapping
    public ResultsForFront selectAll(){
        return new ResultsForFront(true,userService.list());
    }
    //TODO：按id查询（已完成）
    @GetMapping("{id}")
    public ResultsForFront selectById(@PathVariable Integer id){
        return new ResultsForFront(true,userService.getById(id));
    }
    //TODO：登陆检验
//    @GetMapping("/checkLogin")
//    public ResultsForFront selectByUsername(@RequestBody User user){
//        System.out.println(user);
//
//        boolean check = false;
//        User select = userService.selectByUsername(user);
//        if (select!=null){
//            //获取密码进行匹配
//            check =select.getPassword().equals(user.getPassword()) ? true:false;
//        }
//        return new ResultsForFront(check,check?"登录成功":"登录失败");
//    }

    @PostMapping("/checkLogin")
    public ResultsForFront selectByUsername(@RequestBody User user) {
        System.out.println(user);

        boolean check = false;
        User select = userService.selectByUsername(user);
        if (select!=null){
            //获取密码进行匹配
            check = select.getPassword().equals(user.getPassword());
        }
        return new ResultsForFront(check,check?"登录成功":"登录失败");
    }

    @PostMapping("/registerUser")
    public ResultsForFront registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }
}
