<%@page import="csbrain.data.service.MbResebsVO"%>
<%@page import="java.util.List"%>
<%@page import="csbrain.common.util.DateUtil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="csbrain.common.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/common.inc"%>
<jsp:include page='<%=incInit%>' flush="false"></jsp:include>
<script>
	$(function(){
		check_login(); //로그인체크

		check_auth("csbr2090"); //권한체크
	});
</script>

<%
// "d"는 종료 파라미터값.
String d = request.getParameter("d");
	if(StringUtil.isEmptyString(d)){
		out.close();
	}

	String dtext = DateUtil.getSimpleDate("yyyy-MM-dd", d);
	dtext = dtext + "(" + DateUtil.getDayOfWeek(d) + ")";

	d = DateUtil.getSimpleDate("yyyyMMdd", d);

	String xls = request.getParameter("xls");
	if(!StringUtil.isEmptyString(xls)){

		//찍기 시작 -- 엑셀파일에 대한 content-type
		response.setHeader("Content-type", "application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=" + StringUtil.encodeURIComponent("버칼예약리스트_") + d + ".xls");
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
		print_head("버칼예약리스트(<%=d%>)", new Array('print.js'), new Array('print.css'));
	</script>
	<body>
<%  } %>

<h2 style='font-family:궁서;text-align:center;'><%=dtext%> 버칼예약관리정보</h2>
<table width='100%' border='1'>
	<tr>
		<td style='background-color:#E0E0E0;'>ID</td>
		<td style='background-color:#E0E0E0;'>방문시각</td>
		<td style='background-color:#E0E0E0;'>이름</td>
		<td style='background-color:#E0E0E0;'>주민번호</td>
		<td style='background-color:#E0E0E0;'>생년월일</td>
		<td style='background-color:#E0E0E0;'>성별</td>
		<td style='background-color:#E0E0E0;'>집전화</td>
		<td style='background-color:#E0E0E0;'>휴대전화</td>
		<td style='background-color:#E0E0E0;'>SNSB</td>
		<td style='background-color:#E0E0E0;'>BSID</td>
		<td style='background-color:#E0E0E0;'>CUDC</td>
		<td style='background-color:#E0E0E0;'>참석</td>
		<td style='background-color:#E0E0E0;'>차수</td>
		<td style='background-color:#E0E0E0;'>촬영여부</td>
		<td style='background-color:#E0E0E0;'>채취여부</td>
		<td style='background-color:#E0E0E0;'>통합차수</td>
		<td style='background-color:#E0E0E0;'>비고</th>
		<td style='background-color:#E0E0E0;'>동의</td>
		<td style='background-color:#E0E0E0;'>사진</td>
		<td style='background-color:#E0E0E0;'>혈액</td>
		<td style='background-color:#E0E0E0;'>담당자</td>
		<td style='background-color:#E0E0E0;'>CD</td>
		<td style='background-color:#E0E0E0;'>판독</td>
		<td style='background-color:#E0E0E0;'>상담</td>
		<td style='background-color:#E0E0E0;'>담당자</td>
		<td style='background-color:#E0E0E0;'>진단</th>
	</tr>
<%
	List<MbResebsVO> list = (List<MbResebsVO>) request.getAttribute("list");
	String testyn, bsyn, bloodyn;

	if(list != null){
		for(int i=0; i<list.size(); i++){

			if("0".equals(StringUtil.NVL(list.get(i).getTestyn()))) testyn = "&nbsp;"; else testyn = "V";
			if("0".equals(StringUtil.NVL(list.get(i).getBsyn()))) bsyn = "&nbsp;"; else bsyn = "V";
			if("0".equals(StringUtil.NVL(list.get(i).getBloodyn()))) bloodyn = "&nbsp;"; else bloodyn = "V";

%>
		<tr>
			<td ><%=StringUtil.NVL(list.get(i).getObjectIdx())%></td>
			<td ><%=StringUtil.NVL(list.get(i).getResetime()).substring(0,2) + ":" + StringUtil.NVL(list.get(i).getResetime()).substring(2,4)%></td>
			<td ><%=StringUtil.NVL(list.get(i).getResename())%></td>
			<td ><%=StringUtil.NVL(list.get(i).getJumin())%></td>
			<td ><%=StringUtil.NVL(list.get(i).getBirth())%></td>
			<td ><%=StringUtil.NVL(list.get(i).getGender())%></td>
			<td ><%=StringUtil.NVL(list.get(i).getTelNo())%></td>
			<td ><%=StringUtil.NVL(list.get(i).getMobile())%></td>
			<td ><%=StringUtil.NVL(list.get(i).getSnsbdate())%></td>
			<td ><%=StringUtil.NVL(list.get(i).getBsid())%></td>
			<td ><%=StringUtil.NVL(list.get(i).getCudc())%></td>
			<td ><%=testyn%></td>
			<td ><%=StringUtil.NVL(list.get(i).getSelectionNum())%></td>
			<td ><%=bsyn%></td>
			<td ><%=bloodyn%></td>
			<td ><%=StringUtil.NVL(list.get(i).getIntegrationNum())%></td>
			<td style='text-align:left;'><%=StringUtil.NVL(list.get(i).getRemark())%></td>
			<td ><%=StringUtil.NVL(list.get(i).getAgree())%></td>
			<td ><%=StringUtil.NVL(list.get(i).getPhoto())%></td>
			<td ><%=StringUtil.NVL(list.get(i).getBlood())%></td>
			<td ><%=StringUtil.NVL(list.get(i).getDamdang1())%></td>
			<td ><%=StringUtil.NVL(list.get(i).getCdrom())%></td>
			<td ><%=StringUtil.NVL(list.get(i).getReading())%></td>
			<td ><%=StringUtil.NVL(list.get(i).getConsult())%></td>
			<td ><%=StringUtil.NVL(list.get(i).getDamdang2())%></td>
			<td ><%=StringUtil.NVL(list.get(i).getDiagnose())%></td>
		</tr>
<%
		}
	}
%>
</table>
</body>
</html>