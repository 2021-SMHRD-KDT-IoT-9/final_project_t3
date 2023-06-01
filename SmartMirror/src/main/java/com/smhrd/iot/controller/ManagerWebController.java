package com.smhrd.iot.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.smhrd.iot.domain.HairStyle;
import com.smhrd.iot.domain.Image;
import com.smhrd.iot.domain.Video;
import com.smhrd.iot.service.MirrorService;

@RestController
public class ManagerWebController {

	@Autowired
	private MirrorService service;
	
	@Autowired
	private HttpSession session;
	
    // 관리자 페이지 - 스타일 관리
    @PostMapping("/hairmanager")
    public List<HairStyle> hairManager() {
    	return service.hairManager();
    }
    
    // 관리자 - 동영상 관리
    @PostMapping("/videomanager")
    public List<Video> videoManager(){
    	return service.videoManager();
    }
    
    // 관리자 - 이미지 
    @PostMapping("/imagemanager")
    public List<Image> imageManager(){
    	return service.imageManager();
    }
    
    // 관리자 - 등록 스타일 
    @PostMapping("hairsaveyes")
    public List<HairStyle> hairYesManager(){
    	return service.hairYesManager();
    }
    
    // 동영상 삭제 
    @GetMapping("/videodelete")
    public void videoDelete(@RequestParam("name") String name) {
    	System.out.println(name);
    }
    
    // 동영상 저장
    @PostMapping("/videoupload")
    public void videoUpload(@RequestParam("name") String name, @RequestPart("file") MultipartFile file) {
        // name과 file을 사용하여 업로드된 파일 처리
        // 예: 파일 저장, 데이터베이스에 정보 저장 등
        System.out.println("Name: " + name);
        System.out.println("File Name: " + file.getOriginalFilename());
        System.out.println("File Size: " + file.getSize());
    }
    // 이미지 삭제 
    @GetMapping("imgdelete")
    public void imgDelete(@RequestParam("id") String id) {    	
    	service.imgDelete(id);
    	
    	Integer cnt = (Integer) session.getAttribute("cnt");
    	cnt--;
    	System.out.println(cnt);
    	session.setAttribute("cnt", cnt);
    }
    // 이미지 저장
    @PostMapping("/imgupload")
    public void imgUpload(@RequestParam("name") String name, @RequestPart("file") MultipartFile file) throws IOException {	
       Integer cnt = (Integer) session.getAttribute("cnt");
       if(cnt == null) {
    	   cnt = 0;
       }
    	
    	if (!file.isEmpty()) {
        	System.out.println(cnt);
        	cnt++;
        	session.setAttribute("cnt", cnt);
        	
            Image i = new Image();
            String path = "img" + cnt;
            i.setSalon_id("a001");
            i.setImg_id(i.getSalon_id() + "_" + path);
            String fullPath = "C:/Users/user/git/final_project_t3/SmartMirror/src/main/resources/static/uploadimg/" + i.getImg_id() + ".jpg";
            System.out.println("파일저장 fullPath = " + fullPath);
            file.transferTo(new File(fullPath));
            i.setImg_name(name);
            service.saveImg(i);              
        }
       
    }

    
    
}
