package com.company.temp.service.impl;

import java.util.List;
import java.util.Map;

import com.company.common.SaleVO;

public interface EmpMapper {
	//단건조회
	public List<Map<String, Object>> getEmpList();	//단건이면 Map만 여러건이라서 List
	
	//일별 판매합계
	public List<Map<String, Object>> getDaySum(SaleVO vo);
	
}
