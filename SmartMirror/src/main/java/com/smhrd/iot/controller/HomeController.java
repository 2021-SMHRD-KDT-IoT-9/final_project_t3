package com.smhrd.iot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String main() {
		return "testmain";
	}
	
	@GetMapping("/iup")
	public String test() {
		return "saveImg";
	}
	@GetMapping("/vup")
	public String vup() {
		return "saveVideo";
	}

	
	
}
