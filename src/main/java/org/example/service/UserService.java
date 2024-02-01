package org.example.service;

import org.example.pojo.User;

public interface UserService {
    //根據用戶名查詢用戶
    User findByUserName(String username);

    //註冊
    void register(String username, String password);

}
