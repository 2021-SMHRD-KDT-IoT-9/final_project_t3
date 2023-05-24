package com.smhrd.iot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smhrd.iot.domain.Mirror;
import com.smhrd.iot.domain.Picture;
import com.smhrd.iot.mapper.MirrorMapper;

@Service
public class MirrorService {

	@Autowired
	private MirrorMapper mapper;
	
	public void insert(Mirror m) {
		mapper.insert(m);
	}
	
	public void insertpic(Picture p) {
		mapper.insertpic(p);
	}
	
}
