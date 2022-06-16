package com.skinnovation.citizen.login.model;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@SuppressWarnings("serial")
@Data 
@Alias("userM")
public class User implements Serializable{
	private String userId;
	private String userName;
	private String userPass;
	private String mobileNo;
	private String email;
}
