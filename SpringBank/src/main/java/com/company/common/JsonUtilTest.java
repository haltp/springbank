package com.company.common;

import java.util.HashMap;
import java.util.Map;

import com.company.board.service.BoardsVO;

public class JsonUtilTest {
	public static void main(String[] args) {
		JsonUtil json = new JsonUtil();
		Map<String, Object> map = new HashMap<>();
		map.put("username", "홍길동");
		map.put("age", "30");
		map.put("dept", "개발");
		String result = json.toJson(map);
		System.out.println(result);	//  {"username" : "홍길동" , "age" : 30, "dept" : "개발"}
		
		BoardsVO vo = new BoardsVO();
		vo.setContent("test");
		vo.setTitle("title");
		vo.setFilename("file");
		result = json.toObjectJson(vo);
		
	}
}
