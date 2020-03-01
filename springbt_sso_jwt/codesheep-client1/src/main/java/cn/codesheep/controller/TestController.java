package cn.codesheep.controller;

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
    @PreAuthorize("hasAuthority('ROLE_NORMAL')")
    public String normal( ) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	OAuth2AuthenticationDetails details= (OAuth2AuthenticationDetails) authentication.getDetails();
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(details.getTokenValue());
        Map<String,Object> additionalMap = accessToken.getAdditionalInformation();
        Object resourceMap = additionalMap.get("resourceIds");
        Object keys = ((Map<String, Object>) resourceMap).keySet();
        return "normal permission test success !!!";
    }

    @GetMapping("/medium")
    @PreAuthorize("hasAuthority('ROLE_MEDIUM')")
    public String medium() {
        return "medium permission test success !!!";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String admin() {
        return "admin permission test success !!!";
    }
}