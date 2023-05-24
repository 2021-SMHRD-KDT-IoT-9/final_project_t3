package com.smhrd.iot.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.smhrd.iot.domain.Mirror;
import com.smhrd.iot.domain.Picture;
import com.smhrd.iot.service.MirrorService;

@RestController
public class RestMirrorController {

	@Autowired
	private MirrorService service;
	
	private String fullPath;
	private String username;
	
	@GetMapping("/insert")
	public String insert(Mirror m) {
		service.insert(m);
		System.out.println("가입성공");
		System.out.println(m.getId());
		System.out.println(m.getPw());
		return "success";
	}
	
	@PostMapping("/savepic")
	public String addFile(@RequestParam String username, @RequestParam MultipartFile file)
	throws IOException{
		System.out.println("username = "+ username);
		
		this.username = username; 
				
		if(!file.isEmpty()) {
			fullPath = "C:/Users/user/Desktop/pictest/" + file.getOriginalFilename();
			System.out.println("파일저장 fullPath = " + fullPath);
			file.transferTo(new File(fullPath));
				
			Picture p = new Picture();
			
			p.setUsername(username);
			p.setFullPath(fullPath);
			
			service.insertpic(p);
			
		}
		
		return "success";
	}
	
	public void insertpic(Picture p) {
		service.insertpic(p);
	}
	
	
}
