package com.hyundai.project.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hyundai.project.dto.BCountDTO;
import com.hyundai.project.dto.BoardDTO;
import com.hyundai.project.dto.ReplyDTO;


public interface BoardService {
	public List<BoardDTO> getBoardList(String brand);

	public void insertBoard(BoardDTO boardDTO);

	public BoardDTO getBoard(String bno);

	public void enrollReply(ReplyDTO replyDTO);

	public List<ReplyDTO> getReplyList(String bid);

	public void delReply(String rno);

	public void delBoard(String bid);

	public int getCount(String bname);

	public List<BoardDTO> getPageBoard(BoardDTO boardDTO);

	public void incHits(BCountDTO bCountDTO) throws SQLException;


}
