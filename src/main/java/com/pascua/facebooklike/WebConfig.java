package com.pascua.facebooklike;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // FIX: Added the correct port (5173) for the currently running React app.
        registry.addMapping("/api/**") // Apply only to /api endpoints (better practice)
                .allowedOrigins("http://localhost:3000", "http://localhost:5173") // Allow both common ports
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true); // Needed if using sessions/cookies, good practice to include
    }
}
