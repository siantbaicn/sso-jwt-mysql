package cn.codesheep.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

public class CustomJwtTokenEnhancer implements TokenEnhancer {
	  @Override
	  public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
	    Map<String, Object> additionalInfo = new HashMap<>();
	    
	    String clientId=authentication.getOAuth2Request().getClientId();
	    String loginUser = authentication.getName();
	    Set<String> app1 = new HashSet<String>();	    
	    app1.add("rs_ydzbgkt");
	    app1.add("rubin");
	    app1.add("ZBGKT");
	    List<String> app2 = new ArrayList<String>();
	    app2.add("role_ydzbgkt_user");
	    Map<String,Object> customInfo = new HashMap<String,Object>();
	    customInfo.put("resources", app1);
	    customInfo.put("authorities", app2);
	    customInfo.put("client_id", clientId);
	    customInfo.put("loginUser", loginUser);
	    additionalInfo.put("customInfo", customInfo);
	    ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);   
	    return accessToken;
	  }
	}