package com.hyundai.project.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Data
@ToString
public class DrawDTO {
	private String email;
	private String psid;
	private String pcid;
	private String pid;
	private int pmileage;
}
