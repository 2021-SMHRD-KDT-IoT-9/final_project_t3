package com.smhrd.iot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.http.ResponseEntity;

import com.smhrd.iot.domain.Mirror;
import com.smhrd.iot.domain.MyHistroy;
import com.smhrd.iot.domain.Picture;

@Mapper
public interface MirrorMapper {

	@Insert("insert into test (id, pw) values (#{id},#{pw})")
	public void insert(Mirror m);
	
	@Insert("insert into picture (username, fullPath) values (#{username},#{fullPath})")
	public void insertpic(Picture p);

	public List<MyHistroy> myHistory(String id);
}
