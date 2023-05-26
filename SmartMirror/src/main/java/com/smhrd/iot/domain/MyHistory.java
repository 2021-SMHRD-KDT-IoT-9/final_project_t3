package com.smhrd.iot.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyHistory {

		private String member_id;
		private String cut_dy;
		private int sequence;
		private String salon_id;
		private String pic_path;
		private String memo;
		private String salon_name; // 안드로이드 떄문에 추가 

}
