package com.tukorea.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.tukorea.board.domain.Board;

@Mapper
public interface BoardMapper {
	//게시판 글 등록
	public int insertBoard(Board board);
	//게시판 총 건수 조회
	public int selectBoardListTotalCount();
	//게시판 목록 조회
	public List<Board> selectBoardList(Map<String, Object> paramMap);
	//게시판 상세 정보 조회
	public Board selectBoardInfo(int boardSeq);
	//게시물 조회수 증가
	public int updateBoardHits(int boardSeq);
	//게시물 글 소유자 확인
	public int selectBoardPasswordForCheck(Map<String, Object> paramMap);
	//게시물 글 삭제
	public int deleteBoard(int boardSeq);
	//게시물 글 수정
	public int updateBoard(Board board);
}
