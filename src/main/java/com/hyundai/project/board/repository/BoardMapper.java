package com.hyundai.project.board.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyundai.project.dto.BoardDTO;

@Mapper
public interface BoardMapper {

	List<BoardDTO> getBoard(String brand);

	void insertBoard(BoardDTO boardDTO);

}
