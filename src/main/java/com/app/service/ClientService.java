package com.app.service;

import org.springframework.data.domain.Page;

import com.app.requestdto.ClientRequestDto;
import com.app.responsedto.ClientResponseDto;

public interface ClientService {
	ClientResponseDto createClient(ClientRequestDto dto);
	Page<ClientResponseDto> getAllClients(int page, int size);
    ClientResponseDto getClientById(Long id);
    ClientResponseDto updateClient(Long id, ClientRequestDto dto);
    void deleteClient(Long id);
}
