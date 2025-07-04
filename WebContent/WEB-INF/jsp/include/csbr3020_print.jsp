<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.lang.reflect.Method"%>
<%@page import="java.util.List"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="csbrain.common.util.DateUtil"%>
<%@page import="csbrain.common.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/common.inc"%>
<jsp:include page='<%=incInit%>' flush="false"></jsp:include>
<script>
	$(function(){
		check_login(); //로그인체크

		check_auth("csbr3010"); //권한체크
	});
</script>

<%
	String xls = request.getParameter("xls");
	if(!StringUtil.isEmptyString(xls)){
		//엑셀다운로드하는 부분
		//찍기 시작
		response.setHeader("Content-type", "application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=" + StringUtil.encodeURIComponent("Excel_pet_data_") + DateUtil.getSimpleDate("yyyyMMdd") + ".xls");
		response.setHeader("Content-Description", "JSP Generated Data");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");

		try{
			PrintWriter writer = response.getWriter();
			writer.print('\ufeff'); //BOM
		}catch(Exception e){
			e.printStackTrace();
		}
%>
<!-- 헤더 찍기 -->
<html xmlns:x='urn:schemas-microsoft-com:office:excel'>
	<head>
		<meta http-equiv='content-type' content='text/plain; chartset=utf-8'>
	</head>
	<body>
<%
	}else{
%>
	<script>
		print_head("Excel_pet_data_", new Array('print.js'), new Array('print.css'));
	</script>
	<body>
<%  } %>

<%
	List<LinkedHashMap<String, Object>> list = (List<LinkedHashMap<String, Object>>) request.getAttribute("list");
	String n = "";
	int w = 0;
	String s = "";
%>
<table width='100%' border='1'>
	<tr>
		<%
		for (String key : list.get(0).keySet()) {
	 		w = key.length() * 15;
	 		if ( w < 100 ) w = 100;
			if ( w > 250 ) w = 250;
			if("Remark".equals(key)) w= 500;
		%>
				<td><%=key%></td>
		<%
		}
		%>
	</tr>
<%
	for(int i=0; i<list.size(); i++){
		%>
		<tr>
		<%
		for (String key : list.get(i).keySet()) {
			if("Jumin".equals(key)) s = "style='mso-number-format:\"@\";'";
			else s = "";
			%>
			<td <%=s%>><%=StringUtil.NVL(list.get(i).get(key))%></td>
			<%
		}
		%>
		</tr>
<%
	}
%>
</table>
</body>
</html>