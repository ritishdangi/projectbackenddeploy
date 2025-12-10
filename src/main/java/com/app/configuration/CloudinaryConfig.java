package com.app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Configuration
public class CloudinaryConfig {
	@Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "db14wpwrg",
            "api_key", "654282526131927",
            "api_secret", "if_q5s83fNCd_CWYvlHOey6v5-M",
            "secure", true
        ));
    }

}
