
$(function(){

		//숫자만 입력칸(class='onlydigit') -값도 입력가능
		$(document).on('keydown','.onlydigit',function(e) {
			if ($.inArray(e.keyCode, [46, 8, 9, 27, 13]) !== -1 || (e.keyCode == 65 && e.ctrlKey === true) || (e.keyCode >= 35 && e.keyCode <= 39)) return;
	        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) e.preventDefault();
		});
		$(document).on('keypress','.onlyfloat',function(e) {
			if ((e.which != 46 || $(this).val().indexOf('.') != -1) && (e.which < 48 || e.which > 57) && e.which != 8) {
				e.preventDefault();
			}
		});

		//마우스우클릭 막기
		//TODO: 주석 해제
		//$(document).on('contextmenu', function(){ return false; });
		//글자 선택안되게
		document.onselectstart = function (e) { return false; };

		$(".calendar").datepicker({
			showMonthAfterYear:true,
			monthNames:['년 1월','년 2월','년 3월','년 4월','년 5월','년 6월','년 7월','년 8월','년 9월','년 10월','년 11월','년 12월'],
			monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
			dayNamesMin: ['일','월','화','수','목','금','토'],
			weekHeader: 'Wk',
			dateFormat: 'yy-mm-dd',
			changeYear:true,
			changeMonth:true,
			showButtonPanel: true,
			closeText: '닫기'
		});
		$(".calendar").attr('readonly',true);
		$(".calendar").css({'margin-right':'2px', 'width':'80px'});

		$(".calendars").datepicker({
			showMonthAfterYear:true,
			monthNames:['년 1월','년 2월','년 3월','년 4월','년 5월','년 6월','년 7월','년 8월','년 9월','년 10월','년 11월','년 12월'],
			monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
			dayNamesMin: ['일','월','화','수','목','금','토'],
			weekHeader: 'Wk',
			dateFormat: 'yy-mm-dd',
			changeYear:true,
			changeMonth:true,
			yearRange: "c-120:c",
			showButtonPanel: true,
			closeText: '닫기'
		});
		$(".calendars").attr('readonly',true);
		$(".calendars").css({'margin-right':'2px', 'width':'80px'});
		
		
		//뒤로가기 안되게
		window.history.pushState(null, "", window.location.href);
        window.onpopstate = function() {
            window.history.pushState(null, "", window.location.href);
        };



}); //$(function(){


