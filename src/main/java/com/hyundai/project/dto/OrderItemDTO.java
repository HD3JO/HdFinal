package com.hyundai.project.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderItemDTO {
	private String psid;
	private int oid;
	private int oicount;
	private int oitotalprice;
	
	private String pname;
	private String ostatus;
	private String mid;
}
