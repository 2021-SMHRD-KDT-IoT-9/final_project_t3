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

import com.smhrd.iot.domain.Mirror;
import com.smhrd.iot.domain.MyHistory;
import com.smhrd.iot.domain.Picture;
import com.smhrd.iot.domain.Video;
import com.smhrd.iot.service.MirrorService;

@RestController
public class RestMirrorController {

	@Autowired
	private MirrorService service;
	
	private String fullPath;
	private String username;
	
	@GetMapping("/insert")
	public String insert(Mirror m) {
		service.insert(m);
		System.out.println("가입성공");
		System.out.println(m.getId());
		System.out.println(m.getPw());
		return "success";
	}
	
	// username, 사진 받아서 서버 저장 / username, 사진 저장 경로 db에 저장
	@PostMapping("/savepic")
	public String addFile(@RequestParam String username, @RequestParam MultipartFile file)
	throws IOException{
		System.out.println("username = "+ username);
		
		this.username = username; 
				
		if(!file.isEmpty()) {
			fullPath = "C:/Users/user/Desktop/pictest/" + file.getOriginalFilename();
			System.out.println("파일저장 fullPath = " + fullPath);
			file.transferTo(new File(fullPath));
				
			Picture p = new Picture();
			
			p.setUsername(username);
			p.setFullPath(fullPath);
			
			service.insertpic(p);
		}
		
		return "success";
	}
	
	
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
	
	// id값 받아서 db에서 pw값 가져오기
	@CrossOrigin
	@GetMapping("/idconfig")
	public String idconfig(@RequestParam String id) {
		System.out.println(id);
		return service.idconfig(id);
	}
	
	// 문자열 리턴
    @CrossOrigin
    @GetMapping("/test")
    public ResponseEntity<String> getData() {
        String data = "스마트미러";
        return ResponseEntity.ok(data);
    }

    // MyHistory테이블 id값으로 조회
    @CrossOrigin
    @GetMapping("/myhistory")
    public ResponseEntity<List<MyHistory>> myHistory(@RequestParam String query) {
       System.out.println("Received id: " + query);
       
       return ResponseEntity.ok(service.myHistory(query));
    }
    
    // 리스트에 담아 보내기
    @CrossOrigin
    @GetMapping("/videolist")
    public ResponseEntity<List<Video>> getVideos(@RequestParam String query) {
       System.out.println("Received query: " + query);
        List<Video> videos = new ArrayList<>();
        videos.add(new Video(1, "Video 1","https://www.youtube.com/embed/mFP7oGm-3nk"));
        videos.add(new Video(2, "Video 2",""));
        videos.add(new Video(3, "Video 3",""));
        return ResponseEntity.ok(videos);
    }
        
    // JDK 라파-Spring 저장

    @PostMapping("/pic")
    public void takePic(@RequestParam("img") MultipartFile files) throws IOException{
    	System.out.println("성공");
    	
    	byte [] imgData = files.getBytes();
    	String fileName = files.getOriginalFilename();
    	System.out.println("파일 확인 : "+Arrays.toString(imgData));
    	System.out.println("원본이름 : "+fileName);
    	
    	try (FileOutputStream fos = new FileOutputStream("C:\\Users\\user\\Desktop\\pictest\\imgg1.jpg")){
        		fos.write(imgData);
        		fos.flush();
        		System.out.println("이미지 파일 저장");
    	} catch (Exception e) {
    		System.out.println("이미지 저장 오류"+e.getMessage());
		}
    }
    	
    
}

	
