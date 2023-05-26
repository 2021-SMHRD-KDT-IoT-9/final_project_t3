package com.smhrd.iot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.smhrd.iot.domain.Mirror;
import com.smhrd.iot.domain.MyHistory;
import com.smhrd.iot.mapper.MirrorMapper;

@Service
public class MirrorService {

	@Autowired
	private MirrorMapper mapper;
	
	public void insert(Mirror m) {
		mapper.insert(m);
	}
	
	public void insertpic(MyHistory mh) {
		mapper.insertpic(mh);
	}
	
	public List<MyHistory> myHistory (String id){
		return mapper.myHistory(id);
	}
	
	public String idconfig(String id) {
		return mapper.idconfig(id);
	}
	
}
