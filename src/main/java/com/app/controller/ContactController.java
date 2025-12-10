package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.requestdto.ContactRequestDto;
import com.app.responsedto.ContactResponseDto;
import com.app.service.ContactService;

import jakarta.validation.Valid;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/contracts")
@Validated
public class ContactController {
	
	@Autowired
    private ContactService contactService;

    @PostMapping("/create")
    public ContactResponseDto createContact(@Valid @RequestBody ContactRequestDto request) {
        return contactService.createContact(request);
    }

    @GetMapping("/all")
    public Page<ContactResponseDto> getAllContacts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return contactService.getAllContacts(page, size);
    }
	
}
