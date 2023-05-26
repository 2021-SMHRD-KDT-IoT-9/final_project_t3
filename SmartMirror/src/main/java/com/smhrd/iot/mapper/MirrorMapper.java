package com.smhrd.iot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.http.ResponseEntity;

import com.smhrd.iot.domain.Mirror;
import com.smhrd.iot.domain.MyHistory;
import com.smhrd.iot.domain.Picture;

@Mapper
public interface MirrorMapper {

	@Insert("insert into test (id, pw) values (#{id},#{pw})")
	public void insert(Mirror m);
	
	@Insert("insert into my_history (member_id, salon_id, pic_path) values (#{member_id},#{salon_id},#{pic_path})")
	public void insertpic(MyHistory mh);

	public List<MyHistory> myHistory(String id);
	
	@Select("select pw from test where id=#{id}")
	public String idconfig(String id);
	
}
