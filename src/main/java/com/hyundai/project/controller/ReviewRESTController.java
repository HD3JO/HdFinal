package com.hyundai.project.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hyundai.project.dto.ReviewDTO;
import com.hyundai.project.service.ReviewService;

@RestController
public class ReviewRESTController {

	@Autowired
	ReviewService reviewService;
	
	@PostMapping(value="/uploadFile")
	@ResponseBody
	public String uploadFile(@RequestPart("file") MultipartFile uploadfile) {
		
		String result = "";
		try {
		    // Get the filename and build the local file path 
		    // (be sure that the application have write permissions on such directory)
			UUID uuid = UUID.randomUUID();
		    String filename = uuid + uploadfile.getOriginalFilename();
		    String directory = "C:\\file_repo";
		    String filepath = Paths.get(directory, filename).toString();
		    
		    // Save the file locally
		    BufferedOutputStream stream =
		        new BufferedOutputStream(new FileOutputStream(new File(filepath)));
		    stream.write(uploadfile.getBytes());
		    stream.close();
		    result = filepath;
		  }
		  catch (Exception e) {
		    System.out.println(e.getMessage());   
		  }
		return result;
	}
	
	@PostMapping(value="/insertReview")
	public String insertReview (@RequestBody ReviewDTO reviewDTO) {	
		try {
			reviewService.insertReview(reviewDTO);
			return "성공";
		}catch(Exception e) {
			return "실패";
		}
	}
	
}
