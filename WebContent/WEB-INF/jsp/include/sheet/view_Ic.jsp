<%@page import="java.sql.Date"%>
<%@page import="csbrain.common.service.MbFmhVO"%>
<%@page import="csbrain.common.service.MbFhVO"%>
<%@page import="csbrain.common.service.MbIcVO"%>
<%@page import="csbrain.common.util.DateUtil"%>
<%@page import="csbrain.common.util.StringUtil"%>
<%@page import="csbrain.common.service.MbC3VO"%>
<%@page import="csbrain.common.service.MbC2VO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/common.inc"%>
<jsp:include page='<%=incInit%>' flush="false"></jsp:include>
<%
	String sdate = request.getParameter("sdate");
	
	//if ( StringUtil.isEmptyString(edate) ) edate = DateUtil.getTodayByFormat("yyyy-MM-dd");


	List<MbIcVO> ic_list = (List<MbIcVO>) request.getAttribute("ic_list");
	MbIcVO icvo = null;
//	MbC3VO c3vo = null;

	if(ic_list != null && ic_list.size() > 0 ){
		icvo =ic_list.get(0);
		
		String d = icvo.getTestDay();
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
		width:200px;
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
.table.type02 tbody th.ta_l {padding-left:6px;}
	
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.js"></script>
<script>
	$(function(){
	/*
		$("input").click(function(){
			return false;
		});
		
	*/
	var action = $('#action').val();
	if(action =='hidden'){
	     $('#btnArea').css({"visibility": "hidden"});
	}
	
	var idx = $('#idx').val();
		console.log(idx);
		
		$("#new").click(function() {
			if(idx == ''){
				alert("검색항목을 입력해주세요");
				return false;
			
			}
				
			
			if($('#insert').css("display")=="none"){
				$('#insert').show();	
			}
		$('input:text[name="sdate"]').attr("disabled", false);
		$('input:text[name="ddate"]').attr("disabled", false);
		$('input:radio[class="r"]:not(:checked)').attr("disabled", false);
	   	 $(this).prop('checked', false);
	   	 $('input[class="r"]').removeAttr('checked');
		 $('input[class="t"]').val('');
		 $('input[name="sdate"]').val('');
		 $('input[name="ddate"]').val('');
	   	 	var selnew = $("#new").val(); 
	   	 	console.log(selnew+"::selenew")
			checking = $('#chk').val(selnew);
		});
		
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
		
		$("#edit").click(function() {
			if(idx == ''){
				alert("검색항목을 입력해주세요");
			//	return false;
			var e
			}
				
			
			if($('#insert').css("display")=="none"){
				$('#insert').show();	
			}
		$('input:text[name="sdate"]').attr("disabled", false);
		$('input:text[name="ddate"]').attr("disabled", false);
		$('input:radio[class="r"]:not(:checked)').attr("disabled", false);
	   	 $(this).prop('checked', false);
		
			
	   	 	var selnew = $("#edit").val(); 
	   	 	console.log(selnew+"::selenew")
			checking = $('#chk').val(selnew);
		});
	
	$('#insert').click(function() {
		
		//배열선언
		
		var radioArr = [];
		
		var sdates = $("#sdate").val();
		var ddates = $("#ddate").val();
		var selection_num = $("#selectionNum").val();
		var exam_num= $("#selectionNum").val();
		var exam_idx= $("#examIdx").val(); 
  		var exam_id = $("#exid").val();		
		console.log("exam_num::"+exam_num)
	//	console.log("selection_num::"+selection_num)
		console.log("설문실행일:"+sdates);
		console.log("설문실행일:"+ddates);
		var whichChk = $("#chk").val();
		console.log(whichChk+":::whichChk")
		var cdbs = $("#cdbs").val();
	
		
		
		var value;
		$('input:radio[class="r"]:checked').each(function(){//체크된 리스트 저장
			
			value = $(this).val();
			
			 radioArr.push(value);
        });
	
		var num;
		var num1;
		 num = $('input:radio[class="r"]:checked').length;
 		console.log(num+"저장된체크넘버");
 		num1 = $('input:radio[class="r"]:not(:checked)').length;

 		
 		if(num == 3){
 		
 	
		
		var objParams = {
			"radioArr" : radioArr,	
			"sdate" : sdates,  
			"ddate" : ddates,
			"idx" : idx,
			"whichChk" : whichChk,
			"exam_num" : exam_num,
			"exam_idx" : exam_idx,
  			"exam_id" : exam_id,
			"selectionNum" : selection_num,
			"cdbs" : cdbs
	
		};
		
		//ajax호출
		$.ajax({
			url         :   getContextPath()+"/include/sheet/view_saveIc.do",
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
            error 	:   function(request, status, error){
                console.log("AJAX_ERROR");
            }
	
		});
 		}else if(num < 3){
 			alert("체크 되지 않는 검사지항목이 존재합니다. \n입력해주시길바랍니다.")
 		}
		
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
	console.log("ddateVal뒤::"+ddateVal);
		
	
		
	 $('input:radio[class="r"]:not(:checked)').attr("disabled", true); //비활성화
			
	 
	 $('input:text[name="sdate"]').attr("disabled", true);
	 $('input:text[name="ddate"]').attr("disabled", true);
		
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
	<button name="수정" id="edit" class ="csbr1010btn"  value="edit" >수정</button>
	<button name="신규" id ="new" class ="csbr1010btn"  value="new">신규</button>
	<input type="hidden" id ="object_idx" style="display:none;" class ="object_idx" value='<%= icvo == null ? "" :StringUtil.NVL(icvo.getObjectIdx())%>'>
	<input type='hidden' name='selectionNum' id="selectionNum"  value='<%= icvo == null ? "" :StringUtil.NVL(icvo.getSelectionNum())%>'> 
	<input type='hidden' name='examIdx' id="examIdx"  value='<%= icvo == null ? "" :StringUtil.NVL(icvo.getIdx())%>'> 
	<input name="idx" id ="idx" class ="idx" value="${idx}" style="display:none;"/>
	<input type ="hidden" name="exid" id ="exid" class ="exid" value="${exam_id}" style="display:none;"/>
	<input type ="hidden" name="cdbs" id ="cdbs" class ="cdbs" value="0015" style="display:none;"/>
	<input type ="hidden" name="exnum" id ="exnum" class ="exnum" value="${exam_num}" style="display:none;"/>                     
	<input type ="hidden" name="chk" id ="chk" class ="chk" value="" style="display:none;"/>
</div>
<div id="tabbox" style="height:auto;">

	<table class="table type02">
        <colgroup>
            <col width="40%" />
            <col width="30%" />
            <col width="30%" />
        </colgroup>
        <thead>
            <tr>
                <th class="bg1 tbl_tit" colspan="3">참여동의</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <th class="ta_l">연구 참여 동의일</th>
                <td colspan="2" class="ta_l">
					<input type='text' class='calendar' id='sdate' name='sdate' placeholder='yyyy/mm/dd'  style='width:120px;' value ='<%= icvo == null ? "" :StringUtil.NVL(DateUtil.getSimpleDate("yyyyMMdd",icvo.getTestDay()))%>'>
                </td>
            </tr>
            <tr>
                <th class="ta_l">연구 참여 동의 철회일</th>
                <td colspan="2" class="ta_l">
                    <input type='text' class='calendar' id='ddate' name='ddate' placeholder='yyyy/mm/dd'  style='width:120px;' 
			<%if(icvo == null) {%>value=""<%}
			else if(StringUtil.NVL(icvo.getIcFdropDate()) == "" ){%>value=""<%}
			else{%>value="<%=StringUtil.NVL(DateUtil.getSimpleDate("yyyyMMdd",icvo.getIcFdropDate())) %>"<%}%>>
                </td>
            </tr>
            <tr>
                <th class="ta_l">연구 참여 동의서 사본</th>
                <td><label><input type='radio' name ='r1' class='r' value="1" <% if("1".equals((icvo == null ? "" : StringUtil.NVL(icvo.getIcFCopy())))){%>checked <%}%>><span>예</span></label></td>
                <td><label><input type='radio' name ='r1' class='r' value="2" <% if("2".equals((icvo == null ? "" : StringUtil.NVL(icvo.getIcFCopy())))){%>checked <%}%>><span>아니오</span></label></td>
            </tr>
            <tr>
                <th class="ta_l">인체 유래물 연구 동의서 사본</th>
                <td><label><input type='radio' name ='r2' class='r' value="1" <% if("1".equals((icvo == null ? "" : StringUtil.NVL(icvo.getIcFsmpCopy())))){%>checked <%}%>><span>예</span></label></td>
                <td><label><input type='radio' name ='r2' class='r' value="2" <% if("2".equals((icvo == null ? "" : StringUtil.NVL(icvo.getIcFsmpCopy())))){%>checked <%}%>><span>아니오</span></label></td>
            </tr>
            <tr>
                <th class="ta_l">인체 유래물등의 기증 동의서 사본</th>
                <td><label><input type='radio' name ='r3' class='r' value="1" <% if("1".equals((icvo == null ? "" : StringUtil.NVL(icvo.getIcFsmpdntCopy())))){%>checked <%}%>><span>예</span></label></label></td>
                <td><label><input type='radio' name ='r3' class='r' value="2" <% if("2".equals((icvo == null ? "" : StringUtil.NVL(icvo.getIcFsmpdntCopy())))){%>checked <%}%>><span>아니오</span></label></td>
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
var ddateVal = $('#ddate').val();

console.log("sdateVal::"+sdateVal);
console.log("ddateVal::"+ddateVal);
	


</script>
</html>
