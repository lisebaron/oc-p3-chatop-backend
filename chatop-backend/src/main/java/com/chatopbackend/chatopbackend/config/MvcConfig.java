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
    public static final String FOLDER_PATH = "/api/";
    public static final String FILE_PATH = "file:/";

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        exposeDirectory(registry);
    }

    /**
     * Expose a directory for uploading files
     * @param registry configures the handling of static resources
     */
    private void exposeDirectory(final ResourceHandlerRegistry registry) {
        final Path uploadDir = Paths.get(dirName);
        final String uploadPath = uploadDir.toFile().getAbsolutePath();

        final String pathPattern = "/api/" + dirName + "/**";
        final String resourceLocation = "file:/" + uploadPath + "/";

        if (dirName.startsWith("../")) {
            dirName = dirName.replace("../", "");
        }
        registry.addResourceHandler(FOLDER_PATH + dirName + "/**")
                .addResourceLocations(FILE_PATH + uploadPath + "/");
    }
}