$(document).ready(function(){
	
	//获取JS传递的语言参数
	var utils = new Utils();
	var args = utils.getScriptArgs();	
	
	
	//隐藏Loading/注册失败 DIV
	$(".loading").hide();
	$(".login-error").hide();
	registError = $("<label class='error repeated'></label>");
	
	//加载国际化语言包资源
//	utils.loadProperties(args.lang);
	
	//输入框激活焦点、移除焦点
	jQuery.focusblur = function(focusid) {
		var focusblurid = $(focusid);
		var defval = focusblurid.val();
		focusblurid.focus(function(){
			var thisval = $(this).val();
			if(thisval==defval){
				$(this).val("");
			}
		});
		focusblurid.blur(function(){
			var thisval = $(this).val();
			if(thisval==""){
				$(this).val(defval);
			}
		});
	 
	};
	/*下面是调用方法*/
	$.focusblur("#email");
	$.focusblur("#name");
//	$.focusblur("#realName");
//	$.focusblur("#phone");
	$.focusblur("#password");
	//获取表单验证对象[填写验证规则]
	var validate = $("#signupForm").validate({
		rules: {
			name:{
				required :true
			},
			email: {
				required: true,
				email: true
			},
			password: {
				required: true,
				minlength: 4,
				maxlength: 16
			},
			passwordAgain: {
				required: true,
				minlength: 4,
				maxlength: 16,
				equalTo: "#password"
			},
//			realName: {
//				required: true
//			},
//			phone: {
//				required: true,
//				digits:true
//			}
		},
		messages: {
			name:{
				required: $.i18n.prop("请输入name"),
			},
			email: {
				required: $.i18n.prop("请输入email"),
				email: $.i18n.prop("请输入正确的email")
			},
			password: {
				required: $.i18n.prop("请输入密码"),
				minlength: jQuery.format($.i18n.prop("您的密码过短")),
				maxlength: jQuery.format($.i18n.prop("您的密码过长"))
			},
			passwordAgain: {
				required: $.i18n.prop("请再次输入密码"),
				minlength: jQuery.format($.i18n.prop("您的密码过短")),
				maxlength: jQuery.format($.i18n.prop("您的密码过长")),
				equalTo: jQuery.format($.i18n.prop("两次的密码不同"))
			},
//			realName: {
//				required: $.i18n.prop("请输入您的真实姓名")
//			},
//			phone: {
//				required: $.i18n.prop("请输入手机号"),
//				digits: $.i18n.prop("请输入正确的手机号")
//			}
		}
	});
	
	
	//输入框激活焦点、溢出焦点的渐变特效
	if($("#name").val()){
		$("#name").prev().fadeOut();
	};
	$("#name").focus(function(){
		$(this).prev().fadeOut();
	});
	$("#name").blur(function(){
		if(!$("#name").val()){
			$(this).prev().fadeIn();
		};		
	});
	if($("#email").val()){
		$("#email").prev().fadeOut();
	};
	$("#email").focus(function(){
		$(this).prev().fadeOut();
	});
	$("#email").blur(function(){
		if(!$("#email").val()){
			$(this).prev().fadeIn();
		};		
	});
	if($("#password").val()){
		$("#password").prev().fadeOut();
	};
	$("#password").focus(function(){
		$(this).prev().fadeOut();
	});
	$("#password").blur(function(){
		if(!$("#password").val()){
			$(this).prev().fadeIn();
		};		
	});
	if($("#passwordAgain").val()){
		$("#passwordAgain").prev().fadeOut();
	};
	$("#passwordAgain").focus(function(){
		$(this).prev().fadeOut();
	});
	$("#passwordAgain").blur(function(){
		if(!$("#passwordAgain").val()){
			$(this).prev().fadeIn();
		};		
	});
//	if($("#realName").val()){
//		$("#realName").prev().fadeOut();
//	};
//	$("#realName").focus(function(){
//		$(this).prev().fadeOut();
//	});
//	$("#realName").blur(function(){
//		if(!$("#realName").val()){
//			$(this).prev().fadeIn();
//		};		
//	});
//	if($("#phone").val()){
//		$("#phone").prev().fadeOut();
//	};
//	$("#phone").focus(function(){
//		$(this).prev().fadeOut();
//	});
//	$("#phone").blur(function(){
//		if(!$("#phone").val()){
//			$(this).prev().fadeIn();
//		};		
//	});
	
	//ajax提交注册信息
	$("#submit").bind("click", function(){
		regist(validate);
	});
//	
	$("body").each(function(){
		$(this).keydown(function(){
			if(event.keyCode == 13){
				regist(validate);
			}
		});
	});
	
});

function regist(validate){
	console.log("this is regist");
	//校验name,realName,phone,Email, password，校验如果失败的话不提交
//	console.log($("#checkBox").prop("checked"));
	if(validate.form()){
		if($("#checkBox").prop("checked")){
			var md5 = new MD5();
//			console.log("post method before");
			$.post({
				url : "user/register",
				data: {
					loginName: $("#name").val(),
					password: md5.MD5($("#password").val()),
					email: $("#email").val(),
//					realName: $("#realName").val(),
//					phone: $("#phone").val(),	
				},
				beforeSend: function(){
					$('.loading').show();
				},
				success: function(data,status){
//					console.log("data");
					$('.loading').hide();
					if(data){
						if(data == "0"){
							//注册成功
							window.location.href="login.jsp"
//							window.location.href = "registOk.jsp?email="+$('#email').val();
						}else if(data == "1"){
							//数据库链接失败
							$(".login-error").html($.i18n.prop("服务器有问题，请刷新注册"));
						}else if(data == "2"){
							//参数传递失败
							$(".login-error").show();
							$(".login-error").html($.i18n.prop("您的网络有问题"));
						}else if(data == "3"){
							//名字已经被注册
							$("#name").addClass("error");
							$("#name").after(registError);						
							$("#name").next("label.repeated").text($.i18n.prop("您的用户名已经存在"));
							registError.show();
						}else if(data == "4"){
							//邮箱已经被注册
							$("#email").addClass("error");
							$("#email").after(registError);
							$("#email").next("label.repeated").text($.i18n.prop("邮箱已经存在"));
							registError.show();
						}else{
							//系统错误
							$(".login-error").html($.i18n.prop("系统错误"));
						}
					}
				}
			});
//			$.ajax({
//				url: "user/register",
//				type: "post",
//				data: {
//					name: $("#name").val(),
//					password: md5.MD5($("#password").val()),
//					email: $("#email").val(),
//					realName: $("#realName").val(),
//					phone: $("#phone").val(),
//					
//				},
//				beforeSend: function(){
//					$('.loading').show();
//				},
//				success: function(data){
//					$('.loading').hide();
//					if(data.hasOwnProperty("code")){
//						if(data.code == 0){
//							//注册成功
////							window.location.href = "registOk.jsp?email="+$('#email').val();
//						}else if(data.code == 1){
//							//数据库链接失败
//							$(".login-error").html($.i18n.prop("Error.Exception"));
//						}else if(data.code == 2){
//							//参数传递失败
//							$(".login-error").show();
//							$(".login-error").html($.i18n.prop("Error.ParameterError"));
//						}else if(data.code == 3){
//							//公司已经被注册
//							$("#company").addClass("error");
//							$("#company").after(registError);						
//							$("#company").next("label.repeated").text($.i18n.prop("Error.CompaniesAlreadyExists"));
//							registError.show();
//						}else if(data.code == 4){
//							//邮箱已经被注册
//							$("#email").addClass("error");
//							$("#email").after(registError);
//							$("#email").next("label.repeated").text($.i18n.prop("Error.EmailAlreadyExists"));
//							registError.show();
//						}else{
//							//系统错误
//							$(".login-error").html($.i18n.prop("Error.SysError"));
//						}
//					}
//				}
//			});
		}else{
			//勾选隐私政策和服务条款
			$(".login-error").show();
			$(".login-error").html($.i18n.prop("没有勾选隐私政策和服务条款"));
		}
	}
}

var Utils = function(){};

//Utils.prototype.loadProperties = function(lang){
//	jQuery.i18n.properties({// 加载资浏览器语言对应的资源文件
//		name:'ApplicationResources',
//		language: lang,
//		path:'resources/i18n/',
//		mode:'map',
//		callback: function() {// 加载成功后设置显示内容
//		} 
//	});	
//};

Utils.prototype.getScriptArgs = function(){//获取多个参数
    var scripts=document.getElementsByTagName("script"),
    //因为当前dom加载时后面的script标签还未加载，所以最后一个就是当前的script
    script=scripts[scripts.length-1],
    src=script.src,
    reg=/(?:\?|&)(.*?)=(.*?)(?=&|$)/g,
    temp,res={};
    while((temp=reg.exec(src))!=null) res[temp[1]]=decodeURIComponent(temp[2]);
    return res;
};
