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

import com.app.requestdto.NewsletterRequest;
import com.app.responsedto.NewsLetterResponse;
import com.app.service.NewsLetterService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/newsletter")
@Validated
public class NewsLetterController {

    @Autowired
    private NewsLetterService service;

    @PostMapping("/subscribe")
    public NewsLetterResponse subscribe(@Valid @RequestBody NewsletterRequest request) {
        return service.subscribe(request);
    }

    @GetMapping("/all")
    public Page<NewsLetterResponse> getAllSubscribers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return service.getAllSubscribers(page, size);
    }

}
