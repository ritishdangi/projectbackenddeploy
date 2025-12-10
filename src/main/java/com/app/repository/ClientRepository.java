package com.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Client;
import com.app.model.Project;

public interface ClientRepository extends JpaRepository<Client, Long>{
	Page<Client> findAllByOrderByIdDesc(Pageable pageable);

}
