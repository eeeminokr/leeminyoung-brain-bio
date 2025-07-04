<%@page import="csbrain.common.service.MbSJVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/common.inc"%>
<div class='grid_header'>
	<ul>
		<li style='width:110px;text-align:center;'>검사명</li>
		<li style='width:40px;text-align:center;'>차수</li>
		<li style='width:90px;text-align:center;'>검진일</li>
		<li style='width:50px;text-align:center;'>총점</li>
		<li style='width:90px;text-align:center;'>검사자</li>
	</ul>
</div>
<div class='grid_content'>
	<c:forEach var="list" items="${sunbyul_list}" varStatus="status">
		<ul row_data='${list.examId}|${list.examIdx}|${list.examNum}|${list.testday}'>
			<li style='text-align:left;'>${list.examNm}</li>
			<li style='text-align:center;'>${list.examNum}</li>
			<li style='text-align:center;'>${list.testday}</li>
			<li style='text-align:center;'>${list.total}</li>
			<li style='text-align:center;'>${list.subjectNm}</li>
		</ul>
	</c:forEach>
</div>


		


