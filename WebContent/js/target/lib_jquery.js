function check_field(target, type)
{

	if(type == "radio")
	{
		var target = "input:radio[name='"+target+"']";

		if(!$(target).is(":checked")) { $(target).focus(); return false; }
		else { return true; }
	}
	else if(type == "text")
	{
		var target = "input[name='"+target+"']";

		if($(target).val().length ==0) { $(target).focus(); return false; }
		else { return true; }
	}
	else if(type == "select")
	{
		var target = "select[name='"+target+"']";

		if($.trim($(target).val()) == "선택") { $(target).focus(); return false; }
		else { return true; }
	}
}