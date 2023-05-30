package com.smhrd.iot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.smhrd.iot.domain.Image;
import com.smhrd.iot.domain.Mirror;
import com.smhrd.iot.domain.MyHistory;
import com.smhrd.iot.domain.Video;
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
	
	public List<MyHistory> idconfig(String id) {
		return mapper.idconfig(id);
	}
	
	public void saveImg(Image i) {
		mapper.saveImg(i);
	}
	
	public void saveVideo(Video v) {
		mapper.saveVideo(v);
	}
	
}
