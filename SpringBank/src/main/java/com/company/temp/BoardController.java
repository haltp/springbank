package com.company.temp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.temp.service.BoardVO;

@Controller
public class BoardController {
	
	//파라미터를 VO에 담고 결과페이지에서 titile, writer를 출력하는 핸들러
	@RequestMapping("/insertBoard")
	public String getTest10(BoardVO vo) {
		return "insertBoardResult";
	}
	
	//파라미터를 VO에 담고 vo 응답하는 핸들러
	@RequestMapping("/ajaxInsertBoard")
	@ResponseBody
	public BoardVO ajaxInsertBoard(BoardVO vo) {
		return vo;
	}
	
}
