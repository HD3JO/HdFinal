package com.hyundai.project.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.project.dto.PurchasedProductDTO;
import com.hyundai.project.dto.ReviewDTO;
import com.hyundai.project.user.repository.ReviewMapper;

@Service
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	ReviewMapper reviewMapper;
	
	@Override
	public void insertReview(ReviewDTO reviewDTO) {
		
		try {
			reviewMapper.insertReview(reviewDTO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	public List<ReviewDTO> getReview(ReviewDTO reviewDTO) {
		return reviewMapper.getReview(reviewDTO);
	}


	@Override
	public List<PurchasedProductDTO> isPurchased(PurchasedProductDTO purchasedProductDTO) {
		// TODO Auto-generated method stub
		return reviewMapper.isPurchased(purchasedProductDTO);
	}
	
	
	

}
