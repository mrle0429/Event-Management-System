package com.eventmanagementsystem.service;

import com.eventmanagementsystem.model.Account;
import com.eventmanagementsystem.model.User;
import com.eventmanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private UserRepository userRepository;

    // 根据邮箱查找用户
    public User getAccountByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // 创建新用户账户
    public User createAccount(String name, String email, String password) {
        // 检查邮箱是否已存在
        if (userRepository.findByEmail(email) != null) {
            throw new RuntimeException("邮箱已被注册");
        }
        
        // 创建新用户
        User user = new User(name, email, password); // 直接使用原始密码,不做加密
        return userRepository.save(user);
    }

    // 验证用户登录
    public Account verifyLogin(String email, String password) {
        Account account = userRepository.findByEmail(email);
        if (account == null) {
            //account = adminRepository.findByEmail(email);
        }
        if (account != null && account.getPassword().equals(password)) {
            return account;
        }
        return null;
    }
}