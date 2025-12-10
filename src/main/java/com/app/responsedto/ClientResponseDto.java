package com.app.responsedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponseDto {
	private Long id;
    private String image;
    private String name;
    private String description;
    private String designation;

}
