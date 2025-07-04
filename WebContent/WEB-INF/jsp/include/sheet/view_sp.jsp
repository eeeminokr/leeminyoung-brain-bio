<%@page import="java.sql.Date"%>
<%@page import="csbrain.common.service.MbSpVO"%>
<%@page import="csbrain.common.util.DateUtil"%>
<%@page import="csbrain.common.util.StringUtil"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/common.inc"%>
<jsp:include page='<%=incInit%>' flush="false"></jsp:include>
<%
	String sdate = request.getParameter("sdate");
	String exam_num = request.getParameter("exam_num");
	//if ( StringUtil.isEmptyString(edate) ) edate = DateUtil.getTodayByFormat("yyyy-MM-dd");

	List<MbSpVO> sp_list = (List<MbSpVO>) request.getAttribute("sp_list");
	MbSpVO spvo = null;
	//	MbC3VO c3vo = null;

	if (sp_list != null && sp_list.size() > 0) {
		spvo = sp_list.get(0);

		String d = spvo.getTestDay();
		d = DateUtil.getSimpleDate("yyyyMMdd", d);
		System.out.print(d);

	}
	//	if( c3_list != null && c3_list.size() > 0 ){
	//		c3vo = c3_list.get(0);
	//	}
%>

<script>
	$(function() {
		print_head("");

		check_login(); //로그인체크

	});
</script>
<style>
.rbox {
	border: 0px solid red;
	position: absolute;
}
/* .r {
		position:absolute;
	} */
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
	width: 608px;
	height: 1850px;;
	background-repeat: no-repeat;
	/*
  	background-color: #e0e0e0;
	*/
}

.csbr1010btn {
	box-shadow: inset 0px 1px 0px 0px #ffffff;
	background: linear-gradient(to bottom, #f9f9f9 5%, #e9e9e9 100%);
	background-color: #f9f9f9;
	border-radius: 6px;
	border: 1px solid #dcdcdc;
	display: inline-block;
	cursor: pointer;
	color: #666666;
	font-family: Arial;
	font-size: 12px;
	font-weight: bold;
	margin: 3px 10px 0px 0px;
	float: right;
	width: 65px;
	height: 28px;
	text-decoration: none;
	text-shadow: 0px 1px 0px #ffffff;
}

.csbr1010btn:hover {
	background: linear-gradient(to bottom, #e9e9e9 5%, #f9f9f9 100%);
	background-color: #e9e9e9;
}

.csbr1010btn:active {
	position: relative;
	top: 1px;
}

.btn_wrap {
	margin-right: 13px;
}

.ans.col_4>label {
	display: inline-block;
	margin-right: 15px;
	width: 120px;
	vertical-align: middle;
}

.ans.col_4>label:last-child {
	margin-right: 0;
}
</style>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.11.3.js"></script>
<script>
	$(function() {
		/*
			$("input").click(function(){
				return false;
			});
			
		 */
		var idx = $('#idx').val();
		console.log(idx);

		var action = $('#action').val();
		if (action == 'hidden') {
			$('#btnArea').css({
				"visibility" : "hidden"
			});
		}

		$("#new").click(function() {
			if (idx == '') {
				alert("검색항목을 입력해주세요");
				return false;

			}
			var getexmN = $("#exnum").val();

			if ($('#insert').css("display") == "none") {
				$('#insert').show();
			}
			$('input:text[name="sdate"]').attr("disabled", false);
			$('input:radio[class="r"]').attr("disabled", false);
			$('input:text[class="t"]').attr("disabled", false);
			//$('input:text[name="ddate"]').attr("disabled", false);
			$('input[class="r"]').removeAttr('checked');
			$('input[class="t"]').val('');
			$('input[name="sdate"]').val('');
			//	$('input:radio[class="r"]:not(:checked)').attr("disabled", false);
			//	$('input:text[class="t"]').attr("disabled", false);	
			//   	 $(this).prop('checked', false);

			var selnew = $("#new").val();
			console.log(selnew + "::selenew")
			checking = $('#chk').val(selnew);
		});

		$("#edit").click(function() {
			if (idx == '') {
				alert("검색항목을 입력해주세요");
				return false;

			}

			if ($('#insert').css("display") == "none") {
				$('#insert').show();
			}
			$('input:text[name="sdate"]').attr("disabled", false);
			console.log($(".t").attr("disabled"));
			$('input:radio[class="r"]:not(:checked)').attr("disabled", false);
			$('input:text[class="t"]').attr("disabled", false);
			$(this).prop('checked', false);

			var selnew = $("#edit").val();
			console.log(selnew + "::selenew")
			checking = $('#chk').val(selnew);
		});

		$('input:radio[name="r1"]').click(function() {

			var r1val = $(this).val();

			$('input:radio[class="r"]:not(:checked)').attr("disabled", false); //비활성화
			// $('input:radio[class="r"]:not(:checked)').attr("disabled", true); //비활성화
			$('input:radio[name="r1"]:not(:checked)').attr("disabled", false);
			$('input:text[name="t3a"]').attr("disabled", false);
			$('input:text[name="t3a"]').focus();
			if ($('input:text[name="t3a"]').is(":focus") == true) {
				setTimeout(function() {
					$('input:text[name="t3b"]').attr("disabled", false);
					$('.t').find('input:text[name="t3b"]').focus();
				}, 0);
			}
			if ($('input:text[name="t3b"]').is(":focus") == true) {
				setTimeout(function() {
					$('input:text[name="t3c"]').attr("disabled", false);
					$('.t').find('input:text[name="t3c"]').focus();
				}, 0);
			}
			if ($('input:text[name="t3c"]').is(":focus") == true) {
				setTimeout(function() {
					$('input:text[name="t3d"]').attr("disabled", false);
					$('.t').find('input:text[name="t3d"]').focus();
				}, 0);
			}

		});

		$('input:text[id="num"]').keyup(function() {
			var regNumber = /^[0-9]*$/;
			var input = $(this).val();
			var maxLimit = $(this).attr("maxlength");
			var minLimit = $(this).attr("minlength");
			if (!regNumber.test(input)) {
				alert("숫자만 입력가능합니다");
				//$('input:text[id="num"]').val(input.replace(/[^0-9]/g, ""));
			}
			if (this.value.length >= maxLimit) {
				$(this).next('input:text[id="num"]').focus();
				return false;
			} else if (this.value.length < minLimit) {
				$(this).focus();
				return false;
			}

		});

		
		$('input:text[name="t3a"]').change(function() {
			
		var t3bVal = $('input:text[name="t3b"]').val();
			
				if(t3bVal ==''){
				
				$('input:text[name="t3b"]').focus();
				}
		});
	
		$('input:text[name="t3c"]').change(function() {
			
			var t3cVal = $('input:text[name="t3d"]').val();		
			if(t3bVal ==''){	
			$('input:text[name="t3d"]').focus();
				}
			});
		
		
		$('input:text[name="t3e"]').change(function() {
			
			var t3cVal = $('input:text[name="t3f"]').val();		
			if(t3bVal ==''){	
			$('input:text[name="t3f"]').focus();
				}
			});
		
		$('input:text[name="t3g"]').change(function() {
			
			var t3cVal = $('input:text[name="t3h"]').val();		
			if(t3bVal ==''){	
			$('input:text[name="t3h"]').focus();
				}
			});
		
		$('input:radio[name="r2"]')
				.click(
						function() {
							$('input:text[name="r1"]').attr("disabled", true);
							if ($('input:radio[name="r1"]').is(":checked") == false) {
								alert("1번 문항을 입력하지 않았습니다!")
								$('input:text[name="r1"]').attr("disabled",
										false);
								$('input:radio[class="r1"]:not(:checked)')
										.attr("disabled", false);
							
							} else if ($('input:radio[name="r2"]').is(
									":checked") == true
									&& $('input:text[name="t3a"]').val() === 0 && $('input:text[name="t3b"]').val() === 0) {
								alert("1번 텍스트 문항을 입력하지 않았습니다!")
								$('input:text[name="t3a"]').focus();
							
							} else if ($('input:text[name="t3c"]').val().length === 0
									&& $('input:text[name="t3d"]').val().length === 0) {
								alert("2번 텍스트 문항을 입력하지 않았습니다!")
							}

							var r2val = $(this).val();

					
							$('input:text[name="t3e"]').attr("disabled", false);
							$('input:radio[name="r2"]:not(:checked)').attr(
									"disabled", false);
							setTimeout(function() {
								$('input:text[name="t3e"]').attr("disabled",
										false);
								$('input:text[name="t3e"]').focus();
							}, 0);

							if ($('input:text[name="t3e"]').is(":focus") == true) {
								setTimeout(function() {
									$('input:text[name="t3f"]').attr(
											"disabled", false);
									$('input:text[name="t3f"]').focus();
								}, 10);
							}
							if ($('input:text[name="t3f"]').is(":focus") == true) {
								setTimeout(function() {
									$('input:text[name="t3g"]').attr(
											"disabled", false);
									$('.t').find('input:text[name="t3g"]')
											.focus();
								}, 0);
							}
							if ($('input:text[name="t3g"]').is(":focus") == true) {
								setTimeout(function() {
									$('input:text[name="t3h"]').attr(
											"disabled", false);
									$('.t').find('input:text[name="t3h"]')
											.focus();
								}, 0);
							}

						});

		$('input:radio[name="r3"]')
				.click(
						function() {

							if ($('input:radio[name="r1"]').is(":checked") == false) {
								alert("1번 문항을 입력하지 않았습니다!")
								$('input:radio[class="r1"]:not(:checked)')
										.attr("disabled", false);
								$('input:radio[name="r2"]').prop("checked",
										false);
							} else if ($('input:radio[name="r1"]').is(
									":checked") == true
									&& $('input:text[name="t3a"]').val().length === 0
									&& $('input:text[name="t3b"]').val().length === 0) {
								alert("1번 텍스트 문항을 입력하지 않았습니다!")
								$('input:text[name="t3a"]').focus();
								if ($('input:text[name="t3a"]').is(":focus") == true) {
									setTimeout(function() {
										$('input:text[name="t3b"]').attr(
												"disabled", false);
										$('.t').find('input:text[name="t3b"]')
												.focus();
									}, 0);
								}
							} else if ($('input:radio[name="r2"]').is(
									":checked") == true
									&& $('input:text[name="t3e"]').val().length === 0
									&& $('input:text[name="t3f"]').val().length === 0) {
								alert("3번 텍스트 문항을 입력하지 않았습니다!")
								$('input:text[name="t3e"]').focus();
								if ($('input:text[name="t3e"]').is(":focus") == true) {
									setTimeout(function() {
										$('input:text[name="t3f"]').attr(
												"disabled", false);
										$('.t').find('input:text[name="t3f"]')
												.focus();
									}, 0);
								}
							} 

						});

		$('input:radio[name="r1"]').change(function() {
			//	var r2Chkval = $('input:radio[name="r2"]:checked').val();
			$('input:text[class="t"]').attr("disabled", false);
			$('input:text[name="t3a"]').attr("disabled", false);
			$('input:text[name="t3a"]').val('');
			$('input:text[name="t3a"]').focus();

			if ($('input:text[name="t3a"]').is(":focus") == true) {
				setTimeout(function() {
					$('input:text[name="t3b"]').attr("disabled", false);
					$('.t').find('input:text[name="t3b"]').focus();
				}, 10);
			}
			if ($('input:text[name="t3b"]').is(":focus") == true) {
				setTimeout(function() {
					$('input:text[name="t3c"]').attr("disabled", false);
					$('.t').find('input:text[name="t3c"]').focus();
				}, 10);
			}
			if ($('input:text[name="t3c"]').is(":focus") == true) {
				setTimeout(function() {
					$('input:text[name="t3d"]').attr("disabled", false);
					$('.t').find('input:text[name="t3d"]').focus();
				}, 10);
			}

		});

		$('#insert')
				.click(
						function() {

							//배열선언

							var radioArr = [];
							var textArr = [];
							var tcolNmArr = [];
							var sdates = $("#sdate").val();
							var exam_num = $("#selectionNum").val();
							var exam_idx = $("#examIdx").val();
							var exam_id = $("#exid").val();
							console.log("exam_num::" + exam_num)
							console.log("exam_idx::" + exam_idx)
							console.log("설문실행일:" + sdates);
							var whichChk = $("#chk").val();
							console.log(whichChk + ":::whichChk")
							var cdbs = $("#cdbs").val();

							if (sdates == '') {
								alert("설문실행일을 체크해주세요!")
								return false;
							}

							var chk_radio = $('input:radio[class="r"]')

							var sel_val;
							for (var i = 0; i < chk_radio.length; i++) {

								if (chk_radio.eq(i).is(":checked") == true) {

									sel_val = chk_radio.eq(i).val();
									//	alert(sel_val)
								}


							}

							var value;
							$('input:radio[class="r"]:checked')
									.each(
											function(i) {//체크된 리스트 저장

												value = $(this).val();
												console.log(value
														+ "::::::[음주]values");

												var Fval = $('input:radio[class="r"]:checked').length;
												console.log(Fval + "체크한카운트")
												if (Fval < 22) {
													alert("체크되지 않는 입력값이 있습니다!")
													return false;
												} else {
													radioArr.push(value);
												}

											});

							var value1;
							var add = 0;
							$('input:text[class="t"]').each(
									function(i) {

								var	checkV = $(this).length;
									var checkIdx = $(this).index();
										value1 = $(this).val();
										getNm = $(this).attr('name');
										
										
										if(value1 != '' &&  getNm !=''){
											console.log(getNm+":::::::::::getNm["+i+"]");
											console.log(value1+":[pha 텍스트]values1["+i+"]");
												 textArr.push(value1);		
												 tcolNmArr.push(getNm);	 
										}
									
							
									
									});

							var num;
							var num1;
							num = $('input:radio[class="r"]:checked').length;
							console.log(num + "저장된체크넘버");
							num1 = $('input:radio[class="r"]:not(:checked)').length;

							var objParams = {
								"radioArr" : radioArr,
								"textArr" : textArr,
								"tcolNmArr" : tcolNmArr,
								"sdate" : sdates,
								"exam_id" : exam_id,
								"idx" : idx,
								"whichChk" : whichChk,
								"exam_num" : exam_num,
								"exam_idx" : exam_idx,
							    "cdbs" : cdbs
							};

							//ajax호출
							$.ajax({
										url : getContextPath()
												+ "/include/sheet/view_savesp.do",
										dataType : "json",
										contentType : "application/x-www-form-urlencoded; charset=UTF-8",
										type : "post",
										data : objParams,
										success : function(json) {
											if (json.model.code == "OK") {
												var obj = json.model.objIdx;
												var examN = json.model.exam_num;
												var flag = false;
												if (json.model.result == "new") {
													alert(json.model.message);
														flag = true;
														parent.load_sunbyul(flag);
												} else if (json.model.result == "edit") {
													alert(json.model.message);
													flag = true;
													parent.load_sunbyul(flag);
												}
											} else {
												alert("등록을 실패하였습니다 다시시도해주세요!")
											}
										},
										error : function(request, status, error) {
											console.log("AJAX_ERROR");
										}

									});

						});

		//if($("input:radio[name=r"+i+"]").is("checked") == true){

		//	$("input:radio[name=r"+i+"]").attr("disabled", fal); //비활성화	

		//}				
		//var sdateVal = ('input:text[name="sdate"]').value();
		//	var sdateVal = document.getElementById('sdate').value();	
		//var sdateVal = $('#ddate').val();	
		//var ddateVal = $('#ddate').val();
		var sdateVal = $('input:text[name="sdate"]').attr('value')
		var date = $("calendar").val();
		console.log(date);
		console.log("sdateVal앞::" + sdateVal);

		$('input:radio[class="r"]:checked').attr("disabled", false);
		$('input:radio[class="r"]:not(:checked)').attr("disabled", true);
		// $('input:radio[name="r2"]').attr("disabled", true); //비활성화
		$('input:text[class="t"]').attr("disabled", true);

		$('input:text[name="sdate"]').attr("disabled", true);

	});
</script>
<body>
	<%!/*
		public String make_radio(String v) {
			String checked1 = "";
			String checked2 = "";
			if ( "1".equals(v) ) checked1 = "checked";
			if ( "2".equals(v) ) checked2 = "checked";
			String radioTag = "<input type='radio' class='r' " + checked1 + " style='left:16px;top:5px;'>"
			+ "<input type='radio' class='r'  " + checked2 + " style='left:100px;top:5px;'>";
			return radioTag;
		}
	
		public String make_radio_v(String v) {
			String checked1 = "";
			String checked2 = "";
			String checked3 = "";
			String checked4 = "";
			String checked5 = "";
			String checked6 = "";
	
			if ( "1".equals(v) ) checked1 = "checked";
			if ( "2".equals(v) ) checked2 = "checked";
			if ( "3".equals(v) ) checked3 = "checked";
			if ( "4".equals(v) ) checked3 = "checked";
			if ( "4".equals(v) ) checked3 = "checked";
			if ( "5".equals(v) ) checked3 = "checked";
			if ( "6".equals(v) ) checked3 = "checked";
			
			String radioTag = "<input type='radio' class='r' " + checked1 + " style='left:16px;top:4px;'>"
			+ "<input type='radio' class='r'  " + checked2 + " style='left:100px;top:4px;'>"
			+ "<input type='radio' class='r'  " + checked3 + " style='left:190px;top:4px;'>"
			+ "<input type='radio' class='r'  " + checked4 + " style='left:280px;top:4px;'>"
			+ "<input type='radio' class='r'  " + checked5 + " style='left:360px;top:4px;'>"
			+ "<input type='radio' class='r'  " + checked6 + " style='left:450px;top:4px;'>";
			
			return radioTag;
		}
		*/%>
	<div id="btnArea" class="btn_wrap top">
		<input type="hidden" id="action" value="${action}"
			style="display: none;" />
		<!-- <button name="저장" id="insert" class ="csbr1010btn" style="display:none;">저장</button> -->
		<button name="수정" id="edit" class="csbr1010btn" value="edit">수정</button>
		<button name="신규" id="new" class="csbr1010btn" value="new">신규</button>

		<input type="hidden" id="object_idx" style="display: none;"
			class="object_idx"
			value='<%=spvo == null ? "" : StringUtil.NVL(spvo.getObjectIdx())%>'>
		<input type='hidden' name='selectionNum' id="selectionNum"
			value='<%=spvo == null ? "" : StringUtil.NVL(spvo.getSelectionNum())%>'>
		<input type='hidden' name='examIdx' id="examIdx"
			value='<%=spvo == null ? "" : StringUtil.NVL(spvo.getIdx())%>'>
		<input name="idx" id="idx" class="idx" value="${idx}"
			style="display: none;" /> <input type="hidden" name="exid" id="exid"
			class="exid" value="${exam_id}" style="display: none;" /> <input
			type="hidden" name="exnum" id="exnum" class="exnum"
			value="${exam_num}" style="display: none;" /> 
				<input type ="hidden" name="cdbs" id ="cdbs" class ="cdbs" value="0019" style="display:none;"/>
			<input type="hidden"
			name="chk" id="chk" class="chk" value="" style="display: none;" />
	</div>
	<div id="tabbox" style="height: auto;">

		<table class="table type02">
			<colgroup>
				<col width="40px" />
				<col width="*" />
				<col width="80px" />
				<col width="80px" />
				<col width="80px" />
				<col width="80px" />
			</colgroup>
			<thead>
				<tr>
					<th class="bg1 tbl_tit" colspan="6">수면</th>
				</tr>
				<tr>
					<th class="survey_date" colspan="6">
						<div class="sv_text">설문실행일</div>
						<div class="sv_date">
							<div
								style='height: 23px; margin: 3px; left: 2em; border: 0px solid black; padding: 0px;'>
								<input type='text' class='calendar' id='sdate' name='sdate'
									placeholder='yyyy/mm/dd' style='width: 120px;'
									value='<%=spvo == null ? "" : StringUtil.NVL(DateUtil.getSimpleDate("yyyyMMdd", spvo.getTestDay()))%>'>
							</div>
						</div>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>1.</th>
					<td colspan="5" class="ta_l">
						<p>지난 한달 동안, 당신은 평소 몇 시에 잠자리에 들었습니까?</p>

						<div class="ans first">
							보통 <label
								style="display: inline-block; width: 50px; margin-left: 4px;"><input
								type='radio' name='r1' class='r' value="1"
								<%if ("1".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp01())))) {%>
								checked <%}%>><span>오전</span></label> <label
								style="display: inline-block; width: 50px;"><input
								type='radio' name='r1' class='r' value="2"
								<%if ("2".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp01())))) {%>
								checked <%}%>><span>오후</span></label> <span class="input_wrap">
								<input type='text' id="num" name='t3a' class='t' minlength="1"
								maxlength="2"
								value='<%=(spvo == null ? "" : StringUtil.NVL(spvo.getSp01a()))%>'>시
								<input type='text' id="num" name='t3b' class='t' minlength="1"
								maxlength="2"
								value='<%=(spvo == null ? "" : StringUtil.NVL(spvo.getSp01b()))%>'>분에
								잠자리에 든다
							</span>
						</div>
					</td>
				</tr>
				<tr>
					<th>2.</th>
					<td colspan="5" class="ta_l">
						<p>지난 한달 동안, 당신은 밤에 잠자리에 들어서 잠이 들기까지 보통 얼마나 오래 걸리셨습니까?</p>

						<div class="ans first">
							<span class="input_wrap"> <input type='text' id="num"
								name='t3c' class='t' minlength="1" maxlength="2"
								value='<%=(spvo == null ? "" : StringUtil.NVL(spvo.getSp02a()))%>'>시간
								<input type='text' id="num" name='t3d' class='t' minlength="1"
								maxlength="2"
								value='<%=(spvo == null ? "" : StringUtil.NVL(spvo.getSp02b()))%>'>분이
								걸린다
							</span>
						</div>
					</td>
				</tr>
				<tr>
					<th>3.</th>
					<td colspan="5" class="ta_l">
						<p>지난 한달 동안, 당신은 평소 아침 몇 시에 일어났습니까?</p>

						<div class="ans first">
							보통 <label
								style="display: inline-block; width: 50px; margin-left: 4px;"><input
								type='radio' name='r2' class='r' value="1"
								<%if ("1".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp03())))) {%>
								checked <%}%>><span>오전</span></label> <label
								style="display: inline-block; width: 50px;"><input
								type='radio' name='r2' class='r' value="2"
								<%if ("2".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp03())))) {%>
								checked <%}%>><span>오후</span></label> <span class="input_wrap">
								<input type='text' id="num" name='t3e' minlength="1"
								maxlength="2" class='t'
								value='<%=(spvo == null ? "" : StringUtil.NVL(spvo.getSp03a()))%>'>시
								<input type='text' id="num" name='t3f' minlength="1"
								maxlength="2" class='t'
								value='<%=(spvo == null ? "" : StringUtil.NVL(spvo.getSp03b()))%>'>분에
								일어난다
							</span>
						</div>
					</td>
				</tr>
				<tr>
					<th>4.</th>
					<td colspan="5" class="ta_l">
						<p>
							지난 한달 동안, 당신이 밤에 실제로 잠잔 시간은 얼마나 됩니까?<br /> (이것은 당신이 잠자리에서 보낸 시관과
							다를 수 있습니다.)
						</p>

						<div class="ans first">
							<span class="input_wrap"> 하루 밤에 <input type='text'
								id="num" name='t3g' minlength="1" maxlength="2" class='t'
								value='<%=(spvo == null ? "" : StringUtil.NVL(spvo.getSp04a()))%>'>시간
								<input type='text' id="num" name='t3h' minlength="1"
								maxlength="2" class='t'
								value='<%=(spvo == null ? "" : StringUtil.NVL(spvo.getSp04b()))%>'>분
							</span>
						</div>
					</td>
				</tr>
				<tr>
					<th>5.</th>
					<td class="ta_l">지난 한달 동안, 당신은 아래의 이유로 잠자는데 얼마나 자주 문제가 있었습니까?</td>
					<td style="padding: 0; letter-spacing: -1px;">지난 한달 동안 없었다<br />(없다)
					</td>
					<td style="padding: 0; letter-spacing: -1px;">한주에 1번보다 적게<br />(주
						1회 미만)
					</td>
					<td style="padding: 0; letter-spacing: -1px;">한 주에 1~2번 정도<br />(주
						1~2회)
					</td>
					<td style="padding: 0; letter-spacing: -1px;">한 주에 3번 이상<br />(주
						3회 이상)
					</td>
				</tr>
				<tr>
					<th class="border_dash">a.</th>
					<td class="ta_l border_dash">취침 후 30분 이내에 잠들 수 없었다.</td>
					<td class="border_dash"><label><input type='radio'
							id="k" name='r3' class='r' value="1"
							<%if ("1".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp05a())))) {%>
							checked <%}%>><span><em class="blind">지난 한달
									동안 없었다 (없다)</em></span></label></td>
					<td class="border_dash"><label><input type='radio'
							id="k" name='r3' class='r' value="2"
							<%if ("2".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp05a())))) {%>
							checked <%}%>><span><em class="blind">한주에
									1번보다 적게 (주 1회 미만)</em></span></label></td>
					<td class="border_dash"><label><input type='radio'
							id="k" name='r3' class='r' value="3"
							<%if ("3".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp05a())))) {%>
							checked <%}%>><span><em class="blind">한 주에
									1~2번 정도 (주 1~2회)</em></span></label></td>
					<td class="border_dash"><label><input type='radio'
							id="k" name='r3' class='r' value="4"
							<%if ("4".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp05a())))) {%>
							checked <%}%>><span><em class="blind">한 주에 3번
									이상 (주 3회 이상)</em></span></label></td>
				</tr>
				<tr>
					<th class="border_dash">b.</th>
					<td class="ta_l border_dash">한밤중이나 새벽에 깼다.</td>
					<td class="border_dash"><label><input type='radio'
							id="k" name='r4' class='r' value="1"
							<%if ("1".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp05b())))) {%>
							checked <%}%>><span><em class="blind">지난 한달
									동안 없었다 (없다)</em></span></label></td>
					<td class="border_dash"><label><input type='radio'
							id="k" name='r4' class='r' value="2"
							<%if ("2".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp05b())))) {%>
							checked <%}%>><span><em class="blind">한주에
									1번보다 적게 (주 1회 미만)</em></span></label></td>
					<td class="border_dash"><label><input type='radio'
							id="k" name='r4' class='r' value="3"
							<%if ("3".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp05b())))) {%>
							checked <%}%>><span><em class="blind">한 주에
									1~2번 정도 (주 1~2회)</em></span></label></td>
					<td class="border_dash"><label><input type='radio'
							id="k" name='r4' class='r' value="4"
							<%if ("4".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp05b())))) {%>
							checked <%}%>><span><em class="blind">한 주에 3번
									이상 (주 3회 이상)</em></span></label></td>
				</tr>
				<tr>
					<th class="border_dash">c.</th>
					<td class="ta_l border_dash">화장실 가려고 일어나야 했다.</td>
					<td class="border_dash"><label><input type='radio'
							id="k" name='r5' class='r' value="1"
							<%if ("1".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp05c())))) {%>
							checked <%}%>><span><em class="blind">지난 한달
									동안 없었다 (없다)</em></span></label></td>
					<td class="border_dash"><label><input type='radio'
							id="k" name='r5' class='r' value="2"
							<%if ("2".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp05c())))) {%>
							checked <%}%>><span><em class="blind">한주에
									1번보다 적게 (주 1회 미만)</em></span></label></td>
					<td class="border_dash"><label><input type='radio'
							id="k" name='r5' class='r' value="3"
							<%if ("3".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp05c())))) {%>
							checked <%}%>><span><em class="blind">한 주에
									1~2번 정도 (주 1~2회)</em></span></label></td>
					<td class="border_dash"><label><input type='radio'
							id="k" name='r5' class='r' value="4"
							<%if ("4".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp05c())))) {%>
							checked <%}%>><span><em class="blind">한 주에 3번
									이상 (주 3회 이상)</em></span></label></td>
				</tr>
				<tr>
					<th class="border_dash">d.</th>
					<td class="ta_l border_dash">편안하게 숨쉴 수가 없었다.</td>
					<td class="border_dash"><label><input type='radio'
							id="k" name='r6' class='r' value="1"
							<%if ("1".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp05d())))) {%>
							checked <%}%>><span><em class="blind">지난 한달
									동안 없었다 (없다)</em></span></label></td>
					<td class="border_dash"><label><input type='radio'
							id="k" name='r6' class='r' value="2"
							<%if ("2".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp05d())))) {%>
							checked <%}%>><span><em class="blind">한주에
									1번보다 적게 (주 1회 미만)</em></span></label></td>
					<td class="border_dash"><label><input type='radio'
							id="k" name='r6' class='r' value="3"
							<%if ("3".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp05d())))) {%>
							checked <%}%>><span><em class="blind">한 주에
									1~2번 정도 (주 1~2회)</em></span></label></td>
					<td class="border_dash"><label><input type='radio'
							id="k" name='r6' class='r' value="4"
							<%if ("4".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp05d())))) {%>
							checked <%}%>><span><em class="blind">한 주에 3번
									이상 (주 3회 이상)</em></span></label></td>
				</tr>
				<tr>
					<th class="border_dash">e.</th>
					<td class="ta_l border_dash">기침을 하거나 시끄럽게 코를 골았다.</td>
					<td class="border_dash"><label><input type='radio'
							id="k" name='r7' class='r' value="1"
							<%if ("1".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp05e())))) {%>
							checked <%}%>><span><em class="blind">지난 한달
									동안 없었다 (없다)</em></span></label></td>
					<td class="border_dash"><label><input type='radio'
							id="k" name='r7' class='r' value="2"
							<%if ("2".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp05e())))) {%>
							checked <%}%>><span><em class="blind">한주에
									1번보다 적게 (주 1회 미만)</em></span></label></td>
					<td class="border_dash"><label><input type='radio'
							id="k" name='r7' class='r' value="3"
							<%if ("3".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp05e())))) {%>
							checked <%}%>><span><em class="blind">한 주에
									1~2번 정도 (주 1~2회)</em></span></label></td>
					<td class="border_dash"><label><input type='radio'
							id="k" name='r7' class='r' value="4"
							<%if ("4".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp05e())))) {%>
							checked <%}%>><span><em class="blind">한 주에 3번
									이상 (주 3회 이상)</em></span></label></td>
				</tr>
				<tr>
					<th class="border_dash">f.</th>
					<td class="ta_l border_dash">너무 춥다고 느꼈다.</td>
					<td class="border_dash"><label><input type='radio'
							id="k" name='r8' class='r' value="1"
							<%if ("1".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp05f())))) {%>
							checked <%}%>><span><em class="blind">지난 한달
									동안 없었다 (없다)</em></span></label></td>
					<td class="border_dash"><label><input type='radio'
							id="k" name='r8' class='r' value="2"
							<%if ("2".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp05f())))) {%>
							checked <%}%>><span><em class="blind">한주에
									1번보다 적게 (주 1회 미만)</em></span></label></td>
					<td class="border_dash"><label><input type='radio'
							id="k" name='r8' class='r' value="3"
							<%if ("3".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp05f())))) {%>
							checked <%}%>><span><em class="blind">한 주에
									1~2번 정도 (주 1~2회)</em></span></label></td>
					<td class="border_dash"><label><input type='radio'
							id="k" name='r8' class='r' value="4"
							<%if ("4".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp05f())))) {%>
							checked <%}%>><span><em class="blind">한 주에 3번
									이상 (주 3회 이상)</em></span></label></td>
				</tr>
				<tr>
					<th class="border_dash">g.</th>
					<td class="ta_l border_dash">너무 덥다고 느꼈다.</td>
					<td class="border_dash"><label><input type='radio'
							id="k" name='r9' class='r' value="1"
							<%if ("1".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp05g())))) {%>
							checked <%}%>><span><em class="blind">지난 한달
									동안 없었다 (없다)</em></span></label></td>
					<td class="border_dash"><label><input type='radio'
							id="k" name='r9' class='r' value="2"
							<%if ("2".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp05g())))) {%>
							checked <%}%>><span><em class="blind">한주에
									1번보다 적게 (주 1회 미만)</em></span></label></td>
					<td class="border_dash"><label><input type='radio'
							id="k" name='r9' class='r' value="3"
							<%if ("3".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp05g())))) {%>
							checked <%}%>><span><em class="blind">한 주에
									1~2번 정도 (주 1~2회)</em></span></label></td>
					<td class="border_dash"><label><input type='radio'
							id="k" name='r9' class='r' value="4"
							<%if ("4".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp05g())))) {%>
							checked <%}%>><span><em class="blind">한 주에 3번
									이상 (주 3회 이상)</em></span></label></td>
				</tr>
				<tr>
					<th class="border_dash">h.</th>
					<td class="ta_l border_dash">취침 후 30분 이내에 잠들 수 없었다.</td>
					<td class="border_dash"><label><input type='radio'
							id="k" name='r10' class='r' value="1"
							<%if ("1".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp05h())))) {%>
							checked <%}%>><span><em class="blind">지난 한달
									동안 없었다 (없다)</em></span></label></td>
					<td class="border_dash"><label><input type='radio'
							id="k" name='r10' class='r' value="2"
							<%if ("2".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp05h())))) {%>
							checked <%}%>><span><em class="blind">한주에
									1번보다 적게 (주 1회 미만)</em></span></label></td>
					<td class="border_dash"><label><input type='radio'
							id="k" name='r10' class='r' value="3"
							<%if ("3".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp05h())))) {%>
							checked <%}%>><span><em class="blind">한 주에
									1~2번 정도 (주 1~2회)</em></span></label></td>
					<td class="border_dash"><label><input type='radio'
							id="k" name='r10' class='r' value="4"
							<%if ("4".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp05h())))) {%>
							checked <%}%>><span><em class="blind">한 주에 3번
									이상 (주 3회 이상)</em></span></label></td>
				</tr>
				<tr>
					<th class="border_dash">i.</th>
					<td class="ta_l border_dash">나쁜 꿈을 꾸었다.</td>
					<td class="border_dash"><label><input type='radio'
							id="k" name='r11' class='r' value="1"
							<%if ("1".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp05i())))) {%>
							checked <%}%>><span><em class="blind">지난 한달
									동안 없었다 (없다)</em></span></label></td>
					<td class="border_dash"><label><input type='radio'
							id="k" name='r11' class='r' value="2"
							<%if ("2".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp05i())))) {%>
							checked <%}%>><span><em class="blind">한주에
									1번보다 적게 (주 1회 미만)</em></span></label></td>
					<td class="border_dash"><label><input type='radio'
							id="k" name='r11' class='r' value="3"
							<%if ("3".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp05i())))) {%>
							checked <%}%>><span><em class="blind">한 주에
									1~2번 정도 (주 1~2회)</em></span></label></td>
					<td class="border_dash"><label><input type='radio'
							id="k" name='r11' class='r' value="4"
							<%if ("4".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp05i())))) {%>
							checked <%}%>><span><em class="blind">한 주에 3번
									이상 (주 3회 이상)</em></span></label></td>
				</tr>
				<tr>
					<th rowspan="3" class="border_dash">j.</th>
					<td colspan="5" class="ta_l border_dash">그 외에 다른 이유가 있다면
						기입해주세요.</td>
				</tr>
				<tr>
					<td colspan="5" class="ta_l border_dash">이유는? <input
						type='text' id="reason" name='t3i' minlength="1" maxlength="50"
						class='t'
						value='<%=(spvo == null ? "" : StringUtil.NVL(spvo.getSp05ja()))%>'
						style='width: 428px;'></td>
				</tr>
				<tr>
					<td class="ta_l border_dash">지난 한달 동안, 당신은 위에 기입한 이유들 때문에 잠자는
						데 얼마나 자주 어려움이 있었습니까?</td>
					<td class="border_dash"><label><input type='radio'
							id="k" name='r12' class='r' value="1"
							<%if ("1".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp05jb())))) {%>
							checked <%}%>><span><em class="blind">지난 한달
									동안 없었다 (없다)</em></span></label></td>
					<td class="border_dash"><label><input type='radio'
							id="k" name='r12' class='r' value="2"
							<%if ("2".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp05jb())))) {%>
							checked <%}%>><span><em class="blind">한주에
									1번보다 적게 (주 1회 미만)</em></span></label></td>
					<td class="border_dash"><label><input type='radio'
							id="k" name='r12' class='r' value="3"
							<%if ("3".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp05jb())))) {%>
							checked <%}%>><span><em class="blind">한 주에
									1~2번 정도 (주 1~2회)</em></span></label></td>
					<td class="border_dash"><label><input type='radio'
							id="k" name='r12' class='r' value="4"
							<%if ("4".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp05jb())))) {%>
							checked <%}%>><span><em class="blind">한 주에 3번
									이상 (주 3회 이상)</em></span></label></td>
				</tr>
				<tr>
					<th>6.</th>
					<td colspan="5" class="ta_l">
						<p>지난 한달 동안, 당신은 전반적으로 수면의 질이 어느 정도라고 평가하십니까?</p>

						<div class="ans first col_4">
							<label><input type='radio' id="k1" name='r13' class='r'
								value="1"
								<%if ("1".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp06())))) {%>
								checked <%}%>><span>매우 좋음</span></label> <label><input
								type='radio' id="k1" name='r13' class='r' value="2"
								<%if ("2".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp06())))) {%>
								checked <%}%>><span>상당히 좋음</span></label> <label><input
								type='radio' id="k1" name='r13' class='r' value="3"
								<%if ("3".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp06())))) {%>
								checked <%}%>><span>상당히 나쁨</span></label> <label><input
								type='radio' id="k1" name='r13' class='r' value="4"
								<%if ("4".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp06())))) {%>
								checked <%}%>><span>매우 나쁨</span></label>
						</div>
					</td>
				</tr>
				<tr>
					<th>7.</th>
					<td colspan="5" class="ta_l">
						<p>지난 한달 동안, 당신은 잠이 들기 위해 얼마나 자주 약을 복용 했습니까?</p>

						<div class="ans first col_4">
							<label><input type='radio' id="k1" name='r14' class='r'
								value="1"
								<%if ("1".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp07())))) {%>
								checked <%}%>><span>지난 한달동안 없었다</span></label> <label><input
								type='radio' id="k1" name='r14' class='r' value="2"
								<%if ("2".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp07())))) {%>
								checked <%}%>><span>한 주에 1번보다 적게</span></label> <label><input
								type='radio' id="k1" name='r14' class='r' value="3"
								<%if ("3".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp07())))) {%>
								checked <%}%>><span>한 주에 1~2번 정도</span></label> <label><input
								type='radio' id="k1" name='r14' class='r' value="4"
								<%if ("4".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp07())))) {%>
								checked <%}%>><span>한 주에 3번 이상</span></label>
						</div>
					</td>
				</tr>
				<tr>
					<th>8.</th>
					<td colspan="5" class="ta_l">
						<p>지난 한달 동안, 당신은 운전하거나, 식사 때 혹은 사회활동을 하는 동안 얼마나 자주 졸음을 느꼈습니까?</p>

						<div class="ans first col_4">
							<label><input type='radio' id="k1" name='r15' class='r'
								value="1"
								<%if ("1".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp08())))) {%>
								checked <%}%>><span>지난 한달동안 없었다</span></label> <label><input
								type='radio' id="k1" name='r15' class='r' value="2"
								<%if ("2".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp08())))) {%>
								checked <%}%>><span>한 주에 1번보다 적게</span></label> <label><input
								type='radio' id="k1" name='r15' class='r' value="3"
								<%if ("3".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp08())))) {%>
								checked <%}%>><span>한 주에 1~2번 정도</span></label> <label><input
								type='radio' id="k1" name='r15' class='r' value="4"
								<%if ("4".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp08())))) {%>
								checked <%}%>><span>한 주에 3번 이상</span></label>
						</div>
					</td>
				</tr>
				<tr>
					<th>9.</th>
					<td colspan="5" class="ta_l">
						<p>지난 한달 동안, 당신은 일에 열중하는데 얼마나 많은 문제가 있었습니까?</p>

						<div class="ans first col_4">
							<label><input type='radio' id="k1" name='r16' class='r'
								value="1"
								<%if ("1".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp09())))) {%>
								checked <%}%>><span>전혀 없었다</span></label> <label><input
								type='radio' id="k1" name='r16' class='r' value="2"
								<%if ("2".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp09())))) {%>
								checked <%}%>><span>매우 조금 있었다</span></label> <label><input
								type='radio' id="k1" name='r16' class='r' value="3"
								<%if ("3".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp09())))) {%>
								checked <%}%>><span>다소 있었다</span></label> <label><input
								type='radio' id="k1" name='r16' class='r' value="4"
								<%if ("4".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp09())))) {%>
								checked <%}%>><span>매우 많이 있었다</span></label>
						</div>
					</td>
				</tr>
				<tr>
					<th rowspan="2">10.</th>
					<td colspan="5" class="ta_l">
						<p>당신은 다른 사람과 같은 잠자리에 자거나 집을 같이 쓰는 사람이 있습니까?</p>

						<div class="ans first">
							<label><input type='radio' name='r17' class='r' value="1"
								<%if ("1".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp10())))) {%>
								checked <%}%>><span>같은 잠자리에 자거나 집을 같이 쓰는 사람이 없다</span></label>
						</div>
						<div class="ans">
							<label><input type='radio' name='r17' class='r' value="2"
								<%if ("2".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp10())))) {%>
								checked <%}%>><span>방을 같이 쓰지만 같은 잠자리를 자지 않는다</span></label>
						</div>
						<div class="ans">
							<label><input type='radio' name='r17' class='r' value="3"
								<%if ("3".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp10())))) {%>
								checked <%}%>><span>집에 다른 방을 쓰는 사람이 있다</span></label>
						</div>
						<div class="ans">
							<label><input type='radio' name='r17' class='r' value="4"
								<%if ("4".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp10())))) {%>
								checked <%}%>><span>같은 잠자리에 자는 사람이 있다</span></label>
						</div>
					</td>
				</tr>
				<tr>
					<td class="ta_l">만일 같은 방을 쓰거나 같은 잠자리에서 자는 사람이 있다면, 그 사람에게 지난 한
						달간, 당신이 다음과 같은 행동을 얼마나 자주 했는지 물어보십시오.</td>
					<td style="padding: 0; letter-spacing: -1px;">지난 한달 동안 없었다<br />(없다)
					</td>
					<td style="padding: 0; letter-spacing: -1px;">한주에 1번보다 적게<br />(주
						1회 미만)
					</td>
					<td style="padding: 0; letter-spacing: -1px;">한 주에 1~2번 정도<br />(주
						1~2회)
					</td>
					<td style="padding: 0; letter-spacing: -1px;">한 주에 3번 이상<br />(주
						3회 이상)
					</td>
				</tr>
				<tr>
					<th class="border_dash">a.</th>
					<td class="ta_l border_dash">심하게 코골기</td>
					<td class="border_dash"><label><input type='radio'
							name='r18' class='r' value="1"
							<%if ("1".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp10a())))) {%>
							checked <%}%>><span><em class="blind">지난 한달
									동안 없었다 (없다)</em></span></label></td>
					<td class="border_dash"><label><input type='radio'
							name='r18' class='r' value="2"
							<%if ("2".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp10a())))) {%>
							checked <%}%>><span><em class="blind">한주에
									1번보다 적게 (주 1회 미만)</em></span></label></td>
					<td class="border_dash"><label><input type='radio'
							name='r18' class='r' value="3"
							<%if ("3".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp10a())))) {%>
							checked <%}%>><span><em class="blind">한 주에
									1~2번 정도 (주 1~2회)</em></span></label></td>
					<td class="border_dash"><label><input type='radio'
							name='r18' class='r' value="4"
							<%if ("4".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp10a())))) {%>
							checked <%}%>><span><em class="blind">한 주에 3번
									이상 (주 3회 이상)</em></span></label></td>
				</tr>
				<tr>
					<th class="border_dash">b.</th>
					<td class="ta_l border_dash">잠잘 때 숨을 한동안 멈추고 다시 숨쉬기</td>
					<td class="border_dash"><label><input type='radio'
							name='r19' class='r' value="1"
							<%if ("1".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp10b())))) {%>
							checked <%}%>><span><em class="blind">지난 한달
									동안 없었다 (없다)</em></span></label></td>
					<td class="border_dash"><label><input type='radio'
							name='r19' class='r' value="2"
							<%if ("2".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp10b())))) {%>
							checked <%}%>><span><em class="blind">한주에
									1번보다 적게 (주 1회 미만)</em></span></label></td>
					<td class="border_dash"><label><input type='radio'
							name='r19' class='r' value="3"
							<%if ("3".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp10b())))) {%>
							checked <%}%>><span><em class="blind">한 주에
									1~2번 정도 (주 1~2회)</em></span></label></td>
					<td class="border_dash"><label><input type='radio'
							name='r19' class='r' value="4"
							<%if ("4".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp10b())))) {%>
							checked <%}%>><span><em class="blind">한 주에 3번
									이상 (주 3회 이상)</em></span></label></td>
				</tr>
				<tr>
					<th class="border_dash">c.</th>
					<td class="ta_l border_dash">잠잘 때 다리를 갑자기 떨거나 흔들기</td>
					<td class="border_dash"><label><input type='radio'
							name='r20' class='r' value="1"
							<%if ("1".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp10c())))) {%>
							checked <%}%>><span><em class="blind">지난 한달
									동안 없었다 (없다)</em></span></label></td>
					<td class="border_dash"><label><input type='radio'
							name='r20' class='r' value="2"
							<%if ("2".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp10c())))) {%>
							checked <%}%>><span><em class="blind">한주에
									1번보다 적게 (주 1회 미만)</em></span></label></td>
					<td class="border_dash"><label><input type='radio'
							name='r20' class='r' value="3"
							<%if ("3".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp10c())))) {%>
							checked <%}%>><span><em class="blind">한 주에
									1~2번 정도 (주 1~2회)</em></span></label></td>
					<td class="border_dash"><label><input type='radio'
							name='r20' class='r' value="4"
							<%if ("4".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp10c())))) {%>
							checked <%}%>><span><em class="blind">한 주에 3번
									이상 (주 3회 이상)</em></span></label></td>
				</tr>
				<tr>
					<th class="border_dash">d.</th>
					<td class="ta_l border_dash">잠자다가 잠시 시간, 장소, 상황을 인직하지 못하거나
						혼란스러워함</td>
					<td class="border_dash"><label><input type='radio'
							name='r21' class='r' value="1"
							<%if ("1".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp10d())))) {%>
							checked <%}%>><span><em class="blind">지난 한달
									동안 없었다 (없다)</em></span></label></td>
					<td class="border_dash"><label><input type='radio'
							name='r21' class='r' value="2"
							<%if ("2".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp10d())))) {%>
							checked <%}%>><span><em class="blind">한주에
									1번보다 적게 (주 1회 미만)</em></span></label></td>
					<td class="border_dash"><label><input type='radio'
							name='r21' class='r' value="3"
							<%if ("3".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp10d())))) {%>
							checked <%}%>><span><em class="blind">한 주에
									1~2번 정도 (주 1~2회)</em></span></label></td>
					<td class="border_dash"><label><input type='radio'
							name='r21' class='r' value="4"
							<%if ("4".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp10d())))) {%>
							checked <%}%>><span><em class="blind">한 주에 3번
									이상 (주 3회 이상)</em></span></label></td>
				</tr>
				<tr>
					<th rowspan="2" class="border_dash"
						style="border-bottom: 1px solid #c4d9f6;">e.</th>
					<td class="ta_l border_dash">잠자는 동안 다른 뒤척거리 행동</td>
					<td class="border_dash"><label><input type='radio'
							name='r22' class='r' value="1"
							<%if ("1".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp10e())))) {%>
							checked <%}%>><span><em class="blind">지난 한달
									동안 없었다 (없다)</em></span></label></td>
					<td class="border_dash"><label><input type='radio'
							name='r22' class='r' value="2"
							<%if ("2".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp10e())))) {%>
							checked <%}%>><span><em class="blind">한주에
									1번보다 적게 (주 1회 미만)</em></span></label></td>
					<td class="border_dash"><label><input type='radio'
							name='r22' class='r' value="3"
							<%if ("3".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp10e())))) {%>
							checked <%}%>><span><em class="blind">한 주에
									1~2번 정도 (주 1~2회)</em></span></label></td>
					<td class="border_dash"><label><input type='radio'
							name='r22' class='r' value="4"
							<%if ("4".equals((spvo == null ? "" : StringUtil.NVL(spvo.getSp10e())))) {%>
							checked <%}%>><span><em class="blind">한 주에 3번
									이상 (주 3회 이상)</em></span></label></td>
				</tr>
				<tr>
					<td class="ta_l border_dash">뒤척거리는 행동들이 었었으면 직접 기입해주십시오</td>
					<td colspan="4" class="ta_l border_dash"><input type='text'
						id="reason" name='t3j' minlength="1" maxlength="50" class='t'
						value='<%=(spvo == null ? "" : StringUtil.NVL(spvo.getSp10e2()))%>'
						style='width: 298px;'></td>
				</tr>
			</tbody>
		</table>

	</div>


	<!-- 저장 버튼을 하단에 배치 -->
	<div id="btnArea" class="btn_wrap btm">
		<button name="저장" id="insert" class="csbr1010btn" style="">저장</button>
	</div>



</body>
<script>
	var sdateVal = $('#sdate').val();

	console.log("sdateVal::" + sdateVal);
</script>
</html>
