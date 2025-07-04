

$(function(){


}); //$(function(){

//원래 mb_etccd 테이블에 B002코드로 탭명칭관리가 있으며 거기서 이름을 관리함.
//편의를 위해 여기에 고정으로 입력해 놓음(수정시 여기도 수정해야 함)

		
	var tab = [
	[
		//{name:'MMSE-DS', code:'0101', url:getContextPath() + '/include/sheet/view_mmseds.do'},
		//{name:'K-MMSE',code:'0102',url:getContextPath() + '/include/sheet/view_kmmse.do'},
	//	{name:'MMSE-KC',code:'0103',url:getContextPath() + '/include/sheet/view_mmsekc.do'},
		{name:'참여동의',code:'0015',url:getContextPath() + '/include/sheet/view_ic.do'},
		{name:'Demography(인구사회학적 정보)',code:'0001',url:getContextPath() + '/include/sheet/view_demography.do'},
		{name:'흡연',code:'0016',url:getContextPath() + '/include/sheet/view_sm.do'},
		{name:'음주',code:'0017',url:getContextPath() + '/include/sheet/view_dr.do'},
		{name:'신체활동',code:'0018',url:getContextPath() + '/include/sheet/view_pha.do'},
		{name:'수면',code:'0019',url:getContextPath() + '/include/sheet/view_sp.do'},
		{name:'질병력',code:'0004',url:getContextPath() + '/include/sheet/view_medical.do'},
		{name:'Drug',code:'0006',url:getContextPath() + '/include/sheet/view_drug.do'},
		{name:'가족력',code:'0014',url:getContextPath() + '/include/sheet/view_family.do'},
		{name:'삶의 질',code:'0020',url:getContextPath() + '/include/sheet/view_eq.do'}
		],
	[
		{name:'K-MMSE2',code:'0104',url:getContextPath() + '/include/sheet/view_kmmse2.do'},
		{name:'도구적 일상생활능력',code:'0010',url:getContextPath() + '/include/sheet/view_kiadl.do'},	
		{name:'KDSQ',code:'0008',url:getContextPath() + '/include/sheet/view_kdsq.do'},
		{name:'KDSQ-V',code:'0009',url:getContextPath() + '/include/sheet/view_kdsqv.do'},
		{name:'SMCQ',code:'0013',url:getContextPath() + '/include/sheet/view_smcq.do'},
		{name:'GDS-K',code:'0007',url:getContextPath() + '/include/sheet/view_gdsk.do'},
		{name:'Memory',code:'0201',url:getContextPath() + '/include/sheet/view_memory.do'},
		{name:'판단력과 문제 해결',code:'0207',url:getContextPath() + '/include/sheet/view_cognition2_2.do'},
		{name:'Progression',code:'0208',url:getContextPath() + '/include/sheet/view_progression.do'}
	
	],
	[
		{name:'신체계측 및 활력징후',code:'0021',url:getContextPath() + '/include/sheet/view_pe.do'},
		{name:'언어',code:'0202',url:getContextPath() + '/include/sheet/view_cognition1.do'},
		{name:'인격 혹은 행동',code:'0203',url:getContextPath() + '/include/sheet/view_cognition1_2.do'},
		{name:'시간이나 장소에 대한 지남력',code:'0204',url:getContextPath() + '/include/sheet/view_cognition1_3.do'},
		{name:'일상 생활동작(ADL)',code:'0205',url:getContextPath() + '/include/sheet/view_cognition1_4.do'},
		{name:'지역, 사회, 교양, 또는 직업 활동',code:'0206',url:getContextPath() + '/include/sheet/view_cognition2_1.do'},
	//	{name:'지역, 사회, 교양, 또는 직업 활동',code:'0207',url:getContextPath() + '/include/sheet/view_cognition1_5.do'},
	],
	
	
	/*
	[
	//	{name:'HRSD-1',code:'0209',url:getContextPath() + '/include/sheet/view_hrsd1.do'},
	//	{name:'HRSD-2',code:'0210',url:getContextPath() + '/include/sheet/view_hrsd2.do'},
	//	{name:'HRSD-3',code:'0211',url:getContextPath() + '/include/sheet/view_hrsd3.do'},
		{name:'CDR-1',code:'0212',url:getContextPath() + '/include/sheet/view_cdr1.do'},
		{name:'CDR-2',code:'0213',url:getContextPath() + '/include/sheet/view_cdr2.do'},
		{name:'LAB-1',code:'0214',url:getContextPath() + '/include/sheet/view_lab1.do'},
		{name:'LAB-2 / 진단분류 / Remark',code:'0215',url:getContextPath() + '/include/sheet/view_lab2.do'},
		{name:'혈액검사',code:'0301',url:getContextPath() + '/include/sheet/view_blood.do'}
	]
	*/
	
	];








	//검사명 별로 탭 지정
	//원래 mb_etccd 테이블에 B001코드로 검사명별 탭 관리함.(수정시 여기도 수정해야 함)
	var CHTAB = [];
	//왼쪽 B001 = 오른쪽 B002
	CHTAB["0015"] = "0015";	
	CHTAB["0001"] = "0001";
	//CHTAB["0002"] = "0001";
	//CHTAB["0003"] = "0001";
	CHTAB["0004"] = "0004";
	
  //CHTAB["0003"] = "0016";
	//CHTAB["0004"] = "0017";
	//CHTAB["0005"] = "0018";
	CHTAB["0006"] = "0006";
	CHTAB["0007"] = "0007";
	CHTAB["0104"] = "0104";
	CHTAB["0008"] = "0008";
	CHTAB["0009"] = "0009";
	CHTAB["0010"] = "0010";
	CHTAB["0013"] = "0013";
	
	CHTAB["0014"] = "0014";
	CHTAB["0016"] = "0016";
	CHTAB["0017"] = "0017";
	CHTAB["0018"] = "0018";
	CHTAB["0019"] = "0019";
	CHTAB["0020"] = "0020";
	CHTAB["0021"] = "0021";
	
	


	CHTAB["0201"] = "0201";
	CHTAB["0202"] = "0202";
	CHTAB["0203"] = "0203";
	CHTAB["0204"] = "0204";
	CHTAB["0205"] = "0205";
	CHTAB["0206"] = "0206";
	CHTAB["0207"] = "0207";
	CHTAB["0208"] = "0208";
	/*원래 HRSD-1,2,3 코드들 
	CHTAB["0204"] = "0204";
	CHTAB["0205"] = "0207";
	CHTAB["0206"] = "0209";
	*/
	
	CHTAB["0209"] = "0209";
	
	CHTAB["0210"] = "0212";
	CHTAB["0212"] = "0214";
	
	
	CHTAB["0212"] = "0215";
	CHTAB["0213"] = "0215";
	
	CHTAB["0301"] = "0301";
	CHTAB["0302"] = "0302";
	CHTAB["0303"] = "0303";
	CHTAB["0304"] = "0304";
	CHTAB["0305"] = "0305";



	function draw_tab() {
		var sobjidx = $("#selected_object_idx").val(); //메인폼에 있음
		console.log(sobjidx+"fisrt idx")
		var ntabcode = $("#tab_code").val();
		
		console.log("draw_tab ntabcode 값:"+ntabcode);
	 	var geteid1;
	 	var geteidx1;
	 	var getenums1;
	 	var getsidx1; 
	 	var geteday1;

	
			var  eid;
			var eidx;
			var enums;
			var sidx;
			var eday;
			
			var getSid;
    		var getSidx;
    		var getSnum;
    		var getSsidx;
			
			$.ajax({
				url :   getContextPath()+"/include/data/load_selTablist.do",
				//dataType    :   "text",
		      //  contentType :   "application/x-www-form-urlencoded; charset=UTF-8",
		        type        :   "post",
		        data        : {object_idx:sobjidx ,examId : ntabcode},
				 error       :   function(request, status, error){
		                console.log("AJAX_ERROR");
		            }			
			
				}).done(function(data) {
					 
					console.log(data.examId+":eid들어갔다")
                	console.log(data.examIdx+":eid들어갔다")
                	console.log(data.examNum+":eid들어갔다")
                	console.log(data.subjectIdx+":eid들어갔다")
                	console.log(data.examDay+":eid들어갔다")
                geteid1  = $("#tab_exam_ids").val(data.examId);
				geteidx1 =  $("#tab_exam_idxs").val(data.examIdx);
				getnums1 =  $("#tab_exam_nums").val(data.examNum);
				getsidx1 =  $("#tab_exam_sidx").val(data.subjectIdx);               	
				getexday1 =  $("#tab_exam_day").val(data.examDay); 
				
                 	console.log(geteid1+"geteid")
                 	console.log(geteidx1+"geteidx")   
                 	console.log(getenums1+"getenums")   
                 	console.log(getexday1 +"getsidx")   
 
            		 getSid = $("#tab_exam_ids").val();
            		 getSidx = $("#tab_exam_idxs").val();
            		 getSnum = $("#tab_exam_nums").val();
            		 getSsidx = $("#tab_exam_sidx").val();
            		 getSday = $("#tab_exam_days").val();
            	
            		console.log("getSid::"+getSid);	
            		console.log("getSidx::"+getSidx);
            		console.log("getSnum::"+getSnum);
            		console.log("getSsidx::"+getSsidx);
            		console.log("getSday::"+getSday);
				
            		var url = "";
            		if(ntabcode=='') ntabcode="0015"; //기본선택탭
            		$("#tab_code").val(ntabcode);
            		//현재 탭이 몇번째 줄에 있는지 확인
            		var ntab_line = 0;
            		for(var i = 0; i <= 2; i++ ) {
            			var line = "tab_line" + (i+1);
            			$("#"+line).html('');
            			for(var k=0; k<tab[i].length;k++) {
            				var n = tab[i][k];	
            				if(n.code==ntabcode) {
            					ntab_line = i+1;
            					break;
            				}
            			}
            		}
            		if(ntab_line == 0 ) {
            			ntabcode="0015";
            			ntab_line = 1;
            			$("#tab_code").val(ntabcode);
            		}
            		//alert("현재탭코드:" + ntabcode);
            		var lines = [];
            		if(ntab_line==1){
            			lines = [2,3,1];
            		} else if(ntab_line==2){
            			lines = [3,1,2];
            		} else if(ntab_line==3){
            			lines = [1,2,3];
            		}
            		var exid;
            		for(var i = 0; i <= 2; i++ ) {
            			var line = "tab_line" + (i+1);
            			$("#"+line).html('');
            			var ni = lines[i];
            			ni = ni -1;
            			for(var k=0; k<tab[ni].length;k++) {
            				var n = tab[ni][k];
            				console.log(n.code+"::code")
            				console.log(ntabcode +"::ntancode")
            				if(n.code==ntabcode) {
            					$("#" + line).append("<span class='tab_item_selected' code='"+ n.code +"'>"+n.name+"</span>");
            					url = n.url;
            					exid = n.code;
            					console.log("code::"+exid);

            				} else {
            					$("#" + line).append("<span class='tab_item' code='"+ n.code +"'>"+n.name+"</span>");
            				}
            			}
            		}
            		
            		
            	
            		//현재 선택된 환자 번호
            		var tid = $("#tab_exam_id").val();     // mb_ettcd CDBS 각 테이블 마다의 코드 명

            	
            		console.log(tid+"::::::tid 값")
            		var tidx = $("#tab_exam_idx").val();   // 해당 테이블 idx 시퀀스 알리아스 명(exam_idx)
            	
            		console.log(tidx+"::tidx값은 무엇인가?");
            		var tnum = $("#tab_exam_num").val();  // 해당 테이블 selection_num 
            		console.log(tnum+":::tnum")
            		var tday = $("#tab_exam_day").val();
            		
            	
           	$("#tab_iframe").attr('src',url + "?object_idx=" + sobjidx + "&exam_id=" + ntabcode + "&exam_idx=" + tidx + "&exam_num=" + tnum + "&exam_day=" + tday);	
            		
           // 		$("#tab_iframe").attr('src',url + "?object_idx=" + sobjidx + "&exam_id=" + ntabcode + "&exam_idx=" + getSidx + "&exam_num=" + getSnum + "&exam_day=" +  getSday);	
				
            		$(".tab_item").click(function(){
            			
            		
            			var ntab = $(this).attr('code');
            			console.log(ntab+"::::ntab");

 				
            			$("#tab_code").val(ntab);
            			draw_tab2();
            		});

				
				}
				
				
				
				);

	  		 getSid1 = $("#tab_exam_ids").val();
    		 getSidx1 = $("#tab_exam_idxs").val();
    		 getSnum1 = $("#tab_exam_nums").val();
    		 getSsidx1 = $("#tab_exam_sidx").val();
    		 getSday1 = $("#tab_exam_days").val();
    	
    		console.log("getSid1::"+getSid1);	
    		console.log("getSidx1::"+getSidx1);
    		console.log("getSnum1::"+getSnum1);
    		console.log("getSsid1x::"+getSsidx1);
    		console.log("getSday1::"+getSday1);
			
		var url = "";
		if(ntabcode=='') ntabcode="0015"; //기본선택탭
		$("#tab_code").val(ntabcode);
		//현재 탭이 몇번째 줄에 있는지 확인
		var ntab_line = 0;
		for(var i = 0; i <= 2; i++ ) {
			var line = "tab_line" + (i+1);
			$("#"+line).html('');
			for(var k=0; k<tab[i].length;k++) {
				var n = tab[i][k];	
				if(n.code==ntabcode) {
					ntab_line = i+1;
					break;
				}
			}
		}
		if(ntab_line == 0 ) {
			ntabcode="0015";
			ntab_line = 1;
			$("#tab_code").val(ntabcode);
		}
		//alert("현재탭코드:" + ntabcode);
		var lines = [];
		if(ntab_line==1){
			lines = [2,3,1];
		} else if(ntab_line==2){
			lines = [3,1,2];
		} else if(ntab_line==3){
			lines = [1,2,3];
		}
		var exid;
		for(var i = 0; i <= 2; i++ ) {
			var line = "tab_line" + (i+1);
			$("#"+line).html('');
			var ni = lines[i];
			ni = ni -1;
			for(var k=0; k<tab[ni].length;k++) {
				var n = tab[ni][k];
				console.log(n.code+"::code")
				console.log(ntabcode +"::ntancode")
				if(n.code==ntabcode) {
					$("#" + line).append("<span class='tab_item_selected' code='"+ n.code +"'>"+n.name+"</span>");
					url = n.url;
					exid = n.code;
					console.log("code::"+exid);

				} else {
					$("#" + line).append("<span class='tab_item' code='"+ n.code +"'>"+n.name+"</span>");
				}
			}
		}


			
		//현재 선택된 환자 번호
		var tid = $("#tab_exam_id").val();     // mb_ettcd CDBS 각 테이블 마다의 코드 명

	
		console.log(tid+"::::::tid 값")
		var tidx = $("#tab_exam_idx").val();   // 해당 테이블 idx 시퀀스 알리아스 명(exam_idx)
	
		console.log(tidx+"::tidx값은 무엇인가?");
		var tnum = $("#tab_exam_num").val();  // 해당 테이블 selection_num 
		var tday = $("#tab_exam_day").val();
			
	//	$("#tab_iframe").attr('src',url + "?object_idx=" + sobjidx + "&exam_id=" + ntabcode + "&exam_idx=" + tidx + "&exam_num=" + tnum + "&exam_day=" + tday);	
		
			$(".tab_item").unbind();
		$(".tab_item").click(function(){
			var ntab = $(this).attr('code');
			console.log(ntab+"::::ntab");

				
				
				
			$("#tab_code").val(ntab);
			draw_tab();
		});

		$("#btn_tab_next").unbind();
		$("#btn_tab_next").click(function(){
			tab_next();
		});
		$("#btn_tab_prev").unbind();
		$("#btn_tab_prev").click(function(){
			tab_prev();
		});
	}


	
	
	function draw_tab2() {
		var sobjidx = $("#selected_object_idx").val(); //메인폼에 있음
		console.log(sobjidx+"fisrt idx")
		var ntabcode = $("#tab_code").val();
		
		console.log("draw_tab ntabcode 값:"+ntabcode);
	 	var geteid1;
	 	var geteidx1;
	 	var getenums1;
	 	var getsidx1; 
	 	var geteday1;

	
			var  eid;
			var eidx;
			var enums;
			var sidx;
			var eday;
			
			var getSid;
    		var getSidx;
    		var getSnum;
    		var getSsidx;
			
			$.ajax({
				url :   getContextPath()+"/include/data/load_selTablist.do",
				//dataType    :   "text",
		      //  contentType :   "application/x-www-form-urlencoded; charset=UTF-8",
		        type        :   "post",
		        data        : {object_idx:sobjidx ,examId : ntabcode},
				 error       :   function(request, status, error){
		                console.log("AJAX_ERROR");
		            }			
			
				}).done(function(data) {
					 
					console.log(data.examId+":eid들어갔다")
                	console.log(data.examIdx+":eid들어갔다")
                	console.log(data.examNum+":eid들어갔다")
                	console.log(data.subjectIdx+":eid들어갔다")
                	console.log(data.examDay+":eid들어갔다")
                geteid1  = $("#tab_exam_ids").val(data.examId);
				geteidx1 =  $("#tab_exam_idxs").val(data.examIdx);
				getnums1 =  $("#tab_exam_nums").val(data.examNum);
				getsidx1 =  $("#tab_exam_sidx").val(data.subjectIdx);               	
				getexday1 =  $("#tab_exam_day").val(data.examDay); 
				
                 	console.log(geteid1+"geteid")
                 	console.log(geteidx1+"geteidx")   
                 	console.log(getenums1+"getenums")   
                 	console.log(getexday1 +"getsidx")   
 
            		 getSid = $("#tab_exam_ids").val();
            		 getSidx = $("#tab_exam_idxs").val();
            		 getSnum = $("#tab_exam_nums").val();
            		 getSsidx = $("#tab_exam_sidx").val();
            		 getSday = $("#tab_exam_days").val();
            	
            		console.log("getSid::"+getSid);	
            		console.log("getSidx::"+getSidx);
            		console.log("getSnum::"+getSnum);
            		console.log("getSsidx::"+getSsidx);
            		console.log("getSday::"+getSday);
				
            		var url = "";
            		if(ntabcode=='') ntabcode="0015"; //기본선택탭
            		$("#tab_code").val(ntabcode);
            		//현재 탭이 몇번째 줄에 있는지 확인
            		var ntab_line = 0;
            		for(var i = 0; i <= 2; i++ ) {
            			var line = "tab_line" + (i+1);
            			$("#"+line).html('');
            			for(var k=0; k<tab[i].length;k++) {
            				var n = tab[i][k];	
            				if(n.code==ntabcode) {
            					ntab_line = i+1;
            					break;
            				}
            			}
            		}
            		if(ntab_line == 0 ) {
            			ntabcode="0015";
            			ntab_line = 1;
            			$("#tab_code").val(ntabcode);
            		}
            		//alert("현재탭코드:" + ntabcode);
            		var lines = [];
            		if(ntab_line==1){
            			lines = [2,3,1];
            		} else if(ntab_line==2){
            			lines = [3,1,2];
            		} else if(ntab_line==3){
            			lines = [1,2,3];
            		}
            		var exid;
            		for(var i = 0; i <= 2; i++ ) {
            			var line = "tab_line" + (i+1);
            			$("#"+line).html('');
            			var ni = lines[i];
            			ni = ni -1;
            			for(var k=0; k<tab[ni].length;k++) {
            				var n = tab[ni][k];
            				console.log(n.code+"::code")
            				console.log(ntabcode +"::ntancode")
            				if(n.code==ntabcode) {
            					$("#" + line).append("<span class='tab_item_selected' code='"+ n.code +"'>"+n.name+"</span>");
            					url = n.url;
            					exid = n.code;
            					console.log("code::"+exid);

            				} else {
            					$("#" + line).append("<span class='tab_item' code='"+ n.code +"'>"+n.name+"</span>");
            				}
            			}
            		}
            		
            		
            	
            		//현재 선택된 환자 번호
            		var tid = $("#tab_exam_id").val();     // mb_ettcd CDBS 각 테이블 마다의 코드 명

            	
            		console.log(tid+"::::::tid 값")
            		var tidx = $("#tab_exam_idx").val();   // 해당 테이블 idx 시퀀스 알리아스 명(exam_idx)
            	
            		console.log(tidx+"::tidx값은 무엇인가?");
            		var tnum = $("#tab_exam_num").val();  // 해당 테이블 selection_num 
            		var tday = $("#tab_exam_day").val();
            		
            	
            	//	$("#tab_iframe").attr('src',url + "?object_idx=" + sobjidx + "&exam_id=" + ntabcode + "&exam_idx=" + tidx + "&exam_num=" + tnum + "&exam_day=" + tday);	
            	
            		$("#tab_iframe").attr('src',url + "?object_idx=" + sobjidx + "&exam_id=" + ntabcode + "&exam_idx=" + getSidx + "&exam_num=" + getSnum + "&exam_day=" +  getSday);	
				
            		$(".tab_item").click(function(){
            			
            		
            			var ntab = $(this).attr('code');
            			console.log(ntab+"::::ntab");

 				
            			$("#tab_code").val(ntab);
            			draw_tab2();
            		});

				
				}
				
				
				
				);

	  		 getSid1 = $("#tab_exam_ids").val();
    		 getSidx1 = $("#tab_exam_idxs").val();
    		 getSnum1 = $("#tab_exam_nums").val();
    		 getSsidx1 = $("#tab_exam_sidx").val();
    		 getSday1 = $("#tab_exam_days").val();
    	
    		console.log("getSid1::"+getSid1);	
    		console.log("getSidx1::"+getSidx1);
    		console.log("getSnum1::"+getSnum1);
    		console.log("getSsid1x::"+getSsidx1);
    		console.log("getSday1::"+getSday1);
			
		var url = "";
		if(ntabcode=='') ntabcode="0015"; //기본선택탭
		$("#tab_code").val(ntabcode);
		//현재 탭이 몇번째 줄에 있는지 확인
		var ntab_line = 0;
		for(var i = 0; i <= 2; i++ ) {
			var line = "tab_line" + (i+1);
			$("#"+line).html('');
			for(var k=0; k<tab[i].length;k++) {
				var n = tab[i][k];	
				if(n.code==ntabcode) {
					ntab_line = i+1;
					break;
				}
			}
		}
		if(ntab_line == 0 ) {
			ntabcode="0015";
			ntab_line = 1;
			$("#tab_code").val(ntabcode);
		}
		//alert("현재탭코드:" + ntabcode);
		var lines = [];
		if(ntab_line==1){
			lines = [2,3,1];
		} else if(ntab_line==2){
			lines = [3,1,2];
		} else if(ntab_line==3){
			lines = [1,2,3];
		}
		var exid;
		for(var i = 0; i <= 2; i++ ) {
			var line = "tab_line" + (i+1);
			$("#"+line).html('');
			var ni = lines[i];
			ni = ni -1;
			for(var k=0; k<tab[ni].length;k++) {
				var n = tab[ni][k];
				console.log(n.code+"::code")
				console.log(ntabcode +"::ntancode")
				if(n.code==ntabcode) {
					$("#" + line).append("<span class='tab_item_selected' code='"+ n.code +"'>"+n.name+"</span>");
					url = n.url;
					exid = n.code;
					console.log("code::"+exid);

				} else {
					$("#" + line).append("<span class='tab_item' code='"+ n.code +"'>"+n.name+"</span>");
				}
			}
		}


			
		//현재 선택된 환자 번호
		var tid = $("#tab_exam_id").val();     // mb_ettcd CDBS 각 테이블 마다의 코드 명

	
		console.log(tid+"::::::tid 값")
		var tidx = $("#tab_exam_idx").val();   // 해당 테이블 idx 시퀀스 알리아스 명(exam_idx)
	
		console.log(tidx+"::tidx값은 무엇인가?");
		var tnum = $("#tab_exam_num").val();  // 해당 테이블 selection_num 
		var tday = $("#tab_exam_day").val();
			
	//	$("#tab_iframe").attr('src',url + "?object_idx=" + sobjidx + "&exam_id=" + ntabcode + "&exam_idx=" + tidx + "&exam_num=" + tnum + "&exam_day=" + tday);	
		
			$(".tab_item").unbind();
		$(".tab_item").click(function(){
			var ntab = $(this).attr('code');
			console.log(ntab+"::::ntab");

				
				
				
			$("#tab_code").val(ntab);
			draw_tab();
		});

		$("#btn_tab_next").unbind();
		$("#btn_tab_next").click(function(){
			tab_next();
		});
		$("#btn_tab_prev").unbind();
		$("#btn_tab_prev").click(function(){
			tab_prev();
		});
	}
	
	
	function tab_nowtabcode(nt, tid, tidx, tnum, tday ) {

		//파라미터는 검사명이며 검사명별로 탭지정으로 변경함.
		nt = CHTAB[nt];

		if( nt=='' ) return false;
		//추가된 코드가 리스트에 있는지 확인
		for(var i = 0; i <= 2; i++ ) {
			for(var k=0; k<tab[i].length;k++) {
				var n = tab[i][k];
				if(n.code==nt) {
					$("#tab_code").val(nt);
					$("#tab_exam_id").val(tid);
					$("#tab_exam_idx").val(tidx);
					$("#tab_exam_num").val(tnum);
					$("#tab_exam_day").val(tday);
					
					break;
				}
			}
		}
	}

	function tab_next() {
		var ntabcode = $("#tab_code").val();
		console.log(ntabcode+"tab_next ntabcode");
		//현재 탭이 몇번째 줄에 있는지 확인
		var ntab_index = 0;
		var tab_index = [];
		var cindex = 0;
		for(var i = 0; i <= 2; i++ ) {
			for(var k=0; k<tab[i].length;k++) {
				var n = tab[i][k];
				console.log("tab_next tab n값: "+n)
				console.log("tab_next n.code값: "+n.code);
				tab_index[cindex]=n.code;
				if(n.code==ntabcode) {
					ntab_index = cindex;
				}
				cindex++;
			}
		}
		ntab_index++;
		if(ntab_index>tab_index.length ) ntab_index = 0;
		$("#tab_code").val(tab_index[ntab_index]);

		draw_tab();
	}

	function tab_prev(){
		var ntabcode = $("#tab_code").val();
		console.log(ntabcode+"tab_prev ntabcode");
		//현재 탭이 몇번째 줄에 있는지 확인
		console.log("현재 선택된 코드:" + ntabcode);
		var ntab_index = 0;
		var tab_index = [];
		var cindex = 0;
		for(var i = 0; i <= 2; i++ ) {
			for(var k=0; k<tab[i].length;k++) {
				var n = tab[i][k];
				console.log("tab_prev tab n값"+n)
				tab_index[cindex]=n.code;
				//console.log(cindex+" -> " + n.code);
				if(n.code==ntabcode) {
					//console.log(cindex+"번째에 같은값 있음");
					ntab_index = cindex;
				}
				cindex++;
			}
		}
		ntab_index--;
		if(ntab_index<0 ) ntab_index = tab_index.length-1;
		$("#tab_code").val(tab_index[ntab_index]);

		draw_tab();
	}

