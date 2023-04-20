package com.tukorea.board.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tukorea.board.domain.Board;
import com.tukorea.board.dto.BoardForm;
import com.tukorea.board.dto.BoardModifyForm;
import com.tukorea.board.service.BoardService;

@Controller
public class BoardController {
	//BoardService.java 의존성 주입
	private final BoardService service;
	@Autowired
	public BoardController(BoardService service) {
		this.service = service;
	}
	
	
	//게시판 등록 페이지
	@GetMapping("/board/form")
	public String addBoardForm() {
		return "board/addBoardForm";
	}

	
	
	//게시판 등록 Controller
	@PostMapping("/board/new")
	public String addBoard(BoardForm boardForm) {
		//게시판 등록 메서드 호출
		service.addBoard(boardForm);
		//TODO목록 개발 전까지는 /board/form 으로 redirect 가게 처리
		return "redirect:/board/list";
	}

	
	//게시판 목록 페이지 연결 Controller
	@GetMapping("/board/list")
	public String getBoardList(@RequestParam(required = false, defaultValue = "1") int pageNum, Model model) {
		//게시판 목록 조회 메서드 호출
		Map<String, Object> result = service.getBoardlist(pageNum);
		
		model.addAttribute("PageNum",pageNum);
		//Service 결과 모두 model에 설정
		model.addAllAttributes(result);
		return "board/boardList";
	}
	
	
	//게시판 상세 페이지 연결 Controller
	@GetMapping("/board/detail")
	public String getBoardDetail(@RequestParam int boardSeq, Model model) {
		Board board = service.getBoardDetail(boardSeq);
		model.addAttribute("board",board);
		
		return "board/boardDetail";
		
	}
	
	@ResponseBody
	@PostMapping("/board/checkPassword")
	public boolean checkPassword(@RequestParam int boardSeq, @RequestParam String password, Model model) {
		//게시판 등록 메서드 호출
		boolean result = service.checkBoardOwner(boardSeq, password);
		return result;
	}
	
	@PostMapping("/board/modifyForm")
	public String getBoardModifyForm(@RequestParam int boardSeq, Model model) {
		Board board = service.getBoardDetailForModify(boardSeq);
		model.addAttribute("board",board);
		return "board/modifyBoardForm";
		
	}
	
	//게시물 정보 삭제 Controller Method
	@PostMapping("/board/delete")
	public String deleteBoard(@RequestParam int boardSeq) {
		service.deleteBoard(boardSeq);
		return "redirect:/board/list";
	}
	
	
	//게시물 정보 수정 Controller Method
	@PostMapping("/board/modify")
	public String modifyBoard(BoardModifyForm boardForm) {
		service.updateBoard(boardForm);
		return "redirect:/board/list";
	}
	
	
	
	

}
