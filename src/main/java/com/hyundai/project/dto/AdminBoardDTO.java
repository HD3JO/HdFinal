package com.hyundai.project.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AdminBoardDTO {
	private int bid;
	private String brand;
	private String bheader;
	private String btitle;
	private String bauthor;
	private String bdate;
	private String bimg;
	private String bcontent;
	
	private int hits;
	private int likes;
	
	private int prevDate;
	private int nextDate;
	
	
}
