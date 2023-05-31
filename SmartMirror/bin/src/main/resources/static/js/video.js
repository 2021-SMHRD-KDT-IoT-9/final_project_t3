// 관리자 - 동영상 관리
function video_Manager() {
	$.ajax({
		url: "videomanager",
		type: "post",
		dataType: "json",
		success: videoView,
		error: function() {
			alert("통신실패!")
		}
	})
}
function videoView(data) {
	console.log("비디오 성공", data)
	var result = "<div class='logoset' id='videoList'></div>";
	$.each(data, (index, vo) => {
		result += "<div class='logostyle'>";
		result += "<video width='150' height='150' controls>";
		result += "<source src='/uploadvideo/" + vo.video_id + ".mp4' type='video/mp4'>";
		result += "Your browser does not support the video tag.";
		result += "</video>";
		result += "<p>" + vo.video_name + "</p>";
		result += "<span>삭제하기❌</span>";
		result += "</div>";
	});
	result += "<div style='margin-top: 30px;'>";
	result += "<input type='file' id='uploadBtn'>영상 이름 설정 :";
	result += "<input type='text' id='videoName'>";
	result += "<button onclick='uploadFile()' class='btn-gradient'>업로드</button>";
	result += "</div>";
	$("#videoList").html(result);
}

function uploadFile() {
	const input = document.getElementById("uploadBtn");
	const videoName = document.getElementById("videoName")
	const file = input.files[0]; // 선택된 파일 가져오기

	if (file) {
		// 파일 업로드 처리 로직을 여기에 작성합니다.
		console.log("Selected file:", file);
		// 파일 업로드 로직을 작성할 수 있습니다.
		// 예를 들어, 서버로 파일을 전송하거나 다른 처리를 수행할 수 있습니다.
	} else {
		console.log("No file selected.");
	}
}