var mode = pageLoad.getParameter('mode');
var paramId = pageLoad.getParameter('id');
var Handler = {};
var DataSource = {};
var click = false;
Ext.onReady(function() {
	Ext.QuickTips.init();
    Handler.doAction = function(type) {
    
    	switch(type) {
    		
	    	case 'SAVE':
	    		var allowBlankObj = ['id','name', 'email', 'pw', 'pw2']; //,'pw','pw2'
	    		var allowBlankNm = ['사용자 ID' , '사용자명', '이메일', '비밀번호', '비밀번호 재확인'] //
	    		
    			for(var i = 0; i < allowBlankObj.length; i ++) {
    				var obj = eval('document.memberFrm.' + allowBlankObj[i]);
    				if(obj.value == '') {
    					Ext.MessageBox.show({
 		                   title:'유효성 검사 에러',
 		                   msg:  '필수 항목을 확인하십시오 [' + allowBlankNm[i] +']',
 		                   buttons: Ext.MessageBox.OK,
 		                   fn: function(btn){
 		       	        	if(btn == 'ok'){
 		       	        		eval('document.memberFrm.' + allowBlankObj[i] +'.focus();');
 		       	        	}
 		                   },
 		               });
    					
    					//Ext.MessageBox.alert('유효성 검사 에러', '필수 항목을 확인하십시오 [' + allowBlankNm[i] +']');
    					//eval('document.memberFrm.' + allowBlankObj[i] +'.focus();');
    					return true;
    				}
    			}
	    		var password = document.getElementById('pw').value;
	    		var password2 = document.getElementById('pw2').value;
	    		if(password.length==0){
	    			Ext.MessageBox.show({
	                   title:'유효성 검사 에러',
	                   msg:  '비밀번호를 먼저 입력해주세요.',
	                   buttons: Ext.MessageBox.OK,
	                   fn: function(btn){
	       	        	if(btn == 'ok'){
	       	        		document.getElementById('pw').focus();
	       	        		}
	                   	},
	    			});
					
					return;
				}
				if(password2==0 || password!=password2){
					Ext.MessageBox.show({
		                   title:'유효성 검사 에러',
		                   msg:  '비밀번호가 일치하지 않습니다.',
		                   buttons: Ext.MessageBox.OK,
		                   fn: function(btn){
		       	        	if(btn == 'ok'){
		       	        		document.getElementById('pw2').focus();
		       	        		}
		                   	},
		    			});
					return;
				}
    			if(!Ext.form.VTypes.email(document.memberFrm.email.value)) {
    				Ext.MessageBox.show({
 	                   title:'유효성 검사 에러',
 	                   msg:  '입력하신 이메일 주소를 확인하십시오..',
 	                   buttons: Ext.MessageBox.OK,
 	                   fn: function(btn){
 	       	        	if(btn == 'ok'){
 	       	        	document.memberFrm.email.focus();
 	       	        		}
 	                   	},
 	    			});
					return true;
    			}
				if(document.memberFrm.isUserId.value == 'N') {
					
					//Ext.MessageBox.alert('유효성 검사 에러', '아이디 중복확인을 하십시오.');
					Ext.MessageBox.alert('유효성 검사 에러', '아이디 중복확인을 하십시오.');
					return;
    			}
			
			if (!click) {
				click = true;
	            // 타이밍 추가
	            setTimeout(function () {
	                click = false;
	            }, 3000)
	            
	    		var oURL = '';
	    		oURL = GLOBAL_CONTEXT_PATH + '/dementia/basicinfo/insertUserReq.do';
	    	 	Ext.Ajax.request({
	    	 		url : oURL,
	    	 	    form : memberFrm,
	    	 	    method : 'POST',
	    	 	    timeout : 100000000,
	    	        success : function(response, opts) {
	    	        	var obj = Ext.decode(response.responseText);
	    	            if (obj.success) {
	    	            	Ext.MessageBox.show({
	    	                    title:'알림',
	    	                    msg:  '계정이 신청되었습니다.',
	    	                    buttons: Ext.MessageBox.OK,
	    	                    fn: function(btn){
	    	        	        	if(btn == 'ok'){
	    	        	        		parent.fn_close();
	    	        	        		//window.close();
	    	          					//window.opener.location.reload(true);
	    	        	        		//Ext.getCmp('userReqPopup-window').destroy();
	    	        	        	}
	    	                    },
	    	                });
	    	            	
	    	            }
	    	        },
	    	        failure : function(){
	    	        },
	    	        callback : function() {
	    	        }
	    	 	});
			}
	    	break;
	    		
	    	
	    	case 'ID_CHECK':
	    		var id = document.getElementById('id').value;
	    		if(id == '') {
	    			Ext.MessageBox.show({
		                   title:'유효성 검사 에러',
		                   msg:  'ID를 입력 하십시오.',
		                   buttons: Ext.MessageBox.OK,
		                   fn: function(btn){
		       	        	if(btn == 'ok'){
		       	        		document.getElementById('id').focus();
		       	        		}
		                   	},
		    			});
	    			return;
	    		}
	    		
	    		Ext.Ajax.request({
	    			url: GLOBAL_CONTEXT_PATH + '/dementia/basicinfo/checkUserId.do',
	    			params: {
	    				id: id,
	    			},
	    			success: function(response, options) {
	    				var obj = Ext.decode(response.responseText);
	    				if(obj.results.success==true){
		    				Ext.MessageBox.alert('유효성 검사 에러', '사용할 수 있는 ID 입니다.');
		    				document.memberFrm.isUserId.value = 'Y';
	    				}else{
	    					Ext.MessageBox.alert('유효성 검사 에러', '이미 사용 중인 ID 입니다.');
		    				document.memberFrm.isUserId.value = 'N';
	    				}
	    			},
	    			failure: function(form, action) {
	    				Ext.MessageBox.alert('유효성 검사 에러', '이미 사용 중인 ID 입니다.');
	    				document.memberFrm.isUserId.value = 'N';
	    			}
	    		});
	    		
	    	break;
	    	
	    	case 'ID_CHANGE':
	    		document.memberFrm.isUserId.value = 'N';
	    	break;
    	}
    	
    };
    //Handler.doAction();   
    
});

function valiCheck(type){
	var id = document.getElementById('id').value;
	var password = document.getElementById('pw').value;
	var password2 = document.getElementById('pw2').value;
	switch(type) {
		
		case 'ID':
			
			//아이디 문자 , 숫자 조합이여야 되는 로직
			var regexId = /^.*(?=.*\d)(?=.*[a-zA-Z]).*$/;
			if(!id.match(regexId)|| id.length < 4)
			{
				Ext.MessageBox.show({
	                   title:'유효성 검사 에러',
	                   msg:  '사용자 ID가 문자/숫자 혼합 4자리 이상이여야 합니다.',
	                   buttons: Ext.MessageBox.OK,
	                   fn: function(btn){
	       	        	if(btn == 'ok'){
	       	        		document.getElementById('id').focus();
	       	        		}
	                   	},
	    			});
				
				return;
			}
			break;
		case 'PW_VALI':
			//패스워드 문자 , 숫자 , 특수문자 조합이여야 되는 로직
			var regexPw = /^.*(?=.*\d)(?=.*[a-zA-Z])(?=.*[~`!@#$%\^&*()+= -]).*$/;
			if(password.length>0 && (!password.match(regexPw)|| password.length < 8))
				{
					Ext.MessageBox.show({
	                   title:'유효성 검사 에러',
	                   msg:  '비밀번호가 문자/숫자/특수문자포함 8자리 이상이여야 합니다.',
	                   buttons: Ext.MessageBox.OK,
	                   fn: function(btn){
	       	        	if(btn == 'ok'){
	       	        		document.getElementById('pw').focus();
	       	        		}
	                   	},
	    			});
					return;
				}
    			
			break;
		case 'PW_CHECK':
			if(password.length==0){
				Ext.MessageBox.show({
	                   title:'유효성 검사 에러',
	                   msg:  '비밀번호를 먼저 입력해주세요.',
	                   buttons: Ext.MessageBox.OK,
	                   fn: function(btn){
	       	        	if(btn == 'ok'){
	       	        		document.getElementById('pw').focus();
	       	        		}
	                   	},
	    			});
				return;
			}
			if(password2==0 || password!=password2){
				Ext.MessageBox.show({
	                   title:'유효성 검사 에러',
	                   msg:  '비밀번호가 일치하지 않습니다.',
	                   buttons: Ext.MessageBox.OK,
	                   fn: function(btn){
	       	        	if(btn == 'ok'){
	       	        		document.getElementById('pw2').focus();
	       	        		}
	                   	},
	    			});
				return;
			}
	}
}

