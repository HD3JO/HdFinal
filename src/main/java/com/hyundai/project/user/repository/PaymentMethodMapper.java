package com.hyundai.project.user.repository;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyundai.project.dto.PaymentMethodDTO;

@Mapper
public interface PaymentMethodMapper {

	public List<PaymentMethodDTO> selectPaymentMethod(PaymentMethodDTO payment) throws SQLException;
	
	public void insertPaymentMethod(PaymentMethodDTO payment) throws SQLException;
	
	public void updatePaymentMethod(PaymentMethodDTO payment) throws SQLException;
	
	public void deletePaymentMethod(String pmcode) throws SQLException;
}
