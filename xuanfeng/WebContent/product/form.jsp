<%@page import="com.xuanfeng.user.control.UserControl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.xuanfeng.user.*"%>
<%@page import="com.xuanfeng.util.*"%>
<%@page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Product List</title>
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<link rel="stylesheet" type="text/css"
	href="${ctx }/resources/style/bootstrap.css">

<link rel="stylesheet" type="text/css"
	href="${ctx }/resources/style/theme.css">
<link rel="stylesheet" href="${ctx }/resources/style/font-awesome.css">

<script src="${ctx }/resources/js/jquery-1.7.2.min.js"type="text/javascript"></script>

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
			<li><a href="${ctx }/user/lists">Home</a> <span class="divider">/</span></li>
			<li><a href="${ctx }/product/list">List</a> <span class="divider">/</span></li>
			<li class="active">Form</li>
		</ul>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="well">
					<ul class="nav nav-tabs">
						<li class="active"><a href="#home" data-toggle="tab">Profile</a></li>

					</ul>
					<div id="myTabContent" class="tab-content">
						<div class="tab-pane active in" id="home">
							<c:if test="${action=='edit' }">
								<form action="${ctx }/product/edit" method="post" id="tab"
									style="margin-left: 1em">
									<label>productId</label> <input type="text" name="productId"
										value="${pro.productId}" class="input-xlarge"
										readonly="readonly">
							</c:if>
							<c:if test="${action!='edit' }">
								<form action="${ctx }/product/add" method="post" id="tab"
									style="margin-left: 1em">
							</c:if>
							<label>name</label> <input type="text" name="name"
								value="${pro.name}" class="input-xlarge"> 
								<label>price</label>
							<input type="text" name="price" value="${pro.price}"
								class="input-xlarge">
							<div class="btn-toolbar">
								<button class="btn btn-primary">
									<c:if test="${action=='edit' }">
										<i class="icon-save"></i> Update
										</c:if>
									<c:if test="${action!='edit' }">
										<i class="icon-save"></i> Save
										</c:if>
								</button>
								<div class="btn-group"></div>
							</div>
							</form>

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