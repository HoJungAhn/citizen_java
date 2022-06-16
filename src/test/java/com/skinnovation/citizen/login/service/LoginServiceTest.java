package com.skinnovation.citizen.login.service;

import com.skinnovation.citizen.login.dto.LoginDTO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.apache.http.client.utils.URIBuilder;

//@RunWith(SpringRunner.class) 
//@SpringBootTest
class LoginServiceTest {
	@Autowired
	private LoginService service;

	@Test
	void testLogin() throws Exception {
		LoginDTO dto = new LoginDTO();
		dto.setUserId("ski_admin");
		assertNotNull(service.login(dto));
	}
	
	@Test
	void testURL() throws Exception{
		URIBuilder builder = new URIBuilder("http://localhost");
		builder.setCustomQuery("/api/bcm/v2/commoncode-mng/{groupId}");
		System.out.println(builder.build());
	}

}
