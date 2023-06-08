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
	
	// 웹 - 동영상 파일명 리스트
	public List<Video> videolist() {
		return mapper.videolist();
	}

	// 웹 - 이미지 파일명 리스트
	public List<Image> imglist(){
		return mapper.imglist();
	}
	
	// 웹 - 헤어 스타일 이미지 테이블 파일명 리스트
	public List<HairStyle> hairstylelist(){
		return mapper.hairstylelist();
	}
	
	// my_history 테이블 시퀀스 마지막 값 불러오기
	public int getMHSeq() {
		return mapper.getMHSeq();
	}

	// hair_style_img 테이블 시퀀스 마지막 값 불러오기
	public int getHSSeq() {
		return mapper.getHSSeq();
	}

	// image 테이블 시퀀스 마지막 값 불러오기
	public int getImgSeq() {
		return mapper.getImgSeq();
	}
	
	// image 테이블 시퀀스 마지막 값 불러오기
	public int getVideoSeq() {
		return mapper.getVideoSeq();
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
	
	// 안드로이드
	public List<HairStyle> allStyle(String id){
		return mapper.allStyle(id);
	}
	
	// 관리자 - 스타일 수정 
	public void styleUpload(String id, String name, String show) {
		int cnt = mapper.styleUpload(id, name, show);
		System.out.println("수정 성공 : "+cnt);	
	}
	
	// 안드 - 메모 수정 
	public void memoUpdate(String path, String memo) {
		int cnt = mapper.memoUpdate(path, memo);
		System.out.println("메모 수정 : "+cnt);
	}
	
	// 안드 - 메모 삭제 
	public void memoDelete(String path) {
		int cnt = mapper.memoDelete(path);
		System.out.println("메모 삭제 : "+cnt);
	}
	
}
