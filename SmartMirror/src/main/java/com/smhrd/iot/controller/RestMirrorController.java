package com.smhrd.iot.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.smhrd.iot.domain.HairStyle;
import com.smhrd.iot.domain.Image;
import com.smhrd.iot.domain.Mirror;
import com.smhrd.iot.domain.MyHistory;
import com.smhrd.iot.domain.Picture;
import com.smhrd.iot.domain.Salon;
import com.smhrd.iot.domain.Video;
import com.smhrd.iot.service.MirrorService;

import io.opencensus.resource.Resource;

@RestController
public class RestMirrorController {

	@Autowired
	private MirrorService service;
	
    // video 테이블 파일명 리스트 불러오기
    @CrossOrigin
    @GetMapping("/videolist")
    public ResponseEntity<List<Video>> videolist() {

    	List<Video> videos = service.videolist();
    	
        return ResponseEntity.ok(videos);
    }
     
    // image 테이블 파일명 불러오기
    @CrossOrigin
    @GetMapping("/imglist")
    public ResponseEntity<List<Image>> imglist() {
        
         List<Image> imgs = service.imglist();
                  
         return ResponseEntity.ok(imgs);
     }
    
    // hair_style_img 테이블 파일명 리스트 불러오기
    @CrossOrigin
    @GetMapping("/stylelist")
    public ResponseEntity<List<HairStyle>> hairstylelist() {
         List<HairStyle> styles = service.hairstylelist();
         return ResponseEntity.ok(styles);
     }
    
    // my_history 테이블 마지막 시퀀스 가져오기
	public int getMHSeq() {
		return service.getMHSeq();
	}
	
	// hair_style_img 테이블 마지막 시퀀스 가져오기
	public int getHSSeq() {
		return service.getHSSeq();
	}
	
	// image 테이블 마지막 시퀀스 가져오기
	public int getImgSeq() {
		return service.getImgSeq();
	}
	
	// video 테이블 마지막 시퀀스 가져오기
	public int getVideoSeq() {
		return service.getVideoSeq();
	}
	
	// Flask 서버와 통신하여 헤어+모델 얼굴 이미지 합성하는 메서드
	public ResponseEntity<String> callFlaskServer() {
	    String flaskUrl = "http://localhost:5001/pytest";
	    UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(flaskUrl)
	            .queryParam("salon_id", "a000");

	    RestTemplate restTemplate = new RestTemplate();
	    String errorMessage = "";
	    
	    try {
	        ResponseEntity<String> response = restTemplate.getForEntity(builder.toUriString(), String.class);
	        String responseBody = response.getBody();
	        // TODO: 응답 결과를 원하는 방식으로 처리
	        System.out.println(response);
	        return response;
	    } catch (HttpServerErrorException.InternalServerError e) {
	        // Flask 서버에서 500 오류 응답이 왔을 경우 처리
	        errorMessage = "Flask 서버에서 내부 오류가 발생했습니다.";
	        System.err.println(errorMessage);
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
	    } catch (RestClientException e) {
	        // Flask 서버와의 통신 중에 발생한 다른 예외 처리
	        errorMessage = "Flask 서버와의 통신 중에 오류가 발생했습니다.";
	        System.err.println(errorMessage);
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
	    }
	}
	
}

	
