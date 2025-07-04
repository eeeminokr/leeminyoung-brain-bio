<%@page import="java.sql.Date"%>
<%@page import="csbrain.common.service.MbKmmse2VO"%>
<%@page import="csbrain.common.util.DateUtil"%>
<%@page import="csbrain.common.util.StringUtil"%>
<%@page import="java.util.List"%>
<%@page import="csbrain.common.service.MbKiadlVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/common.inc"%>
<jsp:include page='<%=incInit%>' flush="false"></jsp:include>
<%
	List<MbKiadlVO> kiadl_list = (List<MbKiadlVO>) request.getAttribute("kiadl_list");
	//List<String> remark_list = (List<String>) request.getAttribute("remark_list");
	MbKiadlVO vo = null;

	if( kiadl_list != null && kiadl_list.size() > 0 ){
		vo = kiadl_list.get(0);
	}
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
		position:absolute !important;
		margin:0px !important;
		border:2px solid  rgb(96, 157, 190) !important;
		border-radius: 5px !important;
		text-align:left;
		font-weight:normal;
		height:25px;
		/* overflow:hidden; */
		background-color:transparent  !important;
		color:blue !important;
		box-sizing: border-box; /* padding을 줘도 크기 안변하게 */
		padding-left:1px;
		width: 60px;
		
	}	
	
	
		#tabbox {

 /*	background-size: cover;
	*/
	width:608px;
	height: 1850px;
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
	var idx = $('#idx').val();
	console.log(idx);
	
	var action = $('#action').val();
	if(action =='hidden'){
	     $('#btnArea').css({"visibility": "hidden"});
	}

/*
	$("input[type=radio]").click(function(){    
		var pre = $(this).data("previous");    
		var chk = $(this).is(":checked");    
		var name = $(this).attr('name');    
		if(chk == true && pre == $(this).val()){        
		
			$(this).prop('checked',false);        
			$("input[name='"+name+"']").data("previous",'');    
		
		}else{        
			if(chk == true) $("input[name='"+name+"']").data("previous",$(this).val());    		
		}
		
	});
*/

	/*
	var valu1;
	var addT1 = 0;
	$("input[class='r']").click(function() {
		
		
		value1 = $(this).val();
		var kval =  $('input:radio[id="k"]:checked').length;
		console.log(kval+"k체크한카운트")
		console.log(kval+"t체크한카운트")			
		addT1 += Number(value1);
		var zero = 0.00;
		if(kval == 11){
		
			$('input:text[name="kts"]').val(zero);	
			$('input:text[name="tts"]').val(addT1.toFixed(2));		
		}else{
			var getTts = $('input:text[name="tts"]').val();		
			 getTts = Number(getTts);  
			zero = getTts/(11-Number(kval));
			$('input:text[name="kts"]').val(zero.toFixed(2));	
			$('input:text[name="tts"]').val(addT1);		
		}

});
	
	$("input:radio[class='r']").change(function(){  
		 var chk = $(this).is(":checked");
	     var name = $(this).attr('name');    
	//if(chk == true)
		$("input[name='"+name+"']").data("previous",$(this).val());
	
		});
	
	
	var value;
	var addT = 0;
	$('input:radio[class="r"]:checked').change(function() {

		var pre = $(this).data("previous"); 
		alert(pre);
			value = $(this).val();
		alert(value)	
			var kval =  $('input:radio[id="k"]:checked').length;
			console.log(kval+"k체크한카운트")
			console.log(kval+"t체크한카운트")			
			addT += Number(value);
			var zero = 0.00;
			if(kval == 11){
			
				$('input:text[name="kts"]').val(zero);	
				$('input:text[name="tts"]').val(addT.toFixed(2));		
			}else{
				var getTts = $('input:text[name="tts"]').val();		
				 getTts = Number(getTts);  
				zero = getTts/(11-Number(kval));
				$('input:text[name="kts"]').val(zero.toFixed(2));	
				$('input:text[name="tts"]').val(addT);		
			}

	});
	*/	
	
	
	
	function chkTts() {
	var rchk = $('input:radio[class="r"]:checked');
	
	var sum = 0;
	var count = rchk.length; // radio 갯수	

	var kval =  $('input:radio[id="k"]:checked').length;
	console.log(kval+"k체크한카운트")
	console.log(count+"t체크한카운트")			
	var zero = 0.00;
	if(kval == 11){
		$('input:text[name="kts"]').val(zero);	
		for(var i=0; i < count; i++ ){
		       if( rchk[i].checked == true ){ // 체크 된것만
		         sum += Number(rchk[i].value); // radio value 값 합산
		       }
		   }	
	}else{
		for(var i=0; i < count; i++ ){
		       if( rchk[i].checked == true ){ // 체크 된것만
		         sum += Number(rchk[i].value); // radio value 값 합산
		       }
		   }	
		zero = sum /(11-Number(kval));
		$('input:text[name="kts"]').val(zero.toFixed(2));	
		
	}
	
	
	
	
	
	
	 $('input:text[name="tts"]').val(sum); 
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

	//$('input:text[name="ddate"]').attr("disabled", false);
	 $('input[class="r"]').removeAttr('checked');
	 $('input[name="sdate"]').val('');
	 $('input[class="t"]').val('');
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
	$('input:radio[class="r"]:not(:checked)').attr("disabled", false);
   	 $(this).prop('checked', false);

		// $('input:radio[name="r2"]').attr("disabled", true); //비활성화

		
   	 	var selnew = $("#edit").val(); 
   	 	console.log(selnew+"::selenew")
		checking = $('#chk').val(selnew);
	});
	
	
	
	
	
	
	
	$('#insert').click(function() {
		
		//배열선언

		var radioArr = [];
		var textArr = [];
		var sdates = $("#sdate").val();
		var exam_num= $("#selectionNum").val();
		var exam_idx= $("#examIdx").val(); 
		var exam_id = $("#exid").val();
		var tts = $('input:text[name="tts"]').val();
		var kts = $('input:text[name="kts"]').val();
		console.log("kts::"+kts)
		console.log("tts::"+tts)		
		console.log("exam_id::"+exam_id)
		console.log("exam_num::"+exam_num)
		console.log("exam_idx::"+exam_idx)
		console.log("설문실행일:"+sdates);
		var whichChk = $("#chk").val();
		console.log(whichChk+":::whichChk")
		var cdbs = $("#cdbs").val();
		
		if(sdates == ''){
			alert("설문실행일을 체크해주세요!")
			return false;
		}
		

		var value;
		$('input:radio[class="r"]:checked').each(function(i){//체크된 리스트 저장
			
			value = $(this).val();
			console.log(value+":[mh 텍스트]values1["+i+"]");

			var Fval =  $('input:radio[class="r"]:checked').length;
			console.log(Fval+"체크한카운트")
			
			if(Fval == 11){
					chkTts();
					 tts=  $('input:text[name="tts"]').val();
					 kts= $('input:text[name="kts"]').val();
					// alert(tts+"tts값")
					//radioArr.push(value);	
					  radioArr.push(value);		

			}else if(Fval < 11){
				alert("체크되지 않는 입력값이 있습니다!")
				return false;
			}
			
        });
	

		

		
		
		var num;
		var num1;
		 num = $('input:radio[class="r"]:checked').length;
 		console.log(num+"저장된체크넘버");
 		num1 = $('input:radio[class="r"]:not(:checked)').length;
		

		var objParams = {
			"radioArr" : radioArr,
			"sdate" : sdates,  
			"idx" : idx,
			"whichChk" : whichChk,
			"exam_id" : exam_id,
			"exam_num" : exam_num,
			"exam_idx" : exam_idx,
			"cdbs" : cdbs,
			"tts" : tts,
			"kts" : kts
	
		};
		   setTimeout(function() {		
		//ajax호출
		$.ajax({
			url         :   getContextPath()+"/include/sheet/view_savekiadl.do",
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
			},1000);	
		
	});
	var sdateVal = $('input:text[name="sdate"]').attr('value')
	 var date = $("calendar").val();
	console.log(date);
	console.log("sdateVal앞::"+sdateVal);

	
	 $('input:radio[class="r"]:checked').attr("disabled", false);	
	 $('input:radio[class="r"]:not(:checked)').attr("disabled", true);
	// $('input:radio[name="r2"]').attr("disabled", true); //비활성화
	 $('input:text[class="t"]').attr("disabled",true);		
	 
	 $('input:text[name="sdate"]').attr("disabled", true);
	
	

	
});
</script>
<body>
<!-- 

	public String make_radio(String v) {
		String checked1 = "";
		String checked2 = "";
		String checked3 = "";
		String checked4 = "";
		String checked5 = "";

		if ( "1".equals(v) ) checked1 = "checked";
		if ( "2".equals(v) ) checked2 = "checked";
		if ( "3".equals(v) ) checked3 = "checked";
		if ( "4".equals(v) ) checked4 = "checked";
		if ( "5".equals(v) ) checked5 = "checked";

		String radioTag = "<input type='radio' class='r' " + checked1 + " style='left:20px;top:5px;'>"
		+ "<input type='radio' class='r'  " + checked2 + " style='left:69px;top:5px;'>"
		+ "<input type='radio' class='r'  " + checked3 + " style='left:118px;top:5px;'>"
		+ "<input type='radio' class='r'  " + checked4 + " style='left:164px;top:5px;'>"
		+ "<input type='radio' class='r'  " + checked5 + " style='left:213px;top:5px;'>";
		return radioTag;
	}

 -->
 
 
<div class="btn_wrap top">
	<!-- <button name="저장" id="insert" class ="csbr1010btn" style="display:none;">저장</button> -->
  	<input type ="hidden" id="action" value="${action}" style="display:none;"/>   
	<button name="수정" id="edit" class ="csbr1010btn"  value="edit">수정</button>
	<button name="신규" id ="new" class ="csbr1010btn"  value="new">신규</button>

	<input type="hidden" id ="object_idx" style="display:none;" class ="object_idx" value='<%= vo == null ? "" :StringUtil.NVL  (vo.getObjectIdx())%>'>
	<input type='hidden' name='selectionNum' id="selectionNum"  value='<%= vo == null ? "" :StringUtil.NVL(vo.getSelectionNum())%>'>
	<input type='hidden' name='examIdx' id="examIdx"  value='<%= vo == null ? "" :StringUtil.NVL(vo.getIdx())%>'>
	<input name="idx" id ="idx" class ="idx" value="${idx}" style="display:none;"/>
	<input type ="hidden" name="exid" id ="exid" class ="exid" value="${exam_id}" style="display:none;"/>
	<input type ="hidden" name="exnum" id ="exnum" class ="exnum" value="${exam_num}" style="display:none;"/>
	<input type ="hidden" name="cdbs" id ="cdbs" class ="cdbs" value="0010" style="display:none;"/>
	<input type ="hidden" name="chk" id ="chk" class ="chk" value="" style="display:none;"/>
</div>


<div id="tabbox"style="height:auto;">
	<!-- 도구적 일상생활능력 설문지 HTML -->
	<table class="table type02">
        <colgroup>
            <col width="40px" />
            <col width="*" />
            <col width="45px" />
            <col width="45px" />
            <col width="45px" />
            <col width="45px" />
            <col width="45px" />
        </colgroup>
        <thead>
            <tr>
                <th class="bg1 tbl_tit" colspan="7">도구적 일상생활능력</th>
            </tr>
            <tr>
                <th class="survey_date" colspan="7">
                    <div class="sv_text">설문실행일</div>
                    <div class="sv_date">
						<input type='text' class='calendar' id='sdate' name='sdate' placeholder='yyyy/mm/dd'  style='width:120px;' 
					<%if(vo == null) {%>value=""<%}
			else if(StringUtil.NVL(vo.getTestDay()) == "" ){%>value=""<%}
			else{%>value="<%=StringUtil.NVL(DateUtil.getSimpleDate("yyyyMMdd",vo.getTestDay()))%>"<%}%>>
					</div>
                </th>
            </tr>
            <tr>
                <th>순번</th>
                <th>항목</th>
                <th class="bg2">혼자<br />가능</th>
                <th class="bg2">약간<br />도움이<br />필요</th>
                <th class="bg2">많은<br />도움이<br />필요</th>
                <th class="bg2">불가능</th>
                <th class="bg2">해당<br />없음</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <th>1</th>
                <td class="ta_l">시장 보기, 쇼핑<br />
                    상점에 가서 계획한 물건들을 잊지 않으며 돈 계산에 실수없이 구매합니까?</td>
                <td><label><input type='radio' id='r' name ='r1' class='r' value="0" <% if("0".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl01())))){%>checked <%}%>>  <span><em class="blind">혼자 가능</em></span></label></td>
                <td><label><input type='radio' id='r' name ='r1' class='r' value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl01())))){%>checked <%}%>><span><em class="blind">약간 도움이 필요</em></span></label></td>
                <td><label><input type='radio' id='r' name ='r1' class='r' value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl01())))){%>checked <%}%>><span><em class="blind">많은 도움이 필요</em></span></label></td>
                <td><label><input type='radio' id='r' name ='r1' class='r' value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl01())))){%>checked <%}%>><span><em class="blind">불가능</em></span></label></td>
                <td><label><input type='radio' id='k' name ='r1' class='r' value="9" <% if("9".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl01())))){%>checked <%}%>><span><em class="blind">해당 없음</em></span></label></td>
            </tr>
            <tr>
                <th>2</th>
                <td class="ta_l">교통 수단 이용<br />
                    대중교통을 이용하거나 스스로 운전해서 길을 잃지 않고 목적지에 갑니까?</td>
                <td><label><input type='radio' id='r' id='r'name ='r2' class='r' value="0" <% if("0".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl02())))){%>checked <%}%>><span><em class="blind">혼자 가능</em></span></label></td>
                <td><label><input type='radio' id='r' id='r'name ='r2' class='r' value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl02())))){%>checked <%}%>><span><em class="blind">약간 도움이 필요</em></span></label></td>
                <td><label><input type='radio' id='r' id='r'name ='r2' class='r' value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl02())))){%>checked <%}%>><span><em class="blind">많은 도움이 필요</em></span></label></td>
                <td><label><input type='radio' id='r' id='r'name ='r2' class='r' value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl02())))){%>checked <%}%>><span><em class="blind">불가능</em></span></label></td>
                <td><label><input type='radio' id='k' id='k'name ='r2' class='r' value="9" <% if("9".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl02())))){%>checked <%}%>><span><em class="blind">해당 없음</em></span></label></td>
            </tr>
            <tr>
                <th>3</th>
                <td class="ta_l">돈 관리<br />
                    용돈을 관리하고, 은행에 가서 저축을 하는 등의 돈과 관련된 일을 처리합니까?</td>
                <td><label><input type='radio' id='r' name ='r3' class='r' value="0" <% if("0".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl03())))){%>checked <%}%>><span><em class="blind">혼자 가능</em></span></label></td>
                <td><label><input type='radio' id='r' name ='r3' class='r' value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl03())))){%>checked <%}%>><span><em class="blind">약간 도움이 필요</em></span></label></td>
                <td><label><input type='radio' id='r' name ='r3' class='r' value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl03())))){%>checked <%}%>><span><em class="blind">많은 도움이 필요</em></span></label></td>
                <td><label><input type='radio' id='r' name ='r3' class='r' value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl03())))){%>checked <%}%>><span><em class="blind">불가능</em></span></label></td>
                <td><label><input type='radio' id='k' name ='r3' class='r' value="9" <% if("9".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl03())))){%>checked <%}%>><span><em class="blind">해당 없음</em></span></label></td>
            </tr>
            <tr>
                <th>4</th>
                <td class="ta_l">집안일 하기, 기구 사용<br />
                    진공 청소가, 다리미 등의 기구들을 잘 다루며 일상적인 집안일(예:청소, 화초 물주기, 설거지)을 예전과 같이 잘 할 수 있습니까?</td>
                <td><label><input type='radio' id='r' name ='r4' class='r' value="0" <% if("0".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl04())))){%>checked <%}%>><span><em class="blind">혼자 가능</em></span></label></td>
                <td><label><input type='radio' id='r' name ='r4' class='r' value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl04())))){%>checked <%}%>><span><em class="blind">약간 도움이 필요</em></span></label></td>
                <td><label><input type='radio' id='r' name ='r4' class='r' value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl04())))){%>checked <%}%>><span><em class="blind">많은 도움이 필요</em></span></label></td>
                <td><label><input type='radio' id='r' name ='r4' class='r' value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl04())))){%>checked <%}%>><span><em class="blind">불가능</em></span></label></td>
                <td><label><input type='radio' id='k' name ='r4' class='r' value="9" <% if("9".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl04())))){%>checked <%}%>><span><em class="blind">해당 없음</em></span></label></td>
            </tr>
            <tr>
                <th>5</th>
                <td class="ta_l">음식 준비<br />
                    적절한 식사를 계획하여 재료를 준비하고, 예전과 같이 맛있게 음식을 만듭니까?</td>
                <td><label><input type='radio' id='r' name ='r5' class='r' value="0" <% if("0".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl05())))){%>checked <%}%>><span><em class="blind">혼자 가능</em></span></label></td>
                <td><label><input type='radio' id='r' name ='r5' class='r' value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl05())))){%>checked <%}%>><span><em class="blind">약간 도움이 필요</em></span></label></td>
                <td><label><input type='radio' id='r' name ='r5' class='r' value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl05())))){%>checked <%}%>><span><em class="blind">많은 도움이 필요</em></span></label></td>
                <td><label><input type='radio' id='r' name ='r5' class='r' value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl05())))){%>checked <%}%>><span><em class="blind">불가능</em></span></label></td>
                <td><label><input type='radio' id='k' name ='r5' class='r' value="9" <% if("9".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl05())))){%>checked <%}%>><span><em class="blind">해당 없음</em></span></label></td>
            </tr>
            <tr>
                <th>6</th>
                <td class="ta_l">전화 사용<br />
                    필요한 전화 번호를 수첩에서 찾거나 기억하여 전화를 겁니까?</td>
                <td><label><input type='radio' id='r' name ='r6' class='r' value="0" <% if("0".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl06())))){%>checked <%}%>><span><em class="blind">혼자 가능</em></span></label></td>
                <td><label><input type='radio' id='r' name ='r6' class='r' value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl06())))){%>checked <%}%>><span><em class="blind">약간 도움이 필요</em></span></label></td>
                <td><label><input type='radio' id='r' name ='r6' class='r' value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl06())))){%>checked <%}%>><span><em class="blind">많은 도움이 필요</em></span></label></td>
                <td><label><input type='radio' id='r' name ='r6' class='r' value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl06())))){%>checked <%}%>><span><em class="blind">불가능</em></span></label></td>
                <td><label><input type='radio' id='k' name ='r6' class='r' value="9" <% if("9".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl06())))){%>checked <%}%>><span><em class="blind">해당 없음</em></span></label></td>
            </tr>
            <tr>
                <th>7</th>
                <td class="ta_l">약 복용<br />
                    시간과 용량을 지켜 약을 먹습니까?</td>
                <td><label><input type='radio' id='r' name ='r7' class='r' value="0" <% if("0".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl07())))){%>checked <%}%>><span><em class="blind">혼자 가능</em></span></label></td>
                <td><label><input type='radio' id='r' name ='r7' class='r' value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl07())))){%>checked <%}%>><span><em class="blind">약간 도움이 필요</em></span></label></td>
                <td><label><input type='radio' id='r' name ='r7' class='r' value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl07())))){%>checked <%}%>><span><em class="blind">많은 도움이 필요</em></span></label></td>
                <td><label><input type='radio' id='r' name ='r7' class='r' value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl07())))){%>checked <%}%>><span><em class="blind">불가능</em></span></label></td>
                <td><label><input type='radio' id='k' name ='r7' class='r' value="9" <% if("9".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl07())))){%>checked <%}%>><span><em class="blind">해당 없음</em></span></label></td>
            </tr>
            <tr>
                <th>8</th>
                <td class="ta_l">최근 기억<br />
                    약속, 어제의 일 또는 다른 사람에게 전달해야 할 전화 내용 등을 기억합니까?</td>
                <td><label><input type='radio' id='r' name ='r8' class='r' value="0" <% if("0".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl08())))){%>checked <%}%>><span><em class="blind">혼자 가능</em></span></label></td>
                <td><label><input type='radio' id='r' name ='r8' class='r' value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl08())))){%>checked <%}%>><span><em class="blind">약간 도움이 필요</em></span></label></td>
                <td><label><input type='radio' id='r' name ='r8' class='r' value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl08())))){%>checked <%}%>><span><em class="blind">많은 도움이 필요</em></span></label></td>
                <td><label><input type='radio' id='r' name ='r8' class='r' value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl08())))){%>checked <%}%>><span><em class="blind">불가능</em></span></label></td>
                <td><label><input type='radio' id='k' name ='r8' class='r' value="9" <% if("9".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl08())))){%>checked <%}%>><span><em class="blind">해당 없음</em></span></label></td>
            </tr>
            <tr>
                <th>9</th>
                <td class="ta_l">취미 생활<br />
                    종교, 독서, 바둑, 장기, 화투, 산책, 등산, 운동 등의 예전에 하던 취미를 그대로 즐깁니까?</td>
                <td><label><input type='radio' id='r' name ='r9' class='r' value="0" <% if("0".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl09())))){%>checked <%}%>><span><em class="blind">혼자 가능</em></span></label></td>
				<td><label><input type='radio' id='r' name ='r9' class='r' value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl09())))){%>checked <%}%>><span><em class="blind">약간 도움이 필요</em></span></label></td>
                <td><label><input type='radio' id='r' name ='r9' class='r' value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl09())))){%>checked <%}%>><span><em class="blind">많은 도움이 필요</em></span></label></td>
                <td><label><input type='radio' id='r' name ='r9' class='r' value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl09())))){%>checked <%}%>><span><em class="blind">불가능</em></span></label></td>
                <td><label><input type='radio' id='k' name ='r9' class='r' value="9" <% if("9".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl09())))){%>checked <%}%>><span><em class="blind">해당 없음</em></span></label></td>
            </tr>
            <tr>
                <th>10</th>
                <td class="ta_l">텔레비전 시청<br />
                    집중해서 텔레비전을 보며 그 내용을 이해합니까?</td>
                <td><label><input type='radio' id='r' name ='r10' class='r' value="0" <% if("0".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl10())))){%>checked <%}%>><span><em class="blind">혼자 가능</em></span></label></td>
                <td><label><input type='radio' id='r' name ='r10' class='r' value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl10())))){%>checked <%}%>><span><em class="blind">약간 도움이 필요</em></span></label></td>
                <td><label><input type='radio' id='r' name ='r10' class='r' value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl10())))){%>checked <%}%>><span><em class="blind">많은 도움이 필요</em></span></label></td>
                <td><label><input type='radio' id='r' name ='r10' class='r' value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl10())))){%>checked <%}%>><span><em class="blind">불가능</em></span></label></td>
                <td><label><input type='radio' id='k' name ='r10' class='r' value="9" <% if("9".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl10())))){%>checked <%}%>><span><em class="blind">해당 없음</em></span></label></td>
            </tr>
            <tr>
                <th>11</th>
                <td class="ta_l">집안 수리<br />
                    못박기나 전구 끼우기 같은 집안 잡일을 수행합니까?</td>
                <td><label><input type='radio' id='r' name ='r11' class='r' value="0" <% if("0".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl11())))){%>checked <%}%>><span><em class="blind">혼자 가능</em></span></label></td>
                <td><label><input type='radio' id='r' name ='r11' class='r' value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl11())))){%>checked <%}%>><span><em class="blind">약간 도움이 필요</em></span></label></td>
                <td><label><input type='radio' id='r' name ='r11' class='r' value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl11())))){%>checked <%}%>><span><em class="blind">많은 도움이 필요</em></span></label></td>
                <td><label><input type='radio' id='r' name ='r11' class='r' value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl11())))){%>checked <%}%>><span><em class="blind">불가능</em></span></label></td>
                <td><label><input type='radio' id='k' name ='r11' class='r' value="9" <% if("9".equals((vo == null ? "" : StringUtil.NVL(vo.getKiadl11())))){%>checked <%}%>><span><em class="blind">해당 없음</em></span></label></td>
            </tr>
        </tbody>
        <tfoot>
            <tr>
                <td class="ta_l" colspan="7">
                    <span class="fw_b">평점(총점/11문항-해당없음 문항수):</span>
                    <input type='text' name ='tts' class='t'  value='<%=(vo == null ? "" : StringUtil.NVL(vo.getKiadlTotal()))%>'>/<input type='text' name ='kts' class='t'  value='<%=(vo == null ? "" : StringUtil.NVL(vo.getKiadlKscore()))%>'>  
                </td>
            </tr>
        </tfoot>
    </table>
	<!-- //도구적 일상생활능력 설문지 HTML -->

</div>


<!-- 상단 버튼을 하단에 중복배치 -->
<div class="btn_wrap btm">
	<button name="저장" id="insert" class ="csbr1010btn" style="">저장</button>
</div>

</body>
</html>