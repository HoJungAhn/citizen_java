package com.skinnovation.citizen.bcm;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties(value = { BCMCommonProperties.class })
public class BCMCommonConfig {
	@Bean
	public BCMCommon bcmCommon() {
		return new BCMCommon();
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.build();
	}
	
	@Bean
	public RestTemplateCustomizer restTemplateCustomizer() {
		return (restTemplate)->restTemplate.setRequestFactory(clientHttpRequestFactory());
	}
	
	@Bean
	public ClientHttpRequestFactory clientHttpRequestFactory() {
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(); 
		clientHttpRequestFactory.setConnectTimeout(3000); 
		clientHttpRequestFactory.setReadTimeout(5000); 
		return clientHttpRequestFactory;
		
	}
}
