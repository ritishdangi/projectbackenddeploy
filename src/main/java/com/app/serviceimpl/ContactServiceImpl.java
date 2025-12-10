package com.app.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import com.app.exception.ResourceNotFoundException;
import com.app.model.Contact;
import com.app.repository.ContactRepository;
import com.app.requestdto.ContactRequestDto;
import com.app.responsedto.ContactResponseDto;
import com.app.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public ContactResponseDto createContact(ContactRequestDto dto) {
        Contact contact = mapToEntity(dto);
        Contact saved = contactRepository.save(contact);
        return mapToResponse(saved);
    }

    @Override
    public Page<ContactResponseDto> getAllContacts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Contact> contacts = contactRepository.findAllByOrderByIdDesc(pageable);
        if (contacts.isEmpty()) {
            throw new ResourceNotFoundException("No projects found");
        }
        return contacts.map(this::mapToResponse);
    }


    private Contact mapToEntity(ContactRequestDto dto) {
        Contact contact = new Contact();
        contact.setFullName(dto.getFullName());
        contact.setEmail(dto.getEmail());
        contact.setMobile(dto.getMobile());
        contact.setCity(dto.getCity());
        return contact;
    }

    private ContactResponseDto mapToResponse(Contact contact) {
        return new ContactResponseDto(
                contact.getId(),
                contact.getFullName(),
                contact.getEmail(),
                contact.getMobile(),
                contact.getCity()
        );
    }
}
