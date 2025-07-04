
$(function(){

	const MY_MENU = new Map();

	//나에게 할당된 메뉴 리스트 추출
	$.post(
		getContextPath() + '/include/selectMemberPermissionPowerList.do',
		{

		}
	).done(function(data){
		data = JSON.parse(data);
		//console.log(data.list);

		for (var idx in data.list) {
			for (var prop in data.list[idx]) {
				if(prop == "progid"){
					MY_MENU.set(data.list[idx][prop].toUpperCase(), "Y");
				}
			}
		}

		//console.log(MY_MENU);

	}).fail(function(){
		alert('FAULT! 개발자에게 문의바랍니다.');
	});

	//메뉴 리스트 추출
	//'progcd01'이 '01'인 항목은 csbrain에서 동작하고, '02'인 항목은 basc.co.kr(테블릿 사이트)에서 돌아감.
	// '03'인 항목은 '비밀번호체크' 한개 있는데 어디에서 사용하는지 모름
	$.post(
		getContextPath() + '/include/selectMemberProgramListForWeb.do',
		{

		}
	).done(function(data){
		data = JSON.parse(data);
		//console.log(data.list);

		//debugger;

		const TOP_MENU = new Map();

		var progcd01, progcd02, progcd03;
		for (var idx in data.list) {
			var row = data.list[idx];
			progcd01 = row["progcd01"];
			progcd02 = row["progcd02"];
			progcd03 = row["progcd03"];

			row["progid"] = row["progid"].toUpperCase();

			if ( progcd02 != "" ) {
				if ( progcd03 == "" ) {
					TOP_MENU.set(row["progcd02"], new Map().set("name", row["progname"]));
				} else {
					MY_MENU.forEach(function (value, key, map) {
						if(key == row["progid"] && value == 'Y'){ //나에게 허용된것만
							if ( row["progid"] != "CSBR1030" && row["progid"] != "CSBR1040" && row["progid"] != "CSBR1041" && row["progid"] != "CSBR4110" && row["progid"] != "CSBR7002" && row["progid"] != "CSBR9990" && row["progid"] != "CSBR9980" && row["progid"] != "CSBR5030") {
								TOP_MENU.set(row["progcd02"], TOP_MENU.get(row["progcd02"]).set(row["progcd03"], new Map().set(row["progname"], row["progid"])));
								//console.log("[" + row["progcd02"] + "][" + row["progcd03"] + "]");
								//console.log("(\"" + row["progname"] + "\",\"" + row["progid"] + "\")");
							}
						}
					});
				}
			}
		}
		//console.log(TOP_MENU);

		let topMenuHtml = "";

		//debugger;

		//찍기
		TOP_MENU.forEach(function (value, key, map) {
			if(value instanceof Map){
				if(value.size > 1){ //서버메뉴가 있어야 출력 ( name은 기본적으로 있으니 한개보다 더 있어야 서브메뉴가 있는것임 )

					if(value.get("name") != ""){
						topMenuHtml += "<div class='top_menu' mid='" + key + "'>" + value.get("name");
						topMenuHtml += "<div id='sub_menu_" + key + "' class='sub_menu'>";
					}
					value.forEach(function (value2, key2, map2) {
						if(value2 != ""){
							if(value2 instanceof Map){
								value2.forEach(function (value3, key3, map3) {
									menu_name = key3;
									menu_id = value3;
									if ( menu_name != "" && menu_id != "" ) {
										 topMenuHtml += "<li class='sub_menu_item' menu_id='" + menu_id + "'>" + menu_name + "</li>";
									}
								});
							}
						}
					});
					topMenuHtml += "</div>";
					topMenuHtml += "</div>";
				}
			}
		});

		topMenuHtml += "<div id='btn_logout'>[LOGOUT]</div>";
		//console.log(topMenuHtml);
		$("#top_menu").html(topMenuHtml);

	}).fail(function(){
		alert('FAULT! 개발자에게 문의바랍니다.');
	});

	$(document).on("mouseenter mouseleave", ".top_menu", function (e) {
    	var menu_id = $(this).attr('mid');
		if(e.type == "mouseenter"){
			$("#sub_menu_"  + menu_id).show();
		}else if(e.type == "mouseleave"){
			$("#sub_menu_"  + menu_id).hide();
		}
	});

	$(document).on("click", ".sub_menu_item", function (e) {
    	var menu_id = $(this).attr('menu_id');
		var menu_name = $(this).html();
		$(".sub_menu").hide();
		var murl = "subpage.see?menu_id=" + menu_id.toLowerCase(); //메뉴코드는 소문자로 ..
		add_tab(menu_id.toUpperCase(), menu_name, murl);
	});

	$(document).on("click", "#btn_logout", function (e) {
    	if(confirm('로그아웃하시겠습니까?') ) {
			location.replace('include/logout.do');
		}
	});

}); //$(function(){


