package com.smhrd.iot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.http.ResponseEntity;

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

	public List<MyHistory> myHistory(String id);
	
	public List<MyHistory> idconfig(String id);
	
	public void saveImg(Image i);
	
	public void saveVideo(Video v);
	
}
