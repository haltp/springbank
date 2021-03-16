package com.company.board.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.company.board.service.BoardsVO;
import com.company.common.FileRenamePolicy;
import com.company.temp.service.impl.BoardMapper;

@Controller
public class BoardsController {
	
	@Autowired BoardMapper boardMapper;

	@GetMapping("/insertBoard")
	public String insertBoardForm(BoardsVO vo) {
		return "board/insertBoard";
	}

	@PostMapping("/insertBoard")
	public String insertBoard(BoardsVO vo) throws Exception {
		System.out.println(vo);
		//첨부파일처리
		MultipartFile[] files = vo.getUploadFile();
		String filenames = "";
		boolean start = true;
		for(MultipartFile file : files) {
		if(file != null && ! file.isEmpty()&& file.getSize()>0) {
			String filename = file.getOriginalFilename();
			//파일명 중복체크 -> rename
			File rename = FileRenamePolicy.rename(new File("c:/upload", filename)); //파일 있는지 검사 파일있으면 새로운 이름 만들 예정
			//처음 저장할 때 쉼표 빼기
			if(start) {
				start = false;
				filename += ",";
			}else {
				start = false;
			}
			//여러건 저장할 때 중간에 쉼표 붙이기
			filenames +=rename.getName()+",";
			//임시폴더에서 업로드 폴더로 파일 이동
			file.transferTo(rename);
			}
		}
		vo.setFilename(filenames); //vo 업로드 된 파일명 담아서 DB저장
		
		//등록 서비스 호촐
		boardMapper.insertBoard(vo);
		return "redirect:/getBoard?seq="+vo.getSeq();
	}
	
	//단건조회
	@GetMapping("/getBoard")
	public String getBoard(BoardsVO vo, Model model ) {
		model.addAttribute("board", boardMapper.getBoard(vo));
		return "board/getBoard";
		
	}
	
	@RequestMapping("/fileDown")
	public void fileDown(BoardsVO vo, HttpServletResponse response) throws IOException{
		vo = boardMapper.getBoard(vo);
		File file = new File("c:/upload", vo.getFilename());
		if(file.exists()) {
			response.setContentType("application/octet-stream;charset=UTF-8");
			 response.setHeader("Content-Disposition", "attachment;filename=\"" + URLEncoder.encode(vo.getFilename(), "utf-8") + "\"");
			 
			 BufferedInputStream in = null;
			 BufferedOutputStream out = null;
			 try {
				 
				 in = new BufferedInputStream(new FileInputStream(file));
				 out = new BufferedOutputStream(response.getOutputStream());
				 FileCopyUtils.copy(in, out);
				 out.flush();
			 } catch (IOException ex) {
			 } finally {
				 in.close(); 
				 response.getOutputStream().flush(); 
				 response.getOutputStream().close();
			 }
		} else {
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter()
					.append("<script>")
					.append("alert('파일 없음')")
					.append("history,go(-1);")	//이전페이지 이동
					.append("</script>");
			}
	}
	
	@RequestMapping("/fileNameDown")
	public void fileNameDown(BoardsVO vo, HttpServletResponse response) throws IOException{
		File file = new File("c:/upload", vo.getFilename());
		if(file.exists()) {
			response.setContentType("application/octet-stream;charset=UTF-8");
			 response.setHeader("Content-Disposition", "attachment;filename=\"" + URLEncoder.encode(vo.getFilename(), "utf-8") + "\"");
			 
			 BufferedInputStream in = null;
			 BufferedOutputStream out = null;
			 try {
				 
				 in = new BufferedInputStream(new FileInputStream(file));
				 out = new BufferedOutputStream(response.getOutputStream());
				 FileCopyUtils.copy(in, out);
				 out.flush();
			 } catch (IOException ex) {
			 } finally {
				 in.close(); 
				 response.getOutputStream().flush(); 
				 response.getOutputStream().close();
			 }
		} else {
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter()
					.append("<script>")
					.append("alert('파일 없음')")
					.append("history,go(-1);")	//이전페이지 이동
					.append("</script>");
			}
	}
	
	
	

}
