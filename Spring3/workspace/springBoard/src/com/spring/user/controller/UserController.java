package com.spring.user.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.board.HomeController;
import com.spring.board.service.boardService;
import com.spring.board.vo.CommandVo;
import com.spring.common.CommonUtil;
import com.spring.user.service.UserService;
import com.spring.user.vo.UserVo;
@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	boardService boardService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/user/join.do", method = RequestMethod.GET)
	public String join(Model model) throws Exception{
		
		List<CommandVo> commandList = new ArrayList<CommandVo>();
		CommandVo commandVo = new CommandVo("phone");
	
		commandList = boardService.selectCommandNameList(commandVo);
		model.addAttribute("commandList", commandList);
		
		return "user/joinList";
	}
	
	
	@RequestMapping(value = "/user/checkid.do", produces = "application/json; charset=UTF-8", method = RequestMethod.GET)
	@ResponseBody
	public String checkid(UserVo userVo) throws Exception{
		CommonUtil commonUtil = new CommonUtil();
		Map<String, String> result = new HashMap<String, String>();
		
		int cnt = userService.checkCnt(userVo);
		result.put("data" , Integer.toString(cnt));
		result.put("success", cnt > 0 ? "중복된 아이디입니다." : "생성할 수 있는 아이디입니다.");
		
		String resultData = commonUtil.getJsonCallBackString(" ", result);
		System.out.println(resultData);
		return resultData;
	}
	
	
	
}
