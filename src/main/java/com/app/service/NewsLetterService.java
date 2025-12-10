package com.app.service;


import org.springframework.data.domain.Page;

import com.app.requestdto.NewsletterRequest;
import com.app.responsedto.NewsLetterResponse;

public interface NewsLetterService {
	NewsLetterResponse subscribe(NewsletterRequest request);
	Page<NewsLetterResponse> getAllSubscribers(int page, int size);

}
