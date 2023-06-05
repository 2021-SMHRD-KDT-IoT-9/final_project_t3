// 관리자 - 스타일 관리
function hair_Manager() {
	$.ajax({
		url: "hairmanager",
		type: "post",
		dataType: "json",
		success: styleView,
		error: function() {
			alert("통신실패!")
		}
	})
}

// 관리자 - 스타일 관리 view
function styleView(data) {
	console.log("스타일 관리 성공", data);
	var result = "<div class='logoset'>";
	result += "<div id='styleList'>";
	$.each(data, (index, vo) => {
		result += "<div class='logostyle2'>";
		result += "<img src='/hairStyle/" + vo.hair_id + ".jpg' width='200px' height='200px' />";
		result += "<br>";
		result += "<p>스타일명 : " + vo.style_name + "</p>";
		result += "<p>날짜 : " + vo.cut_dy + "</p>";
		result += "<p>노출여부 : " + vo.img_show + "</p>";
		result += "<div class='setting'>";
		result += "<span>스타일명 : </span>";
		result += "<input type='text' class='style-input' id='style" + index + "' /> <br>"; // ID에 인덱스 추가
		result += "<span>노출여부 : </span>";
		result += "<input type='text' class='exposure-input' id='exposure" + index + "' /> <br>"; // ID에 인덱스 추가
		result += "<button onclick='submitSettings(" + index + ",\"" + vo.hair_id + "\",\"" + vo.style_name + "\",\"" + vo.img_show + "\")'>수정완료</button>";
		result += "</div></div>";
	});
	result += "</div></div>";
	$("#styleList").html(result);
}

// 스타일 수정 
function submitSettings(index, id, styleName, exposure) {
	var styleInput = document.getElementById('style' + index);
	var exposureInput = document.getElementById('exposure' + index);

	var newStyleName = styleInput.value;
	var newExposure = exposureInput.value;

	console.log('순서:', index);
	console.log('변경된 스타일명:', newStyleName);
	console.log('변경된 노출여부:', newExposure);
	$.ajax({
		url: '/styleupload', // 서버의 업데이트 URL
		method: 'POST',
		data: {
			'id': id,
			'newStyleName': newStyleName,
			'newExposure': newExposure
		},
		success: function() {
			console.log("수정 성공");
			location.reload();
		}
		,
		error: function() {
			alert("통신실패!")
		}
	});
}

// 등록 스타일 보기
function hair_saveyes() {
	$.ajax({
		url: "hairsaveyes",
		type: "post",
		dataType: "json",
		success: styleYesView,
		error: function() {
			alert("통신실패!")
		}
	})
<<<<<<< HEAD
=======
}
// 등록 스타일 보기 view  
function styleYesView(data) {
	console.log("등록 스타일 성공", data)
	var result = "<div class='logoset' id='hairyYesList'>";
	$.each(data, (index, vo) => {
		result += "<div class='logostyle3'>";
		result += "<img src='/hairStyle/" + vo.hair_id + ".jpg' width='200px' height='200px' /><br>";
		result += "<p>" + vo.style_name + "</p>";
		result += "<p>" + vo.cut_dy + "</p>";
		result += "</div>";
	});
	result += "</div>";
	$("#hairyYesList").html(result);
>>>>>>> branch 'back' of https://github.com/2021-SMHRD-KDT-IoT-9/final_project_t3.git
}

<<<<<<< HEAD
// 등록 스타일 보기 view  
function styleYesView(data) {
	console.log("등록 스타일 성공", data)
	var result = "<div class='logoset' id='hairyYesList'>";
	$.each(data, (index, vo) => {
		result += "<div class='logostyle3'>";
		result += "<img src='/hairStyle/" + vo.hair_id + ".jpg' width='200px' height='200px' /><br>";
		result += "<p>" + vo.style_name + "</p>";
		result += "<p>" + vo.cut_dy + "</p>";
		result += "</div>";
	});
	result += "</div>";
	$("#hairyYesList").html(result);
}
=======


>>>>>>> branch 'back' of https://github.com/2021-SMHRD-KDT-IoT-9/final_project_t3.git
