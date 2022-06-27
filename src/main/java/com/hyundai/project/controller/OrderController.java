package com.hyundai.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hyundai.project.dto.CartDTO;
import com.hyundai.project.dto.CartListDTO;
import com.hyundai.project.dto.OrderDTO;
import com.hyundai.project.dto.OrderItemDTO;
import com.hyundai.project.dto.OrderMemberDTO;
import com.hyundai.project.dto.OrderPageDTO;
import com.hyundai.project.dto.PaymentMethodDTO;
import com.hyundai.project.service.OrderMemberService;
import com.hyundai.project.service.OrderPageService;
import com.hyundai.project.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderPageService orderPageService;
	
	@Autowired
	private OrderMemberService orderMemberService;
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/orderSheet")
	public String productOrderSheet (Authentication authentication, CartListDTO list, Model model) throws Exception {
		
		// 로그인 정보 받아오기 위한 UserDetails 클래스 사용 
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		// 현재 로그인 유저 이메일 
		String email = userDetails.getUsername();
		
		// 현재 로그인 유저 정보
		OrderMemberDTO member = orderMemberService.getOrderMember(email);
		
		// 결제수단 정보
		List<PaymentMethodDTO> paylist = orderPageService.getPaymentMethod();

		// 상품 정보 받기(장바구니에서 여러개 상품이 넘어올 수도 있으므로 List형으로 받는다.)
		List<OrderPageDTO> productList = orderPageService.selectProduct(list.getList());
		
		// 장바구니 상품의 수량과 주문 상품의 수량을 똑같이 맞춰준다.
		for(OrderPageDTO dto : productList) {
			for(CartDTO cart : list.getList()) {
				if((cart.getPsid()).equals(dto.getPsid()))
					dto.setPquantity(cart.getPquantity());
			}
		}
		
		// 상품, 로그인, 결제수단 정보 model에 담아서 전달	
		model.addAttribute("productList", productList);
		model.addAttribute("member", member);
		model.addAttribute("payList", paylist);
		
		return "order/order"; 
	}
	
	@PostMapping("/insertOrder")
	public ResponseEntity<String> insertOrder(@RequestBody OrderDTO orderDTO) throws Exception{
		List<OrderItemDTO> orderItemList = orderDTO.getOrderItemList();
		int rows = orderService.insertOrder(orderDTO, orderItemList);		

		if(rows == 0) {
			return new ResponseEntity<>("Order is fail.", HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<>("Order is done.", HttpStatus.OK);
	}
	
	@GetMapping("/orderComplete")
	public String orderComplete() {
		return "/order/orderComplete";
	}
}
