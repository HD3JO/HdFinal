package com.hyundai.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hyundai.project.dto.PurchasedProductDTO;
import com.hyundai.project.dto.ReviewDTO;

@Service
public interface ReviewService {
	
	void insertReview(ReviewDTO reviewDTO);
	
	List<ReviewDTO> getReview(ReviewDTO reviewDTO);

	List<PurchasedProductDTO> isPurchased(PurchasedProductDTO purchasedProductDTO);
	
	int reviewcount(String email);
	
	String reviewCheck(String email);
	
}
