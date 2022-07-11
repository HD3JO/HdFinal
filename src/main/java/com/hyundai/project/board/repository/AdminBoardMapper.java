package com.hyundai.project.board.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyundai.project.dto.AdminBoardDTO;
import com.hyundai.project.dto.AdminReplyDTO;

@Mapper
public interface AdminBoardMapper {
	public List<AdminBoardDTO> getBoardList(AdminBoardDTO adminBoardDTO) throws Exception;

	public void deleteBoard(AdminBoardDTO adminBoardDTO);
	
	public List<AdminReplyDTO> getReplyList(AdminReplyDTO adminReplyDTO) throws Exception;

	public void deleteReply(AdminReplyDTO dto);
	
	public int getBoardCntByDay(AdminBoardDTO adminBoardDTO) throws Exception;
}

