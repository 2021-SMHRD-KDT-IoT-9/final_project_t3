package com.smhrd.iot.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.smhrd.iot.domain.HairStyle;
import com.smhrd.iot.domain.Image;
import com.smhrd.iot.domain.Video;
import com.smhrd.iot.service.MirrorService;

@RestController
public class ManagerWebController {

	@Autowired
	private MirrorService service;

	@Autowired
	private HttpSession session;

	// 관리자 페이지 - 스타일 관리
	@PostMapping("/hairmanager")
	public List<HairStyle> hairManager() {
		return service.hairManager();
	}

	// 관리자 - 동영상 관리
	@PostMapping("/videomanager")
	public List<Video> videoManager() {
		return service.videoManager();
	}

	// 관리자 - 이미지
	@PostMapping("/imagemanager")
	public List<Image> imageManager() {
		return service.imageManager();
	}

	// 관리자 - 등록 스타일
	@PostMapping("/hairsaveyes")
	public List<HairStyle> hairYesManager() {
		return service.hairYesManager();
	}

	// 동영상 삭제 YG
	@GetMapping("/videodelete")
	public void videoDelete(@RequestParam("id") String id) {
		service.videoDelete(id);

		Integer cnt = (Integer) session.getAttribute("videocnt");
		cnt--;
		System.out.println("삭제 " + cnt);
		session.setAttribute("videocnt", cnt);

		File file = new File(
				"C:/Users/user/git/final_project_t3/SmartMirror/src/main/resources/static/uploadvideo/" + id + ".MP4");
		if (file.exists()) { // 파일 존재 확인
			if (file.delete()) {
				System.out.println("파일삭제 성공");
			} else {
				System.out.println("파일삭제 실패");
			}
		} else {
			System.out.println("파일이 존재하지 않습니다.");
		}

	}

	// 동영상 저장
	@PostMapping("/videoupload")
	public void videoUpload(@RequestParam("name") String name, @RequestPart("file") MultipartFile file)
			throws IOException {
		{
			Integer cnt = (Integer) session.getAttribute("videocnt");
			if (cnt == null) {
				cnt = 0;
			}

			if (!file.isEmpty()) {
				cnt++;
				System.out.println("추가" + cnt);
				session.setAttribute("videocnt", cnt);
				Video v = new Video();
				String path = "video" + cnt;
				v.setSalon_id("a001");
				v.setVideo_id(v.getSalon_id() + "_" + path);
				String fullPath = "C:/Users/user/git/final_project_t3/SmartMirror/src/main/resources/static/uploadvideo/"
						+ v.getVideo_id() + ".MP4";
				System.out.println("파일저장 fullpath=" + fullPath);
				file.transferTo(new File(fullPath));
				v.setVideo_name(name);
				service.saveVideo(v);
			}
		}
	}

	// 이미지 삭제
	@GetMapping("/imgdelete")
	public void imgDelete(@RequestParam("id") String id) {
		service.imgDelete(id);

		Integer cnt = (Integer) session.getAttribute("imgcnt");
		cnt--;
		System.out.println("삭제" + cnt);
		session.setAttribute("imgcnt", cnt);
		File file = new File(
				"C:/Users/user/git/final_project_t3/SmartMirror/src/main/resources/static/uploadimg/" + id + ".jpg");
		if (file.exists()) { // 파일 존재 확인
			if (file.delete()) {
				System.out.println("파일삭제 성공");
			} else {
				System.out.println("파일삭제 실패");
			}
		} else {
			System.out.println("파일이 존재하지 않습니다.");
		}
	}

	// 이미지 저장 2
	@PostMapping("/imgupload")
	public void imgUpload(@RequestParam("name") String name, @RequestPart("file") MultipartFile file)
			throws IOException {
		Integer cnt = (Integer) session.getAttribute("imgcnt");
		if (cnt == null) {
			cnt = 0;
		}
		if (!file.isEmpty()) {
			cnt++;
			System.out.println("추가" + cnt);
			session.setAttribute("imgcnt", cnt);

			Image i = new Image();
			String path = "img" + cnt;
			i.setSalon_id("a001");
			i.setImg_id(i.getSalon_id() + "_" + path);
			String fullPath = "C:/Users/user/git/final_project_t3/SmartMirror/src/main/resources/static/uploadimg/"
					+ i.getImg_id() + ".jpg";
			System.out.println("파일저장 fullPath = " + fullPath);
			file.transferTo(new File(fullPath));
			i.setImg_name(name);
			service.saveImg(i);
		}
	}

	// 관리자 - 스타일 수정
	@PostMapping("/styleupload")
	public void styleUpload(@RequestParam("id") String id, @RequestParam("newStyleName") String newName,
			@RequestParam("newExposure") String newExposure) {
		System.out.println("id: " + id);
		System.out.println("newStyleName: " + newName);
		System.out.println("newExposure: " + newExposure);
		
		service.styleUpload(id, newName, newExposure);
	}

}
