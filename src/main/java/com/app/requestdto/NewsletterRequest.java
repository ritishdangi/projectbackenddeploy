package com.app.requestdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsletterRequest {
	@NotBlank(message = "Email is required")
    @Email(message = "Please enter a valid email")
    private String email;

}
