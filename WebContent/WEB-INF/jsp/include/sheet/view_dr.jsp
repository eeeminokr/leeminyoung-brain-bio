<%@page import="java.sql.Date"%>
<%@page import="csbrain.common.service.MbSmVO"%>
<%@page import="csbrain.common.service.MbDrVO"%>
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


	List<MbDrVO> dr_list = (List<MbDrVO>) request.getAttribute("dr_list");
	MbDrVO drvo = null;
//	MbC3VO c3vo = null;

	if(dr_list != null && dr_list.size() > 0 ){
		drvo =dr_list.get(0);
		
		String d = drvo.getTestDay();
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
	} 
	*/
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
		padding-left:1px;*/
		width: 60px;
	}
	#tabbox {

 /*	background-size: cover;
	*/
	width:608px;
	height: 1265px;
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

		if(r1val== "2"){
			 $('input:radio[class="r"]:not(:checked)').attr("disabled", false); //비활성화
			// $('input:radio[class="r"]:not(:checked)').attr("disabled", true); //비활성화
			 $('input:radio[name="r1"]:not(:checked)').attr("disabled", false);		
			 $('input:text[name="t3age"]').attr("disabled",false);
			 $('input:text[name="t3age"]').focus();
		/*	if($('input:radio[name="r2"]').is("checked") == true || !($('input:text[id="num"]').val() == null)){
		
				$('input:radio[name="r2"]').prop("checked",false)
				
			}
		*/	
				
			
		}else if(r1val== "1"){
			 $('input:radio[class="r"]:not(:checked)').attr("disabled", false); //비활성화
			
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
		 $('input:text[name="r1"]').attr("disabled",true);
		if($('input:radio[name="r1"]').is(":checked")==false){
			alert("1번 문항을 입력하지 않았습니다!")
			 $('input:radio[name="r2"]').prop("checked",false);
			 $('input:text[class="t"]').attr("disabled",true);	
			 $('input:text[class="tage"]').attr("disabled",true);	
	}else if($('input:radio[name="r1"]').next().is(":checked")==true && $('input:text[name="t3age"]').val()==''){
		alert("1-1번 문항을 입력하지 않았습니다!")
		$('input:text[name="t3age"]').focus();
			 $('input:radio[name="r2"]').prop("checked",false);
	}else if($('input:radio[name="r1"]').is(":checked")==true){
	//	$('input:radio[name="r1"]:checked').attr("disabled", true);		
		$('input:text[name="t3age"]').attr("disabled", true);		
	}
		
		
	var r2val=	$(this).val();
		
	if(r2val == "1"){
		 $('input:radio[class="r1"]:not(:checked)').attr("disabled", true); //비활성화
	//	 $('input:text[class="t"]').attr("disabled",true);		
		 $('input:text[name="t3a"]').attr("disabled",false);
		 $('input:radio[name="r2"]:not(:checked)').attr("disabled", false);

		 if($('input:text[name="t3a"]').is(":disabled")==false ){
			 $('input:text[name="t3a"]').focus();
		 }
			if($('input:text[name="t3b"]').is(":focus")== true){		
				setTimeout(function() {
					 $('input:text[name="t3b"]').attr("disabled",false);
				$('.t').find('input:text[name="t3c"]').focus();
				},0);
		
	
	}else if(r2val == "2" || r2val == "3" || r2val == "4" || r2val == "5" ||r2val == "6"){
		// $('input:radio[class="r"]:not(:checked)').attr("disabled", true); //비활성화
		 $('input:radio[name="r2"]:not(:checked)').attr("disabled", false);
		
			}
	
		
		}
	
		
	});
	
	$('input:radio[name="r3"]').click(function() {
		// $('input:text[name="r1"]').attr("disabled",true);
		if($('input:radio[name="r1"]').is(":checked")==false){
			alert("1번 문항을 입력하지 않았습니다!")
			 $('input:radio[name="r3"]').prop("checked",false);
			 $('input:text[class="t"]').attr("disabled",true);	
			 $('input:text[class="tage"]').attr("disabled",true);	
	}else if($('input:radio[name="r1"]').next().is(":checked")==true && $('input:text[name="t3age"]').val()==''){
		alert("1-1번 문항을 입력하지 않았습니다!")
		$('input:text[name="t3age"]').focus();
	//	 $('input:radio[name="r3"]').prop("checked",false);
	//	 $('input:radio[name="r2"]').prop("checked",false);
	}else if($('input:radio[name="r1"]').is(":checked")==true){
	//	$('input:radio[name="r1"]:checked').attr("disabled", true);		
		$('input:text[name="t3age"]').attr("disabled", true);		
	}else if($('input:radio[name="r2"]').is(":checked")==false){
		alert("2번 문항을 입력하지 않았습니다!")		
	}else if($('input:radio[name="r2"]').next().is(":checked")==true && $('input:text[name="t3a"]').val()==''){
		alert("2-1번 1번답 주관식을 입력하지 않았습니다!")
		$('input:text[name="t3b"]').focus();
	//	 $('input:radio[name="r3"]').prop("checked",false);
	}
		
		
	var r2val=	$(this).val();
		
	if(r2val == "5"){
		 $('input:radio[class="r1"]:not(:checked)').attr("disabled", true); //비활성화
		 $('input:radio[class="r2"]:not(:checked)').attr("disabled", true); //비활성화
	//	 $('input:text[class="t"]').attr("disabled",true);		
		 $('input:text[name="t3c"]').attr("disabled",false);
		 $('input:radio[name="r3"]:not(:checked)').attr("disabled", false);

			if($('input:text[name="t3c"]').is(":disabled")== false){		
				setTimeout(function() {
				$('.t').find('input:text[name="t3c"]').focus();
				},0);
		
	
	}else if(r2val == "1" || r2val == "2" || r2val == "3" || r2val == "4"){
		// $('input:radio[class="r"]:not(:checked)').attr("disabled", true); //비활성화
		 $('input:radio[name="r3"]:not(:checked)').attr("disabled", false);
				
			}
	
		
		}
	
		
	});
	
	
	$('input:radio[name="r4"]').click(function() {
		// $('input:text[name="r1"]').attr("disabled",true);
		if($('input:radio[name="r1"]').is(":checked")==false){
			alert("1번 문항을 입력하지 않았습니다!")
			 $('input:radio[name="r3"]').prop("checked",false);
			 $('input:text[class="t"]').attr("disabled",true);	
			 $('input:text[class="tage"]').attr("disabled",true);	
	}else if($('input:radio[name="r1"]').next().is(":checked")==true && $('input:text[name="t3age"]').val()==''){
		alert("1-1번 문항을 입력하지 않았습니다!")
		$('input:text[name="t3age"]').focus();
	}else if($('input:radio[name="r1"]').is(":checked")==true){
	//	$('input:radio[name="r1"]:checked').attr("disabled", true);		
		$('input:text[name="t3age"]').attr("disabled", true);		
	}else if($('input:radio[name="r2"]').is(":checked")==false){
		alert("2번 문항을 입력하지 않았습니다!")		
	}else if($('input:radio[name="r2"]').next().is(":checked")==true && $('input:text[name="t3a"]').val()==''){
		alert("2-1번 1번답 주관식을 입력하지 않았습니다!")
		$('input:text[name="t3b"]').focus();
		// $('input:radio[name="r3"]').prop("checked",false);
	}else if($('input:radio[name="r3"]').is(":checked")==false){
		alert("2-2번 문항을 입력하지 않았습니다!")		
	}else if($('input:radio[name="r3"]').next().is(":checked")==true && $('input:text[name="t3c"]').val()==''){
		alert("2-1번 1번답 주관식을 입력하지 않았습니다!")
		$('input:text[name="t3b"]').focus();
		// $('input:radio[name="r3"]').prop("checked",false);
	}
		
		

		
	});
	
	$('input:radio[name="r5"]').click(function() {
		// $('input:text[name="r1"]').attr("disabled",true);
		if($('input:radio[name="r1"]').is(":checked")==false){
			alert("1번 문항을 입력하지 않았습니다!")
			 $('input:radio[name="r3"]').prop("checked",false);
			 $('input:text[class="t"]').attr("disabled",true);	
			 $('input:text[class="tage"]').attr("disabled",true);	
	}else if($('input:radio[name="r1"]').next().is(":checked")==true && $('input:text[name="t3age"]').val()==''){
		alert("1-1번 문항을 입력하지 않았습니다!")
		$('input:text[name="t3age"]').focus();
	}else if($('input:radio[name="r1"]').is(":checked")==true){
	//	$('input:radio[name="r1"]:checked').attr("disabled", true);		
		$('input:text[name="t3age"]').attr("disabled", true);		
	}else if($('input:radio[name="r2"]').is(":checked")==false){
		alert("2번 문항을 입력하지 않았습니다!")		
	}else if($('input:radio[name="r2"]').next().is(":checked")==true && $('input:text[name="t3a"]').val()==''){
		alert("2-1번 1번답 주관식을 입력하지 않았습니다!")
		$('input:text[name="t3b"]').focus();
		// $('input:radio[name="r3"]').prop("checked",false);
	}else if($('input:radio[name="r3"]').is(":checked")==false){
		alert("2-2번 문항을 입력하지 않았습니다!")		
	}else if($('input:radio[name="r3"]').next().is(":checked")==true && $('input:text[name="t3c"]').val()==''){
		alert("2-1번 1번답 주관식을 입력하지 않았습니다!")
		$('input:text[name="t3b"]').focus();
		// $('input:radio[name="r3"]').prop("checked",false);
	}else if($('input:radio[name="r4"]').is(":checked")==false){
		alert("2-3번 문항을 입력하지 않았습니다!")		
	}	
	});
	
	
	
	$('input:radio[name="r1"]').change(function() {
		var r2Chkval = $('input:radio[name="r2"]:checked').val();
		if($('input:radio[name="r1"]:checked').val() =='1'){
			$('input:text[name="t3age"]').val('');
			$('input:text[name="t3age"]').attr("disabled",true);
			
		}else if($('input:radio[name="r1"]:checked').val() =='2'){
			$('input:text[name="t3age"]').focus();
			
		}
		
		
	});
	
	
	$('input:radio[name="r2"]').change(function() {
		var r2Chkval = $('input:radio[name="r2"]:checked').val();
		var r2val=	$(this).val();
		if($('input:radio[name="r2"]:checked').val() =='1'){
			 $('input:text[name="t3a"]').attr("disabled",false);
			 $('input:text[name="t3b"]').attr("disabled",false);
			$('input:text[name="t3a"]').val('');
			//$('input:text[name="t3c"]').val('');
			$('input:text[name="t3a"]').focus();
			if($('input:text[name="t3a"]').is(":focus")== true){		
				
				setTimeout(function() {
					
				$('.t').find('input:text[name="t3b"]').focus();
				},0);		
					}	

		}else if(r2Chkval =='2' || r2Chkval =='3' || r2Chkval =='4' || r2Chkval =='5' ||r2Chkval =='6' ){
			$('input:text[name="t3a"]').val('');
			$('input:text[name="t3b"]').val('');
			
			$('input:text[name="t3a"]').attr("disabled",true);
			 $('input:text[name="t3b"]').attr("disabled",true);
		
		}
		

	});
	
	
	$('input:radio[name="r3"]').change(function() {
		var r2Chkval = $('input:radio[name="r3"]:checked').val();
		var r2val=	$(this).val();
		if($('input:radio[name="r3"]:checked').val() =='5'){
			 $('input:text[name="t3c"]').attr("disabled",false);
			 $('input:text[name="t3c"]').val('');
			 $('input:text[name="t3c"]').focus();

		}else if(r2Chkval =='1' || r2Chkval =='2' || r2Chkval =='3' || r2Chkval =='4'  ){
			$('input:text[name="t3c"]').val('');
			$('input:text[name="t3c"]').attr("disabled",true);
		
		}
	});
	
	
	
	$('input:text[name="t3sum1"]').change(function() {
		
		 var getText1 = $('input:text[name="t3sum1"]').val();
		 var getText2 = $('input:text[name="t3sum2"]').val();
		 var getText3 = $('input:text[name="t3sum3"]').val();
		 var t3Total = 0;

		 //var r2val=	$(this).val();
		
		$('input:text[name="t3sum2"]').focus();
		if($('input:text[name="t3sum2"]').is(":focus")== true){
			setTimeout(function() {		
				$('.t').find('input:text[name="t3sum3"]').focus();
				},0);		
					}		
	 if($('input:text[name="t3sum3"]').is(":focus")== true){    	 

	      t3Total = Number(getText1)*Number(getText2);
	    
	      	setTimeout(function() {      		
	      $('input:text[name="t3sum3"]').focus();
	      $('input:text[name="t3sum3"]').val(t3Total);
			},0);		
	      }
	});
	
	$('input:text[name="t3sum1"]').keyup(function() {
		
		 var getText1 = $('input:text[name="t3sum1"]').val();
		 var getText2 = $('input:text[name="t3sum2"]').val();
		 var getText3 = $('input:text[name="t3sum3"]').val();
		 var t3Total = 0;

		 //var r2val=	$(this).val();
	
		$('input:text[name="t3sum1"]').focus();
		if($('input:text[name="t3sum1"]').is(":focus")== true){
			setTimeout(function() {		
			    
			      t3Total = Number(getText1)*Number(getText2);
			      $('input:text[name="t3sum3"]').val(t3Total);
				},0);		
					}		
	});
	
	
	$('input:text[name="t3sum2"]').keyup(function() {
		
		 var getText1 = $('input:text[name="t3sum1"]').val();
		 var getText2 = $('input:text[name="t3sum2"]').val();
		 var getText3 = $('input:text[name="t3sum3"]').val();
		 var t3Total = 0;

		 //var r2val=	$(this).val();
		
		$('input:text[name="t3sum2"]').focus();
		if($('input:text[name="t3sum2"]').is(":focus")== true){
			setTimeout(function() {		
			    
			      t3Total = Number(getText1)*Number(getText2);
			      $('input:text[name="t3sum3"]').val(t3Total);
				},0);		
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
			console.log(value+"::::::[음주]values");
		
			// radioArr.push(value);
				var Fval =  $('input:radio[class="r"]:checked').length;
				console.log(Fval+"체크한카운트")
				if(Fval < 5){
					alert("체크되지 않는 입력값이 있습니다!")
					return false;
				}else{
					 radioArr.push(value);		
				}
			 
        });
	
		var value1;
		$('input:text[class="t"]').each(function(i) {
			
			value1 =$(this).val();
			
			console.log(value1+":[음주 텍스트]values1["+i+"]");
			textArr.push(value1);
		});
		
		
		
		
		var num;
		var num1;
		 num = $('input:radio[class="r"]:checked').length;
 		console.log(num+"저장된체크넘버");
 		num1 = $('input:radio[class="r"]:not(:checked)').length;
		

		var objParams = {
			"radioArr" : radioArr,
			"textArr" : textArr,	
			"sdate" : sdates,  
			"idx" : idx,
			"whichChk" : whichChk,
			"exam_id" : exam_id,
			"exam_num" : exam_num,
			"exam_idx" : exam_idx,
			"cdbs" : cdbs
	
		};
		
		//ajax호출
		$.ajax({
			url         :   getContextPath()+"/include/sheet/view_savedr.do",
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
<div class="btn_wrap top">
	<!-- <button name="저장" id="insert" class ="csbr1010btn" style="display:none;">저장</button> -->
	<input type ="hidden" id="action" value="${action}" style="display:none;"/>
	<button name="수정" id="edit" class ="csbr1010btn"  value="edit">수정</button>
	<button name="신규" id ="new" class ="csbr1010btn"  value="new">신규</button>

	<input type="hidden" id ="object_idx" style="display:none;" class ="object_idx" value='<%= drvo == null ? "" :StringUtil.NVL  (drvo.getObjectIdx())%>'>
	<input type='hidden' name='selectionNum' id="selectionNum"  value='<%= drvo == null ? "" :StringUtil.NVL(drvo.getSelectionNum())%>'> 
	<input type='hidden' name='examIdx' id="examIdx"  value='<%= drvo == null ? "" :StringUtil.NVL(drvo.getIdx())%>'> 
	<input name="idx" id ="idx" class ="idx" value="${idx}" style="display:none;"/>
	<input type ="hidden" name="exid" id ="exid" class ="exid" value="${exam_id}" style="display:none;"/> 
	<input type ="hidden" name="exnum" id ="exnum" class ="exnum" value="${exam_num}" style="display:none;"/>
	<input type ="hidden" name="cdbs" id ="cdbs" class ="cdbs" value="0017" style="display:none;"/>
	<input type ="hidden" name="chk" id ="chk" class ="chk" value="" style="display:none;"/>
</div>

<div id="tabbox" style="height:auto;">

	<!-- 음주 설문지 HTML -->
	<table class="table type02">
        <colgroup>
            <col width="40px" />
            <col width="*" />
        </colgroup>
        <thead>
            <tr>
                <th class="bg1 tbl_tit" colspan="2">음주</th>
            </tr>
            <tr>
                <th class="survey_date" colspan="2">
                    <div class="sv_text">설문실행일</div>
                    <div class="sv_date">
						<input type='text' class='calendar' id='sdate' name='sdate' placeholder='yyyy/mm/dd'  style='width:120px;' value ='<%= drvo == null ? "" :StringUtil.NVL(DateUtil.getSimpleDate("yyyyMMdd",drvo.getTestDay()))%>'>
					</div>
                </th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <th>1.</th>
                <td class="ta_l">
                    <p>지금까지 살아오면서 1잔 이상의 술을 마신 적이 있습니까?</p>
                    <p class="txt_s">* 제사, 차례 때 몇 모금 마셔본 것은 제외합니다.</p>
                    <div class="ans">
						<label><input type='radio' name ='r1' class='r' value="1" <% if("1".equals((drvo == null ? "" : StringUtil.NVL(drvo.getDr01())))){%>checked <%}%>><span>술을 마셔 본 적 없다</span></label>
					</div>
                    <div class="ans">
                        <label><input type='radio' name ='r1' class='r' value="2" <% if("2".equals((drvo == null ? "" : StringUtil.NVL(drvo.getDr01())))){%>checked <%}%>><span>있다</span></label>
                        <div class="box_q">
							<span class="arrow"></span>
                            <p><span class="num_s">1-1. </span>처음으로 술 1잔을 모두 마셔본 적은 언제 입니까?</p>
                            <p class="txt_s">* 제사, 차례 때 몇 모금 마셔본 것은 제외합니다.</p>
                            <p>만 <input type='text' id="num" name='t3age' class='t'minlength="1" maxlength="2" value='<%=(drvo == null ? "" : StringUtil.NVL(drvo.getDr01a()))%>'>세</p>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <th>2.</th>
                <td class="ta_l">
                    <p>다음은 <span class="underline">최근 1년 동안</span>의 음주(술) 경험에 대한 질문입니다.</p>
                </td>
            </tr>
            <tr>
                <th class="border_dash">2-1.</th>
                <td class="ta_l border_dash">
                    <p>술을 얼마나 자주 마십니니까?</p>
                    <div class="ans first">
                        <label class="input_r"><input type='radio' name ='r2' class='r' value="1" <% if("1".equals((drvo == null ? "" : StringUtil.NVL(drvo.getDr02a())))){%>checked <%}%>><span>최근 1년간 전혀 마시지 않았다</span></label>
                        <div class="box_q">
							<span class="arrow"></span>
                            <input type='text' id="num" name='t3a' minlength="1" maxlength="2" class='t' value='<%=(drvo == null ? "" : StringUtil.NVL(drvo.getDr02b()))%>'>년 <input type='text' id="num" name='t3b' minlength="1" maxlength="2" class='t' value='<%=(drvo == null ? "" : StringUtil.NVL(drvo.getDr02c()))%>'>개월 전에 끊었다
                        </div>
                    </div>
                    <div class="ans"><label><input type='radio' name ='r2' class='r' value="2" <% if("2".equals((drvo == null ? "" : StringUtil.NVL(drvo.getDr02a())))){%>checked <%}%>><span>한달에 1번 미만</span></label></div>
                    <div class="ans"><label><input type='radio' name ='r2' class='r' value="3" <% if("3".equals((drvo == null ? "" : StringUtil.NVL(drvo.getDr02a())))){%>checked <%}%>><span>한달에 1번 정도</span></label></div>
                    <div class="ans"><label><input type='radio' name ='r2' class='r' value="4" <% if("4".equals((drvo == null ? "" : StringUtil.NVL(drvo.getDr02a())))){%>checked <%}%>><span>한달에 2~4번</span></label></div>
                    <div class="ans"><label><input type='radio' name ='r2' class='r' value="5" <% if("5".equals((drvo == null ? "" : StringUtil.NVL(drvo.getDr02a())))){%>checked <%}%>><span>일주일에 2~3번 정도</span></label></div>
                    <div class="ans"><label><input type='radio' name ='r2' class='r' value="6" <% if("6".equals((drvo == null ? "" : StringUtil.NVL(drvo.getDr02a())))){%>checked <%}%>><span>일주일에 4번 이상</span></label></div>
                </td>
            </tr>
            <tr>
                <th class="border_dash">2-2.</th>
                <td class="ta_l border_dash">
                    <p>한번에 술을 얼마나 자주 마십니니까?</p>
                    <p class="txt_s">* 소주, 양주 구분 없이 각각의 술잔으로 계산합니다. 단 캔맥주 1개(355cc)는 맥주 1.6과 같습니다.</p>
                    <div class="ans first"><label><input type='radio' name ='r3' class='r' value="1" <% if("1".equals((drvo == null ? "" : StringUtil.NVL(drvo.getDr02d())))){%>checked <%}%>><span>1~2잔</span></label></div>
                    <div class="ans"><label><input type='radio' name ='r3' class='r' value="2" <% if("2".equals((drvo == null ? "" : StringUtil.NVL(drvo.getDr02d())))){%>checked <%}%>><span>3~4잔</span></label></div>
                    <div class="ans"><label><input type='radio' name ='r3' class='r' value="3" <% if("3".equals((drvo == null ? "" : StringUtil.NVL(drvo.getDr02d())))){%>checked <%}%>><span>5~6잔</span></label></div>
                    <div class="ans"><label><input type='radio' name ='r3' class='r' value="4" <% if("4".equals((drvo == null ? "" : StringUtil.NVL(drvo.getDr02d())))){%>checked <%}%>><span>7~9잔</span></label></div>
                    <div class="ans">
                        <label><input type='radio' name ='r3' class='r' value="5" <% if("5".equals((drvo == null ? "" : StringUtil.NVL(drvo.getDr02d())))){%>checked <%}%>><span>10잔 이상</span></label>
                        <div class="box_q">
							<span class="arrow"></span>
                            <p><span class="num_s">2-2-1. </span>한번에 술을 얼마나 마십니까?</p>
                            <p class="txt_s">숫자로 기입해 주십시오 * 소주는 1병은 7잔과 같습니다.</p>
                            <p><input type='text' id="num" name='t3c' minlength="1" maxlength="2" class='t' style="margin-left:0;" value='<%=(drvo == null ? "" : StringUtil.NVL(drvo.getDr02e()))%>'>잔</p>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <th class="border_dash">2-3.</th>
                <td class="ta_l border_dash">
                    <p>한 번의 술자리에서 소주, 양주 구분 없이 각각의 술잔으로 7잔(또는 맥주 5캔 정도) 이상을 마시는 횟수는 어느 정도입니까?</p>
                    <div class="ans first"><label><input type='radio' name ='r4' class='r' value="1" <% if("1".equals((drvo == null ? "" : StringUtil.NVL(drvo.getDr02f())))){%>checked <%}%>><span>전혀 없다</span></label></div>
                    <div class="ans"><label><input type='radio' name ='r4' class='r' value="2" <% if("2".equals((drvo == null ? "" : StringUtil.NVL(drvo.getDr02f())))){%>checked <%}%>><span>한달에 1번 미만</span></label></div>
                    <div class="ans"><label><input type='radio' name ='r4' class='r' value="3" <% if("3".equals((drvo == null ? "" : StringUtil.NVL(drvo.getDr02f())))){%>checked <%}%>><span>한달에 1번 정도</span></label></div>
                    <div class="ans"><label><input type='radio' name ='r4' class='r' value="4" <% if("4".equals((drvo == null ? "" : StringUtil.NVL(drvo.getDr02f())))){%>checked <%}%>><span>일주일에 1번 정도</span></label></div>
                    <div class="ans"><label><input type='radio' name ='r4' class='r' value="5" <% if("5".equals((drvo == null ? "" : StringUtil.NVL(drvo.getDr02f())))){%>checked <%}%>><span>거의 매일</span></label></div>
                </td>
            </tr>
            <tr>
                <th class="border_dash">2-4.</th>
                <td class="ta_l border_dash">
                    <p><span class="unerline">(여성분만 응답해 주십시오)</span> 한번의 술자리에서 소주, 양주 구분 없이 각각의 술자으로 5잔(또는 맥주 3캔 정도) 이상을 마시는 횟수는 어느 정도입니까?</p>
                    <div class="ans first"><label><input type='radio' name ='r5' class='r' value="1" <% if("1".equals((drvo == null ? "" : StringUtil.NVL(drvo.getDr02g())))){%>checked <%}%>><span>전혀 없다</span></label></div>
                    <div class="ans"><label><input type='radio' name ='r5' class='r' value="2" <% if("2".equals((drvo == null ? "" : StringUtil.NVL(drvo.getDr02g())))){%>checked <%}%>><span>한달에 1번 미만</span></label></div>
                    <div class="ans"><label><input type='radio' name ='r5' class='r' value="3" <% if("3".equals((drvo == null ? "" : StringUtil.NVL(drvo.getDr02g())))){%>checked <%}%>><span>한달에 1번 정도</span></label></div>
                    <div class="ans"><label><input type='radio' name ='r5' class='r' value="4" <% if("4".equals((drvo == null ? "" : StringUtil.NVL(drvo.getDr02g())))){%>checked <%}%>><span>일주일에 1번 정도</span></label></div>
                    <div class="ans"><label><input type='radio' name ='r5' class='r' value="5" <% if("5".equals((drvo == null ? "" : StringUtil.NVL(drvo.getDr02g())))){%>checked <%}%>><span>거의 매일</span></label></div>
                </td>
            </tr>
            <tr>
                <th class="border_dash">2-5.</th>
                <td class="ta_l border_dash">
                    <p>1회 음주량(SU) × 일주일에 술을 마시는 횟수</p>
                    <div class="ans">
                        <input type='text'  id="num" name='t3sum1'class='t' minlength="1" maxlength="2" value='<%=(drvo == null ? "" : StringUtil.NVL(drvo.getDr02h()))%>'> SU × <input type='text'  id="num" name='t3sum2' class='t' minlength="1" maxlength="2" value='<%=(drvo == null ? "" : StringUtil.NVL(drvo.getDr02i()))%>'> 회 = <input type='text'  id="num" name='t3sum3' class='t' minlength="1" maxlength="2" value='<%=(drvo == null ? "" : StringUtil.NVL(drvo.getDr02j()))%>'>
                    </div>
                </td>
            </tr>
        </tbody>
    </table>
	<!-- // 음주 설문지 HTML -->

</div>


<!-- 상단 버튼을 하단에 중복배치 -->
<div class="btn_wrap btm">
	<button name="저장" id="insert" class ="csbr1010btn" style="">저장</button>
</div>



</body>
<script>
var sdateVal = $('#sdate').val();


console.log("sdateVal::"+sdateVal);

	


</script>
</html>
