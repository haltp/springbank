package com.company.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JsonUtil {
	
	public String toJson(Map<String, Object> map) {
		StringBuilder result = new StringBuilder();	//그냥 string보다 성능 좋은 StringBuilder
		boolean start = true;
		result.append("{");
		//Iterator : 순서정렬
		Iterator<String> keys = map.keySet().iterator();
		
		while(keys.hasNext()) {
			String key = keys.next();
			String value = (String)map.get(key);
			
			//쉼표 붙이기
			if(! start) {
				result.append(",");
			} else {
				start = false;
			}
			result.append("\""+key+"\""+":"+"\""+value+"\"");
		}
		result.append("}");
		return result.toString();
	}
	
	public String toJson(List<Map<String, Object>> map) {
		StringBuilder result = new StringBuilder();
		//to do
		result.append("[").append("{");
		
		result.append("}").append("]");
		return result.toString();
	}
	
	public String toObjectJson(Object vo) throws NoSuchMethodException{
		String result = "";
		//to do
		Field[] fields = vo.getClass().getDeclaredFields(); //getDeclaredFields의 리턴타입이 Field라고 적혀있음 마우스 올려보삼
		for(Field field : fields) {
			String fieldName = field.getName();
			String method = "get"+field.getName().substring(0, 1).toUpperCase()+field.getName().substring(1);
			Method m = vo.getClass().getDeclaredMethod(method, null);
			String value = (String) m.invoke(vo, null);	//메서드 실행
			System.out.println(fieldName, value);
		}
		return result;
	}
	
	public String toObjectJson(List<Object> vo) {
		String result = "";
		//to do
		return result;
	}
}
