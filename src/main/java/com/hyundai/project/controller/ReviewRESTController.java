package com.hyundai.project.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hyundai.project.dto.PurchasedProductDTO;
import com.hyundai.project.dto.ReviewDTO;
import com.hyundai.project.service.ProductService;
import com.hyundai.project.service.ReviewService;

@RestController
public class ReviewRESTController {

	@Autowired
	ReviewService reviewService;
	ProductService productService;
	
	@PostMapping(value="/uploadFile")
	@ResponseBody
	public String uploadFile(@RequestPart("file") MultipartFile uploadfile) {
		HttpHeaders header = new HttpHeaders();
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

	
	@PostMapping("/display")
	public ResponseEntity<byte[]> getFile(String filepath) {
		
		File file = new File(filepath);
		
		ResponseEntity<byte[]> result = null;
		try {
			HttpHeaders header = new HttpHeaders();
			header.add("Content-Type",Files.probeContentType(file.toPath()));
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		} catch(IOException e) {
			e.printStackTrace();
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
	
	
	@PostMapping(value="/getReview")
	public ResponseEntity<List<ReviewDTO>> getReview (@RequestBody ReviewDTO reviewDTO) {
		try {
			List<ReviewDTO> dto = reviewService.getReview(reviewDTO);
			return new ResponseEntity<>(dto, HttpStatus.OK);
		}catch(Exception e) {
			return null;
		}
	}
	
	
	@PostMapping(value="/getPhotoReview")
	public ResponseEntity<List<ReviewDTO>> getPhotoReview (@RequestBody ReviewDTO reviewDTO) {
		try {
			List<ReviewDTO> dto = reviewService.getReview(reviewDTO);
			
			List<ReviewDTO> photoReview = new ArrayList<ReviewDTO>();
			
			for(ReviewDTO k : dto) {
				if(k.getFilepath() != null) {
					photoReview.add(k);
				}
			}
			return new ResponseEntity<>(photoReview, HttpStatus.OK);
		}catch(Exception e) {
			return null;
		}
	}
	
	@PostMapping(value="/getCommonReview")
	public ResponseEntity<List<ReviewDTO>> getCommonReview (@RequestBody ReviewDTO reviewDTO) {
		try {
			List<ReviewDTO> dto = reviewService.getReview(reviewDTO);
			
			List<ReviewDTO> CommonReview = new ArrayList<ReviewDTO>();
			
			for(ReviewDTO k : dto) {
				if(k.getFilepath() == null) {
					CommonReview.add(k);
				}
			}
			return new ResponseEntity<>(CommonReview, HttpStatus.OK);
		}catch(Exception e) {
			return null;
		}
	}
	
	
	
	@PostMapping(value="/isPurchased")
	public ResponseEntity<List<PurchasedProductDTO>> getNewProduct(@RequestBody PurchasedProductDTO purchasedProductDTO) {
		
		List<PurchasedProductDTO> list = reviewService.isPurchased(purchasedProductDTO);
		
		if (list.size() <= 0) 
			return null;
		
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	
	
}
