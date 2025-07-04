<%@page import="csbrain.common.util.DateUtil"%>
<%@page import="csbrain.common.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String edate = request.getParameter("edate");
	String sdate = request.getParameter("sdate");
	if ( StringUtil.isEmptyString(edate) ) edate = DateUtil.getTodayByFormat("yyyy-MM-dd");
	if ( StringUtil.isEmptyString(sdate) ) sdate = DateUtil.addDayDate("yyyy-MM-dd", -6);
%>
<script>
	var mmse_count = 0;
	var sunbyul_count = 0;
	var jungmil_count = 0;
	var etc_count = 0;
	var is_memclick = false; //명단 클릭시에는 모든 검사결과리스트에서 클릭 이벤트가 일어나지 않도록

	$(function(){


		$("#btn_search").click(function(){
			load_day();
		});

		$("#btn_excel").click(function(){
			var s = $("#selected_date").val();
			if ( s == '' ) return false;
			location.href=(getContextPath() + '/include/csbr1020_print.do?xls=1&d='+s);
		});

		$("#btn_close").click(function(){
			sub_close();
		});

		$(window).resize(function(){
			resize();
		});


		resize();

		is_memclick = true;
		load_day();

	});

	function load_day() {
		$("#selected_date").val('');
		$("#selected_tabobjectid").val('');

		$("#left_data").html('');
		$("#list_data").html(''); //내용지우기
		$("#mmse_data").html('');
		$("#sunbyul_data").html('');
		$("#jungmil_data").html('');
		$("#etc_data").html('');


		var sdate = $("#sdate").val();
		var edate = $("#edate").val();
		var s = $("#selected_date").val();

		$.post(getContextPath() + "/include/data/csbr1020_left_data.do", {sdate:sdate, edate:edate})
		.done(function(data){
			$("#left_data").html(data).mygrid({
				cursorpointer: true,
				auto_first_select:true,
				hover:true,
				rowclick: function(sdata) {
					$("#selected_date").val(sdata);
					load_list();
				}
			});
		});
	}

	function load_list() {
		//console.log('load_list');
		$("#right_data").html('');
		$("#mmse_data").html('');
		$("#sunbyul_data").html('');
		$("#jungmil_data").html('');
		$("#etc_data").html('');

		var s = $("#selected_date").val();
		$("#selected_object_idx").val('');
		$.post(getContextPath() + "/include/data/csbr1020_list_data.do", {d:s})
		.done(function(data){
			$("#list_data").html(data).mygrid({
				cursorpointer: true,
				auto_first_select:true,
				hover:true,
				rowclick: function(sdata) {
					$("#selected_object_idx").val(sdata);
					$("#selected_tabobjectid").val(sdata);
					load_right();
					load_etc();
				}
			});
			$("#list_data").find("li").css({'padding-left':'3px', 'padding-right':'3px'});
		});
	}


	function load_right() {
		//console.log('load_right');
		$("#right_data").html('');
		var sidx = $("#selected_object_idx").val();
		$.post(getContextPath() + "/include/data/csbr1010_right.do", {object_idx:sidx})
		.done(function(data){
			$("#right_data").html(data);
			$("#btnsave_opinion").unbind();
			$("#btnsave_opinion").click(function(){
				$("#form_opinion").attr('action',getContextPath() + '/include/csbr1010_opinion_save.do?object_idx=' + sidx);
				$("#form_opinion").submit();

			});
		});
	}

	function load_mmse() {
		//console.log('load_mmse');
		$("#mmse_data").html('');
		var sidx = $("#selected_object_idx").val();
		$.post(getContextPath() + "/include/data/mmse_list.do", {object_idx:sidx})
		.done(function(data){
			$("#mmse_data").html(data).mygrid({
				cursorpointer: true,
				auto_first_select:true,
				hover:true,
				rowclick: function(sdata) {
					if(is_memclick == false ) {
						var sp = sdata.split('|');
						if (sp.length != 4) return false;
						tab_nowtabcode(sp[0], sp[0], sp[1], sp[2], sp[3]);
						draw_tab();
					}
				},
				onfinish: function(s,c){
					//mmse_count = $("#mmse_data").find("#row_count").val();
					//var sdata = $("#mmse_data").find("#select_data").val(); //선택된 데이터
					mmse_count = c;
					var sdata = s; //선택된 데이터
					var sp = sdata.split('|');
					if (sp.length == 4) {
						tab_nowtabcode(sp[0], sp[0], sp[1], sp[2], sp[3]);
					}
					resize();
					draw_tab();
					is_memclick=false;
				}
			});
			$("#mmse_data").find("li").css({'padding-left':'3px', 'padding-right':'3px'});
		});
	}

	function load_sunbyul() {
		//console.log('load_sunbyul');
		$("#sunbyul_data").html('');
		var sidx = $("#selected_object_idx").val();
		$.post(getContextPath() + "/include/data/sunbyul_list.do", {object_idx:sidx})
		.done(function(data){
			$("#sunbyul_data").html(data).mygrid({
				cursorpointer: true,
				auto_first_select:true,
				hover:true,
				rowclick: function(sdata) {
					if(is_memclick == false ) {
						var sp = sdata.split('|');
						if (sp.length != 4) return false;
						tab_nowtabcode(sp[0], sp[0], sp[1], sp[2], sp[3]);
						draw_tab();
					}
				},
				onfinish: function(s, c){
					//sunbyul_count = $("#sunbyul_data").find("#row_count").val();
					//var sdata = $("#sunbyul_data").find("#select_data").val(); //선택된 데이터
					sunbyul_count = c;
					var sdata = s; //선택된 데이터
					//console.log("sdata:"+ sdata);
					var sp = sdata.split('|');
					if (sp.length == 4) {
						tab_nowtabcode(sp[0], sp[0], sp[1], sp[2], sp[3]);
					}
					load_mmse();
				}
			});
			$("#sunbyul_data").find("li").css({'padding-left':'3px', 'padding-right':'3px'});
		});
	}
	/*
	function load_jungmil() {
		//console.log('load_jungmil');
		$("#jungmil_data").html('');
		var sidx = $("#selected_object_idx").val();
		$.post(getContextPath() + "/include/data/jungmil_list.do", {object_idx:sidx})
		.done(function(data){
			$("#jungmil_data").html(data).mygrid({
				cursorpointer: true,
				auto_first_select:true,
				hover:true,
				rowclick: function(sdata) {
					if(is_memclick == false ) {
						var sp = sdata.split('|');
						if (sp.length != 4) return false;
						tab_nowtabcode(sp[0], sp[0], sp[1], sp[2], sp[3]);
						draw_tab();
					}
				},
				onfinish: function(s,c){
					//jungmil_count = $("#jungmil_data").find("#row_count").val();
					//var sdata = $("#jungmil_data").find("#select_data").val(); //선택된 데이터
					jungmil_count = c;
					var sdata = s; //선택된 데이터
					var sp = sdata.split('|');
					if (sp.length == 4) {
						tab_nowtabcode(sp[0], sp[0], sp[1], sp[2], sp[3]);
					}
					load_sunbyul();
				}
			});
			$("#jungmil_data").find("li").css({'padding-left':'3px', 'padding-right':'3px'});
		});
	}
*/
	function load_etc() {
		//console.log('load_etc');
		$("#etc_data").html('');
		var sidx = $("#selected_object_idx").val();
		$.post(getContextPath() + "/include/data/etc_list.do", {object_idx:sidx})
		.done(function(data){
			$("#etc_data").html(data).mygrid({
				cursorpointer: true,
				auto_first_select:true,
				hover:true,
				rowclick: function(sdata) {
					if(is_memclick == false ) {
						var sp = sdata.split('|');
						if (sp.length != 4) return false;
						tab_nowtabcode(sp[0], sp[0], sp[1], sp[2], sp[3]);
						draw_tab();
					}
				},
				onfinish: function(s,c){
					//etc_count = $("#etc_data").find("#row_count").val();
					//var sdata = $("#etc_data").find("#select_data").val(); //선택된 데이터
					etc_count = c;
					var sdata = s; //선택된 데이터
					var sp = sdata.split('|');
					if (sp.length == 4) {
						tab_nowtabcode(sp[0], sp[0], sp[1], sp[2], sp[3]);
					}
					load_sunbyul();
				}
			});
			$("#etc_data").find("li").css({'padding-left':'3px', 'padding-right':'3px'});
		});
	}


	function resize(){

		//console.log("개수들:" + mmse_count + "/" + sunbyul_count + "/" + jungmil_count + "/" + etc_count);

		var doc_width = $(window).width();
		var doc_height = $(window).height();

		if(doc_width > 1250 ) {
			$("#search_list").css({'width':'400px', 'height':(doc_height-$("#info_list").height()-85)+'px'});
			$("#list_data").css({'width':'340px'});
		} else {
			$("#search_list").css({'width':(doc_width-900)+'px', 'height':(doc_height-$("#info_list").height()-85)+'px'});
			$("#list_data").css({'width':(doc_width-900-170)+'px'});
		}

		var w = $("#list_data").width();
		var h = $("#list_data").height();
		var t = $("#list_data").position().top;
		var l = $("#list_data").position().left;

		var hpl = 25; //줄단 높이
		var th = 75; //상단 제목부분
		var mmse_height = 0;
		if(mmse_count > 5 ) mmse_height = 5 * hpl + th;
		else mmse_height= mmse_count * hpl + th;
		var sunbyul_height = 0;
		if(sunbyul_count > 7 ) sunbyul_height = 7 * hpl + th;
		else sunbyul_height= sunbyul_count * hpl + th;
		var etc_height = 0;
		if(etc_count > 5 ) jungmil_height = 5 * hpl + th;
		else etc_height= jungmil_count * hpl + th;
		
		$("#mmse_list").css({'left': (l+w+5)+'px', 'top':(t)+'px', 'width':(doc_width-900-w-140)+'px','height':(mmse_height)+'px'});
		$("#sunbyul_list").css({'left':(l+w+5)+'px', 'top':(t)+'px', 'width':(doc_width-900-w-140)+'px','height':(mmse_height)+'px'});
		$("#etc_list").css({'left':(l+w+5)+'px', 'top':($("#sunbyul_list").position().top+$("#sunbyul_list").height()+5)+'px','width':($("#mmse_list").width())+'px'});
		//$("#etc_list").css({'left':(l+w+5)+'px', 'top':($("#jungmil_list").position().top+$("#jungmil_list").height()+5)+'px','width':($("#mmse_list").width())+'px','height':(doc_height-$("#jungmil_list").position().top-$("#jungmil_list").height()-18)+'px'});
		$("#etc_data").css('height',($("#etc_list").height()-30)+'px');

		$("#search_area").css({'width':(l+w + $("#mmse_list").width()+3)+'px'});

		$("#tabcontent_area").css({'left':($("#search_area").position().left+$("#search_area").width()+9)+'px','width':'876px','height':(doc_height-15)+'px'});

		$("#opinion").css({'height':($("#right_data").height()-250)+'px'});
		$("#opinion_text").css({'height':($("#right_data").height()-250)+'px'});

	}



</script>

<input type='hidden' id='selected_object_idx' value=''>

<div id='search_area' style='position:relative;margin:3px;padding:0px;width:calc(100% - 8px);height:30px;background-color:#ebecef;border-radius: 3px;border:1px solid #c2c4cb;'>

	<div style='position:absolute;left:0px;overflow:hidden;width:calc(100% - 170px);height:100%;display:inline;'>
		<div style='width:80px;height:23px;line-height:23px;margin:3px;border:1px solid black;background-color:#c0c0ff;text-align:center;float:left;font-weight:bold;'>일&nbsp;&nbsp;&nbsp;자</div>
		<div style='height:23px;margin:3px;float:left;border:0px solid black;padding:0px;'>
			<input type='text' class='calendar' id='sdate' value='<%=sdate%>' >
			~
			<input type='text' class='calendar' id='edate' value='<%=edate%>' >
		</div>
	</div>
	<div style='height:23px;line-height:23px;margin:3px;float:right;border:0px solid black;padding:0px;cursor:pointer;'>
			<img id='btn_search' src='images/button/btn_search.png' align='absmiddle' >
			<img id='btn_excel' src='images/button/btn_excel.png' align='absmiddle' >
			<img id='btn_close' src='images/button/btn_close.png' align='absmiddle' >
	</div>
</div>
<input type='hidden' id='selected_date' value='' >
<div id='left_data' style='position:absolute;left:3px;top:40px;width:133px;border:1px solid #849dbd;height:calc(100% - 45px);overflow-y:hidden;overflow-x:auto;'></div>
<div id='list_data' style='position:absolute;left:140px;top:40px;width:180px;border:1px solid #849dbd;height:calc(100% - 45px);overflow-y:hidden;overflow-x:auto;'></div>

<div id='search_list' style='position:absolute;left:3px;top:40px;border:1px solid #849dbd;overflow-y:hidden;overflow-x:auto;display:none;'></div>

<div id='info_list' style='position:absolute;height:400px;border:0px solid #849dbd;display:none;'>
	<div style='width:100%;height:25px;line-height:25px;border:1px solid black;background-color:#add8e6;text-align:center;font-weight:bold;overflow:hidden;'>인적사항</div>
	<div id='info_data' style='margin-top:3px;width:100%;height:370px;border:1px solid #849dbd;'></div>
</div>

<div id='mmse_list' style='position:absolute;width:460px;height:150px;border:0px solid #849dbd; display: none;'>
	<div style='width:100%;height:25px;line-height:25px;border:1px solid black;background-color:#add8e6;text-align:center;font-weight:bold;overflow:hidden;'>MMSE</div>
	<div id='mmse_data' style='margin-top:3px;width:100%;height:calc(100% - 30px);border:1px solid #849dbd;'></div>
</div>
<div id='sunbyul_list' style='position:absolute;height:160px;border:0px solid #849dbd;'>
	<div style='width:100%;height:25px;line-height:25px;border:1px solid black;background-color:#add8e6;text-align:center;font-weight:bold;overflow:hidden;'>선별검사</div>
	<div id='sunbyul_data' style='margin-top:3px;width:100%;height:calc(100% - 30px);border:1px solid #849dbd;'></div>
</div>

<div id='etc_list' style='position:absolute;height:115px;border:0px solid #849dbd;'>
	<div style='width:100%;height:25px;line-height:25px;border:1px solid black;background-color:#add8e6;text-align:center;font-weight:bold;overflow:hidden;'>기타검사</div>
	<div id='etc_data' style='margin-top:3px;width:100%;height:calc(100% - 30px);border:1px solid #849dbd;'></div>
</div>

<div id='tabcontent_area' style='position:absolute;width:875px;height:100%;top:2px;right:3px;border:0px solid red;'>
	<div id='tab_area' style='position:absolute;left:0px;top:0px;width:100%;height:70px;border:0px solid blue;background-color:#EFEFEF;'>
		<div id='tab_line1' style='width:99%;margin-left:0.5%;height:23px;'></div>
		<div id='tab_line2' style='width:99%;margin-left:0.5%;height:23px;'></div>
		<div id='tab_line3' style='width:99%;margin-left:0.5%;height:23px;'></div>
		<input type='hidden' id='tab_code' value=''>
		<input type='hidden' id='tab_exam_id' value=''>
		<input type='hidden' id='tab_exam_idx' value=''>
		<input type='hidden' id='tab_exam_num' value=''>
		<input type='hidden' id='tab_exam_day' value=''>
	</div>
	<div style='position:absolute;left:0px;top:70px;width:72%;height:calc(100% - 70px);border:1px solid #a5a5a5;overflow:hidden;'>
		<iframe id='tab_iframe' style='width:100%;height:100%;' frameborder='0'></iframe>
	</div>

	<div id='right_data' style='position:absolute;left:calc(72% + 4px);top:70px;width:calc(28% - 4px);height:calc(100% - 70px);border:1px solid #a5a5a5;text-align:center;background-color:#EFEFEF;'>

	</div>
</div>