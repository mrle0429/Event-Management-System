package com.eventmanagementsystem.controller;

import com.eventmanagementsystem.model.Account;
import com.eventmanagementsystem.model.User;
import com.eventmanagementsystem.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/account")
public class AccountSystem {

    @Autowired
    private AccountService accountService;

    // 显示注册页面
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // 处理注册请求
    @PostMapping("/register")
    public String registerAccount(
            @RequestParam String email,
            @RequestParam String name,
            @RequestParam String password,
            Model model) {
        try {
            // 基本验证
            if (email == null || email.trim().isEmpty() ||
                name == null || name.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {
                return "redirect:/account/register?error=invalid_input";
            }

            // 检查邮箱是否已存在
            if (accountService.getAccountByEmail(email) != null) {
                return "redirect:/account/register?error=email_exists";
            }

            // 创建账户
            User user = accountService.createAccount(name, email, password);
            
            // 注册成功，重定向到登录页
            return "redirect:/account/login";
            
        } catch (Exception e) {
            return "redirect:/account/register?error=registration_failed";
        }
    }

    // 显示登录页面
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    // 处理登录请求
    @PostMapping("/login")
    public String login(
            @RequestParam String email,
            @RequestParam String password,
            Model model) {

        Account account = accountService.verifyLogin(email, password);
        if (account == null) {
            return "redirect:/account/login";
        }

            model.addAttribute("user", account);
            return "redirect:/account/user/dashboard";


    }


    @GetMapping("/user/dashboard")
    public String showUserDashboard() {
        return "user_dashboard";
    }


    // 错误处理
    @ExceptionHandler(Exception.class)
    public String handleError(Exception e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "error";
    }
}