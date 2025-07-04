<%@page import="java.sql.Date"%>
<%@page import="csbrain.common.util.StringUtil"%>
<%@page import="csbrain.common.util.DateUtil"%>
<%@page import="csbrain.common.service.MbC3VO"%>
<%@page import="csbrain.common.service.MbC2VO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/common.inc"%>
<jsp:include page='<%=incInit%>' flush="false"></jsp:include>
<%
	List<MbC2VO> c2_list2 = (List<MbC2VO>) request.getAttribute("c2_list2");
//	List<MbC3VO> c3_list = (List<MbC3VO>) request.getAttribute("c3_list");
	MbC2VO c2vo = null;
//	MbC3VO c3vo = null;

	if( c2_list2 != null && c2_list2.size() > 0 ){
		c2vo = c2_list2.get(0);
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
		
	position: absolute;
	}

	/* .r {
	 position: relative;
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
	height: 800px;
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
		var getexmN = $("#exnum").val();

		
		if($('#insert').css("display")=="none"){
			$('#insert').show();	
		}
	$('input:text[name="sdate"]').attr("disabled", false);
	$('input:radio[class="r"]').attr("disabled", false);
	//$('input:text[class="t"]').attr("disabled", false);
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
	
	
	$('input:radio[name="r11"]').click(function() {
		var rval = $(this).val();
		if(rval == '2'){
			setTimeout(function() {
				 $('input:text[name="tac2"]').attr("disabled",false);
				 $('input:text[name="tac2"]').focus();	
					},0);
			
			
		}else{
			$('input:text[name="tac2"]').val('');
			$('input:text[name="tac2"]').attr("disabled",true);
		}
		
		
		
	});
	
	
	
	
	$('#insert').click(function() {
		
		//배열선언
		var colNmArr = [];
		var radioArr = [];
		var textArr = [];
		let tscore = 0;
		var sdates = $("#sdate").val();
		var exam_num= $("#selectionNum").val();
		var exam_idx= $("#examIdx").val(); 
		var exam_id = $("#exid").val();
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
		var value1;
		$('input:radio[class="r"]:checked').each(function(i){//체크된 리스트 저장
			
			value = $(this).val();
			console.log(value+":[KDSQ 텍스트]values1["+i+"]");
			
			getNm = $(this).attr('name');
			console.log(getNm+":::::::::::getNm")
			
		
			
			
			var Fval =  $('input:radio[class="r"]:checked').length;
			console.log(Fval+"체크한카운트")
			if(Fval < 11){
				alert("체크되지 않는 입력값이 있습니다!")
				return false;
			}else{
				 radioArr.push(value);		
			}
		
			
			
		
        });

		$('input:text[class="t"]').each(function(){	
			value1 = $(this).val();
			
			var Selval11 =  $('input:radio[name="r11"]:checked').val();
			var r11text = $('input:text[name="tac2"]').val();
			if(Selval11 =='2' && r11text == ''){
				alert("11번 문항을 기술해주시길 바랍니다.")
				setTimeout(function() {
					 $('input:text[name="tac2"]').attr("disabled",false);
					 $('input:text[name="tac2"]').focus();	
				},0);
				return false;		
			}else{
				textArr.push(value1);
			}			
		
		});
		
		

		
		
		var num;
		var num1;
		 num = $('input:radio[class="r"]:checked').length;
 		console.log(num+"저장된체크넘버");
 		num1 = $('input:radio[class="r"]:not(:checked)').length;
		

		var objParams = {	
				"textArr" : textArr,
				"radioArr" : radioArr,
				"sdate" : sdates,  
				"idx" : idx,
				"exam_id" : exam_id,
				"whichChk" : whichChk,
				"exam_num" : exam_num,
				"exam_idx" : exam_idx,
				"cdbs" : cdbs

				};
		
		//ajax호출
		$.ajax({
			url         :   getContextPath()+"/include/sheet/view_savecognition2_2.do",
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

	
	 $('input:radio[class="r"]:checked').attr("disabled", false);	
	 $('input:radio[class="r"]:not(:checked)').attr("disabled", true);
	// $('input:radio[name="r2"]').attr("disabled", true); //비활성화
	 $('input:text[class="t"]').attr("disabled",true);		
	 
	 $('input:text[name="sdate"]').attr("disabled", true);
	
	

	
});
</script>
<body>

<!--  public String make_radio(String v) {
			
		String checked1 = "";
		String checked2 = "";
		String checked3 = "";
		
		if ( "1".equals(v) ) checked1 = "checked";
		if ( "2".equals(v) ) checked2 = "checked";
		if ( "3".equals(v) ) checked3 = "checked";

		
		

		
			
		
		 String radioTag = "<input type='radio'  value='1' class='r' " + checked1 + " style='left:16px;top:5px;'>"
		+ "<input type='radio' class='r'   value='2' " + checked2 + " style='left:57px;top:5px;'>"
		+ "<input type='radio' class='r'  value='3' " + checked3 + " style='left:98px;top:5px;'>";

		return radioTag;		
		

	
		
		
	
	}
	
	public String make_radio_v(String v) {
		String checked1 = "";
		String checked2 = "";
		String checked3 = "";
		String checked4 = "";

		if ( "1".equals(v) ) checked1 = "checked";
		if ( "2".equals(v) ) checked2 = "checked";
		if ( "3".equals(v) ) checked3 = "checked";
		if ( "4".equals(v) ) checked3 = "checked";

		
	String radioTag = "<input type='radio' class='r' " + checked1 + " style='left:11px;top:4px;'>"
		+ "<input type='radio'  class='r'  " + checked2 + " style='left:11px;top:26px;'>"
		+ "<input type='radio'  class='r'  " + checked3 + " style='left:11px;top:48px;'>"
		+ "<input type='radio'  class='r'  " + checked4 + " style='left:11px;top:70px;'>";
	
		
	
		return radioTag;
	
	}
-->
<div id="btnArea" class="btn_wrap top">
	<input type ="hidden" id="action" value="${action}" style="display:none;"/>
	<!-- <button name="저장" id="insert" class ="csbr1010btn" style="display:none;">저장</button> -->
	<button name="수정" id="edit" class ="csbr1010btn"  value="edit">수정</button>
	<button name="신규" id ="new" class ="csbr1010btn"  value="new">신규</button>

	<input type="hidden" id ="object_idx" style="display:none;" class ="object_idx" value='<%= c2vo == null ? "" :StringUtil.NVL(c2vo.getObjectIdx())%>'>
	<input type='hidden' name='selectionNum' id="selectionNum"  value='<%= c2vo == null ? "" :StringUtil.NVL(c2vo.getSelectionNum())%>'> 
	<input type='hidden' name='examIdx' id="examIdx"  value='<%= c2vo == null ? "" :StringUtil.NVL(c2vo.getIdx())%>'> 
	<input name="idx" id ="idx" class ="idx" value="${idx}" style="display:none;"/>
	<input type ="hidden" name="exid" id ="exid" class ="exid" value="${exam_id}" style="display:none;"/>     
	<input type ="hidden" name="exnum" id ="exnum" class ="exnum" value="${exam_num}" style="display:none;"/>
	<input type ="hidden" name="cdbs" id ="cdbs" class ="cdbs" value="0207" style="display:none;"/>
	<input type ="hidden" name="chk" id ="chk" class ="chk" value="" style="display:none;"/>
</div> 	

<div id="tabbox" style="height:auto;">

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
                <th class="bg1 tbl_tit" colspan="5">판단력과 문제 해결</th>
            </tr>
            <tr>
                <th class="survey_date" colspan="5">
                    <div class="sv_text">설문실행일</div>
                    <div class="sv_date">
						<input type='text' class='calendar' id='sdate' name='sdate' placeholder='yyyy/mm/dd'  style='width:120px;' value ='<%= c2vo == null ? "" :StringUtil.NVL(DateUtil.getSimpleDate("yyyyMMdd",c2vo.getTestDay()))%>'>
					</div>
                </th>
            </tr>
            <tr>
                <th>J.</th>
                <th></th>
                <th class="bg2">아니오</th>
                <th class="bg2">예</th>
                <th class="bg2">불확실</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <th>1</th>
                <td class="ta_l">대상자가 집에 온 손님에게 부적절하게 반응하는 등 판단상의 문제를 보이는가?</td>
                <td><label><input type='radio' name="r1" class='r' value="1" <% if("1".equals((c2vo == null ? "" : StringUtil.NVL(c2vo.getcJ01())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' name="r1" class='r' value="2" <% if("2".equals((c2vo == null ? "" : StringUtil.NVL(c2vo.getcJ01())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' name="r1" class='r' value="3" <% if("3".equals((c2vo == null ? "" : StringUtil.NVL(c2vo.getcJ01())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th>2</th>
                <td class="ta_l">대상자가 텔레비전 드라마나 신문기사를 이해하거나 이에 관해 이야기하는데 어려움을 보이는가?</td>
                <td><label><input type='radio' name="r2" class='r' value="1" <% if("1".equals((c2vo == null ? "" : StringUtil.NVL(c2vo.getcJ02())))){%>checked <%}%>> <span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' name="r2" class='r' value="2" <% if("2".equals((c2vo == null ? "" : StringUtil.NVL(c2vo.getcJ02())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' name="r2" class='r' value="3" <% if("3".equals((c2vo == null ? "" : StringUtil.NVL(c2vo.getcJ02())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th>3</th>
                <td class="ta_l">스스로 잘 해오던 일들을 어떻게 진행해야 할지 몰라 당황한 적이 있는가?</td>
                <td><label><input type='radio' name="r3" class='r' value="1" <% if("1".equals((c2vo == null ? "" : StringUtil.NVL(c2vo.getcJ03())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' name="r3" class='r' value="2" <% if("2".equals((c2vo == null ? "" : StringUtil.NVL(c2vo.getcJ03())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' name="r3" class='r' value="3" <% if("3".equals((c2vo == null ? "" : StringUtil.NVL(c2vo.getcJ03())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th>4</th>
                <td class="ta_l">본인 스스로 계획에 의해 일을 수행하는 것이 어려워졌는가?</td>
                <td><label><input type='radio' name="r4" class='r' value="1" <% if("1".equals((c2vo == null ? "" : StringUtil.NVL(c2vo.getcJ04())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' name="r4" class='r' value="2" <% if("2".equals((c2vo == null ? "" : StringUtil.NVL(c2vo.getcJ04())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' name="r4" class='r' value="3" <% if("3".equals((c2vo == null ? "" : StringUtil.NVL(c2vo.getcJ04())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th>5</th>
                <td class="ta_l">돈 관리나 거래를 이전과 같이 하기 어려울 것 같다. (세금, 각종 관리비 및 돈을 빌리거나 지불하는 능력 등)</td>
                <td><label><input type='radio' name="r5" class='r' value="1" <% if("1".equals((c2vo == null ? "" : StringUtil.NVL(c2vo.getcJ05())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' name="r5" class='r' value="2" <% if("2".equals((c2vo == null ? "" : StringUtil.NVL(c2vo.getcJ05())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' name="r5" class='r' value="3" <% if("3".equals((c2vo == null ? "" : StringUtil.NVL(c2vo.getcJ05())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th>6</th>
                <td class="ta_l">대상자의 실수로 인해 경제적 순실이 발생한 적이 있다.</td>
                <td><label><input type='radio' name="r6" class='r' value="1" <% if("1".equals((c2vo == null ? "" : StringUtil.NVL(c2vo.getcJ06())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' name="r6" class='r' value="2" <% if("2".equals((c2vo == null ? "" : StringUtil.NVL(c2vo.getcJ06())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' name="r6" class='r' value="3" <% if("3".equals((c2vo == null ? "" : StringUtil.NVL(c2vo.getcJ06())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th>7</th>
                <td class="ta_l">대상자의 주장에 의해 주변사람과의 관계에서 문제가 된 일이 자주 있다.</td>
                <td><label><input type='radio' name="r7" class='r' value="1" <% if("1".equals((c2vo == null ? "" : StringUtil.NVL(c2vo.getcJ07())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' name="r7" class='r' value="2" <% if("2".equals((c2vo == null ? "" : StringUtil.NVL(c2vo.getcJ07())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' name="r7" class='r' value="3" <% if("3".equals((c2vo == null ? "" : StringUtil.NVL(c2vo.getcJ07())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th>8</th>
                <td class="ta_l">상황에 맞지 않는 말이나 행동을 한 적이 있다.</td>
                <td><label><input type='radio' name="r8" class='r' value="1" <% if("1".equals((c2vo == null ? "" : StringUtil.NVL(c2vo.getcJ08())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' name="r8" class='r' value="2" <% if("2".equals((c2vo == null ? "" : StringUtil.NVL(c2vo.getcJ08())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' name="r8" class='r' value="3" <% if("3".equals((c2vo == null ? "" : StringUtil.NVL(c2vo.getcJ08())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th>9</th>
                <td class="ta_l">옷을 상황에 맞게 고를 수 없다.</td>
                <td><label><input type='radio' name="r9" class='r' value="1" <% if("1".equals((c2vo == null ? "" : StringUtil.NVL(c2vo.getcJ09())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' name="r9" class='r' value="2" <% if("2".equals((c2vo == null ? "" : StringUtil.NVL(c2vo.getcJ09())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' name="r9" class='r' value="3" <% if("3".equals((c2vo == null ? "" : StringUtil.NVL(c2vo.getcJ09())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th>10</th>
                <td class="ta_l">돌발 상황(갑작스런 사고나 외출한 후 길을 잃었을 경우)이 생겼을 때 잘 대처하기 어려울 것 같다.</td>
                <td><label><input type='radio' name="r10" class='r' value="1" <% if("1".equals((c2vo == null ? "" : StringUtil.NVL(c2vo.getcJ10())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' name="r10" class='r' value="2" <% if("2".equals((c2vo == null ? "" : StringUtil.NVL(c2vo.getcJ10())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' name="r10" class='r' value="3" <% if("3".equals((c2vo == null ? "" : StringUtil.NVL(c2vo.getcJ10())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
			<tr>
                <th rowspan="2" style="border-bottom: 1px solid #c4d9f6;">11</th>
                <td class="ta_l">대상자는 이 외의 다른 인지적 문제를 갖고 있는가?</td>
                <td><label><input type='radio' name="r11" class='r' value="1" <% if("1".equals((c2vo == null ? "" : StringUtil.NVL(c2vo.getcZ01())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' name="r11" class='r' value="2" <% if("2".equals((c2vo == null ? "" : StringUtil.NVL(c2vo.getcZ01())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' name="r11" class='r' value="3" <% if("3".equals((c2vo == null ? "" : StringUtil.NVL(c2vo.getcZ01())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <td colspan="4" class="ta_l border_dash">만약 '예'라면 기술 하시오 <input type='text' name="tac2" class='t' value='<%=(c2vo == null ? "" : StringUtil.NVL(c2vo.getcZ02()))%>' style='width:380px;'></td>
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