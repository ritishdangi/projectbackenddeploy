package com.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{
	Page<Project> findAllByOrderByIdDesc(Pageable pageable);
	boolean existsByName(String name);

}
