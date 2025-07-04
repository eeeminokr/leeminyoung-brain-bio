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

		resize();

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
		$("#mmse_data").html('');
		$("#sunbyul_data").html('');
		$("#jungmil_data").html('');
		$("#etc_data").html('');
		$("#snsb_data").html('');
		$("#mri_data").html('');
		$("#pet_data").html('');
		$("#csf_data").html('');
		$("#blod_data").children('ul').children('li').html('');
		$("#apoe_data").children('ul').children('li').html('');
		$("#info_iframe").attr('src','blank.jsp');

		var sdate = $("#sdate").val();
		var edate = $("#edate").val();
		var st_name = $("#st_name").val();
		var st_idx = $("#st_idx").val();
		var st_num = $("#st_num").val();
		
		$.post(getContextPath() + "/include/data/csbr1050_search.do", {sdate:sdate, edate:edate, st_name:st_name, st_idx:st_idx, st_num:st_num})
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
					//load_mmse();
					load_total();
					load_info();
				}
			});
		});
	}

	function load_info() {
		var sidx = $("#selected_object_idx").val();
		var snum = $("#selected_selection_num").val();
		$("#info_iframe").attr('src',getContextPath() + '/include/csbr1050_info.do?object_idx=' + sidx + '&selection_num=' + snum);
		$("#normal_mod_buttons").show(); //일반버튼 보이기
		$("#edit_mod_buttons").hide(); //수정버튼 숨기기
	}

	function load_info_edit() {
		var sidx = $("#selected_object_idx").val();
		var snum = $("#selected_selection_num").val();
		if ( sidx == '' || snum == '' ) return false;
		$("#info_iframe").attr('src',getContextPath() + '/include/csbr1050_info.do?edit=true&object_idx=' + sidx + '&selection_num=' + snum);
		$("#normal_mod_buttons").hide(); //일반버튼 보이기
		$("#edit_mod_buttons").show(); //수정버튼 숨기기
	}

	function load_mmse() {
		var sidx = $("#selected_object_idx").val();
		$.post(getContextPath() + "/include/data/mmse_list.do", {object_idx:sidx})
		.done(function(data){
			$("#mmse_data").html(data).mygrid({
				cursorpointer: true,
				auto_first_select:true,
				hover:true,
				rowclick: function(sdata) {
				},
				dblclick: function(sdata) {
					var sp = sdata.split('|');
					if (sp.length != 4) return false;
					popup_tab(sp[0], sp[0], sp[1], sp[2], sp[3]);
				}
			});
			$("#mmse_data").find("li").css({'padding-left':'3px', 'padding-right':'3px'});
		});
		//load_sunbyul();
		load_total();
	}
	
	function load_total() {
		var sidx = $("#selected_object_idx").val();
		$.post(getContextPath() + "/include/data/sunbyul_list.do", {object_idx:sidx})
		.done(function(data){
			$("#total_data").html(data).mygrid({
				cursorpointer: true,
				auto_first_select:true,
				hover:true,
				rowclick: function(sdata) {
				},
				dblclick: function(sdata) {
					var sp = sdata.split('|');
					if (sp.length != 4) return false;
					popup_tab(sp[0], sp[0], sp[1], sp[2], sp[3]);
				}
			});
			$("#total_data").find("li").css({'padding-left':'3px', 'padding-right':'3px'});
		});
		load_etc();
	}

	function load_sunbyul() {
		var sidx = $("#selected_object_idx").val();
		$.post(getContextPath() + "/include/data/sunbyul_list.do", {object_idx:sidx})
		.done(function(data){
			$("#sunbyul_data").html(data).mygrid({
				cursorpointer: true,
				auto_first_select:true,
				hover:true,
				rowclick: function(sdata) {
				},
				dblclick: function(sdata) {
					var sp = sdata.split('|');
					if (sp.length != 4) return false;
					popup_tab(sp[0], sp[0], sp[1], sp[2], sp[3]);
				}
			});
			$("#sunbyul_data").find("li").css({'padding-left':'3px', 'padding-right':'3px'});
		});
		load_jungmil();
	}
	function load_jungmil() {
		var sidx = $("#selected_object_idx").val();
		$.post(getContextPath() + "/include/data/jungmil_list.do", {object_idx:sidx})
		.done(function(data){
			$("#jungmil_data").html(data).mygrid({
				cursorpointer: true,
				auto_first_select:true,
				hover:true,
				rowclick: function(sdata) {
				},
				dblclick: function(sdata) {
					var sp = sdata.split('|');
					if (sp.length != 4) return false;
					popup_tab(sp[0], sp[0], sp[1], sp[2], sp[3]);
				}
			});
			$("#jungmil_data").find("li").css({'padding-left':'3px', 'padding-right':'3px'});
		});
		load_etc();
	}

	function load_etc() {
		var sidx = $("#selected_object_idx").val();
		$.post(getContextPath() + "/include/data/etc_list.do", {object_idx:sidx})
		.done(function(data){
			$("#etc_data").html(data).mygrid({
				cursorpointer: true,
				auto_first_select:true,
				hover:true,
				rowclick: function(sdata) {
				},
				dblclick: function(sdata) {
					var sp = sdata.split('|');
					if (sp.length != 4) return false;
					popup_tab(sp[0], sp[0], sp[1], sp[2], sp[3]);
				}
			});
			$("#etc_data").find("li").css({'padding-left':'3px', 'padding-right':'3px'});
		});
		load_snsb();
	}

	function load_snsb() {
		var sidx = $("#selected_object_idx").val();
		$.post(getContextPath() + "/include/data/snsb_list.do", {object_idx:sidx})
		.done(function(data){
			$("#snsb_data").html(data).mygrid({
				cursorpointer: true,
				auto_first_select:true,
				hover:true,
				rowclick: function(sdata) {

				}
			});
			$("#snsb_data").find("li").css({'padding-left':'3px', 'padding-right':'3px'});
		});
		load_mri();
	}
	function load_mri() {
		var sidx = $("#selected_object_idx").val();
		$.post(getContextPath() + "/include/data/mri_list.do", {object_idx:sidx})
		.done(function(data){
			$("#mri_data").html(data).mygrid({
				cursorpointer: true,
				auto_first_select:true,
				hover:true,
				rowclick: function(sdata) {

				}
			});
			$("#mri_data").find("li").css({'padding-left':'3px', 'padding-right':'3px'});
		});
		load_pet();
	}
	function load_pet() {
		var sidx = $("#selected_object_idx").val();
		$.post(getContextPath() + "/include/data/pet_list.do", {object_idx:sidx})
		.done(function(data){
			$("#pet_data").html(data).mygrid({
				cursorpointer: true,
				auto_first_select:true,
				hover:true,
				rowclick: function(sdata) {

				}
			});
			$("#pet_data").find("li").css({'padding-left':'3px', 'padding-right':'3px'});
		});
		load_csf();
	}
	function load_csf() {
		var sidx = $("#selected_object_idx").val();
		$.post(getContextPath() + "/include/data/csf_list.do", {object_idx:sidx})
		.done(function(data){
			$("#csf_data").html(data).mygrid({
				cursorpointer: true,
				auto_first_select:true,
				hover:true,
				rowclick: function(sdata) {

				}
			});
			$("#csf_data").find("li").css({'padding-left':'3px', 'padding-right':'3px'});
		});
		load_blood();
	}
	function load_blood() {
		var sidx = $("#selected_object_idx").val();
		$.post(getContextPath() + "/include/data/blood_list.do", {object_idx:sidx})
		.done(function(data){
			$("#blod_data").html(data).mygrid({
				cursorpointer: true,
				auto_first_select:true,
				hover:true,
				rowclick: function(sdata) {

				}
			});
		});
		load_apoe();
	}
	function load_apoe() {
		var sidx = $("#selected_object_idx").val();
		$.post(getContextPath() + "/include/data/apoe_list.do", {object_idx:sidx})
		.done(function(data){
			$("#apoe_data").html(data).mygrid({
				cursorpointer: true,
				auto_first_select:true,
				hover:true,
				rowclick: function(sdata) {

				}
			});
		});
	}
	
	function resize(){

		var doc_width = $(window).width();
		var doc_height = $(window).height();

		$("#search_list").css({'width':(doc_width-500)+'px', 'height':(doc_height-665)+'px'});

		var w = $("#search_list").width();
		var h = $("#search_list").height();
		var t = $("#search_list").position().top;
		var l = $("#search_list").position().left;


		$("#total_list").css({'left': (l)+'px', 'top':(t+h+5)+'px'});
		$("#info_data").css('left',(l+w+5)+'px');
		//$("#total_list").css({'left':(l)+'px', 'top':($("#mmse_list").position().top+$("#mmse_list").height()+5)+'px','width':($("#mmse_list").width())+'px'});
		//$("#sunbyul_list").css({'left':(l)+'px', 'top':($("#mmse_list").position().top+$("#mmse_list").height()+5)+'px','width':($("#mmse_list").width())+'px'});
		//$("#jungmil_list").css({'left':(l)+'px', 'top':($("#sunbyul_list").position().top+$("#sunbyul_list").height()+5)+'px','width':($("#mmse_list").width())+'px'});
		$("#etc_list").css({'left':(l)+'px', 'top':($("#total_list").position().top+$("#total_list").height()+15)+'px','width':($("#total_list").width())+'px'});

		$("#snsb_list").css({'left': ($("#total_list").width()+8)+'px', 'top':($("#total_list").position().top)+'px', 'width':(w-$("#total_list").width()-5)+'px'});
		$("#mri_list").css({'left': ($("#total_list").width()+8)+'px', 'top':($("#snsb_list").position().top+$("#snsb_list").height()+5)+'px', 'width':(w-$("#total_list").width()-5)+'px'});
		$("#blod_apoe_list").css({'left': ($("#total_list").width()+8)+'px', 'top':($("#mri_list").position().top+$("#mri_list").height()+5)+'px', 'width':(w-$("#total_list").width()-5)+'px'});
		$("#pet_list").css({'left': ($("#total_list").width()+8)+'px', 'top':($("#blod_apoe_list").position().top+$("#blod_apoe_list").height()+5)+'px', 'width':(w-$("#total_list").width()-5)+'px'});
		$("#csf_list").css({'left': ($("#total_list").width()+8)+'px', 'top':($("#pet_list").position().top+$("#pet_list").height()+5)+'px', 'width':(w-$("#total_list").width()-5)+'px'});
		
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
		url = url + "?object_idx=" + sobjidx + "&exam_id=" + tid + "&exam_idx=" + tidx + "&exam_num=" + tnum + "&exam_day=" + tday + "&action=hidden";
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

<div id='search_list' style='position:absolute;left:3px;top:40px;border:1px solid #849dbd;overflow-y:hidden;overflow-x:auto;'></div>
<%--
<div id='mmse_list' style='position:absolute;width:450px;height:140px;border:0px solid #849dbd;'>
	<div style='width:100%;height:25px;line-height:25px;border:1px solid black;background-color:#add8e6;text-align:center;font-weight:bold;'>MMSE</div>
	<div id='mmse_data' style='margin-top:3px;width:100%;height:110px;border:1px solid #849dbd;'></div>
</div> --%>
<div id='total_list' style='position:absolute;width:450px;height:465px;border:0px solid #849dbd;'>
	<div style='width:100%;height:25px;line-height:25px;border:1px solid black;background-color:#add8e6;text-align:center;font-weight:bold;'>선별/정밀 검사</div>
	<div id='total_data' style='margin-top:3px;width:100%;height:445px;border:1px solid #849dbd;'></div>
</div>
<%--
<div id='sunbyul_list' style='position:absolute;height:165px;border:0px solid #849dbd;'>
	<div style='width:100%;height:25px;line-height:25px;border:1px solid black;background-color:#add8e6;text-align:center;font-weight:bold;'>선별검사</div>
	<div id='sunbyul_data' style='margin-top:3px;width:100%;height:135px;border:1px solid #849dbd;'></div>
</div>
<div id='jungmil_list' style='position:absolute;height:160px;border:0px solid #849dbd;'>
	<div style='width:100%;height:25px;line-height:25px;border:1px solid black;background-color:#add8e6;text-align:center;font-weight:bold;'>정밀검사</div>
	<div id='jungmil_data' style='margin-top:3px;width:100%;height:130px;border:1px solid #849dbd;'></div>
</div>
 --%>
<div id='etc_list' style='position:absolute;height:125px;border:0px solid #849dbd;'>
	<div style='width:100%;height:25px;line-height:25px;border:1px solid black;background-color:#add8e6;text-align:center;font-weight:bold;'>기타검사</div>
	<div id='etc_data' style='margin-top:3px;width:100%;height:95px;border:1px solid #849dbd;'></div>
</div>

<div id='snsb_list' style='position:absolute;height:140px;border:0px solid #849dbd;'>
	<div style='position:absolute;left:0px;top:0px;width:50px;height:100%;border:1px solid black;background-color:#add8e6;text-align:center;font-weight:bold;'><br><br><br><br>SNSB</div>
	<div id='snsb_data' style='position:absolute;left:53px;top:0px;width:calc(100% - 53px);height:100%;border:1px solid #849dbd;'></div>
</div>
<div id='blod_apoe_list' style='position:absolute;height:25px;border:0px solid #849dbd;width:100%;'>
	<div id='blod_box' style='width:50%;'>
		<div style='position:absolute;left:0px;top:0px;width:50px;height:100%;border:1px solid black;background-color:#add8e6;text-align:center;font-weight:bold;'><span style="display: inline-block; margin-top: 4px;font-size: 11px;">Blood ID</span></div>
		<div id='blod_data' style='position:absolute;left:53px;top:0px;width: 200px;height:100%;border:1px solid #849dbd;'></div>
	</div>
	<div id='apoe_box' style='width: 50%;'>
		<div style='position:absolute;left: 257px;top:0px;width:50px;height:100%;border:1px solid black;background-color:#add8e6;text-align:center;font-weight:bold;'><span style="display: inline-block; margin-top: 4px;font-size: 11px;">APOE+</span></div>
		<div id='apoe_data' style='position:absolute;left: 310px;top:0px;width: 200px;height:100%;border:1px solid #849dbd;'></div>
	</div>
</div>
<div id='mri_list' style='position:absolute;height:165px;border:0px solid #849dbd;'>
	<div style='position:absolute;left:0px;top:0px;width:50px;height:100%;border:1px solid black;background-color:#add8e6;text-align:center;font-weight:bold;'><br><br><br><br><br>MRI</div>
	<div id='mri_data' style='position:absolute;left:53px;top:0px;width:calc(100% - 53px);height:100%;border:1px solid #849dbd;'></div>
</div>
<div id='pet_list' style='position:absolute;height:130px;border:0px solid #849dbd;'>
	<div style='position:absolute;left:0px;top:0px;width:50px;height:100%;border:1px solid black;background-color:#add8e6;text-align:center;font-weight:bold;'><br><br><br><br>PET-<br>amyloid</div>
	<div id='pet_data' style='position:absolute;left:53px;top:0px;width:calc(100% - 53px);height:100%;border:1px solid #849dbd;'></div>
</div>
<div id='csf_list' style='position:absolute;height:125px;border:0px solid #849dbd;'>
	<div style='position:absolute;left:0px;top:0px;width:50px;height:100%;border:1px solid black;background-color:#add8e6;text-align:center;font-weight:bold;'><br><br><br><br>CSF</div>
	<div id='csf_data' style='position:absolute;left:53px;top:0px;width:calc(100% - 53px);height:100%;border:1px solid #849dbd;'></div>
</div>

<div id='info_data' style='position:absolute;top:40px;width:485px;height:calc(100% - 55px);border:0px solid #E0E0E0;'>
	<iframe id='info_iframe' style='width:100%;height:100%;' frameborder='0'></iframe>
</div>

<div id='sheet_popup' style='position:absolute;left:0px;top:0px;width:630px;height:720px;background-color:#FFFFFF;border:2px solid #858585;overflow:hidden;display:none;z-index:9999999'>
	<div id='popup_header' style='width:100%;height:20px;line-height:20px;color:white;font-weight:bold;background-color:#bcbcbc;border-bottom:1px solid #858585;cursor:all-scroll;'>&nbsp;[TAB]</div>
	<div id='popup_close' style='position:absolute;right:5px;top:3px;font-weight:bold;cursor:pointer;'>x</div>

	<iframe id='popup_iframe' style='width:100%;height:700px;' frameborder='0'></iframe>

</div>