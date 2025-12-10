package com.app.serviceimpl;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class CloudinaryServiceImpl {
	@Autowired
    private Cloudinary cloudinary;

	// ✅ Default upload (no folder)
    public String uploadFile(MultipartFile file) throws IOException {
        Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        return uploadResult.get("secure_url").toString();
    }

    // ✅ Overloaded method to upload to a specific folder
    public String uploadFile(MultipartFile file, String folderName) throws IOException {
        Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(),
                ObjectUtils.asMap("folder", folderName));
        return uploadResult.get("secure_url").toString();
    }

}
