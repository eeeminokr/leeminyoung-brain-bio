<%@page import="java.sql.Date"%>
<%@page import="csbrain.common.util.StringUtil"%>
<%@page import="csbrain.common.util.DateUtil"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="csbrain.data.service.MbObjectVO"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/common.inc"%>
<%
	Map<String, String> DDI = (Map<String, String>) request.getAttribute("DDI");
	Map<String, String> EDU = (Map<String, String>) request.getAttribute("EDU");
	MbObjectVO vo = (MbObjectVO) request.getAttribute("movo");
%>


<style>



	#info_data ul{
		list-style-type: none;
		width:;
		height:24px;
	}
	#info_data li{
		height:23px;
		line-height:23px;
		float:left;
		border-top:1px solid black;
		border-left:1px solid black;
		background-color:#f5f5f7;
		padding-left:2px;
		overflow:hidden;
	}
	#info_data li.header{
		background-color:#c0c0ff;
		text-align:center;
	}
		.csbr1010btn {
		box-shadow:inset 0px 1px 0px 0px #ffffff;
		background:linear-gradient(to bottom, #f9f9f9 5%, #e9e9e9 );
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
		background:linear-gradient(to bottom, #e9e9e9 5%, #f9f9f9 );
		background-color:#e9e9e9;
	}
	.csbr1010btn:active {
		position:relative;
		top:1px;
	}
	




table.type03 {
  /*border-collapse: collapse;*/
  text-align: left;
  /*line-height: 1.5;*/
  border-top: 1px solid #ccc;
  border-left: 3px solid #369;
  font-size: 2px;
  margin : 40px 10px;
  position: relative;
  top: 10px;
}
table.type03 th {
  /*width: 50px;
  padding: 10px;
                 */    
  font-weight: bold;
  vertical-align: top;
  color: #153d73;
  border-right: 1px solid #ccc;
  border-bottom: 1px solid #ccc;
 

}
table.type03 td {

 /* vertical-align: top;*/
  border-right: 1px solid #ccc;
  border-bottom: 1px solid #ccc;

 }

	
/* .t{
	border:0 solid black;
	text-align: center;
	width: 100%;;
	height: 100%;
}

.tn{
	border:0 solid black;
	width: 160px;
	text-align: center;
} */
	
</style>
<script type="text/javascript">
$(function(){
	
	var getidx = $('#info_name').val();

	console.log(idx+"#idx");
$("#edit").click(function() {

	console.log(getidx+"getidx::::")
	if(getidx== ''){
		
			alert("검색항목을 입력해주세요");
			$('#edit').attr("disabled", true);
			return false;
		
		
	}else{
		$('#edit').attr("disabled", false);
	}

	if($('#insert').css("display")=="none"){
		setTimeout(function() {
		$('#insert').show();
		$("#sel_gender").show();
		$("#sel_yearani").show();
		$("#sel_edu").show();
		$('input:text[name="gender"]').hide();
		$('input:text[name="year_ani"]').hide();
		$('input:text[name="edu"]').hide();
		},0);
	}
$('input:text[class="t"]').attr("disabled", false);
console.log($(".t").attr("disabled"));

	// $('input:radio[name="r2"]').attr("disabled", true); //비활성화
	 	var selnew = $("#edit").val(); 
	 	console.log(selnew+"::selenew")
	checking = $('#chk').val(selnew);
});

$('#insert').click(function() {
	
	//배열선언
	var tcolNmArr = [];
	var textArr = [];
	var sdates = $("#sdate").val();
	var exam_num= $("#selectionNum").val();
	var exam_idx= $("#examIdx").val(); 
	console.log("exam_num::"+exam_num)
	console.log("exam_idx::"+exam_idx)
	console.log("설문실행일:"+sdates);
	var whichChk = $("#chk").val();
	console.log(whichChk+":::whichChk")
		var idx = $('#idx').val();
	var gender = $('#sel_gender').val();
	var edu = $('#sel_edu').val();
	var year_ani =$('#sel_yearani').val();



	var value1;
	$('input:text[class="t"]').each(function(i){	
		value1 = $(this).val();
		var  getNm = $(this).attr('name');
	
		if(value1 != '' &&  getNm !=''){
			console.log(getNm+":::::::::::getNm["+i+"]");
			console.log(value1+":[dm 텍스트]values1["+i+"]");
				 textArr.push(value1);		
				 tcolNmArr.push(getNm);	
		}
		
	});
	
	

	
	
	var num;
	var num1;
	 num = $('input:radio[class="r"]:checked').length;
		console.log(num+"저장된체크넘버");
		num1 = $('input:radio[class="r"]:not(:checked)').length;
	

	var objParams = {
		"tcolNmArr" : tcolNmArr,	
		"textArr" : textArr,
		"sdate" : sdates,  
		"idx" : idx,
		"edu" : edu,
		"gender" : gender,
		"year_ani" :year_ani,
		"whichChk" : whichChk

	};
	
	//ajax호출
	$.ajax({
		url         :   getContextPath()+"/include/data/editcsbr1010_info.do",
        dataType    :   "json",
        contentType :   "application/x-www-form-urlencoded; charset=UTF-8",
        type        :   "post",
        data        :   objParams,
        success     :   function(json){
   			if(json.model.code =="OK"){
   				alert(json.model.message);
   			//	location.reload(true);
   				$("#info_data").load(getContextPath() + "/include/data/csbr1010_info.do?object_idx="+idx)
   				return;
   			}else{
   				alert("등록을 실패하였습니다 다시시도해주세요!")
   			} 		
        },
        error       :   function(request, status, error){
            console.log("AJAX_ERROR");
        }

	});
	
});

$('input:text[class="t"]').attr("disabled", true);


$("input[name='jumin1']").keyup(
		function() {
  			var now = new Date();
  			var year = now.getFullYear();
  			console.log("연도: "+year);
			
			$(this).val( $(this).val().replace(/[^0-9]/gi,"") );

			if($(this).val().length >=6 ) 
			{ 
				
				$("input[name='jumin2']").focus(); 
			}
		}
	);

$("input[name='jumin2']").keyup(
		function() {

			$(this).val( $(this).val().replace(/[^0-9]/gi,"") );

			if($(this).val().length >=4 ) 
			{

				$("input[name='jumin3']").focus(); 
			}
		}
	);
	$("input[name='jumin3']").keyup(
		function() {

			$(this).val( $(this).val().replace(/[^0-9]/gi,"") );

			if($(this).val().length >=3 ) 
			{

				$("select[name='sel_gender']").focus(); 
			}
		}
	);





$("input[name='birth1']").keyup(
		function() {
  			var now = new Date();
  			var year = now.getFullYear();
  			console.log("연도: "+year);
			
			$(this).val( $(this).val().replace(/[^0-9]/gi,"") );

			if($(this).val().length >=4 ) 
			{ 
				$("input[name='age']").val( year - $("input[name='birth1']").val() );
				$("input[name='birth2']").focus(); 
			}
		}
	);

$("input[name='birth2']").keyup(
		function() {

			$(this).val( $(this).val().replace(/[^0-9]/gi,"") );

			if($(this).val().length >=2 ) 
			{

				$("input[name='birth3']").focus(); 
			}
		}
	);
	$("input[name='birth3']").keyup(
		function() {

			$(this).val( $(this).val().replace(/[^0-9]/gi,"") );

			if($(this).val().length >=2 ) 
			{

				$("select[name='gender']").focus(); 
			}
		}
	);

	$("input[name='tel1']").keyup(
			function() {

				$(this).val( $(this).val().replace(/[^0-9]/gi,"") );

				if($(this).val().length >=3 ) { $("input[name='tel2']").focus(); }
			}
		);
		$("input[name='tel2']").keyup(
			function() {

				$(this).val( $(this).val().replace(/[^0-9]/gi,"") );

				if($(this).val().length >=4 ) { $("input[name='tel3']").focus(); }
			}
		);
		$("input[name='tel3']").keyup(
			function() {

				$(this).val( $(this).val().replace(/[^0-9]/gi,"") );

				if($(this).val().length >=4 ) { $("input[name='mobile1']").focus(); }
			}
		);

		$("input[name='mobile1']").keyup(
			function() {

				$(this).val( $(this).val().replace(/[^0-9]/gi,"") );

				if($(this).val().length >=3 ) { $("input[name='mobile2']").focus(); }
			}
		);
		$("input[name='mobile2']").keyup(
			function() {

				$(this).val( $(this).val().replace(/[^0-9]/gi,"") );

				if($(this).val().length >=4 ) { $("input[name='mobile3']").focus(); }
			}
		);
		$("input[name='mobile3']").keyup(
			function() {

				$(this).val( $(this).val().replace(/[^0-9]/gi,"") );

				if($(this).val().length >=4 ) { $("input[name='sample4_sido']").focus(); }
			}
		);


		$("input[name='age']").keyup(
			function() {

				$(this).val( $(this).val().replace(/[^0-9]/gi,"") );

			}
		);
		 $('input:text[name="idx"]').attr("disabled", true);

});
</script>




				
		
		
<div id="layout" >

	<!-- 인적사항 -->
	<div class="table_info_data">
		<table class="table type01">
			<colgroup>
				<col width="102px" />
				<col width="*" />
			</colgroup>
			<tbody>
				<tr>
					<th scope="row">이 름</th>
					<td>
						<input type="text" name= 'name' id='info_name' class='t' value= '<%=(vo == null ? "" : vo.getName())%>' style="width:102px;" >
						<input type="text" name ='idx' class='tn' id='idx' value= '<%=(vo == null ? "" : vo.getIdx())%>' style="width:101px;" readonly="readonly">
					</td>
				</tr>
				<tr>
					<th scope="row">주민번호</th>
					<td>
						<input type="text" name='jumin1' id='info_jumin1' class='t' value ='<%=(vo == null ? "" : vo.getJumin1())%>' style="width:60px;">
						<span class="dash">-</span>
						<input type="text" name='jumin2' id='info_jumin2'  class='t' value='<%=(vo == null ? "" : vo.getJumin2())%>'  style="width:60px;">
						<input type="text" name='jumin3' id='info_jumin3'  class='t' value='<%=(vo == null ? "" : vo.getJumin3())%>' style="width:50px;">
					</td>
					</tr>
					<tr>
					<th scope="row">성 별</th>
					<td>
						<input type="text" id='info_gender' class='tn'  name='gender'  value='<%=(vo == null ? "" : vo.getGender())%>' readonly style="width:133px;">
						<select name ='sel_gender' id='sel_gender' style="display: none;">
							<option class='r' value='1'  <%if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getGender())))){%>selected <%}%>>남</option>
							<option class='r' value='2' <%if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getGender())))){%>selected <%}%>>여</option>
						</select>
					</td>
				</tr>
				<tr>
					<th scope="row">띠</th>
					<td>
						<input type="text"  name='year_ani' value='<%=(vo == null ? "" : (DDI == null ? "" : DDI.get(vo.getYearAni())))%>' readonly style="width:133px;">
						<select name ='sel_yearani' id='sel_yearani' style="display: none;" >
							<option class='r' value='1'  <%if("1".equals((vo == null ? "" : StringUtil.NVL(vo.getYearAni())))){%>selected <%}%>>쥐 띠</option>
							<option class='r' value='2'  <%if("2".equals((vo == null ? "" : StringUtil.NVL(vo.getYearAni())))){%>selected <%}%>>소 띠</option>
							<option class='r' value='3'  <%if("3".equals((vo == null ? "" : StringUtil.NVL(vo.getYearAni())))){%>selected <%}%>>호랑이 띠</option>
							<option class='r' value='4'  <%if("4".equals((vo == null ? "" : StringUtil.NVL(vo.getYearAni())))){%>selected <%}%>>토끼 띠 </option>
							<option class='r' value='5'  <%if("5".equals((vo == null ? "" : StringUtil.NVL(vo.getYearAni())))){%>selected <%}%>>용 띠</option>
							<option class='r' value='6'  <%if("6".equals((vo == null ? "" : StringUtil.NVL(vo.getYearAni())))){%>selected <%}%>>뱀 띠</option>
							<option class='r' value='7'  <%if("7".equals((vo == null ? "" : StringUtil.NVL(vo.getYearAni())))){%>selected <%}%>>말 띠</option>
							<option class='r' value='8'  <%if("8".equals((vo == null ? "" : StringUtil.NVL(vo.getYearAni())))){%>selected <%}%>>양 띠</option>
							<option class='r' value='9'  <%if("9".equals((vo == null ? "" : StringUtil.NVL(vo.getYearAni())))){%>selected <%}%>>원숭이 띠</option>
							<option class='r' value='10' <%if("10".equals((vo == null ? "" : StringUtil.NVL(vo.getYearAni())))){%>selected <%}%>>닭 띠</option>
							<option class='r' value='12' <%if("11".equals((vo == null ? "" : StringUtil.NVL(vo.getYearAni())))){%>selected <%}%>>개 띠</option>
							<option class='r' value='13' <%if("12".equals((vo == null ? "" : StringUtil.NVL(vo.getYearAni())))){%>selected <%}%>>돼지 띠</option>
						</select> 
					</td>
				</tr>
				<tr>
					<th scope="row">실생년월일</th>
					<td>
						<input type="text" name="birth1" class='t' value='<%=(vo == null ? "" : vo.getBirth1())%>' style="width:65px;">
						<input type="text" name="birth2" class='t' value='<%=(vo == null ? "" : vo.getBirth2())%>' style="width:65px;">
						<input type="text" name="birth3" class='t' value='<%=(vo == null ? "" : vo.getBirth3())%>' style="width:65px;">
					</td>
				</tr>            
				<tr>
				<th scope="row">나 이</th>
					<td><input type="text" class='t' name="age" value='<%=(vo == null ? "" : StringUtil.NVL(vo.getAge()))%>' style="width:133px;"></td>
				</tr>        
				<tr>
					<th scope="row">교육기간</th>
					<td>
						<input type="text" name='edu'  value='<%=(vo == null ? "" : (EDU == null ? "" : EDU.get(vo.getEdu())))%>' style="width:133px;">
						<select name ='sel_edu' id='sel_edu' style="display: none;" >
							<option class='r' value='99'  <%if("99".equals((vo == null ? "" : StringUtil.NVL(vo.getEdu())))){%>selected <%}%>>무학</option>
							<option class='r' value='8'   <%if("8".equals((vo == null ? "" : StringUtil.NVL(vo.getEdu())))){%>selected <%}%>>초등학교졸업</option>
							<option class='r' value='11'  <%if("11".equals((vo == null ? "" : StringUtil.NVL(vo.getEdu())))){%>selected <%}%>>중학교졸업</option>
							<option class='r' value='14'  <%if("14".equals((vo == null ? "" : StringUtil.NVL(vo.getEdu())))){%>selected <%}%>>고등학교졸업</option>       	
							<option class='r' value='18'  <%if("18".equals((vo == null ? "" : StringUtil.NVL(vo.getEdu())))){%>selected <%}%>>대학교졸업</option>
							<option class='r' value='20'  <%if("20".equals((vo == null ? "" : StringUtil.NVL(vo.getEdu())))){%>selected <%}%>>대학원졸업이상</option>
						</select> 
					</td>
				</tr> 
				<tr>
					<th scope="row">집전화</th>
					<td>
						<input type="text" id='info_telno1' name='tel1' class='t' value='<%=(vo == null ? "" : vo.getTel1())%>' style="width: 60px;">
						<span class="dash">-</span>
						<input type="text" id='info_telno2'  name='tel2' class='t' value='<%=(vo == null ? "" : vo.getTel2())%>' style="width: 60px;">
						<span class="dash">-</span>
						<input type="text" id='info_telno3'  name='tel3' class='t' value='<%=(vo == null ? "" : vo.getTel3())%>' style="width: 60px;">
					</td>
				</tr>                       
					<tr>
					<th scope="row">휴대전화</th>
					<td>
						<input type="text" id='info_mobile1'  name='mobile1' class='t' value='<%=(vo == null ? "" : vo.getMobile1())%>' style="width: 60px;">
						<span class="dash">-</span>
						<input type="text" id='info_mobile2'  name='mobile2' class='t' value='<%=(vo == null ? "" : vo.getMobile2())%>' style="width: 60px;">
						<span class="dash">-</span>
						<input type="text" id='info_mobile3'  name='mobile3' class='t' value='<%=(vo == null ? "" : vo.getMobile3())%>' style="width: 60px;">
					</td>
				</tr>          
				<tr>
					<th rowspan="3" scope="row">주소</th>
					<td>
						<input type="text" id ="info_address1" name='address1' class='t' value='<%=(vo == null ? "" : vo.getAddress1())%>'style="width:102px;">
						<input type="text" id ="info_address2" name='address2' class='t' value='<%=(vo == null ? "" : vo.getAddress2())%>'style="width:101px;">
					</td>
				</tr>
				<tr>
					<td><input type="text" id='info_address3' name='address3'  class='t' value='<%=(vo == null ? "" : vo.getAddress3())%>' style="width:100%;"></td>
				</tr>
				<tr>
					<td><input type="text" id='info_mobile' name='address4'  class='t' value='<%=(vo == null ? "" : vo.getAddress4())%>' style="width:100%;"></td>
				</tr>
			</tbody>
		</table>
	</div>
	<!-- //인적사항 -->


</div>


<div class="btn_wrap btm">
	<button name="저장" id="insert" class ="csbr1010btn" style="display:none;">저장</button>
	<button name="수정" id="edit" class ="csbr1010btn"  value="edit">수정</button>
	<input type ="hidden" name="chk" id ="chk" class ="chk" value="" style="display:none;"/>
</div>
		
		
		
		
		
		
		
		
		
		