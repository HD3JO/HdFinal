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
public class ProductViewDTO {

	private String bname;
	private String pid;
	private String pname;
	private int cateNo;
	private String pcimg1;
	private String pcid;
	private String pcchipimg;
	private String pcprice;
}
