package com.dataBytes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import com.dataBytes.dao.UserDao;
import com.dataBytes.dto.User;
 
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
 
    @Autowired
    private UserDao dao;
 
    public User findById(int id) {
        return dao.findById(id);
    }
 
    
    public User findBySso(String sso) {
        return dao.findBySSO(sso);
    }
    
    public User add(String sso) {
        return dao.findBySSO(sso);
    }
    
 
}