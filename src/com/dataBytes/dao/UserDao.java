package com.dataBytes.dao;

import com.dataBytes.dto.User;

public interface UserDao {
 
    User findById(int id);
     
    User findBySSO(String sso);
     
}