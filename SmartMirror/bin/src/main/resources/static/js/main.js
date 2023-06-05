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
   console.log("스타일 관리 성공", data)
   var result = "<div class='logoset'>";
   result += "<div id='styleList'>";
   $.each(data, (index, vo) => {
      result += "<div class='logostyle2'>";
      result += "<img src='/hairStyle/" + vo.hair_id +".jpg' width='200px' height='200px' />";
      result += "<br>";
      result += "<p>스타일명 : " + vo.style_name + "</p>";
      result += "<p>날짜 : " + vo.cut_dy + "</p>";
      result += "<p>노출여부 : " + vo.img_show + "</p>";
      result += "</a>";
      result += "<div class='setting'>";
      result += "<span>스타일명 :</span>";
      result += "<input type='text' id='style' /> <br>";
      result += "<span>노출여부 : </span>";
      result += "<input type='text' id='exposure' /> <br>";
      result += "<button onclick='submitSettings(this)'>수정완료</button>";
      result += "</div> </div></div>";
      result +="</div>";   
   });   
   $("#styleList").html(result);
}
// 등록 스타일 보기
function hair_saveyes(){
   $.ajax({
      url: "hairsaveyes",
      type: "post",
      dataType: "json",
      success: styleYesView,
      error: function() {
         alert("통신실패!")
      }
   })
}
// 등록 스타일 보기 view 
function styleYesView(data){
   console.log("등록 스타일 성공",data)
   var result = "<div class='logoset' id='hairyYesList'>";
   $.each(data,(index,vo)=>{
      result += "<div class='logostyle2'>";
      result += "<img src='/hairStyle/" + vo.hair_id +".jpg' width='200px' height='200px' /><br>";
      result += "<p>"+vo.style_name+"</p>";
      result += "<p>"+vo.cut_dy+"</p>";
      result += "</div>";
   });
   result += "</div>";
   $("#hairyYesList").html(result);
}



function submitSettings(button) {
   var settingDiv = button.parentNode;
   var styleInput = settingDiv.querySelector(".style-input");
   var exposureInput = settingDiv.querySelector(".exposure-input");
   // 여기에서 설정 값을 처리하거나 서버로 전송할 수 있습니다.
   }