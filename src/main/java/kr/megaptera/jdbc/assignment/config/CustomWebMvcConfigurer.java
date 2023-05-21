package kr.megaptera.jdbc.assignment.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CustomWebMvcConfigurer implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/posts/**")
                .allowedOrigins("http://localhost:8000")
                .allowedMethods("GET", "POST", "PATCH", "DELETE", "OPTIONS");
        registry.addMapping("/comments/**")
                .allowedOrigins("http://localhost:8000")
                .allowedMethods("GET", "POST", "PATCH", "DELETE", "OPTIONS");
    }
}
