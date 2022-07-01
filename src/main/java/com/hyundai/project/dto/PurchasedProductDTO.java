package com.hyundai.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PurchasedProductDTO {
	private String email;
	private String pcid;
	private String psid;
	private String pccolorcode;
	private String psize;
	private String pcimg1;
	private String oreceiver;
	private String ostatus;
	//   ORECEIVER,  OSTATUS,
}
