<%@page import="java.sql.Date"%>
<%@page import="csbrain.common.util.StringUtil"%>
<%@page import="csbrain.common.service.MbMhVO"%>
<%@page import="csbrain.common.util.DateUtil"%>
<%@page import="csbrain.common.util.StringUtil"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/common.inc"%>
<jsp:include page='<%=incInit%>' flush="false"></jsp:include>
<%
	String sdate = request.getParameter("sdate");
	String exam_num =request.getParameter("exam_num");	
	List<MbMhVO> mh_list = (List<MbMhVO>) request.getAttribute("mh_list");
	MbMhVO vo = null;

	if( mh_list != null && mh_list.size() > 0 ){
		vo = mh_list.get(0);
		
		
		String d = vo.getTestDay();
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
		box-sizing: border-box; */
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
  		var exam_id = $("#exid").val();
		var exam_num= $("#selectionNum").val();
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
		$('input:radio[class="r"]:checked').each(function(i){//체크된 리스트 저장
			
			value = $(this).val();
			console.log(value+":[mh 텍스트]values1["+i+"]");

			var Fval =  $('input:radio[class="r"]:checked').length;
			console.log(Fval+"체크한카운트")
			if(Fval < 22){
				alert("체크되지 않는 입력값이 있습니다!")
				return false;
			}else{
				 radioArr.push(value);		
			}
			
			
		
        });
		var value1;
		$('input:text[class="t"]').each(function(i) {
			
			value1 =$(this).val();
			
			console.log(value1+":[mh 텍스트]values1["+i+"]");
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
  			"exam_id" : exam_id,			
			"whichChk" : whichChk,
			"exam_num" : exam_num,
			"exam_idx" : exam_idx,
			"cdbs" : cdbs,
			"exam_id" : exam_id
	
		};
		
		//ajax호출
		$.ajax({
			url         :   getContextPath()+"/include/sheet/view_savemedical.do",
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
            			   //parent.document;
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

<div id="btnArea" class="btn_wrap top">
	<input type ="hidden" id="action" value="${action}" style="display:none;"/>
	<!-- <button name="저장" id="insert" class ="csbr1010btn" style="display:none;">저장</button> -->
	<button name="수정" id="edit" class ="csbr1010btn"  value="edit">수정</button>
	<button name="신규" id ="new" class ="csbr1010btn"  value="new">신규</button>

	<input type="hidden" id ="object_idx" style="display:none;" class ="object_idx" value='<%= vo == null ? "" :StringUtil.NVL(vo.getObjectIdx())%>'>
	<input type='hidden' name='selectionNum' id="selectionNum"  value='<%= vo == null ? "" :StringUtil.NVL(vo.getSelectionNum())%>'> 
	<input type='hidden' name='examIdx' id="examIdx"  value='<%= vo == null ? "" :StringUtil.NVL(vo.getIdx())%>'> 
	<input name="idx" id ="idx" class ="idx" value="${idx}" style="display:none;"/>
	<input type ="hidden" name="exnum" id ="exnum" class ="exnum" value="${exam_num}" style="display:none;"/>
	<input type ="hidden" name="exid" id ="exid" class ="exid" value="${exam_id}" style="display:none;"/>   
	<input type ="hidden" name="cdbs" id ="cdbs" class ="cdbs" value="0004" style="display:none;"/>
	<input type ="hidden" name="chk" id ="chk" class ="chk" value="" style="display:none;"/>
</div>
<div id="tabbox"  style="height:auto;">

	<table class="table type02">
        <colgroup>
            <col width="40px" />
            <col width="*" />
            <col width="45px" />
            <col width="45px" />
            <col width="45px" />
            <col width="45px" />
            <col width="45px" />
            <col width="45px" />
        </colgroup>
        <thead>
            <tr>
                <th class="bg1 tbl_tit" colspan="8">질병력</th>
            </tr>
            <tr>
                <th class="survey_date" colspan="8">
                    <div class="sv_text">설문실행일</div>
                    <div class="sv_date">
						<input type='text' class='calendar' id='sdate' name='sdate' placeholder='yyyy/mm/dd'  style='width:120px;' value ='<%= vo == null ? "" :StringUtil.NVL(DateUtil.getSimpleDate("yyyyMMdd",vo.getTestDay()))%>'>	
					</div>
                </th>
            </tr>
            <tr>
                <th colspan="2" rowspan="2">대상자가 아래와 같은 진단을 받은적 있는가?</th>
                <th colspan="3" class="bg2">과거(P)</th>
                <th colspan="3" class="bg2">미래(N)</th>
            </tr>
            <tr>
                <th class="bg2">아니오</th>
                <th class="bg2">예</th>
                <th class="bg2">불확실</th>
                <th class="bg2">아니오</th>
                <th class="bg2">예</th>
                <th class="bg2">불확실</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <th rowspan="7">1</th>
                <td class="ta_l">심장병</td>
                <td><label><input type='radio' class='r'  name="r1" value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getMh01P())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r1" value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getMh01P())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r1" value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getMh01P())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r21" value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getMh01N())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r21" value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getMh01N())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r21" value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getMh01N())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <td class="ta_l">'예'라면, 종류는?</td>
                <td colspan="3"></td>
                <td colspan="3"></td>
            </tr>
            <tr>
                <td class="ta_l">a. 심장혈관질환(협심증, 심근경색증 등)</td>
                <td><label><input type='radio' class='r'  name="r2" value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getMh01AP())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r2" value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getMh01AP())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r2" value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getMh01AP())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r22" value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getMh01AN())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r22" value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getMh01AN())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r22" value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getMh01AN())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <td class="ta_l">b. 심(장)부전(심장기능저하 또는 상실)</td>
                <td><label><input type='radio' class='r'  name="r3" value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getMh01BP())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r3" value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getMh01BP())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r3" value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getMh01BP())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r23" value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getMh01BN())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r23" value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getMh01BN())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r23" value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getMh01BN())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <td class="ta_l">c. 부정맥(또는 맥박이 건너 뜀)</td>
                <td><label><input type='radio' class='r'  name="r4" value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getMh01CP())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r4" value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getMh01CP())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r4" value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getMh01CP())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r24" value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getMh01CN())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r24" value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getMh01CN())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r24" value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getMh01CN())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <td class="ta_l">d. 심장판막관련질환</td>
                <td><label><input type='radio' class='r'  name="r5" value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getMh01DP())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r5" value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getMh01DP())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r5" value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getMh01DP())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r25" value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getMh01DN())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r25" value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getMh01DN())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r25" value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getMh01DN())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <td class="ta_l">e. 기타</td>
                <td><label><input type='radio' class='r'  name="r6" value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getMh01EP())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r6" value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getMh01EP())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r6" value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getMh01EP())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r26" value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getMh01EN())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r26" value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getMh01EN())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r26" value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getMh01EN())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th>2</th>
                <td class="ta_l">현재 고혈압을 앓고 있습니까?</td>
                <td colspan="3"></td>
                <td><label><input type='radio' class='r'  name="r27" value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getMh02N())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r27" value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getMh02N())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r27" value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getMh02N())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th>3</th>
                <td class="ta_l">현재 당뇨를 앓고 있습니까?</td>
                <td colspan="3"></td>
                <td><label><input type='radio' class='r'  name="r28" value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getMh03N())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r28" value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getMh03N())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r28" value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getMh03N())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th rowspan="3">4</th>
                <td class="ta_l">과거, 현재 상관없이 의사에게 이상지혈증(고지혈증)을 진단받은 적이 있습니까?</td>
                <td colspan="3"></td>
                <td><label><input type='radio' class='r'  name="r29" value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getMhNew04AN())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r29" value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getMhNew04AN())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td></td>
            </tr>
            <tr>
                <td class="ta_l">과거, 현재 상관없이 의사에게 응고결합, 자반 및 기타 출혈성 병태를 진단받은 적이 있습니까?</td>
                <td colspan="3"></td>
                <td><label><input type='radio' class='r'  name="r30" value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getMhNew04BN())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r30" value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getMhNew04BN())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td></td>
            </tr>
            <tr>
                <td class="ta_l">50세 이후에 전신 마취를 한 경험이 있습니까?</td>
                <td colspan="3"></td>
                <td><label><input type='radio' class='r'  name="r31" value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getMhNew04CN())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r31" value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getMhNew04CN())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td></td>
            </tr>
            <tr>
                <th>5</th>
                <td class="ta_l">일상적인 대화에 영향을 미치는 청력의 저하가 있습니까?</td>
                <td colspan="3"></td>
                <td><label><input type='radio' class='r'  name="r32" value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getMh04N())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r32" value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getMh04N())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r32" value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getMh04N())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th>6</th>
                <td class="ta_l">도움을 받을 정도의 시력 저하가 있습니까?</td>
                <td colspan="3"></td>
                <td><label><input type='radio' class='r'  name="r33" value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getMh05N())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r33" value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getMh05N())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r33" value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getMh05N())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th>7</th>
                <td class="ta_l">전간(경련)</td>
                <td><label><input type='radio' class='r'  name="r7" value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getMh06P())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r7" value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getMh06P())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r7" value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getMh06P())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r34" value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getMh06N())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r34" value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getMh06N())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r34" value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getMh06N())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th>8</th>
                <td class="ta_l">갑상선 질환(갑상선 기능저하 또는 항진)</td>
                <td><label><input type='radio' class='r'  name="r8" value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getMh07P())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r8" value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getMh07P())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r8" value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getMh07P())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r35" value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getMh07N())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r35" value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getMh07N())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r35" value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getMh07N())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th>9</th>
                <td class="ta_l">암</td>
                <td><label><input type='radio' class='r'  name="r9" value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getMh08P())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r9" value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getMh08P())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r9" value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getMh08P())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
                <td colspan="3"></td>
            </tr>
            <tr>
                <th>10</th>
                <td class="ta_l">머리에 심한 충격을 받은 적이 있습니까?</td>
                <td><label><input type='radio' class='r'  name="r10" value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getMh09P())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r10" value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getMh09P())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r10" value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getMh09P())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
                <td colspan="3"></td>
            </tr>
            <tr>
                <th>11</th>
                <td class="ta_l">있다면 의식소실이 있었습니까?</td>
                <td><label><input type='radio' class='r'  name="r11" value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getMh10P())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r11" value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getMh10P())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r11" value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getMh10P())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r36" value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getMh08N())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r36" value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getMh08N())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r36" value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getMh08N())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th>12</th>
                <td class="ta_l">신장(콩팥)질환</td>
                <td><label><input type='radio' class='r'  name="r12" value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getMh11P())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r12" value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getMh11P())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r12" value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getMh11P())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r37" value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getMh11N())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r37" value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getMh11N())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r37" value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getMh11N())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th>13</th>
                <td class="ta_l">간질환(간염, 간경화 등)</td>
                <td><label><input type='radio' class='r'  name="r13" value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getMh12P())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r13" value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getMh12P())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r13" value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getMh12P())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r38" value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getMh12N())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r38" value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getMh12N())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r38" value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getMh12N())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th>14</th>
                <td class="ta_l">뇌 감염질환(매독, AIDS, HIV 등)</td>
                <td><label><input type='radio' class='r'  name="r14" value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getMh13P())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r14" value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getMh13P())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r14" value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getMh13P())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r39" value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getMh13N())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r39" value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getMh13N())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r39" value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getMh13N())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th>15</th>
                <td class="ta_l">중추신경계 질환(치매, 뇌졸중, 파킨슨씨병, 다발성경화)</td>
                <td><label><input type='radio' class='r'  name="r15" value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getMh14P())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r15" value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getMh14P())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r15" value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getMh14P())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r40" value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getMh14N())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r40" value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getMh14N())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r40" value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getMh14N())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th>16</th>
                <td class="ta_l">폐질환(만성기관지염, 폐기종, 기타)</td>
                <td><label><input type='radio' class='r'  name="r16" value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getMh15P())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r16" value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getMh15P())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r16" value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getMh15P())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r41" value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getMh15N())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r41" value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getMh15N())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r41" value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getMh15N())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th>17</th>
                <td class="ta_l">관절염</td>
                <td><label><input type='radio' class='r'  name="r17" value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getMh16P())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r17" value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getMh16P())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r17" value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getMh16P())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r42" value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getMh16N())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r42" value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getMh16N())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r42" value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getMh16N())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th>18</th>
                <td class="ta_l">척추질환(디스크(추간판탈출증), 골절, 포함)</td>
                <td><label><input type='radio' class='r'  name="r18" value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getMh17P())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r18" value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getMh17P())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r18" value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getMh17P())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r43" value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getMh17N())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r43" value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getMh17N())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r43" value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getMh17N())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th>19</th>
                <td class="ta_l">우울증, 알코올 문제 등을 비롯한 정신건강질환</td>
                <td><label><input type='radio' class='r'  name="r19" value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getMh18P())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r19" value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getMh18P())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r19" value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getMh18P())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r44" value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getMh18N())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r44" value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getMh18N())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r44" value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getMh18N())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th>20</th>
                <td class="ta_l">전립선 비대</td>
                <td colspan="3"></td>
                <td><label><input type='radio' class='r'  name="r45" value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getMh21N())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r45" value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getMh21N())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r45" value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getMh21N())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th>21</th>
                <td class="ta_l">수술을 받은적이 있습니까?</td>
                <td><label><input type='radio' class='r'  name="r20" value="1" <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getMh19P())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r20" value="2" <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getMh19P())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r'  name="r20" value="3" <% if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getMh19P())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
                <td colspan="3"></td>
            </tr>
            <tr>
                <th>22</th>
                <td class="ta_l">'예'라고 답한 항목에 대하여 설명하시오.</td>
                <td colspan="6" class="ta_l"><input type='text' id="reason" name='t3a' minlength="1" maxlength="50" class='t' value='<%=(vo == null ? "" : StringUtil.NVL(vo.getMh20()))%>' style='width:248px;'></td>
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