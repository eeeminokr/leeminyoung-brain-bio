<%@page import="java.util.LinkedHashMap"%>
<%@page import="csbrain.common.util.StringUtil"%>
<%@page import="java.lang.reflect.Method"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class='grid_header'>
	<ul>
<%
	List<LinkedHashMap<String, Object>> list = (List<LinkedHashMap<String, Object>>) request.getAttribute("list");
	String n = "";
	int w = 0;


/*for(LinkedHashMap<String, Object> maps:  list ) {
	 for (Entry<String, Object> entry : maps.entrySet()) {
           out.println("[key(jsp쪽------------)]:" + entry.getKey() + ", [value]:" + entry.getValue());
       }
	String test = (String)request.getAttribute("test");
	System.out.println(test+"test!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	*/ 
	if(list.size()!=0) {
	 	for (String key : list.get(0).keySet()) {	
	 		w = key.length() * 15;
	 		if ( w < 100 ) w = 100;
			if ( w > 250 ) w = 250;
			if("obejct_idx".equals(key)) w= 500;
%>
		<li style='width:<%=w%>px;text-align:center;'><%=key%></li>

<%
		}
	}
%>

	</ul>
</div>
<div class='grid_content'>
<%
	for(int i=0; i<list.size(); i++){
%>
		<ul row_data='<%=StringUtil.NVL(list.get(i).get("object_idx"))%>'>
<%
		for (String key : list.get(i).keySet()) {
%>
			<li><%=StringUtil.NVL(list.get(i).get(key))%></li>
<%
		}
%>
		</ul>
<%
	}
%>

</div>
<div id='grid_page'>
<%
	int intPage = Integer.parseInt((String) request.getAttribute("page"));	
	System.out.println(intPage+"intPage넘어옴");
	int intTotalPage = (int) request.getAttribute("total_page");
	int ppage = intPage - 1;
	int npage = intPage + 1;
	if ( intPage ==  1 ) out.print("<span style='color:lightgray;'>◀ 이전</span>");
	else out.print("<span style='color:blue;cursor:pointer;' page='" + ppage + "'>◀ 이전</span>");
	out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
	out.print("[현재페이지:" + intPage + "/" + intTotalPage + "]");
	out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
	if ( intPage >= intTotalPage ) out.print("<span style='color:lightgray;'>▶ 다음</span>");
	else out.print("<span style='color:blue;cursor:pointer;' page='" + npage + "'>▶ 다음</span>");
%>
</div>
