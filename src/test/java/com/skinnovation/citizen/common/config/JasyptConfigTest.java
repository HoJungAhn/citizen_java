package com.skinnovation.citizen.common.config;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class) 
@SpringBootTest
class JasyptConfigTest {
	@Autowired
	StringEncryptor encryptor;
	
	
	@Test
	void testStringEncrypter() {
		String enc = "SA";
		String encrypt = encryptor.encrypt(enc);
		String decrypt = encryptor.decrypt(encrypt);
		System.out.println(encrypt);
		System.out.println(decrypt);

		
		enc = "!Skcc1234";
		encrypt = encryptor.encrypt(enc);
		decrypt = encryptor.decrypt(encrypt);
		System.out.println(encrypt);
		System.out.println(decrypt);
		assertEquals(enc, decrypt);
	}
	
	
	@Autowired
	PasswordEncoder passwordEncoder;	
	@Test
	void testPassword() {
		String passwd = "passwd";
		
		String encode = passwordEncoder.encode(passwd);
		System.out.println(encode);
		
		System.out.println(passwordEncoder.matches(passwd, encode));
	}	
	

}
