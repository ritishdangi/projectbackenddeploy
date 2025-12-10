package com.app.service;

import org.springframework.data.domain.Page;

import com.app.requestdto.ProjectRequestDto;
import com.app.responsedto.ProjectResponseDto;

public interface ProjectService {
	
	ProjectResponseDto createProject(ProjectRequestDto dto);
    Page<ProjectResponseDto> getAllProjects(int page, int size);
    ProjectResponseDto getProjectById(Long id);
    ProjectResponseDto updateProject(Long id, ProjectRequestDto dto);
    void deleteProject(Long id);
}
