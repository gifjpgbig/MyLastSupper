//package com.projectdemo.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		 registry.addMapping("/ws/**") // 支持跨來源訪問的端點
//         .allowedOrigins("*") // 允許所有來源的訪問
//         .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 支持的 HTTP 方法
//         .allowedHeaders("*") // 允許所有標頭
//         .allowCredentials(true); // 允許使用認證
//	}
//}
