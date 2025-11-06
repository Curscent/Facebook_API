package com.pascua.facebooklike;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        String renderUiUrl = "https://facebook-ui-wm1k.onrender.com";

        registry.addMapping("/api/**") // Applies to all API endpoints like /api/posts
                .allowedOrigins("http://localhost:3000", "http://localhost:5173", renderUiUrl)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
