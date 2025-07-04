<%@page import="java.sql.Date"%>
<%@page import="csbrain.common.util.StringUtil"%>
<%@page import="csbrain.common.util.DateUtil"%>
<%@page import="csbrain.common.service.MbSmcqVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/common.inc"%>
<jsp:include page='<%=incInit%>' flush="false"></jsp:include>
<%
	List<MbSmcqVO> smcq_list = (List<MbSmcqVO>) request.getAttribute("smcq_list");
	MbSmcqVO vo = null;

	if( smcq_list != null && smcq_list.size() > 0 ){
		vo = smcq_list.get(0);
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
		position:relative;
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
	height: 700px;
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
			
			if(value == '1'){
				tscore += 1;
		
			}else if(value =='2'){
				
					 tscore += 0;
			}
			
			console.log(value+":[KDSQ 텍스트]values1["+i+"]");
			var Fval =  $('input:radio[class="r"]:checked').length;
			console.log(Fval+"체크한카운트")
			if(Fval < 15){
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
				"exam_num" : exam_num,
	  			"exam_id" : exam_id,
				"exam_idx" : exam_idx,
				"cdbs" : cdbs,
				"tscore" : tscore
	
		};
		
		//ajax호출
		$.ajax({
			url         :   getContextPath()+"/include/sheet/view_savesmcq.do",
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

		if ( "1".equals(v) ) checked1 = "checked";//아니오
		if ( "2".equals(v) ) checked2 = "checked";//예

		String radioTag = "<input type='radio' class='r' " + checked2 + " style='left:25px;top:5px;'>"
		+ "<input type='radio' class='r'  " + checked1 + " style='left:84px;top:5px;'>";
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
	<input type ="hidden" name="exid" id ="exid" class ="exid" value="${exam_id}" style="display:none;"/>    
	<input type ="hidden" name="exnum" id ="exnum" class ="exnum" value="${exam_num}" style="display:none;"/>
	<input type ="hidden" name="cdbs" id ="cdbs" class ="cdbs" value="0013" style="display:none;"/>
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
                <th class="bg1 tbl_tit" colspan="4">SMCQ</th>
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
                <th class="th_info" colspan="4">
                    SMCQ 아래 질문들은 어르신의 평소 기억력 저하 여부를 검사하기 위한 질문들입니다.<br />
                    왼쪽 질물을 잘 읽고 오른쪽의 '아니오'와 '예' 중 가장 적절한 답을 골라 V표시를 하십시오.
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
                <td class="ta_l">기억력에 문제가 있습니까?</td>
                <td><label><input type='radio' class='r' name='r1' value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getSmcq01())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r' name='r1' value='2' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getSmcq01())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
            </tr>
            <tr>
                <th>2</th>
                <td class="ta_l">기억력이 3년 전에 비해 저하되었습니까?</td>
                <td><label><input type='radio' class='r' name='r2' value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getSmcq02())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r' name='r2' value='2' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getSmcq02())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
            </tr>
            <tr>
                <th>3</th>
                <td class="ta_l">기억력이 10년 전보다 나빠졌다고 생각하십니까?</td>
                <td><label><input type='radio' class='r' name='r3' value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getSmcqN03())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r' name='r3' value='2' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getSmcqN03())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
            </tr>
            <tr>
                <th>4</th>
                <td class="ta_l">기억력이 동년의 다른 사람들에 비해 나쁘다고 생각합니까?</td>
                <td><label><input type='radio' class='r' name='r4' value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getSmcq03())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r' name='r4' value='2' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getSmcq03())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
            </tr>
            <tr>
                <th>5</th>
                <td class="ta_l">기억력 저하로 일상생활에 불편을 느끼십니까?</td>
                <td><label><input type='radio' class='r' name='r5' value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getSmcq04())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r' name='r5' value='2' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getSmcq04())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
            </tr>
            <tr>
                <th>6</th>
                <td class="ta_l">최근에 일어난 일을 기억하는 것이 어렵습니까?</td>
                <td><label><input type='radio' class='r' name='r6' value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getSmcq05())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r' name='r6' value='2' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getSmcq05())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
            </tr>
            <tr>
                <th>7</th>
                <td class="ta_l">며칠 전에 나눈 대화 내용을 기억하는 것이 어렵습니까?</td>
                <td><label><input type='radio' class='r' name='r7' value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getSmcq06())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r' name='r7' value='2' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getSmcq06())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
            </tr>
            <tr>
                <th>8</th>
                <td class="ta_l">며칠 전에 한 약속을 기억하기 어렵습니까?</td>
                <td><label><input type='radio' class='r' name='r8' value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getSmcq07())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r' name='r8' value='2' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getSmcq07())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
            </tr>
            <tr>
                <th>9</th>
                <td class="ta_l">친한 사람의 이름을 기억하기 어렵습니까?</td>
                <td><label><input type='radio' class='r' name='r9' value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getSmcq08())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r' name='r9' value='2' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getSmcq08())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
            </tr>
            <tr>
                <th>10</th>
                <td class="ta_l">물건 둔 곳을 기억하기 어렵습니까?</td>
                <td><label><input type='radio' class='r' name='r10'  value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getSmcq09())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r' name='r10'  value='2' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getSmcq09())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
            </tr>
            <tr>
                <th>11</th>
                <td class="ta_l">이전에 비해 물건을 자주 잃어버립니까?</td>
                <td><label><input type='radio' class='r' name='r11' value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getSmcq10())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r' name='r11' value='2' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getSmcq10())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
            </tr>
            <tr>
                <th>12</th>
                <td class="ta_l">집 근처에서 길을 잃은 적이 있습니까?</td>
                <td><label><input type='radio' class='r' name='r12' value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getSmcq11())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r' name='r12' value='2' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getSmcq11())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
            </tr>
            <tr>
                <th>13</th>
                <td class="ta_l">가게에서 사려고 하는 두세 가지 물건 이름을 기억하기 어렵습니까?</td>
                <td><label><input type='radio' class='r' name='r13' value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getSmcq12())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r' name='r13' value='2' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getSmcq12())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
            </tr>
            <tr>
                <th>14</th>
                <td class="ta_l">가스나 전기 불 끄는 것을 기억하기 어렵습니까?</td>
                <td><label><input type='radio' class='r' name='r14' value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getSmcq13())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r' name='r14' value='2' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getSmcq13())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
            </tr>
            <tr>
                <th>15</th>
                <td class="ta_l">자주 사용하는 전화번호(자신 혹은 자녀의 집)을 기억하기 어렵습니까?</td>
                <td><label><input type='radio' class='r' name='r15' value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getSmcq14())))){%>checked <%}%>><span><em class="blind">예</em></span></label></td>
                <td><label><input type='radio' class='r' name='r15' value='2' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getSmcq14())))){%>checked <%}%>><span><em class="blind">아니오</em></span></label></td>
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