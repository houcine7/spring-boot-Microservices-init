package com.authentication.app;

import com.authentication.app.Enums.Role;
import com.authentication.app.entity.UserEntity;
import com.authentication.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class AppApplication {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder pwdEncoder;


	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}


	@Bean
	CommandLineRunner commandLineRunner(){
		return args -> {
			userRepository.save(
					UserEntity.builder().
							username("admin")
							.roles(List.of(Role.ADMIN))
							.password(pwdEncoder.encode("1234"))
							.build()
			);
		};
	}

}
