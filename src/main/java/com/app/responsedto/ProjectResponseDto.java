package com.app.responsedto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResponseDto {

    private Long id;
    private String image;
    private String name;
    private String description;
}
