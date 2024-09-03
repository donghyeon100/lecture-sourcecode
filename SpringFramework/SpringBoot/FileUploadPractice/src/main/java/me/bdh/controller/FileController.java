package me.bdh.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.bdh.service.FileService;


@Controller
@Slf4j
@RequiredArgsConstructor
public class FileController {
	private final FileService fileService;
	
	@PostMapping("fileTest")
	public String postMethodName(
			@RequestParam("selectFile") List<MultipartFile> selectFile
			, RedirectAttributes ra)  throws IllegalStateException, IOException{
		
		int count = fileService.save(selectFile);
		
		
		ra.addFlashAttribute("message", count + "개 파일이 저장되었습니다");		
		
		return "redirect:/";
	}
	
	
	
}
