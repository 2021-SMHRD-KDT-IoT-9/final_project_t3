package com.smhrd.iot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.smhrd.iot.domain.HairStyle;
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
	
	public void insertvideo(Video v) {
		mapper.insertvideo(v);
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
	
	public List<Video> videolist() {
		return mapper.videolist();
	}
	
	public List<Image> imglist(){
		return mapper.imglist();
	}
	
	// 관리자 - 스타일
	public List<HairStyle> hairManager() {
		return mapper.hairManager();
	}
	// 관리자 - 비디오 
	public List<Video> videoManager(){
		return mapper.videoManager();
	}
	// 관리자 - 이미지 
	public List<Image> imageManager(){
		return mapper.imageManager();
	}
	// 관리자 - 등록 스타일 
	public List<HairStyle> hairYesManager(){
		return mapper.hairYesManager();
	}
	// 관리자 - 이미지 삭제 
	public void imgDelete(String id) {
		mapper.imgDelete(id);
	}
	// 관리자 - 동영상 삭제 YG
	public void videoDelete(String id) {
		mapper.videoDelete(id);
	}
	
	
}
