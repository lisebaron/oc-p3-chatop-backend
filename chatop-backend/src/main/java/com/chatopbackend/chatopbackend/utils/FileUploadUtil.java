package com.chatopbackend.chatopbackend.utils;

import java.io.*;
import java.nio.file.*;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadUtil {


    public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
        // Converts the path's type string to object Path
        Path uploadPath = Paths.get(uploadDir);

        // Checks if directory exists. If not, it creates it.
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Saves the file
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }
}