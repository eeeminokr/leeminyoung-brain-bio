<%@page import="java.sql.Date"%>
<%@page import="csbrain.common.util.StringUtil"%>
<%@page import="csbrain.common.util.DateUtil"%>
<%@page import="csbrain.common.service.MbC2VO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/common.inc"%>
<jsp:include page='<%=incInit%>' flush="false"></jsp:include>
<%
	List<MbC2VO> c2_list1 = (List<MbC2VO>) request.getAttribute("c2_list1");
	MbC2VO c2vo = null;

	if( c2_list1 != null && c2_list1.size() > 0 ){
		c2vo = c2_list1.get(0);
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
		var exam_id = $("#exid").val();
		var sdates = $("#sdate").val();
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
			console.log(value+":[KDSQ 텍스트]values1["+i+"]");
			
			getNm = $(this).attr('name');
			console.log(getNm+":::::::::::getNm")
			
		
			
			
			var Fval =  $('input:radio[class="r"]:checked').length;
			console.log(Fval+"체크한카운트")
			if(Fval < 4){
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
		var tab_exam_nums1 = $('#tab_exam_nums').val();


		var objParams = {	
				"radioArr" : radioArr,
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
			url         :   getContextPath()+"/include/sheet/view_savecognition2_1.do",
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
	<input type ="hidden" name="cdbs" id ="cdbs" class ="cdbs" value="0206" style="display:none;"/>
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
                <th class="bg1 tbl_tit" colspan="5">지역, 사회, 교양 또는 직업활동</th>
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
                <th>S.</th>
                <th>지역, 사회, 교양 또는 직업활동</th>
                <th class="bg2">아니오</th>
                <th class="bg2">예</th>
                <th class="bg2">불확실</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <th>1</th>
                <td class="ta_l">대상자가 신체적 어려움과 무관한 이유로 사회적 또는 지역사회에서의 활동(친지방문, 결혼식 참석이나 상가방문, 교회나 절에 감)을 잘 수행하지 못하거나 혹은 그 횟수가 감소되었는가?</td>
                <td><label><input type='radio' name="r1" class='r' value="1" <% if("1".equals((c2vo == null ? "" : StringUtil.NVL(c2vo.getcS01())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' name="r1" class='r' value="2" <% if("2".equals((c2vo == null ? "" : StringUtil.NVL(c2vo.getcS01())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' name="r1" class='r' value="3" <% if("3".equals((c2vo == null ? "" : StringUtil.NVL(c2vo.getcS01())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th>2</th>
                <td class="ta_l">대상자가 특정 기술이나 흥미 혹은 취미(예: 바느질, 정원, 가꾸기, 독서, 화투, 장기나 바둑)를 상실하였는가?</td>
                <td><label><input type='radio' name="r2" class='r' value="1" <% if("1".equals((c2vo == null ? "" : StringUtil.NVL(c2vo.getcS02())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' name="r2" class='r' value="2" <% if("2".equals((c2vo == null ? "" : StringUtil.NVL(c2vo.getcS02())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' name="r2" class='r' value="3" <% if("3".equals((c2vo == null ? "" : StringUtil.NVL(c2vo.getcS02())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th>3</th>
                <td class="ta_l">대상자가 사회적으로 부적절한 행동이나 대화를 하는가?</td>
                <td><label><input type='radio' name="r3" class='r' value="1" <% if("1".equals((c2vo == null ? "" : StringUtil.NVL(c2vo.getcS03())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' name="r3" class='r' value="2" <% if("2".equals((c2vo == null ? "" : StringUtil.NVL(c2vo.getcS03())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' name="r3" class='r' value="3" <% if("3".equals((c2vo == null ? "" : StringUtil.NVL(c2vo.getcS03())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
            </tr>
            <tr>
                <th>4</th>
                <td class="ta_l">대상자가 직업수행능력의 손상을 보이는가?</td>
                <td><label><input type='radio' name="r4" class='r' value="1" <% if("1".equals((c2vo == null ? "" : StringUtil.NVL(c2vo.getcS04())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
                <td><label><input type='radio' name="r4" class='r' value="2" <% if("2".equals((c2vo == null ? "" : StringUtil.NVL(c2vo.getcS04())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' name="r4" class='r' value="3" <% if("3".equals((c2vo == null ? "" : StringUtil.NVL(c2vo.getcS04())))){%>checked <%}%>><span><em class="blind">불확실</em></span></label></td>
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