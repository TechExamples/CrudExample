package com.dataBytes.service;

import com.dataBytes.dto.User;

public interface UserService {

	User findById(int id);

	User findBySso(String sso);

}