package com.company.temp;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.common.BankAPI;

@Controller
public class BankController {
	
	@Autowired BankAPI bankAPI;
	
	@RequestMapping("/auth")
	   public String auth() throws Exception {
	      String reqURL = "https://testapi.openbanking.or.kr/oauth/2.0/authorize_account";
	      String response_type = "code";
	      String client_id = "6ff5b4c8-02a0-4eee-a6d8-b4e8e25f34e5";
	      String redirect_uri = "http://localhost:86/temp/callback";
	      String scope = "login inquiry transfer";
	      String state = "01234567890123456789012345678912";
	      String auth_type = "0";
	      
	      StringBuilder qstr = new StringBuilder();
	      qstr.append("response_type=" + response_type)
	         .append("&client_id=" + client_id)
	         .append("&redirect_uri=" + redirect_uri)
	         .append("&scope=" + scope)
	         .append("&state=" + state)
	         .append("&auth_type=" + auth_type);
	      
	      return "redirect:" + reqURL + "?" + qstr.toString();
	   }
	
	@RequestMapping("/callback")
	public String callback(@RequestParam Map<String, Object> map) {
		System.out.println(map.get("code"));
		String code = (String)map.get("code");
		//access_token 받기
		String access_token = bankAPI.getAccessToken(code);
		System.out.println("access_token=" + access_token);
		return "home";
	}
	
	@RequestMapping("/userinfo")
	public String userinfo(HttpServletRequest request) {
		String access_token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiIxMTAwNzcwNTIxIiwic2NvcGUiOlsiaW5xdWlyeSIsImxvZ2luIiwidHJhbnNmZXIiXSwiaXNzIjoiaHR0cHM6Ly93d3cub3BlbmJhbmtpbmcub3Iua3IiLCJleHAiOjE2MjMxMzc5MDEsImp0aSI6IjFjMDBhYTFjLWQzMjgtNDZkZC1iYmQyLTdkMDFjYTkzNzA2OSJ9.RmkYC1HpuDlTAK-49cZ2edk9ERaBjqmTcaCauGGw8yQ";
		String use_num = "1100770521";
		Map<String, Object> userinfo = bankAPI.getUserInfo(access_token, use_num);
		System.out.println("userinfo=" + userinfo);
		return "home";
	}
	
}
