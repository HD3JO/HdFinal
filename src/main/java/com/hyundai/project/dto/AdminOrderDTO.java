package com.hyundai.project.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AdminOrderDTO implements Serializable{
	
	private int oid;
	private String oaddress1;
	private String oreceiver;
	private String ophone;
	private String otel;
	private String omemo;
	private String oemail;
	private int obeforeprice;
	private int oafterprice;
	private int mileage;
	private String ostatus;
	private String email;
	private String pmcode;
	
	private String odate;
	private String oaddress2;
	
	
	private String startdate;
	
	private String enddate;
	
	private List<OrderItemDTO> orderItemList;
}