package com.hdquoc.project4_spring_boot.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;

    @Autowired
    public CloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }


    public String uploadImage(MultipartFile file) {
        try {
            // Upload file to Cloudinary
            Map<String, Object> options = new HashMap<>();
            options.put("resource_type", "image"); // Or use an upload preset
            Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), options);

            // Return the URL of the uploaded image
            return (String) uploadResult.get("url");

        } catch (IOException e) {
            // Handle IO exception
            throw new RuntimeException("Image upload fail");
        }
    }
    public void delete(String publicId) throws IOException {
        try {
            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
        } catch (IOException e) {
            throw new IOException("Failed to delete image from Cloudinary: " + e.getMessage());
        }
    }

}

