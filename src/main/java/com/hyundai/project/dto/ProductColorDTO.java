package com.hyundai.project.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductColorDTO implements Serializable {
	private String pid;
	private String pcid;
	private String pccolorcode;
	private String pcchipimg;
	private String pcimg1;
	private String pcimg2;
	private String pcimg3;
	
}
