<%@page import="java.sql.Date"%>
<%@page import="csbrain.common.service.MbKmmse2VO"%>
<%@page import="csbrain.common.util.DateUtil"%>
<%@page import="csbrain.common.util.StringUtil"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/common.inc"%>
<jsp:include page='<%=incInit%>' flush="false"></jsp:include>
<%
	String sdate = request.getParameter("sdate");
	String exam_num =request.getParameter("exam_num");
	//if ( StringUtil.isEmptyString(edate) ) edate = DateUtil.getTodayByFormat("yyyy-MM-dd");


	List<MbKmmse2VO> kmmse2_list = (List<MbKmmse2VO>) request.getAttribute("kmmse2_list");
	MbKmmse2VO kmmse2vo = null;
//	MbC3VO c3vo = null;

	if(kmmse2_list != null && kmmse2_list.size() > 0 ){
		kmmse2vo =kmmse2_list.get(0);
		
		
	}
//	if( c3_list != null && c3_list.size() > 0 ){
//		c3vo = c3_list.get(0);
//	}

%>

<script>
	$(function(){
		print_head("");

		check_login(); //로그인체크

	});
</script>
<style>
	.rbox {
		border:0px solid red;
		position:absolute;
	
	}
	/* .r {
		position:absolute;
	} */
	.t {
		/* position:absolute !important;
		margin:0px !important;
		border:2px solid  rgb(96, 157, 190) !important;
		border-radius: 5px !important;
		text-align:left;
		font-weight:normal;
		height:25px;
		overflow:hidden;
		background-color:transparent  !important;
		color:blue !important;
		box-sizing: border-box;
		padding-left:1px; */
		width: 60px;
		
	}
	.tn {
		/* position:absolute !important;
		margin:0px !important;
		border:2px solid  rgb(96, 157, 190) !important;
		border-radius: 5px !important;
		text-align:left;
		font-weight:normal;
		height:25px;
		overflow:hidden;
		background-color:transparent  !important;
		color:blue !important;
		box-sizing: border-box;
		padding-left:1px; */
		width: 60px;
		
	}	
	
	
		#tabbox {

 /*	background-size: cover;
	*/
	width:608px;
	height: 1850px;;
	background-repeat: no-repeat;
	/*
  	background-color: #e0e0e0;
	*/
	}	
	

.csbr1010btn {
	box-shadow:inset 0px 1px 0px 0px #ffffff;
	background:linear-gradient(to bottom, #f9f9f9 5%, #e9e9e9 100%);
	background-color:#f9f9f9;
	border-radius:6px;
	border:1px solid #dcdcdc;
	display:inline-block;
	cursor:pointer;
	color:#666666;
	font-family:Arial;
	font-size:12px;
	font-weight:bold;
	margin: 3px 10px 0px 0px;
	float: right;
	width: 65px;
	height: 28px;
	text-decoration:none;
	text-shadow:0px 1px 0px #ffffff;
}
.csbr1010btn:hover {
	background:linear-gradient(to bottom, #e9e9e9 5%, #f9f9f9 100%);
	background-color:#e9e9e9;
}
.csbr1010btn:active {
	position:relative;
	top:1px;
}
	
.btn_wrap {margin-right:13px;}
	
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.js"></script>
<script>
	$(function(){
	/*
		$("input").click(function(){
			return false;
		});
		
	*/
	var idx = $('#idx').val();
		console.log(idx);
		
		
		var action = $('#action').val();
		if(action =='hidden'){
		     $('#btnArea').css({"visibility": "hidden"});
		}
		
		$("#new").click(function() {
			if(idx == ''){
				alert("검색항목을 입력해주세요");
				return false;
	
			}
			var getexmN = $("#exnum").val();
			
			
			if($('#insert').css("display")=="none"){
				$('#insert').show();	
			}
		$('input:text[name="sdate"]').attr("disabled", false);
		$('input:radio[class="r"]').attr("disabled", false);
		$('input:text[class="t"]').attr("disabled", false);
		//$('input:text[name="ddate"]').attr("disabled", false);
		 $('input[class="r"]').removeAttr('checked');
		 $('input[class="t"]').val('');
		 $('input[class="tn"]').val('');
		 $('input[name="sdate"]').val('');
	//	$('input:radio[class="r"]:not(:checked)').attr("disabled", false);
	//	$('input:text[class="t"]').attr("disabled", false);	
	//   	 $(this).prop('checked', false);
		

			
	   	 	var selnew = $("#new").val(); 
	   	 	console.log(selnew+"::selenew")
			checking = $('#chk').val(selnew);
		});
	
		$("#edit").click(function() {
			if(idx == ''){
				alert("검색항목을 입력해주세요");
				return false;
			
			}
		
			
			if($('#insert').css("display")=="none"){
				$('#insert').show();	
			}
		$('input:text[name="sdate"]').attr("disabled", false);
		console.log($(".t").attr("disabled"));
		$('input:radio[class="r"]:not(:checked)').attr("disabled", false);
		$('input:text[class="t"]').attr("disabled", false);	
	   	 $(this).prop('checked', false);
		
			
	   	 	var selnew = $("#edit").val(); 
	   	 	console.log(selnew+"::selenew")
			checking = $('#chk').val(selnew);
		});
	

		
	
	$('input:text[id="num"]').keyup(function() {
		var regNumber = /^[0-9]*$/;
		var input = $(this).val();
		var maxLimit = $(this).attr("maxlength"); 
		var minLimit = $(this).attr("minlength");
		if(!regNumber.test(input)){
	        alert("숫자만 입력가능합니다");
			$('input:text[id="num"]').val(input.replace(/[^0-9]/g , ""));
			}
		if(this.value.length >= maxLimit){	
			$(this).next('input:text[id="num"]').focus();
			var add = 0;
			var addN = 0;
			
			
			
			if($('input:text[name="ts"]').is(':focus')){
				for(let i =5; i<10; i++){					
					var n = $('input:text[name="t'+i+'"]').val();			 				
					addN +=  Number(n);
					
				}
				$('input:text[name="ts"]').val(addN);
				//$('input:text[name="tts"]').val(addN);
			}
			if($('input:text[name="tts"]').is(':focus')){
				for(let i =0; i<11; i++){					
					var n = $('input:text[name="t'+i+'"]').val();			 				
					addN +=  Number(n);
					
				}

				$('input:text[name="tts"]').val(addN);
			}else if($('input:text[name="t0"]').is(':focus')){
				for(let i =0; i<5; i++){					
					var n = $('input:text[name="t'+i+'"]').val();			 				
					add +=  Number(n);
					
				}
				$('input:text[name="tts"]').val(add);
			}else if($('input:text[name="t1"]').is(':focus')){
				for(let i =0; i<5; i++){					
					var n = $('input:text[name="t'+i+'"]').val();			 				
					add +=  Number(n);
					
				}
				$('input:text[name="tts"]').val(add);
			}else if($('input:text[name="t2"]').is(':focus')){
				for(let i =0; i<5; i++){					
					var n = $('input:text[name="t'+i+'"]').val();			 				
					add +=  Number(n);
					
				}
				$('input:text[name="tts"]').val(add);
			}else if($('input:text[name="t3"]').is(':focus')){
				for(let i =0; i<5; i++){					
					var n = $('input:text[name="t'+i+'"]').val();			 				
					add +=  Number(n);
					
				}
				$('input:text[name="tts"]').val(add);
			}else if($('input:text[name="t4"]').is(':focus')){
				for(let i =0; i<5; i++){					
					var n = $('input:text[name="t'+i+'"]').val();			 				
					add +=  Number(n);
					
				}
				$('input:text[name="tts"]').val(add);
			}else if($('input:text[name="t5"]').is(':focus')){
				for(let i =0; i<11; i++){					
					var n = $('input:text[name="t'+i+'"]').val();			 				
					add +=  Number(n);
					
				}
				$('input:text[name="tts"]').val(add);
				
				for(let i =5; i<10; i++){					
					var n = $('input:text[name="t'+i+'"]').val();			 				
					addN +=  Number(n);
					
				}
				$('input:text[name="ts"]').val(addN);
				
				
				//$('input:text[name="ts"]').val(addN);
//				$('input:text[name="ts"]').val(addN);
//				$('input:text[name="tts"]').val((add+addN));
	
			}
			else if($('input:text[name="t6"]').is(':focus')){
				for(let i =0; i<11; i++){					
					var n = $('input:text[name="t'+i+'"]').val();			 				
					add +=  Number(n);
					
				}
				$('input:text[name="tts"]').val(add);
				
				for(let i =5; i<10; i++){					
					var n = $('input:text[name="t'+i+'"]').val();			 				
					addN +=  Number(n);
					
				}
				$('input:text[name="ts"]').val(addN);
			}
			else if($('input:text[name="t7"]').is(':focus')){
				for(let i =0; i<11; i++){					
					var n = $('input:text[name="t'+i+'"]').val();			 				
					add +=  Number(n);
					
				}
				$('input:text[name="tts"]').val(add);
				
				for(let i =5; i<10; i++){					
					var n = $('input:text[name="t'+i+'"]').val();			 				
					addN +=  Number(n);
					
				}
				$('input:text[name="ts"]').val(addN);
				
			}else if($('input:text[name="t8"]').is(':focus')){
				for(let i =0; i<11; i++){					
					var n = $('input:text[name="t'+i+'"]').val();			 				
					add +=  Number(n);
					
				}
				$('input:text[name="tts"]').val(add);
				
				for(let i =5; i<10; i++){					
					var n = $('input:text[name="t'+i+'"]').val();			 				
					addN +=  Number(n);
					
				}
				$('input:text[name="ts"]').val(addN);
				
			}else if($('input:text[name="t9"]').is(':focus')){
				for(let i =0; i<11; i++){					
					var n = $('input:text[name="t'+i+'"]').val();			 				
					add +=  Number(n);
					
				}
				$('input:text[name="tts"]').val(add);
				
				for(let i =5; i<10; i++){					
					var n = $('input:text[name="t'+i+'"]').val();			 				
					addN +=  Number(n);
					
				}
				$('input:text[name="ts"]').val(addN);
				
			}else if($('input:text[name="t10"]').is(':focus')){
				for(let i =0; i<11; i++){					
					var n = $('input:text[name="t'+i+'"]').val();			 				
					add +=  Number(n);
					
				}
				$('input:text[name="tts"]').val(add);
				
				for(let i =5; i<10; i++){					
					var n = $('input:text[name="t'+i+'"]').val();			 				
					addN +=  Number(n);
					
				}
				$('input:text[name="ts"]').val(addN);
			}
	
	
			return false;
		}else if(this.value.length < minLimit){
			$(this).focus();
			return false;
		}
	
	
		
		
		});
	
	
	
	
	$('#insert').click(function() {
		
		//배열선언

		var radioArr = [];
		var textArr = [];
		var sdates = $("#sdate").val();
		var exam_num= $("#selectionNum").val();
		var exam_idx= $("#examIdx").val(); 
		var exam_id= $("#exid").val();
		var ts =$('input:text[name="ts"]').val();
		var tts =$('input:text[name="tts"]').val();
		console.log("exam_num::"+exam_num)
		console.log("exam_idx::"+exam_idx)
		console.log("설문실행일:"+sdates);
		console.log("6번점수:"+ts);
		console.log("총점수:"+tts);
		var whichChk = $("#chk").val();
		console.log(whichChk+":::whichChk")
		var cdbs = $("#cdbs").val();
		
		if(sdates == ''){
			alert("설문실행일을 체크해주세요!")
			return false;
		}

		
		
		
		
	
		var value1;
		$('input:text[class="t"]').each(function(i) {
			
			value1 =$(this).val();
			
			
			
	
			
			
			
			if($('input:text[name="t'+i+'"]').val() == ''){
				alert("체크되지 않는 입력값이 있습니다!")
			 return false;
			}else{
				textArr.push(value1);
			}
			
			console.log(value1+":[음주 텍스트]values1["+i+"]");
			//textArr.push(value1);
		});
		
		
		
		
		var num;
		var num1;
		 num = $('input:radio[class="r"]:checked').length;
 		console.log(num+"저장된체크넘버");
 		num1 = $('input:radio[class="r"]:not(:checked)').length;
		

		var objParams = {
			"textArr" : textArr,	
			"sdate" : sdates,  
			"idx" : idx,
			"whichChk" : whichChk,
			"exam_id" : exam_id,
			"exam_num" : exam_num,
			"exam_idx" : exam_idx,
			"ts" : ts,
			"tts" : tts,
			"cdbs" : cdbs
	
		};
		
		//ajax호출
		$.ajax({
			url         :   getContextPath()+"/include/sheet/view_savekmmse2.do",
            dataType    :   "json",
            contentType :   "application/x-www-form-urlencoded; charset=UTF-8",
            type        :   "post",
            data        :   objParams,
            success     :   function(json){
       			if(json.model.code =="OK"){
       				var obj = json.model.objIdx;
       				var examN = json.model.exam_num;
       				var flag = false;
       				if(json.model.result=="new"){
       					alert(json.model.message);
            			   flag = true;
            			   parent.load_sunbyul(flag);
       				}else if(json.model.result=="edit"){
       					alert(json.model.message);
       					flag = true;
         			    parent.load_sunbyul(flag);
       				}
       			}else{
       				alert("등록을 실패하였습니다 다시시도해주세요!")
       			} 		
            },
            error       :   function(request, status, error){
                console.log("AJAX_ERROR");
            }
	
		});
		
	});
	
	


	var sdateVal = $('input:text[name="sdate"]').attr('value')
	 var date = $("calendar").val();
	console.log(date);
	console.log("sdateVal앞::"+sdateVal);

	// $('input:radio[name="r2"]').attr("disabled", true); //비활성화
	 $('input:text[class="t"]').attr("disabled",true);		
	 
	 $('input:text[name="sdate"]').attr("disabled", true);
		
		
	});
</script>
<body>

<div id="btnArea" class="btn_wrap top">
	<input type ="hidden" id="action" value="${action}" style="display:none;"/>
	<!-- <button name="저장" id="insert" class ="csbr1010btn" style="display:none;">저장</button> -->
	<button name="수정" id="edit" class ="csbr1010btn"  value="edit">수정</button>
	<button name="신규" id ="new" class ="csbr1010btn"  value="new">신규</button>

	<input type="hidden" id ="object_idx" style="display:none;" class ="object_idx" value='<%= kmmse2vo == null ? "" :StringUtil.NVL  (kmmse2vo.getObjectIdx())%>'>
	<input type='hidden' name='selectionNum' id="selectionNum"  value='<%= kmmse2vo == null ? "" :StringUtil.NVL(kmmse2vo.getSelectionNum())%>'>
	<input type='hidden' name='examIdx' id="examIdx"  value='<%= kmmse2vo == null ? "" :StringUtil.NVL(kmmse2vo.getIdx())%>'>
	<input name="idx" id ="idx" class ="idx" value="${idx}" style="display:none;"/>
	<input type ="hidden" name="exid" id ="exid" class ="exid" value="${exam_id}" style="display:none;"/>
	<input type ="hidden" name="exnum" id ="exnum" class ="exnum" value="${exam_num}" style="display:none;"/>
	<input type ="hidden" name="cdbs" id ="cdbs" class ="cdbs" value="0104" style="display:none;"/>
	<input type ="hidden" name="chk" id ="chk" class ="chk" value="" style="display:none;"/>
</div>

<div id="tabbox" style="height:auto;">

	<table class="table type02">
        <colgroup>
            <col width="20%" />
            <col width="25%" />
            <col width="30%" />
            <col width="25%" />
        </colgroup>
        <thead>
            <tr>
                <th class="bg1 tbl_tit" colspan="4">K-MMSE 2</th>
            </tr>
            <tr>
                <th class="survey_date" colspan="4">
                    <div class="sv_text">설문실행일</div>
                    <div class="sv_date">
						<input type='text' class='calendar' id='sdate' name='sdate' placeholder='yyyy/mm/dd'  style='width:120px;' 
						<%
						if(kmmse2vo == null) {%>value=""<%}
						else if(StringUtil.NVL(kmmse2vo.getTestDay()) == "" ){%>value=""<%}
						else{%>value="<%=StringUtil.NVL(DateUtil.getSimpleDate("yyyyMMdd",kmmse2vo.getTestDay()))%>"<%}%>>	
					</div>
                </th>
            </tr>
            <tr>
                <th colspan="2">항목</th>
                <th colspan="2" class="bg2">합계</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <th colspan="2" class="ta_l">1. 기억 등록</th>
                <td colspan="2">
                    <input type='text' id="num" name='t0' class='t'minlength="1" maxlength="1" value='<%=(kmmse2vo == null ? "" : StringUtil.NVL(kmmse2vo.getKms01()))%>'> / 3
                </td>
            </tr>
            <tr>
                <th colspan="2" class="ta_l">2. 시간 지남력</th>
                <td colspan="2">
                    <input type='text' id="num" name='t1' class='t'minlength="1" maxlength="1" value='<%=(kmmse2vo == null ? "" : StringUtil.NVL(kmmse2vo.getKms02()))%>'> / 5
                </td>
            </tr>
            <tr>
                <th colspan="2" class="ta_l">3. 장소 지남력</th>
                <td colspan="2">
                    <input type='text' id="num" name='t2' class='t'minlength="1" maxlength="1" value='<%=(kmmse2vo == null ? "" : StringUtil.NVL(kmmse2vo.getKms03()))%>'> / 5
                </td>
            </tr>
            <tr>
                <th colspan="2" class="ta_l">4. 기억 회상</th>
                <td colspan="2">
                    <input type='text' id="num" name='t3' class='t'minlength="1" maxlength="1" value='<%=(kmmse2vo == null ? "" : StringUtil.NVL(kmmse2vo.getKms04()))%>'> / 3
                </td>
            </tr>
            <tr>
                <th colspan="2" class="ta_l">5. 주의 집중 및 계산</th>
                <td colspan="2">
                    <input type='text' id="num" name='t4' minlength="1" maxlength="1" class='t' value='<%=(kmmse2vo == null ? "" : StringUtil.NVL(kmmse2vo.getKms05()))%>'> / 5
                </td>
            </tr>
            <tr>
                <th rowspan="5" class="ta_l">6. 언어</th>
                <th>이름대기</th>
                <td><input type='text' id="num" name='t5' minlength="1" maxlength="1" class='t' value='<%=(kmmse2vo == null ? "" : StringUtil.NVL(kmmse2vo.getKms06a()))%>'> / 2</td>
                <td rowspan="5"><input type='text'  name='ts' minlength="1" maxlength="1" class='tn' value='<%=(kmmse2vo == null ? "" : StringUtil.NVL(kmmse2vo.getKms06score()))%>' readonly> / 8</td>
            </tr>
            <tr>
                <th>따라 말하기</th>
                <td><input type='text' id="num" name='t6' minlength="1" maxlength="1" class='t' value='<%=(kmmse2vo == null ? "" : StringUtil.NVL(kmmse2vo.getKms06b()))%>'> / 1</td>
            </tr>
            <tr>
                <th>이해</th>
                <td><input type='text' id="num" name='t7' minlength="1" maxlength="1" class='t' value='<%=(kmmse2vo == null ? "" : StringUtil.NVL(kmmse2vo.getKms06c()))%>'> / 3</td>
            </tr>
            <tr>
                <th>읽기</th>
                <td><input type='text' id="num" name='t8' minlength="1" maxlength="1" class='t' value='<%=(kmmse2vo == null ? "" : StringUtil.NVL(kmmse2vo.getKms06d()))%>'> / 1</td>
            </tr>
            <tr>
                <th>쓰기</th>
                <td><input type='text' id="num" name='t9' minlength="1" maxlength="1" class='t' value='<%=(kmmse2vo == null ? "" : StringUtil.NVL(kmmse2vo.getKms06e()))%>'> / 1</td>
            </tr>
            <tr>
                <th colspan="2" class="ta_l">7. 그리기</th>
                <td colspan="2">
                    <input type='text' id="num" name='t10' minlength="1" maxlength="1" class='t' value='<%=(kmmse2vo == null ? "" : StringUtil.NVL(kmmse2vo.getKms07()))%>'> / 1
                </td>
            </tr>
        </tbody>
        <tfoot>
            <tr>
                <tr>
                    <th colspan="2">총점</th>
                    <td colspan="2">
                        <input type='text'  name='tts' class='tn' minlength="1" maxlength="2" value='<%=(kmmse2vo == null ? "" : StringUtil.NVL(kmmse2vo.getKmsAllscore()))%>' readonly> / 30
                    </td>
                </tr>
            </tr>
        </tfoot>
    </table>

</div>


<!-- 저장 버튼을 하단에 배치 -->
<div id="btnArea" class="btn_wrap btm">
	<button name="저장" id="insert" class ="csbr1010btn" style="">저장</button>
</div>



</body>
<script>
var sdateVal = $('#sdate').val();


console.log("sdateVal::"+sdateVal);

	


</script>
</html>
