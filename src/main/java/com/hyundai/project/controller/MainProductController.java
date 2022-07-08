package com.hyundai.project.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hyundai.project.dto.BestProductDTO;
import com.hyundai.project.dto.CategoryDTO;
import com.hyundai.project.dto.NewProductDTO;
import com.hyundai.project.service.MainProductService;

@Controller
@RequestMapping(value="/", method=RequestMethod.GET)
public class MainProductController {
	
	@Autowired
	private MainProductService mainProductService;
	
	
	@RequestMapping(value="/main", method=RequestMethod.GET)
	public String mainproductList(Model model, @RequestParam(value="id", required=false) String id, HttpServletRequest request) throws Exception{
		
		//chatting session
		HttpSession session =  request.getSession();
		System.out.println("chat param id 값 출력 : " + id);
		
		if(id == null) {
			String name = "guest"+session.toString().substring(session.toString().indexOf("@"));
			session.setAttribute("sessionId", name);
		}else if(id.equals("master")) {
			String name = "master";
			session.setAttribute("sessionId", name);
		}
		System.out.println("@MainProductController, getChat()");
		
		List<NewProductDTO> newproductList = mainProductService.getNewProduct("WOMEN");
		List<BestProductDTO> bestproductList = mainProductService.getBestProduct("WOMEN");
		//System.out.println(newproductList.size() + " @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		model.addAttribute("newproductList", newproductList);
		model.addAttribute("bestproductList", bestproductList);
//		mainProductService.getNewProduct(depth1name.toUpperCase());
//		mainProductService.getBestProduct(depth1name.toUpperCase());
		return "mainproductList";
	}
	
	@RequestMapping(value="/mainAjaxNew", method=RequestMethod.POST)
	public ResponseEntity<List<NewProductDTO>> mainAjaxNew(@RequestBody CategoryDTO categoryDTO) throws Exception{
		System.out.println(categoryDTO + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		List<NewProductDTO> newproductList = mainProductService.getNewProduct(categoryDTO.getDepth1name());
		newproductList= newproductList.subList(0, 4);
		return new ResponseEntity<List<NewProductDTO>>(newproductList, HttpStatus.OK);
	}
	
	@RequestMapping(value="/mainAjaxBest", method=RequestMethod.POST)
	public ResponseEntity<List<BestProductDTO>> mainAjaxBest(@RequestBody CategoryDTO categoryDTO) throws Exception{
		List<BestProductDTO> bestproductList = mainProductService.getBestProduct(categoryDTO.getDepth1name());
		bestproductList = bestproductList.subList(0, 4);
		return new ResponseEntity<List<BestProductDTO>>(bestproductList, HttpStatus.OK);
	}
	
}
