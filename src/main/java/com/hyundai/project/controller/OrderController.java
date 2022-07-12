package com.hyundai.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.hyundai.project.dto.CartDTO;
import com.hyundai.project.dto.CartListDTO;
import com.hyundai.project.dto.OrderCompleteDTO;
import com.hyundai.project.dto.EmailDTO;
import com.hyundai.project.dto.MemberDTO;
import com.hyundai.project.dto.MemberUserDetails;
import com.hyundai.project.dto.OrderDTO;
import com.hyundai.project.dto.OrderItemDTO;
import com.hyundai.project.dto.OrderListDTO;
import com.hyundai.project.dto.OrderMemberDTO;
import com.hyundai.project.dto.OrderPageDTO;
import com.hyundai.project.dto.PaymentMethodDTO;
import com.hyundai.project.service.EmailService;
import com.hyundai.project.service.MemberService;
import com.hyundai.project.service.MessageService;
import com.hyundai.project.service.OrderMemberService;
import com.hyundai.project.service.OrderPageService;
import com.hyundai.project.service.OrderService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderPageService orderPageService;
	
	@Autowired
	private OrderMemberService orderMemberService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private MessageService messageService;
	
	@PostMapping("/orderSheet")
	public String productOrderSheet (Authentication authentication, CartListDTO list, Model model) throws Exception {
		
		// 로그인 정보 받아오기 위한 UserDetails 클래스 사용
		if(authentication == null) {
			return "redirect:/customLogin";
		}
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
	public ResponseEntity<OrderDTO> insertOrder(@RequestBody OrderDTO orderDTO) throws Exception{
		List<OrderItemDTO> orderItemList = orderDTO.getOrderItemList();
		int rows = orderService.insertOrder(orderDTO, orderItemList);		

		if(rows == 0) {
			return new ResponseEntity<OrderDTO>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<OrderDTO>(orderDTO, HttpStatus.OK);
	}
	
	@GetMapping("/orderComplete")
	public String orderComplete(@RequestParam String oid, Model model, Authentication authentication) throws Exception {
		if(authentication == null) {
			return "redirect:/customLogin";
		}
		List<OrderCompleteDTO> completeList = orderService.getOrderComplete(oid);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		EmailDTO emailDTO = new EmailDTO();
		emailDTO.setAddress(email);
		emailDTO.setTitle("[4st.men] 주문완료 이메일입니다.");
		Map<String, Object> variables = new HashMap<>();
		variables.put("orderList", completeList);
		if(completeList.get(0).getMarketingemail().equals("Y")) {
			emailService.sendMailWithFiles(emailDTO, variables);
		}
		else{
			log.info("");
		}
		if(completeList.get(0).getMarketingsms().equals("Y")) {
			messageService.sendMessage(completeList);
		}
		else{
			log.info("");
		}
		model.addAttribute("complete", completeList);

		return "order/orderComplete";
	}
	
	@GetMapping("/orderStatus")
	public String orderStatus(Model model, @RequestParam("oid") String oid) throws Exception {
		
		List<OrderCompleteDTO> completeList = orderService.getOrderComplete(oid);
		model.addAttribute("orderList", completeList);
		model.addAttribute("oid", oid);
		return "order/orderStatus";
	}
	
	@GetMapping("/orderDetail")
	public String orderDetail(Model model, @RequestParam("oid") String oid) throws Exception {
		List<OrderCompleteDTO> completeList = orderService.getOrderComplete(oid);
		String odate = completeList.get(0).getOdate();
		String pmcompany = completeList.get(0).getPmcompany();
		int oafterprice = completeList.get(0).getOafterprice();
		String oaddress1 = completeList.get(0).getOaddress1();
		String oaddress2 = completeList.get(0).getOaddress2();
		String oreceiver = completeList.get(0).getOreceiver();
		String ophone = completeList.get(0).getOphone();
		String phone = completeList.get(0).getPhone();
		model.addAttribute("orderList", completeList);
		model.addAttribute("oid", oid);
		model.addAttribute("odate", odate);
		model.addAttribute("ophone", ophone);
		model.addAttribute("phone", phone);
		model.addAttribute("oreceiver", oreceiver);
		model.addAttribute("pmcompany", pmcompany);
		model.addAttribute("oafterprice", oafterprice);
		model.addAttribute("oaddress1", oaddress1);
		model.addAttribute("oaddress2", oaddress2);
		model.addAttribute("oafterprice", oafterprice);
		return "order/orderDetail";
	}
	
}
