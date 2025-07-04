<%@page import="csbrain.data.service.Csbr1031SearchVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/common.inc"%>
<div class='grid_header'>
	<ul>
		<li style='width:40px;text-align:center;'>#</li>
		<li style='width:50px;text-align:center;'>차수</li>
		<li style='width:60px;text-align:center;'>ID</li>
		<li style='width:90px;text-align:center;'>이름</li>
		<li style='width:90px;text-align:center;'>검진(DM)일</li>
		<li style='width:90px;text-align:center;'>MRIID</li>
		<li style='width:90px;text-align:center;'>SNSB</li>
		<li style='width:90px;text-align:center;'>검사일</li>
		<li style='width:90px;text-align:center;'>검사자</li>
       	<li style='width:90px;text-align:center;'>PDF여부</li>
		<li style='width:90px;text-align:center;'>진단여부</li>
		<li style='width:90px;text-align:center;'>진료소견</li>
	    <li style='width:40px;text-align:center;'>Normal</li>
		<li style='width:50px;text-align:center;'>SMI</li>
		<li style='width:60px;text-align:center;'>MCI</li>
		<li style='width:90px;text-align:center;'>aMCI-SD</li>
		<li style='width:90px;text-align:center;'>aMCI-SD</li>
		<li style='width:90px;text-align:center;'>naMCI-SD</li>
		<li style='width:90px;text-align:center;'>naMCI-MD</li>
		<li style='width:90px;text-align:center;'>VCI</li>
		<li style='width:90px;text-align:center;'>Dementia</li>
       	<li style='width:90px;text-align:center;'>AD</li>
		<li style='width:90px;text-align:center;'>VAD</li>
		<li style='width:90px;text-align:center;'>mixed</li>
		<li style='width:50px;text-align:center;'>LBD_DLB</li>
		<li style='width:60px;text-align:center;'>LBD_PDD</li>
		<li style='width:90px;text-align:center;'>FTD_PNFA</li>
		<li style='width:90px;text-align:center;'>FTD_SD</li>
		<li style='width:90px;text-align:center;'>FTD-BvFTD</li>
		<li style='width:90px;text-align:center;'>Alcohol_D</li>
		<li style='width:90px;text-align:center;'>NPH</li>
		<li style='width:90px;text-align:center;'>unspecified</li>
       	<li style='width:90px;text-align:center;'>depression</li>
		<li style='width:90px;text-align:center;'>SNSB기타</li>
		<li style='width:90px;text-align:center;'>MRI기타</li>
	</ul>
</div>
<div class='grid_content'>
	<c:forEach var="list" items="${list}" varStatus="status">
		<ul row_data='${list.objectIdx}|${list.selectionNum}'>
			<li style='text-align:center;'>${status.index + 1}</li>
			<li style='text-align:center;'>${list.selectionNum}</li>
			<li style='text-align:center;'>${list.objectIdx}</li>
			<li style='text-align:center;'>${list.objectNm}</li>
			<li style='text-align:center;'>${list.testDay}</li>
			<li style='text-align:center;'>${list.mriid}</li>
			<li style='text-align:center;'>${list.snsbdate}</li>
			<li style='text-align:center;'>${list.diagDay}</li>
			<li style='text-align:center;'>${list.subjectNm}</li>
			<li style='text-align:center;'>${list.pdfPath}</li>
			<li style='text-align:center;'>${list.diagyn}</li>
			<li style='text-align:center;'>${list.remark}</li>
			<li style='text-align:center;'>${list.normal}</li>
			<li style='text-align:center;'>${list.smi}</li>
			<li style='text-align:center;'>${list.mci}</li>
			<li style='text-align:center;'>${list.amciSd}</li>
			<li style='text-align:center;'>${list.amciMd}</li>
			<li style='text-align:center;'>${list.namciSd}</li>
			<li style='text-align:center;'>${list.namciMd}</li>
			<li style='text-align:center;'>${list.vci}</li>
			<li style='text-align:center;'>${list.dementia}</li>
			<li style='text-align:center;'>${list.ad}</li>
			<li style='text-align:center;'>${list.vad}</li>
			<li style='text-align:center;'>${list.mixed}</li>
			<li style='text-align:center;'>${list.dlb}</li>
			<li style='text-align:center;'>${list.pdd}</li>
			<li style='text-align:center;'>${list.pnfa}</li>
			<li style='text-align:center;'>${list.sd}</li>
			<li style='text-align:center;'>${list.bvftd}</li>
			<li style='text-align:center;'>${list.alcohol}</li>
			<li style='text-align:center;'>${list.nph}</li>
			<li style='text-align:center;'>${list.unspecified}</li>
			<li style='text-align:center;'>${list.depression}</li>
			<li style='text-align:center;'>${list.snsbRemark}</li>
			<li style='text-align:center;'>${list.mriRemark}</li>
		</ul>
	</c:forEach>
</div>