package com.hyundai.project.user.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hyundai.project.board.repository.BoardMapper;

@SpringBootTest
public class BoardMapperTest {
	@Autowired
	private BoardMapper boardMapper;
	
	@Test
	public void test() {
		System.out.println(boardMapper.showMileage("seokjune96@naver.com"));
	}

}
