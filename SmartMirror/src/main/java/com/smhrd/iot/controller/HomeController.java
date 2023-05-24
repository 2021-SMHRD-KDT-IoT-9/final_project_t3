package com.smhrd.iot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String test() {
		return "savetest";
	}
	
	//  아자 
	public void test1() {
		// 삭제 하셍ㅇ
	}
	
	public void tt1() {
		// 삭제 하셍ㅇ
	}
	
}
