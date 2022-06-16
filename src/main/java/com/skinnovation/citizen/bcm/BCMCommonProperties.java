package com.skinnovation.citizen.bcm;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = BCMCommonConst.BCM_COMMON_PREFIX)
public class BCMCommonProperties {
	private String jwkKey;
	private String bcmInfoUrl;
	
	public String getJwkKey() {
		return jwkKey;
	}
	public void setJwkKey(String jwkKey) {
		this.jwkKey = jwkKey;
	}
	public String getBcmInfoUrl() {
		return bcmInfoUrl;
	}
	public void setBcmInfoUrl(String bcmInfoUrl) {
		this.bcmInfoUrl = bcmInfoUrl;
	}
	
}
