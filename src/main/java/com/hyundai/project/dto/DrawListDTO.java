package com.hyundai.project.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Data
@ToString
public class DrawListDTO {
	
	private String email;
	private String psid;
	private String pcid;
	private String pid;
	private String pmimg1;
	private String pmimg2;
	private String bname;
	private String pname;
	private int pmileage;
	private String psize;
	private String pcolorcode;
	private String pnote;
	private String openyn;
}
