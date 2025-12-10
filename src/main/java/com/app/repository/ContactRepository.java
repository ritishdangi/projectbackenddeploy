package com.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long>{
	Page<Contact> findAllByOrderByIdDesc(Pageable pageable);
}
