package com.eventmanagementsystem.repository;

import com.eventmanagementsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // 根据邮箱查找用户
    User findByEmail(String email);
    
    // 检查邮箱是否存在
    boolean existsByEmail(String email);
    
    // 根据用户名查找用户
    User findByName(String name);
    

    
    // 根据邮箱删除用户
    void deleteByEmail(String email);
    

}