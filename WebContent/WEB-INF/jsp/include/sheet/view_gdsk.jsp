<%@page import="java.lang.reflect.Method"%>
<%@page import="javax.script.Compilable"%>
<%@page import="javax.script.ScriptEngine"%>
<%@page import="javax.script.ScriptEngineManager"%>
<%@page import="csbrain.common.service.MbGdsVO"%>
<%@page import="java.sql.Date"%>
<%@page import="csbrain.common.util.StringUtil"%>
<%@page import="csbrain.common.util.DateUtil"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/common.inc"%>
<jsp:include page='<%=incInit%>' flush="false"></jsp:include>
<%
	List<MbGdsVO> gdsk_list = (List<MbGdsVO>) request.getAttribute("gdsk_list");
	MbGdsVO vo = null;
	if( gdsk_list != null && gdsk_list.size() > 0 ){
		vo = gdsk_list.get(0);
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
		//alert("examN"+getexmN)
		
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
			
			getNm = $(this).attr('name');
			console.log(getNm+":::::::::::getNm")
			
			if(value == '1'){
				
				tscore += 1;
		
			}else if(value =='2'|| getNm =="gds01" || getNm =="gds05"|| getNm =="gds07" ||getNm =="gds09"
                ||getNm =="gds15" || getNm =="gds19" || getNm =="gds21" || getNm =="gds27"
	                  || getNm =="gds29" || getNm =="gds30"){
				if(getNm =="gds01" || getNm =="gds05"|| getNm =="gds07" ||getNm =="gds09"
	                  ||getNm =="gds15" || getNm =="gds19" || getNm =="gds21" || getNm =="gds27"
	                  || getNm =="gds29" || getNm =="gds30"){
						
						tscore += 1;		
					}else{
						tscore += 0;	
					}
			}
			
			
			var Fval =  $('input:radio[class="r"]:checked').length;
			console.log(Fval+"체크한카운트")
			if(Fval < 30){
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
				"tscore" : tscore,
				"cdbs" : cdbs
	
		};
		
		//ajax호출
		$.ajax({
			url         :   getContextPath()+"/include/sheet/view_savegdsk.do",
            dataType    :   "json",
            contentType :   "application/x-www-form-urlencoded; charset=UTF-8",
            type        :   "post",
            data        :   objParams,
            success     :   function(json){
            	console.log(json.model.message);
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
		if ( "1".equals(v) ) checked1 = "checked";
		if ( "2".equals(v) ) checked2 = "checked";
		String radioTag = "<input type='radio' class='r' " + checked1 + " style='left:25px;top:5px;'>"
		+ "<input type='radio' class='r'  " + checked2 + " style='left:84px;top:5px;'>";
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
	<input type ="hidden" name="cdbs" id ="cdbs" class ="cdbs" value="0007" style="display:none;"/>
	<input type ="hidden" name="chk" id ="chk" class ="chk" value="" style="display:none;"/>
</div> 

<div id="tabbox" style="height:auto;">

	<table class="table type02">
        <colgroup>
            <col width="40px" />
            <col width="*" />
            <col width="45px" />
            <col width="45px" />
        </colgroup>
        <thead>
            <tr>
                <th class="bg1 tbl_tit" colspan="4">GDS-K</th>
            </tr>
            <tr>
                <th class="survey_date" colspan="4">
                    <div class="sv_text">설문실행일</div>
                    <div class="sv_date">
						<input type='text' class='calendar' id='sdate' name='sdate' placeholder='yyyy/mm/dd'  style='width:120px;' value ='<%= vo == null ? "" :StringUtil.NVL(DateUtil.getSimpleDate("yyyyMMdd",vo.getTestDay()))%>'>	
					</div>
                </th>
            </tr>
            <tr>
                <th colspan="2"></th>
                <th class="bg2">예</th>
                <th class="bg2">아니오</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <th>1</th>
                <td class="ta_l">자신의 삶에 대체로 만족하십니까?</td>
                <td><label><input type='radio' class='r' name='gds01' value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getGds01())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r' name='gds01' value='2' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getGds01())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
            </tr>
            <tr>
                <th>2</th>
                <td class="ta_l">활동이나 관심거리가 많이 줄었습니까?</td>
                <td><label><input type='radio' class='r' name='gds02' value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getGds02())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r' name='gds02' value='2' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getGds02())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
            </tr>
            <tr>
                <th>3</th>
                <td class="ta_l">삶이 공허하다고 느끼십니까?</td>
                <td><label><input type='radio' class='r' name='gds03' value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getGds03())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r' name='gds03' value='2' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getGds03())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
            </tr>
            <tr>
                <th>4</th>
                <td class="ta_l">지루하거나 따분할 때가 많습니까?</td>
                <td><label><input type='radio' class='r' name='gds04' value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getGds04())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r' name='gds04' value='2' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getGds04())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
            </tr>
            <tr>
                <th>5</th>
                <td class="ta_l">앞날이 희망적이라고 생각하십니까?</td>
                <td><label><input type='radio' class='r' name='gds05' value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getGds05())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r' name='gds05' value='2' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getGds05())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
            </tr>
            <tr>
                <th>6</th>
                <td class="ta_l">떨쳐버릴 수 없는 생각들 때문에 괴롭습니까?</td>
                <td><label><input type='radio' class='r' name='gds06' value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getGds06())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r' name='gds06' value='2' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getGds06())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
            </tr>
            <tr>
                <th>7</th>
                <td class="ta_l">대체로 활기차게 사는 편입니까?</td>
                <td><label><input type='radio' class='r' name='gds07' value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getGds07())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r' name='gds07' value='2' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getGds07())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
            </tr>
            <tr>
                <th>8</th>
                <td class="ta_l">자신에게 좋지 않은 일이 생길 것 같아 걱정스럽습니까?</td>
                <td><label><input type='radio' class='r' name='gds08' value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getGds08())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r' name='gds08' value='2' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getGds08())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
            </tr>
            <tr>
                <th>9</th>
                <td class="ta_l">대체로 행복하다고 느끼십니까?</td>
                <td><label><input type='radio' class='r' name='gds09'  value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getGds09())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r' name='gds09'  value='2' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getGds09())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
            </tr>
            <tr>
                <th>10</th>
                <td class="ta_l">아무 것도 할 수 없을 것 같은 무력감이 자주 듭니까?</td>
                <td><label><input type='radio' class='r' name='gds10' value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getGds10())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r' name='gds10' value='2' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getGds10())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
            </tr>
            <tr>
                <th>11</th>
                <td class="ta_l">불안해지거나 안절부절 못 할 때가 자주 있습니까?</td>
                <td><label><input type='radio' class='r' name='gds11' value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getGds11())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r' name='gds11' value='2' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getGds11())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
            </tr>
            <tr>
                <th>12</th>
                <td class="ta_l">외출하는 것 보다 그냥 집안에 있는 것이 더 좋습니까?</td>
                <td><label><input type='radio' class='r' name='gds12' value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getGds12())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r' name='gds12' value='2' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getGds12())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
            </tr>
            <tr>
                <th>13</th>
                <td class="ta_l">앞날에 대한 걱정을 자주 하십니까?</td>
                <td><label><input type='radio' class='r' name='gds13' value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getGds13())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r' name='gds13' value='2' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getGds13())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
            </tr>
            <tr>
                <th>14</th>
                <td class="ta_l">다른 사람들보다 기억력에 문제가 더 많다고 느끼십니까?</td>
                <td><label><input type='radio' class='r' name='gds14' value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getGds14())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r' name='gds14' value='2' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getGds14())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
            </tr>
            <tr>
                <th>15</th>
                <td class="ta_l">지금 살아있다는 사실이 정말 좋다고 느껴지십니까?</td>
                <td><label><input type='radio' class='r' name='gds15' value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getGds15())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r' name='gds15' value='2' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getGds15())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
            </tr>
            <tr>
                <th>16</th>
                <td class="ta_l">기분이 가라않거나 울적할 때가 자주 있습니까?</td>
                <td><label><input type='radio' class='r' name='gds16' value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getGds16())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r' name='gds16' value='2' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getGds16())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
            </tr>
            <tr>
                <th>17</th>
                <td class="ta_l">요즘 자신이 아무 쓸모없는 사람처럼 느껴지십니까?</td>
                <td><label><input type='radio' class='r' name='gds17' value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getGds17())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r' name='gds17' value='2' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getGds17())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
            </tr>
            <tr>
                <th>18</th>
                <td class="ta_l">지난 일에 대해 걱정을 많이 하십니까?</td>
                <td><label><input type='radio' class='r' name='gds18' value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getGds18())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r' name='gds18' value='2' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getGds18())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
            </tr>
            <tr>
                <th>19</th>
                <td class="ta_l">산다는 것이 매우 신나고 즐겁습니까?</td>
                <td><label><input type='radio' class='r' name='gds19' value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getGds19())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r' name='gds19' value='2' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getGds19())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
            </tr>
            <tr>
                <th>20</th>
                <td class="ta_l">새로운 일을 시작하는 것이 어렵습니까?</td>
                <td><label><input type='radio' class='r' name='gds20' value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getGds20())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r' name='gds20' value='2' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getGds20())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
            </tr>
            <tr>
                <th>21</th>
                <td class="ta_l">생활의 활력이 넘치십니까?</td>
                <td><label><input type='radio' class='r' name='gds21' value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getGds21())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r' name='gds21' value='2' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getGds21())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
            </tr>
            <tr>
                <th>22</th>
                <td class="ta_l">자신의 처지가 절망적이라고 느끼십니까?</td>
                <td><label><input type='radio' class='r' name='gds22' value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getGds22())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r' name='gds22' value='2' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getGds22())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
            </tr>
            <tr>
                <th>23</th>
                <td class="ta_l">다른 사람들이 대체로 자신보다 낫다고 느끼십니까?</td>
                <td><label><input type='radio' class='r' name='gds23' value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getGds23())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r' name='gds23' value='2' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getGds23())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
            </tr>
            <tr>
                <th>24</th>
                <td class="ta_l">사소한 일에도 속상할 때가 많습니까?</td>
                <td><label><input type='radio' class='r' name='gds24' value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getGds24())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r' name='gds24' value='2' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getGds24())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
            </tr>
            <tr>
                <th>25</th>
                <td class="ta_l">울고 싶을 때가 자주 있습니까?</td>
                <td><label><input type='radio' class='r' name='gds25' value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getGds25())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r' name='gds25' value='2' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getGds25())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
            </tr>
            <tr>
                <th>26</th>
                <td class="ta_l">집중하기가 어렵습니까?</td>
                <td><label><input type='radio' class='r' name='gds26' value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getGds26())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r' name='gds26' value='2' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getGds26())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
            </tr>
            <tr>
                <th>27</th>
                <td class="ta_l">아침에 기분 좋게 일어나십니까?</td>
                <td><label><input type='radio' class='r' name='gds27' value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getGds27())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r' name='gds27' value='2' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getGds27())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
            </tr>
            <tr>
                <th>28</th>
                <td class="ta_l">사람들과 어울리는 자리를 피하는 편이십니까?</td>
                <td><label><input type='radio' class='r' name='gds28' value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getGds28())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r' name='gds28' value='2' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getGds28())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
            </tr>
            <tr>
                <th>29</th>
                <td class="ta_l">쉽게 결정하는 편이십니까?</td>
                <td><label><input type='radio' class='r' name='gds29' value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getGds29())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r' name='gds29' value='2' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getGds29())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
            </tr>
            <tr>
                <th>30</th>
                <td class="ta_l">예전처럼 정신이 맑습니까?</td>
                <td><label><input type='radio' class='r' value='1' name='gds30' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getGds30())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r' value='2' name='gds30' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getGds30())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
            </tr>
        </tbody>
    </table>

                                                                                                                                                               
	
<!--  
	int top = 44;

	String t = "";

	for(int i=1; i<=30; i++) {
		String rboxTag = "<div class='rbox' style='left:489px;top:" + top + "px;width:117px;'>";
		if(i<10) t = "Gds0" + i;
		else t = "Gds" + i;
		if(vo == null){
			rboxTag += make_radio("");
		}else{
			Method method = vo.getClass().getMethod("get" + t);
			String getRtnStr = (String) method.invoke(vo);
			rboxTag += make_radio(getRtnStr);
			rboxTag += "</div>";
		}
		top += 22;
		out.print(rboxTag);
	}

	/* if(gdsk_list != null){
		out.print("<div class='rbox' style='left:489px;top:" + top + "px;width:117px;'>" + make_radio((vo == null ? "" : vo.getGds01())) + "</div>"); top += 22;
		out.print("<div class='rbox' style='left:489px;top:" + top + "px;width:117px;'>" + make_radio((vo == null ? "" : vo.getGds02())) + "</div>"); top += 22;
		out.print("<div class='rbox' style='left:489px;top:" + top + "px;width:117px;'>" + make_radio((vo == null ? "" : vo.getGds03())) + "</div>"); top += 22;
		out.print("<div class='rbox' style='left:489px;top:" + top + "px;width:117px;'>" + make_radio((vo == null ? "" : vo.getGds04())) + "</div>"); top += 22;
		out.print("<div class='rbox' style='left:489px;top:" + top + "px;width:117px;'>" + make_radio((vo == null ? "" : vo.getGds05())) + "</div>"); top += 22;
		out.print("<div class='rbox' style='left:489px;top:" + top + "px;width:117px;'>" + make_radio((vo == null ? "" : vo.getGds06())) + "</div>"); top += 22;
		out.print("<div class='rbox' style='left:489px;top:" + top + "px;width:117px;'>" + make_radio((vo == null ? "" : vo.getGds07())) + "</div>"); top += 22;
		out.print("<div class='rbox' style='left:489px;top:" + top + "px;width:117px;'>" + make_radio((vo == null ? "" : vo.getGds08())) + "</div>"); top += 22;
		out.print("<div class='rbox' style='left:489px;top:" + top + "px;width:117px;'>" + make_radio((vo == null ? "" : vo.getGds09())) + "</div>"); top += 22;
		out.print("<div class='rbox' style='left:489px;top:" + top + "px;width:117px;'>" + make_radio((vo == null ? "" : vo.getGds10())) + "</div>"); top += 22;
		out.print("<div class='rbox' style='left:489px;top:" + top + "px;width:117px;'>" + make_radio((vo == null ? "" : vo.getGds11())) + "</div>"); top += 22;
		out.print("<div class='rbox' style='left:489px;top:" + top + "px;width:117px;'>" + make_radio((vo == null ? "" : vo.getGds12())) + "</div>"); top += 22;
		out.print("<div class='rbox' style='left:489px;top:" + top + "px;width:117px;'>" + make_radio((vo == null ? "" : vo.getGds13())) + "</div>"); top += 22;
		out.print("<div class='rbox' style='left:489px;top:" + top + "px;width:117px;'>" + make_radio((vo == null ? "" : vo.getGds14())) + "</div>"); top += 22;
		out.print("<div class='rbox' style='left:489px;top:" + top + "px;width:117px;'>" + make_radio((vo == null ? "" : vo.getGds15())) + "</div>"); top += 22;
		out.print("<div class='rbox' style='left:489px;top:" + top + "px;width:117px;'>" + make_radio((vo == null ? "" : vo.getGds16())) + "</div>"); top += 22;
		out.print("<div class='rbox' style='left:489px;top:" + top + "px;width:117px;'>" + make_radio((vo == null ? "" : vo.getGds17())) + "</div>"); top += 22;
		out.print("<div class='rbox' style='left:489px;top:" + top + "px;width:117px;'>" + make_radio((vo == null ? "" : vo.getGds18())) + "</div>"); top += 22;
		out.print("<div class='rbox' style='left:489px;top:" + top + "px;width:117px;'>" + make_radio((vo == null ? "" : vo.getGds19())) + "</div>"); top += 22;
		out.print("<div class='rbox' style='left:489px;top:" + top + "px;width:117px;'>" + make_radio((vo == null ? "" : vo.getGds20())) + "</div>"); top += 22;
		out.print("<div class='rbox' style='left:489px;top:" + top + "px;width:117px;'>" + make_radio((vo == null ? "" : vo.getGds21())) + "</div>"); top += 22;
		out.print("<div class='rbox' style='left:489px;top:" + top + "px;width:117px;'>" + make_radio((vo == null ? "" : vo.getGds22())) + "</div>"); top += 22;
		out.print("<div class='rbox' style='left:489px;top:" + top + "px;width:117px;'>" + make_radio((vo == null ? "" : vo.getGds23())) + "</div>"); top += 22;
		out.print("<div class='rbox' style='left:489px;top:" + top + "px;width:117px;'>" + make_radio((vo == null ? "" : vo.getGds24())) + "</div>"); top += 22;
		out.print("<div class='rbox' style='left:489px;top:" + top + "px;width:117px;'>" + make_radio((vo == null ? "" : vo.getGds25())) + "</div>"); top += 22;
		out.print("<div class='rbox' style='left:489px;top:" + top + "px;width:117px;'>" + make_radio((vo == null ? "" : vo.getGds26())) + "</div>"); top += 22;
		out.print("<div class='rbox' style='left:489px;top:" + top + "px;width:117px;'>" + make_radio((vo == null ? "" : vo.getGds27())) + "</div>"); top += 22;
		out.print("<div class='rbox' style='left:489px;top:" + top + "px;width:117px;'>" + make_radio((vo == null ? "" : vo.getGds28())) + "</div>"); top += 22;
		out.print("<div class='rbox' style='left:489px;top:" + top + "px;width:117px;'>" + make_radio((vo == null ? "" : vo.getGds29())) + "</div>"); top += 22;
		out.print("<div class='rbox' style='left:489px;top:" + top + "px;width:117px;'>" + make_radio((vo == null ? "" : vo.getGds30())) + "</div>"); top += 22;
	} */
	%>
-->
</div>


<!-- 저장 버튼을 하단에 배치 -->
<div id="btnArea" class="btn_wrap btm">
	<button name="저장" id="insert" class ="csbr1010btn" style="">저장</button>
</div>


</body>
</html>