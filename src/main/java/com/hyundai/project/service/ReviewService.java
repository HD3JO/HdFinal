package com.hyundai.project.service;

import org.springframework.stereotype.Service;

import com.hyundai.project.dto.ReviewDTO;

@Service
public interface ReviewService {
	void insertReview(ReviewDTO reviewDTO);
}
