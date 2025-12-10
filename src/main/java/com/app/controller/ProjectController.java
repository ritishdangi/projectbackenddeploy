package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.app.requestdto.ProjectRequestDto;
import com.app.responsedto.ProjectResponseDto;
import com.app.service.ProjectService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/projects")
@Validated
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectResponseDto> createProject(@ModelAttribute @Validated ProjectRequestDto dto) {
        return ResponseEntity.ok(projectService.createProject(dto));
    }

    @GetMapping
    public ResponseEntity<Page<ProjectResponseDto>> getAllProjects(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Page<ProjectResponseDto> projects = projectService.getAllProjects(page, size);
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> getProjectById(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProjectResponseDto> updateProject(
            @PathVariable Long id,
            @ModelAttribute @Validated ProjectRequestDto dto) {

        return ResponseEntity.ok(projectService.updateProject(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok("Project deleted successfully");
    }
}
