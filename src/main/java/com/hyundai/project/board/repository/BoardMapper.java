package com.hyundai.project.board.repository;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyundai.project.dto.BCountDTO;
import com.hyundai.project.dto.BoardDTO;
import com.hyundai.project.dto.ReplyDTO;

@Mapper
public interface BoardMapper {

	List<BoardDTO> getBoardList(String brand);

	void insertBoard(BoardDTO boardDTO);

	BoardDTO getBoard(String bno);

	void enrollReply(ReplyDTO replyDTO);

	List<ReplyDTO> getReplyList(String bid);

	void delReply(String rno);

	void delBoard(String bid);

	int getCount(String bname);

	List<BoardDTO> getPageBoard(BoardDTO boardDTO);

	void incHits(BCountDTO bCountDTO) throws SQLException;

}
