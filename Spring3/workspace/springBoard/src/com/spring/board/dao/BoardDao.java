package com.spring.board.dao;

import java.util.List;
import java.util.Map;

import com.spring.board.vo.BoardVo;
import com.spring.board.vo.CommandVo;
import com.spring.board.vo.PageVo;

public interface BoardDao {

	public String selectTest() throws Exception;

	public List<BoardVo> selectBoardList(Map<String, Object> streamData) throws Exception;

	public BoardVo selectBoard(BoardVo boardVo) throws Exception;

	public int selectBoardCnt(Map<String, Object> streamData) throws Exception;

	public int boardInsert(BoardVo boardVo) throws Exception;
	
	public int update(BoardVo boardVo) throws Exception;
	
	public int delete(BoardVo boardVo) throws Exception;
	
	public List<CommandVo> selectCommandList(CommandVo commandVo) throws Exception;
	
}
