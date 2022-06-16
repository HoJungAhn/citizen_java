package com.skinnovation.citizen.bcm;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BCMCommon {
	@Autowired
	private BCMCommonProperties properties;
	@Autowired
	private RestTemplate restTemplate;
	
	public <T> ResponseEntity<T> getBcmService(String api, MultiValueMap<String, String> headers, Class<T> responseType, Map<String, ?> param) throws URISyntaxException{
		return this.bcmService(api, HttpMethod.GET, headers, responseType, param);
	}
	
	public <T> ResponseEntity<T> postBcmService(String api, MultiValueMap<String, String> headers, Class<T> responseType, Map<String, ?> param) throws URISyntaxException{
		return this.bcmService(api, HttpMethod.POST, headers, responseType, param);
	}	
	
	public <T> ResponseEntity<T> bcmService(String api, HttpMethod method, MultiValueMap<String, String> headers, Class<T> responseType, Map<String, ?> param){
		return this.restTemplate.exchange(this.properties.getBcmInfoUrl()+api, HttpMethod.GET, new HttpEntity(headers), responseType, param);
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getRoles() {
		return Optional.ofNullable((List<String>)getClaims().get("authorities")).orElseGet(() -> new ArrayList<>());
	}
	
	public String getCorpId(){
		return getClaimsString("corpId");
	}
	
	public String getDeptCd(){
		return getClaimsString("deptCd");
	}	
	
	public String getClaimsString(String key){
		return Optional.ofNullable((String)getClaims().get(key)).orElse(null);
	}
	
	private Claims getClaims() {
		Claims claims = null;

		try {
			claims = Jwts.parser().setSigningKey(properties.getJwkKey().getBytes("UTF-8")).parseClaimsJws(
					((OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails())
							.getTokenValue())
					.getBody();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return claims;
	}
	
}
