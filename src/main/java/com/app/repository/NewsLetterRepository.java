package com.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Newsletter;

public interface NewsLetterRepository extends JpaRepository<Newsletter, Long> {
	Page<Newsletter> findAllByOrderByIdDesc(Pageable pageable);
	boolean existsByEmail(String email);

}
