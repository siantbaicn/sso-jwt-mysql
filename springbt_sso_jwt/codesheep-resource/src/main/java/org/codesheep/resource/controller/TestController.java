package org.codesheep.resource.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
    @Autowired
    private TokenStore tokenStore;
	
    @GetMapping("/normal")
    public String normal( ) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	OAuth2AuthenticationDetails details= (OAuth2AuthenticationDetails) authentication.getDetails();
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(details.getTokenValue());
        Map<String,Object> additionalMap = accessToken.getAdditionalInformation();
    	System.out.println("hello");
        return "normal permission test success rubin!!!";
    }

}