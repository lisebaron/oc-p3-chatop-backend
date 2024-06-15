package com.chatopbackend.chatopbackend.config;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Value("${file.upload.dir}")
    private String dirName;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        exposeDirectory(registry);
    }
    //mettre des commentaires java doc au dessus des méthodes
    //mettre registry  en final
    private void exposeDirectory(final ResourceHandlerRegistry registry) {
        //mettre la variable uploadDir  en final
        final Path uploadDir = Paths.get(dirName);
        //mettre la variable uploadPath  en final
        String uploadPath = uploadDir.toFile().getAbsolutePath();

        if (dirName.startsWith("../")) {
            dirName = dirName.replace("../", "");
        }
        // "/api/" dans une constante
        // file:/ dans une constante
        registry.addResourceHandler("/api/" + dirName + "/**")
                .addResourceLocations("file:/" + uploadPath + "/");
    }
}