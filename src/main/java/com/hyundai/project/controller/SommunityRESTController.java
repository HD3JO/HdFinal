package com.hyundai.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hyundai.project.dto.BoardDTO;
import com.hyundai.project.service.BoardService;

@RestController
public class SommunityRESTController {
	
	@Autowired
	BoardService boardService;
	
	@PostMapping(value="/getBoard")
	public List<BoardDTO> getBoard(@RequestBody BoardDTO boardDTO) {
		return boardService.getBoard(boardDTO.getBrand());
	} 
	
}
