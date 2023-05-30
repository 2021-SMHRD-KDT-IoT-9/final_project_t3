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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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
	
	private int cnt = 0;
	
	@GetMapping("/insert")
	public String insert(Mirror m) {
		service.insert(m);
		System.out.println("가입성공");
		System.out.println(m.getId());
		System.out.println(m.getPw());
		return "test-success";
	}
	
	
	// 이미지 저장/ db 저장
	@PostMapping("/saveImg")
	public String addImg(@RequestParam String imgName, @RequestParam MultipartFile file)
	throws IOException{
		System.out.println("imgName = "+ imgName);
		cnt++;
				
		if(!file.isEmpty()) {
			
			Image i = new Image();
			
			String path = "img"+cnt;
			
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
	
	
	// 영상 저장/ db 저장
//	@PostMapping("/saveVideo")
//	public String addVideo(@RequestParam String videoName, @RequestParam MultipartFile file)
//	throws IOException{
//		System.out.println("videoName = "+ videoName);
//		cnt++;
//				
//		if(!file.isEmpty()) {
//			
//			Video v = new Video();
//			
//			String path = "video"+cnt;
//			
//			v.setSalon_id("a001");
//			v.setVideo_id(v.getSalon_id()+"_"+path);
//			
//			String fullPath = "C:/Users/user/git/final_project_t3/SmartMirror/src/main/resources/static/uploadvideo/"+v.getVideo_id()+".mp4";
//			System.out.println("파일저장 fullPath = " + fullPath);
//			file.transferTo(new File(fullPath));
//			v.setVideo_name(videoName);
//				
//			service.saveVideo(v);
//		}
//		return "test-success";
//	}
//	
	
	// 플라스크에서 스프링으로 스트링 타입 받기
	@RequestMapping(value = "/testt", method = RequestMethod.GET)
	public ModelAndView Test() {
		ModelAndView mav = new ModelAndView();
		
		String url = "http://121.147.52.253:5000/tospring";
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

			e.printStackTrace();
		}
		mav.addObject("test1", sb.toString()); // "test1"는 jsp파일에서 받을때 이름, 
        						//sb.toString은 value값(여기에선 test)
		mav.addObject("fail", false);
		mav.setViewName("test");   // jsp파일 이름
		return mav;
 }
		
	// 이미지 파일 명 웹 리턴
    @CrossOrigin
    @GetMapping("/test")
    public ResponseEntity<String> getData() {
        String data = "img01";
        return ResponseEntity.ok(data);
    }
    
    @CrossOrigin
    @GetMapping("/hodoo")
    public ResponseEntity<String> sendVideo() {
        String data = "hodoo.mp4";
        return ResponseEntity.ok(data);
    }
    
//    @GetMapping("/img")
//    public String img() {
//    	return "test";
//    }
   

    // 리스트에 담아 웹 보내기
    @CrossOrigin
    @GetMapping("/videolist")
    public ResponseEntity<List<Video>> videolist() {
    	//String salon_id = "a001";
//    	List<Video> videos = new ArrayList<>();
//      videos.add(new Video("a001_video1", "1234"));
//      videos.add(new Video("a001_video2", "12345"));
//      videos.add(new Video("a001_video3", "123"));
    	
    	List<Video> videos = service.videolist();
    	
        return ResponseEntity.ok(videos);
    }
     
    @CrossOrigin
    @GetMapping("/imglist")
    public ResponseEntity<List<Image>> imglist() {
        
         List<Image> imgs = service.imglist();
         
//         imgs.add(new Video(1, "Video 1",""));
//         imgs.add(new Video(2, "Video 2",""));
//         imgs.add(new Video(3, "Video 3",""));
         
         return ResponseEntity.ok(imgs);
     }
        
    
    
//    @CrossOrigin
//    @GetMapping("/image")
//    public ResponseEntity<FileSystemResource> getImage() throws IOException {
//            // 이미지 파일을 읽어옵니다.
//            FileSystemResource imageResource = new FileSystemResource("static/img/img01.jpg"); // 이미지 파일 경로로 수정해야 합니다.
//
//            // 이미지 파일을 응답에 포함하여 전송합니다.
//            return ResponseEntity.ok()
//                    .contentType(MediaType.IMAGE_JPEG)
//                    .body(imageResource);
//        }

    
    
    

    
    
    
}

	
