package com.hyundai.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdminProductDTO {
	private String bname;
	private String pid;
	private String pname;
	private String depth1name;
	private String depth2name;
	private String depth3name;
}
