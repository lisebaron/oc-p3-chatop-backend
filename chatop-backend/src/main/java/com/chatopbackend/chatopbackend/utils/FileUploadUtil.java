package com.chatopbackend.chatopbackend.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUploadUtil {

    /**
     * Saves a multipart file to the specified upload directory with the given file name.
     *
     * @param uploadDir the directory where the file should be saved
     * @param fileName the name of the file to save
     * @param multipartFile the multipart file to be saved
     * @throws IOException if an I/O error occurs while saving the file
     */
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