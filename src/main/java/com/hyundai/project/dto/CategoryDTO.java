package com.hyundai.project.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

	private int cateNo;
	private String depth1name;
	private String depth2name;
	private String depth3name;
	
	private int pagenum;
	private String pname;
	
	private List<CategoryDTO> subList;
	
}
