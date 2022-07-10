package com.hyundai.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hyundai.project.dto.AdminBoardDTO;
import com.hyundai.project.dto.AdminReplyDTO;

@Service
public interface AdminBoardService {
	public List<AdminBoardDTO> getBoardList(AdminBoardDTO adminBoardDTO) throws Exception;

	public void deleteBoard(AdminBoardDTO adminBoardDTO);
	
	public List<AdminReplyDTO> getReplyList(AdminReplyDTO adminReplyDTO) throws Exception;

	public void deleteReply(List<AdminReplyDTO> adminReplyDTOList);
}
