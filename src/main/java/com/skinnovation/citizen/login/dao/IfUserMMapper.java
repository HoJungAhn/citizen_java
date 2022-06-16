package com.skinnovation.citizen.login.dao;

import org.apache.ibatis.annotations.Mapper;

import com.skinnovation.citizen.login.model.User;

@Mapper
public interface IfUserMMapper {

   public User selectByLoginUserInfo(String userId);

}