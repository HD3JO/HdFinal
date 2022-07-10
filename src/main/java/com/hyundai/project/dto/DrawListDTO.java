package com.hyundai.project.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString 
public class DrawListDTO implements Serializable{
	
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
	private String regyn;
	
	private int cntByPsid;
	
	private List<DrawRegDTO> modRegList; 
}
