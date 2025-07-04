<%@page import="java.sql.Date"%>
<%@page import="csbrain.common.service.MbFmhVO"%>
<%@page import="csbrain.common.util.DateUtil"%>
<%@page import="csbrain.common.util.StringUtil"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/common.inc"%>
<jsp:include page='<%=incInit%>' flush="false"></jsp:include>
<%
	String sdate = request.getParameter("sdate");
	//if ( StringUtil.isEmptyString(edate) ) edate = DateUtil.getTodayByFormat("yyyy-MM-dd");


	List<MbFmhVO> fmh_list = (List<MbFmhVO>) request.getAttribute("fmh_list");
	MbFmhVO fmbvo = null;
//	MbC3VO c3vo = null;

	if(fmh_list != null && fmh_list.size() > 0 ){
		fmbvo =fmh_list.get(0);
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
		width:125px;
		height:21px;
	}
	/* .r {
		position:absolute;
	} */
	.t {
		/* position:absolute !important;
		margin:0px !important;
		border:0px solid red !important;
		border-radius: 0px !important;
		text-align:left;
		font-weight:normal;
		height:23px;
		overflow:hidden;
		background-color:transparent  !important;
		color:blue !important;
		box-sizing: border-box;
		padding-left:1px; */
	}
		#tabbox {

 /*	background-size: cover;
	*/
	width:608px;
	height:781px;;
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
.ans label {display:inline-block;width:75px;vertical-align:top;}
.ans label:last-child {width:auto;}
	
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

	//$('input:text[name="ddate"]').attr("disabled", false);
	 $('input[class="r"]').removeAttr('checked');
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
		var selection_num = $("#selectionNum").val();
		var exam_num= $("#selectionNum").val();
		var exam_idx= $("#examIdx").val(); 
		var exam_id = $("#exid").val();
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
			if(Fval < 6){
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
			"exam_id" : exam_id,
			"exam_num" : exam_num,
			"exam_idx" : exam_idx,
			"selectionNum" : selection_num,
			"cdbs" : cdbs
	
		};
		
		//ajax호출
		$.ajax({
			url         :   getContextPath()+"/include/sheet/view_savefamily.do",
            dataType    :   "json",
            contentType :   "application/x-www-form-urlencoded; charset=UTF-8",
            type        :   "post",
            data        :   objParams,
            success     :   function(json){
       			if(json.model.code =="OK"){
       				var obj = json.model.objIdx;
       				var examN = json.model.exam_num;
//        				var obj = json.model.objIdx;
//        				var examN = json.model.exam_num;
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
	<input type="hidden" id ="object_idx" style="display:none;" class ="object_idx" value='<%= fmbvo == null ? "" :StringUtil.NVL(fmbvo.getObjectIdx())%>'>
	<input type='hidden' name='selectionNum' id="selectionNum"  value='<%= fmbvo == null ? "" :StringUtil.NVL(fmbvo.getSelectionNum())%>'> 
	<input type='hidden' name='examIdx' id="examIdx"  value='<%= fmbvo == null ? "" :StringUtil.NVL(fmbvo.getIdx())%>'> 
	<input name="idx" id ="idx" class ="idx" value="${idx}" style="display:none;"/>
	<input type ="hidden" name="exid" id ="exid" class ="exid" value="${exam_id}" style="display:none;"/>
	<input type ="hidden" name="exnum" id ="exnum" class ="exnum" value="${exam_num}" style="display:none;"/>
	<input type ="hidden" name="cdbs" id ="cdbs" class ="cdbs" value="0003" style="display:none;"/>
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
                <th class="bg1 tbl_tit" colspan="2">가족력</th>
            </tr>
            <tr>
                <th class="survey_date" colspan="2">
                    <div class="sv_text">설문실행일</div>
                    <div class="sv_date">
						<input type='text' class='calendar' id='sdate' name='sdate' placeholder='yyyy/mm/dd' value='${testDay}' style='width:120px;'>
					</div>
                </th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <th>1-1</th>
                <td class="ta_l">
                    <p>가족(아버지, 어머나, 형제, 자매, 아들, 딸) 중 치매를 진단받은 사람이 있습니까?</p>

                    <div class="ans first">
                        <label><input type='radio' name ='r1' class='r' value="1" <% if("1".equals((fmbvo == null ? "" : StringUtil.NVL(fmbvo.getF01A())))){%>checked <%}%>><span>예</span></label>
                        <label style="width:auto;"><input type='radio' name ='r1' class='r' value="2" <% if("2".equals((fmbvo == null ? "" : StringUtil.NVL(fmbvo.getF01A())))){%>checked <%}%>><span>아니오</span></label> <span>( → 2-1 항목으로 이동)</span>
                    </div>
                </td>
            </tr>
            <tr>
                <th>1-2</th>
                <td class="ta_l">
                    <p>누가 진단 받았습니까? 진단받은 가족 모두 표시해 주세요.</p>

                    <div class="ans first">
                        <label><input type='radio' name ='r2' class='r' value="1" <% if("1".equals((fmbvo == null ? "" : StringUtil.NVL(fmbvo.getF01B())))){%>checked <%}%>><span>아버지</span></label>
                        <label><input type='radio' name ='r2' class='r' value="2" <% if("2".equals((fmbvo == null ? "" : StringUtil.NVL(fmbvo.getF01B())))){%>checked <%}%>><span>어머니</span></label>
                        <label><input type='radio' name ='r2' class='r' value="3" <% if("3".equals((fmbvo == null ? "" : StringUtil.NVL(fmbvo.getF01B())))){%>checked <%}%>><span>형제</span></label>
                        <label><input type='radio' name ='r2' class='r' value="4" <% if("4".equals((fmbvo == null ? "" : StringUtil.NVL(fmbvo.getF01B())))){%>checked <%}%>><span>자매</span></label>
                        <label><input type='radio' name ='r2' class='r' value="5" <% if("5".equals((fmbvo == null ? "" : StringUtil.NVL(fmbvo.getF01B())))){%>checked <%}%>><span>아들</span></label>
                        <label><input type='radio' name ='r2' class='r' value="6" <% if("6".equals((fmbvo == null ? "" : StringUtil.NVL(fmbvo.getF01B())))){%>checked <%}%>><span>딸</span></label>
                    </div>
                </td>
            </tr>
            <tr>
                <th>2-1</th>
                <td class="ta_l">
                    <p>가족(아버지, 어머나, 형제, 자매, 아들, 딸) 중 파킨슨병을 진단받은 사람이 있습니까?</p>

                    <div class="ans first">
                        <label><input type='radio' name ='r3' class='r' value="1" <% if("1".equals((fmbvo == null ? "" : StringUtil.NVL(fmbvo.getF02A())))){%>checked <%}%>><span>예</span></label>
                        <label style="width:auto;"><input type='radio' name ='r3' class='r' value="2" <% if("2".equals((fmbvo == null ? "" : StringUtil.NVL(fmbvo.getF02A())))){%>checked <%}%>><span>아니오</span></label> <span>( → 3-1 항목으로 이동)</span>
                    </div>
                </td>
            </tr>
            <tr>
                <th>2-2</th>
                <td class="ta_l">
                    <p>누가 진단 받았습니까? 진단받은 가족 모두 표시해 주세요.</p>

                    <div class="ans first">
                        <label><input type='radio' name ='r4' class='r' value="1" <% if("1".equals((fmbvo == null ? "" : StringUtil.NVL(fmbvo.getF02B())))){%>checked <%}%>><span>아버지</span></label>
                        <label><input type='radio' name ='r4' class='r' value="2" <% if("2".equals((fmbvo == null ? "" : StringUtil.NVL(fmbvo.getF02B())))){%>checked <%}%>><span>어머니</span></label>
                        <label><input type='radio' name ='r4' class='r' value="3" <% if("3".equals((fmbvo == null ? "" : StringUtil.NVL(fmbvo.getF02B())))){%>checked <%}%>><span>형제</span></label>
                        <label><input type='radio' name ='r4' class='r' value="4" <% if("4".equals((fmbvo == null ? "" : StringUtil.NVL(fmbvo.getF02B())))){%>checked <%}%>><span>자매</span></label>
                        <label><input type='radio' name ='r4' class='r' value="5" <% if("5".equals((fmbvo == null ? "" : StringUtil.NVL(fmbvo.getF02B())))){%>checked <%}%>><span>아들</span></label>
                        <label><input type='radio' name ='r4' class='r' value="6" <% if("6".equals((fmbvo == null ? "" : StringUtil.NVL(fmbvo.getF02B())))){%>checked <%}%>><span>딸</span></label>
                    </div>
                </td>
            </tr>
            <tr>
                <th>3-1</th>
                <td class="ta_l">
                    <p>가족(아버지, 어머나, 형제, 자매, 아들, 딸) 중 뇌졸중(중풍)을 진단받은 사람이 있습니까?</p>

                    <div class="ans first">
                        <label><input type='radio' name ='r5' class='r' value="1" <% if("1".equals((fmbvo == null ? "" : StringUtil.NVL(fmbvo.getF03A())))){%>checked <%}%>><span>예</span></label>
                        <label><input type='radio' name ='r5' class='r' value="2" <% if("2".equals((fmbvo == null ? "" : StringUtil.NVL(fmbvo.getF03A())))){%>checked <%}%>><span>아니오</span></label>
                    </div>
                </td>
            </tr>
            <tr>
                <th style="border-bottom:1px solid #c4d9f6;">3-2</th>
                <td class="ta_l">
                    <p>누가 진단 받았습니까? 진단받은 가족 모두 표시해 주세요.</p>

                    <div class="ans first">
                        <label><input type='radio' name ='r6' class='r' value="1" <% if("1".equals((fmbvo == null ? "" : StringUtil.NVL(fmbvo.getF03B())))){%>checked <%}%>><span>아버지</span></label>
                        <label><input type='radio' name ='r6' class='r' value="2" <% if("2".equals((fmbvo == null ? "" : StringUtil.NVL(fmbvo.getF03B())))){%>checked <%}%>><span>어머니</span></label>
                        <label><input type='radio' name ='r6' class='r' value="3" <% if("3".equals((fmbvo == null ? "" : StringUtil.NVL(fmbvo.getF03B())))){%>checked <%}%>><span>형제</span></label>
                        <label><input type='radio' name ='r6' class='r' value="4" <% if("4".equals((fmbvo == null ? "" : StringUtil.NVL(fmbvo.getF03B())))){%>checked <%}%>><span>자매</span></label>
                        <label><input type='radio' name ='r6' class='r' value="5" <% if("5".equals((fmbvo == null ? "" : StringUtil.NVL(fmbvo.getF03B())))){%>checked <%}%>><span>아들</span></label>
                        <label><input type='radio' name ='r6' class='r' value="6" <% if("6".equals((fmbvo == null ? "" : StringUtil.NVL(fmbvo.getF03B())))){%>checked <%}%>><span>딸</span></label>
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
</html>