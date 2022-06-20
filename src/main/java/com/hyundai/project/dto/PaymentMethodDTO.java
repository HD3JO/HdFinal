package com.hyundai.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethodDTO {

	private String pmcode;
	private String pmcompany;
	private int pmmethod;
}
