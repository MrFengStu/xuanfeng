<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page errorPage="error.jsp"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
<link href="resources/style/style_login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/resources/js/jquery.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/jquery.i18n.properties-1.0.9.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/jquery-ui-1.10.3.custom.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/md5.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/page_login.js?lang=zh"></script>
<!--[if IE]>
  <script src="resources/js/html5.js"></script>
<![endif]-->
<!--[if lte IE 6]>
	<script src="resources/js/DD_belatedPNG_0.0.8a-min.js" language="javascript"></script>
	<script>
	  DD_belatedPNG.fix('div,li,a,h3,span,img,.png_bg,ul,input,dd,dt');
	</script>
<![endif]-->
<script type="text/javascript">
</script>
</head>
<body class="loginbody">
	<div class="dataEye">
		<div class="loginbox">
			<div class="logo-a">
				<a href="http://www.fengtingxin.cn" title="旋风"> <img
					src="${ctx }/resources/images/logo-a.png" width="220px" height="110px">
				</a>
			</div>
			<div class="login-content">
				<div class="loginbox-title">
					<h3>登录</h3>
				</div>
				<form id="signupForm">
					<div class="login-error"></div>
					<div class="row">
						<label class="field">email</label> <input type="text"
							class="input-text-user input-click" name="email" id="email" value="1523056@qq.com">
					</div>
					<div class="row">
						<label class="field">用户名</label> <input type="text"
							class="input-text-user input-click" name="loginName"
							id="loginName" value="123456">
					</div>
					<div class="row">
						<label class="field">密码</label> <input type="password"
							class="input-text-password input-click" name="password"
							id="password" value="123456">
					</div>
					<div class="row">
						<label class="field">验证码</label> <input type="text"
							class="input-text-user input-click" name="codeValue"
							id="codeValue" style="width: 120px;">
						<img  src="MakeCodeServlet" width="140px" height="40px"style="float: right;"  >
					</div>
					<div class="row btnArea">
						<a class="login-btn" id="submit">登录</a>
					</div>
				</form>
			</div>
			<div class="go-regist">
				还没有账号？请 <a href="register.jsp">立即注册</a>
			</div>
		</div>

		<div id="footer">
			<div class="dblock">
				<div class="inline-block">
					<img src="${ctx }/resources/images/logo-gray.png" width="220px"
						height="110px">
				</div>
				<div class="inline-block copyright">
					<p>
						<a href="#">关于我们</a> | <a href="#">微博</a> | <a href="#">隐私政策</a> |
						<a href="#">人员招聘</a>
					</p>
					<p>Copyright © 2016 xuanfeng网</p>
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