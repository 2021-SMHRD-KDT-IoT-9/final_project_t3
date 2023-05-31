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
public class Video {
	@Nonnull
    private String video_id;
    private String salon_id;
    @Nonnull
    private String video_name;

}
