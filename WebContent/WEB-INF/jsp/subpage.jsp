<%@page import="csbrain.main.controller.MainController"%>
<%@page import="csbrain.common.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/common.inc"%>
<jsp:include page='<%=incInit%>' flush="false"></jsp:include>
<%
	String menu_id = request.getParameter("menu_id");
	if(StringUtil.isEmptyString(menu_id)){
		out.flush();
	}
	String include_file =  "/include/" + menu_id + ".see?menu_id=" + menu_id;
%>
<script> 
	$(function(){
		print_head("CsBrain 2.0", new Array('subpage.js','mygrid.min.js','tab.js', 'jquery.mask.js'), new Array('subpage.css','mygrid.css','tab.css'));
		let menu_id = '<%=menu_id%>';
		//const MENUNAME = check_auth(menu_id);
		//기록
		if ( menu_id != "" ) { 
			save_start_log(menu_id);
		}
	});
</script>
<body style='background-color:#EFEFEF;'>
<div id='container'>
	<div id='bodycontainer'>
		<jsp:include page='<%=include_file%>' flush="false"></jsp:include>
	</div>
	<!--<div id='bodytitle'>
		<div id='subtitle'><b><%--MENUNAME--%></b>(<%--menu_id--%>)</div>
		<div id='sub_close' style='position:absolute;right:10px;top:10px;cursor:pointer;'><b>X</b></div>
	</div>-->
</div>
</body>
</html>