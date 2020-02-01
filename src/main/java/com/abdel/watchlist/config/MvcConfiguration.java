package com.abdel.watchlist.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer{

	// override the auto-confiration of springboot
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {


		registry.addViewController("/home").setViewName("Home.html");
		registry.addViewController("/").setViewName("Home.html");
//		registry.addRedirectViewController(urlPath, redirectUrl)
	}


	
	
	
}
