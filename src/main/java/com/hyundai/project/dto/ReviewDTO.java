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

	private int rid;
	private String title;
	private String rcontent;
	private Date rdate;
	private String rimage;
	private String email;
	private String psid;
}
