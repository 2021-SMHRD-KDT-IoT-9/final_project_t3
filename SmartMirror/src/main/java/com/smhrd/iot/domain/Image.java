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
public class Image {
	
	@Nonnull
	private String img_id;
	private String salon_id;
	@Nonnull
	private String img_name; 
	
}
