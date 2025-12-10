package com.app.responsedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactResponseDto {
	
	private Long id;
    private String fullName;
    private String email;
    private String mobile;
    private String city;
	

}
