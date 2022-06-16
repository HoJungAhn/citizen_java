package com.skinnovation.citizen.login.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@Data
@Alias("loginDTO")
public class LoginDTO {
    private String userId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String userPass;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String userName;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)    
    private String mobileNo;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String email;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)    
    private List<String> userRole;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)    
    private boolean isLoginSuccess;
}
