package com.hyundai.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.project.board.repository.AdminBoardMapper;
import com.hyundai.project.dto.AdminBoardDTO;
import com.hyundai.project.dto.AdminOrderDTO;
import com.hyundai.project.dto.AdminReplyDTO;

@Service
public class AdminBoardServiceImpl implements AdminBoardService {

	@Autowired
	private AdminBoardMapper adminBoardMapper;
	
	@Override
	public List<AdminBoardDTO> getBoardList(AdminBoardDTO adminBoardDTO) throws Exception {
		// TODO Auto-generated method stub
		return adminBoardMapper.getBoardList(adminBoardDTO);
	}

	@Override
	public void deleteBoard(AdminBoardDTO adminBoardDTO) {
		// TODO Auto-generated method stub
		
		adminBoardMapper.deleteBoard(adminBoardDTO);
	}

	@Override
	public List<AdminReplyDTO> getReplyList(AdminReplyDTO adminReplyDTO) throws Exception {
		// TODO Auto-generated method stub
		return adminBoardMapper.getReplyList(adminReplyDTO);
	}

	@Override
	public void deleteReply(List<AdminReplyDTO> adminReplyDTOList) {
		// TODO Auto-generated method stub
		for(AdminReplyDTO dto : adminReplyDTOList) {
			adminBoardMapper.deleteReply(dto);
		}
		
	}

}
