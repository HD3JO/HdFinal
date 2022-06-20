package com.hyundai.project.user.repository;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hyundai.project.dto.PaymentMethodDTO;

@SpringBootTest
public class PaymentMethodMapperTests {

	@Autowired
	private PaymentMethodMapper paymentmapper;
	
	@Test
	public void insertPaymentMethodTest() throws SQLException {
		PaymentMethodDTO payment = new PaymentMethodDTO();
		payment.setPmcode("PMM");
		payment.setPmcompany("aaaa");
		payment.setPmmethod(1);
		
		paymentmapper.insertPaymentMethod(payment);
	}
	
	
	@Test
	public void selectPaymentMethodTest() throws SQLException {
		PaymentMethodDTO payment = new PaymentMethodDTO();
		payment.setPmcode("PM02");
		payment.setPmcompany("KDB");
		payment.setPmmethod(1);
		
		System.out.println(paymentmapper.selectPaymentMethod(payment).get(0));
	}
	
	
	@Test
	public void updatePaymentMethodTest() throws SQLException {
		PaymentMethodDTO payment = new PaymentMethodDTO();
		payment.setPmcode("PM02");
		payment=paymentmapper.selectPaymentMethod(payment).get(0);
		System.out.println(paymentmapper.selectPaymentMethod(payment).get(0));
		
		payment.setPmcompany("KB");
		paymentmapper.updatePaymentMethod(payment);
	}
	
	
	@Test
	public void deletePaymentMethodTest() throws SQLException {
		paymentmapper.deletePaymentMethod("PM");
	}
}
