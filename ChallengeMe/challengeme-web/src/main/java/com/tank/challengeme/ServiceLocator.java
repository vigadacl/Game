package com.tank.challengeme;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.challengeme.dao.UserDao;

@Configuration
public class ServiceLocator {

	@Bean
	public UserDao getUserService(){
		return new UserDao();
	}
}
