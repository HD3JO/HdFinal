package com.hyundai.project.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class DrawWinDTO {
	private String email;
	private String psid;
	private String pcid;
	private String pid;
}
