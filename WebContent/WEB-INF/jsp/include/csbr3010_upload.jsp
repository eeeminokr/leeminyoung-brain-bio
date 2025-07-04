<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/common.inc"%>
<head>
<!--<link rel="shortcut icon" href="/csbrain_web/favicon.ico" type="image/x-icon">-->
</head>
<jsp:include page='<%=incInit%>' flush="false"></jsp:include>
<script>

   $(function(){
      
      print_head("PET 엑셀 업로드");
      check_login(); //로그인체크

      $("#btn_upload").click(function(){
    	  
    	  if ( $("#upfile").val() == '' ) {
    	         alert('첨부파일을 선택해주세요.');
    	         return false;
    	  }
    	  
    	  var filename = $("#upfile").val();
    	  var extension = filename.substr(filename.lastIndexOf('.') + 1);
    	  
    	  if ( extension != "xls" ) {
    	         alert('엑셀파일(확장자 .xls)을 선택해주세요');
    	         $("#upfile").val('');
    	         return false;
    	  }
    	  
    	  if(confirm("엑셀 파일을 업로드하시겠습니까?")){
    	         
    	         $("#btn_upload").hide();
    	         $("#loading").show();
    	         
    	 	     //$('#upload').submit();
    	 	     
    	         var frameName = 'unique';  //generate a random name

    	         var $frame = $('<iframe>', {
    	           src: "javascript: false;",
    	           name: frameName
    	         }).appendTo('body').hide();; // Add iframe to body and hide

    	         var form = document.forms.upload;
    	         form.target = frameName;  //set form target to iframe
    	         
    	         form.submit();

    	         $frame.load(function(e) {
    	           var doc = getDoc($frame[0]);
    	           var docRoot = doc.body ? doc.body : doc.documentElement;
    	           var data = docRoot.innerHTML;
    	           
    	           if(data.indexOf('"success":true') != -1){
    	        	   alert("엑셀 업로드가 완료되었습니다.");
    	           }else{
    	        	   alert("엑셀 업로드에 실패하였습니다. 다시 시도해주세요.");
    	           }
    	           
    	           $("#btn_upload").show();
      	           $("#loading").hide();
      	           
    	           close();
    	         });
    	       
    	  }
    
   });
 });


   function frameLoading(){
	   console.log("eeeeee 프레임로딩이잉");
	   
   }
   
   function err(msg) {
      alert(msg);
      $("#loading").hide();
      $("#btn_upload").show();
   }
   
   function getDoc(frame) {
	   var doc = null;

	   // IE8 cascading access check
	   try {
	     if (frame.contentWindow) {
	       doc = frame.contentWindow.document;
	     }
	   } catch(err) {
	   }

	   if (doc) { // successful getting content
	     return doc;
	   }

	   try { // simply checking may throw in ie8 under ssl or mismatched protocol
	     doc = frame.contentDocument ? frame.contentDocument : frame.document;
	   } catch(err) {
	     // last attempt
	     doc = frame.document;
	   }
	   return doc;
	 }
</script>
<body>
<form name='upload' id='upload' method='post' action='<%=request.getContextPath()%>/include/csbr3010_upload_excel.do' enctype='multipart/form-data' >
<div style='width:100%;height:30px;line-height:30px;text-align:center;background-color:#87cefa;'>PET 엑셀 업로드</div>
<div style='width:97%;margin-left:1%;height:60px;line-height:30px;padding:3px;text-align:center;color:red;'>
1,000건당 약 2~3분의 시간이 소요되니<br>완료될때까지 기다려 주세요.
</div>
<div style='text-align:center;'>
   <input type='file' id='upfile' name='upfile' style='width:90%;' multiple="multiple">
   <br><br><br>
   <input id='btn_upload' type='button' value='upload'>
   <img id='loading' src='../images/loading.gif' style='display:none;'>
</div>
</form>
<iframe id='upload_iframe' name='upload_iframe' style='width:100%;height:500px;display:none;'></iframe>
</body>
</html>