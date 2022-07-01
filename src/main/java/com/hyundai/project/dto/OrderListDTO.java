package com.hyundai.project.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderListDTO {
	private int oid;
	private String psid;
	private String email;
	private String name;
	private int oicount;
	private String pcimg1;
	private String bname;
	private String pname;
	private String pccolorcode;
	private int pcprice;
	private String psize;
	private String ostatus;
	private Date odate;
	private String pcid;
}
