package com.kitkamseng.votebackend.model;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticResourceConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Add a resource handler for serving static files from a specific directory
        registry.addResourceHandler("/images/**") // URL path for accessing the static files
                .addResourceLocations("file:C:\\Users\\Marte\\OneDrive\\Desktop\\vote-backend\\vote-backend\\src\\images\\"); // Actual directory path
    }
}
