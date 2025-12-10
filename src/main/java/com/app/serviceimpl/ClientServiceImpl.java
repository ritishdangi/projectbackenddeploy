package com.app.serviceimpl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import com.app.exception.BadRequestException;
import com.app.exception.FileUploadException;
import com.app.exception.ResourceNotFoundException;
import com.app.model.Client;
import com.app.repository.ClientRepository;
import com.app.requestdto.ClientRequestDto;
import com.app.responsedto.ClientResponseDto;
import com.app.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private CloudinaryServiceImpl cloudinaryService;

	@Override
	public ClientResponseDto createClient(ClientRequestDto dto) {
		if (dto.getImage() == null || dto.getImage().isEmpty()) {
	        throw new BadRequestException("Client image is required");
	    }
		Client client = mapToEntity(dto);

        // Upload image if provided
        if (dto.getImage() != null && !dto.getImage().isEmpty()) {
            try {
                String imageUrl = cloudinaryService.uploadFile(dto.getImage(), "clients");
                client.setImage(imageUrl);
            } catch (IOException e) {
                throw new FileUploadException("Image upload failed: " + e.getMessage());
            }
        }

        Client saved = clientRepository.save(client);
        return mapToResponse(saved);
	}

	@Override
	public Page<ClientResponseDto> getAllClients(int page, int size) {
	    Pageable pageable = PageRequest.of(page, size);
	    Page<Client> clientPage = clientRepository.findAllByOrderByIdDesc(pageable);
	    if (clientPage.isEmpty()) {
            throw new ResourceNotFoundException("No projects found");
        }
	    return clientPage.map(this::mapToResponse);
	}

	@Override
	 public ClientResponseDto getClientById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with ID: " + id));

        return mapToResponse(client);
    }

	@Override
	public ClientResponseDto updateClient(Long id, ClientRequestDto dto) {

        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with ID: " + id));


        client.setName(dto.getName());
        client.setDescription(dto.getDescription());
        client.setDesignation(dto.getDesignation());

        // Update image only if provided
        if (dto.getImage() != null && !dto.getImage().isEmpty()) {
            try {
                String uploadedUrl = cloudinaryService.uploadFile(dto.getImage(), "clients");
                client.setImage(uploadedUrl);
            } catch (IOException e) {
                throw new RuntimeException("Failed to upload image: " + e.getMessage());
            }
        }

        Client updated = clientRepository.save(client);
        return mapToResponse(updated);
    }

	@Override
	public void deleteClient(Long id) {

        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with ID: " + id));

        clientRepository.delete(client);
    }
	
	private Client mapToEntity(ClientRequestDto dto) {
        Client client = new Client();

        client.setName(dto.getName());
        client.setDescription(dto.getDescription());
        client.setDesignation(dto.getDesignation());

        return client;
    }

    private ClientResponseDto mapToResponse(Client client) {
        ClientResponseDto dto = new ClientResponseDto();

        dto.setId(client.getId());
        dto.setImage(client.getImage());
        dto.setName(client.getName());
        dto.setDescription(client.getDescription());
        dto.setDesignation(client.getDesignation());

        return dto;
    } 
}
