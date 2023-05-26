package com.smhrd.iot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
		return ResponseEntity.ok(service.idconfig(id));
	}
		
    // MyHistory테이블 id값으로 조회
    @CrossOrigin
    @GetMapping("/myhistory")
    public ResponseEntity<List<MyHistory>> myHistory(@RequestParam String query) {
       System.out.println("Received id: " + query);
       System.out.println(service.myHistory(query));
       return ResponseEntity.ok(service.myHistory(query));
    }
    
    
	
}
