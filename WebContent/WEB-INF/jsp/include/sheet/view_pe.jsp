<%@page import="java.sql.Date"%>
<%@page import="csbrain.common.util.StringUtil"%>
<%@page import="csbrain.common.util.DateUtil"%>
<%@page import="csbrain.common.service.MbPeVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/common.inc"%>
<jsp:include page='<%=incInit%>' flush="false"></jsp:include>
<%
	List<MbPeVO> pe_list = (List<MbPeVO>) request.getAttribute("pe_list");


	MbPeVO vo = null;


	if( pe_list != null && pe_list.size() > 0 ){
		vo = pe_list.get(0);
	}

%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/target/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/target/lib_jquery.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
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
	
	.tn {
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
	.tn {
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
	height: 3000px;
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
.table.type02 tbody th.ta_l {padding-left:40px;line-height:18px;}
.table.type02 tbody .table th, .table.type02 tbody .table td {border-bottom:0;border-left:0;background-color:#fff;}
.table.type02 tbody .table tr:first-child td, .table.type02 tbody .table thead tr:first-child th {border-top:0;}
.table.type02 tbody .table tbody tr:last-child th, .table.type02 tbody .table tbody tr:last-child td {border-bottom:0;}
.table.type02 tbody .table tbody tr th {border-left:0;}
.table.type02 tbody .table thead tr th:last-child, .table.type02 tbody .table tbody tr td:last-child {border-right:0;}
.ans label {display:inline-block;width:75px;vertical-align:top;}
.ans label:last-child {width:auto;}	
	
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
			var addN = 0;
			
			return false;
			
		}else if(this.value.length < minLimit){
			$(this).focus();
			return false;
		}
	
		});
	
	
	

	
	
	var addN = 0;
	$("input:text[name='t6']").change(function() {
	

			
		var pe_bpsys = $(this).val();	
			setTimeout(function() {
			
		//	var pe_bpsys = $('input:text[name="t6"]').val();
		
		var pe_bpsys1 = $('input:text[name="t7"]').val();
				
			pe_bpsys = 	Number(pe_bpsys);
			pe_bpsys1 = Number(pe_bpsys1);
			
			addN =  (pe_bpsys+pe_bpsys1)/2;
			$('input:text[name="peBpsysavg"]').val(addN);
			},300);
			
	});
	
	
	$("input:text[name='t7']").change(function() {
		

		
		var pe_bpsys1 = $(this).val();
		setTimeout(function() {
		
	//	var pe_bpsys = $('input:text[name="t6"]').val();
			
		var pe_bpsys = $('input:text[name="t6"]').val();
			
		pe_bpsys = 	Number(pe_bpsys);
		pe_bpsys1 = Number(pe_bpsys1);
		
		addN =  (pe_bpsys+pe_bpsys1)/2;
		$('input:text[name="peBpsysavg"]').val(addN);
		},300);
		
	});
	
	
	$("input:text[name='t8']").change(function() {
		
		var pe_bpdia = $(this).val()	
		setTimeout(function() {
	
	;
	var pe_bpdia1 = $('input:text[name="t9"]').val();
			
	pe_bpdia = Number(pe_bpdia)
  	pe_bpdia1 = Number(pe_bpdia1)
		
		addN =  (pe_bpdia+pe_bpdia1)/2;
		$('input:text[name="peBpdiaavg"]').val(addN);
		},300);
		
	});
	
	$("input:text[name='t9']").change(function() {
		
		var pe_bpdia1 = $(this).val();		
		setTimeout(function() {
	

	var pe_bpdia = $('input:text[name="t8"]').val();
			
	pe_bpdia = Number(pe_bpdia)
  	pe_bpdia1 = Number(pe_bpdia1)
		
		addN =  (pe_bpdia+pe_bpdia1)/2;
		$('input:text[name="peBpdiaavg"]').val(addN);
		},300);
		
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
	
	
	$('input:radio[id="r2"]').click(function() {
		
		var r1val = $(this).val();
			 $('input:radio[name="r2"]:not(:checked)').attr("disabled", false);	
		
			
				setTimeout(function() {
				 $('input:text[name="pe_height"]').focus();	
					},100);
			
		});		
	
	$('input:radio[name="r1"]').change(function() {
		var r1val = $(this).val();
		 $('input:radio[name="r1"]:not(:checked)').attr("disabled", false);	
		$('input:text[name="tac3"]').val('');

	});
	
	
	
	
	

	
	
	
	
	$('#insert').click(function() {
		
		//배열선언
		var rcolNmArr = [];
		var radioArr = [];
		var textArr = [];
		
		var sdates = $("#sdate").val();
		var pe_bpsysavg = $('input[name="peBpsysavg"]').val();
		var pe_bpdiaavg = $('input[name="peBpdiaavg"]').val();
		var exam_num= $("#selectionNum").val();
		var exam_idx= $("#examIdx").val(); 
		var exam_id= $("#exid").val();
		console.log("exam_num::"+exam_num)
		console.log("exam_idx::"+exam_idx)
		console.log("설문실행일:"+sdates);
		console.log("실제생년월일:"+sdates)
		var whichChk = $("#chk").val();
		console.log(whichChk+":::whichChk")
		var cdbs = $("#cdbs").val();
		
		if(sdates == ''){
			alert("설문실행일을 체크해주세요!")
			return false;
		}
		


		var value;
		var value1;
		var value2;
		$('input:radio[class="r"]:checked').each(function(i){//체크된 리스트 저장
			
			value = $(this).val();	
			var getNm = $(this).attr('name');	
			var Fval =  $('input:radio[class="r"]:checked').length;
			console.log(Fval+"체크한카운트")
			if(value1 != '' &&  getNm !=''){
				console.log(getNm+":::::::::::getNm["+i+"]")
				console.log(value+":[dm 체크밸류]values1["+i+"]");
				 radioArr.push(value);		
				 rcolNmArr.push(getNm);
				 
			}	 
        });

		
		var value1;
		$('input:text[class="t"]').each(function(i) {
			
			value1 =$(this).val();
			
		if($('input:text[name="t'+i+'"]').val() == ''){
				alert("체크되지 않는 입력값이 있습니다!")
			 return false;
			}else{
				textArr.push(value1);
			}
			
			console.log(value1+":[음주 텍스트]values1["+i+"]");
			//textArr.push(value1);
		});
		
		
			

		
		
		
		
		var num;
		var num1;
		 num = $('input:radio[class="r"]:checked').length;
 		console.log(num+"저장된체크넘버");
 		num1 = $('input:radio[class="r"]:not(:checked)').length;
		

		var objParams = {	
				"rcolNmArr" : rcolNmArr,
				"radioArr" : radioArr,
				"textArr" : textArr,
				"sdate" : sdates,  
				"pe_bpsysavg" : pe_bpsysavg,
				"pe_bpdiaavg" : pe_bpdiaavg,
				"idx" : idx,
				"whichChk" : whichChk,
				"exam_id" : exam_id,
				"exam_num" : exam_num,
				"exam_idx" : exam_idx,
				"cdbs" : cdbs
		};
		
		//ajax호출
		$.ajax({
			url         :   getContextPath()+"/include/sheet/view_savepe.do",
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
            			  //	$("#tabbox").load(getContextPath() + "/include/sheet/view_family.do? object_idx="+obj+"&exam_num="+examN);
            			   flag = true;
            			   parent.load_sunbyul(flag);
       				}else if(json.model.result=="edit"){
       					alert(json.model.message);
       					flag = true;
     			        parent.load_sunbyul(flag);
       			  //	$("#tabbox").load(getContextPath() + "/include/sheet/view_family.do? object_idx="+obj+"&exam_num="+examN);
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
	 


	 $('input:text[class="t"]').attr("disabled",true);		
	 
	 $('input:text[name="sdate"]').attr("disabled", true);
	
	

 
	 
	 
			
	
});
</script>


<body>
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
	<input type ="hidden" name="cdbs" id ="cdbs" class ="cdbs" value="0021" style="display:none;"/>
	<input type ="hidden" name="chk" id ="chk" class ="chk" value="" style="display:none;"/>
</div> 
<div id ="tabbox" style="height:auto;">

	<table class="table type02">
        <colgroup>
            <col width="40px" />
            <col width="*" />
        </colgroup>
        <thead>
            <tr>
                <th class="bg1 tbl_tit" colspan="2">신체계측 및 활력징후</th>
            </tr>
            <tr>
                <th class="survey_date" colspan="2">
                    <div class="sv_text">설문실행일</div>
                    <div class="sv_date">
                        <input type='text' class='calendar' id='sdate' name='sdate' placeholder='yyyy/mm/dd'  style='width:120px;' 
		<%if(vo == null) {%>value=""<%}
			else if(StringUtil.NVL(vo.getTestDay()) == "" ){%>value=""<%}
			else{%>value="<%=StringUtil.NVL(DateUtil.getSimpleDate("yyyyMMdd",vo.getTestDay())) %>"<%}%>>
                    </div>
                </th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <th colspan="2" class="ta_l">신체계측</th>
            </tr>
            <tr>
                <th>1.</th>
                <td class="ta_l">
                    <p>수집방법</p>

                    <div class="ans first">
                        <label><input type='radio' class='r' name="peMethod" value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getPeMethod())))){%>checked <%}%>><span>측정</span></label>
                        <label><input type='radio' class='r' name="peMethod" value='2' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getPeMethod())))){%>checked <%}%>><span>환자 진술</span></label>
                    </div>
                </td>
            </tr>
            <tr>
                <th>2.</th>
                <td class="ta_l">
                    <p>신장계 미접촉</p>

                    <div class="ans first">
                        <label><input type='radio' class='r' id ="r2" name="peHeightcnt01" value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getPeHeightcnt01())))){%>checked <%}%>><span>뒷머리</span></label>
                        <label><input type='radio' class='r' id ="r2" name="peHeightcnt02" value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getPeHeightcnt02())))){%>checked <%}%>><span>어깨</span></label>
                        <label><input type='radio' class='r' id ="r2" name="peHeightcnt03" value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getPeHeightcnt03())))){%>checked <%}%>><span>엉덩이</span></label>
                        <label><input type='radio' class='r' id ="r2" name="peHeightcnt04" value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getPeHeightcnt04())))){%>checked <%}%>><span>발뒤꿈치</span></label>
                    </div>
                </td>
            </tr>
            <tr>
                <th>3.</th>
                <td class="ta_l">
                    <p>신장(선 키)</p>

                    <div class="ans first">
                        <input type='text' class ='t' minlength="1" maxlength="3" id= 'num' name="t0" value='<%=(vo == null ? "" : StringUtil.NVL(vo.getPeHeight()))%>'> cm
                    </div>
                </td>
            </tr>
            <tr>
                <th>4.</th>
                <td class="ta_l">
                    <p>신장(누운 키)</p>

                    <div class="ans first">
                        <input type='text' class ='t' minlength="1" maxlength="3" id= 'num' name="t1" value='<%=(vo == null ? "" : StringUtil.NVL(vo.getPeDwheight()))%>'> cm
                    </div>
                </td>
            </tr>
            <tr>
                <th>5.</th>
                <td class="ta_l">
                    <p>체중</p>

                    <div class="ans first">
                        <input type='text' class ='t' minlength="1" maxlength="3" id= 'num' name="t2" value='<%=(vo == null ? "" : StringUtil.NVL(vo.getPeWeight()))%>'>  kg
                    </div>
                </td>
            </tr>
            <tr>
                <th>6.</th>
                <td class="ta_l">
                    <p>BMI</p>

                    <div class="ans first">
                        <input type='text' class ='t' minlength="1" maxlength="3" id= 'num' name="t3" value='<%=(vo == null ? "" : StringUtil.NVL(vo.getPeBmi()))%>'>
                    </div>
                </td>
            </tr>
            <tr>
                <th>7.</th>
                <td class="ta_l">
                    <p>허리둘레</p>

                    <div class="ans first">
                        <input type='text' class ='t' minlength="1" maxlength="3" id= 'num' name="t4" value='<%=(vo == null ? "" : StringUtil.NVL(vo.getPeWstcirin()))%>'> cm
                    </div>
                </td>
            </tr>
            <tr>
                <th>8.</th>
                <td class="ta_l">
                    <p>엉덩이 둘레</p>

                    <div class="ans first">
                        <input type='text' class ='t' minlength="1" maxlength="3" id= 'num' name="t5" value='<%=(vo == null ? "" : StringUtil.NVL(vo.getPeButtomcirin()))%>'> cm
                    </div>
                </td>
            </tr>
            <tr>
                <th colspan="2" class="ta_l">활력징후</th>
            </tr>
            <tr>
                <th>1.</th>
                <td class="ta_l">
                    <p>측정 장비</p>

                    <div class="ans first">
                        <label><input type='radio' class='r' name="peVsmethod" value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getPeVsmethod())))){%>checked <%}%>><span>커프식</span></label>
                        <label><input type='radio' class='r' name="peVsmethod" value='2' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getPeVsmethod())))){%>checked <%}%>><span>전자식</span></label>
                    </div>
                </td>
            </tr>
            <tr>
                <th>2.</th>
                <td class="ta_l">
                    <p>측정 팔</p>

                    <div class="ans first">
                        <label><input type='radio' class='r' name="peBparm" value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getPeBparm())))){%>checked <%}%>><span>오른팔</span></label>
                        <label><input type='radio' class='r' name="peBparm" value='2' <% if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getPeBparm())))){%>checked <%}%>><span>왼팔</span></label>
                    </div>
                </td>
            </tr>
            <tr>
                <th colspan="2" class="ta_l">측정조건</th>
            </tr>
            <tr>
                <th>1.</th>
                <td class="ta_l">
                    <p>30분 이내 흡연을 하셨거나 커피 섭취하셨습니까?</p>

                    <div class="ans first">
                        <label><input type='radio' class='r' name="peSmoke" value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getPeSmoke())))){%>checked <%}%>><span>흡연</span></label>
                        <label><input type='radio' class='r' name="peCoffee" value='1' <% if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getPeCoffee())))){%>checked <%}%>><span>커피섭취</span></label>
                    </div>
                </td>
            </tr>
            <tr>
                <th colspan="2" class="ta_l">수축기 혈압 / 이완기 혈압(mmHg)</th>
            </tr>
            <tr>
                <td colspan="2" style="padding:0;border-left:1px solid #c4d9f6;">
                    <table class="table">
                        <colgroup>
                            <col width="25%" />
                            <col width="25%" />
                            <col width="25%" />
                            <col width="25%" />
                        </colgroup>
                        <thead>
                            <tr>
                                <th></th>
                                <th>1차</th>
                                <th>2차</th>
                                <th>평균</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <th>수축기</th>
                                <td><input type='text' class='t' minlength="1" maxlength="3" id='num' name='t6' value='<%=(vo == null ? "" : StringUtil.NVL(vo.getPeBpsys()))%>' style="width:100px;"></td>
                                <td><input type='text' class='t' minlength="1" maxlength="3" id='num' name='t7' value='<%=(vo == null ? "" : StringUtil.NVL(vo.getPeBpsys1()))%>' style="width:100px;"></td>
                                <td><input type='text' class='tn'  id='num' name='peBpsysavg' value='<%=(vo == null ? "" : StringUtil.NVL(vo.getPeBpsysavg()))%>' style="width:100px;" readonly></td>
                            </tr>
                            <tr>
                                <th class="border_dash">이완기</th>
                                <td class="border_dash"><input type='text' class='t' minlength="1" maxlength="3" id='num' name='t8' value='<%=(vo == null ? "" : StringUtil.NVL(vo.getPeBpdia()))%>' style="width:100px;"></td>
                                <td class="border_dash"><input type='text' class='t'minlength="1" maxlength="3" id='num' name='t9' value='<%=(vo == null ? "" : StringUtil.NVL(vo.getPeBpdia1()))%>' style="width:100px;"></td>
                                <td class="border_dash"><input type='text' class='tn'  id='num' name='peBpdiaavg' value='<%=(vo == null ? "" : StringUtil.NVL(vo.getPeBpdiaavg()))%>' style="width:100px;" readonly></td>
                            </tr>
                        </tbody>
                    </table>
                </td>
            </tr>
            <tr>
                <th colspan="2" class="ta_l">맥박</th>
            </tr>
            <tr>
                <td colspan="2" class="ta_l" style="padding-left:40px;border-left:1px solid #c4d9f6;">
                    <input type='text' class ='t' id= 'num' name="t10"  value='<%=(vo == null ? "" : StringUtil.NVL(vo.getPePulse()))%>' style="width:100px;"> 회 / 30초
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