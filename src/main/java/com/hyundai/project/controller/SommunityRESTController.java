package com.hyundai.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hyundai.project.dto.BoardDTO;
import com.hyundai.project.dto.ReplyDTO;
import com.hyundai.project.service.BoardService;

@RestController
public class SommunityRESTController {
	
	@Autowired
	BoardService boardService;
	
	@PostMapping(value="/getBoard")
	public List<BoardDTO> getBoardList(@RequestBody BoardDTO boardDTO) {
		return boardService.getBoardList(boardDTO.getBrand());
	} 
	
	@PostMapping(value="/delBoard")
	public void delBoard(@RequestParam("bid") String bid) {
		boardService.delBoard(bid);
	}
	
	@PostMapping(value="/enrollReply")
	public void enrollReply(@RequestBody ReplyDTO replyDTO) {
		boardService.enrollReply(replyDTO);
		return;
	}
	
	@PostMapping(value="/getReplyList")
	public List<ReplyDTO> getReplyList (@RequestBody ReplyDTO replyDTO) {
		return boardService.getReplyList(replyDTO.getBid());
	}
	
	@PostMapping(value="/delReply")
	public void delReply(@RequestParam("rno") String rno) {
		boardService.delReply(rno);
	}
	
}
