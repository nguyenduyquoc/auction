package com.hdquoc.project4_spring_boot.configuration;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary(){
        Map<String, Object> config = new HashMap<>();
        config.put("cloud_name", "dh2jzuhav");
        config.put("api_key", "569448952183952");
        config.put("api_secret", "7RGYBoTyGzzxyTMhuu3oc45RJeo ");
        config.put("secure", true);
        return new Cloudinary(config);
    }
}
