package com.hyundai.project.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyundai.project.dto.AdminBoardDTO;
import com.hyundai.project.dto.AdminOrderDTO;
import com.hyundai.project.dto.AdminProductDTO;

import com.hyundai.project.dto.AdminReplyDTO;

import com.hyundai.project.dto.DrawListDTO;
import com.hyundai.project.dto.DrawWinDTO;

import com.hyundai.project.dto.MemberDTO;
import com.hyundai.project.dto.OrderCompleteDTO;
import com.hyundai.project.dto.PaymentMethodDTO;
import com.hyundai.project.dto.ProductCommonDTO;
import com.hyundai.project.dto.ProductDetailDTO;
import com.hyundai.project.dto.ProductStockDTO;

import com.hyundai.project.service.AdminBoardService;

import com.hyundai.project.product.repository.DrawMapper;

import com.hyundai.project.service.AdminMainService;
import com.hyundai.project.service.AdminOrderService;
import com.hyundai.project.service.AdminProductService;
import com.hyundai.project.service.DrawService;
import com.hyundai.project.service.MemberService;
import com.hyundai.project.user.repository.OrderMapper;
import com.hyundai.project.user.repository.PaymentMethodMapper;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private AdminProductService adminProductService;
	@Autowired
	private AdminMainService adminMainService;
	@Autowired
	private AdminOrderService adminOrderService;
	@Autowired
	private AdminBoardService adminBoardService;
	@Autowired
	private DrawService drawService;
	@Autowired
	private PaymentMethodMapper paymentMethodMapper;
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private DrawMapper drawMapper;	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String index(Model model) throws Exception {
		int totalusercount = adminMainService.getTotalUser();
		int totalordercount = adminMainService.getMonthOrderCount();
		int totalproductcount = adminMainService.getTotalProductQty();
		int totalproductprice = adminMainService.getMonthOrderPrice();		
		
		// 날짜별 게시글 현황
		AdminBoardDTO adminBoardDTO = new AdminBoardDTO();
		
		Calendar cal = Calendar.getInstance();
		int last_day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		int[] cnt = new int[last_day];
		
		for(int i = 1; i <= last_day; i++) {
			int firstDay = i;
			int SecondDay = i+1;
			
			adminBoardDTO.setPrevDate(firstDay);
			adminBoardDTO.setNextDate(SecondDay);
			
			cnt[i-1] = adminBoardService.getBoardCntByDay(adminBoardDTO);
		}		
		
		ObjectMapper mapper = new ObjectMapper();
		
		// psid별 드로우 응모현황
		List<DrawListDTO> drawList = drawService.getDrawList();
		
		Set<String> redisKeys = redisTemplate.keys("*");
		Iterator<String> keys = redisKeys.iterator();
		List<String> keysList = new ArrayList<>();
		List<Integer> cntByPsidList = new ArrayList<>();
		int cntByPsid = 0;
		int totalCnt = 0;
		
		// redis에 있는 모든 키 값들 리스트 형태로 받아옴
				while(keys.hasNext()) {
					String data = keys.next();
					keysList.add(data);
				}
				
				// 드로우 상품 별 응모자 수
				for(int i = 0; i < drawList.size(); i++) {						 
					 for(int j = 0; j < keysList.size(); j++) {
						 Object val = redisTemplate.opsForValue().get(String.valueOf(keysList.get(j)));
						 Map<String, Object> map = mapper.convertValue(val, Map.class);
						 
						 if(((String)map.get("psid")).equals(drawList.get(i).getPsid())) {
							 cntByPsid++;
						 }
					 }
					 cntByPsidList.add(cntByPsid);
					 drawList.get(i).setCntByPsid(cntByPsid);
					 totalCnt += cntByPsid; 
					 cntByPsid = 0;
				 }
		
		// 등급별 사용자 수
		int[] cntByGrade = new int[5];
		
		cntByGrade[0] = adminMainService.getMemberGrade("FRIEND");
		cntByGrade[1] = adminMainService.getMemberGrade("CREW");
		cntByGrade[2] = adminMainService.getMemberGrade("MANIA");
		cntByGrade[3] = adminMainService.getMemberGrade("STAR");
		cntByGrade[4] = adminMainService.getMemberGrade("THE STAR");
		
		// 멤버 매출 탑5
		List<MemberDTO> dto = adminMainService.rankMember();
		
		model.addAttribute("cnt", cnt);
		model.addAttribute("total", totalCnt);
		model.addAttribute("grade", cntByGrade);
		model.addAttribute("rank", dto);
		model.addAttribute("drawList", drawList);
		model.addAttribute("totalproductprice", totalproductprice);
		model.addAttribute("totalproductcount", totalproductcount);
		model.addAttribute("totalordercount", totalordercount);
		model.addAttribute("totalusercount", totalusercount);
		return "admin/index";
	}
	
	@RequestMapping(value="/blank", method=RequestMethod.GET)
	public String blank() {
		return "admin/blank";
	}
	@RequestMapping(value="/buttons", method=RequestMethod.GET)
	public String buttons() {
		return "admin/buttons";
	}
	@RequestMapping(value="/cards", method=RequestMethod.GET)
	public String cards() {
		return "admin/cards";
	}
	@RequestMapping(value="/charts", method=RequestMethod.GET)
	public String charts() {
		return "admin/charts";
	}
	@RequestMapping(value="/forgotpassword", method=RequestMethod.GET)
	public String forgotpassowrd() {
		return "admin/forgot-password";
	}
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String register() {
		return "admin/register";
	}
	@RequestMapping(value="/membertables", method=RequestMethod.GET)
	public String tables(Model model) throws Exception {
		List<MemberDTO> list = memberService.selectUserAdmin(new MemberDTO());
		model.addAttribute("memberList", list);
		System.out.println(list);
		return "admin/tables";
	}
	@RequestMapping(value="/membertables", method=RequestMethod.POST)
	public String membertables(MemberDTO memberDTO, Model model) throws Exception{
		System.out.println(memberDTO);
		List<MemberDTO> list = memberService.selectUserAdmin(memberDTO);
		model.addAttribute("memberList", list);
		System.out.println("member tables");
		System.out.println(list);
		return "admin/tables";
	}
	
	@RequestMapping(value="/modifyMember", method=RequestMethod.POST)
	public ResponseEntity<String> modifyMember(@RequestBody List<MemberDTO> memberList) throws Exception{
		System.out.println(memberList);
		memberService.updateMemeberAdmin(memberList);
		
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	@RequestMapping(value="/producttables", method=RequestMethod.GET)
	public String producttablesGet(Model model) throws Exception{
		model.addAttribute("brandlist", adminProductService.getBrand());
		
		return "admin/producttables";
	}
	
	@RequestMapping(value="/producttables", method=RequestMethod.POST)
	public String producttablesGet(Model model, AdminProductDTO adminProductCommon) throws Exception{
		System.out.println(adminProductCommon+"@@@@@@@@@@@@@@@");
		model.addAttribute("selectedBname",adminProductCommon.getBname());
		model.addAttribute("selectedPname",adminProductCommon.getPname());
		
		model.addAttribute("brandlist", adminProductService.getBrand());
		List<AdminProductDTO> list = adminProductService.getProductCommon(adminProductCommon);
		model.addAttribute("productlist", list);
		return "admin/producttables";
	}
	
	@RequestMapping(value="/productDetail", method=RequestMethod.POST)
	public ResponseEntity<List<ProductDetailDTO>> productDetailPost(@RequestBody ProductCommonDTO productCommonDTO) throws Exception{
		List<ProductDetailDTO> list = adminProductService.getProductDetail(productCommonDTO.getPid());
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@RequestMapping(value="/updateProductStock", method=RequestMethod.POST)
	public ResponseEntity<String> updateProductStock(@RequestBody List<ProductStockDTO> productStockDTOList) throws Exception{
		for(ProductStockDTO a : productStockDTOList) {
			System.out.println(a);
		}
		adminProductService.updateProductStock(productStockDTOList);
		
		return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
	}
	
	@RequestMapping(value="/chat", method=RequestMethod.GET)
	public String chat(HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession();
		session.setAttribute("sessionId", "master");
		
		return "admin/chat";
	}
	
	@RequestMapping(value="/order", method=RequestMethod.GET)
	public String order(Model model) throws Exception{
		List<PaymentMethodDTO> list = paymentMethodMapper.selectPaymentMethod(new PaymentMethodDTO());
		for(PaymentMethodDTO a : list) {
			System.out.println(a+" @@@@@");
		}
		model.addAttribute("paymentlist", list);
		return "admin/order";
	}
	
	@RequestMapping(value="/order", method=RequestMethod.POST)
	public String orderPost(AdminOrderDTO adminOrderDTO, Model model) throws Exception{
		System.out.println(adminOrderDTO);
		List<AdminOrderDTO> list = adminOrderService.getOrderList(adminOrderDTO);
		for(AdminOrderDTO a : list) {
			System.out.println(a + "@@@@@");
		}
		model.addAttribute("orderlist", list);
		
		//검색input값들을 위한 모델값들
		model.addAttribute("oreceiver", adminOrderDTO.getOreceiver());
		model.addAttribute("email", adminOrderDTO.getEmail());
		model.addAttribute("ostatus", adminOrderDTO.getOstatus());
		model.addAttribute("pmcode", adminOrderDTO.getPmcode());
		model.addAttribute("startdate", adminOrderDTO.getStartdate());
		model.addAttribute("enddate", adminOrderDTO.getEnddate());
		model.addAttribute("pmcode", adminOrderDTO.getPmcode());
		
		List<PaymentMethodDTO> paymentlist = paymentMethodMapper.selectPaymentMethod(new PaymentMethodDTO());
		
		model.addAttribute("paymentlist", paymentlist);
		
		
		return "admin/order";
	}
	
	@RequestMapping(value="/modifyOrder", method=RequestMethod.POST)
	public ResponseEntity<String> modifyOrder(@RequestBody List<AdminOrderDTO> adminOrderDTOList) throws Exception{
		System.out.println(adminOrderDTOList);
		adminOrderService.updateOrder(adminOrderDTOList);
		
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	@RequestMapping(value="/getOrderItem", method=RequestMethod.POST)
	public ResponseEntity<List<OrderCompleteDTO>> getOrderItem(@RequestBody AdminOrderDTO adminOrderDTO) throws Exception{
		System.out.println(adminOrderDTO.getOid());
		List<OrderCompleteDTO> list = orderMapper.getOrderComplete(Integer.toString(adminOrderDTO.getOid()));
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@RequestMapping(value="/board", method=RequestMethod.GET)
	public String board(Model model) throws Exception{
		model.addAttribute("brandlist", adminProductService.getBrand());
		System.out.println(adminProductService.getBrand());
		
		return "admin/board";
	}
	
	@RequestMapping(value="/board", method=RequestMethod.POST)
	public String boardList(Model model, AdminBoardDTO adminBoardDTO) throws Exception{
		System.out.println(adminBoardDTO + "@@@@@@@");
		model.addAttribute("brandlist", adminProductService.getBrand());
		model.addAttribute("selectedBname", adminBoardDTO.getBrand());
		model.addAttribute("selectedBheader", adminBoardDTO.getBheader());
		model.addAttribute("selectedBtitle", adminBoardDTO.getBtitle());
		model.addAttribute("selectedBauthor", adminBoardDTO.getBauthor());
		
		List<AdminBoardDTO> boardlist = adminBoardService.getBoardList(adminBoardDTO);
		model.addAttribute("boardlist", boardlist);
		System.out.println(boardlist);
		return "admin/board";
	}
	
	@RequestMapping(value="/deleteBoard", method=RequestMethod.POST)
	public ResponseEntity<String> deleteBoard(@RequestBody AdminBoardDTO adminBoardDTO) throws Exception{
		System.out.println(adminBoardDTO + "!!!");
		adminBoardService.deleteBoard(adminBoardDTO);
		return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
	}
	
	@RequestMapping(value="/reply", method=RequestMethod.GET)
	public String reply() throws Exception{
		
		return "admin/reply";
	}
	@RequestMapping(value="/reply", method=RequestMethod.POST)
	public String replyList(Model model, AdminReplyDTO adminReplyDTO) throws Exception {
		model.addAttribute("selectedRauthor", adminReplyDTO.getRauthor());
		model.addAttribute("selectedRcontent", adminReplyDTO.getRcontent());
		
		List<AdminReplyDTO> replylist = adminBoardService.getReplyList(adminReplyDTO);
		model.addAttribute("replylist", replylist);		
		return "admin/reply";
	}
	
	@RequestMapping(value="deleteReply", method=RequestMethod.POST)
	public ResponseEntity<String> deleteReply(@RequestBody List<AdminReplyDTO> adminReplyDTOList) throws Exception{
		adminBoardService.deleteReply(adminReplyDTOList);
		
		return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
	}

	@RequestMapping(value="/utilities-animation", method=RequestMethod.GET)
	public String utilitiesAnimation() {
		return "admin//utilities-animation";
	}
	@RequestMapping(value="/utilities-border", method=RequestMethod.GET)
	public String utilitiesBorder() {
		return "admin/utilities-border";
	}
	@RequestMapping(value="/utilities-color", method=RequestMethod.GET)
	public String utilitiesColor() {
		return "admin/utilities-color";
	}
	@RequestMapping(value="/utilities-other", method=RequestMethod.GET)
	public String utilitiesOther() {
		return "admin/utilities-other";
	}
	@RequestMapping(value="/drawProduct", method=RequestMethod.GET)
	public String drawProduct(Model model) throws Exception{
		List<DrawListDTO> drawListDTO = drawMapper.getDrawListForAdmin();
		
		model.addAttribute("drawList", drawListDTO);
		
		return "admin/drawProduct";
	}
	@RequestMapping(value="/winDrawList", method=RequestMethod.GET)
	public String winDrawList(Model model) throws Exception{	
		
		List<DrawWinDTO> winList = drawMapper.getWinList();
		
		model.addAttribute("list", winList);
		
		return "admin/winDrawList";
	}
}