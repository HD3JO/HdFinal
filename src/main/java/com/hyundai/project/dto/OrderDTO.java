package com.hyundai.project.dto;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderDTO {
	
	private int oid;
	private String oaddress1;
	private String oreceiver;
	private String ophone;
	private String otel;
	private String omemo;
	private String oemail;
	private int obeforeprice;
	private int oafterprice;
	private String ostatus;
	private String email;
	private String pmcode;
	private Date odate;
	private String oaddress2;
	
	private List<OrderItemDTO> orderItemList;
}