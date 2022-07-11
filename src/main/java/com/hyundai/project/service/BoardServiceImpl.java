package com.hyundai.project.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.project.board.repository.BoardMapper;
import com.hyundai.project.dto.BCountDTO;
import com.hyundai.project.dto.BoardDTO;
import com.hyundai.project.dto.ReplyDTO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardMapper boardMapper;
	
	
	
	@Override
	public int getCount(String bname) {
		return boardMapper.getCount(bname);
	}



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
	public List<BoardDTO> getPageBoard(BoardDTO boardDTO) {
		// TODO Auto-generated method stub
		return boardMapper.getPageBoard(boardDTO);
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



	@Override
	public void incHits(BCountDTO bCountDTO) throws SQLException {
		boardMapper.incHits(bCountDTO);
		
	}

	@Override
	public void incLikes(BCountDTO bCountDTO) throws SQLException {
		boardMapper.incLikes(bCountDTO);
		return;
	}

	@Override
	public int getLikesCount(String bid) {
		
		return boardMapper.getLikesCount(bid);
	}



	@Override
	public int getTodayBoardCnt(String bauthor) {
		
		return boardMapper.getTodayBoardCnt(bauthor);
	}
	
	@Override
	public int getTodayReplayCnt(String rauthor) {
		return boardMapper.getTodayReplyCnt(rauthor);
	}

	@Override
	public void getMileage(int amount, String bauthor) {
		boardMapper.getMileage(amount, bauthor);
		return;
	}
	
	

}
