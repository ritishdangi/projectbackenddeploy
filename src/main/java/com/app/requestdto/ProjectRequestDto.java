package com.app.requestdto;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRequestDto {

    private MultipartFile image;

    @NotBlank(message = "Project name is required")
    @Size(min = 2, max = 50, message = "Project name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Project description is required")
    @Size(min = 2, max = 1000, message = "Project name must be between 2 and 1000 characters")
    private String description;
}
