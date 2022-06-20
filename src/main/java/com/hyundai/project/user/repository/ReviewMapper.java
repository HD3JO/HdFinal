package com.hyundai.project.user.repository;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyundai.project.dto.ReviewDTO;

@Mapper
public interface ReviewMapper {

	public List<ReviewDTO> selectReview(ReviewDTO reviewDTO) throws SQLException;
	
	public void insertReview(ReviewDTO reviewDTO) throws SQLException;
	
	public void updateReview(ReviewDTO reviewDTO) throws SQLException;
	
	public void deleteReview(int rid) throws SQLException;
}