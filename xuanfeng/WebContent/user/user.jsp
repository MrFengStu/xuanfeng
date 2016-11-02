<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User edit</title>

<link rel="stylesheet" type="text/css"	href="${ctx }/resources/style/bootstrap.css">

<link rel="stylesheet" type="text/css"	href="${ctx }/resources/style/theme.css">
<link rel="stylesheet" href="${ctx }/resources/style/font-awesome.css">
<script src="${ctx }/resources/js/md5.js"	type="text/javascript"></script>
<script src="${ctx }/resources/js/jquery-1.7.2.min.js"	type="text/javascript"></script>


<!-- Demo page code -->

<style type="text/css">
#line-chart {
	height: 300px;
	width: 800px;
	margin: 0px auto;
	margin-top: 1em;
}

.brand {
	font-family: georgia, serif;
}

.brand .first {
	color: #ccc;
	font-style: italic;
}

.brand .second {
	color: #fff;
	font-weight: bold;
}
</style>
<script type="text/javascript">
	/*
	 方法：详情清除缓存，
	 */
	function clear() {
		$.session.clear();
	}
	/*
	方法详情：当你没有登陆访问这个页面时，会自动跳转到登陆界面
*/
$(function(){
	var menus="<%=session.getAttribute("menus")%>";
	if(menus ==="null"){
		window.location.href="${ctx }/login.jsp";
	}
})
function submit_user(){
		var pwd=$("#password").val();
		var md5 = new MD5();
		$("#password").val(md5.MD5(pwd));
		$("#tab").submit();
	}
</script>
</head>

<!--[if lt IE 7 ]> <body class="ie ie6"> <![endif]-->
<!--[if IE 7 ]> <body class="ie ie7 "> <![endif]-->
<!--[if IE 8 ]> <body class="ie ie8 "> <![endif]-->
<!--[if IE 9 ]> <body class="ie ie9 "> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<body class="">
	<!--<![endif]-->

	<div class="navbar">
		<div class="navbar-inner">
			<ul class="nav pull-right">

				<li><a href="#"
					class="hidden-phone visible-tablet visible-desktop" role="button">Settings</a></li>
				<li id="fat-menu" class="dropdown"><a href="#" role="button"
					class="dropdown-toggle" data-toggle="dropdown"> <i
						class="icon-user"></i> hello,${loginRoleName} <i
						class="icon-caret-down"></i>
				</a>

					<ul class="dropdown-menu">
						<li><a tabindex="-1" href="#">My Account</a></li>
						<li class="divider"></li>
						<li><a tabindex="-1" class="visible-phone" href="#">Settings</a></li>
						<li class="divider visible-phone"></li>
						<li><a tabindex="-1" href="${ctx }/login.jsp"
							onclick="clear()">Logout</a></li>
					</ul></li>
			</ul>
			<a class="brand" href="index.html"><span class="first">XUAN</span>
				<span class="second">FENG</span></a>
		</div>
	</div>




	<div class="sidebar-nav">
		<c:forEach items="${menus }" var="ms">
			<a href="#dashboard-menu" class="nav-header" data-toggle="collapse">
				<i class="icon-dashboard"></i>${ms.key.name }</a>
			<ul id="dashboard-menu" class="nav nav-list collapse in">
				<c:forEach items="${ms.value }" var="menu" varStatus="sta">
					<li><a href="${menu.url }">${menu.name }</a></li>
				</c:forEach>
			</ul>
		</c:forEach>
	</div>

	<div class="content">

		<div class="header">

			<h1 class="page-title">Edit User</h1>
		</div>

		<ul class="breadcrumb">
			<li><a href="lists">Home</a> <span class="divider">/</span></li>
			<li><a href="lists">Users</a> <span class="divider">/</span></li>
			<li class="active">User</li>
		</ul>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="well">
					<ul class="nav nav-tabs">
						<li class="active"><a href="#home" data-toggle="tab">Profile</a></li>

					</ul>
					<div id="myTabContent" class="tab-content">
						<div class="tab-pane active in" id="home">
						<!-- 当修改的时候不可以更改用户的权限，当用户创建的时候权限已经确定 -->
							<c:if test="${action=='edit' }">
								<form action="${ctx }/user/edit" method="post" id="tab"	style="margin-left: 1em">
									<label>LoginName</label> 
									<input type="text" name="loginName"	value="${loginUser.loginName}" class="input-xlarge"
										readonly="readonly">
									<label>Role</label> 
									<input type="text"	value="${loginUser.role.name}" class="input-xlarge"
										readonly="readonly">
										
									<label>Password</label> 
							<input type="text" name="password" id="password"
								value="${loginUser.password}" class="input-xlarge"> 
							<label>Email</label>
							<input type="text" name="email" id="email" value="${loginUser.email}"
								class="input-xlarge">
							<div class="btn-toolbar">
							<button class="btn btn-primary" >
										<i class="icon-save" ></i> Update
										</button>
									<div class="btn-group"></div>
									</div>
									</form>
							</c:if>
							<c:if test="${action!='edit' }">
								<form action="${ctx }/user/add" method="post" id="tab"style="margin-left: 1em">
									<label>LoginName</label> 
									<input type="text" name="loginName" id="loginName"
										 class="input-xlarge">
									<label>RoleId</label>
									<input type="text" name="roleId" id="roleId"/>
									<label>Password</label> 
							<input type="text" name="password" id="password"
								 class="input-xlarge"> 
							<label>Email</label>
							<input type="text" name="email" id="email"
								class="input-xlarge">
							<div class="btn-toolbar">
							<button class="btn btn-primary"  onclick="javascrpt:submit_user()">
										<i class="icon-save" ></i> Save
									</button>
									<div class="btn-group"></div>
									</div>
									</form>
							</c:if>
						</div>
					</div>

				</div>

				<div class="modal small hide fade" id="myModal" tabindex="-1"
					role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h3 id="myModalLabel">Delete Confirmation</h3>
					</div>
					<div class="modal-body">

						<p class="error-text">
							<i class="icon-warning-sign modal-icon"></i>Are you sure you want
							to delete the user?
						</p>
					</div>
					<div class="modal-footer">
						<button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
						<button class="btn btn-danger" data-dismiss="modal">Delete</button>
					</div>
				</div>



				<footer>
				<hr>


				<p class="pull-right">
					Collect from <a href="http://fengtingxin.cn" title="网页模板"
						target="_blank">网页模板</a>
				</p>

				<p>
					&copy; 2012 <a href="#" target="_blank">Portnine</a>
				</p>
				</footer>

			</div>
		</div>
	</div>



	<script src="${ctx }/resources/js/bootstrap.js"></script>
	<script type="text/javascript">
		$("[rel=tooltip]").tooltip();
		$(function() {
			$('.demo-cancel-click').click(function() {
				return false;
			});
		});
	</script>

</body>
</html>