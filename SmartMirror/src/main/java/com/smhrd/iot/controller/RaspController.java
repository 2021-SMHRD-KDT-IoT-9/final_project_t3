package com.smhrd.iot.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.smhrd.iot.domain.MyHistory;
import com.smhrd.iot.domain.Picture;
import com.smhrd.iot.service.MirrorService;

@RestController
public class RaspController {

	@Autowired
	private MirrorService service;
	
	@Autowired
	private RestMirrorController restMirrorController;
	
    // 라즈베리파이에서 이미지 파일 받기 / Before 사진 서버 저장
    @PostMapping("/before")
    public void beforePic(@RequestParam("img") MultipartFile files) throws IOException{
    	int seq = restMirrorController.getMHSeq();
    	
    	byte [] imgData = files.getBytes();
    	String fileName = files.getOriginalFilename();
    	System.out.println("파일 확인 : "+Arrays.toString(imgData));
    	System.out.println("원본이름 : "+fileName);
    	
		MyHistory mh = new MyHistory();
    	
		mh.setMember_id("30495592wo51");
		mh.setSalon_id("a000");
    	mh.setMemo("Before Memo!");
    	
		String picName = "before_"+ mh.getSalon_id()+"_"+mh.getMember_id()+"_"+seq+".jpg";
		
    	String src = "C:/Users/user/git/final_project_t3/SmartMirror/src/main/resources/static/beforeImg/"+picName;

		mh.setPic_path(picName);
    	
    	try (FileOutputStream fos = new FileOutputStream(src)){
        		fos.write(imgData);
        		fos.flush();
        		System.out.println("Before 이미지 파일 저장 성공");
        		System.out.println("파일 저장 경로 :"+src);
        		
    	} catch (Exception e) {
    		System.out.println("이미지 저장 오류"+e.getMessage());
		}
    }
    
    // 라파베리파이에서 사진, member_id 받기 / after 사진 저장 / 세그먼트한 헤어와 모델 합성 후 서버에 합성 사진 저장
    @PostMapping("/after")
    public ResponseEntity<String> afterPic(@RequestParam("img") MultipartFile files, @RequestParam("text") String text) throws IOException{
    	int seq = restMirrorController.getMHSeq();
    	
    	byte [] imgData = files.getBytes();
    	String fileName = files.getOriginalFilename();
    	System.out.println("파일 확인 : "+Arrays.toString(imgData));
    	System.out.println("원본이름 : "+fileName);
    	System.out.println("userId : "+text);
		MyHistory mh = new MyHistory();
		
		mh.setMember_id(text);
		mh.setSalon_id("a000");
    	mh.setMemo("AfterMemo");
    	
		String picName = "after_"+mh.getSalon_id()+"_"+mh.getMember_id()+"_"+seq+".jpg";
				
    	String src = "C:/Users/user/git/final_project_t3/SmartMirror/src/main/resources/static/afterImg/"+picName;
    	
		mh.setPic_path(picName);
    	
		ResponseEntity<String> result = restMirrorController.callFlaskServer();
		
    	try (FileOutputStream fos = new FileOutputStream(src)){
        		fos.write(imgData);
        		fos.flush();
        		
        		service.insertpic(mh);

        		System.out.println("이미지 파일 저장");
        		System.out.println("파일 저장 경로 :"+src);
        		
        		restMirrorController.callFlaskServer();
        		
        		System.out.println("python 연결 성공");        		
        		return result;
        		
    	} catch (Exception e) {
    		System.out.println("이미지 저장 오류"+e.getMessage());
    			return result;
		}
    }
	
}
