<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/common.inc"%>
<jsp:include page='<%=incInit%>' flush="false"></jsp:include>
<%
	String msg = (String) request.getAttribute("msg");
%>
<script>
	$(function(){
		check_login(); //로그인체크

		parent.err(<%=msg%>);
	});
</script>

