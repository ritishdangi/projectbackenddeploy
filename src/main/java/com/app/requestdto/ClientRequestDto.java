package com.app.requestdto;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequestDto {
	
	private MultipartFile image;
	
	@NotBlank(message = "Client name is required")
	@Length(min = 2, max = 20, message = "Client Name must be between 2 and 20 characters")
    private String name;

    @NotBlank(message = "Client description is required")
    @Length(min = 2, max = 100, message = "Client description must be between 2 and 100 characters")
    private String description;

    @NotBlank(message = "Client designation is required")
    private String designation;

}
