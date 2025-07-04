<%@page import="java.sql.Date"%>
<%@page import="csbrain.common.util.StringUtil"%>
<%@page import="csbrain.common.util.DateUtil"%>
<%@page import="csbrain.common.service.MbKdsqVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/common.inc"%>
<jsp:include page='<%=incInit%>' flush="false"></jsp:include>
<%
	List<MbKdsqVO> kdsq_list = (List<MbKdsqVO>) request.getAttribute("kdsq_list");
	MbKdsqVO kdsqvo = null;
	
	if( kdsq_list != null && kdsq_list.size() > 0 ){
		kdsqvo = kdsq_list.get(0);
	
	
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
 #tabbox {

 /*	background-size: cover;
	*/
	width:608px;
	height: 400px;;
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
.table.type02 thead th.bg2 {letter-spacing:-1px;}	
	
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
	
	
	
	var sum = 0;
	function chkTts() {
		var rchk = $('input:radio[class="r"]:checked');
		
		
		var count = rchk.length; // radio 갯수	

		console.log(count+"t체크한카운트")			

	if(count == 15){
			for(var i=0; i < count; i++ ){
			       if( rchk[i].checked == true ){ // 체크 된것만
			    	  if(rchk[i].value == '3'){
			    		  sum += 1;
			    	  }else if(rchk[i].value =='4'){
							
			    		  sum += 2;
					}else if(rchk[i].value =='1' || rchk[i].value =='2'){
						sum += 0;
					}	 
			    	   
			    	   
			    	   
			          // radio value 값 합산
			       }
			   }

		}else{
			alert("체크를 다해주시길 바랍니다!")
			return false;
		}
		
			console.log(sum+"sum값")
		   $('input:text[name="tts"]').val(sum); 
	 		var tts =  $('input:text[name="tts"]').val(); 
			console.log(tts+"tts값");
	}
	
	
	$('#insert').click(function() {
		
		//배열선언
		var radioArr = [];
		var textArr = [];
		var tscore;
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
			
			
		

			console.log(value+":[KDSQ 텍스트]values1["+i+"]");
			var Fval =  $('input:radio[class="r"]:checked').length;
			console.log(Fval+"체크한카운트")
			if(Fval == 15){
				
				//radioArr.push(value);	
				  radioArr.push(value);		

			}else if(Fval < 15){
				
				
				alert("체크되지 않는 입력값이 있습니다!")
				return false;
			}
			
			
		
        });

		
		var count =  $('input:radio[class="r"]:checked').length;
		if(count == 15){
			chkTts();
			 tscore =  $('input:text[name="tts"]').val();
			 
			 console.log(tscore+"tscore값")
			
		}
		
		
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
			"tscore" : tscore
	
		};
		  setTimeout(function() {	
		//ajax호출
		$.ajax({
			url         :   getContextPath()+"/include/sheet/view_savekdsq.do",
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
            error : function(request, status, error){
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

	public String make_radio_4(String v) {
		String checked1 = "";
		String checked2 = "";
		String checked3 = "";
		String checked4 = "";

		if ( "1".equals(v) ) checked1 = "checked";
		if ( "2".equals(v) ) checked2 = "checked";
		if ( "3".equals(v) ) checked3 = "checked";
		if ( "4".equals(v) ) checked4 = "checked";

		String radioTag = "<input type='radio' class='r' " + checked1 + " style='left:25px;top:5px;'>"
		+ "<input type='radio' class='r'  " + checked2 + " style='left:87px;top:5px;'>"
		+ "<input type='radio' class='r'  " + checked2 + " style='left:148px;top:5px;'>"
		+ "<input type='radio' class='r'  " + checked2 + " style='left:208px;top:5px;'>";
		return radioTag;
	}

	public String make_radio_3(String v) {
		String checked1 = "";
		String checked2 = "";
		String checked3 = "";

		if ( "1".equals(v) ) checked1 = "checked";
		if ( "2".equals(v) ) checked2 = "checked";
		if ( "3".equals(v) ) checked3 = "checked";

		String radioTag = "<input type='radio' class='r' " + checked1 + " style='left:25px;top:5px;'>"
		+ "<input type='radio' class='r'  " + checked2 + " style='left:88px;top:5px;'>"
		+ "<input type='radio' class='r'  " + checked3 + " style='left:150px;top:5px;'>";
		return radioTag;
	}
-->

<div id="btnArea" class="btn_wrap top">
	<input type ="hidden" id="action" value="${action}" style="display:none;"/>
	<!-- <button name="저장" id="insert" class ="csbr1010btn" style="display:none;">저장</button> -->
	<button name="수정" id="edit" class ="csbr1010btn"  value="edit">수정</button>
	<button name="신규" id ="new" class ="csbr1010btn"  value="new">신규</button>

	<input type="hidden" id ="object_idx" style="display:none;" class ="object_idx" value='<%= kdsqvo == null ? "" :StringUtil.NVL(kdsqvo.getObjectIdx())%>'>
	<input type='hidden' name='selectionNum' id="selectionNum"  value='<%= kdsqvo == null ? "" :StringUtil.NVL(kdsqvo.getSelectionNum())%>'> 
	<input type='hidden' name='examIdx' id="examIdx"  value='<%= kdsqvo == null ? "" :StringUtil.NVL(kdsqvo.getIdx())%>'> 
	<input name="idx" id ="idx" class ="idx" value="${idx}" style="display:none;"/>
	<input type ="hidden" name="exid" id ="exid" class ="exid" value="${exam_id}" style="display:none;"/>                   
	<input type ="hidden" name="exnum" id ="exnum" class ="exnum" value="${exam_num}" style="display:none;"/>
	<input type ="hidden" name="chk" id ="chk" class ="chk" style="display:none;"/>
	<input type ="hidden" name="cdbs" id ="cdbs" class ="cdbs" value="0009" style="display:none;"/>
	<input type ="text" name="tts" id ="tts" class ="tts" style="display:none;"/>
</div> 

<div id="tabbox" style="height:auto;">

	<table class="table type02">
        <colgroup>
            <col width="40px" />
            <col width="*" />
            <col width="60px" />
            <col width="60px" />
            <col width="60px" />
            <col width="60px" />
        </colgroup>
        <thead>
            <tr>
                <th class="bg1 tbl_tit" colspan="6">KDSQ</th>
            </tr>
            <tr>
                <th class="survey_date" colspan="6">
                    <div class="sv_text">설문실행일</div>
                    <div class="sv_date">
						<input type='text' class='calendar' id='sdate' name='sdate' placeholder='yyyy/mm/dd'  style='width:120px;' value ='<%= kdsqvo == null ? "" :StringUtil.NVL(DateUtil.getSimpleDate("yyyyMMdd",kdsqvo.getTestDay()))%>'>	
					</div>
                </th>
            </tr>
            <tr>
                <th colspan="2"></th>
                <th class="bg2">해당사항<br />없음</th>
                <th class="bg2">아니다</th>
                <th class="bg2">가끔(조금)<br />그렇다</th>
                <th class="bg2">자주(많이)<br />그렇다</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <th>1</th>
                <td class="ta_l">오늘이 몇 월이고, 무슨 요일인지를 잘 모른다.</td>
                <td><label><input type='radio' name ='r1' class='r' value="1" <% if("1".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq01())))){%>checked <%}%>><span><em class="blind">해당사항 없음</em></span></label></td>
                <td><label><input type='radio' name ='r1' class='r' value="2" <% if("2".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq01())))){%>checked <%}%>><span><em class="blind">아니다</em></span></label></td>
                <td><label><input type='radio' name ='r1' class='r' value="3" <% if("3".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq01())))){%>checked <%}%>><span><em class="blind">가끔(조금) 그렇다</em></span></label></td>
                <td><label><input type='radio' name ='r1' class='r' value="4" <% if("4".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq01())))){%>checked <%}%>><span><em class="blind">자주(많이) 그렇다</em></span></label></td>
            </tr>
            <tr>
                <th>2</th>
                <td class="ta_l">자기가 놔둔 물건을 찾지 못한다.</td>
                <td><label><input type='radio' name ='r2' class='r' value="1" <% if("1".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq02())))){%>checked <%}%>><span><em class="blind">해당사항 없음</em></span></label></td>
                <td><label><input type='radio' name ='r2' class='r' value="2" <% if("2".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq02())))){%>checked <%}%>><span><em class="blind">아니다</em></span></label></td>
                <td><label><input type='radio' name ='r2' class='r' value="3" <% if("3".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq02())))){%>checked <%}%>><span><em class="blind">가끔(조금) 그렇다</em></span></label></td>
                <td><label><input type='radio' name ='r2' class='r' value="4" <% if("4".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq02())))){%>checked <%}%>><span><em class="blind">자주(많이) 그렇다</em></span></label></td>
            </tr>
            <tr>
                <th>3</th>
                <td class="ta_l">같은 질문을 반복해서 한다.</td>
                <td><label><input type='radio' name ='r3' class='r' value="1" <% if("1".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq03())))){%>checked <%}%>><span><em class="blind">해당사항 없음</em></span></label></td>
                <td><label><input type='radio' name ='r3' class='r' value="2" <% if("2".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq03())))){%>checked <%}%>><span><em class="blind">아니다</em></span></label></td>
                <td><label><input type='radio' name ='r3' class='r' value="3" <% if("3".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq03())))){%>checked <%}%>><span><em class="blind">가끔(조금) 그렇다</em></span></label></td>
                <td><label><input type='radio' name ='r3' class='r' value="4" <% if("4".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq03())))){%>checked <%}%>><span><em class="blind">자주(많이) 그렇다</em></span></label></td>
            </tr>
            <tr>
                <th>4</th>
                <td class="ta_l">약속을 하고서 잊어버린다.</td>
                <td><label><input type='radio' name ='r4' class='r' value="1" <% if("1".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq04())))){%>checked <%}%>><span><em class="blind">해당사항 없음</em></span></label></td>
                <td><label><input type='radio' name ='r4' class='r' value="2" <% if("2".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq04())))){%>checked <%}%>><span><em class="blind">아니다</em></span></label></td>
                <td><label><input type='radio' name ='r4' class='r' value="3" <% if("3".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq04())))){%>checked <%}%>><span><em class="blind">가끔(조금) 그렇다</em></span></label></td>
                <td><label><input type='radio' name ='r4' class='r' value="4" <% if("4".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq04())))){%>checked <%}%>><span><em class="blind">자주(많이) 그렇다</em></span></label></td>
            </tr>
            <tr>
                <th>5</th>
                <td class="ta_l">물건을 가지러 갔다가 잊어버리고 그냥 온다.</td>
                <td><label><input type='radio' name ='r5' class='r' value="1" <% if("1".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq05())))){%>checked <%}%>><span><em class="blind">해당사항 없음</em></span></label></td>
                <td><label><input type='radio' name ='r5' class='r' value="2" <% if("2".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq05())))){%>checked <%}%>><span><em class="blind">아니다</em></span></label></td>
                <td><label><input type='radio' name ='r5' class='r' value="3" <% if("3".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq05())))){%>checked <%}%>><span><em class="blind">가끔(조금) 그렇다</em></span></label></td>
                <td><label><input type='radio' name ='r5' class='r' value="4" <% if("4".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq05())))){%>checked <%}%>><span><em class="blind">자주(많이) 그렇다</em></span></label></td>
            </tr>
            <tr>
                <th>6</th>
                <td class="ta_l">물건이나, 사람의 이름을 대기가 힘들어 머뭇거린다.</td>
                <td><label><input type='radio' name ='r6' class='r' value="1" <% if("1".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq06())))){%>checked <%}%>><span><em class="blind">해당사항 없음</em></span></label></td>
                <td><label><input type='radio' name ='r6' class='r' value="2" <% if("2".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq06())))){%>checked <%}%>><span><em class="blind">아니다</em></span></label></td>
                <td><label><input type='radio' name ='r6' class='r' value="3" <% if("3".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq06())))){%>checked <%}%>><span><em class="blind">가끔(조금) 그렇다</em></span></label></td>
                <td><label><input type='radio' name ='r6' class='r' value="4" <% if("4".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq06())))){%>checked <%}%>><span><em class="blind">자주(많이) 그렇다</em></span></label></td>
            </tr>
            <tr>
                <th>7</th>
                <td class="ta_l">대화 중 내용이 이해되지 않아 반복해서 물어본다.</td>
                <td><label><input type='radio' name ='r7' class='r' value="1" <% if("1".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq07())))){%>checked <%}%>><span><em class="blind">해당사항 없음</em></span></label></td>
                <td><label><input type='radio' name ='r7' class='r' value="2" <% if("2".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq07())))){%>checked <%}%>><span><em class="blind">아니다</em></span></label></td>
                <td><label><input type='radio' name ='r7' class='r' value="3" <% if("3".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq07())))){%>checked <%}%>><span><em class="blind">가끔(조금) 그렇다</em></span></label></td>
                <td><label><input type='radio' name ='r7' class='r' value="4" <% if("4".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq07())))){%>checked <%}%>><span><em class="blind">자주(많이) 그렇다</em></span></label></td>
            </tr>
            <tr>
                <th>8</th>
                <td class="ta_l">길을 잃거나 헤맨 적이 있다.</td>
                <td><label><input type='radio' name ='r8' class='r' value="1" <% if("1".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq08())))){%>checked <%}%>><span><em class="blind">해당사항 없음</em></span></label></td>
                <td><label><input type='radio' name ='r8' class='r' value="2" <% if("2".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq08())))){%>checked <%}%>><span><em class="blind">아니다</em></span></label></td>
                <td><label><input type='radio' name ='r8' class='r' value="3" <% if("3".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq08())))){%>checked <%}%>><span><em class="blind">가끔(조금) 그렇다</em></span></label></td>
                <td><label><input type='radio' name ='r8' class='r' value="4" <% if("4".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq08())))){%>checked <%}%>><span><em class="blind">자주(많이) 그렇다</em></span></label></td>
            </tr>
            <tr>
                <th>9</th>
                <td class="ta_l">예전에 비해서 계산능력이 떨어졌다.<br />(예: 물건 값이나 거스름돈 계산을 못한다.)</td>
                <td><label><input type='radio' name ='r9' class='r' value="1" <% if("1".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq09())))){%>checked <%}%>><span><em class="blind">해당사항 없음</em></span></label></td>
                <td><label><input type='radio' name ='r9' class='r' value="2" <% if("2".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq09())))){%>checked <%}%>><span><em class="blind">아니다</em></span></label></td>
                <td><label><input type='radio' name ='r9' class='r' value="3" <% if("3".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq09())))){%>checked <%}%>><span><em class="blind">가끔(조금) 그렇다</em></span></label></td>
                <td><label><input type='radio' name ='r9' class='r' value="4" <% if("4".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq09())))){%>checked <%}%>><span><em class="blind">자주(많이) 그렇다</em></span></label></td>
            </tr>
            <tr>
                <th>10</th>
                <td class="ta_l">예전에 비해 성격이 변했다.</td>
                <td><label><input type='radio' name ='r10' class='r' value="1" <% if("1".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq10())))){%>checked <%}%>><span><em class="blind">해당사항 없음</em></span></label></td>
                <td><label><input type='radio' name ='r10' class='r' value="2" <% if("2".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq10())))){%>checked <%}%>><span><em class="blind">아니다</em></span></label></td>
                <td><label><input type='radio' name ='r10' class='r' value="3" <% if("3".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq10())))){%>checked <%}%>><span><em class="blind">가끔(조금) 그렇다</em></span></label></td>
                <td><label><input type='radio' name ='r10' class='r' value="4" <% if("4".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq10())))){%>checked <%}%>><span><em class="blind">자주(많이) 그렇다</em></span></label></td>
            </tr>
            <tr>
                <th>11</th>
                <td class="ta_l">이전에 잘 다루던 기구의 사용이 서툴러졌다.<br />(세탁기, 전기 밥솥, 경운기 등)</td>
                <td><label><input type='radio' name ='r11' class='r' value="1" <% if("1".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq11())))){%>checked <%}%>><span><em class="blind">해당사항 없음</em></span></label></td>
                <td><label><input type='radio' name ='r11' class='r' value="2" <% if("2".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq11())))){%>checked <%}%>><span><em class="blind">아니다</em></span></label></td>
                <td><label><input type='radio' name ='r11' class='r' value="3" <% if("3".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq11())))){%>checked <%}%>><span><em class="blind">가끔(조금) 그렇다</em></span></label></td>
                <td><label><input type='radio' name ='r11' class='r' value="4" <% if("4".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq11())))){%>checked <%}%>><span><em class="blind">자주(많이) 그렇다</em></span></label></td>
            </tr>
            <tr>
                <th>12</th>
                <td class="ta_l">예전에 비해 방이나 집안의 정리 정돈을 하지 못한다.</td>
                <td><label><input type='radio' name ='r12' class='r' value="1" <% if("1".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq12())))){%>checked <%}%>><span><em class="blind">해당사항 없음</em></span></label></td>
                <td><label><input type='radio' name ='r12' class='r' value="2" <% if("2".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq12())))){%>checked <%}%>><span><em class="blind">아니다</em></span></label></td>
                <td><label><input type='radio' name ='r12' class='r' value="3" <% if("3".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq12())))){%>checked <%}%>><span><em class="blind">가끔(조금) 그렇다</em></span></label></td>
                <td><label><input type='radio' name ='r12' class='r' value="4" <% if("4".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq12())))){%>checked <%}%>><span><em class="blind">자주(많이) 그렇다</em></span></label></td>
            </tr>
            <tr>
                <th>13</th>
                <td class="ta_l">상황에 맞게 스스로 옷을 선택하여 입지 못한다.</td>
                <td><label><input type='radio' name ='r13' class='r' value="1" <% if("1".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq13())))){%>checked <%}%>><span><em class="blind">해당사항 없음</em></span></label></td>
                <td><label><input type='radio' name ='r13' class='r' value="2" <% if("2".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq13())))){%>checked <%}%>><span><em class="blind">아니다</em></span></label></td>
                <td><label><input type='radio' name ='r13' class='r' value="3" <% if("3".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq13())))){%>checked <%}%>><span><em class="blind">가끔(조금) 그렇다</em></span></label></td>
                <td><label><input type='radio' name ='r13' class='r' value="4" <% if("4".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq13())))){%>checked <%}%>><span><em class="blind">자주(많이) 그렇다</em></span></label></td>
            </tr>
            <tr>
                <th>14</th>
                <td class="ta_l">혼자 대중교통 수단을 이용하여 목적지에 가기 힘들다.<br />(신체적인 문제(관절염)로 인한 것은 제외됨)</td>
                <td><label><input type='radio' name ='r14' class='r' value="1" <% if("1".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq14())))){%>checked <%}%>><span><em class="blind">해당사항 없음</em></span></label></td>
                <td><label><input type='radio' name ='r14' class='r' value="2" <% if("2".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq14())))){%>checked <%}%>><span><em class="blind">아니다</em></span></label></td>
                <td><label><input type='radio' name ='r14' class='r' value="3" <% if("3".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq14())))){%>checked <%}%>><span><em class="blind">가끔(조금) 그렇다</em></span></label></td>
                <td><label><input type='radio' name ='r14' class='r' value="4" <% if("4".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq14())))){%>checked <%}%>><span><em class="blind">자주(많이) 그렇다</em></span></label></td>
            </tr>
            <tr>
                <th>15</th>
                <td class="ta_l">내복이나 옷이 더러워져도 갈아입지 않으려고 한다.</td>
                <td><label><input type='radio' name ='r15' class='r' value="1" <% if("1".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq15())))){%>checked <%}%>><span><em class="blind">해당사항 없음</em></span></label></td>
                <td><label><input type='radio' name ='r15' class='r' value="2" <% if("2".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq15())))){%>checked <%}%>><span><em class="blind">아니다</em></span></label></td>
                <td><label><input type='radio' name ='r15' class='r' value="3" <% if("3".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq15())))){%>checked <%}%>><span><em class="blind">가끔(조금) 그렇다</em></span></label></td>
                <td><label><input type='radio' name ='r15' class='r' value="4" <% if("4".equals((kdsqvo == null ? "" : StringUtil.NVL(kdsqvo.getKdsq15())))){%>checked <%}%>><span><em class="blind">자주(많이) 그렇다</em></span></label></td>
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
