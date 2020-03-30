package org.codesheep.resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;


/**
 * Hello world!
 *
 */
@SpringBootApplication
public class ResourceApplication 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(ResourceApplication.class, args);
    }
}
