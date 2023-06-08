package com.smhrd.iot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	// 관리자 메인 페이지 
	@GetMapping("/main")
	public String main() {
		return "main";
	}
	
	// 관리자 동영상 관리 페이지
	@GetMapping("/video")
	public String video() {
		return "video";
	}
	
	// 관리자 이미지 관리 페이지
	@GetMapping("/image")
	public String image() {
		return "image";
	}	
	
	// 관리자 스타일 관리 페이지
	@GetMapping("/style")
	public String style() {
		return "style";
	}
	
	// 관리자 등록 스타일 보기 페이지
	@GetMapping("/hair")
	public String hair() {
		return "hair";
	}	

}
