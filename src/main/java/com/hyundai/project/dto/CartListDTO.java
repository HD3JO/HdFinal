package com.hyundai.project.dto;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class CartListDTO {
	private List<CartDTO> list;
}
