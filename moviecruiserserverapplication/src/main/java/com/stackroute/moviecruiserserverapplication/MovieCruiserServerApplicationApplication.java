package com.stackroute.moviecruiserserverapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.stackroute.moviecruiserserverapplication.filter.JwtFilter;

@SpringBootApplication
public class MovieCruiserServerApplicationApplication {

	@Bean
	public FilterRegistrationBean jwtFilter(){
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/api/*");
		return registrationBean;
	}
	// Our spring boot application starts from this point
	public static void main(String[] args) {
		SpringApplication.run(MovieCruiserServerApplicationApplication.class, args);
	}

}
