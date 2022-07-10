package com.hyundai.project.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AdminReplyDTO {
	private int bid;
	private int rno;
	private String rauthor;
	private String rcontent;
	private String rdate;
	
	private String btitle;
}
