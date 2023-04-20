package com.tukorea.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tukorea.board.dto.BoardForm;
import com.tukorea.board.service.BoardService;

@SpringBootTest
class BoardApplicationTests {
	@Autowired
	BoardService service;

	@Test
	void testAddBoard() {
		for (int i = 0; i < 82; i++) {
			BoardForm form = new BoardForm();
			form.setTitle("junit으로 넣은 페이징 테스트용 게시글" + (i + 1));
			form.setWriter("junit");
			form.setPassword("1111");
			form.setContents("junit으로 넣은 페이징 테스트용 게시글 내용입니다" + (i + 1));

			service.addBoard(form);
		}

	}

}
