package com.hyundai.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BestProductDTO {

	private String pid;
	private String pcid;
	private String bname;
	private int pcprice;
	private String pcimg1;
	private String depth1name;
	private int rcount;
	private String category;
}
