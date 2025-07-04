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


		$("#btn_search").click(function(){
			load_search();
		});

		$("#btn_mod").click(function(){
			 load_info_edit();
		});

		$("#btn_cancel").click(function(){
			load_info();
		});

		$("#btn_save").click(function(){
			$("#info_iframe").contents().find("#info_form").submit();
		});

		$("#btn_excel").click(function(){
			var sdate = $("#sdate").val();
			var edate = $("#edate").val();
			var st_name = $("#st_name").val();
			var st_idx = $("#st_idx").val();
			var st_num = $("#st_num").val();

			location.href=(getContextPath() + '/include/csbr1050_print.do?xls=1&sdate='+sdate + '&edate=' + edate + '&st_name=' + st_name + '&st_idx=' + st_idx + '&st_num=' + st_num);
		});

		$("#btn_close").click(function(){
			sub_close();
		});

		$("#st_name, #st_idx, #st_num").keypress(function(e){
			if ( e.which == 13 ){
				$("#btn_search").click();
			}
		});

		$("#sheet_popup").draggable();
		//팝업 바깥쪽 클릭시 사라지게
		$(document).mouseup(function(e){
			var container = $("#sheet_popup");
			if (!container.is(e.target) && container.has(e.target).length === 0) {
				hide_popup();
			}
		});
		$(document).keyup(function(e) {
			if (e.keyCode === 27) hide_popup();   // esc
		});
		$("#popup_close").click(function(){
			hide_popup();
		});

		$(window).resize(function(){
			resize();
		});

	//	resize();

		load_search();

	});

	function hide_popup() {
		$("#sheet_popup").hide();
		$("#popup_iframe").attr('src','blank.jsp');
	}
	function show_popup(tabname) {
		var left = 0;
		var top = 0;
		$("#popup_header").html("&nbsp;" + tabname);
  
		 left = parseInt( $(window).width() / 2 ) - parseInt( $("#sheet_popup").width() / 2 );
		 top = parseInt( $(window).height() / 2 ) - parseInt( $("#sheet_popup").height() / 2 );

		$("#sheet_popup").css('left',left+'px');
		$("#sheet_popup").css('top',top+'px');
		$("#sheet_popup").show();
	}

	function load_search() {
		$("#selected_object_idx").val('');
		$("#selected_selection_num").val('');
		$("#search_list").html("<img src='images/loading.gif' style='position:absolute;left:45%;top:40%;'>");
		var sdate = $("#sdate").val();
		var edate = $("#edate").val();
		var st_name = $("#st_name").val();
		var st_idx = $("#st_idx").val();
		var st_num = $("#st_num").val();
		
		$.post(getContextPath() + "/include/data/csbr1031_search.do", {sdate:sdate, edate:edate, st_name:st_name, st_idx:st_idx, st_num:st_num})
		.done(function(data){
			$("#search_list").html(data).mygrid({
				cursorpointer: true,
				auto_first_select:true,
				hover:true,
				rowclick: function(sdata) {
					var sp = sdata.split('|');
					if (sp.length != 2) return false;
					$("#selected_object_idx").val(sp[0]);
					$("#selected_selection_num").val(sp[1]);
					load_mmse();
					load_info();
				}
			});
		});
	}

	function popup_tab(nt, tid, tidx, tnum, tday) {
		//파라미터는 검사명이며 검사명별로 탭지정으로 변경함.
		nt = CHTAB[nt];
		if( nt=='' ) return false;

		var url = "";
		var tabname = "";
		for(var i = 0; i <= 2; i++ ) {
			for(var k=0; k<tab[i].length;k++) {
				var n = tab[i][k];
				if(n.code==nt) {
					url = n.url;
					tabname = n.name;
				}
			}
		}
		if ( url == "" ) return false;
		var sobjidx = $("#selected_object_idx").val(); //메인폼에 있음
		if (sobjidx=='') return false;
		url = url + "?object_idx=" + sobjidx + "&exam_id=" + tid + "&exam_idx=" + tidx + "&exam_num=" + tnum + "&exam_day=" + tday;
		$("#popup_iframe").attr('src',url);
		show_popup(tabname);
	
	}
</script>
<input type='hidden' id='selected_object_idx' value=''>
<input type='hidden' id='selected_selection_num' value=''>
<div style='margin:3px;padding:0px;width:calc(100% - 8px);height:30px;background-color:#ebecef;border-radius: 3px;border:1px solid #c2c4cb;'>
	<div style='width:80px;height:23px;line-height:23px;margin:3px;border:1px solid black;background-color:#c0c0ff;text-align:center;float:left;font-weight:bold;'>일&nbsp;&nbsp;&nbsp;자</div>
	<div style='height:23px;margin:3px;float:left;border:0px solid black;padding:0px;'>
		<input type='text' class='calendar' id='sdate' value='<%=sdate%>' >
		~
		<input type='text' class='calendar' id='edate' value='<%=edate%>' >
	</div>
	<div style='width:40px;height:23px;line-height:23px;margin:3px 0px 0px 3px;border:1px solid black;background-color:#c0c0ff;text-align:center;float:left;font-weight:bold;'>이름</div>
	<input type='text' id='st_name' value='' style='float:left;width:50px;height:23px;padding:0px;margin:3px 0px 0px 0px;border-radius:0px;' autocomplete='off'>
	<div style='width:50px;height:23px;line-height:23px;margin:3px 0px 0px 0px;border:1px solid black;background-color:#c0c0ff;text-align:center;float:left;font-weight:bold;'>등록번호</div>
	<input type='text' id='st_idx' value='' style='float:left;width:40px;height:23px;padding:0px;margin:3px 0px 0px 0px;border-radius:0px;' autocomplete='off'>
	<div style='width:30px;height:23px;line-height:23px;margin:3px 0px 0px 0px;border:1px solid black;background-color:#c0c0ff;text-align:center;float:left;font-weight:bold;'>차수</div>
	<input type='text' id='st_num' value='' style='float:left;width:30px;height:23px;padding:0px;margin:3px 0px 0px 0px;border-radius:0px;' autocomplete='off'>
	<div style='height:23px;line-height:23px;margin:3px;margin-left:20px;float:left;border:0px solid black;padding:0px;cursor:pointer;'>
		<div id='normal_mod_buttons'>
			<img id='btn_search' src='images/button/btn_search.png' align='absmiddle'>
			<img id='btn_mod' src='images/button/btn_mod.png' align='absmiddle'>
			<img src='images/button/btn_save_d.png' align='absmiddle'>
			<img src='images/button/btn_cancel_d.png' align='absmiddle'>
			<img id='btn_excel' src='images/button/btn_excel.png' align='absmiddle'>
			<img id='btn_close' src='images/button/btn_close.png' align='absmiddle'>
		</div>
		<div id='edit_mod_buttons' style='display:none;'>
			<img src='images/button/btn_search_d.png' align='absmiddle'>
			<img src='images/button/btn_mod_d.png' align='absmiddle'>
			<img id='btn_save' src='images/button/btn_save.png' align='absmiddle'>
			<img id='btn_cancel' src='images/button/btn_cancel.png' align='absmiddle'>
			<img src='images/button/btn_excel_d.png' align='absmiddle'>
			<img src='images/button/btn_close_d.png' align='absmiddle'>
		</div>
	</div>
</div>

<div id='search_list' style='position:absolute;left:3px;top:40px;width:calc(100% - 8px);height:calc(100% - 45px);border:1px solid #849dbd;overflow-y:hidden;overflow-x:auto;'></div>

<div id='sheet_popup' style='position:absolute;left:0px;top:0px;width:630px;height:720px;background-color:#FFFFFF;border:2px solid #858585;overflow:hidden;display:none;z-index:9999999'>
	<div id='popup_header' style='width:100%;height:20px;line-height:20px;color:white;font-weight:bold;background-color:#bcbcbc;border-bottom:1px solid #858585;cursor:all-scroll;'>&nbsp;[TAB]</div>
	<div id='popup_close' style='position:absolute;right:5px;top:3px;font-weight:bold;cursor:pointer;'>x</div>

	<iframe id='popup_iframe' style='width:100%;height:700px;' frameborder='0'></iframe>

</div>




