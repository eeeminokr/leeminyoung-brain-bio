<%@page import="java.sql.Date"%>
<%@page import="csbrain.common.service.MbIcVO"%>
<%@page import="csbrain.common.service.MbSmVO"%>
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


	List<MbSmVO> sm_list = (List<MbSmVO>) request.getAttribute("sm_list");
	MbSmVO smvo = null;
//	MbC3VO c3vo = null;

	if(sm_list != null && sm_list.size() > 0 ){
		smvo =sm_list.get(0);
		
		String d = smvo.getTestDay();
		d = DateUtil.getSimpleDate("yyyyMMdd",d);	
		System.out.print(d);
		
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
		#tabbox {

 /*	background-size: cover;
	*/
	width:608px;
	height: 920px;;
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
	
	$('input:radio[name="r1"]').click(function() {
		
		var r1val = $(this).val();
		if(r1val== "3"){
			 $('input[name="r2"]').removeAttr('checked');		
			 $('input:radio[class="r"]:not(:checked)').attr("disabled", true); //비활성화
			 $('input:radio[name="r1"]:not(:checked)').attr("disabled", false);	
		
			 $('input[name="t3a"]').val('');
			 $('input[name="t3b"]').val('');
			 $('input[name="t3c"]').val('');
			 $('input[name="t3d"]').val('');
			 $('input[name="t3e"]').val('');
			 $('input[name="t3f1"]').val('');
			 $('input[name="t3f2"]').val('');
			$('input:text[class="t"]').attr("disabled", true);
				
			 $('input:text[name="forwardt"]').attr("disabled",false);	
			 $('input:text[name="forwardt"]').focus();
			
	
					
			 
			if($('input:radio[name="r2"]').is("checked") == true || !($('input:text[id="num"]').val() == null)){
				$('input:radio[name="r2"]').prop("checked",false)
				
			}
				
			
		}else if(r1val== "1"|| r1val== "2"){
			 $('input:radio[class="r"]:not(:checked)').attr("disabled", false); //비활성화
			 $('input:text[class="t"]').attr("disabled",false);
			// $('input:text[name="forwardt"]').attr("disabled",true);
			
		}
		
		


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
			return false;
		}else if(this.value.length < minLimit){
			$(this).focus();
			return false;
		}
	
		});
	
	$('input:radio[name="r2"]').click(function() {
		if($('input:radio[name="r1"]').is(":checked")==false){
			alert("1번 문항을 입력하지 않았습니다!")
			 $('input:radio[name="r2"]').prop("checked",false);
			 $('input:text[class="t"]').attr("disabled",true);	
	}else if($('input:text[name="t3age"]').val()==''){
		alert("2번 문항을 입력하지 않았습니다!")
		$('input:text[name="t3age"]').focus();
		 $('input:radio[name="r2"]').prop("checked",false);
	}
		
		
	var r2val=	$(this).val();
		
	if(r2val == "1"){
		 $('input:radio[class="r1"]:not(:checked)').attr("disabled", true); //비활성화
	//	 $('input:text[class="t"]').attr("disabled",true);		
		 $('input:text[name="t3a"]').attr("disabled",false);
		 $('input:radio[name="r2"]:not(:checked)').attr("disabled", false);

		 if($('input:text[name="t3a"]').is(":disabled")==false ){
			 $('input:text[name="t3a"]').focus();
			 $('input:text[name="forwardt"]').attr("disabled",false)
		 }
		
	
	}else if(r2val == "2"){
		// $('input:radio[class="r"]:not(:checked)').attr("disabled", true); //비활성화
		 $('input:radio[name="r2"]:not(:checked)').attr("disabled", false);
		// $('input:text[class="t"]').attr("disabled",true);				
			 $('input:text[name="t3b"]').attr("disabled",false);
			 $('input:text[name="t3c"]').attr("disabled",false);
			 $('input:text[name="t3b"]').focus();
			 
			if($('input:text[name="t3b"]').is(":focus")== true){		
				setTimeout(function() {
				$('.t').find('input:text[name="t3c"]').focus();
				},0);

			}
	
		
	}else if(r2val == "3"){
		// $('input:radio[class="r"]:not(:checked)').attr("disabled", true); //비활성화
		// $('input:text[class="t"]').attr("disabled",true);		
		 $('input:text[name="t3d1"]').attr("disabled",false);
		 $('input:radio[name="r2"]:not(:checked)').attr("disabled", false);
		$('input:text[name="t3d1"]').focus();
	
		if($('input:text[name="t3d1"]').is(":focus")== true){		
			
			setTimeout(function() {
			$('input:text[name="t3d2"]').attr("disabled",false);
			$('.t').find('input:text[name="t3d2"]').focus();
			},0);
		}
		
			if($('input:text[name="t3d2"]').is(":focus")== true){		
		
			setTimeout(function() {
				 $('input:text[name="t3e"]').attr("disabled",false);
				$('.t').find('input:text[name="t3e"]').focus();
			},0);
		}
	 if($('input:text[name="t3e"]').is(":focus")== true){		
		 $('input:text[name="t3f1"]').attr("disabled",false);
		setTimeout(function() {
		$('.t').find('input:text[name="t3f1"]').focus();
		},0);
	}
	 if($('input:text[name="t3f1"]').is(":focus")== true){		
		 $('input:text[name="t3f2"]').attr("disabled",false);
		setTimeout(function() {
		$('.t').find('input:text[name="t3f2"]').focus();
		},0);
	}
	 if($('input:text[name="t3f2"]').is(":focus")== true){		
		$('input:text[name="forwardt"]').attr("disabled",false);
		setTimeout(function() {
			$('.t').find('input:text[name="forwardt"]').focus();
		},0);

		
	}
			

		
		
		
	}
	
		
	});
	
	$('input:radio[name="r2"]').change(function() {
	
		var r2val=	$(this).val();
		if(r2val =='3'){
			$('input:text[name="t3a"]').val('');
			$('input:text[name="t3b"]').val('');
			$('input:text[name="t3c"]').val('');

			 $('input:text[name="t3a"]').attr("disabled",true);
			 $('input:text[name="t3b"]').attr("disabled",true);
			 $('input:text[name="t3c"]').attr("disabled",true);
				if($('input:text[name="t3f2"]').is(":focus")== true){		
				
					setTimeout(function() {
						$('.t').find('input:text[name="forwardt"]').focus();
						$('input:text[name="forwardt"]').attr("disabled",false);
					},0);

					
				}
			 

		}else if(r2val =='2'){
			$('input:text[name="t3a"]').val('');
			$('input:text[name="t3d1"]').val('');
			$('input:text[name="t3d2"]').val('');
			$('input:text[name="t3e"]').val('');
			$('input:text[name="t3f1"]').val('');
			$('input:text[name="t3f2"]').val('');
			 $('input:text[name="t3da"]').attr("disabled",true);
			$('input:text[name="t3d1"]').attr("disabled",true);
			 $('input:text[name="t3d2"]').attr("disabled",true);
			 $('input:text[name="t3e"]').attr("disabled",true);
			 $('input:text[name="t3f1"]').attr("disabled",true);
			 $('input:text[name="t3f2"]').attr("disabled",true);
			
		}else if(r2val =='1'){
			$('input:text[name="t3b"]').val('');
			$('input:text[name="t3c"]').val('');
			$('input:text[name="t3d1"]').val('');
			$('input:text[name="t3d2"]').val('');
			$('input:text[name="t3e"]').val('');
			$('input:text[name="t3f1"]').val('');
			$('input:text[name="t3f2"]').val('');
			 $('input:text[name="t3b"]').attr("disabled",true);
			 $('input:text[name="t3c"]').attr("disabled",true);
				$('input:text[name="t3d1"]').attr("disabled",true);
				 $('input:text[name="t3d2"]').attr("disabled",true);
				 $('input:text[name="t3e"]').attr("disabled",true);
				 $('input:text[name="t3f1"]').attr("disabled",true);
				 $('input:text[name="t3f2"]').attr("disabled",true);
	
				 
		if($('input:text[name="t3a"]').is(":focus")== true){		
						
			setTimeout(function() {
			$('.t').find('input:text[name="forwardt"]').focus();
			},0);		
				}			 		 
			}
		

	});
		
	$('#insert').click(function() {
		
		//배열선언

			var rcolNmArr = [];
		var tcolNmArr = [];
		var radioArr = [];
		var textArr = [];
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
		$('input:radio[class="r"]:checked').each(function(i){//체크된 리스트 저장
			
			value = $(this).val();
			var getNm = $(this).attr('name');
			console.log(value+"::::::[흡연]values");
			if(value != '' &&  getNm !=''){
				console.log(getNm+":::::::::::getNm["+i+"]")
				console.log(value+":[흡연 체크밸류]values["+i+"]");
				 radioArr.push(value);		
				 rcolNmArr.push(getNm);
				 
			}
        });
	
		var value1;
		$('input:text[class="t"]').each(function(i) {
			
			value1 =$(this).val();
			var  getNm = $(this).attr('name');
			
			if(value1 != '' &&  getNm !=''){
				console.log(getNm+":::::::::::getNm["+i+"]")
				console.log(value+":[흡연 체크밸류]values["+i+"]");
					 textArr.push(value1);		
					 tcolNmArr.push(getNm);	
						textArr.push(value1);
						tcolNmArr.push(getNm);	
			}
			


		});
		
		
		
		
		var num;
		var num1;
		 num = $('input:radio[class="r"]:checked').length;
 		console.log(num+"저장된체크넘버");
 		num1 = $('input:radio[class="r"]:not(:checked)').length;
		

		var objParams = {
			"rcolNmArr" : rcolNmArr,
			"tcolNmArr" : tcolNmArr,
			"radioArr" : radioArr,
			"textArr" : textArr,
			"sdate" : sdates,  
			"exam_id" : exam_id,			
			"idx" : idx,
			"whichChk" : whichChk,
			"exam_num" : exam_num,
			"exam_idx" : exam_idx,
			"cdbs" : cdbs
 		};
		
		//ajax호출
		$.ajax({
			url         :   getContextPath()+"/include/sheet/view_savesm.do",
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
	
	

		
//if($("input:radio[name=r"+i+"]").is("checked") == true){
		
//	$("input:radio[name=r"+i+"]").attr("disabled", fal); //비활성화	
	
	
//}				
	//var sdateVal = ('input:text[name="sdate"]').value();
//	var sdateVal = document.getElementById('sdate').value();	
	//var sdateVal = $('#ddate').val();	
	//var ddateVal = $('#ddate').val();
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
<%!
/*
	public String make_radio(String v) {
		String checked1 = "";
		String checked2 = "";
		if ( "1".equals(v) ) checked1 = "checked";
		if ( "2".equals(v) ) checked2 = "checked";
		String radioTag = "<input type='radio' class='r' " + checked1 + " style='left:16px;top:5px;'>"
		+ "<input type='radio' class='r'  " + checked2 + " style='left:100px;top:5px;'>";
		return radioTag;
	}

	public String make_radio_v(String v) {
		String checked1 = "";
		String checked2 = "";
		String checked3 = "";
		String checked4 = "";
		String checked5 = "";
		String checked6 = "";

		if ( "1".equals(v) ) checked1 = "checked";
		if ( "2".equals(v) ) checked2 = "checked";
		if ( "3".equals(v) ) checked3 = "checked";
		if ( "4".equals(v) ) checked3 = "checked";
		if ( "4".equals(v) ) checked3 = "checked";
		if ( "5".equals(v) ) checked3 = "checked";
		if ( "6".equals(v) ) checked3 = "checked";
		
		String radioTag = "<input type='radio' class='r' " + checked1 + " style='left:16px;top:4px;'>"
		+ "<input type='radio' class='r'  " + checked2 + " style='left:100px;top:4px;'>"
		+ "<input type='radio' class='r'  " + checked3 + " style='left:190px;top:4px;'>"
		+ "<input type='radio' class='r'  " + checked4 + " style='left:280px;top:4px;'>"
		+ "<input type='radio' class='r'  " + checked5 + " style='left:360px;top:4px;'>"
		+ "<input type='radio' class='r'  " + checked6 + " style='left:450px;top:4px;'>";
		
		return radioTag;
	}
	*/
%>
<div id="btnArea" class="btn_wrap top">
	<input type ="hidden" id="action" value="${action}" style="display:none;"/>
	<!-- <button name="저장" id="insert" class ="csbr1010btn" style="display:none;">저장</button> -->
	<button name="수정" id="edit" class ="csbr1010btn"  value="edit">수정</button>
	<button name="신규" id ="new" class ="csbr1010btn"  value="new">신규</button>
	<input type="hidden" id ="object_idx" style="display:none;" class ="object_idx" value='<%= smvo == null ? "" :StringUtil.NVL  (smvo.getObjectIdx())%>'>
	<input type='hidden' name='selectionNum' id="selectionNum"  value='<%= smvo == null ? "" :StringUtil.NVL(smvo.getSelectionNum())%>'> 
	<input type='hidden' name='examIdx' id="examIdx"  value='<%= smvo == null ? "" :StringUtil.NVL(smvo.getIdx())%>'> 
	<input name="idx" id ="idx" class ="idx" value="${idx}" style="display:none;"/>
	<input type ="hidden" name="exnum" id ="exnum" class ="exnum" value="${exam_num}" style="display:none;"/>
	<input type ="hidden" name="exid" id ="exid" class ="exid" value="${exam_id}" style="display:none;"/> 
	<input type ="hidden" name="cdbs" id ="cdbs" class ="cdbs" value="0016" style="display:none;"/>  
	<input type ="hidden" name="chk" id ="chk" class ="chk" value="" style="display:none;"/>
</div>

<div id="tabbox" style="height:auto;">

	<table class="table type02">
        <colgroup>
            <col width="40px" />
            <col width="*" />
        </colgroup>
        <thead>
            <tr>
                <th class="bg1 tbl_tit" colspan="2">흡연</th>
            </tr>
            <tr>
                <th class="survey_date" colspan="2">
                    <div class="sv_text">설문실행일</div>
                    <div class="sv_date">
						<input type='text' class='calendar' id='sdate' name='sdate' placeholder='yyyy/mm/dd'  style='width:120px;' value ='<%= smvo == null ? "" :StringUtil.NVL(DateUtil.getSimpleDate("yyyyMMdd",smvo.getTestDay()))%>'>
					</div>
                </th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <th>1.</th>
                <td class="ta_l">
                    <p>지금까지 살아오는 동안 피운 담배의 양은 총 얼마나 됩니까?</p>
                    <div class="ans first"><label><input type='radio' name ='r1' class='r' value="1" <% if("1".equals((smvo == null ? "" : StringUtil.NVL(smvo.getSm01())))){%>checked <%}%>><span>5갑(100개비) 미만</span></label></div>
                    <div class="ans"><label><input type='radio' name ='r1' class='r' value="2" <% if("2".equals((smvo == null ? "" : StringUtil.NVL(smvo.getSm01())))){%>checked <%}%>><span>5갑(100개비) 이상</span></label></div>
                    <div class="ans"><label><input type='radio' name ='r1' class='r' value="3" <% if("3".equals((smvo == null ? "" : StringUtil.NVL(smvo.getSm01())))){%>checked <%}%>><span>피운 적 없다</span></label> <span class="fw_b">( → 4번으로)</span></div>
                </td>
            </tr>
            <tr>
                <th>2.</th>
                <td class="ta_l">
                    <p>처음으로 담배 한 개비를 다 피운시기는 언제입니까?</p>
                    <div class="ans first"><p>만<input type='text' id="num" name='t3age' class='t'minlength="1" maxlength="2" value='<%=(smvo == null ? "" : StringUtil.NVL(smvo.getSm02()))%>'>세</p></div>
                </td>
            </tr>
            <tr>
                <th>3.</th>
                <td class="ta_l">
                    <p>현재 담배를 피우십니까?</p>

                    <div class="ans first">
                        <label class="input_r"><input type='radio' name ='r2' class='r' value="1" <% if("1".equals((smvo == null ? "" : StringUtil.NVL(smvo.getSm03())))){%>checked <%}%>><span>매일 피운다</span></label>
                        <div class="box_q">
                            <span class="arrow"></span>
                            <p><span class="num_s">3-1. </span>하루 평균 담배 흡연량은 몇 개비 입니까?</p>
                            <p><input type='text' id="num" name='t3a' minlength="1" maxlength="2" class='t' value='<%=(smvo == null ? "" : StringUtil.NVL(smvo.getSm03a()))%>' style="margin-left:0;">개비 <span class="fw_b">( → 4번으로)</span></p>
                        </div>
                    </div>
                    <div class="ans mt_5">
                        <label class="input_r"><input type='radio' name ='r2' class='r' value="2" <% if("2".equals((smvo == null ? "" : StringUtil.NVL(smvo.getSm03())))){%>checked <%}%>><span>가끔 피운다</span></label>
                        <div class="box_q">
                            <span class="arrow"></span>
                            <p><span class="num_s">3-2. </span>최근 1달 동안 담배 흡연 일수는 며칠입니까?</p>
                            <p><input type='text' id="num" name='t3b'class='t' minlength="1" maxlength="2" value='<%=(smvo == null ? "" : StringUtil.NVL(smvo.getSm03b()))%>' style="margin-left:0;">일 <span class="fw_b">( → 4번으로)</span></p>

                            <p class="mt_5"><span class="num_s">3-3. </span>담배를 흡연할 날 하루 평균 흡연량은 몇 개비 입니까?</p>
                            <p><input type='text' placeholder="2자제한" id="num" name='t3c' class='t' minlength="1" maxlength="2" value='<%=(smvo == null ? "" : StringUtil.NVL(smvo.getSm03c()))%>' style='margin-left:0;'>개비 <span class="fw_b">( → 4번으로)</span></p>
                        </div>
                    </div>
                    <div class="ans mt_5">
                        <label class="input_r"><input type='radio' name ='r2' class='r' value="3" <% if("3".equals((smvo == null ? "" : StringUtil.NVL(smvo.getSm03())))){%>checked <%}%>><span>과거에는 피웠으나 현재 피우지 않는다</span></label>
                        <div class="box_q">
                            <span class="arrow"></span>
                            <p><span class="num_s">3-4. </span>과거 담배 흡연 기간은 얼마나 되셨습니끼?</p>
                            <p><input type='text' id="num" name='t3d1'class='t' minlength="1" maxlength="2" value='<%=(smvo == null ? "" : StringUtil.NVL(smvo.getSm03d1()))%>' style='margin-left:0;'>년 <input type='text' placeholder="2자제한" id="num" name='t3d2'class='t'minlength="1" maxlength="2" value='<%=(smvo == null ? "" : StringUtil.NVL(smvo.getSm03d2()))%>'>개월</p>

                            <p class="mt_5"><span class="num_s">3-5. </span>과거 담배를 피울 때 하루 평균 흡연량은 얼마나 됩니까?</p>
                            <p><input type='text' placeholder="2자제한" id="num" name='t3e'class='t' minlength="1" maxlength="2" value='<%=(smvo == null ? "" : StringUtil.NVL(smvo.getSm03e()))%>' style='margin-left:0;'>개비</p>

                            <p class="mt_5"><span class="num_s">3-6. </span>담배를 끊은지 얼마나 되셨습니까?</p>
                            <p><input type='text' placeholder="2자제한" id="num" name='t3f1' class='t' minlength="1" maxlength="2" value='<%=(smvo == null ? "" : StringUtil.NVL(smvo.getSm03f1()))%>' style='margin-left:0;'>년 <input type='text' placeholder="2자제한" id="num" name='t3f2' class='t' minlength="1" maxlength="2" value='<%=(smvo == null ? "" : StringUtil.NVL(smvo.getSm03f2()))%>'>개월 <span class="fw_b">( → 4번으로)</span></p>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <th>4.</th>
                <td class="ta_l">
                    <p>평생 동안 담배를 피운 기간(년)</p>
                    <div class="ans first">
                        <input type='text'  id="num" name="forwardt" class='t' value='<%=(smvo == null ? "" : StringUtil.NVL(smvo.getSm04()))%>'>
                    </div>
                </td>
            </tr>
        </tbody>
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
