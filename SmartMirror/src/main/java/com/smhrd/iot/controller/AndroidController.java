package com.smhrd.iot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smhrd.iot.domain.HairStyle;
import com.smhrd.iot.domain.MyHistory;
import com.smhrd.iot.service.MirrorService;

@RestController
public class AndroidController {
	
	@Autowired
	private MirrorService service;
	
	// id값 받아서 db에서 pw값 가져오기
	@CrossOrigin
	@GetMapping(value="/idconfig")
	public ResponseEntity<List<MyHistory>> idconfig(@RequestParam String id) {
		System.out.println(id);
		List<MyHistory> list = service.idconfig(id);
		System.out.println(list.get(0).getSalon_name());
		return ResponseEntity.ok(list);
	}
	
    // MyHistory테이블 id값으로 조회
    @CrossOrigin
    @GetMapping("/myhistory")
    public ResponseEntity<List<MyHistory>> myHistory(@RequestParam String query) {
       System.out.println("Received id: " + query);
       List<MyHistory> list = service.myHistory(query);
       System.out.println(list.get(0));
       return ResponseEntity.ok(list);
    }
    
    // allStyle view
    @GetMapping("/and/allStyle")
    public ResponseEntity<List<HairStyle>> allStyle(@RequestParam("id")String id) {
    	System.out.println(id);
    	List<HairStyle> list = service.allStyle(id);
    	System.out.println("allStyle : "+list.get(0).getStyle_name());
    	System.out.println("id : "+list.get(0).getHair_id());
    	return ResponseEntity.ok(list);
    	
    }
    
    // 메모 저장
    @PostMapping("/and/memoUpdate")
    public void memoUpdate(@RequestParam("imgPath") String path , @RequestParam("memo") String memo) {
    	System.out.println("메모 진입");
    	System.out.println("path: "+path);
    	System.out.println("memo : "+memo);
    	service.memoUpdate(path, memo);
    }
    
    // 메모 삭제
    @GetMapping("/and/memoDelete")
    public void memoDelete(@RequestParam("id")String path) {
    	System.out.println("진입 ");
    	System.out.println("path:" + path);
    	service.memoDelete(path);	
    }
    
    
	
}
