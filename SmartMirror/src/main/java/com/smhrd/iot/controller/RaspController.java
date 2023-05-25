package com.smhrd.iot.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.smhrd.iot.service.MirrorService;

@RestController
public class RaspController {

	@Autowired
	private MirrorService service;
	
    // JDK 라파-Spring 저장

    @PostMapping("/pic")
    public void takePic(@RequestParam("img") MultipartFile files) throws IOException{
    	System.out.println("성공");
    	
    	byte [] imgData = files.getBytes();
    	String fileName = files.getOriginalFilename();
    	System.out.println("파일 확인 : "+Arrays.toString(imgData));
    	System.out.println("원본이름 : "+fileName);
    	
    	try (FileOutputStream fos = new FileOutputStream("C:\\Users\\user\\Desktop\\pictest\\imgg1.jpg")){
        		fos.write(imgData);
        		fos.flush();
        		System.out.println("이미지 파일 저장");
    	} catch (Exception e) {
    		System.out.println("이미지 저장 오류"+e.getMessage());
		}
    }
	
	
}
