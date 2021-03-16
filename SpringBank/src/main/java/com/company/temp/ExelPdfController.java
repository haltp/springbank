package com.company.temp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.common.SaleVO;
import com.company.temp.service.impl.EmpMapper;

@Controller
public class ExelPdfController {

	@Autowired
	EmpMapper empMapper;

	@RequestMapping("/getEmpExcel")
	public String getEmpExcel(Model model) {
		List<Map<String, Object>> list = empMapper.getEmpList(); // 한번 출력해보기
		System.out.println(list.get(0));
		model.addAttribute("filename", "empList");
		model.addAttribute("headers", new String[] { "firstName", "lastName", "salary", "email" });
		model.addAttribute("datas", empMapper.getEmpList());
		return "commonExcelView"; // CommonExcelView이러면 jsp 찾아간다 반드시 소문자 시작
	}

	@RequestMapping("/pdf/empList")
	public String getPdfEmpList(Model model) {
		model.addAttribute("filename", "/reports/empList.jasper");
		return "pdfView";
	}

	@RequestMapping("/pdf/empList2")
	public String getPdfEmpList2(Model model, @RequestParam String dept) {
		// 파라미터 맵
		HashMap<String, Object> map = new HashMap<>();
		map.put("P_DEPARTMENT_ID", dept);

		model.addAttribute("filename", "/reprots/empList2.jasper");
		model.addAttribute("filename", "/reprots/empList2.jasper");
		return "pdfView";
	}

//	@RequestMapping("/pdf/empList3")
//	public void getPdfEmpList3(HttpServletRequest response) throws Exception {
//		Connection conn = ConnectionManager.getConnnect();
//		//소스파일 컴파일하여 저장 : compileReportToFile
//		 String jrxmlFile = getClass().getResource("/empmaster.jrxml").getFile()
//		jasperFile = JasperCompileManager.compileReportToFile( jrxmlFile );
//		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperFile, null, conn);
//		JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream())
//	}

	@RequestMapping("/getChartData")
	@ResponseBody
	public List<Map<String, Object>> getChartData(SaleVO vo) {
		return empMapper.getDaySum(vo);
	}

}
