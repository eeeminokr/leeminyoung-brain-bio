<%@page import="java.sql.Date"%>
<%@page import="csbrain.common.util.StringUtil"%>
<%@page import="csbrain.common.util.DateUtil"%>
<%@page import="csbrain.common.service.MbDmVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/common.inc"%>
<jsp:include page='<%=incInit%>' flush="false"></jsp:include>
<%
	List<MbDmVO> dm_list = (List<MbDmVO>) request.getAttribute("dm_list");


	MbDmVO dmvo = null;


	if( dm_list != null && dm_list.size() > 0 ){
		dmvo = dm_list.get(0);
	}

%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/target/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/target/lib_jquery.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
	$(function(){
		print_head("");

		check_login(); //로그인체크

	});
</script>
<style>
	.rbox {
		border:0px solid red;
		
	position: absolute;
	}

	/* .r {
	 position: relative;
	} */
	
	.tn {
		/* position:absolute !important;
		margin:0px !important;
		border:2px solid  rgb(96, 157, 190) !important;
		border-radius: 5px !important;
		text-align:left;
		font-weight:normal;
		height:25px;
		overflow:hidden;
		background-color:transparent  !important;
		color:blue !important;
		box-sizing: border-box;
		padding-left:1px; */
		width: 60px;
		
	}
	
	.t {
		/* position:absolute !important;
		margin:0px !important;
		border:2px solid  rgb(96, 157, 190) !important;
		border-radius: 5px !important;
		text-align:left;
		font-weight:normal;
		height:25px;
		overflow:hidden;
		background-color:transparent  !important;
		color:blue !important;
		box-sizing: border-box;
		padding-left:1px; */
		width: 60px;
		
	}
 #tabbox {

 /*	background-size: cover;
	*/
	width:608px;
	height: 3000px;
	background-repeat: no-repeat;
	/*
  	background-color: #e0e0e0;
	*/
	}	
	

.csbr1010btn {
	box-shadow:inset 0px 1px 0px 0px #ffffff;
	background:linear-gradient(to bottom, #f9f9f9 5%, #e9e9e9 100%);
	background-color:#f9f9f9;
	border-radius:6px;
	border:1px solid #dcdcdc;
	display:inline-block;
	cursor:pointer;
	color:#666666;
	font-family:Arial;
	font-size:12px;
	font-weight:bold;
	margin: 3px 10px 0px 0px;
	float: right;
	width: 65px;
	height: 28px;
	text-decoration:none;
	text-shadow:0px 1px 0px #ffffff;
}
.csbr1010btn:hover {
	background:linear-gradient(to bottom, #e9e9e9 5%, #f9f9f9 100%);
	background-color:#e9e9e9;
}
.csbr1010btn:active {
	position:relative;
	top:1px;
}
	
.table.type02 tbody th.ta_l {padding-left:40px;line-height:18px;}
.table.type02 tbody .table th, .table.type02 tbody .table td {text-align:left;border-bottom:0;}
.table.type02 tbody .table tr:first-child td, .table.type02 tbody .table thead tr:first-child th {border-top:0;}
.table.type02 tbody .table tbody tr:last-child th, .table.type02 tbody .table tbody tr:last-child td {border-bottom:0;}
.table.type02 tbody .table thead tr th:last-child, .table.type02 tbody .table tbody tr td:last-child {border-right:0;}
.table.type02 tbody .table input[type="text"] {margin:0 3px;}
.table.type02 tbody .table .ans input[type="text"] {margin:2px 3px;}
.table.type02 tbody .table .ans input[type="button"] {border-radius:6px;font-size:12px;height:24px;margin:2px 3px;width:100px;border:1px solid #999;vertical-align:middle;cursor:pointer;}
.ans label {display:inline-block;vertical-align:middle;width:80px;}	
	
</style>

<script>	
$(function(){
	var idx = $('#idx').val();
	console.log(idx);
	
	var action = $('#action').val();
	if(action =='hidden'){
	     $('#btnArea').css({"visibility": "hidden"});
	}
	
	$("input[type=radio]").each(function(){  
		 var chk = $(this).is(":checked");
	     var name = $(this).attr('name');    
	if(chk == true) $("input[name='"+name+"']").data("previous",$(this).val());
	
		});
	
	$("input[type=radio]").click(function(){    
		var pre = $(this).data("previous");    
		var chk = $(this).is(":checked");    
		var name = $(this).attr('name');    
		if(chk == true && pre == $(this).val()){        
		
			$(this).prop('checked',false);        
			$("input[name='"+name+"']").data("previous",'');    
		
		}else{        
			if(chk == true) $("input[name='"+name+"']").data("previous",$(this).val());    		
		}
		
	});
	
	$('input:text[id="num"]').keyup(function() {
		var regNumber = /^[0-9]*$/;
		var input = $(this).val();
		var maxLimit = $(this).attr("maxlength"); 
		var minLimit = $(this).attr("minlength");
		if(!regNumber.test(input)){
	        alert("숫자만 입력가능합니다");
		//	$('input:text[id="num"]').val(input.replace(/[^0-9]/g , ""));
			}
		if(this.value.length >= maxLimit){	
			$(this).next('input:text[id="num"]').focus();
			return false;
		}else if(this.value.length < minLimit){
			$(this).focus();
			return false;
		}
	
		});
	
	$("#new").click(function() {
		if(idx == ''){
			alert("검색항목을 입력해주세요");
			return false;

		}
		
		var obj =  $('#object_idx').val();
		var obj2 = $('#idx').val();

		var objParams = {
			
				"obj" : obj
		};
		
		//ajax호출
		$.ajax({
			url         :   getContextPath()+"/include/sheet/view_from_object_to_dm.do",
            dataType    :   "json",
            contentType :   "application/x-www-form-urlencoded; charset=UTF-8",
            type        :   "post",
            data        :   objParams,
            success     :   function(json){
       			if(json.model.code =="OK"){
       				var name = json.model.dm04;
       				var mobile =  json.model.dm13;
       				var tel = json.model.dm14;
       				var add1 = json.model.dm15;
       				var add2 = json.model.dm16;
       				var add3 = json.model.dm17;
       				var add4 = json.model.dm18;
       				var gender = json.model.dm06;
       				
       				var yearAni = json.model.dm09;
       				var jumin1 = json.model.dm07R;
       				var jumin2 = json.model.dm07A;
       				var jumin3 = json.model.dm07B;
       				var juminS = json.model.dm08;
       				var birth = json.model.dm07C;
       				
       				$('input:text[name="dm04"]').val(name)
					$('input:text[name="dm13"]').val(mobile)
					$('input:text[name="dm14"]').val(tel)
					$('input:text[name="dm15"]').val(add1)
					$('input:text[name="dm16"]').val(add2)
					$('input:text[name="dm17"]').val(add3)
					$('input:text[name="dm18"]').val(add4)
					$('input:text[name="dm08"]').val(juminS)
					$('input:text[id="dm07C"]').val(birth)
					
// 					alert(birth)
					if(yearAni =='1'){
						$('input:radio[id="y1"]').prop('checked', true);
						
					}else if(yearAni == '2'){
						$('input:radio[id="y2"]').prop('checked', true);
					}else if(yearAni == '3'){
						$('input:radio[id="y3"]').prop('checked', true);
					}else if(yearAni == '4'){
						$('input:radio[id="y4"]').prop('checked', true);
					}else if(yearAni == '5'){
						$('input:radio[id="y5"]').prop('checked', true);
					}else if(yearAni == '6'){
						$('input:radio[id="y6"]').prop('checked', true);
					}else if(yearAni == '7'){
						$('input:radio[id="y7"]').prop('checked', true);
					}else if(yearAni == '8'){
						$('input:radio[id="y8"]').prop('checked', true);
					}else if(yearAni == '9'){
						$('input:radio[id="y9"]').prop('checked', true);
					}else if(yearAni == '10'){
						$('input:radio[id="y10"]').prop('checked', true);
					}else if(yearAni == '11'){
						$('input:radio[id="y11"]').prop('checked', true);
					}else if(yearAni == '12'){
						$('input:radio[id="y12"]').prop('checked', true);
					}
       				
       				if(gender =='1'){
       					$('input:radio[id="g1"]').prop('checked', true);
						
					}else if(gender == '2'){
						$('input:radio[id="g2"]').prop('checked', true);
					}
					//$('input:text[name="dm06"]').val(gender)
					//$('input:text[name="dm09"]').val(yearAni)
					$('input:text[name="dm07R"]').val(jumin1)
					$('input:text[name="dm07A"]').val(jumin2)
					$('input:text[name="dm07B"]').val(jumin3)
       							
				
					var now = new Date();
		  			var year = now.getFullYear();
		  
		  			
		  			var years = year - jumin1
					$("input[name='dm10']").val(years);
				
					//	$("input[name='dm07A']").focus(); 
       			}else{
       				alert("등록을 실패하였습니다 다시시도해주세요!")
       			} 		
            },
            error       :   function(request, status, error){
                console.log("AJAX_ERROR");
            }
	
		});
		
		
		
		
		
		if($('#insert').css("display")=="none"){
			$('#insert').show();	
		}
	$('input:text[name="sdate"]').attr("disabled", false);
	$('input:radio[class="r"]').attr("disabled", false);
	$('input:text[class="t"]').attr("disabled", false);
	//$('input:text[name="ddate"]').attr("disabled", false);
	 $('input[class="r"]').removeAttr('checked');
	
		 
		 $('input[class="t"]').val('');

	 $('input[class="t"]').val('');
	 $('input[name="sdate"]').val('');
//	$('input:radio[class="r"]:not(:checked)').attr("disabled", false);
//	$('input:text[class="t"]').attr("disabled", false);	
//   	 $(this).prop('checked', false);
	

		var dm13 = $('input[name="dm13"]').val();
		var dm14 = $('input[name="dm14"]').val();
		
		console.log(dm13+"::dm13");
		console.log(dm14+"::dm14")
		
   	 	var selnew = $("#new").val(); 
   	 	console.log(selnew+"::selenew")
		checking = $('#chk').val(selnew);
	});

	$("#edit").click(function() {
		if(idx == ''){
			alert("검색항목을 입력해주세요");
			return false;
		
		}
	
		
		if($('#insert').css("display")=="none"){
			$('#insert').show();	
		}
	$('input:text[name="sdate"]').attr("disabled", false);
	console.log($(".t").attr("disabled"));
	
	$('input:radio[class="r"]:not(:checked)').attr("disabled", false);
	$('input:text[class="t"]').attr("disabled", false);
//	$('input:text[name="ta3"]').attr("disabled", false);	
   	 $(this).prop('checked', false);

		// $('input:radio[name="r2"]').attr("disabled", true); //비활성화

		
   	 	var selnew = $("#edit").val(); 
   	 	console.log(selnew+"::selenew")
		checking = $('#chk').val(selnew);
	});
	
	
	$('input:radio[name="r1"]').click(function() {
		
		var r1val = $(this).val();
		//	 $('input:radio[class="r"]:not(:checked)').attr("disabled", true); //비활성화
			 $('input:radio[name="tbc3"]').attr("disabled", true);
			 $('input:radio[name="tcc3"]').attr("disabled", true);
			 $('input:radio[name="r1"]:not(:checked)').attr("disabled", false);	
		
			
				setTimeout(function() {
				 $('input:text[name="tac3"]').attr("disabled",false);
				 $('input:text[name="tac3"]').focus();	
					},0);
			
		});		
	
	$('input:radio[name="r1"]').change(function() {
		var r1val = $(this).val();
		 $('input:radio[name="r1"]:not(:checked)').attr("disabled", false);	
		$('input:text[name="tac3"]').val('');

	});
	
	
	
	$('input:radio[name="dm29"]').click(function() {
		
		var r1val = $(this).val();
		//	 $('input:radio[class="r"]:not(:checked)').attr("disabled", true); //비활성화
			 $('input:radio[name="tac3"]').attr("disabled", true);
			 $('input:radio[name="tcc3"]').attr("disabled", true);
			 $('input:radio[name="dm29"]:not(:checked)').attr("disabled", false);	
		
			if(r1val =='1'){	
				setTimeout(function() {
					 $('input:radio[name="dm22"]').attr("disabled", false); 
					 $('input:radio[name="dm22A"]').attr("disabled", false); 
					 $('input:radio[name="dm22B"]').attr("disabled", false); 
					 $('input:radio[name="dm22C"]').attr("disabled", false); 
					 $('input:radio[name="dm22D"]').attr("disabled", false); 
					 $('input:radio[name="dm22E"]').attr("disabled",false); 
					 $('input:radio[name="dm22F"]').attr("disabled",false); 
					 $('input:radio[name="dm22G"]').attr("disabled",false); 
					 $('input:radio[name="dm22H"]').attr("disabled",false); 						
					},0);
			}else{
				 $('input:radio[name="dm22"]').attr("disabled", true); 
				 $('input:radio[name="dm22A"]').attr("disabled", true); 
				 $('input:radio[name="dm22B"]').attr("disabled", true); 
				 $('input:radio[name="dm22C"]').attr("disabled", true); 
				 $('input:radio[name="dm22D"]').attr("disabled", true); 
				 $('input:radio[name="dm22E"]').attr("disabled",true); 
				 $('input:radio[name="dm22F"]').attr("disabled",true); 
				 $('input:radio[name="dm22G"]').attr("disabled",true); 
				 $('input:radio[name="dm22H"]').attr("disabled",true); 
				
			}

		});	
	
	$('input:radio[name="dm22"]').click(function() {
		
		var r1val = $(this).val();
		//	 $('input:radio[class="r"]:not(:checked)').attr("disabled", true); //비활성화
			 $('input:radio[name="tac3"]').attr("disabled", true);
			 $('input:radio[name="tbc3"]').attr("disabled", true);
			 $('input:radio[name="dm22"]:not(:checked)').attr("disabled", false);	
		
			if(r1val =='1'){			
				if($('input:radio[name="dm22"]').is(':checked')==true){
					setTimeout(function() {
						 $('input:radio[name="dm30"]').attr("disabled", true); 
						 $('input:radio[name="dm31"]').attr("disabled", true); 
						 $('input:radio[name="dm32"]').attr("disabled", true); 
						 $('input:text[name="dm33"]').attr("disabled", true); 
						 $('input:text[name="dm33A"]').attr("disabled", true); 
						 $('input:text[name="dm34"]').attr("disabled", true);  
						 $('input:text[name="dm34A"]').attr("disabled", true); 
						 $('input:text[name="dm35"]').attr("disabled", true);
						 $('input:radio[name="dm11"]').focus();	
						},100);	
				}
				
			}else{
				 $('input:radio[name="dm30"]').attr("disabled", false); 
				 $('input:radio[name="dm31"]').attr("disabled", false); 
				 $('input:radio[name="dm32"]').attr("disabled", false); 
				 $('input:text[name="dm33"]').attr("disabled", false); 
				 $('input:text[name="dm33A"]').attr("disabled", false); 
				 $('input:text[name="dm34"]').attr("disabled", false);  
				 $('input:text[name="dm34A"]').attr("disabled",false); 
				 $('input:text[name="dm35"]').attr("disabled",false);
			}

		});	
	
	
	$('input:radio[name="dm32"]').click(function() {
		
		var r1val = $(this).val();
		//	 $('input:radio[class="r"]:not(:checked)').attr("disabled", true); //비활성화
			 $('input:radio[name="tac3"]').attr("disabled", true);
			 $('input:radio[name="tbc3"]').attr("disabled", true);
			 $('input:radio[name="dm33"]:not(:checked)').attr("disabled", false);	
		
			if(r1val =='1'){			
				if($('input:radio[name="dm30"]').is(':checked')==true){
					setTimeout(function() {
						 $('input:text[name="dm33"]').val(''); 
						 $('input:text[name="dm33A"]').val(''); 
						 $('input:text[name="dm34"]').val('');  
						 $('input:text[name="dm34A"]').val(''); 
						 $('input:text[name="dm35"]').val('');
						
						 $('input:radio[name="dm31"]').attr("disabled", true); 
						 $('input:radio[name="dm32"]').attr("disabled", true); 
						 $('input:text[name="dm33"]').attr("disabled", true); 
						 $('input:text[name="dm33A"]').attr("disabled", true); 
						 $('input:text[name="dm34"]').attr("disabled", true);  
						 $('input:text[name="dm34A"]').attr("disabled", true); 
						 $('input:text[name="dm35"]').attr("disabled", true);
						 $('input:radio[name="dm11"]').focus();	
						},100);	
				}
				
			}else{
				 $('input:radio[name="dm32"]').attr("disabled", false); 
				 $('input:text[name="dm33"]').attr("disabled", false); 
				 $('input:text[name="dm33A"]').attr("disabled", false); 
				 $('input:text[name="dm34"]').attr("disabled", false);  
				 $('input:text[name="dm34A"]').attr("disabled",false); 
				 $('input:text[name="dm35"]').attr("disabled",false);
			}

		});	
	

	$('input:radio[name="dm29"]').change(function() {
		var getNm = $(this).attr('name');
		var r2val=	$(this).val();
		if( r2val == '1'){
			setTimeout(function() {
				 $('input:radio[name="dm22A"]').removeAttr('checked'); 
				 $('input:radio[name="dm22B"]').removeAttr('checked'); 
				 $('input:radio[name="dm22C"]').removeAttr('checked'); 
				 $('input:radio[name="dm22D"]').removeAttr('checked'); 
				 $('input:radio[name="dm22E"]').removeAttr('checked'); 
				 $('input:radio[name="dm22F"]').removeAttr('checked'); 
				 $('input:radio[name="dm22G"]').removeAttr('checked'); 
				 $('input:radio[name="dm22H"]').removeAttr('checked'); 
				 
				 $('input:radio[name="dm30"]').attr("disabled", true); 
				 $('input:radio[name="dm31"]').attr("disabled", true); 
				 $('input:radio[name="dm32"]').attr("disabled", true); 
				 $('input:text[name="dm33"]').attr("disabled", true); 
				 $('input:text[name="dm33A"]').attr("disabled", true); 
				 $('input:text[name="dm34"]').attr("disabled", true);  
				 $('input:text[name="dm34A"]').attr("disabled", true); 
				 $('input:text[name="dm35"]').attr("disabled", true);
				 $('input:radio[name="dm30"]').removeAttr('checked'); 
				 $('input:radio[name="dm31"]').removeAttr('checked'); 
				 $('input:radio[name="dm32"]').removeAttr('checked'); 
				 $('input:text[name="dm33"]').val(''); 
				 $('input:text[name="dm33A"]').val(''); 
				 $('input:text[name="dm34"]').val('');  
				 $('input:text[name="dm34A"]').val(''); 
				 $('input:text[name="dm35"]').val('');
				},0);

		}else{
			setTimeout(function() {
				
			 $('input:radio[name="dm22"]').removeAttr('checked'); 
			 
			 
			 $('input:radio[name="dm22"]').attr("disabled", true); 
			 $('input:radio[name="dm22A"]').attr("disabled", true); 
			 $('input:radio[name="dm22B"]').attr("disabled", true); 
			 $('input:radio[name="dm22C"]').attr("disabled", true); 
			 $('input:radio[name="dm22D"]').attr("disabled", true); 
			 $('input:radio[name="dm22E"]').attr("disabled",true); 
			 $('input:radio[name="dm22F"]').attr("disabled",true); 
			 $('input:radio[name="dm22G"]').attr("disabled",true); 
			 $('input:radio[name="dm22H"]').attr("disabled",true); 
			 
			 
			 $('input:radio[name="dm30"]').attr("disabled", false); 
			 $('input:radio[name="dm31"]').attr("disabled", false); 
			 $('input:radio[name="dm32"]').attr("disabled", false); 
			 $('input:text[name="dm33"]').attr("disabled", false); 
			 $('input:text[name="dm33A"]').attr("disabled", false); 
			 $('input:text[name="dm34"]').attr("disabled", false);  
			 $('input:text[name="dm34A"]').attr("disabled",false); 
			 $('input:text[name="dm35"]').attr("disabled",false);
			},0);
		}
	});
	
	$('input:radio[id="r5"]').change(function() {
		var getNm = $(this).attr('name');
		var r2val=	$(this).val();
		if(getNm == 'dm22'){
			setTimeout(function() {
				 $('input:radio[name="dm22A"]').removeAttr('checked'); 
				 $('input:radio[name="dm22B"]').removeAttr('checked'); 
				 $('input:radio[name="dm22C"]').removeAttr('checked'); 
				 $('input:radio[name="dm22D"]').removeAttr('checked'); 
				 $('input:radio[name="dm22E"]').removeAttr('checked'); 
				 $('input:radio[name="dm22F"]').removeAttr('checked'); 
				 $('input:radio[name="dm22G"]').removeAttr('checked'); 
				 $('input:radio[name="dm22H"]').removeAttr('checked'); 
				 
				 $('input:radio[name="dm30"]').attr("disabled", true); 
				 $('input:radio[name="dm31"]').attr("disabled", true); 
				 $('input:radio[name="dm32"]').attr("disabled", true); 
				 $('input:text[name="dm33"]').attr("disabled", true); 
				 $('input:text[name="dm33A"]').attr("disabled", true); 
				 $('input:text[name="dm34"]').attr("disabled", true);  
				 $('input:text[name="dm34A"]').attr("disabled", true); 
				 $('input:text[name="dm35"]').attr("disabled", true);
				 $('input:radio[name="dm30"]').removeAttr('checked'); 
				 $('input:radio[name="dm31"]').removeAttr('checked'); 
				 $('input:radio[name="dm32"]').removeAttr('checked');  //주보호자와 동거
				 $('input:text[name="dm33"]').val(''); 
				 $('input:text[name="dm33A"]').val(''); 
				 $('input:text[name="dm34"]').val('');  
				 $('input:text[name="dm34A"]').val(''); 
				 $('input:text[name="dm35"]').val('');
				 $('input:radio[name="dm11"]').focus();	
				},0);

		}else{
			setTimeout(function() {
			$('input:radio[name="dm22"]').removeAttr('checked'); 	
			
			 $('input:radio[name="dm22A"]').attr("disabled", false); 
			 $('input:radio[name="dm22B"]').attr("disabled", false); 
			 $('input:radio[name="dm22C"]').attr("disabled", false); 
			 $('input:radio[name="dm22D"]').attr("disabled", false); 
			 $('input:radio[name="dm22E"]').attr("disabled",false); 
			 $('input:radio[name="dm22F"]').attr("disabled",false); 
			 $('input:radio[name="dm22G"]').attr("disabled",false); 
			 $('input:radio[name="dm22H"]').attr("disabled",false); 	
			 
			 $('input:radio[name="dm30"]').attr("disabled", false); 
			 $('input:radio[name="dm31"]').attr("disabled", false); 
			 $('input:radio[name="dm32"]').attr("disabled", false); 
			 $('input:text[name="dm33"]').attr("disabled", false); 
			 $('input:text[name="dm33A"]').attr("disabled", false); 
			 $('input:text[name="dm34"]').attr("disabled", false);  
			 $('input:text[name="dm34A"]').attr("disabled",false); 
			 $('input:text[name="dm35"]').attr("disabled",false);
			 
			},0);
		}
	});
	
	
	$('input:radio[id="dm32"]').change(function() {
		var getNm = $(this).attr('name');
		var r2val=	$(this).val();
		if( r2val == '1'){
			setTimeout(function() {
				 
				 $('input:radio[name="dm32"]').attr("disabled", true); 
				 $('input:text[name="dm33"]').attr("disabled", true); 
				 $('input:text[name="dm33A"]').attr("disabled", true); 
				 $('input:text[name="dm34"]').attr("disabled", true);  
				 $('input:text[name="dm34A"]').attr("disabled", true); 
				 $('input:text[name="dm35"]').attr("disabled", true);
			
				 $('input:text[name="dm33"]').val(''); 
				 $('input:text[name="dm33A"]').val(''); 
				 $('input:text[name="dm34"]').val('');  
				 $('input:text[name="dm34A"]').val(''); 
				 $('input:text[name="dm35"]').val('');
				 $('input:radio[name="dm11"]').focus();	
				},0);

		}else{
			setTimeout(function() {
			 
			 $('input:radio[name="dm30"]').attr("disabled", false); 
			 $('input:radio[name="dm31"]').attr("disabled", false); 
			 $('input:radio[name="dm32"]').attr("disabled", false); 
			 $('input:text[name="dm33"]').attr("disabled", false); 
			 $('input:text[name="dm33A"]').attr("disabled", false); 
			 $('input:text[name="dm34"]').attr("disabled", false);  
			 $('input:text[name="dm34A"]').attr("disabled",false); 
			 $('input:text[name="dm35"]').attr("disabled",false);
			 
			},0);
		}
	});
	
	
	
	
	$('input:text[name="dm33"]').change(function() {
		var getVal = $(this).val();
		var getNm = $(this).attr('name');
		
		if(!(getVal == '')){
			 $('input:text[name="dm33A"]').val(''); 
			 $('input:text[name="dm33A"]').attr("disabled", true); 
		}else if(getVal == ''){
			$(this).attr("disabled", true); 
			setTimeout(function() {
				$('input:text[name="dm33A"]').attr("disabled",false);
				 $('input:text[name="dm33"]').focus();	
				},0);
		}
					
	});
	
	$('input:text[name="dm33A"]').change(function() {
		var getVal = $(this).val();
		var getNm = $(this).attr('name');
		
		if(!(getVal == '')){
			 $('input:text[name="dm33"]').val(''); 
			 $('input:text[name="dm33"]').attr("disabled", true); 
		}else if(getVal == ''){
			$(this).attr("disabled", true); 
			setTimeout(function() {
				$('input:text[name="dm33"]').attr("disabled",false);
				 $('input:text[name="dm33"]').focus();	
				},0);
		}		
	});
	
	
	
	
	$('input:radio[name="dm38"]').change(function() {
		var getVal = $(this).val();
		var getNm = $(this).attr('name');
		
		if(getVal =='0'){
			 $('input:text[name="dm39"]').val(''); 
			 $('input:text[name="dm39"]').attr("disabled", true); 
				setTimeout(function() {	
					 $('input:text[id="dm40"]').focus();	
					},0);
		}else if(getNm =='1'){
			
			 setTimeout(function() {
			 $('input:text[name="dm40"]').val(''); 
			 $('input:text[name="dm39"]').attr("disabled",false); 
			 $('input:text[name="dm40"]').attr("disabled",false); 
				},0);
		}
			
	});
	
	
	
	
	$('#insert').click(function() {
		
		//배열선언
		var rcolNmArr = [];
		var tcolNmArr = [];
		var radioArr = [];
		var textArr = [];
		var addressArr = [];
		let tscore = 0;
		var sdates = $("#sdate").val();
		var dm07C =$('#dm07C').val();
		var dm13 = $('input[name="dm13"]').val();
		var dm14 = $('input[name="dm14"]').val();
		var exam_num= $("#selectionNum").val();
		var exam_idx= $("#examIdx").val(); 
		var exam_id= $("#exid").val(); 
		console.log("exam_num::"+exam_num)
		console.log("exam_idx::"+exam_idx)
		console.log("설문실행일:"+sdates);
		console.log("실제생년월일:"+sdates)
		console.log("mobile:"+dm13);
		console.log("telno:"+dm14);
		var whichChk = $("#chk").val();
		console.log(whichChk+":::whichChk")
		var cdbs = $("#cdbs").val();	
		
		if(sdates == ''){
			alert("설문실행일을 체크해주세요!")
			return false;
		}
		


		var value;
		var value1;
		var value2;
		$('input:radio[class="r"]:checked').each(function(i){//체크된 리스트 저장
			
			value = $(this).val();	
			var getNm = $(this).attr('name');	
			var Fval =  $('input:radio[class="r"]:checked').length;
			console.log(Fval+"체크한카운트")
			if(value != '' &&  getNm !=''){
				console.log(getNm+":::::::::::getNm["+i+"]")
				console.log(value+":[dm 체크밸류]values1["+i+"]");
				 radioArr.push(value);		
				 rcolNmArr.push(getNm);
				 
			}	 
        });

		
		$('input:text[class="t"]').each(function(i){	
			value1 = $(this).val();
			var  getNm = $(this).attr('name');
		
			if(value1 != '' &&  getNm !=''){
				console.log(getNm+":::::::::::getNm["+i+"]");
				console.log(value1+":[dm 텍스트]values1["+i+"]");
					 textArr.push(value1);		
					 tcolNmArr.push(getNm);	
			}
			
		});
		
		
			
		$('input:text[class="inputStyle05"]').each(function(i){	
			value2 = $(this).val();
			console.log(value2+":[dm 주소텍스트]values1["+i+"]");
				 addressArr.push(value2);		
				 
				 
				 
				 
		});
		
		
		
		
		var num;
		var num1;
		 num = $('input:radio[class="r"]:checked').length;
 		console.log(num+"저장된체크넘버");
 		num1 = $('input:radio[class="r"]:not(:checked)').length;
		

		var objParams = {	
				"rcolNmArr" : rcolNmArr,
				"tcolNmArr" : tcolNmArr,
				"radioArr" : radioArr,
				"textArr" : textArr,
				"sdate" : sdates,  
				"dm07C" : dm07C,
				"dm13" : dm13,
				"dm14" : dm14,
				"idx" : idx,
				"whichChk" : whichChk,
				"exam_id" : exam_id,
				"exam_num" : exam_num,
				"exam_idx" : exam_idx,
				"cdbs" : cdbs
	
		};
		
		//ajax호출
		$.ajax({
			url         :   getContextPath()+"/include/sheet/view_savedemography.do",
            dataType    :   "json",
            contentType :   "application/x-www-form-urlencoded; charset=UTF-8",
            type        :   "post",
            data        :   objParams,
            success     :   function(json){
       			if(json.model.code =="OK"){
       				var obj = json.model.objIdx;
       				var examN = json.model.exam_num;
       				var flag = false;       				
       				if(json.model.result=="new"){
       					alert(json.model.message);
            			   flag = true;
            			   parent.load_sunbyul(flag);
       				}else if(json.model.result=="edit"){
       					alert(json.model.message);
       					flag = true;
         			    parent.load_sunbyul(flag);
       				}
       			}else{
       				alert("등록을 실패하였습니다 다시시도해주세요!")
       			} 		
            },
            error       :   function(request, status, error){
                console.log("AJAX_ERROR");
            }
	
		});
		
	});
	var sdateVal = $('input:text[name="sdate"]').attr('value')
	 var date = $("calendar").val();
	console.log(date);
	console.log("sdateVal앞::"+sdateVal);

	
	 $('input:radio[class="r"]:checked').attr("disabled", false);	
	 $('input:radio[class="r"]:not(:checked)').attr("disabled", true);
	 
	 //동거자 비활성화 시키기
	 /*
	 $('input:radio[name="r5"]').attr("disabled", true); //비활성화
	 $('input:radio[name="r6"]').attr("disabled", true); //비활성화
	 $('input:radio[name="r7"]').attr("disabled", true); //비활성화
	 $('input:radio[name="r8"]').attr("disabled", true); //비활성화
	 $('input:radio[name="r9"]').attr("disabled", true); //비활성화
	 $('input:radio[name="r10"]').attr("disabled", true); //비활성화
	 $('input:radio[name="r11"]').attr("disabled", true); //비활성화
	 $('input:radio[name="r12"]').attr("disabled", true); //비활성화
	 $('input:radio[name="r13"]').attr("disabled", true); //비활성화
	 */
	 $('input:radio[name="dm_22A"]:checked').attr("disabled", false);	              
     $('input:radio[name="dm_22A"]:not(:checked)').attr("disabled", true);          
     $('input:radio[name="dm_22B"]:checked').attr("disabled", false);	              
     $('input:radio[name="dm_22B"]:not(:checked)').attr("disabled", true);          
     $('input:radio[name="dm_22C"]:checked').attr("disabled", false);	              
     $('input:radio[name="dm_22C"]:not(:checked)').attr("disabled", true);          
     $('input:radio[name="dm_22D"]:checked').attr("disabled", false);	              
     $('input:radio[name="dm_22D"]:not(:checked)').attr("disabled", true);          
 	$('input:radio[name="dm_22E"]:checked').attr("disabled", false);	              
 	$('input:radio[name="dm_22E"]:not(:checked)').attr("disabled", true);

    $('input:radio[name="dm_22F"]:checked').attr("disabled", false);	              
    $('input:radio[name="dm_22F"]:not(:checked)').attr("disabled", true);          
    $('input:radio[name="dm_22G"]:checked').attr("disabled", false);	              
    $('input:radio[name="dm_22G"]:not(:checked)').attr("disabled", true);          
	$('input:radio[name="dm_22H"]:checked').attr("disabled", false);	              
	$('input:radio[name="dm_22H"]:not(:checked)').attr("disabled", true);
	 
	 $('input:text[class="t"]').attr("disabled",true);		
	 
	 $('input:text[name="sdate"]').attr("disabled", true);
	
	

	 $("input[name='dm07R']").keyup(
				function() {
		  			var now = new Date();
		  			var year = now.getFullYear();
		  			console.log("연도: "+year);
					
					$(this).val( $(this).val().replace(/[^0-9]/gi,"") );

					if($(this).val().length >=4 ) 
					{ 
						$("input[name='dm10']").val( year - $("input[name='dm07R']").val() );
						$("input[name='dm07A']").focus(); 
					}
				}
			);

		$("input[name='dm07A']").keyup(
				function() {

					$(this).val( $(this).val().replace(/[^0-9]/gi,"") );

					if($(this).val().length >=2 ) 
					{

						$("input[name='dm07B']").focus(); 
						
					}
				}
			);
			$("input[name='dm07C']").keyup(
				function() {

					$(this).val( $(this).val().replace(/[^0-9]/gi,"") );

					if($(this).val().length >=2 ) 
					{

						$("select[name='gender']").focus(); 
					}
				}
			);	 
	 
	 
			
	
});
</script>


<body>
<div id="btnArea" class="btn_wrap top">
	<input type ="hidden" id="action" value="${action}" style="display:none;"/>
	<!-- <button name="저장" id="insert" class ="csbr1010btn" style="display:none;">저장</button> -->
	<button name="수정" id="edit" class ="csbr1010btn"  value="edit">수정</button>
	<button name="신규" id ="new" class ="csbr1010btn"  value="new">신규</button>

	<input type="hidden" id ="object_idx" style="display:none;" class ="object_idx" value='<%= dmvo == null ? "" :StringUtil.NVL(dmvo.getObjectIdx())%>'>
	<input type='hidden' name='selectionNum' id="selectionNum"  value='<%= dmvo == null ? "" :StringUtil.NVL(dmvo.getSelectionNum())%>'> 
	<input type='hidden' name='examIdx' id="examIdx"  value='<%= dmvo == null ? "" :StringUtil.NVL(dmvo.getIdx())%>'> 
	<input name="idx" id ="idx" class ="idx" value="${idx}" style="display:none;"/>
	<input type ="hidden" name="exid" id ="exid" class ="exid" value="${exam_id}" style="display:none;"/>     
	<input type ="hidden" name="exnum" id ="exnum" class ="exnum" value="${exam_num}" style="display:none;"/>
<input type ="hidden" name="cdbs" id ="cdbs" class ="cdbs" value="0001" style="display:none;"/>	
	<input type ="hidden" name="chk" id ="chk" class ="chk" value="" style="display:none;"/>
</div> 

<div id ="tabbox" style="height:auto;">

	<table class="table type02">
        <colgroup>
            <col width="40px" />
            <col width="*" />
        </colgroup>
        <thead>
            <tr>
                <th class="bg1 tbl_tit" colspan="2">인구 사회학적 정보</th>
            </tr>
            <tr>
                <th class="survey_date" colspan="2">
                    <div class="sv_text">설문실행일</div>
                    <div class="sv_date">
						<input type='text' class='calendar' id='sdate' name='sdate' placeholder='yyyy/mm/dd'  style='width:120px;' 
						<%if(dmvo == null) {%>value=""<%}
						else if(StringUtil.NVL(dmvo.getTestDay()) == "" ){%>value=""<%}
						else{%>value="<%=StringUtil.NVL(DateUtil.getSimpleDate("yyyyMMdd",dmvo.getTestDay()))%>"<%}%>>	
					</div>
                </th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td colspan="2" style="padding:0;border-left:1px solid #c4d9f6;">
                    <!-- .table (개인정보) -->
                    <table class="table">
                        <colgroup>
                            <col width="93px" />
                            <col width="100px" />
                            <col width="93px" />
                            <col width="*" />
                        </colgroup>
                        <tbody>
                            <tr>
                                <td>* 이름</td>
                                <td><input type='text' class ='t' id='t1' name ='dm04' value='<%=(dmvo == null ? "" : StringUtil.NVL(dmvo.getDm04()))%>' style='width:80px;'/></td>
                                <td>* 전화번호</td>
                                <td>Mobile:<input type='text' class ='tn' id='t2' name ='dm13' value='<%=(dmvo == null ? "" : StringUtil.NVL(dmvo.getMobile()))%>' style='width:100px;text-align:center;'> Telno:<input type='text' class ='tn' id='t2' name ='dm14' value='<%=(dmvo == null ? "" : StringUtil.NVL(dmvo.getTelNo()))%>' style='width:100px;text-align:center;'></td>
                            </tr>
                            <tr>
                                <td>* 성별</td>
                                <td colspan="3">
                                    <div class="ans first">
                                        <label><input type='radio' class='r' name="dm06" id="g1" value='1' <% if("1".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm06())))){%>checked <%}%>><span>남자</span></label>
                                        <label><input type='radio' class='r' name="dm06" id="g2" value='2' <% if("2".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm06())))){%>checked <%}%>><span>여자</span></label>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="4">
                                    <p>* 생년월일(주민등록 기준) 주민등록상의 생년월일은 언제 입니까?</p>

                                    <div class="ans first">
                                        <input  class = 't' id='num' name ='dm07R' type="text" value='<%=(dmvo == null ? "" : StringUtil.NVL(dmvo.getDm07R()))%>' maxlength='4' pattern='[0-9]*' style="text-align: center;" /><input  class = 't' id='num' name ='dm07A' type="text" value='<%=(dmvo == null ? "" : StringUtil.NVL(dmvo.getDm07A()))%>' maxlength='2' pattern='[0-9]*' style="text-align: center;" /><input  class = 't' id='num' name ='dm07B' type="text" value='<%=(dmvo == null ? "" : StringUtil.NVL(dmvo.getDm07B()))%>' maxlength='2' pattern='[0-9]*' style="text-align: center;margin-right:20px;" />
                                        나이: 만 <input 	class = 't'  id='num' name ='dm10' type="text"  value='<%=(dmvo == null ? "" : StringUtil.NVL(dmvo.getDm10()))%>' maxlength='3' pattern='[0-9]*' style="text-align: center;" readonly />세
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="4">
                                    * 주민등록번호 뒷자리는 무엇입니까? <input  type='text' class = 't' id='num' name ='dm08' value='<%=(dmvo == null ? "" : StringUtil.NVL(dmvo.getDm08()))%>' style='width:140px;'>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="4">
                                    * 실제생년월일은 언제 입니까? <input type='text' id='dm07C' class='calendars'  name='sdate' placeholder='yyyy/mm/dd'  style='width:120px;' 
			
									<%if(dmvo == null) {%>value=""<%}
									else if(StringUtil.NVL(dmvo.getDm07C()) == "" ){%>value=""<%}
									else{%>value="<%=StringUtil.NVL(DateUtil.getSimpleDate("yyyyMMdd",dmvo.getDm07C()))%>"<%}%>>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="4">
                                    <p>* 귀하의 띠는 무엇입니까?</p>
                                    
                                    <div class="ans first">
                                        <label style="width:100px;"><input type='radio' class='r' id ="y1" name="dm09" value='1' <% if("1".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm09())))){%>checked <%}%>><span>쥐띠</span></label>
                                        <label style="width:100px;"><input type='radio' class='r' id ="y2" name="dm09" value='2' <% if("2".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm09())))){%>checked <%}%>><span>소띠</span></label>
                                        <label style="width:100px;"><input type='radio' class='r' id ="y3" name="dm09" value='3' <% if("3".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm09())))){%>checked <%}%>><span>범띠</span></label>
                                        <label style="width:100px;"><input type='radio' class='r' id ="y4" name="dm09" value='4' <% if("4".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm09())))){%>checked <%}%>><span>토끼띠</span></label>
                                        <label style="width:100px;"><input type='radio' class='r' id ="y5" name="dm09" value='5' <% if("5".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm09())))){%>checked <%}%>><span>용띠</span></label>
                                        <label style="width:60px;"><input type='radio' class='r' id ="y6" name="dm09" value='6' <% if("6".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm09())))){%>checked <%}%>><span>뱀띠</span></label>
                                    </div>
                                    <div class="ans">
                                        <label style="width:100px;"><input type='radio' class='r' id ="y7" name="dm09" value='7' <% if("7".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm09())))){%>checked <%}%>><span>말띠</span></label>
                                        <label style="width:100px;"><input type='radio' class='r' id ="y8" name="dm09" value='8' <% if("8".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm09())))){%>checked <%}%>><span>양띠</span></label>
                                        <label style="width:100px;"><input type='radio' class='r' id ="y9" name="dm09" value='9' <% if("9".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm09())))){%>checked <%}%>><span>잔나비띠</span></label>
                                        <label style="width:100px;"><input type='radio' class='r' id ="y10" name="dm09" value='10' <% if("10".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm09())))){%>checked <%}%>><span>닭띠</span></label>
                                        <label style="width:100px;"><input type='radio' class='r' id ="y11" name="dm09" value='11' <% if("11".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm09())))){%>checked <%}%>><span>개띠</span></label>
                                        <label style="width:60px;"><input type='radio' class='r' id ="y12" name="dm09" value='12' <% if("12".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm09())))){%>checked <%}%>><span>돼지띠</span></label>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="4">
                                    <p>* 귀하는 외국어 사용이 가능하십니까?</p>
                                    
                                    <div class="ans first">
                                        <label style="width:100px;"><input type='radio' class='r' name="dm12" value='1' <% if("1".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm12())))){%>checked <%}%>><span>불가능</span></label>
                                        <label style="width:100px;"><input type='radio' class='r' name="dm12" value='2' <% if("2".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm12())))){%>checked <%}%>><span>한가지</span></label>
                                        <label style="width:100px;"><input type='radio' class='r' name="dm12" value='3' <% if("3".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm12())))){%>checked <%}%>><span>두가지이상</span></label>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="4">
                                    <p>* 주소</p>
                                    
                                    <div class="ans first inputtopSpace">
                                        <p><input type="button" onclick="sample4_execDaumPostcode();" class="inputBtn01" value="주소검색"><input type="text" class= 'tn' id="sample4_postcode" class="inputStyle03" placeholder="우편번호" style="width: 100px;"></p>
                                        <p><input type="text"  class= 't' name="dm15" id="sample4_sido" class="inputStyle05" placeholder="시도" value='<%=(dmvo == null ? "" : StringUtil.NVL(dmvo.getDm15()))%>' style="width: 100px;"><input type="text"  class= 't' name="dm16" id="sample4_sigungu" class="inputStyle05" placeholder="시/군/구" value='<%=(dmvo == null ? "" : StringUtil.NVL(dmvo.getDm16()))%>' style="width: 100px;" ><input type="text"  class= 't' name="dm17" id="sample4_roadAddress" class="inputStyle05" placeholder="도로명주소" value ='<%=(dmvo == null ? "" : StringUtil.NVL(dmvo.getDm17()))%>' style="width: 300px;"></p>
                                        <p>
											<input type="text" class= 't' name="dm18" id="sample4_detailAddress" placeholder="상세주소" class="inputStyle05" value ='<%=(dmvo == null ? "" : StringUtil.NVL(dmvo.getDm18()))%>' style="width:150px;margin-left:215px;">

											<input type="hidden"  class= 'tn' name="sample4_jibunAddress" id="sample4_jibunAddress" class="inputStyle04" placeholder="지번주소" value ='<%=(dmvo == null ? "" : StringUtil.NVL(dmvo.getDm17()))%>' style="width:150px;">
											<span id="guide" style="color:#999;display:none; position: relative; left: 210px;"></span>
											<input type="hidden" id="sample4_extraAddress" placeholder="참고항목">
											<input type="hidden" id="sample4_engAddress" placeholder="영문주소">
										</p>	
                                    </div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <!-- //.table (개인정보) -->
                </td>
            </tr>

            <tr>
                <th>1.</th>
                <td class="ta_l">
                    <p>1. 주거 형태</p>

                    <div class="ans first">
                        <label><input type='radio' class='r' name="dm29" value='1' <% if("1".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm29())))){%>checked <%}%>><span>집</span></label>
                        <label><input type='radio' class='r' name="dm29" value='2' <% if("2".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm29())))){%>checked <%}%>><span>요양원</span></label>
                        <label><input type='radio' class='r' name="dm29" value='3' <% if("3".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm29())))){%>checked <%}%>><span>요양병원</span></label>
                    </div>
                </td>
            </tr>
            <tr>
                <th>2.</th>
                <td class="ta_l">
                    <p>동거자 (주거 형태가 '집'인 경우에 응답해주세요.) 기존값 초기화 해야할거 같음<br>
                    누구와 동거하고 있습니까? 동거하고 있는 사람들 모두 표시해주세요.</p>

                    <div class="ans first">
                        <label style="width:auto;"><input type='radio' class='r' id='r5' name='dm22' value='1' <% if("1".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm22())))){%>checked <%}%>><span>없음</span></label> <span style="display:inline-block;margin:3px 10px 0 0;vertical-align:middle;">( → 4. 학력 문항으로 이동)</span>
                        <label><input type='radio' class='r' id='r5' name='dm22A' value='1' <% if("2".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm22())))){%>checked <%}
							else if("1".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm22A())))){%>checked <%}%>><span>배우자</span></label>
                        <label style="width:70px;"><input type='radio' class='r' id='r5' name='dm22B' value='1' <% if("1".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm22B())))){%>checked <%}%>><span>아들</span></label>
                        <label style="width:70px;"><input type='radio' class='r' id='r5' name='dm22C' value='1' <% if("1".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm22C())))){%>checked <%}%>><span>딸</span></label>
                        <label style="width:70px;"><input type='radio' class='r' id='r5' name='dm22D' value='1' <% if("1".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm22D())))){%>checked <%}%>><span>며느리</span></label>
                    </div>
                    <div class="ans">
                        <label style="width:70px;"><input type='radio' class='r' id='r5' name='dm22E' value='1' <% if("1".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm22E())))){%>checked <%}%>><span>사위</span></label>
                        <label style="width:70px;"><input type='radio' class='r' id='r5' name='dm22F' value='1' <% if("1".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm22F())))){%>checked <%}%>><span>손주</span></label>
                        <label style="width:143px;"><input type='radio' class='r' id='r5' name='dm22G' value='1' <% if("1".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm22G())))){%>checked <%}%>><span>간병인(요양보호사)</span></label>
                        <label style="width:auto;"><input type='radio' class='r' id='r5' name='dm22H' value='1' <% if("5".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm22())))){%>checked <%}
							else if("1".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm22H())))){%>checked <%}%>><span>기타</span></label> <input type='text' id='t14' <%if("3".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm22())))){%>value="배우자 와 다른 가족" <%}else if("4".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm22())))){%>value ="배우자 없이 가족만"<%}%> class="tn"style='width:150px; border:0; color:#8EC0F6;'readonly>
                    </div>
                </td>
            </tr>
            <tr>
                <th>3-1.</th>
                <td class="ta_l">
                    <p>주보호자는 누구입니까?</p>

                    <div class="ans first">
                        <label style="width:70px;"><input type='radio' class='r' name='dm30' value='1' <% if("1".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm30())))){%>checked <%}%>><span>없음</span></label>
                        <label style="width:70px;"><input type='radio' class='r' name='dm30' value='2' <% if("2".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm30())))){%>checked <%}%>><span>배우자</span></label>
                        <label style="width:70px;"><input type='radio' class='r' name='dm30' value='3' <% if("3".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm30())))){%>checked <%}%>><span>아들</span></label>
                        <label style="width:70px;"><input type='radio' class='r' name='dm30' value='4' <% if("4".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm30())))){%>checked <%}%>><span>딸</span></label>
                        <label style="width:70px;"><input type='radio' class='r' name='dm30' value='5' <% if("5".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm30())))){%>checked <%}%>><span>며느리</span></label>
                    </div>
                    <div class="ans">
                        <label style="width:70px;"><input type='radio' class='r' name='dm30' value='6' <% if("6".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm30())))){%>checked <%}%>><span>사위</span></label>
                        <label style="width:70px;"><input type='radio' class='r' name='dm30' value='7' <% if("7".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm30())))){%>checked <%}%>><span>손주</span></label>
                        <label style="width:144px;"><input type='radio' class='r' name='dm30' value='8' <% if("8".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm30())))){%>checked <%}%>><span>간병인(요양보호사)</span></label>
                        <label style="width:auto;"><input type='radio' class='r' name='dm30' value='9' <% if("9".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm30())))){%>checked <%}%>><span>기타</span></label>
                    </div>
                </td>
            </tr>
            <tr>
                <th>3-2.</th>
                <td class="ta_l">
                    <p>주보호자의 연령은 무엇입니까?</p>

                    <div class="ans first">
                        <label><input type='radio' class='r' name='dm31' value='1' <% if("1".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm31())))){%>checked <%}%>><span>20대</span></label>
                        <label><input type='radio' class='r' name='dm31' value='2' <% if("2".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm31())))){%>checked <%}%>><span>30대</span></label>
                        <label><input type='radio' class='r' name='dm31' value='3' <% if("3".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm31())))){%>checked <%}%>><span>40대</span></label>
                        <label><input type='radio' class='r' name='dm31' value='4' <% if("4".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm31())))){%>checked <%}%>><span>50대</span></label>
                    </div>
                    <div class="ans">
                        <label><input type='radio' class='r' name='dm31' value='5' <% if("5".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm31())))){%>checked <%}%>><span>60대</span></label>
                        <label><input type='radio' class='r' name='dm31' value='6' <% if("6".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm31())))){%>checked <%}%>><span>70대</span></label>
                        <label><input type='radio' class='r' name='dm31' value='7' <% if("7".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm31())))){%>checked <%}%>><span>80대</span></label>
                        <label><input type='radio' class='r' name='dm31' value='8' <% if("8".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm31())))){%>checked <%}%>><span>기타</span></label>
                    </div>
                </td>
            </tr>
            <tr>
                <th>3-3.</th>
                <td class="ta_l">
                    <p>주보호자와 동거하고 있습니까?</p>

                    <div class="ans first">
                        <label style="width:auto;"><input type='radio' class='r' name='dm32' value='1' <% if("1".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm32())))){%>checked <%}%>><span>예</span></label><span style="display:inline-block;margin:3px 20px 0 0;vertical-align:middle;">( → 4. 학력 문항으로 이동)</span>
                        <label><input type='radio' class='r' name='dm32' value='2' <% if("0".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm32())))){%>checked <%}%>><span>아니오</span></label>
                    </div>
                </td>
            </tr>
            <tr>
                <th>3-4.</th>
                <td class="ta_l">
                    <p>주보호자와 만나는 횟수는 몇 번입니까?(두 개 중 하나만 입력해주세요.)</p>

                    <div class="ans first">
                        <input type='text' class='t' id='num' name='dm33' value='<%=(dmvo == null ? "" : StringUtil.NVL(dmvo.getDm33()))%>'>회/월 또는 <input type='text' class='t' id='num' name='dm33A' value='<%=(dmvo == null ? "" : StringUtil.NVL(dmvo.getDm33A()))%>'>회/년
                    </div>
                </td>
            </tr>
            <tr>
                <th>3-5.</th>
                <td class="ta_l">
                    <p>주보호자와 1회 만남 시 얼마나 시간을 보냅니까?</p>

                    <div class="ans first">
                        <input type='text' class='t' id='num' name='dm34' value='<%=(dmvo == null ? "" : StringUtil.NVL(dmvo.getDm34()))%>'>시간 <input type='text' class='t' id='num' name='dm34A' value='<%=(dmvo == null ? "" : StringUtil.NVL(dmvo.getDm34A()))%>'>분
                    </div>
                </td>
            </tr>
            <tr>
                <th>3-6.</th>
                <td class="ta_l">
                    <p>주보호자와 1개월 동안 몇 번 만나십니까?</p>

                    <div class="ans first">
                        <input type='text' class='t' id='num' name='dm35' value='<%=(dmvo == null ? "" : StringUtil.NVL(dmvo.getDm35()))%>'>회/월
                    </div>
                </td>
            </tr>
            <tr>
                <th>4.</th>
                <td class="ta_l">
                    <p>학력 상의</p>

                    <div class="ans first">
                        <label style="width:120px;"><input type='radio' class='r' name='dm11' value='99' <% if("99".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm11())))){%>checked <%}%>><span>무학</span></label>
                        <label style="width:120px;"><input type='radio' class='r' name='dm11' value='8' <% if("8".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm11())))){%>checked <%}%>><span>초등학교 졸업</span></label>
                        <label style="width:120px;"><input type='radio' class='r' name='dm11' value='11' <% if("11".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm11())))){%>checked <%}%>><span>중학교 졸업</span></label>
                    </div>
                    <div class="ans">
                        <label style="width:120px;"><input type='radio' class='r' name='dm11' value='14' <% if("14".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm11())))){%>checked <%}%>><span>고등학교 졸업</span></label>
                        <label style="width:120px;"><input type='radio' class='r' name='dm11' value='18' <% if("18".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm11())))){%>checked <%}%>><span>대학교 졸업</span></label>
                        <label style="width:120px;"><input type='radio' class='r' name='dm11' value='20' <% if("20".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm11())))){%>checked <%}%>><span>대학원 졸업 이상</span></label><input type='text' class="tn" id='t21' style="width: 120px; border:0; color:#8EC0F6;"
						<%if("2".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm11())))){%>value="<%= StringUtil.NVL(dmvo.getDm11Nm()) %>"
						<%}else if("3".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm11())))){%>value="<%=StringUtil.NVL(dmvo.getDm11Nm())%>"
						<%}else if("4".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm11())))){%>value="<%=StringUtil.NVL(dmvo.getDm11Nm())%>"
						<%}else if("5".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm11())))){%>value="<%=StringUtil.NVL(dmvo.getDm11Nm())%>"
						<%}else if("7".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm11())))){%>value="<%=StringUtil.NVL(dmvo.getDm11Nm())%>"
						<%}else if("9".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm11())))){%>value="<%=StringUtil.NVL(dmvo.getDm11Nm())%>"
						<%}else if("10".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm11())))){%>value="<%=StringUtil.NVL(dmvo.getDm11Nm())%>"
						<%}else if("12".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm11())))){%>value="<%=StringUtil.NVL(dmvo.getDm11Nm())%>"
						<%}else if("13".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm11())))){%>value="<%=StringUtil.NVL(dmvo.getDm11Nm())%>"
						<%}else if("15".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm11())))){%>value="<%=StringUtil.NVL(dmvo.getDm11Nm())%>"
						<%}else if("16".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm11())))){%>value="<%=StringUtil.NVL(dmvo.getDm11Nm())%>"
						<%}else if("17".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm11())))){%>value="<%=StringUtil.NVL(dmvo.getDm11Nm())%>"
						<%}else if("19".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm11())))){%>value="<%=StringUtil.NVL(dmvo.getDm11Nm())%>"
						<%}else if("21".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm11())))){%>value="<%=StringUtil.NVL(dmvo.getDm11Nm())%>"
						<%}else if("22".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm11())))){%>value="<%=StringUtil.NVL(dmvo.getDm11Nm())%>"
						<%}else if("23".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm11())))){%>value="<%=StringUtil.NVL(dmvo.getDm11Nm())%>"
						<%}else if("24".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm11())))){%>value="<%=StringUtil.NVL(dmvo.getDm11Nm())%>"<%}%> readonly>
                    </div>
                </td>
            </tr>
            <tr>
                <th rowspan="2">5.</th>
                <td class="ta_l">학력</td>
            </tr>
            <tr>
                <td class="ta_l border_dash">
                    <p>총 정규 교육년수는 몇 년 입니까?</p>

                    <div class="ans first">
                        <input type='text' class ='t' id= 'num' name="dm36" value='<%=(dmvo == null ? "" : StringUtil.NVL(dmvo.getDm36()))%>'>년
                    </div>
                </td>
            </tr>
            <tr>
                <th rowspan="2">6.</th>
                <td class="ta_l">문맹</td>
            </tr>
            <tr>
                <td class="ta_l border_dash">
                    <p>글을 읽거나 쓸 수 있습니까?</p>

                    <div class="ans first">
                        <label><input type='radio' class='r' name='dm37' value='1' <% if("1".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm37())))){%>checked <%}%>><span>예</span></label>
                        <label><input type='radio' class='r' name='dm37' value='0' <% if("0".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm37())))){%>checked <%}%>><span>아니오</span></label>
                    </div>
                </td>
            </tr>
            <tr>
                <th>7.</th>
                <td class="ta_l">직업</td>
            </tr>
            <tr>
                <th class="border_dash">7-1.</th>
                <td class="ta_l border_dash">
                    <p>최근 일주일 동안 수입을 목적으로 1시간 이상을 일하거나, 18시간 이상 무급가족 종사자로 일하십니까? 원래 일을 하고 있지만 일시 휴직 상태도 일을 하신 경우에 포함됩니다.</p>

                    <div class="ans first">
                        <label><input type='radio' class='r' name='dm38' value='1' <% if("1".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm38())))){%>checked <%}%>><span>예</span></label>
                        <label style="width:auto;"><input type='radio' class='r' name='dm38' value='0' <% if("0".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm38())))){%>checked <%}%>><span>아니오</span></label> <span style="display:inline-block;margin:3px 20px 0 0;vertical-align:middle;">( → 7-3. 문항으로 이동)</span>
                    </div>
                </td>
            </tr>
            <tr>
                <th class="border_dash">7-2.</th>
                <td class="ta_l border_dash">
                    <p>종사하고 계신 직업은 무엇입니까? 일의 종류를 구체적으로 말씀해 주십시오.<br>
                        (예를 들면, 사무직원, 판매직원, 부품 생산직, 유치원 보모, 학원 강사, 조리 보조원 등)</p>

                    <div class="ans first">
						<input type='text' class ='t' id= 't23' name="dm39" value='<%=(dmvo == null ? "" : StringUtil.NVL(dmvo.getDm39()))%>' style='width:200px;'>
                    </div>
                </td>
            </tr>
            <tr>
                <th class="border_dash">7-3.</th>
                <td class="ta_l border_dash">
                    <p>평생 동안 가장 오래 종사했던 직업은 무었입니까?</p>

                    <div class="ans first">
                        <input type='text' class ='t' id= 't24' name="dm40" value='<%=(dmvo == null ? "" : StringUtil.NVL(dmvo.getDm40()))%>' style='width:200px;'>
                    </div>
                </td>
            </tr>
            <tr>
                <th>8.</th>
                <td class="ta_l">
                    <p>결혼 상태</p>

                    <div class="ans first">
                        <label><input type='radio' class='r' name='dm21' value='1' <% if("1".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm21())))){%>checked <%}%>><span>결혼</span></label>
                        <label><input type='radio' class='r' name='dm21' value='2' <% if("2".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm21())))){%>checked <%}%>><span>사별</span></label>
                        <label><input type='radio' class='r' name='dm21' value='7' <% if("7".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm21())))){%>checked <%}%>>  <span>동거</span></label>
                        <label><input type='radio' class='r' name='dm21' value='4' <% if("4".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm21())))){%>checked <%}%>><span>이혼</span></label>
                        <label><input type='radio' class='r' name='dm21' value='3' <% if("3".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm21())))){%>checked <%}%>><span>별거</span></label>
                        <label style="width:auto;"><input type='radio' class='r' name='dm21' value='5' <% if("5".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm21())))){%>checked <%}%>><span>미혼</span></label> <input type='text'  id ='r20' class="tn" style="border:0; color:#8EC0F6;" <% if("6".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm21())))){%>value='기타'<%}%> readonly> 
                    </div>
                </td>
            </tr>
            <tr>
                <th>9.</th>
                <td class="ta_l">
                    <p>손잡이</p>

                    <div class="ans first">
                        <label style="width:150px;"><input type='radio' class='r' name='dm41' value='1' <% if("1".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm41())))){%>checked <%}%>><span>오른손잡이</span></label>
                        <label style="width:150px;"><input type='radio' class='r' name='dm41' value='2' <% if("2".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm41())))){%>checked <%}%>><span>왼손잡이</span></label>
                        <label style="width:150px;"><input type='radio' class='r' name='dm41' value='3' <% if("3".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm41())))){%>checked <%}%>>  <span>양손잡이</span></label>
                    </div>
                </td>
            </tr>
            <tr>
                <th rowspan="2">10.</th>
                <td class="ta_l">소득</td>
            </tr>
            <tr>
                <td class="ta_l border_dash">
                    <p>임금, 부동산 소득, 연금, 이자, 정보 보조금, 친척이나 저녀들의 용돈 등 모든 수입을 합 최근 1년 동안 가구의 월 평균 소득은 대략 얼마입니까?</p>

                    <div class="ans first">
                        <label style="width:200px;"><input type='radio' class='r' name='dm42' value='1' <% if("1".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm42())))){%>checked <%}%>><span>50만원 미만</span></label>
                        <label style="width:200px;"><input type='radio' class='r' name='dm42' value='2' <% if("2".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm42())))){%>checked <%}%>><span>50만원 이상 ~ 100만원 미만</span></label>
                    </div>
                    <div class="ans">
                        <label style="width:200px;"><input type='radio' class='r' name='dm42' value='3' <% if("3".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm42())))){%>checked <%}%>><span>100만원 이상 ~ 150만원 미만</span></label>
                        <label style="width:200px;"><input type='radio' class='r' name='dm42' value='4' <% if("4".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm42())))){%>checked <%}%>><span>150만원 이상 ~ 200만원 미만</span></label>
                    </div>
                    <div class="ans">
                        <label style="width:200px;"><input type='radio' class='r' name='dm42' value='5' <% if("5".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm42())))){%>checked <%}%>><span>200만원 이상 ~ 300만원 미만</span></label>
                        <label style="width:200px;"><input type='radio' class='r' name='dm42' value='6' <% if("6".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm42())))){%>checked <%}%>><span>300만원 이상 ~ 400만원 미만</span></label>
                    </div>
                    <div class="ans">
                        <label style="width:200px;"><input type='radio' class='r' name='dm42' value='7' <% if("7".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm42())))){%>checked <%}%>><span>400만원 이상 ~ 600만원 미만</span></label>
                        <label style="width:200px;"><input type='radio' class='r' name='dm42' value='8' <% if("8".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm42())))){%>checked <%}%>><span>600만원 이상</span></label>
                    </div>
                    <div class="ans">
                        <label style="width:200px;"><input type='radio' class='r' name='dm42' value='9' <% if("9".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm42())))){%>checked <%}%>><span>모르겠음</span></label>
                        <label style="width:200px;"><input type='radio' class='r' name='dm42' value='0' <% if("0".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm42())))){%>checked <%}%>><span>밝히고 싶지 않음</span></label>
                    </div>
                </td>
            </tr>
            <tr>
                <th>11.</th>
                <td class="ta_l">
                    <p>귀하의 의료보장은 무엇입니까?</p>

                    <div class="ans first">
                        <label style="width:150px;"><input type='radio' class='r' name='dm23' value='1' <% if("1".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm23())))){%>checked <%}%>><span>의료보험</span></label>
                        <label style="width:150px;"><input type='radio' class='r' name='dm23' value='2' <% if("2".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm23())))){%>checked <%}%>><span>의료급여</span></label>
                        <label style="width:150px;"><input type='radio' class='r' name='dm23' value='3' <% if("3".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm23())))){%>checked <%}%>><span>기타</span></label>
                    </div>
                </td>
            </tr>
            <tr>
                <th>12.</th>
                <td class="ta_l">
                    <p>월생활비</p>

                    <div class="ans first">
                        <label style="width:130px;"><input type='radio' class='r' name='dm26' value='1' <% if("1".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm26())))){%>checked <%}%>><span>100만원 미만</span></label>
                        <label style="width:130px;"><input type='radio' class='r' name='dm26' value='2' <% if("2".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm26())))){%>checked <%}%>><span>100~200만원</span></label>
                        <label style="width:130px;"><input type='radio' class='r' name='dm26' value='3' <% if("3".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm26())))){%>checked <%}%>>  <span>200~300만원</span></label>
                        <label style="width:auto;"><input type='radio' class='r' name='dm26' value='4' <% if("4".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm26())))){%>checked <%}%>>  <span>300만원 이상</span></label>
                    </div>
                </td>
            </tr>
            <tr>
                <th>13.</th>
                <td class="ta_l">
                    <p>종교</p>

                    <div class="ans first">
                        <label><input type='radio' class='r' name='dm28' value='1' <% if("1".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm28())))){%>checked <%}%>><span>없음</span></label>
                        <label><input type='radio' class='r' name='dm28' value='2' <% if("2".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm28())))){%>checked <%}%>><span>기독교</span></label>
                        <label><input type='radio' class='r' name='dm28' value='3' <% if("3".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm28())))){%>checked <%}%>><span>천주교</span></label>
                        <label><input type='radio' class='r' name='dm28' value='4' <% if("4".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm28())))){%>checked <%}%>><span>불교</span></label>
                        <label><input type='radio' class='r' name='dm28' value='5' <% if("5".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm28())))){%>checked <%}%>><span>유교</span></label>
                        <label style="width:auto;"><input type='radio' class='r' name='dm28' value='6' <% if("6".equals((dmvo == null ? "" : StringUtil.NVL(dmvo.getDm28())))){%>checked <%}%>><span>기타</span></label>
                    </div>
                </td>
            </tr>
        </tbody>
    </table>

</div>

<!-- 저장 버튼을 하단에 배치 -->
<div id="btnArea" class="btn_wrap btm">
	<button name="저장" id="insert" class ="csbr1010btn" style="">저장</button>
</div>



<script type="text/javascript">

	

function sample4_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.

            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 참고 항목 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
               extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraRoadAddr !== ''){
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample4_sido').value = data.sido;
            document.getElementById('sample4_sigungu').value = data.sigungu;
            document.getElementById('sample4_postcode').value = data.zonecode;
            document.getElementById("sample4_roadAddress").value = roadAddr;
            document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
            
            // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
            if(roadAddr !== ''){
                document.getElementById("sample4_extraAddress").value = extraRoadAddr;
            } else {
                document.getElementById("sample4_extraAddress").value = '';
            }

            var guideTextBox = document.getElementById("guide");
            // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
            if(data.autoRoadAddress) {
                var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                guideTextBox.style.display = 'block';

            } else if(data.autoJibunAddress) {
                var expJibunAddr = data.autoJibunAddress;
                guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                guideTextBox.style.display = 'none';
            } else {
                guideTextBox.innerHTML = '';
                guideTextBox.style.display = 'none';
            }
        }
    }).open();

}

</script>
</body>
</html>