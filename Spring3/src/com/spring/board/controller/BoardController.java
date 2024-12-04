package com.spring.board.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.board.HomeController;
import com.spring.board.service.boardService;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.CommandVo;
import com.spring.board.vo.PageVo;
import com.spring.common.CommonUtil;

@Controller
public class BoardController {
	
	@Autowired 
	boardService boardService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	@RequestMapping(value = "/board/boardList.do", method = RequestMethod.GET)
	public String boardList(Locale locale, Model model, PageVo pageVo) throws Exception{
		List<BoardVo> boardList = new ArrayList<BoardVo>();
		List<CommandVo> commandList = new ArrayList<CommandVo>();
		CommandVo commandVo = new CommandVo();
		HashMap<String, Object> result = new HashMap<String, Object>();
	
		int page = 1; 
		int totalCnt = 0;
		
		if(pageVo.getPageNo() == 0){
			pageVo.setPageNo(page);
		}
		
		result.put("PageVo", pageVo);
		
		boardList = boardService.selectBoardList(result);
		commandList = boardService.selectCommandNameList(commandVo);
		totalCnt = boardService.selectBoardCnt(result);
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("commandList", commandList);
		model.addAttribute("pageNo", page);
		
		return "board/boardList";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/board/searchBoardList.do", produces = "application/json; charset=UTF-8", method = RequestMethod.POST)
	public String searchBoardList(@RequestBody Map<String, Object> boardType) throws Exception{
		List<BoardVo> boardList = new ArrayList<BoardVo>();
		HashMap<String, Object> result = new HashMap<String, Object>();
		CommonUtil commonUtil = new CommonUtil();
		PageVo pageVo = new PageVo();
		List<CommandVo> commandList = new ArrayList<CommandVo>();
		CommandVo commandVo = new CommandVo();
		
		int page = 1; 
		int totalCnt = 0;
		
		if(pageVo.getPageNo() == 0){
			pageVo.setPageNo(page);
		}
		
		result.put("CodeId", boardType.get("CodeId"));
		result.put("PageVo", pageVo);
		
		boardList = boardService.selectBoardList(result);
		totalCnt = boardService.selectBoardCnt(result);
		commandList = boardService.selectCommandNameList(commandVo);
		
		result.put("boardList", boardList);
		result.put("totalCnt", totalCnt);
		result.put("commandList", commandList);	
		
		String resultData = commonUtil.getJsonCallBackString(" ", result);
		
		System.out.println(resultData);
		return resultData;
	}
	
	
	@RequestMapping(value = "/board/{boardType}/{boardNum}/boardView.do", method = RequestMethod.GET)
	public String boardView(Locale locale, Model model
			,@PathVariable("boardType")String boardType
			,@PathVariable("boardNum")int boardNum) throws Exception{
			
		BoardVo boardVo = new BoardVo();
		boardVo = boardService.selectBoard(boardType,boardNum);
		
		model.addAttribute("boardType", boardType);
		model.addAttribute("boardNum", boardNum);
		model.addAttribute("board", boardVo);
		
		return "board/boardView";
	}
	
	@RequestMapping(value = "/board/boardWrite.do", method = RequestMethod.GET)
	public String boardWrite(Locale locale, Model model) throws Exception{
		CommandVo commandVo = new CommandVo();

		List<CommandVo> commandList = boardService.selectCommandNameList(commandVo);
		
		model.addAttribute("commandList", commandList);
		return "board/boardWrite";
	}
	
	@RequestMapping(value = "/board/boardWriteAction.do", method = RequestMethod.POST)
	@ResponseBody
	public String boardWriteAction(Locale locale, BoardVo boardVo) throws Exception{
		
		//String page = "1";
		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();
		
		int resultCnt = boardService.boardInsert(boardVo);
		
		result.put("success", (resultCnt > 0)?"Y":"N");
		result.put("pageNo", "1");
		
		String callbackMsg = commonUtil.getJsonCallBackString(" ",result);
		
		System.out.println("callbackMsg::"+callbackMsg);
		
		return callbackMsg;
	}
	
	
	@RequestMapping(value = "/board/{boardType}/{boardNum}/boardUpdate.do", method = RequestMethod.GET)
	public String boardUpdate(Model model, BoardVo boardVo 
			,@PathVariable("boardType")String boardType
			,@PathVariable("boardNum")int boardNum) throws Exception{
		
		boardVo = boardService.selectBoard(boardType, boardNum);
		
		model.addAttribute("boardType", boardType);
		model.addAttribute("boardNum", boardNum);
		model.addAttribute("board", boardVo);
		model.addAttribute("flag", 1);
		
		return "board/boardWrite";
	}
	
	
	@RequestMapping(value = "/board/{boardType}/{boardNum}/boardUpdateAction.do", method = RequestMethod.POST)
	@ResponseBody
	public String boardUpdateAction(Model model, BoardVo boardVo
			,@PathVariable("boardType")String boardType
			,@PathVariable("boardNum")int boardNum) throws Exception{
		
		//String page = "1";
		
		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();
		int resultCnt = boardService.boardUpdate(boardVo);
		
		String boardNumToString = Integer.toString(boardNum);
		result.put("success", (resultCnt > 0)?"Y":"N");
		result.put("boardType", boardType);
		result.put("boardNum", boardNumToString);
		result.put("pageNo", "1");
		
		String callbackMsg = commonUtil.getJsonCallBackString(" ", result);
		
		System.out.println("callbackMsg::"+callbackMsg);
		return callbackMsg;
	}


	
	@RequestMapping(value = "/board/{boardType}/{boardNum}/boardDeleteAction.do", method = RequestMethod.POST)
	@ResponseBody
	public String boardDeleteAction(Model model, BoardVo boardVo) throws Exception{
		// String page = "1";
		
		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();
		int resultCnt = boardService.boardDelete(boardVo);
		
		result.put("success", (resultCnt > 0)?"Y":"N");
		result.put("pageNo", "1");
		
		String callbackMsg = commonUtil.getJsonCallBackString(" ", result);
		return callbackMsg;
	}
	
}
