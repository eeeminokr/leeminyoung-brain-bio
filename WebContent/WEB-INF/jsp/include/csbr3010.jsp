<%@page import="csbrain.common.util.DateUtil"%>
<%@page import="csbrain.common.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String edate = request.getParameter("edate");
	String sdate = request.getParameter("sdate");
	if ( StringUtil.isEmptyString(edate) ) edate = DateUtil.getSimpleDate("yyyy-MM-dd");
	if ( StringUtil.isEmptyString(sdate) ) sdate = DateUtil.addMonthDate("yyyy-MM-dd", edate, -2);
%>
<!--
	//unum 최고값 추출
	$query = "select max(Unum) from mb_snsb2data_excel1";
	$trow = db_fetch_row($query,$connect);
	$unum_max = floor($trow[0]/10)*10+10;
	$unum_enum = $unum_max + 1000;
 -->
<script>

	$(function(){

		$("#btn_upload_sample").click(function(){
			location.href = 'sample/snsb2data_excel_sample.xls';
		});

		$("#btn_search").click(function(){
			if($("#search_list_1").is(":visible")){
				load_search_1(1);
			} else if($("#search_list_2").is(":visible")) {
				load_search_2(1);
			} else {
				load_search_3(1);			
			}
		});

		$("#btn_delete").click(function(){
			alert('delete');
		});

		$("#btn_excel").click(function(){
			if($("#search_list_1").is(":visible")){
				var sdate = $("#sdate").val();
				var edate = $("#edate").val();
				location.href=(getContextPath() + '/include/csbr3010_1_print.do?sdate=' + sdate + '&edate=' + edate + '&xls=1');
			} else if($("#search_list_2").is(":visible")) {
				var sdate = $("#sdate").val();
				var edate = $("#edate").val();
				location.href=(getContextPath() + '/include/csbr3010_2_print.do?sdate=' + sdate + '&edate=' + edate + '&xls=1');
			} else {
				var sdate = $("#sdate").val();
				var edate = $("#edate").val();
				location.href=(getContextPath() + '/include/csbr3010_3_print.do?sdate=' + sdate + '&edate=' + edate + '&xls=1');
			}
		});

		$("#btn_close").click(function(){
			sub_close();
		});

		$("#btn_upload").click(function(){
			window.open(getContextPath() + '/include/csbr3010_upload.do','snsb_upload','width=600,height=200,scrollbars=no');
		});


		$("#intitle1_off").click(function(){
			$("#intitle1_off").hide();
			$("#intitle1_on").show();
			$("#intitle2_off").show();
			$("#intitle2_on").hide();
			$("#intitle3_off").show();
			$("#intitle3_on").hide();
			
			$("#search_list_1").show();
			$("#search_list_2").hide();
			$("#search_list_3").hide();
		});
		$("#intitle2_off").click(function(){
			$("#intitle1_off").show();
			$("#intitle1_on").hide();
			$("#intitle2_off").hide();
			$("#intitle2_on").show();
			$("#intitle3_off").show();
			$("#intitle3_on").hide();
			
			$("#search_list_1").hide();
			$("#search_list_2").show();
			$("#search_list_3").hide();
		});
		$("#intitle3_off").click(function(){
			$("#intitle1_off").show();
			$("#intitle1_on").hide();
			$("#intitle2_off").show();
			$("#intitle2_on").hide();
			$("#intitle3_off").hide();
			$("#intitle3_on").show();
			
			$("#search_list_1").hide();
			$("#search_list_2").hide();
			$("#search_list_3").show();
		});
		$("#btn_new").click(function(){
			window.open(getContextPath() + '/include/csbr3010_target.do','snsb_target','width=800,height=600,scrollbars=yes');
		});
	});

	function load_search_1(p) {

		$("#search_list_1").empty().html("<img src='images/loading.gif' style='position:absolute;left:45%;top:40%;'>");
		var sdate = $("#sdate").val();
		var edate = $("#edate").val();

		$.post(getContextPath() + "/include/data/csbr3010_search_1.do", {sdate:sdate, edate:edate, page:p})
		.done(function(data){
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

	function load_search_2(p) {

		$("#search_list_2").empty().html("<img src='images/loading.gif' style='position:absolute;left:45%;top:40%;'>");
		var sdate = $("#sdate").val();
		var edate = $("#edate").val();

		$.post(getContextPath()+"/include/data/csbr3010_search_2.do", {sdate:sdate, edate:edate, page:p})
		.done(function(data){
			$("#search_list_2").html(data).mygrid({
				cursorpointer: true,
				auto_first_select:true,
				hover:true,
				gopage:function(np){
					load_search_2(np);
				}
			});
			$("#search_list_2").find("li").css({'padding-left':'3px', 'padding-right':'3px'});
		});

	}

	function load_search_3(p) {

		$("#search_list_3").empty().html("<img src='images/loading.gif' style='position:absolute;left:45%;top:40%;'>");
		var sdate = $("#sdate").val();
		var edate = $("#edate").val();

		$.post(getContextPath()+"/include/data/csbr3010_search_3.do", {sdate:sdate, edate:edate, page:p})
		.done(function(data){
			$("#search_list_3").html(data).mygrid({
				cursorpointer: true,
				auto_first_select:true,
				hover:true,
				gopage:function(np){
					load_search_3(np);
				}
			});
			$("#search_list_3").find("li").css({'padding-left':'3px', 'padding-right':'3px'});
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
	<div style='height:23px;line-height:23px;margin:3px;margin-left:0px;float:left;border:0px solid black;padding:0px;cursor:pointer;'>
		<img id='btn_upload_sample' src='images/button/btn_xlsuploadsample.png' align='absmiddle' style='padding-top: 1.5px;'>
	</div>
	<div style='height:23px;line-height:23px;margin:3px;float:left;border:0px solid black;padding:0px;cursor:pointer;'>
		<img id='btn_upload' src='images/button/btn_xlsupload.png' align='absmiddle'> (※ 1000건당 2~3분정보 소요됨)
	</div>
</div>

<div id='bodytitle' style='z-index:10;'>
	<div id='intitle1_on' class='intitle' style='left:10px;'><b>1_Excel1_snsb2data</b></div>
	<div id='intitle1_off' class='intitle' style='left:10px;background-color:#EFEFEF;cursor:pointer;border-bottom:1px solid #849dbd;display:none;'><b>1_Excel1_snsb2data</b></div>
	<div id='intitle2_on' class='intitle' style='left:145px;display:none;'><b>2_Excel2_snsb2data</b></div>
	<div id='intitle2_off' class='intitle' style='left:145px;background-color:#EFEFEF;cursor:pointer;border-bottom:1px solid #849dbd;'><b>2_Excel2_snsb2data</b></div>
	<div id='intitle3_on' class='intitle' style='left:280px;display:none;'><b>SNSB</b></div>
	<div id='intitle3_off' class='intitle' style='left:280px;background-color:#EFEFEF;cursor:pointer;border-bottom:1px solid #849dbd;'><b>SNSB</b></div>
</div>

<div id='search_list_1' style='position:absolute;left:3px;top:66px;width:calc(100% );height:calc(100% - 72px);border:1px solid #849dbd;overflow-y:hidden;overflow-x:hidden;z-index:1;'></div>
<div id='search_list_2' style='position:absolute;left:3px;top:66px;width:calc(100% - 8px);height:calc(100% - 72px);border:1px solid #849dbd;overflow-y:hidden;overflow-x:hidden;z-index:1;display:none;'></div>
<div id='search_list_3' style='position:absolute;left:3px;top:66px;width:calc(100% - 8px);height:calc(100% - 72px);border:1px solid #849dbd;overflow-y:hidden;overflow-x:hidden;z-index:1;display:none;'></div>


