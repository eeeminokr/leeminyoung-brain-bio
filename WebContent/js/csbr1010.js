


var GLOBAL_CONTEXT_PATH ='${pageContext.request.contextPath}';
	var mmse_count = 0;
	var sunbyul_count = 0;
	var jungmil_count = 0;
	var etc_count = 0;
	var is_memclick = false; //명단 클릭시에는 모든 검사결과리스트에서 클릭 이벤트가 일어나지 않도록
	var userObjectPopup = {};
	$(function(){

		
		
	/*
		$("#btn_insert_idx").click(function() {	  
			insert_ObjectIdx(idx_popup);
		});
	*/
		$("#btn_search").click(function(){
			load_search();
		});

		
		$("#btn_excel").click(function(){
			var st_name = $("#st_name").val();
			var st_idx = $("#st_idx").val();
			var st_jumin = $("#st_jumin").val();
			var st_telno = $("#st_telno").val();
			var st_consult = $("#st_consult").val();
			var ch = st_name + '' + st_idx + '' + st_jumin + '' + st_telno + '' + st_consult;
			if(ch==''){
				alert('검색항목을 입력해주세요');
				return false;
			}
			location.href=(getContextPath() + '/include/csbr1010_print.do?xls=1&st_name=' + st_name + '&st_idx=' + st_idx + '&st_jumin=' + st_jumin + '&st_telno=' + st_telno + '&st_consult=' + st_consult);
		});

		$("#btn_close").click(function(){
			sub_close();
		});

		$(window).resize(function(){
			resize();
		});

		$("#st_name, #st_idx, #st_jumin, #st_telno, #st_consult").keypress(function(e){
			if ( e.which == 13 ){
				$("#btn_search").click();
			}
		});

		resize();

		is_memclick = true;
		load_info();

	});

	 var windowValue;
	 function insert_ObjectIdx(idx_popup) { 
	
		 document.get
		 $('.'+idx_popup).show();
		 $(".shadow").show(); //부모창 어둡게하기
		 
	
		 var targetTitle = 'csbr1010_insertIdx_OpenPopUp';
		 var popupWidth = 1000;
		 var popupHeight = 450;

		 // 듀얼 모니터 기준
		 var left = (screen.availWidth - popupWidth) / 2;
		 if( window.screenLeft < 0){
		 left += window.screen.width*-1;
		 }
		 else if ( window.screenLeft > window.screen.width ){
		 left += window.screen.width;
		 }

		 var top = (screen.availHeight - popupHeight) / 2 - 10;

		 var url = getContextPath() +"/include/target/csbr1010_insertIdx_OpenPopUp.do";
		 var options = 'resizable=no,left=' + left + ',top=' + top +', width=' + popupWidth+ ',height=' + popupHeight +',menubar=no, status=no, toolbar=no, location=no, scrollbars=yes';
		 windowValue = 	 window.open(url, targetTitle,options);
	/*	 
		 var popupX = (document.body.offsetWidth / 2) - (1000 / 2);
		// 만들 팝업창 좌우 크기의 1/2 만큼 보정값으로 빼주었음

		var popupY= (document.body.offsetHeight / 2) - (450 / 2);
		// 만들 팝업창 상하 크기의 1/2 만큼 보정값으로 빼주었음


	windowValue = window.open(,
		 'csbr1010_insertIdx_OpenPopUp',
		 'scrollbars=no,status=no, height=450, width=1000, left='+ popupX + ', top='+ popupY )
	*/
	
		    var pvalues = document.getElementById("pInput").value;
		
 	 
		console.log(pvalues+"pvalues값");

		
/*
		$(window).addEventListener('beforeunload', function() {
			
			   return confirm("진짜 나갈거임?")

		});
		*/

		var timer = setInterval(function() {  
		    if(windowValue.closed) {  
		        clearInterval(timer);  
		    	$(".shadow").hide();
		    }  
		},500); 

	 }

	var wclose;
	function parent_function() {
		console.log("자식창에서 호출")
	//	$(".shadow").hide();

		//console.log(wclose+"close값")
		//$(".shadow").hide();
	// 	window.closed = wclose;

	//	console.log(window.closed);
	
	
	}
	

	


		  
	
	 
	 

	
	function load_search() {
		$("#selected_object_idx").val('');
		$("#selected_tabobjectid").val('');
		$("#search_list").html('');
		$("#etc_data").html('');
		$("#jungmil_data").html('');
		$("#sunbyul_data").html('');
		$("#mmse_data").html('');

		var st_name = $("#st_name").val();
		var st_idx = $("#st_idx").val();
		var st_jumin = $("#st_jumin").val();
		var st_telno = $("#st_telno").val();
		var st_consult = $("#st_consult").val();
		var ch = st_name + '' + st_idx + '' + st_jumin + '' + st_telno + '' + st_consult;
		if(ch==''){
			alert('검색항목을 입력해주세요');
			return false;
		}
		$.post(getContextPath() + "/include/data/csbr1010_search.do", {st_name:st_name, st_idx:st_idx, st_jumin:st_jumin, st_telno:st_telno, st_consult:st_consult})
		.done(function(data){
			$("#search_list").html(data).mygrid({
				cursorpointer: true,
				auto_first_select:true,
				hover:true,
				rowclick: function(sdata) {
					if($("#selected_object_idx").val() != sdata ) {
						$("#selected_object_idx").val(sdata);
						$("#selected_tabobjectid").val(sdata);
						is_memclick = true;
						load_info();
					}
				}
			});
		});
	}

	function load_info() {
		var sidx = $("#selected_object_idx").val();

		$.post(getContextPath() + "/include/data/csbr1010_info.do", {object_idx:sidx})
		.done(function(data){
			$("#info_data").html(data);

			//pdf 파일 열기
			$("#btn_pdf").unbind();
			$("#btn_pdf").click(function(){
				var sfile = $("#pdf_select").val();
				if(sfile!=''){
					window.open('pdf/' + sfile);
				}
			});
			load_right();
			load_etc();
		});
	}

	function load_right() {
		var sidx = $("#selected_object_idx").val();
		$.post(getContextPath() + "/include/data/csbr1010_right.do", {object_idx:sidx})
		.done(function(data){
			$("#right_data").html(data);
			$("#btnsave_opinion").unbind();
			$("#btnsave_remark").unbind();
			$("#btnsave_opinion").click(function(){
				$("#form_opinion").attr('action',getContextPath() + '/include/csbr1010_opinion_save.do?object_idx=' + sidx);
				$("#form_opinion").submit();

			});
			$("#btnsave_remark").click(function(){
				$("#form_opinion2").attr('action',getContextPath() + '/include/csbr1010_opinion_remark_save.do?object_idx=' + sidx);
				$("#form_opinion2").submit();

			});
		});
	}

	function load_mmse() {
		var sidx = $("#selected_object_idx").val();
		$.post(getContextPath() + "/include/data/mmse_list.do", {object_idx:sidx})
		.done(function(data){ 
			$("#mmse_data").html(data).mygrid({
				cursorpointer: true,
				auto_first_select:true,
				hover:true,
				rowclick: function(sdata) {
					console.log(sdata+"::::sadata 무슨값인가?????")
					if(is_memclick == false ) {
						var sp = sdata.split('|');
						if (sp.length != 4) return false;
						tab_nowtabcode(sp[0], sp[0], sp[1], sp[2], sp[3]);
						draw_tab();
					}
				},
				onfinish: function(s,c){
					//mmse_count = $("#mmse_data").find("#row_count").val();
					//var sdata = $("#mmse_data").find("#select_data").val(); //선택된 데이터
					mmse_count = c;
					var sdata = s;
					var sp = sdata.split('|');
					if (sp.length == 4) {
						tab_nowtabcode(sp[0], sp[0], sp[1], sp[2], sp[3]);
					}
					resize();
					draw_tab();
					is_memclick=false;
				}
			});
			$("#mmse_data").find("li").css({'padding-left':'3px', 'padding-right':'3px'});
		});
	}

	
	/*선별 &정밀 조회값 같이 뿌려짐*/
	function load_sunbyul() {
		var sidx = $("#selected_object_idx").val();
		$.post(getContextPath() + "/include/data/sunbyul_list.do", {object_idx:sidx})
		.done(function(data){
			$("#sunbyul_data").html(data).mygrid({
				cursorpointer: true,
				auto_first_select:true,
				hover:true, 
				rowclick: function(sdata) {
					if(is_memclick == false ) {
						var sp = sdata.split('|');
						if (sp.length != 4) return false;
						tab_nowtabcode(sp[0], sp[0], sp[1], sp[2], sp[3]);
						draw_tab();
					}
				},
				onfinish: function(s,c){
					//sunbyul_count = $("#sunbyul_data").find("#row_count").val();
					//var sdata = $("#sunbyul_data").find("#select_data").val(); //선택된 데이터
					sunbyul_count = c;
					var sdata = s;
					console.log("sdata:"+ sdata);
					var sp = sdata.split('|');
					if (sp.length == 4) {
						tab_nowtabcode(sp[0], sp[0], sp[1], sp[2], sp[3]);
					}
					load_mmse();
				}
			});
			$("#sunbyul_data").find("li").css({'padding-left':'3px', 'padding-right':'3px'});
		});
	}

	/*
	function load_jungmil() {
		var sidx = $("#selected_object_idx").val();
		$.post(getContextPath() + "/include/data/jungmil_list.do", {object_idx:sidx})
		.done(function(data){
			$("#jungmil_data").html(data).mygrid({
				cursorpointer: true,
				auto_first_select:true,
				hover:true,
				rowclick: function(sdata) {
					if(is_memclick == false ) {
						var sp = sdata.split('|');
						if (sp.length != 4) return false;
						tab_nowtabcode(sp[0], sp[0], sp[1], sp[2], sp[3]);
						draw_tab();
					}
				},
				onfinish: function(s, c){
					//jungmil_count = $("#jungmil_data").find("#row_count").val();
					//var sdata = $("#jungmil_data").find("#select_data").val(); //선택된 데이터
					jungmil_count = c;
					var sdata = s;
					var sp = sdata.split('|');
					if (sp.length == 4) {
						tab_nowtabcode(sp[0], sp[0], sp[1], sp[2], sp[3]);
					}
					load_sunbyul();
				}
			});
			$("#sunbyul_data").find("li").css({'padding-left':'3px', 'padding-right':'3px'});
		});
	}
*/
	function load_etc() {
		var sidx = $("#selected_object_idx").val();
		$.post(getContextPath() + "/include/data/etc_list.do", {object_idx:sidx})
		.done(function(data){
			$("#etc_data").html(data).mygrid({
				cursorpointer: true,
				auto_first_select:true,
				hover:true,
				rowclick: function(sdata) {
					if(is_memclick == false ) {
						var sp = sdata.split('|');
						if (sp.length != 4) return false;
						tab_nowtabcode(sp[0], sp[0], sp[1], sp[2], sp[3]);
						draw_tab();
					}
				},
				onfinish: function(s, c){
					//etc_count = $("#etc_data").find("#row_count").val();
					//var sdata = $("#etc_data").find("#select_data").val(); //선택된 데이터
					etc_count = c;
					var sdata = s; //선택된 데이터
					var sp = sdata.split('|');
					if (sp.length == 4) {
						tab_nowtabcode(sp[0], sp[0], sp[1], sp[2], sp[3]);
					}
					//load_jungmil();
					load_sunbyul();
				}
			});
			$("#etc_data").find("li").css({'padding-left':'3px', 'padding-right':'3px'});
		});
	}


	function resize(){

		//console.log("개수들:" + mmse_count + "/" + sunbyul_count + "/" + jungmil_count + "/" + etc_count);

		var doc_width = $(window).width();
		var doc_height = $(window).height();

		if(doc_width > 1250 ) {
			$("#search_list").css({'width':'400px', 'height':(doc_height-$("#info_list").height()-60)+'px'});
		} else {
			$("#search_list").css({'width':(doc_width-900)+'px', 'height':(doc_height-$("#info_list").height()-60)+'px'});
		}

		var w = $("#search_list").width();
		var h = $("#search_list").height();
		var t = $("#search_list").position().top;
		var l = $("#search_list").position().left;

		$("#info_list").css({'left':(l)+'px','top':(t+h+5)+'px', 'width':(w)+'px'});

		var hpl = 25; //줄단 높이
		var th = 75; //상단 제목부분
		var th2 = 18; //기타검사 부분
		var mmse_height = 0;
		if(mmse_count > 5 ) mmse_height = 5 * hpl + th;
		else mmse_height= mmse_count * hpl + th;
		var sunbyul_height = 0;
		if(sunbyul_count > 7 ) sunbyul_height = 400;
			//sunbyul_height = 13 * hpl + th;
		
		else  sunbyul_height = 400
			//sunbyul_height= sunbyul_count * hpl + th;
		var etc_height = 0;
		if(etc_count > 5 ) etc_height = 393;
			//12 * hpl + th + +th2;
		else  etc_height= 393;
			//etc_height * hpl + th +th2;

		$("#mmse_list").css({'left': (l+w+5)+'px', 'top':(t)+'px', 'width':(doc_width-900-w)+'px','height':(mmse_height)+'px'});
		if(doc_width > 1250 ) {
		$("#sunbyul_list").css({'left':(l+w+5)+'px', 'height':(doc_height-$("#etc_list").height()-60)+'px','width':($("#mmse_list").width())+'px'});
		} else {
			$("#sunbyul_list").css({'left':(l+w+5)+'px', 'height':(doc_height-$("#etc_list").height()-60)+'px','width':($("#mmse_list").width())+'px'});
		}
		
		$("#etc_list").css({'left':(l+w+5)+'px','top':(t+h+5)+'px','width':($("#mmse_list").width())+'px'});
	//	$("#etc_list").css({'left':(l+w+5)+'px', 'top':($("#jungmil_list").position().top+$("#jungmil_list").height()+5)+'px','width':($("#mmse_list").width())+'px','height':(doc_height-$("#jungmil_list").position().top-$("#jungmil_list").height()-20)+'px'});
		//$("#etc_data").css('height',($("#etc_list").height()-30)+'px');

		$("#search_area").css({'width':(w + $("#mmse_list").width()+3)+'px'});

		$("#tabcontent_area").css({'left':($("#search_area").position().left+$("#search_area").width()+9)+'px','width':'876px','height':(doc_height-17)+'px'});

		//$("#opinion").css({'height':($("#right_data").height()-250)+'px'});
		//$("#opinion_text").css({'height':($("#right_data").height()-250)+'px'});

	}


