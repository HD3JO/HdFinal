package com.hyundai.project.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderCompleteDTO {
	private int oid;
    private String odate;
    private int oafterprice;
    private int mileage;
    private String oaddress1;
    private String oaddress2;
    private String oreceiver;
    private String ophone;
    private String name;
    private String phone;
    private String email;
    private String psid;
    private int oicount;
    private String psize; 
    private int pcprice;
    private String pcimg1;
    private String pccolorcode;
    private String pname;
    private String bname;
    private String pmcompany;
    private String pcid;
    private String ostatus;
    private String marketingemail;
	private String marketingsms;
	
}
