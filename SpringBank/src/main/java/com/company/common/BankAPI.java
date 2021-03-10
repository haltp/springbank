package com.company.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class BankAPI {
	
	public String getAccessToken (String authorize_code) {
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://testapi.openbanking.or.kr/oauth/2.0/token";
        
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            //    POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            
            //    POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("code=BRUpOhGEmNaAgR4nJkAMvVOQD5oZG5&scope=inquiry");
            sb.append("&client_id=6ff5b4c8-02a0-4eee-a6d8-b4e8e25f34e5");
            sb.append("&client_secret=5e2b1b38-5e81-40ba-b82c-251e9e105e4c");
            sb.append("&redirect_uri=http://localhost:86/temp/callback");
            sb.append("&grant_type=authorization_code");
            bw.write(sb.toString());
            bw.flush();
            
            //    결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);
 
            //    요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";
            
            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);
            
            //    Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);
            
            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
            
            System.out.println("access_token : " + access_Token);
            System.out.println("refresh_token : " + refresh_Token);
            
            br.close();
            bw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        
        return access_Token;
    }
	
	public HashMap<String, Object> getUserInfo (String access_Token, String use_num) {
	   
	    HashMap<String, Object> userInfo = new HashMap<>();
	    String reqURL = "https://testapi.openbanking.or.kr/v2.0/account/list";
	    StringBuilder qstr = new StringBuilder();
	      qstr.append("user_seq_no=" + use_num)
	          .append("&include_cancel_yn=" + "Y")
	          .append("&sort_order=" + "D");
	    try {
	        URL url = new URL(reqURL + "?" + qstr);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        
	        // 요청에 필요한 Header에 포함될 내용
	        conn.setRequestProperty("Authorization", "Bearer " + access_Token);
	        // 출력되는 값이 200이면 정상작동
	        int responseCode = conn.getResponseCode();
	        System.out.println("responseCode : " + responseCode);
	        
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        
	        String line = "";
	        String result = "";
	        
	        while ((line = br.readLine()) != null) {
	            result += line;
	        }
	        System.out.println("response body : " + result);
	        
	        JsonParser parser = new JsonParser();
	        JsonElement element = parser.parse(result);
	        
	        String id = element.getAsJsonObject().get("id").toString();
	        JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
	        JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
			
	        String nickname = properties.getAsJsonObject().get("nickname").getAsString();
	        
	        System.out.println("kakao id : " + id);
	        if(kakao_account.get("has_email").getAsBoolean() && kakao_account.get("email") != null) {
	        	String email = kakao_account.get("email").getAsString();
	        	userInfo.put("email", email);
	        }
	        userInfo.put("nickname", nickname);
	        userInfo.put("kakaoId", id);
	        //userInfo.put("email", email);
	        
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    
	    return userInfo;
	}
}
