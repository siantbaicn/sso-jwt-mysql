package cn.codesheep.config;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;

@Component("resourceIdService")
public class ResourceIdService {
    @Autowired
    private TokenStore tokenStore;
	
	public ResourceIdService() {
		
	}
    public boolean hasResourceId(String... resourceIds) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	OAuth2AuthenticationDetails details= (OAuth2AuthenticationDetails) authentication.getDetails();
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(details.getTokenValue());
        Map<String,Object> additionalMap = accessToken.getAdditionalInformation();
        Object resourceMap = additionalMap.get("customInfo");
        Object resource = ((Map<String, Object>) resourceMap).get("resources");
        List<String> resourceSet = (List<String>)resource;
        String[] resourceIds1 = resourceIds;
        int strLen = resourceIds1.length;
        for(int i=0;i<strLen;i++) {
        	for(String s:resourceSet){
        		if(s.equals(resourceIds1[i])) {
        			return true;
        		}
        	}
        }
        return false;
    }
}
