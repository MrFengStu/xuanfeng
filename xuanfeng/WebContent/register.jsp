<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page errorPage="error.jsp"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="resources/style/style_register.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/resources/js/jquery.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/jquery.i18n.properties-1.0.9.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/jquery-ui-1.10.3.custom.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/md5.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/page_regist.js?lang=zh"></script>
<!--[if IE]>
  <script src="resources/js/html5.js"></script>
<![endif]-->
<!--[if lte IE 6]>
	<script src="resources/js/DD_belatedPNG_0.0.8a-min.js" language="javascript"></script>
	<script>
	  DD_belatedPNG.fix('div,li,a,h3,span,img,.png_bg,ul,input,dd,dt');
	</script>
<![endif]-->
<title>注册</title>
</head>
<body class="loginbody">
	<div class="dataEye">
		<div class="loginbox registbox">
			<div class="logo-a">
				<a href="login.jsp" title="xuanfeng"> <img
					src="${ctx }/resources/images/logo-a.png" width="100px" height="100px">
				</a>
			</div>
			<div class="login-content reg-content">
				<div class="loginbox-title">
					<h3>注册</h3>
				</div>
				<form id="signupForm">
					<div class="login-error"></div>
					<div class="row">
						<label class="field" for="name">用户名</label> <input type="text"
							value="" class="input-text-user noPic input-click" name="name"
							id="name">
					</div>
					<div class="row">
						<label class="field" for="email">注册邮箱</label> <input type="text"
							value="" class="input-text-user noPic input-click" name="email"
							id="email">
					</div>
					<div class="row">
						<label class="field" for="password">密码</label> <input
							type="password" value=""
							class="input-text-password noPic input-click" name="password"
							id="password">
					</div>
					<div class="row">
						<label class="field" for="passwordAgain">确认密码</label> <input
							type="password" value=""
							class="input-text-password noPic input-click"
							name="passwordAgain" id="passwordAgain">
					</div>
					<!-- <div class="row">
						<label class="field" for="realName">真实姓名</label> <input type="text"
							value="" class="input-text-user noPic input-click" name="realName"
							id="realName">
					</div>
					<div class="row">
						<label class="field" for="phone">电话</label> <input type="text"
							value="" class="input-text-user noPic input-click" name="phone"
							id="phone">
					</div> -->
					<div class="row tips">
						<input type="checkbox" id="checkBox"/> <label>
							我已阅读并同意 <a href="#" target="_blank">隐私政策、服务条款</a>
						</label>
					</div>
					<div class="row btnArea">
						<a class="login-btn" id="submit">注册</a>
					</div>
				</form>
			</div>
			<div class="go-regist">
				已有帐号,请<a href="login.jsp" class="link">登录</a>
			</div>
		</div>

		<div id="footer">
			<div class="dblock">
				<div class="inline-block">
					<img src="${ctx }/resources/images/logo-gray.png" width="180px" height="110px">
				</div>
				<div class="inline-block copyright">
					<p>
						<a href="#">关于我们</a> | <a href="#">微博</a> | <a href="#">隐私政策</a> |
						<a href="#">人员招聘</a>
					</p>
					<p>Copyright © 2016 旋风</p>
				</div>
			</div>
		</div>
	</div>
	<div class="loading">
		<div class="mask">
			<div class="loading-img">
				<img src="${ctx }/resources/images/loading.gif" width="31" height="31">
			</div>
		</div>
	</div>
</body>
</html>