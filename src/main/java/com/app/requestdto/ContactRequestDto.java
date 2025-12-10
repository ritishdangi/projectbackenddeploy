package com.app.requestdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactRequestDto {
	@NotBlank(message = "Full Name is required")
    private String fullName;

    @Email(message = "Invalid Email Address")
    @NotBlank(message = "Email Address is required")
    private String email;

    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile Number must be 10 digits")
    private String mobile;

    @NotBlank(message = "City is required")
    private String city;
	
}
