<%@page import="java.sql.Date"%>
<%@page import="csbrain.common.util.StringUtil"%>
<%@page import="csbrain.common.util.DateUtil"%>
<%@page import="csbrain.common.service.MbDgVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/common.inc"%>
<jsp:include page='<%=incInit%>' flush="false"></jsp:include>
<%
	List<MbDgVO> dg_list = (List<MbDgVO>) request.getAttribute("dg_list");

	MbDgVO dgvo = null;

	if( dg_list != null && dg_list.size() > 0 ){
		dgvo = dg_list.get(0);
		
		String d = dgvo.getTestDay();
		d = DateUtil.getSimpleDate("yyyyMMdd",d);	
		System.out.print(d);
		
		
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
	height: 900px;;
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
.radio_col_5 {list-style:none;margin:5px 0 6px;}
.radio_col_5 > li {display:table;width:100%;text-align:left;}
.radio_col_5 > li + li {margin-top:5px;}
.radio_col_5 > li > label {display:table-cell;width:19%;vertical-align:middle;box-sizing:border-box;}
.radio_col_5 > li > label:first-child {width:21%;}
.radio_col_5 > li > label:last-child {width:22%;}	
	
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
	
	
	
	
	
	
	
	$('#insert').click(function() {
		
		//배열선언
		var colNmArr = [];
		var radioArr = [];
		var textArr = [];
		var sdates = $("#sdate").val();
		var exam_num= $("#selectionNum").val();
		var exam_id = $("#exid").val(); 
		var exam_idx= $("#examIdx").val(); 
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
		var getNm;
		$('input:radio[class="r"]:checked').each(function(i){//체크된 리스트 저장
			
			value = $(this).val();
			console.log(value+":[Drug 텍스트]values1["+i+"]");
			
			getNm = $(this).attr('name');
			console.log(getNm+":::::::::::getNm")
			
			
			var Fval =  $('input:radio[class="r"]:checked').length;
			console.log(Fval+"체크한카운트")
			if(Fval < 2){
				alert("체크되지 않는 입력값이 있습니다!")
				return false;
			}else{
				 radioArr.push(value);	
				 colNmArr.push(getNm);
			}
			
			
		
        });
		var value1;
		$('input:text[class="t"]').each(function(i) {
			
			value1 =$(this).val();
			
			console.log(value1+":[Drug 텍스트]values1["+i+"]");
			textArr.push(value1);
		});
		

		
		
		var num;
		var num1;
		 num = $('input:radio[class="r"]:checked').length;
 		console.log(num+"저장된체크넘버");
 		num1 = $('input:radio[class="r"]:not(:checked)').length;
				
		var objParams = {
			"colNmArr" : colNmArr,	
			"radioArr" : radioArr,
			"textArr" : textArr,
			"sdate" : sdates,  
			"idx" : idx,
			"whichChk" : whichChk,
			"exam_num" : exam_num,
			"exam_idx" : exam_idx,
			"cdbs" : cdbs,
			"exam_id" : exam_id
	
		};
		
		//ajax호출
		$.ajax({
			url         :   getContextPath()+"/include/sheet/view_savedrug.do",
            dataType    :   "json",
            contentType :   "application/x-www-form-urlencoded; charset=UTF-8",
            type        :   "post",
            data        :   objParams,
            success     :   function(json){
       			if(json.model.code =="OK"){
       				var obj = json.model.objIdx;
       				var examN = json.model.exam_num;
					var flag = false; 
//        				alert(json.model.message);
//        				return;
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
            	console.log(request);
            	console.log(status);
            	console.log(error);
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
 -->
<div id="btnArea" class="btn_wrap top">
	<input type ="hidden" id="action" value="${action}" style="display:none;"/>
	<!-- <button name="저장" id="insert" class ="csbr1010btn" style="display:none;">저장</button> -->
	<button name="수정" id="edit" class ="csbr1010btn"  value="edit">수정</button>
	<button name="신규" id ="new" class ="csbr1010btn"  value="new">신규</button>

	<input type="hidden" id ="object_idx" style="display:none;" class ="object_idx" value='<%= dgvo == null ? "" :StringUtil.NVL(dgvo.getObjectIdx())%>'>
	<input type='hidden' name='selectionNum' id="selectionNum"  value='<%= dgvo == null ? "" :StringUtil.NVL(dgvo.getSelectionNum())%>'> 
	<input type='hidden' name='examIdx' id="examIdx"  value='<%= dgvo == null ? "" :StringUtil.NVL(dgvo.getIdx())%>'> 
	<input name="idx" id ="idx" class ="idx" value="${idx}" style="display:none;"/>
	<input type ="hidden" name="exnum" id ="exnum" class ="exnum" value="${exam_num}" style="display:none;"/>
	<input type ="hidden" name="exid" id ="exid" class ="exid" value="${exam_id}" style="display:none;"/>  
	<input type ="hidden" name="cdbs" id ="cdbs" class ="cdbs" value="0006" style="display:none;"/>     
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
                <th class="bg1 tbl_tit" colspan="5">Drug</th>
            </tr>
            <tr>
                <th class="survey_date" colspan="5">
                    <div class="sv_text">설문실행일</div>
                    <div class="sv_date">
						<input type='text' class='calendar' id='sdate' name='sdate' placeholder='yyyy/mm/dd'  style='width:120px;' value ='<%= dgvo == null ? "" :StringUtil.NVL(DateUtil.getSimpleDate("yyyyMMdd",dgvo.getTestDay()))%>'>	
					</div>
                </th>
            </tr>
            <tr>
                <th colspan="2"></th>
                <th class="bg2">아니오</th>
                <th class="bg2">예</th>
                <th class="bg2">불확실</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <th>1</th>
                <td class="ta_l">현재 처방을 받아 투약 중인 약물이 있습니까?</td>
                <td><label><input type='radio' name ='dg01' class='r' value="1" <% if("1".equals((dgvo == null ? "" : StringUtil.NVL(dgvo.getDg01())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' name ='dg01' class='r' value="2" <% if("2".equals((dgvo == null ? "" : StringUtil.NVL(dgvo.getDg01())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' name ='dg01' class='r' value="3" <% if("3".equals((dgvo == null ? "" : StringUtil.NVL(dgvo.getDg01())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th>2</th>
                <td class="ta_l">현재 처방을 받지 않고 복용 중인 건강관련 약이나 식품이 있습니까?</td>
                <td><label><input type='radio' name ='dg02' class='r' value="1" <% if("1".equals((dgvo == null ? "" : StringUtil.NVL(dgvo.getDg02())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' name ='dg02' class='r' value="2" <% if("2".equals((dgvo == null ? "" : StringUtil.NVL(dgvo.getDg02())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' name ='dg02' class='r' value="3" <% if("3".equals((dgvo == null ? "" : StringUtil.NVL(dgvo.getDg02())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th rowspan="2">3</th>
                <td class="ta_l" colspan="4"></td>
            </tr>
            <tr>
                <td colspan="4" style="padding:0;">
                    <ul class="radio_col_5">
                        <li>
                            <label><input type='radio' name="dg031" class='r' value="1" <% if("1".equals((dgvo == null ? "" : StringUtil.NVL(dgvo.getDg031())))){%>checked <%}%>><span>고혈압 약</span></label>
                            <label><input type='radio' name="dg032" class='r' value="1" <% if("1".equals((dgvo == null ? "" : StringUtil.NVL(dgvo.getDg032())))){%>checked <%}%>><span>당뇨 약</span></label>
                            <label><input type='radio' name="dg033" class='r' value="1" <% if("1".equals((dgvo == null ? "" : StringUtil.NVL(dgvo.getDg033())))){%>checked <%}%>><span>소화제</span></label>
                            <label><input type='radio' name="dg034" class='r' value="1" <% if("1".equals((dgvo == null ? "" : StringUtil.NVL(dgvo.getDg034())))){%>checked <%}%>><span>고지혈증 약</span></label>
                            <label><input type='radio' name="dg035" class='r' value="1" <% if("1".equals((dgvo == null ? "" : StringUtil.NVL(dgvo.getDg035())))){%>checked <%}%>><span>관절염 약</span></label>
                        </li>
                        <li>
                            <label><input type='radio' name="dg036" class='r'  value= "1" <% if("1".equals((dgvo == null ? "" : StringUtil.NVL(dgvo.getDg036())))){%>check2ed <%}%>><span>골다공증 약</span></label>
                            <label><input type='radio' name="dg037" class='r'  value="1" <% if("1".equals((dgvo == null ? "" : StringUtil.NVL(dgvo.getDg037())))){%>checked <%}%>><span>호르몬제</span></label>
                            <label><input type='radio' name="dg038" class='r' value="1" <% if("1".equals((dgvo == null ? "" : StringUtil.NVL(dgvo.getDg038())))){%>checked <%}%>><span>심장약</span></label>
                            <label><input type='radio' name="dg039" class='r' value="1" <% if("1".equals((dgvo == null ? "" : StringUtil.NVL(dgvo.getDg039())))){%>checked <%}%>><span>뇌졸중 약</span></label>
                            <label><input type='radio' name="dg0310" class='r' value="1" <% if("1".equals((dgvo == null ? "" : StringUtil.NVL(dgvo.getDg0310())))){%>checked <%}%>><span>수면제 포함 정신건강관련 약</span></label>
                        </li>
                        <li>
                            <label><input type='radio' name="dg0311" class='r' value="1" <% if("1".equals((dgvo == null ? "" : StringUtil.NVL(dgvo.getDg0311())))){%>checked <%}%>><span>호흡기 관련 약</span></label>
                            <label><input type='radio' name="dg0312" class='r' value="1" <% if("1".equals((dgvo == null ? "" : StringUtil.NVL(dgvo.getDg0312())))){%>checked <%}%>><span>간 관련 약</span></label>
                            <label><input type='radio' name="dg0313" class='r' value="1" <% if("1".equals((dgvo == null ? "" : StringUtil.NVL(dgvo.getDg0313())))){%>checked <%}%>><span>신장 관련 약</span></label>
                            <label><input type='radio' name="dg0314" class='r' value="1" <% if("1".equals((dgvo == null ? "" : StringUtil.NVL(dgvo.getDg0314())))){%>checked <%}%>><span>한약</span></label>
                            <label><input type='radio' name="dg0315" class='r' value="1" <% if("1".equals((dgvo == null ? "" : StringUtil.NVL(dgvo.getDg0315())))){%>checked <%}%>><span>기타</span></label>
                        </li>
                    </ul>
                </td>
            </tr>
            <tr>
                <th>4</th>
                <td class="ta_l" colspan="4">기타 입력 사항 <input type='text' class='t' name="t3a" value='<%=(dgvo == null ? "" : StringUtil.NVL(dgvo.getDg04()))%>' style='width:380px;' ></td>
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