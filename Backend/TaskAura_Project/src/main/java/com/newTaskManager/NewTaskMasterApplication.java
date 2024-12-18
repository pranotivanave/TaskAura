package com.newTaskManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.dao")
@EntityScan(basePackages = "com.entity")
//@ComponentScan(basePackages = "com.controller")
//@ComponentScan(basePackages = "com.service")

@ComponentScan(basePackages = {"com.controller", "com.service", "com.newTaskManager"})
public class NewTaskMasterApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewTaskMasterApplication.class, args);
	}

}
