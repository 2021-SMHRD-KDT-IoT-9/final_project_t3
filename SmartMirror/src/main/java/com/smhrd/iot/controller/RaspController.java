package com.smhrd.iot.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.smhrd.iot.domain.MyHistory;
import com.smhrd.iot.domain.Picture;
import com.smhrd.iot.service.MirrorService;

@RestController
public class RaspController {

	@Autowired
	private MirrorService service;
	private int cnt = 0;
	
    // JDK 라파-Spring Before 사진 저장
    @PostMapping("/before")
    public void beforePic(@RequestParam("img") MultipartFile files) throws IOException{
    	cnt++;
    	
    	byte [] imgData = files.getBytes();
    	String fileName = files.getOriginalFilename();
    	System.out.println("파일 확인 : "+Arrays.toString(imgData));
    	System.out.println("원본이름 : "+fileName);
    	
		MyHistory mh = new MyHistory();
    	
		mh.setMember_id("bb");
		mh.setSalon_id("a000");
    	mh.setMemo("Before Memo!");
    	
		String picName = "before_"+ mh.getSalon_id()+"_"+mh.getMember_id()+"_"+cnt+".jpg";
		
    	String src = "C:/Users/user/git/final_project_t3/SmartMirror/src/main/resources/static/beforeImg/"+picName;

		mh.setPic_path(picName);
    	
    	try (FileOutputStream fos = new FileOutputStream(src)){
        		fos.write(imgData);
        		fos.flush();
        		
//        		service.insertpic(mh);

        		System.out.println("이미지 파일 저장 성공");
        		System.out.println("파일 저장 경로 :"+src);
        		
    	} catch (Exception e) {
    		System.out.println("이미지 저장 오류"+e.getMessage());
		}
    }
    
    
    // 애프터 사진 파일 저장
    @PostMapping("/after")
    public void afterPic(@RequestParam("img") MultipartFile files) throws IOException{
    	cnt++;
    	    	
    	byte [] imgData = files.getBytes();
    	String fileName = files.getOriginalFilename();
    	System.out.println("파일 확인 : "+Arrays.toString(imgData));
    	System.out.println("원본이름 : "+fileName);
    	
		MyHistory mh = new MyHistory();
		
		mh.setMember_id("bb");
		mh.setSalon_id("a000");
    	mh.setMemo("After Memo");
		
		String picName = "after_"+mh.getSalon_id()+"_"+mh.getMember_id()+"_"+cnt+".jpg";
				
    	String src = "C:/Users/user/git/final_project_t3/SmartMirror/src/main/resources/static/afterImg/"+picName;
    	
		mh.setPic_path(picName);
    	
    	try (FileOutputStream fos = new FileOutputStream(src)){
        		fos.write(imgData);
        		fos.flush();
        		
        		service.insertpic(mh);

        		System.out.println("이미지 파일 저장");
        		System.out.println("파일 저장 경로 :"+src);
        		
    	} catch (Exception e) {
    		System.out.println("이미지 저장 오류"+e.getMessage());
		}
    }
	
	
}
