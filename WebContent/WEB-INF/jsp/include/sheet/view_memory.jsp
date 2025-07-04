<%@page import="java.sql.Date"%>
<%@page import="csbrain.common.util.StringUtil"%>
<%@page import="csbrain.common.util.DateUtil"%>
<%@page import="csbrain.common.service.MbC1VO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/common.inc"%>
<jsp:include page='<%=incInit%>' flush="false"></jsp:include>
<%
	List<MbC1VO> c1_list = (List<MbC1VO>) request.getAttribute("c1_list");
	MbC1VO vo = null;

	if( c1_list != null && c1_list.size() > 0 ){
		vo = c1_list.get(0);
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
		position:relative;
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
 #tabbox {

 /*	background-size: cover;
	*/
	width:608px;
	height: 600px;
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
	
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.js"></script>
<script>	
$(function(){
	
	
	var action = $('#action').val();
	if(action =='hidden'){
	     $('#btnArea').css({"visibility": "hidden"});
	}
	
	
	
	var idx = $('#idx').val();
	console.log(idx);
	$("input[type=radio]").each(function(){  
		 var chk = $(this).is(":checked");
	     var name = $(this).attr('name');    
	if(chk == true) $("input[name='"+name+"']").data("previous",$(this).val());
	
		});
	
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
	
	
	
	$("#new").click(function() {
		if(idx == ''){
			alert("검색항목을 입력해주세요");
			return false;

		}
		
		if($('#insert').css("display")=="none"){
			$('#insert').show();	
		}
	$('input:text[name="sdate"]').attr("disabled", false);
	$('input:radio[class="r"]').attr("disabled", false);
	$('input:text[class="t"]').attr("disabled", false);
	//$('input:text[name="ddate"]').attr("disabled", false);
	 $('input[class="r"]').removeAttr('checked');
	 $('input[class="t"]').val('');
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
	$('input:text[name="ta3"]').attr("disabled", false);	
   	 $(this).prop('checked', false);

		// $('input:radio[name="r2"]').attr("disabled", true); //비활성화

		
   	 	var selnew = $("#edit").val(); 
   	 	console.log(selnew+"::selenew")
		checking = $('#chk').val(selnew);
	});
	
	
	
	
	
	
	
	$('#insert').click(function() {
		
		//배열선언
		var colNmArr = [];
		var radioArr = [];
		var textArr = [];
		let tscore = 0;
		var sdates = $("#sdate").val();
		var selection_num = $("#selectionNum").val();
		var exam_num= $("#selectionNum").val();
		var exam_idx= $("#examIdx").val(); 
		console.log("exam_num::"+exam_num)
		console.log("exam_idx::"+exam_idx)
		console.log("설문실행일:"+sdates);
		var whichChk = $("#chk").val();
		console.log(whichChk+":::whichChk")
		 var exam_id = $("#exid").val();	
		var cdbs = $("#cdbs").val();
		
		if(sdates == ''){
			alert("설문실행일을 체크해주세요!")
			return false;
		}
		


		var value;
		
		$('input:radio[class="r"]:checked').each(function(i){//체크된 리스트 저장
			
			value = $(this).val();
			console.log(value+":[KDSQ 텍스트]values1["+i+"]");
			
			getNm = $(this).attr('name');
			console.log(getNm+":::::::::::getNm")
			
		
			
			
			var Fval =  $('input:radio[class="r"]:checked').length;
			console.log(Fval+"체크한카운트")
			if(Fval < 22){
				alert("체크되지 않는 입력값이 있습니다!")
				return false;
			}else{
				 radioArr.push(value);		
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
				"exam_num" : exam_num,
				"exam_idx" : exam_idx,
				"exam_id" : exam_id,
				"selectionNum" : selection_num,
				"cdbs" : cdbs
				};
		
		//ajax호출
		$.ajax({
			url         :   getContextPath()+"/include/sheet/view_savememory.do",
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
//             			 load_search();       Console Error : Uncaught ReferenceError: load_search is not defined
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

		if ( "1".equals(v) ) checked1 = "checked";
		if ( "2".equals(v) ) checked2 = "checked";
		if ( "3".equals(v) ) checked3 = "checked";

		String radioTag = "<input type='radio' class='r' " + checked1 + " style='left:16px;top:5px;'>"
		+ "<input type='radio' class='r'  " + checked2 + " style='left:57px;top:5px;'>"
		+ "<input type='radio' class='r'  " + checked3 + " style='left:98px;top:5px;'>";
		return radioTag;
	}
%>
 -->


<div class="btn_wrap top">
	<input type ="hidden" id="action" value="${action}" style="display:none;"/>
	<!-- <button name="저장" id="insert" class ="csbr1010btn" style="display:none;">저장</button> -->
	<button name="수정" id="edit" class ="csbr1010btn"  value="edit">수정</button>
	<button name="신규" id ="new" class ="csbr1010btn"  value="new">신규</button>

	<input type="hidden" id ="object_idx" style="display:none;" class ="object_idx" value='<%= vo == null ? "" :StringUtil.NVL(vo.getObjectIdx())%>'>
	<input type='hidden' name='selectionNum' id="selectionNum"  value='<%= vo == null ? "" :StringUtil.NVL(vo.getSelectionNum())%>'> 
	<input type='hidden' name='examIdx' id="examIdx"  value='<%= vo == null ? "" :StringUtil.NVL(vo.getIdx())%>'> 
	<input name="idx" id ="idx" class ="idx" value="${idx}" style="display:none;"/>
	<input type ="hidden" name="exid" id ="exid" class ="exid" value="${exam_id}" style="display:none;"/>
	<input type ="hidden" name="cdbs" id ="cdbs" class ="cdbs" value="0201" style="display:none;"/>
	<input type ="hidden" name="exnum" id ="exnum" class ="exnum" value="${exam_num}" style="display:none;"/>
	<input type ="hidden" name="chk" id ="chk" class ="chk" value="" style="display:none;"/>
</div> 


<div id ="tabbox" style="height:auto;">

	<table class="table type02">
        <colgroup>
            <col width="40px" />
            <col width="*" />
            <col width="45px" />
            <col width="45px" />
            <col width="45px" />
        </colgroup>
        <thead>
            <tr>
                <th class="bg1 tbl_tit" colspan="5">Memory</th>
            </tr>
            <tr>
                <th class="survey_date" colspan="5">
                    <div class="sv_text">설문실행일</div>
                    <div class="sv_date">
						<input type='text' class='calendar' id='sdate' name='sdate' placeholder='yyyy/mm/dd'  style='width:120px;' value ='<%= vo == null ? "" :StringUtil.NVL(DateUtil.getSimpleDate("yyyyMMdd",vo.getTestDay()))%>'>	
					</div>
                </th>
            </tr>
            <tr>
                <th>M.</th>
                <th>기억력</th>
                <th class="bg2">아니오</th>
                <th class="bg2">예</th>
                <th class="bg2">불확실</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <th>1</th>
                <td class="ta_l">대상자는 최근에 일어난 일을 기억하는 데 어려움이 있는가?</td>      
                <td><label><input type='radio' name='r1' value='1' class='r' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getcM01())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label>	<input type='radio' name='r1' value='2' class='r' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getcM01())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label>	<input type='radio' name='r1' value='3' class='r' <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getcM01())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th>2</th>
                <td class="ta_l">대상자는 며칠 전 혹은 몇 시간 전에 있었던 대화를 잊어버리는가?</td>
                <td><label><input type='radio' name='r2' value='1' class='r' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getcM02())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label>	<input type='radio' name='r2' value='2' class='r' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getcM02())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label>	<input type='radio' name='r2' value='3' class='r' <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getcM02())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th>3</th>
                <td class="ta_l">대상자는 동일한 질문을 반복하는 듯이 보이는가?</td>
                <td><label><input type='radio' name='r3' value='1' class='r' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getcM03())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label>	<input type='radio' name='r3' value='2' class='r' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getcM03())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label>	<input type='radio' name='r3' value='3' class='r' <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getcM03())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th>4</th>
                <td class="ta_l">대산자는 가스 불 또는 전기 스위치 끄는 것을 잊어버리는가?</td>
                <td><label><input type='radio' name='r4' value='1' class='r' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getcM04())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label>	<input type='radio' name='r4' value='2' class='r' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getcM04())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label>	<input type='radio' name='r4' value='3' class='r' <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getcM04())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th>5</th>
                <td class="ta_l">자신이 놔둔 물건(안경, 시계, 지갑 등 소지품)을 잊은 적이 있는가?</td>
                <td><label><input type='radio' name='r5' value='1' class='r' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getcM05())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label>	<input type='radio' name='r5' value='2' class='r' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getcM05())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label>	<input type='radio' name='r5' value='3' class='r' <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getcM05())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th>6</th>
                <td class="ta_l">최근 1개월 동안 약속을 지키지 못한 일이 있는가?</td>
                <td><label><input type='radio' name='r6' value='1' class='r' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getcM06())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label>	<input type='radio' name='r6' value='2' class='r' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getcM06())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label>	<input type='radio' name='r6' value='3' class='r' <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getcM06())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th>7</th>
                <td class="ta_l">자신의 이름에 대해 잘 못 대답하였는가?</td>
                <td><label><input type='radio' name='r7' value='1' class='r' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getcM07())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label>	<input type='radio' name='r7' value='2' class='r' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getcM07())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label>	<input type='radio' name='r7' value='3' class='r' <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getcM07())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th>8</th>
                <td class="ta_l">자신의 나이에 대해 잘 못 대답하였는가?</td>
                <td><label><input type='radio' name='r8' value='1' class='r' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getcM08())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label>	<input type='radio' name='r8' value='2' class='r' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getcM08())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label>	<input type='radio' name='r8' value='3' class='r' <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getcM08())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th>9</th>
                <td class="ta_l">자신의 집주소를 잘 못 대답하였는가?</td>
                <td><label><input type='radio' name='r9' value='1' class='r' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getcM09())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label>	<input type='radio' name='r9' value='2' class='r' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getcM09())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label>	<input type='radio' name='r9' value='3' class='r' <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getcM09())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th>10</th>
                <td class="ta_l">자신의 전화번호를 잘 못 대답하였는가?</td>
                <td><label><input type='radio' name='r10' value='1' class='r' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getcM10())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label>	<input type='radio' name='r10' value='2' class='r' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getcM10())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label>	<input type='radio' name='r10' value='3' class='r' <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getcM10())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th>11</th>
                <td class="ta_l">주변 사람의 전화번호가 기억나지 않을 때가 있었는가?</td>
                <td><label><input type='radio' name='r11' value='1' class='r' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getcM11())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label>	<input type='radio' name='r11' value='2' class='r' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getcM11())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label>	<input type='radio' name='r11' value='3' class='r' <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getcM11())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th>12</th>
                <td class="ta_l">자신의 고향에 대해 잘 못 대답하였는가?</td>
                <td><label><input type='radio' name='r12' value='1' class='r' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getcM12())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label>	<input type='radio' name='r12' value='2' class='r' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getcM12())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label>	<input type='radio' name='r12' value='3' class='r' <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getcM12())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th>13</th>
                <td class="ta_l">배우자, 자녀, 손주, 친한 친구 등의 이름을 잘 못 대답하였는가?</td>
                <td><label><input type='radio' name='r13' value='1' class='r' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getcM13())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label>	<input type='radio' name='r13' value='2' class='r' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getcM13())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label>	<input type='radio' name='r13' value='3' class='r' <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getcM13())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th>14</th>
                <td class="ta_l">가까운 사람의 사는 지역, 학교, 직장이름 등을 기억하지 못 한 적이 있는가?</td>
                <td><label><input type='radio' name='r14' value='1' class='r' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getcM14())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label>	<input type='radio' name='r14' value='2' class='r' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getcM14())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label>	<input type='radio' name='r14' value='3' class='r' <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getcM14())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th>15</th>
                <td class="ta_l">자신의 현재 또는 마지막으로 한일(작업)에 대해 잘 못 대답하였는가?</td>
                <td><label><input type='radio' name='r15' value='1' class='r' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getcM15())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label>	<input type='radio' name='r15' value='2' class='r' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getcM15())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label>	<input type='radio' name='r15' value='3' class='r' <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getcM15())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th>16</th>
                <td class="ta_l">최근 일주일 동안 자신이 참여한 일을 잊는 적이 있는가?<br />
                    (예를 들어, 생일잔치, 명절 모임, 결혼식, 장례식, 집안의 기쁜 일, 슬픈 일, 여행, 병원방문 등)</td>
                <td><label><input type='radio' name='r16' value='1' class='r' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getcM16())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label>	<input type='radio' name='r16' value='2' class='r' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getcM16())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label>	<input type='radio' name='r16' value='3' class='r' <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getcM16())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th>17</th>
                <td class="ta_l">최근 3개월 동안 자신이 참여한 일을 잊은 적이 있는가? (위와 동일 내용 질문)</td>
                <td><label><input type='radio' name='r17' value='1' class='r' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getcM17())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label>	<input type='radio' name='r17' value='2' class='r' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getcM17())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label>	<input type='radio' name='r17' value='3' class='r' <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getcM17())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th>18</th>
                <td class="ta_l">기억장애의 시작이 6개월이 이내라고 보고하는가?</td>
                <td><label><input type='radio' name='r18' value='1' class='r' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getcM18())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label>	<input type='radio' name='r18' value='2' class='r' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getcM18())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label>	<input type='radio' name='r18' value='3' class='r' <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getcM18())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th>19</th>
                <td class="ta_l">기억장애의 시작이 1년이 넘었다고 보고하는가?</td>
                <td><label><input type='radio' name='r19' value='1' class='r' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getcM19())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label>	<input type='radio' name='r19' value='2' class='r' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getcM19())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label>	<input type='radio' name='r19' value='3' class='r' <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getcM19())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th>20</th>
                <td class="ta_l">기억장애의 시작이 3년이 넘었다고 보고하는가?</td>
                <td><label><input type='radio' name='r20' value='1' class='r' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getcM20())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label>	<input type='radio' name='r20' value='2' class='r' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getcM20())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label>	<input type='radio' name='r20' value='3' class='r' <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getcM20())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th>21</th>
                <td class="ta_l">기억장애가 서서히 시작되었는가?</td>
                <td><label><input type='radio' name='r21' value='1' class='r' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getcM21())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label>	<input type='radio' name='r21' value='2' class='r' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getcM21())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label>	<input type='radio' name='r21' value='3' class='r' <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getcM21())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th>22</th>
                <td class="ta_l">기억장애가 점차 심해지는가?</td>
                <td><label><input type='radio' name='r22' value='1' class='r' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getcM22())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label>	<input type='radio' name='r22' value='2' class='r' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getcM22())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label>	<input type='radio' name='r22' value='3' class='r' <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getcM22())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
        </tbody>
    </table>
                                                                           
</div>


<!-- 저장 버튼을 하단에 배치 -->
<div id="btnArea" class="btn_wrap btm">
	<button name="저장" id="insert" class ="csbr1010btn" style="">저장</button>
</div>



</body>
</html>
