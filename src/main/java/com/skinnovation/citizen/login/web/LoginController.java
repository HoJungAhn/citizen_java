
package com.skinnovation.citizen.login.web;

import com.skinnovation.citizen.bcm.BCMCommon;
import com.skinnovation.citizen.login.dto.LoginDTO;
import com.skinnovation.citizen.login.service.LoginService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Api
public class LoginController {
    private final LoginService loginService;
    private final BCMCommon bcm_common;

	@PostMapping("/login")
    public ResponseEntity<LoginDTO> doLogin(@RequestBody LoginDTO loginDTO, HttpSession session ) throws Exception {
        
        LoginDTO login = loginService.login(loginDTO);
        login.setUserRole((List<String>) bcm_common.getRoles());
        
        session.setAttribute("login_user", login.getUserId());
        session.setAttribute("login_user_name", login.getUserName());
        
        return ResponseEntity.ok(login);
    }
	
	@GetMapping("/logout")
    public ResponseEntity<LoginDTO> logout(HttpSession session ) throws Exception {
		session.removeAttribute("login_user");
        return ResponseEntity.ok().build();
    }	
}
