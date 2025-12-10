package com.app.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.exception.BadRequestException;
import com.app.exception.ResourceNotFoundException;
import com.app.model.Newsletter;
import com.app.model.Project;
import com.app.repository.NewsLetterRepository;
import com.app.requestdto.NewsletterRequest;
import com.app.responsedto.NewsLetterResponse;
import com.app.responsedto.ProjectResponseDto;
import com.app.service.NewsLetterService;

@Service
public class NewsLetterServiceImpl implements NewsLetterService {

    @Autowired
    private NewsLetterRepository repository;

    @Override
    public NewsLetterResponse subscribe(NewsletterRequest request) {

        if (repository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email already subscribed!");
        }

        Newsletter newsletter = mapToEntity(request);
        Newsletter saved = repository.save(newsletter);

        return mapToResponse(saved);
    }

    @Override
    public Page<NewsLetterResponse> getAllSubscribers(int page, int size) {
//    	if (page < 0) {
//            throw new BadRequestException("Page number cannot be negative");
//        }
//
//        if (size <= 0 || size > 100) {
//            throw new BadRequestException("Page size must be between 1 and 100");
//        }
    	
    	Pageable pageable = PageRequest.of(page, size);
    	Page<Newsletter> newsLetter = repository.findAllByOrderByIdDesc(pageable);
    	if (newsLetter.isEmpty()) {
            throw new ResourceNotFoundException("No subscribers found");
        }
        return newsLetter.map(this::mapToResponse);
    }


    private Newsletter mapToEntity(NewsletterRequest dto) {
        Newsletter newsletter = new Newsletter();
        newsletter.setEmail(dto.getEmail());
        return newsletter;
    }

    private NewsLetterResponse mapToResponse(Newsletter entity) {
        NewsLetterResponse response = new NewsLetterResponse();
        response.setId(entity.getId());
        response.setEmail(entity.getEmail());
        return response;
    }
}
