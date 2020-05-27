package org.joegeek.gadgetstore.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	//Only needed during development to enable connection from a separate web workspace
	// @Bean
	// public WebMvcConfigurer corsConfigurer() {
	// 	return new WebMvcConfigurer() {
	// 		@Override
	// 		public void addCorsMappings(CorsRegistry registry) {
	// 			registry
	// 				.addMapping("/store/gadget/**")
	// 				.allowedOrigins("http://localhost:4200") //angular default port
	// 				.allowedMethods("GET","POST","PUT","DELETE");
	// 		}
	// 	};
	// }
}
