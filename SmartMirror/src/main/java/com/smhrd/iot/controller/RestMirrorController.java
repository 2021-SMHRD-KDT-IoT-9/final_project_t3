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
	
	@GetMapping("/insert")
	public String insert(Mirror m) {
		service.insert(m);
		System.out.println("가입성공");
		System.out.println(m.getId());
		System.out.println(m.getPw());
		return "test-success";
	}
	
	
	// 이미지 저장 / db 저장
	@PostMapping("/saveImg")
	public String addImg(@RequestParam String imgName, @RequestParam MultipartFile file)
	throws IOException{
		System.out.println("imgName = "+ imgName);
		
		int seq = getImgSeq()+1;
				
		if(!file.isEmpty()) {
			
			Image i = new Image();
			
			String path = "img"+seq;
			
			i.setSalon_id("a000");
			i.setImg_id(i.getSalon_id()+"_"+path);
			
			String fullPath = "C:/Users/user/git/final_project_t3/SmartMirror/src/main/resources/static/uploadimg/"+i.getImg_id()+".jpg";
			System.out.println("파일저장 fullPath = " + fullPath);
			file.transferTo(new File(fullPath));
			i.setImg_name(imgName);
				
			service.saveImg(i);
		}
		return "test-success";
	}
	
	
	 //영상 저장/ db 저장
	@PostMapping("/saveVideo")
	public String addVideo(@RequestParam String videoName, @RequestParam MultipartFile file)
	throws IOException{
		System.out.println("videoName = "+ videoName);
		int seq = getVideoSeq()+1;
				
		if(!file.isEmpty()) {
			
			Video v = new Video();
			
			String path = "video"+seq;
			
			v.setSalon_id("a000");
			v.setVideo_id(v.getSalon_id()+"_"+path);
			
			String fullPath = "C:/Users/user/git/final_project_t3/SmartMirror/src/main/resources/static/uploadvideo/"+v.getVideo_id()+".mp4";
			System.out.println("파일저장 fullPath = " + fullPath);
			file.transferTo(new File(fullPath));
			v.setVideo_name(videoName);
				
			service.saveVideo(v);
		}
		return "test-success";
	}

//	// 이미지 파일 명 웹 리턴
//    @CrossOrigin
//    @GetMapping("/imgtest")
//    public ResponseEntity<String> getData() {
//        String data = "img01";
//        return ResponseEntity.ok(data);
//    }

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
	
	@GetMapping("/seqtest")
	public void seqtest() {
		int seq =  getMHSeq()+1;
		System.out.println(seq);
	}
       
	// Flask 서버와 통신하여 헤어+모델 얼굴 이미지 합성
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

	
