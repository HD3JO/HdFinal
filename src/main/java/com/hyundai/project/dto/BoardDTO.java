package com.hyundai.project.dto;

import java.sql.Clob;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
	private int seq;
	private int bid;
	private String brand;
	private String bheader;
	private String btitle;
	private String bcontent;
	private String bauthor;
	private String bdate;
	private Clob bimg;
	private int pageNum;
	private int bhits;
	private int blikes;
}
