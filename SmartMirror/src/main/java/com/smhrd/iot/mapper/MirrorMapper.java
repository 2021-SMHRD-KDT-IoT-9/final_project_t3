package com.smhrd.iot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.http.ResponseEntity;

import com.smhrd.iot.domain.HairStyle;
import com.smhrd.iot.domain.Image;
import com.smhrd.iot.domain.Mirror;
import com.smhrd.iot.domain.MyHistory;
import com.smhrd.iot.domain.Picture;
import com.smhrd.iot.domain.Video;

@Mapper
public interface MirrorMapper {

	@Insert("insert into test (id, pw) values (#{id},#{pw})")
	public void insert(Mirror m);
	
	@Insert("insert into my_history (member_id, salon_id, pic_path) values (#{member_id},#{salon_id},#{pic_path})")
	public void insertpic(MyHistory mh);
	
	@Insert("insert into video (video_id, salon_id, video_name) values (#{video_id},#{salon_id},#{video_name}) ")
	public void insertvideo(Video v);

	public List<MyHistory> myHistory(String id);
	
	public List<MyHistory> idconfig(String id);
	
	public void saveImg(Image i);
	
	public void saveVideo(Video v);
	
	public List<Video> videolist();

	public List<Image> imglist();
	
	// 관리자 - 스타일 관리 
	public List<HairStyle> hairManager();
	
	// 관리자 - 동영상 
	public List<Video> videoManager();
	
	// 관리자 - 이미지 
	public List<Image> imageManager();
	
	// 관리자 - 등록스타일 
	public List<HairStyle> hairYesManager();
	
	// 관리자 - 이미지 삭제 
	@Delete("delete from image where img_id = #{id}")
	public int imgDelete(String id);
	
	//관리자 - 동영상 삭제
	@Delete("delete from video where video_name=#{name}")
	public int videoDelete(String name);
	
	
}
