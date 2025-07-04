 var idField, pwdField;
 var LoginPopup ={};
 var UserReqPopup={};
//로그인 팝업
 LoginPopup.PopupWindow = function() {	
 	LoginPopup.PopupWindow.superclass.constructor.call(this, {
 		id: 'LoginPopup-window',
 	    layout: 'border',
 	    title: '로그인',
 	    modal : true,
 	    width: 820,
 	    height: 795,
 	    frame: true,
 	    closeAction : 'destroy',
 	    resizable : false,
 	    draggable: false,
 	    floating: true,
 	    items:[{
 	        xtype: 'panel',
 	        items: [{
 	        	id :'loginPage',
 	        	loader: {
 	                url: 'NRCD/loginPopup.view',
 	                contentType: 'html',
 	                loadMask: true,
 	                loadOnRender: true
 	            	}
 	        	}]
 	        }],
 	});
 };
 Ext.extend(LoginPopup.PopupWindow,Ext.Window,{
 });
 //대상자등록 팝업
	userObjectPopup.PopupWindow = function() {	
		userObjectPopup.PopupWindow.superclass.constructor.call(this, {
	 		id: 'userObjectPopup-window',
	 	    layout: 'border',
	 	    title: '대상자 등록',
	 	    modal : true,
	 	    width: 600,
	 	    height: 820,
	 	    frame: true,
	 	    closeAction : 'destroy',
	 	    resizable : false,
	 	    draggable: false,
	 	    floating: true,
	 	    items:[{
	 	        xtype: 'panel',
	 	        items: [{
	 		        	id :'reqPage',
	 		        	loader: {
	 		                url: 'CSBRAIN/insertObjectIdx.view',
	 		                contentType: 'html',
	 		                loadMask: true,
	 		                loadOnRender: true
	 		            	}
	 		        	}]
	 	        }],
	 	});
	 };
	 
	 Ext.extend(userObjectPopup.PopupWindow,Ext.Window,{
	 });
 
 function fn_loginPopup(){
 
 var loginPopup = new LoginPopup.PopupWindow();
 loginPopup.show();
 
 }
 
 

 
  function onLoginSubmit() {
  	idField = $('#id').val();
  	pwdField = $('#pw').val();
  	if( idField=="" || pwdField =="" ) {
  		Ext.MessageBox.show({
  			title: '확인',
  			msg: "아이디와 비밀번호를 입력하시기 바랍니다",
  			buttons: Ext.Msg.OK
  		});
  		
  	}
  	
  	if(idField !=="" && pwdField !=="") {
  		Ext.MessageBox.show({
  	         msg: '<br>로그인 중입니다...',
  	         progressText: 'Saving...',
  	         width: 300,
  	         heigth : 30,
  	         wait: {
  	             interval: 200
  	         },
  	     });
  		var svcId = "login";
  		var url = GLOBAL_CONTEXT_PATH + '/dementia/main/login.do';
  		
  		Ext.Ajax.request({
  			url : url,
  			method : 'POST',
  			params : {
  				id: idField,
  				pw: pwdField
  			},
  			success : function(resp) {
  				//mask.hide();

  				var json = Ext.decode(resp.responseText);
  				
  				if(json.success == true) {
  					Ext.MessageBox.hide();
  					//window.close();
  					location.reload();
  					
  					
  				} else {
  					Ext.MessageBox.hide();
  					Ext.Msg.alert('인증 오류', json.errors.reason);
  				}
  			}
  		});
  	}
  	
  	return false;
  }
  
  

  var win ;
  function  insertObjectIdx(){
	  Ext.getCmp('LoginPopup-window').destroy();
	  var userObjectPopup = new userObjectPopup.PopupWindow();
 	  userObjectPopup.show();
	
 }