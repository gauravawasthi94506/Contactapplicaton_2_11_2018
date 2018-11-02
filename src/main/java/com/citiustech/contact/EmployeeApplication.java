package com.citiustech.contact;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
@EnableWebMvc
public class EmployeeApplication extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		
		SpringApplication.run(EmployeeApplication.class, args);
	}

	 protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(EmployeeApplication.class);
	    }
}
