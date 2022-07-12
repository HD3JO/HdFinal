package com.hyundai.project.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyundai.project.dto.DrawListDTO;
import com.hyundai.project.dto.MemberDTO;
import com.hyundai.project.product.repository.DrawMapper;
import com.hyundai.project.user.repository.MemberMapper;


@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberMapper mapper2;
	
	@Autowired
	DrawMapper drawMapper;
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@Override
	public List<MemberDTO> selectAllUser() throws Exception{
		System.out.println("Product Service start.....................");
		MemberDTO memberDTO = new MemberDTO();
		
		return mapper2.getUser(memberDTO);
	}
	@Override
	public int insertMember(MemberDTO memberDTO) throws Exception{
		 return mapper2.insertMember(memberDTO);
	}
	@Override
	public MemberDTO selectOneUser(String email) throws Exception {
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setEmail(email);
		List<MemberDTO> memberList = mapper2.getUser(memberDTO);
		if(memberList.size() == 0) {
			return null;
		}else {
			return memberList.get(0);
		}
	}
	@Override
	public MemberDTO updateMember(MemberDTO memberDTO) throws Exception{
		System.out.println(memberDTO.getEmail());
		MemberDTO vo = new MemberDTO();
		vo.setEmail(memberDTO.getEmail());
		MemberDTO updateUser =mapper2.getUser(vo).get(0);
		
		updateUser.setName(memberDTO.getName());
		updateUser.setPhone(memberDTO.getPhone());
		System.out.println(memberDTO + "here2");
		if(!"".equals(memberDTO.getPassword())) {
			updateUser.setPassword(memberDTO.getPassword());
		}
		updateUser.setMarketingemail(memberDTO.getMarketingemail());
		updateUser.setMarketingsms(memberDTO.getMarketingsms());
		
		return mapper2.updateMember(updateUser);
	}
	
	@Override
	public List<MemberDTO> selectUserAdmin(MemberDTO memberDTO) throws Exception {
		// TODO Auto-generated method stub
		return mapper2.selectUserAdmin(memberDTO);
	}
	@Override
	public void updateMemeberAdmin(List<MemberDTO> memberList) throws Exception {
		// TODO Auto-generated method stub
		for(MemberDTO dto : memberList) {
			String email = dto.getEmail();
			
			MemberDTO updateMember = new MemberDTO();
			updateMember.setEmail(email);
			updateMember = mapper2.getUser(updateMember).get(0);
			
			updateMember.updateMember(dto);
			
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@");
			System.out.println(updateMember);
			mapper2.updateMember(updateMember);
		}
		
	}
	
	@Override
	public void secession(String email) {
		MemberDTO dto = new MemberDTO();
		dto.setEmail(email);
		mapper2.secession(dto);
		
	}
	
	@Override
	public List<DrawListDTO> getMyDrawList(String email) throws Exception {
		
		DrawListDTO myDraw =  new DrawListDTO();
		
		ObjectMapper mapper = new ObjectMapper();
		List<DrawListDTO> myDrawList = new ArrayList<DrawListDTO>(); 
		List<DrawListDTO> dto = drawMapper.getDrawList();
		
		Set<String> redisKeys = redisTemplate.keys("*");
		List<String> keysList = new ArrayList<>();
		List<String> keysListByEmail = new ArrayList<>();
		Iterator<String> keys = redisKeys.iterator();
		
		// redis에 있는 모든 키 값들 리스트 형태로 받아옴
		while(keys.hasNext()) {
			String data = keys.next();
			keysList.add(data);
		}

		
		// 해당 사용자의 이메일에 해당하는 응모 내역 키 값 가져오기 
		for(int i = 0; i < keysList.size(); i++) {														 
			 Object val = redisTemplate.opsForValue().get(String.valueOf(keysList.get(i)));
			 Map<String, Object> map = mapper.convertValue(val, Map.class);					 
			 String emailDB = (String)map.get("email");
			 
			 if(email.equals(emailDB)) { 
				 keysListByEmail.add((String)keysList.get(i));
			 }						 					 					 							
		}
		
		for(int k = 0; k < keysListByEmail.size(); k++) {
			Object val = redisTemplate.opsForValue().get(String.valueOf(keysListByEmail.get(k)));
			Map<String, Object> map = mapper.convertValue(val, Map.class);			
			
			myDraw.setEmail(email);
			myDraw.setPsid((String)map.get("psid"));
			myDraw.setPmileage((Integer)map.get("pmileage"));
			
			DrawListDTO info = drawMapper.getDrawDetail((String)map.get("psid"));
			
			myDraw.setBname(info.getBname());
			myDraw.setPname(info.getPname());
			myDraw.setPmimg2(info.getPmimg2());
			myDraw.setPcolorcode(info.getPcolorcode());
			myDraw.setPsize(info.getPsize());
			
			myDrawList.add(myDraw);
			myDraw = new DrawListDTO();	
		}
		
		return myDrawList;
	}
}
