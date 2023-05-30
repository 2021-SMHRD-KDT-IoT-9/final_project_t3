package com.smhrd.iot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String main() {
		return "main";
	}
	
	@GetMapping("/uptest")
	public String testmain() {
		return "ztest-main";
	}
	
	@GetMapping("/iup")
	public String test() {
		return "ztest-saveImg";
	}
	@GetMapping("/vup")
	public String vup() {
		return "ztest-saveVideo";
	}

	
	
}
