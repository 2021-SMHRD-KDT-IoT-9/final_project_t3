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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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
			
			i.setSalon_id("a001");
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
			
			v.setSalon_id("a001");
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
    
    // RestTemplate 이용한 flask 통신 시도
    @CrossOrigin
    @PostMapping("/testpage")
    public String testpage() {
    	
    	RestTemplate restTemplate = new RestTemplate();
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	
    	String json = "{ \"command\": \"run_script\", \"script_path\": \"/python/Untitled.py\", \"args\": [\"arg1\", \"arg2\"],\"options\": { \"member_id\": \"aa\", \"salon_id\": \"a000\" }}";
    	HttpEntity<String> entity = new HttpEntity<String>(json, headers);
    	
    	String url = "http://59.0.234.211:5000/testpage";
    	String response = restTemplate.postForObject(url, entity, String.class);
    	
    	return response;
    }
    
    // flask url 연동
	@RequestMapping(value = "/pytest", method = RequestMethod.GET)
	public ModelAndView pytest() {
		ModelAndView mav = new ModelAndView();
		
		String salon_id = "a000";
		
		String url = "http://59.0.234.211:5000/testpage?salon_id="+salon_id;
		String sb = "";
		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
			

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

			String line = null;

			while ((line = br.readLine()) != null) {
				sb = sb + line + "\n";
			}
			System.out.println("========br======" + sb.toString());
			if (sb.toString().contains("ok")) {
				System.out.println("test");
				
			}
			br.close();

			System.out.println("" + sb.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mav.addObject("test1", sb.toString()); // "test1"는 jsp파일에서 받을때 이름, 
        						//sb.toString은 value값(여기에선 test)
		mav.addObject("fail", false);
		mav.setViewName("ztest-success");   // jsp파일 이름
		return mav;
	}
    
	public int getMHSeq() {
		return service.getMHSeq();
	}
	
	public int getHSSeq() {
		return service.getHSSeq();
	}
	
	public int getImgSeq() {
		return service.getImgSeq();
	}
	
	public int getVideoSeq() {
		return service.getVideoSeq();
	}
	
	@GetMapping("/seqtest")
	public void seqtest() {
		int seq =  getMHSeq()+1;
		System.out.println(seq);
	}
    
	
	
	
}

	
