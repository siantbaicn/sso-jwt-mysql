package cn.codesheep.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

public class CustomJwtTokenEnhancer implements TokenEnhancer {
	  @Override
	  public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
	    Map<String, Object> additionalInfo = new HashMap<>();
	    
	    String clientId=authentication.getOAuth2Request().getClientId();
	    List<String> app1 = new ArrayList<String>(); 
	    app1.add("rubin");
	    app1.add("white");
	    List<String> app2 = new ArrayList<String>();
	    app2.add("rubin1");
	    app2.add("white1");
	    Map<String,Object> appIdResources = new HashMap<String,Object>();
	    appIdResources.put("app1", app1);
	    appIdResources.put("client_id", clientId);
	    additionalInfo.put("resourceIds", appIdResources);
	    ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);   
	    return accessToken;
	  }
	}