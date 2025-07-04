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
//	List<MbC2VO> c2_list = (List<MbC2VO>) request.getAttribute("c2_list");
	List<MbC3VO> c3_list = (List<MbC3VO>) request.getAttribute("c3_list");
//	MbC2VO c2vo = null;
	MbC3VO c3vo = null;

//	if( c2_list != null && c2_list.size() > 0 ){
//		c2vo = c2_list.get(0);
//	}
	if( c3_list != null && c3_list.size() > 0 ){
		c3vo = c3_list.get(0);
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
	$('input:text[class="t"]').attr("disabled", false);
//	$('input:text[name="ta3"]').attr("disabled", false);	
   	 $(this).prop('checked', false);

		// $('input:radio[name="r2"]').attr("disabled", true); //비활성화

		
   	 	var selnew = $("#edit").val(); 
   	 	console.log(selnew+"::selenew")
		checking = $('#chk').val(selnew);
	});
	
	
	$('input:radio[name="r1"]').click(function() {
		
		var r1val = $(this).val();
		//	 $('input:radio[class="r"]:not(:checked)').attr("disabled", true); //비활성화
			 $('input:radio[name="tbc3"]').attr("disabled", true);
			 $('input:radio[name="tcc3"]').attr("disabled", true);
			 $('input:radio[name="r1"]:not(:checked)').attr("disabled", false);	
		
			
				setTimeout(function() {
				 $('input:text[name="tac3"]').attr("disabled",false);
				 $('input:text[name="tac3"]').focus();	
					},0);
			
		});		
	
	$('input:radio[name="r1"]').change(function() {
		var r1val = $(this).val();
		 $('input:radio[name="r1"]:not(:checked)').attr("disabled", false);	
		$('input:text[name="tac3"]').val('');

	});
	
	
	
	$('input:radio[name="r2"]').click(function() {
		
		var r1val = $(this).val();
		//	 $('input:radio[class="r"]:not(:checked)').attr("disabled", true); //비활성화
			 $('input:radio[name="tac3"]').attr("disabled", true);
			 $('input:radio[name="tcc3"]').attr("disabled", true);
			 $('input:radio[name="r2"]:not(:checked)').attr("disabled", false);	
		
			if(r1val =='4'){	
				setTimeout(function() {
				 $('input:text[name="tbc3"]').attr("disabled",false);
				 $('input:text[name="tbc3"]').focus();	
					},0);
			}else{
				$('input:text[name="tbc3"]').val('');
				 $('input:text[name="tbc3"]').attr("disabled",true);
			}

		});	
	
	$('input:radio[name="r3"]').click(function() {
		
		var r1val = $(this).val();
		//	 $('input:radio[class="r"]:not(:checked)').attr("disabled", true); //비활성화
			 $('input:radio[name="tac3"]').attr("disabled", true);
			 $('input:radio[name="tbc3"]').attr("disabled", true);
			 $('input:radio[name="r3"]:not(:checked)').attr("disabled", false);	
		
			if(r1val =='4'){	
				setTimeout(function() {
				 $('input:text[name="tcc3"]').attr("disabled",false);
				 $('input:text[name="tcc3"]').focus();	
					},0);
			}else{
				$('input:text[name="tcc3"]').val('');
				 $('input:text[name="tcc3"]').attr("disabled",true);
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
			if(Fval < 3){
				alert("체크되지 않는 입력값이 있습니다!")
				return false;
			}else{
				 radioArr.push(value);		
			}
			
		
        });

		$('input:text[class="t"]').each(function(){	
			value1 = $(this).val();
		var Selval1 =  $('input:radio[name="r1"]:checked').val();
		var Selval2 =  $('input:radio[name="r2"]:checked').val();
		var Selval3 =  $('input:radio[name="r3"]:checked').val();
			
		//	alert(Selval1)
		//	alert(Selval3)
			var r1text = $('input:text[name="tac3"]').val();
			var r2text = $('input:text[name="tbc3"]').val();
			var r3text = $('input:text[name="tcc3"]').val();
			console.log(r1text+"::console.log(r1text)")
			console.log(r2text+"::console.log(r2text)")
			console.log(r3text+"::console.log(r3text)")
			if(Selval2== '4' && r2text == ''){
				alert("Ac.기타사항 입력값 누락입니다.\n 기타사항 부분을 기술해주세요!")
			 	setTimeout(function() {
					 $('input:text[name="tbc3"]').attr("disabled",false);
					 $('input:text[name="tbc3"]').focus();	
						},0);
				return false;
			}else if(Selval3== '4' && r3text == ''){
				alert("H.기타사항 입력값 누락입니다.\n 기타사항 부분을 기술해주세요!")
					setTimeout(function() {
					 $('input:text[name="tcc3"]').attr("disabled",false);
					 $('input:text[name="tcc3"]').focus();	
						},0);
				return false;
			}else if(r1text == ''){
				alert("O.문항사항 입력값 누락입니다.\n 대략의날짜 부분을 기술해주세요!")
				setTimeout(function() {
					 $('input:text[name="tac3"]').attr("disabled",false);
					 $('input:text[name="tac3"]').focus();	
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
				"radioArr" : radioArr,
				"textArr" : textArr,
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
			url         :   getContextPath()+"/include/sheet/view_saveprogression.do",
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
		+ "<input type='radio' class='r'  " + checked2 + " style='left:11px;top:26px;'>"
		+ "<input type='radio' class='r'  " + checked3 + " style='left:11px;top:48px;'>"
		+ "<input type='radio' class='r'  " + checked4 + " style='left:11px;top:70px;'>";
		return radioTag;
	}
-->
<div id="btnArea" class="btn_wrap top">
	<input type ="hidden" id="action" value="${action}" style="display:none;"/>
	<!-- <button name="저장" id="insert" class ="csbr1010btn" style="display:none;">저장</button> -->
	<button name="수정" id="edit" class ="csbr1010btn"  value="edit">수정</button>
	<button name="신규" id ="new" class ="csbr1010btn"  value="new">신규</button>

	<input type="hidden" id ="object_idx" style="display:none;" class ="object_idx" value='<%= c3vo == null ? "" :StringUtil.NVL(c3vo.getObjectIdx())%>'>
	<input type='hidden' name='selectionNum' id="selectionNum"  value='<%= c3vo == null ? "" :StringUtil.NVL(c3vo.getSelectionNum())%>'> 
	<input type='hidden' name='examIdx' id="examIdx"  value='<%= c3vo == null ? "" :StringUtil.NVL(c3vo.getIdx())%>'> 
	<input name="idx" id ="idx" class ="idx" value="${idx}" style="display:none;"/>
	<input type ="hidden" name="exid" id ="exid" class ="exid" value="${exam_id}" style="display:none;"/>  
	<input type ="hidden" name="exnum" id ="exnum" class ="exnum" value="${exam_num}" style="display:none;"/>
	<input type ="hidden" name="cdbs" id ="cdbs" class ="cdbs" value="0208" style="display:none;"/>
	<input type ="hidden" name="chk" id ="chk" class ="chk" value="" style="display:none;"/>
</div> 
<div  id="tabbox" style="height:auto;">

	<table class="table type02">
        <colgroup>
            <col width="40px" />
            <col width="*" />
        </colgroup>
        <thead>
            <tr>
                <th class="bg1 tbl_tit" colspan="2">progression</th>
            </tr>
            <tr>
                <th class="survey_date" colspan="2">
                    <div class="sv_text">설문실행일</div>
                    <div class="sv_date">
						<input type='text' class='calendar' id='sdate' name='sdate' placeholder='yyyy/mm/dd'  style='width:120px;' value ='<%= c3vo == null ? "" :StringUtil.NVL(DateUtil.getSimpleDate("yyyyMMdd",c3vo.getTestDay()))%>'>
					</div>
                </th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <th rowspan="2">O.</th>
                <td class="ta_l">
                    <p>증상이 처음으로 나타난 것은 언제인가</p>

                    <div class="ans first"><label><input type='radio' class='r' name ='r1' value='1' <% if("1".equals((c3vo == null ? "" : StringUtil.NVL(c3vo.getcO02())))){%>checked <%}%>><span>6개월이내</span></label></div>
                    <div class="ans"><label><input type='radio' class='r' name ='r1' value='2' <% if("2".equals((c3vo == null ? "" : StringUtil.NVL(c3vo.getcO02())))){%>checked <%}%>><span>6개월 내지 12개월까지</span></label></div>
                    <div class="ans"><label><input type='radio' class='r' name ='r1' value='3' <% if("3".equals((c3vo == null ? "" : StringUtil.NVL(c3vo.getcO02())))){%>checked <%}%>><span>1년 내지 2년 이내</span></label></div>
                    <div class="ans"><label><input type='radio' class='r' name ='r1' value='4' <% if("4".equals((c3vo == null ? "" : StringUtil.NVL(c3vo.getcO02())))){%>checked <%}%>><span>2년 이전</span></label></div>
                </td>
            </tr>
            <tr>
                <td class="ta_l" colspan="4"><span style="vertical-align:middle;">대략의 날짜</span> <input type='text' class='t' name='tac3' value='<%=(c3vo == null ? "" : StringUtil.NVL(c3vo.getcO01()))%>' style='width:200px;'></td>
            </tr>
            <tr>
                <th>Ac.</th>
                <td class="ta_l">
                    <p>증상이 얼마나 갑작스럽게 출현하였는가?</p>

                    <div class="ans first"><label><input type='radio' class='r' name ='r2' value='1' <% if("1".equals((c3vo == null ? "" : StringUtil.NVL(c3vo.getcAc01())))){%>checked <%}%>><span>매우 서서히</span></label></div>
                    <div class="ans"><label><input type='radio' class='r' name ='r2' value='2' <% if("2".equals((c3vo == null ? "" : StringUtil.NVL(c3vo.getcAc01())))){%>checked <%}%>><span>1-3개월에 걸쳐(아급성)</span></label></div>
                    <div class="ans"><label><input type='radio' class='r' name ='r2' value='3' <% if("3".equals((c3vo == null ? "" : StringUtil.NVL(c3vo.getcAc01())))){%>checked <%}%>><span>1개월 이내(급성)</span></label></div>
                    <div class="ans"><label><input type='radio' class='r' name ='r2' value='4' <% if("4".equals((c3vo == null ? "" : StringUtil.NVL(c3vo.getcAc01())))){%>checked <%}%>><span>기타(기술하시오)</span></label><input type='text' class='t' name="tbc3" value='<%=(c3vo == null ? "" : StringUtil.NVL(c3vo.getcAc02()))%>' style='width:380px;'></div>
                </td>
            </tr>
            <tr>
                <th>H.</th>
                <td class="ta_l">
                    <p>증상이 어떻게 진행되었는가?</p>

                    <div class="ans first"><label><input type='radio' class='r' name ='r3' value='1' <% if("1".equals((c3vo == null ? "" : StringUtil.NVL(c3vo.getcH01())))){%>checked <%}%>><span>지속적 악화</span></label></div>
                    <div class="ans"><label><input type='radio' class='r' name ='r3' value='2' <% if("2".equals((c3vo == null ? "" : StringUtil.NVL(c3vo.getcH01())))){%>checked <%}%>><span>악화와 완화를 거듭하는 경향(계단식)</span></label></div>
                    <div class="ans"><label><input type='radio' class='r' name ='r3' value='3' <% if("3".equals((c3vo == null ? "" : StringUtil.NVL(c3vo.getcH01())))){%>checked <%}%>><span>악화되었다가 그 수준에서 안정상태</span></label></div>
                    <div class="ans"><label><input type='radio' class='r' name ='r3' value='4' <% if("4".equals((c3vo == null ? "" : StringUtil.NVL(c3vo.getcH01())))){%>checked <%}%>><span>기타(기술하시오)</span></label><input type='text' class='t' name="tcc3" value='<%=(c3vo == null ? "" : StringUtil.NVL(c3vo.getcH02()))%>' style='width:380px;'></div>
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
</html>