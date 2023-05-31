package com.smhrd.iot.domain;

import javax.annotation.Nonnull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class HairStyle {
	@Nonnull
	private String hair_img_path; 
	@Nonnull
	private String style_name;
	@Nonnull
	private String cut_day; 
	private String img_show;  
}
