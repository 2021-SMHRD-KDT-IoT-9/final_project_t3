package com.smhrd.iot.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView Test() {
		ModelAndView mav = new ModelAndView();
		
		String url = "http://127.0.0.1:5000/tospring";
		String sb = "";
		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

			String line = null;

			while ((line = br.readLine()) != null) {
				sb = sb + line + "\n";
			}
			System.out.println("========br======" + sb.toString());
			if (sb.toString().contains("ok")) {
				System.out.println("test");
				
			}
			br.close();

			System.out.println("" + sb.toString());
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		mav.addObject("test1", sb.toString()); // "test1"는 jsp파일에서 받을때 이름, 
        						//sb.toString은 value값(여기에선 test)
		mav.addObject("fail", false);
		mav.setViewName("test");   // jsp파일 이름
		return mav;
 }
}