package com.smhrd.iot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.http.ResponseEntity;

import com.smhrd.iot.domain.HairStyle;
import com.smhrd.iot.domain.Image;
import com.smhrd.iot.domain.Mirror;
import com.smhrd.iot.domain.MyHistory;
import com.smhrd.iot.domain.Picture;
import com.smhrd.iot.domain.Video;

@Mapper
public interface MirrorMapper {

	public void insertpic(MyHistory mh);

	public List<MyHistory> myHistory(String id);
	
	public List<MyHistory> idconfig(String id);
	
	public void saveImg(Image i);
	
	public void saveVideo(Video v);
	
	public List<Video> videolist();

	public List<Image> imglist();
	
	public List<HairStyle> hairstylelist();
	
	public int getMHSeq();
	
	public int getHSSeq();
	
	public int getImgSeq();
	
	public int getVideoSeq();
	
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
	@Delete("delete from video where video_id=#{id}")
	public int videoDelete(String id);
	
	// 관리자 - 스타일 수정
	@Update("update hair_style_img set style_name= #{name}, img_show= #{show} where hair_id = #{id}")
	public int styleUpload (String id, String name, String show);

	// 안드로이드 
	public List<HairStyle> allStyle(String id);
	
	// 안드로이드 메모 수정
	@Update("update my_history set memo = #{memo} where pic_path = #{path}")
	public int memoUpdate(String path, String memo);
	
	// 안드로이드 메모 삭제
	@Delete("delete from my_history where pic_path = #{path}")
	public int memoDelete(String path);

}
	
