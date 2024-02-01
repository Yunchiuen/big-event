package org.example.controller;

import jakarta.validation.constraints.Pattern;
import org.example.pojo.Result;
import org.example.pojo.User;
import org.example.service.UserService;
import org.example.util.JwtUtil;
import org.example.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username,
                           @Pattern(regexp = "^\\S{5,16}$") String password) {

        User user = userService.findByUserName(username);
        if (user == null) {
            //註冊
            userService.register(username, password);
            return Result.success();
        } else {
            return Result.error("用戶名已被占用");
        }
    }

    @PostMapping("/login")
    public Result login(@Pattern(regexp = "^\\S{5,16}$") String username,
                        @Pattern(regexp = "^\\S{5,16}$") String password) {
        //根據用戶名查詢用戶
        User user = userService.findByUserName(username);
        //判斷用戶是否存在
        if (user == null) {
            return Result.error("用戶名錯誤");
        }
        //判斷密碼是否正確
        if (Md5Util.getMD5String(password).equals(user.getPassword())) {
            Map<String,Object> loginMap = new HashMap<>();
            loginMap.put("id" , user.getId());
            loginMap.put("username" , user.getUsername());
            String token = JwtUtil.genToken(loginMap);
            return Result.success(token);
        }
        return Result.error("用戶密碼錯誤");

    }
}
