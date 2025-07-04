<%@page import="csbrain.common.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/common.inc"%>
<jsp:include page='<%=incInit%>' flush="false"></jsp:include>
<script>
	$(function(){
		print_head("CSBRAIN_LOGIN", "login.js", "login.css");

		var saveid = null; //CSBRAIN_SAVE_ID;

		if( saveid != null && saveid != "" ){
			$("#userid").val(saveid);
			$("#userpass").focus();
			$("#saveid").prop("checked", true);
		}else{
			$("#userid").focus();
		}

		$("#btn_csdn").click(function(){
			location.replace('dn.jsp?f=connector');
		});

		$("#btn_netdn").click(function(){
			location.replace('dn.jsp?f=net40');
		});
	});
</script>
<body>
<div id='container'>
	<div id='login_box'>
		<input type='text' id='userid' value='' style='width:213px; padding:6px;'>
		<input type='password' id='userpass' value='' style='width:213px; padding:6px;'> 
		<div id='saveidbox'><input id='saveid' type='checkbox' value='1'> <label for='saveid' style='font-weight:bold;'>ID 저장</label></div>
		<img id='btn_login' src='images/login/login_btn_login.png'>
		<img id='btn_cancel' src='images/login/login_btn_cancel.png'>

		<img src='images/login/csdn2.png' id='btn_csdn' style='position:absolute;left:790px;top:340px;cursor:pointer;' title='접속프로그램 다운로드'>
		<img src='images/login/netdn2.png' id='btn_netdn' style='position:absolute;left:790px;top:360px;cursor:pointer;' title='.NET Framework 4.0 다운로드'>
	</div>
</div>

</body>
</html>