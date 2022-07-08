package com.hyundai.project.controller;


import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hyundai.project.dto.BCountDTO;
import com.hyundai.project.dto.BoardDTO;
import com.hyundai.project.dto.MemberUserDetails;
import com.hyundai.project.service.BoardService;

@RequestMapping("/sommunity")
@Controller
public class SommunityController {
	
	@Autowired
	BoardService boardService;
	
	@GetMapping("/home")
	public String home() {
		
		return "sommunity/index";
	}
	
	@GetMapping("/board")
	public String brandBoard(@RequestParam("bname") String bname, @RequestParam(value="pageNum", defaultValue = "1") int pageNum, Model model) {

		int count = boardService.getCount(bname);
		model.addAttribute("bname", bname);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("count", count);
		return "sommunity/board";
	}
	
	@GetMapping("/write")
	public String writeform(@RequestParam("bname") String bname, Model model) {
		model.addAttribute("bname", bname);
		return "sommunity/writeForm";
	}
	
	@PostMapping("/writeComplete")
	public String writeComplete(BoardDTO boardDTO) {
		System.out.println(boardDTO.toString());
		String url = "";
		
		try {
			boardService.insertBoard(boardDTO);
			url = "redirect:/sommunity/board?bname=" + boardDTO.getBrand();
		}
		catch(Exception e) {
			url = "redirect:.";
		}
		return url;
	}
	
	
	@GetMapping("/view")
	public String Complete(@RequestParam("bno") String bno, Model model, Authentication authentication) {
		
		
		MemberUserDetails userDetails = (MemberUserDetails)authentication.getPrincipal();
		
		
		BoardDTO boardDTO = boardService.getBoard(bno);
		BCountDTO bCountDTO = new BCountDTO();
		bCountDTO.setBid( Integer.parseInt(bno));
		bCountDTO.setReader(userDetails.getUsername());
		
		try {
			boardService.incHits(bCountDTO);
			//sfdffsfs
			boardDTO.setBhits(boardDTO.getBhits()+1);
		}catch(Exception e) {
			
		}
		
		String cont = boardDTO.getBcontent();
		
		
		cont = cont.replaceAll("&lt;", "<");
		cont = cont.replaceAll("&gt;", ">");
		boardDTO.setBcontent(cont);
		model.addAttribute("boardDTO",boardDTO);
		return "sommunity/viewForm";
	}
	
}
