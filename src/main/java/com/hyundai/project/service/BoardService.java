package com.hyundai.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hyundai.project.dto.BoardDTO;


public interface BoardService {
	public List<BoardDTO> getBoard(String brand);

	public void insertBoard(BoardDTO boardDTO);
}
