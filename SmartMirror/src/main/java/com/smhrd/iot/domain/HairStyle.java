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
	private String hair_id;
	
	private int sequence;
	private String img_show;  
	
	@Nonnull
	private String style_name;
	private String salon_id;
	@Nonnull
	private String cut_dy; 
	
	public HairStyle(@Nonnull String hair_id, @Nonnull String style_name) {
		this.hair_id = hair_id;
		this.style_name = style_name;
	}
	
}
