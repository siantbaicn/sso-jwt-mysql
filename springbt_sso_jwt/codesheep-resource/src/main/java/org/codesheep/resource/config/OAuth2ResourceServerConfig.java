package org.codesheep.resource.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
@EnableOAuth2Sso
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

/*    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        //配置资源服务器的id，“现在我就是资源服务器order-server！！！”
        resources.resourceId("resourceid1");
    }*/
	
    private final ResourceServerProperties resourceServerProperties;

    public OAuth2ResourceServerConfig(ResourceServerProperties resourceServerProperties) {
        this.resourceServerProperties = resourceServerProperties;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(resourceServerProperties.getResourceId());
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/hello")
                .permitAll() //放过/haha不拦截
                .anyRequest().authenticated();//其余所有请求都拦截
    }
}
