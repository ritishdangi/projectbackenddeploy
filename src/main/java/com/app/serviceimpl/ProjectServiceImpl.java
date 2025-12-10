package com.app.serviceimpl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import com.app.exception.BadRequestException;
import com.app.exception.FileUploadException;
import com.app.exception.ResourceNotFoundException;
import com.app.model.Project;
import com.app.repository.ProjectRepository;
import com.app.requestdto.ProjectRequestDto;
import com.app.responsedto.ProjectResponseDto;
import com.app.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private CloudinaryServiceImpl cloudinaryService;

    @Override
    public ProjectResponseDto createProject(ProjectRequestDto dto) {
    	if (dto.getImage() == null || dto.getImage().isEmpty()) {
            throw new BadRequestException("Project image is required");
        }
    	
    	if (projectRepository.existsByName(dto.getName())) {
            throw new BadRequestException("Project name already exists");
        }

        Project project = mapToEntity(dto);

        // Upload Image if provided
        if (dto.getImage() != null && !dto.getImage().isEmpty()) {
        	try {
        	    String imageUrl = cloudinaryService.uploadFile(dto.getImage(), "projects");
        	    project.setImage(imageUrl);
        	} catch (IOException e) {
        	    throw new FileUploadException("Failed to upload project image");
        	}

        }

        Project saved = projectRepository.save(project);
        return mapToResponse(saved);
    }

    @Override
    public Page<ProjectResponseDto> getAllProjects(int page, int size) {
//    	if (page < 0) {
//            throw new BadRequestException("Page number cannot be negative");
//        }
//
//        if (size <= 0 || size > 100) {
//            throw new BadRequestException("Page size must be between 1 and 100");
//        }
    	
    	Pageable pageable = PageRequest.of(page, size);
    	Page<Project> projectPage = projectRepository.findAllByOrderByIdDesc(pageable);
    	if (projectPage.isEmpty()) {
            throw new ResourceNotFoundException("No projects found");
        }
        return projectPage.map(this::mapToResponse);
    }
    

    @Override
    public ProjectResponseDto getProjectById(Long id) {

        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with ID: " + id));

        return mapToResponse(project);
    }

    @Override
    public ProjectResponseDto updateProject(Long id, ProjectRequestDto dto) {

        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with ID: " + id));

        if (!project.getName().equals(dto.getName()) 
                && projectRepository.existsByName(dto.getName())) {
            throw new BadRequestException("Project with this name already exists");
        }

        project.setName(dto.getName());
        project.setDescription(dto.getDescription());

        if (dto.getImage() != null && !dto.getImage().isEmpty()) {
            try {
                String imageUrl = cloudinaryService.uploadFile(dto.getImage(), "projects");
                project.setImage(imageUrl);
            } catch (IOException e) {
                throw new FileUploadException("Failed to upload updated project image");
            }
        }

        Project updated = projectRepository.save(project);
        return mapToResponse(updated);
    }



    @Override
    public void deleteProject(Long id) {

        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with ID: " + id));

        projectRepository.delete(project);
    }

    private Project mapToEntity(ProjectRequestDto dto) {

        Project project = new Project();
        project.setName(dto.getName());
        project.setDescription(dto.getDescription());
        return project;
    }

    private ProjectResponseDto mapToResponse(Project project) {

        ProjectResponseDto dto = new ProjectResponseDto();
        dto.setId(project.getId());
        dto.setImage(project.getImage());
        dto.setName(project.getName());
        dto.setDescription(project.getDescription());
        return dto;
    }
}
