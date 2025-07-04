<%@page import="java.sql.Date"%>
<%@page import="csbrain.common.service.MbSmVO"%>
<%@page import="csbrain.common.service.MbDrVO"%>
<%@page import="csbrain.common.service.MbPhaVO"%>
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


	List<MbPhaVO> pha_list = (List<MbPhaVO>) request.getAttribute("pha_list");
	MbPhaVO phavo = null;
//	MbC3VO c3vo = null;

	if(pha_list != null && pha_list.size() > 0 ){
		phavo = pha_list.get(0);
		

		
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
	height: 680px;
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
	
.ans > label {margin-right:20px;}
.ans > label:last-child {margin-right:0;}
.ans.ans_many {position:relative;margin-bottom:5px;}
.ans.ans_many:before {content:"";display:block;position:absolute;top:-104px;left:0;z-index:-1;width:80px;height:120px;border:1px solid #333;}
.ans.ans_many .box_q {position:absolute;top:-104px;left:88px;margin-left:18px;}
.ans.ans_many .box_q .arrow {left:-25px;width:20px;border-left:0;}
	
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
		
		$('input:text[id="num"]').keyup(function() {
			var regNumber = /^[0-9]*$/;
			var input = $(this).val();
			var maxLimit = $(this).attr("maxlength"); 
			var minLimit = $(this).attr("minlength");
			if(!regNumber.test(input)){
		        alert("숫자만 입력가능합니다");
				//$('input:text[id="num"]').val(input.replace(/[^0-9]/g , ""));
				}
			if(this.value.length >= maxLimit){	
				$(this).next('input:text[id="num"]').focus();
				return false;
			}else if(this.value.length < minLimit){
				$(this).focus();
				return false;
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
		$('input:text[class="t"]').attr("disabled", false);	
	   	 $(this).prop('checked', false);
		
			
	   	 	var selnew = $("#edit").val(); 
	   	 	console.log(selnew+"::selenew")
			checking = $('#chk').val(selnew);
		});
	
	$('input:radio[name="r1"]').click(function() {
		
		var r1val = $(this).val();
		
		if(r1val== "1"){
			
			
			
			 $('input:radio[class="r"]:not(:checked)').attr("disabled", false); //비활성화
			// $('input:radio[class="r"]:not(:checked)').attr("disabled", true); //비활성화
			 $('input:radio[name="r1"]:not(:checked)').attr("disabled", false);		
			 $('input:text[name="t3a"]').attr("disabled",false);
			 $('input:text[name="t3a"]').focus();
		/*	if($('input:radio[name="r2"]').is("checked") == true || !($('input:text[id="num"]').val() == null)){
		
				$('input:radio[name="r2"]').prop("checked",false)
				
			}
		*/	
				
			
		}else if(r1val== "2"){
			 $('input:radio[class="r"]:not(:checked)').attr("disabled", false); //비활성화
			
			// $('input:text[name="forwardt"]').attr("disabled",true);
		}
		
		


	});	
		
	

	
	$('input:radio[name="r2"]').click(function() {		
		
	var r2val=	$(this).val();
		
	if(r2val == "1"){
		 $('input:radio[class="r1"]:not(:checked)').attr("disabled", true); //비활성화
	//	 $('input:text[class="t"]').attr("disabled",true);		
		 $('input:text[name="t3d"]').attr("disabled",false);
		 $('input:radio[name="r2"]:not(:checked)').attr("disabled", false);

			 $('input:text[name="t3d"]').focus();
				
					
	}else if(r2val == "2" ){
		// $('input:radio[class="r"]:not(:checked)').attr("disabled", true); //비활성화
		 $('input:radio[name="r2"]:not(:checked)').attr("disabled", false);
		
			}
	
	
		
	});
	
	
		$('input:radio[name="r3"]').click(function() {
		
		var r1val = $(this).val();
		
		if(r1val== "1"){
			
			
			
			 $('input:radio[class="r"]:not(:checked)').attr("disabled", false); //비활성화
			// $('input:radio[class="r"]:not(:checked)').attr("disabled", true); //비활성화
			 $('input:radio[name="r3"]:not(:checked)').attr("disabled", false);		
			 $('input:text[name="t3h"]').attr("disabled",false);
			 $('input:text[name="t3h"]').focus();
		/*	if($('input:radio[name="r2"]').is("checked") == true || !($('input:text[id="num"]').val() == null)){
		
				$('input:radio[name="r2"]').prop("checked",false)
				
			}
		*/	
				
			
		}else if(r1val== "2"){
			 $('input:radio[class="r"]:not(:checked)').attr("disabled", false); //비활성화
			
			// $('input:text[name="forwardt"]').attr("disabled",true);
		}
		
		


	});	
	
		$('input:radio[name="r4"]').click(function() {
			
			var r1val = $(this).val();
			
			if(r1val== "1"){
				
				
				
				 $('input:radio[class="r"]:not(:checked)').attr("disabled", false); //비활성화
				// $('input:radio[class="r"]:not(:checked)').attr("disabled", true); //비활성화
				 $('input:radio[name="r4"]:not(:checked)').attr("disabled", false);		
				 $('input:text[name="t3k"]').attr("disabled",false);
				 $('input:text[name="t3k"]').attr("disabled",false)
				 $('input:text[name="t3k"]').focus();
			/*	if($('input:radio[name="r2"]').is("checked") == true || !($('input:text[id="num"]').val() == null)){
			
					$('input:radio[name="r2"]').prop("checked",false)
					
				}
			*/	
					
				
			}else if(r1val== "2"){
				 $('input:radio[class="r"]:not(:checked)').attr("disabled", false); //비활성화
				
				// $('input:text[name="forwardt"]').attr("disabled",true);
			}
			
			


		});			
	
	
	$('input:radio[name="r5"]').click(function() {
			
			var r1val = $(this).val();
			
			if(r1val== "1"){
				
				
				
				 $('input:radio[class="r"]:not(:checked)').attr("disabled", false); //비활성화
				// $('input:radio[class="r"]:not(:checked)').attr("disabled", true); //비활성화
				 $('input:radio[name="r5"]:not(:checked)').attr("disabled", false);		
				 $('input:text[name="t3n"]').attr("disabled",false);
				 $('input:text[name="t3n"]').focus();
			/*	if($('input:radio[name="r2"]').is("checked") == true || !($('input:text[id="num"]').val() == null)){
			
					$('input:radio[name="r2"]').prop("checked",false)
					
				}
			*/	
					
				
			}else if(r1val== "2"){
				 $('input:radio[class="r"]:not(:checked)').attr("disabled", false); //비활성화
				
				// $('input:text[name="forwardt"]').attr("disabled",true);
			}
			
			


		});			
		
	$('input:radio[name="r6"]').click(function() {
		
		var r1val = $(this).val();
		
		if(r1val== "1"){
			
			
			
			 $('input:radio[class="r"]:not(:checked)').attr("disabled", false); //비활성화
			// $('input:radio[class="r"]:not(:checked)').attr("disabled", true); //비활성화
			 $('input:radio[name="r6"]:not(:checked)').attr("disabled", false);		
			 $('input:text[name="t3s"]').attr("disabled",false);
			 $('input:text[name="t3s"]').focus();
		/*	if($('input:radio[name="r2"]').is("checked") == true || !($('input:text[id="num"]').val() == null)){
		
				$('input:radio[name="r2"]').prop("checked",false)
				
			}
		*/	
				
			
		}else if(r1val== "2"){
			 $('input:radio[class="r"]:not(:checked)').attr("disabled", false); //비활성화
			
			// $('input:text[name="forwardt"]').attr("disabled",true);
		}
		
		


	});		
	
	$('input:radio[name="r1"]').change(function() {
	//	var r2Chkval = $('input:radio[name="r2"]:checked').val();
		if($('input:radio[name="r1"]:checked').val() =='1'){
			 $('input:text[name="t3a"]').attr("disabled",false);
			 $('input:text[name="t3b"]').attr("disabled",false);
			 $('input:text[name="t3c"]').attr("disabled",false);
			$('input:text[name="t3a"]').val('');
			$('input:text[name="t3b"]').val('');
			$('input:text[name="t3c"]').val('');
			 $('input:text[name="t3a"]').focus();

			
		}else if($('input:radio[name="r1"]:checked').val() =='2'){
			$('input:text[name="t3a"]').val('');
			$('input:text[name="t3b"]').val('');
			$('input:text[name="t3c"]').val('');
			$('input:text[name="t3a"]').attr("disabled",true);
			$('input:text[name="t3b"]').attr("disabled",true);
			$('input:text[name="t3c"]').attr("disabled",true);
			//$('input:text[name="t3age"]').focus();
			
		}
		
		
	});
	
	
	$('input:radio[name="r2"]').change(function() {
		var r2Chkval = $('input:radio[name="r2"]:checked').val();
		var r2val=	$(this).val();
		if($('input:radio[name="r2"]:checked').val() =='1'){
			 $('input:text[name="t3d"]').attr("disabled",false);
				$('input:text[name="t3e"]').attr("disabled",false);		
				$('input:text[name="t3f"]').attr("disabled",false);	
			$('input:text[name="t3d"]').val('');
			$('input:text[name="t3e"]').val('');
			$('input:text[name="t3f"]').val('');
			//$('input:text[name="t3c"]').val('');
			$('input:text[name="t3d"]').focus();

			if($('input:text[name="t3d"]').is(":focus")== true){				
				setTimeout(function() {
				$('.t').find('input:text[name="t3e"]').focus();
				},0);		
					}
			if($('input:text[name="t3e"]').is(":focus")== true){		
				setTimeout(function() {
				$('.t').find('input:text[name="t3f"]').focus();
				},0);		
					}				
		}else if(r2Chkval =='2'){
			$('input:text[name="t3d"]').val('');
			$('input:text[name="t3e"]').val('');
			$('input:text[name="t3f"]').val('');
			$('input:text[name="t3d"]').attr("disabled",true);
			$('input:text[name="t3e"]').attr("disabled",true);
			$('input:text[name="t3f"]').attr("disabled",true);
		}
		

	});
	
	

	$('input:radio[name="r3"]').change(function() {
		//	var r2Chkval = $('input:radio[name="r2"]:checked').val();
			if($('input:radio[name="r3"]:checked').val() =='1'){
				 $('input:text[name="t3h"]').attr("disabled",false);
				 $('input:text[name="t3i"]').attr("disabled",false);
				 $('input:text[name="t3j"]').attr("disabled",false);
				$('input:text[name="t3h"]').val('');
				$('input:text[name="t3i"]').val('');
				$('input:text[name="t3j"]').val('');
				 $('input:text[name="t3h"]').focus();
				
	
				
			}else if($('input:radio[name="r3"]:checked').val() =='2'){
				$('input:text[name="t3h"]').val('');
				$('input:text[name="t3i"]').val('');
				$('input:text[name="t3j"]').val('');
				$('input:text[name="t3h"]').attr("disabled",true);
				$('input:text[name="t3i"]').attr("disabled",true);
				$('input:text[name="t3j"]').attr("disabled",true);
				//$('input:text[name="t3age"]').focus();
				
			}
			
			
		});
	

	$('input:radio[name="r4"]').change(function() {
		//	var r2Chkval = $('input:radio[name="r2"]:checked').val();
			if($('input:radio[name="r4"]:checked').val() =='1'){
				 $('input:text[name="t3k"]').attr("disabled",false);
				 $('input:text[name="t3l"]').attr("disabled",false);
				 $('input:text[name="t3m"]').attr("disabled",false);
				$('input:text[name="t3k"]').val('');
				$('input:text[name="t3l"]').val('');
				$('input:text[name="t3m"]').val('');
				 $('input:text[name="t3k"]').focus();
				
	
				
			}else if($('input:radio[name="r4"]:checked').val() =='2'){
				$('input:text[name="t3k"]').val('');
				$('input:text[name="t3l"]').val('');
				$('input:text[name="t3m"]').val('');
				$('input:text[name="t3k"]').attr("disabled",true);
				$('input:text[name="t3l"]').attr("disabled",true);
				$('input:text[name="t3m"]').attr("disabled",true);
				//$('input:text[name="t3age"]').focus();
				
			}
			
			
		});
	
	$('input:radio[name="r5"]').change(function() {
		//	var r2Chkval = $('input:radio[name="r2"]:checked').val();
			if($('input:radio[name="r5"]:checked').val() =='1'){
				 $('input:text[name="t2n"]').attr("disabled",false);
				 $('input:text[name="t3o"]').attr("disabled",false);
				 $('input:text[name="t3p"]').attr("disabled",false);
				$('input:text[name="t3n"]').val('');
				$('input:text[name="t3o"]').val('');
				$('input:text[name="t3p"]').val('');
				 $('input:text[name="t3n"]').focus();
				
	
				
			}else if($('input:radio[name="r5"]:checked').val() =='2'){
				$('input:text[name="t3n"]').val('');
				$('input:text[name="t3o"]').val('');
				$('input:text[name="t3p"]').val('');
				$('input:text[name="t3n"]').attr("disabled",true);
				$('input:text[name="t3o"]').attr("disabled",true);
				$('input:text[name="t3p"]').attr("disabled",true);
				//$('input:text[name="t3age"]').focus();
				
			}
			
			
		});
	
	$('input:radio[name="r6"]').change(function() {
		//	var r2Chkval = $('input:radio[name="r2"]:checked').val();
			var r6n = $('input:radio[name="r6"]:checked').val();
			if(r6n =='1' || r6n =='2' || r6n =='3' || r6n =='4' || r6n =='5' || r6n =='6' || r6n =='7'){
				 $('input:text[name="t3s"]').attr("disabled",false);
				 $('input:text[name="t3t"]').attr("disabled",false);
				$('input:text[name="t3s"]').val('');
				$('input:text[name="t3t"]').val('');
				 $('input:text[name="t3s"]').focus();
				
	
				
			}else if(r6n =='2'){
				$('input:text[name="t3s"]').val('');
				$('input:text[name="t3t"]').val('');
				$('input:text[name="t3s"]').attr("disabled",true);
				$('input:text[name="t3t"]').attr("disabled",true);
				//$('input:text[name="t3age"]').focus();
				
			}
			
			
		});
	
	$('#insert').click(function() {
		
		//배열선언

		var radioArr = [];
		var textArr = [];
		var textArr = [];
		var tcolNmArr = [];
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
		var  textNm1;
		var  textNm2;
		var  textNm3;
		$('input:radio[class="r"]:checked').each(function(){//체크된 리스트 저장
			
			value = $(this).val();
			var  getNm = $(this).attr('name');
			
			//var getNm = $(this).attr('name');	
			console.log(value+"::::::[pha::]values");
			var Fval =  $('input:radio[class="r"]:checked').length;
			console.log(Fval+"체크한카운트")
			var Fval =  $('input:radio[class="r"]:checked').length;
			console.log(Fval+"체크한카운트")
			
			textNm1 = $("t3a").val();
			 console.log(textNm1)
			 textNm2 = $("t3b").val();
			 console.log(textNm2)
			 textNm3 = $("t3c").val();
			 console.log(textNm2)	
			
			if(Fval < 7){
				alert("체크되지 않는 입력값이 있습니다!")
				return false;
			}else{
				radioArr.push(value);	
			}
			
			
			
	
			
        

			
			
		});
	
		var value1;
		$('input:text[class="t"]').each(function(i) {
			
			value1 =$(this).val();
			var  getNm = $(this).attr('name');
			
			if(value1 != '' &&  getNm !=''){
				console.log(getNm+":::::::::::getNm["+i+"]");
				console.log(value1+":[pha 텍스트]values1["+i+"]");
					 textArr.push(value1);		
					 tcolNmArr.push(getNm);	 
			}else if(value1 == '' && getNm == 't3q'){
				 alert("4-1번 문항 분 시간 값을 입력해주세요!")
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
			"textArr" : textArr,
			"tcolNmArr" : tcolNmArr,
			"sdate" : sdates,  
			"idx" : idx,
			"whichChk" : whichChk,
			"exam_num" : exam_num,
			"exam_idx" : exam_idx,
			"exam_id" : exam_id,
			"cdbs" : cdbs
	
		};
		
		//ajax호출
		$.ajax({
			url         :   getContextPath()+"/include/sheet/view_savepha.do",
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

<div id="btnArea" class="btn_wrap top">
	<input type ="hidden" id="action" value="${action}" style="display:none;"/>
	<!-- <button name="저장" id="insert" class ="csbr1010btn" style="display:none;">저장</button> -->
	<button name="수정" id="edit" class ="csbr1010btn"  value="edit">수정</button>
	<button name="신규" id ="new" class ="csbr1010btn"  value="new">신규</button>

	<input type="hidden" id ="object_idx" style="display:none;" class ="object_idx" value='<%= phavo == null ? "" :StringUtil.NVL  (phavo.getObjectIdx())%>'>
	<input type='hidden' name='selectionNum' id="selectionNum"  value='<%= phavo == null ? "" :StringUtil.NVL(phavo.getSelectionNum())%>'> 
	<input type='hidden' name='examIdx' id="examIdx"  value='<%= phavo == null ? "" :StringUtil.NVL(phavo.getIdx())%>'> 
	<input name="idx" id ="idx" class ="idx" value="${idx}" style="display:none;"/>
	<input type ="hidden" name="exid" id ="exid" class ="exid" value="${exam_id}" style="display:none;"/>   
	<input type ="hidden" name="exnum" id ="exnum" class ="exnum" value="${exam_num}" style="display:none;"/>
	<input type ="hidden" name="cdbs" id ="cdbs" class ="cdbs" value="0018" style="display:none;"/>
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
                <th class="bg1 tbl_tit" colspan="2">신체활동</th>
            </tr>
            <tr>
                <th class="survey_date" colspan="2">
                    <div class="sv_text">설문실행일</div>
                    <div class="sv_date">
						<input type='text' class='calendar' id='sdate' name='sdate' placeholder='yyyy/mm/dd'  style='width:120px;'<%if(phavo == null) {%>value=""<%}
						else if(StringUtil.NVL(phavo.getTestDay()) == "" ){%>value=""<%}
						else{%>value="<%=StringUtil.NVL(DateUtil.getSimpleDate("yyyyMMdd",phavo.getTestDay()))%>"<%}%>>
					</div>
                </th>
            </tr>
			<tr>
				<th class="th_info" colspan="2">
                    다음은 평소 일주일 동안 본인이 참여하고 있는 다양한 신체활동 시간과 관련된 질문입니다.<br>
					신체적으로 활동적인 사람이 아니더라도 질물에 답해주세요.<br>
					'고강도 활동'은 격렬한 신체활동으로 숨이 많이 차거나 심장이 매우 빠르게 뛰는 활동을, <br>
                    '중강도 활동'은 중간 정도의 신체활동으로 숨이 약간 차거나 심장이 빠르게 뛰는 활동을 말합니다.
                </th>
			</tr>
        </thead>
        <tbody>
            <tr>
                <th>1.</th>
                <td class="ta_l">
                    <p>우선 본인이 일하는 시간을 생각해보세요. 일을 돈을 받는일, 돈을 받지않고 하는 일, 학교생활/교육, 집안일, 농업, 어업, 목축업, 구직과 같이 현재 하고 있는 것이라고 생각하시면 됩니다.(예: 직업, 학업, 집안일, 봉사활동, 학교 체육 수업 등)</p>
                </td>
            </tr>
            <tr>
                <th class="border_dash">1-1.</th>
                <td class="ta_l border_dash">
                    <p>본인의 일을 최소 10분 이상 계속 숨이 많이 차거나 심장이 매우 빠르게 뛰는 고강도 신체활동을 포함하고 있습니까?</p>
					<p class="txt_s ex"><span class="r_mark">※ </span>고강도 신체활동: 무거운 것을 들어올리거나 나르는 일(약 20kg 이상), 땅파기, 건설 현장에서의 노동, 계단으로 물건 나르기 등</p>

                    <div class="ans first">
                        <label class="input_r"><input type='radio' name ='r1' class='r' value="1" <% if("1".equals((phavo == null ? "" : StringUtil.NVL(phavo.getPha01())))){%>checked <%}%>><span>예</span></label>
                        <div class="box_q">
                            <span class="arrow"></span>
                            <p><span class="num_s">1-2. </span>평소 일주일 동안, 일과 관련된 고강도 신체활동을 며칠 하셨습니까?</p>
                            <p><span style="display:inline-block;width:50px;">일주일에</span><input type='text' id="num" name='t3a' class='t' minlength="1" maxlength="2" value='<%=(phavo == null ? "" : StringUtil.NVL(phavo.getPha01a()))%>'>일</p>

							<p class="mt_5"><span class="num_s">1-3. </span>평소 하루에 일과 관련된 고강도 신체활동을 몇 시간 하십니까?</p>
							<p><span style="display:inline-block;width:50px;">하루에</span><input type='text' id="num" name='t3b' class='t' minlength="1" maxlength="2" value='<%=(phavo == null ? "" : StringUtil.NVL(phavo.getPha01b()))%>'>시간 <input type='text' id="num" name='t3c' minlength="1" maxlength="2" class='t' value='<%=(phavo == null ? "" : StringUtil.NVL(phavo.getPha01c()))%>'>분</p>
                        </div>
                    </div>
                    <div class="ans mt_5">
                        <label class="input_r"><input type='radio' name ='r1' class='r' value="2" <% if("2".equals((phavo == null ? "" : StringUtil.NVL(phavo.getPha01())))){%>checked <%}%>><span>아니오</span></label>
                        
                    </div>
                </td>
            </tr>
            <tr>
                <th class="border_dash">1-4.</th>
                <td class="ta_l border_dash">
                    <p>본인의 일은 최소 10분 이상 계속 숨이 약간 차거나 심장이 약간 빠르게 뛰는 중강도 신체활동을 포함하고 있습니까?</p>
					<p class="txt_s ex"><span class="r_mark">※ </span>중강도 신체활동: 빠르게 걷기(일하는 중에), 가벼운 물건 나르기, 청소, 육아(목욕시키기, 아이 안아주기 등)</p>

                    <div class="ans first">
                        <label class="input_r"><input type='radio' name ='r2' class='r' value="1" <% if("1".equals((phavo == null ? "" : StringUtil.NVL(phavo.getPha01d())))){%>checked <%}%>><span>예</span></label>
                        <div class="box_q">
                            <span class="arrow"></span>
                            <p><span class="num_s">1-5. </span>평소 일주일 동안, 일과 관련된 중강도 신체활동을 며칠 하셨습니끼?</p>
                            <p><span style="display:inline-block;width:50px;">일주일에</span><input type='text' id="num" name='t3d' minlength="1" maxlength="2" class='t' value='<%=(phavo == null ? "" : StringUtil.NVL(phavo.getPha01e()))%>'>일</p>

							<p class="mt_5"><span class="num_s">1-6. </span>평소 하루에 일과 관련된 중강도 신체활동을 몇 시간 하십니까?</p>
                            <p><span style="display:inline-block;width:50px;">하루에</span><input type='text' id="num" name='t3e' minlength="1" maxlength="2" class='t' value='<%=(phavo == null ? "" : StringUtil.NVL(phavo.getPha01f()))%>'>시간 <input type='text' id="num" name='t3f' minlength="1" maxlength="2" class='t' value='<%=(phavo == null ? "" : StringUtil.NVL(phavo.getPha01g()))%>'>분</p>
                        </div>
                    </div>
                    <div class="ans mt_5">
                        <label class="input_r"><input type='radio' name ='r2' class='r' value="2" <% if("2".equals((phavo == null ? "" : StringUtil.NVL(phavo.getPha01d())))){%>checked <%}%>><span>아니오</span></label>
                    </div>
                </td>
            </tr>
			<tr>
                <th>2.</th>
                <td class="ta_l">
                    <p>앞서 말한 일과 관련된 신체활동은 제외합니다. 본인이 장소를 이동할 때, 어떻게 하시는지에 대해 묻겠습니다.</p>
                    <p class="txt_s ex"><span class="r_mark">※ </span>장소 이동 시 신체활동: 일하러 갈 때, 쇼핑 갈 때, 장보러 갈 때, 예배보러 갈 때, 학교 등·하교 시, 학원 갈 때 등</p>
                </td>
            </tr>
            <tr>
                <th class="border_dash">2-1.</th>
                <td class="ta_l border_dash">
                    <p>평소 장소를 이동할 때 최소 10분 이상 계속 걷거나 자전거 이용을 하십니까?</p>

                    <div class="ans first">
                        <label class="input_r"><input type="radio" name="r3" class='r' value="1"  <% if("1".equals((phavo == null ? "" : StringUtil.NVL(phavo.getPha02())))){%>checked <%}%>/><span>예</span></label>
                        <div class="box_q">
                            <span class="arrow"></span>
                            <p><span class="num_s">2-2. </span>평소 일주일 동안, 장소를 이동할 때 최소 10분 이상 계속 걷거나 자전거 이용을 며칠 하십니까?</p>
                            <p><span style="display:inline-block;width:50px;">일주일에</span><input type="text" id="num" name="t3h" class='t'  minlength="1"  maxlength="2" value='<%=(phavo == null ? "" : StringUtil.NVL(phavo.getPha02a()))%>'/>일</p>

                            <p class="mt_5"><span class="num_s">2-3. </span>평소 하루에 장소를 이동할 때 걷거나 자전거 이요을 몇 시간 하십니까?</p>
                            <p><span style="display:inline-block;width:50px;">하루에</span><input type="text" id="num" name="t3i"  class='t' minlength="1"  maxlength="2" value='<%=(phavo == null ? "" : StringUtil.NVL(phavo.getPha02b()))%>'/>시간 <input type="text" id="num" class='t' name="t3j"  minlength="1"  maxlength="2" value='<%=(phavo == null ? "" : StringUtil.NVL(phavo.getPha02c()))%>'/>분</p>
                        </div>
                    </div>
                    <div class="ans mt_5">
                        <label class="input_r"><input type="radio" name="r3" class='r' value="2"  <% if("2".equals((phavo == null ? "" : StringUtil.NVL(phavo.getPha02())))){%>checked <%}%> /><span>아니오</span></label>
                    </div>
                </td>
            </tr>
            <tr>
                <th>3.</th>
                <td class="ta_l">
                    <p>앞서 말한 일과 장소 이동 시 신체활동에 대해서는 제외합니다. 스포츠, 운동 및 여가 활동에 대하여 묻겠습니다.</p>
                </td>
            </tr>
            <tr>
                <th class="border_dash">3-1.</th>
                <td class="ta_l border_dash">
                    <p>평소 최소 10분 이상 계속 숨이 많이 차거나 심장이 매우 빠르게 뛰는 고강도의 스포츠, 운동 및 여가 활동을 하십니까?</p>
                    <p class="txt_s ex"><span class="r_mark">※ </span>예: 달리기, 줄넘기, 등산, 농구 시합, 수영, 배드민턴 등</p>

                    <div class="ans first">
                        <label class="input_r"><input type="radio" name="r4" class='r' value="1"  <% if("1".equals((phavo == null ? "" : StringUtil.NVL(phavo.getPha03())))){%>checked <%}%>/><span>예</span></label>
                        <div class="box_q">
                            <span class="arrow"></span>
                            <p><span class="num_s">3-2. </span>평소 일주일 동안, 고강도의 스포츠, 운동 및 여가 활동을 며칠 하십니까?</p>
                            <p><span style="display:inline-block;width:50px;">일주일에</span><input type="text" id="num" name="t3k" class='t'  minlength="1"  maxlength="2" value='<%=(phavo == null ? "" : StringUtil.NVL(phavo.getPha03a()))%>'/>일</p>

                            <p class="mt_5"><span class="num_s">3-3. </span>평소 하루에 고강도의 스포츠, 운동 및 여가 활동을 몇 시간 하십니까?</p>
                            <p><span style="display:inline-block;width:50px;">하루에</span><input type="text" id="num" name="t3l"  class='t' minlength="1"  maxlength="2" value='<%=(phavo == null ? "" : StringUtil.NVL(phavo.getPha03b()))%>' />시간 <input type="text" id="num" name="t3m"  class='t' minlength="1"  maxlength="2" value='<%=(phavo == null ? "" : StringUtil.NVL(phavo.getPha02c()))%>' />분</p>
                        </div>
                    </div>
                    <div class="ans mt_5">
                        <label class="input_r"><input type="radio" name="r4" class='r' value="2"  <% if("2".equals((phavo == null ? "" : StringUtil.NVL(phavo.getPha03())))){%>checked <%}%>/><span>아니오</span></label>
                    </div>
                </td>
            </tr>
            <tr>
                <th class="border_dash">3-4.</th>
                <td class="ta_l border_dash">
                    <p>평소 최소 10분 이상 계속 숨이 약간 차거나 심장이 약간 빠르게 뛰는 중강도의 스포츠, 운동 및 여가 활동을 하십니까?</p>
                    <p class="txt_s ex"><span class="r_mark">※ </span>예: 빠르게 걷기, 가볍게 뛰기(조깅), 웨이트 트레이닝(근력 운동), 골프, 댄스스포츠, 필라테스 등</p>

                    <div class="ans first">
                        <label class="input_r"><input type="radio" name="r5" class='r' value="1"  <% if("1".equals((phavo == null ? "" : StringUtil.NVL(phavo.getPha03d())))){%>checked <%}%> /><span>예</span></label>
                        <div class="box_q">
                            <span class="arrow"></span>
                            <p><span class="num_s">3-5. </span>평소 일주일 동안, 중강도의 스포츠, 운동 및 여가 활동을 며칠 하십니까?</p>
                            <p><span style="display:inline-block;width:50px;">일주일에</span><input type="text" id="num" name="t3n" class='t' minlength="1"  maxlength="2" value='<%=(phavo == null ? "" : StringUtil.NVL(phavo.getPha03e()))%>' />일</p>

                            <p class="mt_5"><span class="num_s">3-6. </span>평소 하루에 중강도의 스포츠, 운동 및 여가 활동을 몇 시간 하십니까?</p>
                            <p><span style="display:inline-block;width:50px;">하루에</span><input type="text" id="num" name="t3o" class='t' minlength="1"  maxlength="2" value='<%=(phavo == null ? "" : StringUtil.NVL(phavo.getPha03f()))%>'/>시간 <input type="text" iid="num" name="t3p" class='t'  minlength="1"  maxlength="2" value='<%=(phavo == null ? "" : StringUtil.NVL(phavo.getPha03g()))%>'/>분</p>
                        </div>
                    </div>
                    <div class="ans mt_5">
                        <label class="input_r"><input type="radio" name="r5" class='r' value="2"  <% if("2".equals((phavo == null ? "" : StringUtil.NVL(phavo.getPha03d())))){%>checked <%}%>/><span>아니오</span></label>
                    </div>
                </td>
            </tr>
            <tr>
                <th>4.</th>
                <td class="ta_l">
                    <p>다음은 자는 시간을 제외하고, 일할 때나 집에 있을 때, 장소를 이동할 때, 친구와 함께 할 때에 앉아 있거나 누워 있는 것에 대한 질문입니다.</p>
                    <p class="txt_s ex"><span class="r_mark">※ </span>예: 책상에 앚아 있기, 친구와 앉아 있기, 자동차·버스·기차를 이용해 이용해 이동하기, 책 읽기, 글쓰기, 카드놀이 하기, 텔레비전 보기, 게임 하기(닌텐도, 컴퓨터, 플레이스테이션), 인터넷 사용, 음악감상 등</p>
                </td>
            </tr>
            <tr>
                <th class="border_dash">4-1.</th>
                <td class="ta_l border_dash">
                    <p>평소 하루에 앉아 있거나, 누워 있는 시간이 몇 시간 입니까?</p>

                    <div class="ans first">
                        <p><span style="display:inline-block;width:50px;">하루에</span><input type="text" id="num" name="t3q"  class='t' minlength="1"  maxlength="2" value='<%=(phavo == null ? "" : StringUtil.NVL(phavo.getPha04a()))%>'/>시간 <input type="text" id="num" name="t3r" class='t' minlength="1"  maxlength="2" value='<%=(phavo == null ? "" : StringUtil.NVL(phavo.getPha04b()))%>'/>분</p>
                    </div>
                </td>
            </tr>
            <tr>
                <th>5.</th>
                <td class="ta_l">
                    <p>최근 1주일 동안 한번에 적어도 10분 이상 걸은 날은 며칠입니까?</p>
                    <p class="txt_s ex"><span class="r_mark">※ </span>예: 출퇴근 또는 등하교, 이동 및 운동을 위해 걷는 것을 모두 포함하여 대답해 주십시오.</p>

                    <div class="ans first"><label class="input_r"><input type="radio" name="r6" class='r' value="0"  <% if("0".equals((phavo == null ? "" : StringUtil.NVL(phavo.getPha05())))){%>checked <%}%>/><span>전혀 하지 않았다</span></label> <span>( → 6번으로)</span></div>
                    <div class="ans"><label class="input_r"><input type="radio" name="r6" class='r' value="1"  <% if("1".equals((phavo == null ? "" : StringUtil.NVL(phavo.getPha05())))){%>checked <%}%> /><span>1일</span></label></div>
                    <div class="ans"><label class="input_r"><input type="radio" name="r6" class='r' value="2"  <% if("2".equals((phavo == null ? "" : StringUtil.NVL(phavo.getPha05())))){%>checked <%}%> /><span>2일</span></label></div>
                    <div class="ans"><label class="input_r"><input type="radio" name="r6" class='r' value="3"  <% if("3".equals((phavo == null ? "" : StringUtil.NVL(phavo.getPha05())))){%>checked <%}%>  /><span>3일</span></label></div>
                    <div class="ans"><label class="input_r"><input type="radio" name="r6" class='r' value="4"  <% if("4".equals((phavo == null ? "" : StringUtil.NVL(phavo.getPha05())))){%>checked <%}%>  /><span>4일</span></label></div>
                    <div class="ans"><label class="input_r"><input type="radio" name="r6" class='r' value="5"  <% if("5".equals((phavo == null ? "" : StringUtil.NVL(phavo.getPha05())))){%>checked <%}%>  /><span>5일</span></label></div>
                    <div class="ans"><label class="input_r"><input type="radio" name="r6" class='r' value="6"  <% if("6".equals((phavo == null ? "" : StringUtil.NVL(phavo.getPha05())))){%>checked <%}%>  /><span>6일</span></label></div>
                    <div class="ans ans_many"><label class="input_r"><input type="radio" name="r6" class='r' value="7"  <% if("7".equals((phavo == null ? "" : StringUtil.NVL(phavo.getPha05())))){%>checked <%}%> /><span>7일(매일)</span></label>
                        <div class="box_q">
                            <span class="arrow"></span>
                            <p><span class="num_s">5-1. </span>최근 1주일 동안 한번에 적어도 10분 이상 걸은 날 중 하루 동안 걷는 시간은 보통 얼마나 됩니까?</p>
                            <p><span style="display:inline-block;width:50px;">하루에</span><input type="text" id="num" name="t3s" class='t' minlength="1"  maxlength="2" value='<%=(phavo == null ? "" : StringUtil.NVL(phavo.getPha05a()))%>'/>시간 <input type="text" id="num" name="t3t" class='t'  minlength="1"  maxlength="2" value='<%=(phavo == null ? "" : StringUtil.NVL(phavo.getPha05b()))%>' />분</p>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <th>6.</th>
                <td class="ta_l">
                    <p>최근 1주일 동안 팔굽혀펴기, 윗몸일으키기, 아령, 역기, 철봉 등의 근력 운동을 한 날은 며칠입니까?</p>

                    <div class="ans first">
                        <label class="input_r"><input type="radio" name="r7" class='r' value="0"  <% if("0".equals((phavo == null ? "" : StringUtil.NVL(phavo.getPha06())))){%>checked <%}%>/><span>전혀 하지 않았다</span></label>
                        <label class="input_r"><input type="radio" name="r7" class='r' value="1"  <% if("1".equals((phavo == null ? "" : StringUtil.NVL(phavo.getPha06())))){%>checked <%}%>/><span>1일</span></label>
                        <label class="input_r"><input type="radio" name="r7" class='r' value="2"  <% if("2".equals((phavo == null ? "" : StringUtil.NVL(phavo.getPha06())))){%>checked <%}%> /><span>2일</span></label>
                        <label class="input_r"><input type="radio" name="r7" class='r' value="3"  <% if("3".equals((phavo == null ? "" : StringUtil.NVL(phavo.getPha06())))){%>checked <%}%> /><span>3일</span></label>
                        <label class="input_r"><input type="radio" name="r7" class='r' value="4"  <% if("4".equals((phavo == null ? "" : StringUtil.NVL(phavo.getPha06())))){%>checked <%}%> /><span>4일</span></label>
                        <label class="input_r"><input type="radio" name="r7" class='r' value="5"  <% if("5".equals((phavo == null ? "" : StringUtil.NVL(phavo.getPha06())))){%>checked <%}%> /><span>5일 이상</span></label>
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
