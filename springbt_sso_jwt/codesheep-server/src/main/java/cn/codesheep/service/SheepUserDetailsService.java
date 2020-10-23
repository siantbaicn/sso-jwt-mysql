package cn.codesheep.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import cn.codesheep.bean.Credentials;
import cn.codesheep.repository.CredentialRepository;


@Component
public class SheepUserDetailsService implements UserDetailsService {

/*    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        if( !"codesheep".equals(s) )
            throw new UsernameNotFoundException("用户" + s + "不存在" );

        return new User( s, passwordEncoder.encode("123456"), AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_NORMAL,ROLE_MEDIUM"));
    }*/
	
    @Autowired
    private CredentialRepository credentialRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Credentials credentials = credentialRepository.findByName(username);


        if(credentials==null){

            throw new UsernameNotFoundException("User"+username+"can not be found");
        }

        User user = new User(credentials.getName(),credentials.getPassword(),credentials.isEnabled(),true,true,true,credentials.getAuthorities());

        return  user;


    }
    
	@Value("${ftpInitValue}")
	String ftpInitValue;
    
    @PostConstruct
    public void init() {
        String rubin = ftpInitValue;
        System.out.println(rubin);
    }
}
