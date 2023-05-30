package com.smhrd.iot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String main() {
		return "main";
	}
	
	@GetMapping("/video")
	public String video() {
		return "video";
	}
	
	@GetMapping("/image")
	public String image() {
		return "image";
	}	
	
	@GetMapping("/hair")
	public String hair() {
		return "hair";
	}	
	
	@GetMapping("/style")
	public String style() {
		return "style";
	}
	
	
	// ------------------------test-------------------------
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
