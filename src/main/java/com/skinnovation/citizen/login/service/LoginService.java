package com.skinnovation.citizen.login.service;

import com.skinnovation.citizen.common.exception.exceptions.InvalidInputException;
import com.skinnovation.citizen.common.exception.exceptions.NotFoundException;
import com.skinnovation.citizen.login.dao.IfUserMMapper;
import com.skinnovation.citizen.login.dto.LoginDTO;
import com.skinnovation.citizen.login.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpSession;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginService{
	final IfUserMMapper ifUserMMapper;
    final PasswordEncoder passwordEncoder;

    final HttpSession httpSession;

    public LoginDTO login(LoginDTO loginDTO) throws Exception {

        User ifUserM = ifUserMMapper.selectByLoginUserInfo(loginDTO.getUserId());
        
        if (ObjectUtils.isEmpty(ifUserM)){
        	log.debug("not registration user");
            throw new NotFoundException("not registration user");
        }
        
        if (!ifUserM.getUserPass().equals(loginDTO.getUserPass())) {
        	log.debug("passwords do not match");
            throw new InvalidInputException("passwords do not match");        	
        }
        loginDTO.setUserPass("");
        loginDTO.setUserName(ifUserM.getUserName());
        loginDTO.setMobileNo(ifUserM.getMobileNo());
        loginDTO.setEmail(ifUserM.getEmail());
        loginDTO.setLoginSuccess(true);

        return loginDTO;
    }
}
