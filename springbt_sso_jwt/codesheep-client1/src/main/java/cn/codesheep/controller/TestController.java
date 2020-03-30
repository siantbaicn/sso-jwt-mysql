package cn.codesheep.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {	
    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private RestTemplate restTemplate;
    
    private OAuth2AccessToken oAuth2AccessToken;

    @GetMapping("/normal")
    @PreAuthorize("hasAuthority('ROLE_NORMAL')")
    public String normal( ) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	OAuth2AuthenticationDetails details= (OAuth2AuthenticationDetails) authentication.getDetails();
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(details.getTokenValue());
        Map<String,Object> additionalMap = accessToken.getAdditionalInformation();
        Object resourceMap = additionalMap.get("resourceIds");
        Object keys = ((Map<String, Object>) resourceMap).keySet();
        Object clientId = ((Map<String, Object>) resourceMap).get("client_id");
        return "normal permission test success !!!";
    }

    @GetMapping("/medium")
    @PreAuthorize("hasAuthority('ROLE_MEDIUM')")
    public String medium() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	OAuth2AuthenticationDetails details= (OAuth2AuthenticationDetails) authentication.getDetails();
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(details.getTokenValue());
    	
		String url = "http://127.0.0.1:8088/normal";
		HttpHeaders requestHeaders = new HttpHeaders();
		String token = accessToken.getValue();
		requestHeaders.add("Authorization", OAuth2AccessToken.BEARER_TYPE+" "+accessToken.getValue());
		requestHeaders.add("Connection", "keep-alive");
		requestHeaders.add("Accept", "application/json");
		requestHeaders.add("Accept-Language", "zh-CN,zh;q=0.9");
		requestHeaders.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");
		HttpEntity<String> formEntity = new HttpEntity<String>(null, requestHeaders);
		ResponseEntity<String> exchange = restTemplate.exchange(url,HttpMethod.GET,formEntity, String.class);
		String body = exchange.getBody();
		System.out.println(body);
        return "medium permission test success !!!";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String admin() {
        return "admin permission test success !!!";
    }
}