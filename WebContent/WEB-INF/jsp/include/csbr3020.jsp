<%@page import="csbrain.common.util.DateUtil"%>
<%@page import="csbrain.common.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String edate = request.getParameter("edate");
	String sdate = request.getParameter("sdate");
	if ( StringUtil.isEmptyString(edate) ) edate = DateUtil.getSimpleDate("yyyy-MM-dd");
	if ( StringUtil.isEmptyString(sdate) ) sdate = DateUtil.addMonthDate("yyyy-MM-dd", edate, -2);
%>
<script>

	$(function(){
		$("#btn_upload_sample").click(function(){ 
			location.href = 'sample/suvr_data_excel_sample.xls';
		});
	
		$("#btn_search").click(function(){
			load_search_1(1);		
				
		});

		$(document).ready(function() {
			$("#btn_search").click;
			load_search_1(1);
		});
	 
		$("#btn_delete").click(function(){
			alert('delete');
		});
	
		$("#btn_excel").click(function(){
			if($("#search_list_1").is(":visible")){
				var sdate = $("#sdate").val();
				var edate = $("#edate").val();
				location.href=(getContextPath() + '/include/csbr3010_1_print.do?sdate=' + sdate + '&edate=' + edate + '&xls=1');
			} else {
				var sdate = $("#sdate").val();
				var edate = $("#edate").val();
				location.href=(getContextPath() + '/include/csbr3010_2_print.do?sdate=' + sdate + '&edate=' + edate + '&xls=1');
			}
		});
	
		$("#btn_close").click(function(){
			sub_close();
		});
	
		$("#btn_upload_suvr").click(function(){
			window.open(getContextPath() + '/include/csbr3020_upload_suvr.do','snsb_upload_suvr','width=600,height=200,scrollbars=no');
		});
		
		$("#btn_new").click(function(){
			window.open(getContextPath() + '/include/csbr3020_target.do','pet_target','width=800,height=600,scrollbars=yes');
		});
	
		$("#intitle1_off").click(function(){
			$("#intitle1_off").hide();
			$("#intitle1_on").show();
			$("#intitle2_off").show();
			$("#intitle2_on").hide();
	
			$("#search_list_1").show();
			$("#search_list_2").hide();
		});
		$("#intitle2_off").click(function(){
			$("#intitle1_off").show();
			$("#intitle1_on").hide();
			$("#intitle2_off").hide();
			$("#intitle2_on").show();
	
			$("#search_list_1").hide();
			$("#search_list_2").show();
		});
	
	});

	function load_search_1(p) {
		$("#search_list_1").empty().html("<img src='images/loading.gif' style='position:absolute;left:45%;top:40%;'>");
		var sdate = $("#sdate").val();
		console.log(sdate + ":첫번째날짜")
		var edate = $("#edate").val();
		console.log(edate + ":첫번째날짜")
		
		
		
		
		
		$.post(getContextPath()+ "/include/data/csbr3020_search.do", {sdate:sdate, edate:edate, page:p}).done(function(data){
			$("#search_list_1").html(data).mygrid({
				cursorpointer: true,
				auto_first_select:true,
				hover:true,
				gopage:function(np){
					load_search_1(np);
						
				}
			});
			
			$("#search_list_1").find("li").css({'padding-left':'3px', 'padding-right':'3px'});
		});

	}
</script>
<style>
	.intitle {
		position:absolute;
		left:10px;
		top:5px;
		padding-left:10px;
		padding-right:10px;
		background-color:#FFFFFF;
		border-left:1px solid #a5a5a5;
		border-top:1px solid #a5a5a5;
		border-bottom:1px solid #FFFFFF;
		border-right:1px solid #a5a5a5;
		height:22px;
		line-height:22px;
	}
</style>



<div style='margin:3px;padding:0px;width:calc(100% - 8px);height:30px;background-color:#ebecef;border-radius: 3px;border:1px solid #c2c4cb;'>
	<div style='width:80px;height:23px;line-height:23px;margin:3px;border:1px solid black;background-color:#c0c0ff;text-align:center;float:left;font-weight:bold;'>일&nbsp;&nbsp;&nbsp;자</div>
	<div style='height:23px;margin:3px;float:left;border:0px solid black;padding:0px;'>
		<input type='text' class='calendar' id='sdate' value='<%=sdate%>' >
		~
		<input type='text' class='calendar' id='edate' value='<%=edate%>' >
	</div>
	<div style='height:23px;line-height:23px;margin:3px;margin-left:5px;float:left;border:0px solid black;padding:0px;cursor:pointer;'>
		<img id='btn_search' src='images/button/btn_search.png' align='absmiddle'>
		<!--<img id='btn_delete' src='images/button/btn_delete.png' align='absmiddle'>-->
		<img id='btn_excel' src='images/button/btn_excel.png' align='absmiddle'>
		<img id='btn_close' src='images/button/btn_close.png' align='absmiddle'>
	</div>

	<!--<div style='width:80px;height:23px;line-height:23px;margin:3px;margin-left:10px;border:1px solid black;background-color:#c0c0ff;text-align:center;float:left;font-weight:bold;'>SNSB고유번호</div>
	<div style='height:23px;margin:3px;float:left;border:0px solid black;padding:0px;'>
		<input type='text' id='upload_snum' class='onlydigit' value='<%--=unum_max--%>' style='width:40px;'>번~
		<input type='text' id='upload_enum' class='onlydigit' value='<%--=unum_enum--%>' style='width:40px;'>번 업로드
	</div>-->
	<div style='height:23px;line-height:23px;margin:3px;margin-left:100px;float:left;border:0px solid black;padding:0px;cursor:pointer;'>
		<img id='btn_new' src='images/button/btn_new.png' align='absmiddle' style='padding-top: 1.5px;'>
	</div>
	<div style='height:23px;line-height:23px;margin:3px;margin: 3px;float:left;border:0px solid black;padding:0px;cursor:pointer;'>
		<img id='btn_upload_sample' src='images/button/btn_xlsuploadsample.png' align='absmiddle' style='padding-top: 1.5px;'>
	</div>
	<div style='height:23px;line-height:23px;margin:3px;float:left;border:0px solid black;padding:0px;cursor:pointer;'>
		<img id='btn_upload_suvr' src='images/button/btn_xlsupload.png' align='absmiddle'> (SUVR SCORE UPDATE)
	</div>
</div>
<%-- 
<div id='bodytitle' style='z-index:10;'>
	<div id='intitle1_on' class='intitle' style='left:10px;'><b>pet</b></div>
	<div id='intitle1_off' class='intitle' style='left:10px;background-color:#EFEFEF;cursor:pointer;border-bottom:1px solid #849dbd;display:none;'><b>1_Excel1_snsb2data</b></div>

</div>
--%>
<div id='search_list_1' style='position:absolute;left:3px;top:38px;width:calc(100% - 8px);height:calc(100% - 72px);border:1px solid #849dbd;overflow-y:hidden;overflow-x:hidden;'></div>


