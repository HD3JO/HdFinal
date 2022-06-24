package com.hyundai.project.user.repository;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hyundai.project.dto.ReviewDTO;

@SpringBootTest
public class ReviewMapperTests {
	/*
	@Autowired
	ReviewMapper reviewMapper;
	
	@Test
	public void insertReviewTest() throws SQLException {
		ReviewDTO reviewDTO = new ReviewDTO();
		reviewDTO.setRid(3);
		reviewDTO.setTitle("리뷰3");
		reviewDTO.setRcontent("리뷰 내용3");
		reviewDTO.setRimage("");
		reviewDTO.setEmail("seokjune96@naver.com");
		reviewDTO.setPsid("CM2B1KTO412WPK85");
		
		reviewMapper.insertReview(reviewDTO);
	}
	
	@Test
	public void selectReviewTest() throws SQLException {
		ReviewDTO reviewDTO = new ReviewDTO();
		reviewDTO.setRid(1);
		reviewDTO.setTitle("리뷰1");
		reviewDTO.setRcontent("리뷰 내용");
		reviewDTO.setRimage("");
		reviewDTO.setEmail("seokjune96@naver.com");
		reviewDTO.setPsid("CM2B1KTO412WPK85");
		
		System.out.println(reviewMapper.selectReview(reviewDTO).get(0));
	}
	
	@Test
	public void updateReviewTest() throws SQLException {
		ReviewDTO reviewDTO = new ReviewDTO();
		reviewDTO.setRcontent("리뷰 내용");
		reviewDTO=reviewMapper.selectReview(reviewDTO).get(0);
		System.out.println(reviewMapper.selectReview(reviewDTO).get(0));
		
		reviewDTO.setRcontent("리뷰 수정");
		reviewMapper.updateReview(reviewDTO);
	}
	
	@Test
	public void deleteReviewTest() throws SQLException {
		reviewMapper.deleteReview(3);
	}
	*/
}
