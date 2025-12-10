package com.app.service;

import org.springframework.data.domain.Page;

import com.app.requestdto.ContactRequestDto;
import com.app.responsedto.ContactResponseDto;

public interface ContactService {
	ContactResponseDto createContact(ContactRequestDto dto);
    Page<ContactResponseDto> getAllContacts(int page, int size);
}
