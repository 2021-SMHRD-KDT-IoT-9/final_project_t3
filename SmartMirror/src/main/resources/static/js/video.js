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
		result += "<span style='background-color:burlyWood' onclick='videoDelete(\"" + vo.video_name + "\")'>삭제하기❌</span>";
		result += "</div>";
		console.log(vo);
	});
	result += "<div style='margin-top: 30px;'>";
	result += "<input type='file' id='uploadBtn'>영상 이름 설정 :";
	result += "<input type='text' id='videoName'>";
	result += "<button onclick='uploadFile()' class='btn-gradient'>업로드</button>";
	result += "</div>";
	$("#videoList").html(result);
}

function videoDelete(name) {
	console.log("삭제 클릭", name)
	$.ajax({
		url: "videodelete",
		type: "get",
		data: { "name": name },
		success: function() {
			location.reload();
			console.log("통신 성공")
		},
		error: function() {
			alert("통신실패!")
		}
	})
}

function uploadFile() {
	const input = document.getElementById("uploadBtn");
	const videoName = document.getElementById("videoName").value;
	const file = input.files[0]; // 선택된 파일 가져오기
	if (file) {
		console.log("Selected file:", file);
		const formData = new FormData();
		formData.append("name", videoName);
		formData.append("file", file);
		$.ajax({
			url: "videoupload",
			type: "post",
			data: formData,
			contentType: false,
			processData: false,
			success: function() {
				location.reload();
				console.log("통신 성공")
			},
			error: function() {
				alert("통신실패!")
			}
		});
	} else {
		console.log("No file selected.");
	}
}