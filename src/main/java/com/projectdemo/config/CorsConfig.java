package com.projectdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {


    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");//不能用全部的port  websocket 限制
        config.addAllowedOrigin("http://127.0.0.1:5172"); // 允許特定的前端域名或 IP 位址  
        config.addAllowedOrigin("http://localhost:5172"); // 允許特定的前端域名或 IP 位址  
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
//        config.setAllowCredentials(true); // 設定允許攜帶憑證  必須要加入 websocket 限制
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }


}
