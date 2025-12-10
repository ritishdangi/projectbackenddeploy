package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.requestdto.ClientRequestDto;
import com.app.responsedto.ClientResponseDto;
import com.app.service.ClientService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/clients")
@Validated
public class ClientController {
	
	 @Autowired
	    private ClientService clientService;

	    @PostMapping
	    public ResponseEntity<ClientResponseDto> createClient(
	            @ModelAttribute @Valid ClientRequestDto dto) {

	        ClientResponseDto response = clientService.createClient(dto);
	        return ResponseEntity.ok(response);
	    }

	    @GetMapping
	    public ResponseEntity<Page<ClientResponseDto>> getAllClients(
	            @RequestParam(defaultValue = "0") int page,
	            @RequestParam(defaultValue = "5") int size) {

	        return ResponseEntity.ok(clientService.getAllClients(page, size));
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<ClientResponseDto> getClientById(@PathVariable Long id) {
	        return ResponseEntity.ok(clientService.getClientById(id));
	    }

	    @PutMapping("update/{id}")
	    public ResponseEntity<ClientResponseDto> updateClient(
	            @PathVariable Long id,
	            @ModelAttribute @Valid ClientRequestDto dto) {

	        ClientResponseDto updated = clientService.updateClient(id, dto);
	        return ResponseEntity.ok(updated);
	    }


	    @DeleteMapping("/{id}")
	    public ResponseEntity<String> deleteClient(@PathVariable Long id) {

	        clientService.deleteClient(id);
	        return ResponseEntity.ok("Client deleted successfully.");
	    }

}
