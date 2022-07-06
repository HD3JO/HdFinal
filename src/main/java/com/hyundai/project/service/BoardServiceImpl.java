package com.hyundai.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.project.board.repository.BoardMapper;
import com.hyundai.project.dto.BoardDTO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardMapper boardMapper;
	
	@Override
	public List<BoardDTO> getBoard(String brand) {
		return boardMapper.getBoard(brand);
	}

	@Override
	public void insertBoard(BoardDTO boardDTO) {
		boardMapper.insertBoard(boardDTO);
		return;
	}
}
