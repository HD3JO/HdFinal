package com.hyundai.project.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {

	private String email;
	private String name;
	private String rdate;
	private String grade;
	private String pcid;
	private String psize;
	private String bname;
	private String pname;
	private String pcprice;
	private String age;
	private String height;
	private String enjoysize;
	private String bodytype;
	private String reviewcontent;
	private String filepath;
	private String pccolorcode;
}
