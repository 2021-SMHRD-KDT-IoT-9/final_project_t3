// 관리자 - 이미지
function image_Manager() {
	$.ajax({
		url: "imagemanager",
		type: "post",
		dataType: "json",
		success: imgView,
		error: function() {
			alert("통신실패!")
		}
	})
}

function imgView(data) {
	console.log("이미지 성공", data)
	var result = "<div class='logoset' id='imgList'>";
	$.each(data, (index, vo) => {
		result += "<div class='logostyle'>";
		result += "<img src='/uploadimg/" + vo.img_id + ".jpg' width='146px' height='81px'/>";
		result += "<p>" + vo.img_name + "</p>";
		result += "<span style='background-color:burlyWood' onclick='imgDelete(\"" + vo.img_id + "\")'>삭제하기❌</span>";
		result += "</div>";
	});
	result += "<div style='margin-top: 30px;'>";
	result += "<input type='file' id='uploadBtn'>이미지 이름 설정:";
	result += "<input type='text' id='imgName'>";
	result += "<button onclick='uploadFile()' class='btn-gradient'>업로드</button>";
	result += "</div>";
	result += "</div>";
	$("#imgList").html(result);
}
function imgDelete(id){
	console.log(name)
	$.ajax({
		url: "imgdelete",
		type: "get",
		data: {"id": id },
		success: function() {
			console.log("통신 성공")
			location.reload();
		},
		error: function() {
			alert("통신실패!")
		}
	})
}


function uploadFile() {
	const input = document.getElementById("uploadBtn");
	const imgName = document.getElementById("imgName").value;
	const file = input.files[0]; // 선택된 파일 가져오기
	if (file) {
		console.log("Selected file:", file);
		const formData = new FormData();
		formData.append("name", imgName);
		formData.append("file", file);
		$.ajax({
			url: "imgupload",
			type: "post",
			data: formData,
			contentType: false,
			processData: false,
			success: function() {
				console.log("통신 성공")
				location.reload();
			},
			error: function() {
				alert("통신실패!")
			}
		})
	} else {
		console.log("No file selected.");
	}
}