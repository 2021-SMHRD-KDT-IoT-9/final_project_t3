package com.smhrd.iot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smhrd.iot.domain.HairStyle;
import com.smhrd.iot.domain.Image;
import com.smhrd.iot.domain.Video;
import com.smhrd.iot.service.MirrorService;

@RestController
public class ManagerWebController {

	@Autowired
	private MirrorService service;
	
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
}
