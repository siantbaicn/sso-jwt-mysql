package cn.codesheep.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class passwordEncoder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
         System.out.println(new BCryptPasswordEncoder().encode("123456"));
	}

}
