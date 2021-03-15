package com.company.temp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.company.temp.service.testVO;

@Controller
public class testController {

	// get, post ,커맨드객체(VO)
	@PostMapping("/getTest1") // get post구분없다면 RequestMapping 가능
	public String getTest1(testVO vo) {
		System.out.println(vo); // vo에 값이 들어오는지 확인
		return "/"; // 리턴 홈으로
	}

	// 파라미터 request.getParameter()
	@RequestMapping("/getTest2") // vo이름과 같게 써야한다
	public String getTest2(@RequestParam String firstName, @RequestParam int salary) {
		System.out.println(firstName + ":" + salary);
		return "/";
	}

//	// 배열 request.getParameterValues()
//	@RequestMapping("/getTest3")
//	public String getTest3(@RequestParam String[] hobby) {
//		System.out.println(hobby[0] + "," + hobby[1]);
//		return "/";
//	}

//	// 배열 request.getParameterValues()
//	@RequestMapping("/getTest3")
//	public String getTest3(@RequestParam String[] hobby) {
//		System.out.println(Arrays.asList(hobby));
//		return "/";
//	}

	// 배열 request.getParameterValues()
	@RequestMapping("/getTest3")
	public String getTest3(@RequestParam("hobby") String[] hobbies) {
		System.out.println(Arrays.asList(hobbies));
		return "/";
	}

//	// JSON
//	@RequestMapping("/getTest4")
//	public String getTest4(@RequestBody testVO vo) {
//		System.out.println(vo);
//		return "/";
//	}

//	// JSON 배열
//	@RequestMapping("/getTest4")
//	public String getTest4(@RequestBody List<testVO> vo) {
//		System.out.println(vo);
//		return "/";
//	}

	// JSON 배열
	@RequestMapping("/getTest4")
	public String getTest4(@RequestBody List<Map> vo) {
		System.out.println(vo);
		return "/";
	}

//	// @PathVariable
//	@RequestMapping("/getTest5/{firstName}")
//	public String getTest5(@PathVariable String firstName) {
//		System.out.println(firstName);
//		return "/";
//	}

//	// @PathVariable 값두개
//	@RequestMapping("/getTest5/{firstName}/{salary}")
//	public String getTest5(@PathVariable String firstName, @PathVariable int salary) {
//		System.out.println(firstName + "," + salary);
//		return "/";
//	}

//	// @PathVariable 값두개 vo에 담을 때
//	@RequestMapping("/getTest5/{firstName}/{salary}")
//	public String getTest5(@PathVariable String firstName, @PathVariable int salary, testVO vo) {
//		vo.setFirstName(firstName);
//		vo.setSalary(salary);
//		System.out.println(vo);
//		return "/";
//	}

	// @PathVariable 값두개 vo에 담을 때, Model로 담기 ,test뷰페이지로 이동
	@RequestMapping("/getTest5/{firstName}/{salary}")
	public String getTest5(@PathVariable String firstName, @PathVariable int salary, testVO vo, Model model) {
		vo.setFirstName(firstName);
		vo.setSalary(salary);
		System.out.println(vo);
		model.addAttribute("firstName", firstName);
		return "test";
	}

	// @PathVariable testVO를 ttvo로 이름변경하고플때
	@RequestMapping("/getTest6/{firstName}/{salary}")
	public String getTest6(@PathVariable String firstName, 
							@PathVariable int salary, 
							@ModelAttribute("ttvo")testVO vo, 
							Model model) {
		vo.setFirstName(firstName);
		vo.setSalary(salary);
		System.out.println(vo);
		model.addAttribute("firstName", firstName);
		return "test";
	}
	
	//ModelAndView
	@RequestMapping("/getTest7/{firstName}/{salary}")
	public ModelAndView getTest7(@PathVariable String firstName, 
							@PathVariable int salary, 
							@ModelAttribute("ttvo")testVO vo, 
							Model model) {
		vo.setFirstName(firstName);
		vo.setSalary(salary);
		ModelAndView mv = new ModelAndView();
		mv.addObject("firstName", firstName);
		mv.setViewName("test");
		return mv;
	}
	
	//응답결과 json
	//@ResponseBody밑에 말고 여기적어도 됨
	@RequestMapping("/getTest8/{firstName}/{salary}")
	public @ResponseBody testVO getTest8(@PathVariable String firstName, 
							@PathVariable int salary, 
							testVO vo ) {
		vo.setFirstName(firstName);
		vo.setSalary(salary);
		return vo;
	}
	//응답결과 json
	//@ResponseBody밑에 말고 여기적어도 됨
	@RequestMapping("/getTest9")
	public @ResponseBody List<Map> getTest9() {
		List list = new ArrayList<>();
		Map<String, String> map = new HashMap<>();
		map.put("name", "park");
		map.put("sal", "1000");
		list.add(map);
			
		map = new HashMap<>();
		map.put("name", "kim");
		map.put("sal", "2000");
		list.add(map);
		
		return list;
		}
	
	
	
	
	
	

}
