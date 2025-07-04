<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="eco" uri="www.ecoinsight.co.kr/tag" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<link type="text/css" href="${pageContext.request.contextPath}/css/target/target_basic.css" rel="stylesheet">
<link type="text/css" href="${pageContext.request.contextPath}/css/target/target_style.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/target/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/target/lib_jquery.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<style>
html, body {
 location:no;
 overflow: hidden; }
 
.span-red{
	color : red;	
} *
.boardwrite_type1 table td textarea {
    height: 53px;
}
.boardwrite_type1 table th.txt_height span {
	line-height: 60px;
}
.boardwrite_type1 table th.txt_height .req {
	top : 27px;
}

</style>
<script type="text/javascript">




/* html 최초 로드 및 이벤트 상시 대기 실시 
window.onload = function() {
	console.log("[window onload] : [start]");
	console.log("");
	
	// 브라우저 닫기 이벤트 감지 실시 [1]
	window.addEventListener("beforeunload", function (event) {
		console.log("[window beforeunload] : [start]");
		console.log("");        		
		
		event.returnValue = "브라우저를 종료하시겠습니까?";    			
	});    		
	
};

window.addEventListener('beforeunload', (event) => {
    // 명세에 따라 preventDefault는 호출해야하며, 기본 동작을 방지합니다.
    event.preventDefault();

    // 대표적으로 Chrome에서는 returnValue 설정이 필요합니다.
    event.returnValue = '';
});


	/*$("#address_btn").click( function() { 
		
		goPopup("sub00_02_popup.html", "address_poppup", '800', 600); 
		
		});
	*/
	
    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
   
</script>
	
	



<body>
<div id="wrap">
<!-- mobile header start 
<div id="header">
	<h1><a href="#"><img src="mimg/sub00_tit.png" width="180" height="48" alt="대상자 관리" /></a></h1>
</div>
-->
<!-- mobile header end -->
<hr />
 
<!-- mobile body start -->
<form method='post' name='frm' id='frm' action='<%=request.getContextPath()%>/include/target/csbr1010_Insert_ObjIdx.do'>

<input type='hidden' name='mode' value=''>
<input type='hidden' name='object_idx' value=''>
<input type='hidden' id='cInput' value='${success}'>


<div class="tableWrap">
	<h2><img src='${pageContext.request.contextPath}/images/target/tit_new.png' style="position: relative; top: -1.3em;" width="155" height="65" alt="신규" /></h2>
	<table summary="대상자 관리 신규" style="position: relative; top: -1.6em;">
		<tbody>
			<tr>
				<th scope="row">이 름</th>
				<td class="tdLeft"><input name="name" id ="name" type="text" class="inputStyle02" value="" /></td>
				<th scope="row">ChartNo</th>
				<td class="tdLeft"><input name="chartno" type="text" class="inputStyle02" value="" /></td>
			</tr>
			<tr>
				<th scope="row">주민번호</th>
				<td class="tdLeft"><input name="jumin1" type="text" value="" class="inputStyle09 inputmode1" maxlength='6' pattern='[0-9]*' />
				<p class="inputSpace">-</p><input name="jumin2" type="text" value="" class="inputStyle09 inputmode1" maxlength='4' pattern='[0-9]*' />
				<p class="inputSpace">-</p><input name="jumin3" type="text" value="***" class="inputStyle09 inputmode1" maxlength='3' readonly /></td>
				<th scope="row">실제 생년월</th>
				<td class="tdLeft" colspan="3"><input name="birth1" type="text" value="" class="inputStyle09 inputmode1" maxlength='4' pattern='[0-9]*' />
				<p class="inputSpace">/</p><input name="birth2" type="text" value="" class="inputStyle09 inputmode1" maxlength='2' pattern='[0-9]*' />
				<p class="inputSpace">/</p><input name="birth3" type="text" value="" class="inputStyle09 inputmode1" maxlength='2' pattern='[0-9]*' /></td>
			</tr>
			<tr>
				<th scope="row">성별</th>
				<td class="tdLeft width30">
					<select name="gender" class="selectStyle02">
						<option>선택
						<option value='1' >남자</option>
						<option value='2' >여자</option>
					</select>
				</td>
				<th scope="row" class="width20">나 이</th>
				<td class="tdLeft width30" colspan="3"><input name="age" type="text" class="inputStyle02 inputmode1" value="" maxlength='3' pattern='[0-9]*' readonly /></td>
			</tr>
			<tr>
				<th scope="row">띠</th>
				<td class="tdLeft">
					<select name="year_ani" class="selectStyle05">
						<option>선택
						<option value='1' >쥐 (자)</option>
						<option value='2' >소 (축)</option>
						<option value='3' >호랑이 (인)</option>
					    <option value='4' >토끼 (묘)</option>
					    <option value='5' >용 (진)</option>
					    <option value='6' >뱀 (사)</option>
					    <option value='7' >말 (오)</option>
					    <option value='8' >양 (미)</option>
					    <option value='9' >원숭이 (신)</option>
					    <option value='10'>닭 (유)</option>
					    <option value='11'>개 (술)</option>
					    <option value='12'>돼지 (해)</option>
					</select>
				</td>
				<th scope="row">교육받은 기간</th>
				<td class="tdLeft" colspan="3">
					<select name="edu" class="selectStyle06">
						<option>선택
						<option value='99'>무학</option>
						<option value='8'>초등학교졸업</option>
						<option value='11'>중학교졸업</option>
						<option value='14'>고등학교졸업</option> 
						<option value='15'>대학교2년중퇴</option>  
						<option value='18'>대학교졸업</option> 
						<option value='20'>대학원 졸업 이상</option> 
			
					</select>
				</td>
			</tr>
			<tr>
				<th scope="row">집전화</th>
				<td class="tdLeft"><input name="tel1" type="text" value="" class="inputStyle09 inputmode1" maxlength="3" pattern='[0-9]*' />
				<p class="inputSpace">-</p><input name="tel2" type="text" value="" class="inputStyle09 inputmode1" maxlength="4" pattern='[0-9]*' />
				<p class="inputSpace">-</p><input name="tel3" type="text" value="" class="inputStyle09 inputmode1" maxlength="4" pattern='[0-9]*' /></td>
				<th scope="row">휴대전화</th>
				<td class="tdLeft" colspan="3"><input name="mobile1" type="text" value="" class="inputStyle09 inputmode1" maxlength="3" pattern='[0-9]*' />
				<p class="inputSpace">-</p><input name="mobile2" type="text" value="" class="inputStyle09 inputmode1" maxlength="4" pattern='[0-9]*' />
				<p class="inputSpace">-</p><input name="mobile3" type="text" value="" class="inputStyle09 inputmode1" maxlength="4" pattern='[0-9]*' /></td>
			</tr>
			<tr>
				<th scope="row">주소</th>
				<td class="tdLeft" colspan="3">
			<p class="inputtopSpace">
			<input type="text" id="sample4_postcode" class="inputStyle03" placeholder="우편번호">
			<input type="button" onclick="sample4_execDaumPostcode();" class="inputBtn01" value="주소검색">
			</p>
			
			<input type="text" name="sample4_sido" id="sample4_sido" class="inputStyle04" placeholder="시도">
			<input type="text" name="sample4_sigungu" id="sample4_sigungu" class="inputStyle04" placeholder="시/군/구" >
			<input type="text" name="sample4_roadAddress" id="sample4_roadAddress" class="inputStyle04" placeholder="도로명주소" >
			<input type="hidden" name="sample4_jibunAddress" id="sample4_jibunAddress" class="inputStyle04" placeholder="지번주소" >
			<span id="guide" style="color:#999; display:none; color:ffff;"></span>
			<input type="text" name="sample4_detailAddress" id="sample4_detailAddress" placeholder="상세주소" class="inputStyle05">
			<input type="hidden" id="sample4_extraAddress" placeholder="참고항목">
			<input type="hidden" id="sample4_engAddress" placeholder="영문주소">
	
			
			<!--  
				<input name="address1" type="text" value="" class="inputStyle03" readonly="readonly" />
				<input name="address2" type="text" value="" class="inputStyle03" readonly="readonly" />
				<input name="address3" type="text" value="" class="inputStyle04" readonly="readonly" />
				<p class="inputtopSpace"><input name="address4" type="text" value="" class="inputStyle05" />
				<input type='button'id='address_btn' class="inputBtn01" onclick="sample4_execDaumPostcode()" value='주소검색'></p>
			-->

				</td>
			</tr>
		</tbody>
	</table>
	<div id="btnWrap" style="top: -0.8em;"><p id='next_btn' class="btnStyle02" style="background:#073256; "</p>	저장 </div>
</div>
</form>


<!-- mobile body end -->
<hr />


</div>
<script type="text/javascript">



var cvalues = document.getElementById("cInput").value;
var name = document.getElementById("name").value;
//console.log(window.opener.document.getElementById("pInput").value);
console.log(cvalues+"::::cvalues값");
console.log(name+"::::name값");


var wclose;


/* html 최초 로드 및 이벤트 상시 대기 실시 
window.onload = function() {
	console.log("[window onload] : [start]");
	console.log("");
	
	// 브라우저 닫기 이벤트 감지 실시 [1]
	window.addEventListener("beforeunload", function (event) {
		console.log("[window beforeunload] : [start]");
		console.log("");        		
		
		event.returnValue = "브라우저를 종료하시겠습니까?";    			
	});    		
	
};
*/
/*
window.addEventListener('beforeunload', (event) => { 
    // 명세에 따라 preventDefault는 호출해야하며, 기본 동작을 방지합니다.
    event.preventDefault();

    // 대표적으로 Chrome에서는 returnValue 설정이 필요합니다.
    event.returnValue = '';
});
*/
//$(window).off('beforeunload.windowReload');

//window.off('beforeunload');

 const listener = (event) => {   
	 
	
	 
				event.preventDefault();
				event.returnValue = '';
			}


		

$(function() {

	$("#next_btn").click( function() { 
		
		submitOn(); 

		});
	
	
	var passMessage = "<c:out value ="${pass}" />"
	
	console.log(passMessage+"::::::::::::::message")		
	
	if(passMessage == "true"){
		alert("등록이 성공적으로 완료하였습니다.")

		window.removeEventListener("beforeunload", listener);	
		
		  setTimeout(function() {
				//var passMessage = "<c:out value ="${pass}" />"
			  self.close();
		window.removeEventListener("beforeunload", listener);	
		   },100);
		
	}else if(passMessage == "false"){
		
		alert("등록할 대상자가 이미 존재합니다!");	
		window.removeEventListener("beforeunload", listener);	
		
		  setTimeout(function() {
				//var passMessage = "<c:out value ="${pass}" />"
			  self.close();
		window.removeEventListener("beforeunload", listener);	
		   },100);
	}

/*
	var  message = "${pass}";
	
	console.log(message+"::::::::::::::message")		
	
	if(message == "true"){
		alert("등록이 성공적으로 완료하였습니다.")
		
	}else if(message == "false"){
		
		alert("등록할 대상자가 이미 존재합니다!");	
	}
*/
  

	
	 if($("input[name='name']").val() == null || $("input[name='jumin1']").val() == null || $("input[name='jumin2']").val() == null 
		||$("input[name='jumin3']").val() == null || $("input[name='birth1']").val() == null || $("input[name='birth2']").val() == null
		|| $("input[name='birth3']").val() == null || $("input[name='gender']").val() == null || $("input[name='age']").val() == null
		|| $("select[name='year_ani']").val() == null || $("input[name='edu']").val() == null || $("input[name='address1']").val() == null 
		){
	
/*		 

		 window.addEventListener('beforeunload', (event) => { 
		     // 명세에 따라 preventDefault는 호출해야하며, 기본 동작을 방지합니다.
		     event.preventDefault();

		     // 대표적으로 Chrome에서는 returnValue 설정이 필요합니다.
		     event.returnValue = '';
		 });

*/		 


			window.addEventListener("beforeunload", listener);

	
			
			 }



$("input[name='birth1']").keyup(
		function() {
  			var now = new Date();
  			var year = now.getFullYear();
  			console.log("연도: "+year);
			
			$(this).val( $(this).val().replace(/[^0-9]/gi,"") );

			if($(this).val().length >=4 ) 
			{ 
				$("input[name='age']").val( year - $("input[name='birth1']").val() );
				$("input[name='birth2']").focus(); 
			}
		}
	);

$("input[name='birth2']").keyup(
		function() {

			$(this).val( $(this).val().replace(/[^0-9]/gi,"") );

			if($(this).val().length >=2 ) 
			{

				$("input[name='birth3']").focus(); 
			}
		}
	);
	$("input[name='birth3']").keyup(
		function() {

			$(this).val( $(this).val().replace(/[^0-9]/gi,"") );

			if($(this).val().length >=2 ) 
			{

				$("select[name='gender']").focus(); 
			}
		}
	);

	$("input[name='tel1']").keyup(
			function() {

				$(this).val( $(this).val().replace(/[^0-9]/gi,"") );

				if($(this).val().length >=3 ) { $("input[name='tel2']").focus(); }
			}
		);
		$("input[name='tel2']").keyup(
			function() {

				$(this).val( $(this).val().replace(/[^0-9]/gi,"") );

				if($(this).val().length >=4 ) { $("input[name='tel3']").focus(); }
			}
		);
		$("input[name='tel3']").keyup(
			function() {

				$(this).val( $(this).val().replace(/[^0-9]/gi,"") );

				if($(this).val().length >=4 ) { $("input[name='mobile1']").focus(); }
			}
		);

		$("input[name='mobile1']").keyup(
			function() {

				$(this).val( $(this).val().replace(/[^0-9]/gi,"") );

				if($(this).val().length >=3 ) { $("input[name='mobile2']").focus(); }
			}
		);
		$("input[name='mobile2']").keyup(
			function() {

				$(this).val( $(this).val().replace(/[^0-9]/gi,"") );

				if($(this).val().length >=4 ) { $("input[name='mobile3']").focus(); }
			}
		);
		$("input[name='mobile3']").keyup(
			function() {

				$(this).val( $(this).val().replace(/[^0-9]/gi,"") );

				if($(this).val().length >=4 ) { $("input[name='sample4_sido']").focus(); }
			}
		);


		$("input[name='age']").keyup(
			function() {

				$(this).val( $(this).val().replace(/[^0-9]/gi,"") );

			}
		);
	});



function submitOn(){

	var msg = " 항목을 선택하세요!.";                
	var msg2 = " 항목을 입력하세요!.";               


	if(!check_field("name", "text")) { alert("이름"+msg2); return; }                    
	if(!check_field("jumin1", "text")) { alert("주민번호"+msg2); return; }                
	if(!check_field("jumin2", "text")) { alert("주민번호"+msg2); return; }                
	                                                                                  
	if(!check_field("birth1", "text")) { alert("실제 생년월"+msg2); return; }              
	if(!check_field("birth2", "text")) { alert("실제 생년월"+msg2); return; }              
	if(!check_field("birth3", "text")) { alert("실제 생년월"+msg2); return; }              
	                                                                                  
	if(!check_field("gender", "select")) { alert("성별"+msg); return; }                 
	                                                                                  
	if(!check_field("age", "text")) { alert("나이"+msg2); return; }                     
	                                                                                  
	if(!check_field("year_ani", "select")) { alert("띠"+msg); return; }                
	if(!check_field("edu", "select")) { alert("교육받은 기긴"+msg); return; }               
	                                                                                  
	//if(!check_field("sample4_sido", "text")) { alert("주소 검색을 이용하여 주소를 입력하세요."); return; } 
	//if(!check_field("'sample4_sigungu", "text")) { alert("주소 검색을 이용하여 주소를 입력하세요."); return; } 
	//if(!check_field("sample4_roadAddress", "text")) { alert("주소 검색을 이용하여 주소를 입력하세요."); return; } 

	//띠 확인
	var by = $("input[name='birth1']").val();                                                           
	var ani = (by-1900+1) % 12;                                                                         
	                                                                                                    
	if(ani == 0) ani=12;                                                                                
	                                                                                                    
	var year_ani = $("select[name='year_ani']").val();                                                  
	                                                                                                    
	if(ani != year_ani) { alert("실제 생년월과 띠가 틀립니다."); $("select[name='year_ani']").val(ani); return; }   
	//else                alert("띠 맞음");

	console.log($("input[name='name']").val()+"name value");
	console.log($("select[name='year_ani']").val()+"yaer_ani value");
	

	var checking;
	 if(!($("input[name='name']").val() == null && $("input[name='jumin1']").val() == null && $("input[name='jumin2']").val() == null &&
		$("input[name='jumin3']").val() == null && $("input[name='birth1']").val() == null && $("input[name='birth2']").val() == null
		&& $("input[name='birth3']").val() == null && $("input[name='gender']").val() == null && $("input[name='age']").val() == null
		&& $("select[name='year_ani']").val() == null && $("input[name='edu']").val() == null && $("input[name='address1']").val() == null 
	 )){
	
	
		 

		 
		 
		 

			console.log("들어왔다")


			//	document.frm.action = "${pageContext.request.contextPath}/view_savefamily.do";
		document.frm.submit();
		


	//		window.onbeforeunload == null;
	
					  /*
			  setTimeout(function() {
				self.close();

					   },500);  
				*/
	 }
	 
	 /*else{
		 checking = false;
		 
		 if(checking ==false){
			 window.onload = function() {
					console.log("[window onload] : [start]");
					console.log("");
					
					// 브라우저 닫기 이벤트 감지 실시 [1]
					window.addEventListener("beforeunload", function (event) {
						console.log("[window beforeunload] : [start]");
						console.log("");        		
						
						event.returnValue = "브라우저를 종료하시겠습니까?";    			
					});    		
					
				}; 			 
			 
		 }

	 }
	*/		
			
					
	/*	 
	    setTimeout(function() {   
	    	window.onbeforeunload = null;
	    	self.close();
	 		alert("time에 들어왔다")
	    	window.open("about:blank","_self").close();
	     },100);
	*/



	}



function sample4_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.

            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 참고 항목 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
               extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraRoadAddr !== ''){
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample4_sido').value = data.sido;
            document.getElementById('sample4_sigungu').value = data.sigungu;
            document.getElementById('sample4_postcode').value = data.zonecode;
            document.getElementById("sample4_roadAddress").value = roadAddr;
            document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
            
            // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
            if(roadAddr !== ''){
                document.getElementById("sample4_extraAddress").value = extraRoadAddr;
            } else {
                document.getElementById("sample4_extraAddress").value = '';
            }

            var guideTextBox = document.getElementById("guide");
            // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
            if(data.autoRoadAddress) {
                var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                guideTextBox.style.display = 'block';

            } else if(data.autoJibunAddress) {
                var expJibunAddr = data.autoJibunAddress;
                guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                guideTextBox.style.display = 'none';
            } else {
                guideTextBox.innerHTML = '';
                guideTextBox.style.display = 'none';
            }
        }
    }).open();

}

</script>

</body>
