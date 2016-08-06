package com.dataBytes.model;

import lombok.Getter;
import lombok.Setter;

public enum UserProfileType {
    USER("USER"),
    ADMIN("ADMIN");
   
	@Getter
	@Setter
    String userProfileType;    
	
	private UserProfileType(String userProfileType){
        this.userProfileType = userProfileType;
    }
}