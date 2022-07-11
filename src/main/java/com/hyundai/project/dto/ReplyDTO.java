package com.hyundai.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDTO {
	private String bid;
	private String rno;
	private String rauthor;
	private String rcontent;
	private String rdate;
	private int disabled;
}
