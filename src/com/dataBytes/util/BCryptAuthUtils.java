package com.dataBytes.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptAuthUtils {

	public static void main(String[] args) {
		String password = "abc123";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		System.out.println(passwordEncoder.encode(password));
	}

}
