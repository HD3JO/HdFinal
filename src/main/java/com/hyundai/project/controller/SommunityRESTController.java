package com.hyundai.project.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hyundai.project.dto.BCountDTO;
import com.hyundai.project.dto.BoardDTO;
import com.hyundai.project.dto.MemberDTO;
import com.hyundai.project.dto.ReplyDTO;
import com.hyundai.project.service.BoardService;
import com.hyundai.project.service.S3Service;

import lombok.RequiredArgsConstructor;

@RestController
public class SommunityRESTController {
	
	@Autowired
	BoardService boardService;
	
	@PostMapping(value="/getBoard")
	public List<BoardDTO> getBoardList(@RequestBody BoardDTO boardDTO) {
		return boardService.getBoardList(boardDTO.getBrand());
	} 
	
	@PostMapping(value="/getPageBoard")
	public List<BoardDTO> getPageBoard(@RequestBody BoardDTO boardDTO) {
		System.out.println(boardDTO);
		return boardService.getPageBoard(boardDTO);
	} 
	
	@PostMapping(value="/delBoard")
	public void delBoard(@RequestParam("bid") String bid) {
		boardService.delBoard(bid);
	}
	
	@PostMapping(value="/enrollReply")
	public boolean enrollReply(@RequestBody ReplyDTO replyDTO) {
		int n = boardService.getTodayReplayCnt(replyDTO.getRauthor());
		if(n<10) {
			//오늘 10개 미만로 작성한 경우 10포린트 지급
			boardService.getMileage(10, replyDTO.getRauthor());
		}
		boardService.enrollReply(replyDTO);
		return true;
	}
	
	@PostMapping(value="/getReplyList")
	public List<ReplyDTO> getReplyList (@RequestBody ReplyDTO replyDTO) {
		return boardService.getReplyList(replyDTO.getBid());
	}
	
	@PostMapping(value="/delReply")
	public void delReply(@RequestParam("rno") String rno) {
		boardService.delReply(rno);
	}
	
	@PostMapping(value="/incLikes")
	public boolean incLikes(@RequestBody BCountDTO bCountDTO) throws SQLException {
		boardService.incLikes(bCountDTO);
		return true;
	}
	
	@PostMapping(value="/getLikesCount")
	public int getLikesCount(@RequestParam("bid") String bid) {
		return boardService.getLikesCount(bid);
	}
	
	@PostMapping(value="/showMileage")
	public MemberDTO showMileage (@RequestBody MemberDTO memberDTO) {
		System.out.println(memberDTO + "done1");
		return boardService.showMileage(memberDTO.getEmail());
	}
	
	@PostMapping(value="/getTodayReplayCnt")
	public int getTodayReplayCnt (@RequestParam("email") String email) {
		return boardService.getTodayReplayCnt(email);
	}
	
}
