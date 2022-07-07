package com.hyundai.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.project.board.repository.BoardMapper;
import com.hyundai.project.dto.BoardDTO;
import com.hyundai.project.dto.ReplyDTO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardMapper boardMapper;

	
	@Override
	public BoardDTO getBoard(String bno) {
		// TODO Auto-generated method stub
		return boardMapper.getBoard(bno);
	}

	@Override
	public List<BoardDTO> getBoardList(String brand) {
		return boardMapper.getBoardList(brand);
	}

	@Override
	public void insertBoard(BoardDTO boardDTO) {
		boardMapper.insertBoard(boardDTO);
		return;
	}
	
	
	
	@Override
	public void delBoard(String bid) {
		boardMapper.delBoard(bid);
		return;
	}

	@Override
	public void enrollReply(ReplyDTO replyDTO) {
		boardMapper.enrollReply(replyDTO);
		return;
	}

	@Override
	public List<ReplyDTO> getReplyList(String bid) {
		// TODO Auto-generated method stub
		return boardMapper.getReplyList(bid);
	}

	@Override
	public void delReply(String rno) {
		boardMapper.delReply(rno);
		return;
	}
	
	
	
	
}
