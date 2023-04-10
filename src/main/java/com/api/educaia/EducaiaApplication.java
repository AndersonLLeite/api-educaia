package com.api.educaia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class EducaiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EducaiaApplication.class, args);
		//System.out.println(new BCryptPasswordEncoder().encode("senha123"));
	}

}
