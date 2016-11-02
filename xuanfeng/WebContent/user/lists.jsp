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
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link rel="stylesheet" type="text/css"	href="${ctx }/resources/style/bootstrap.css">
<link rel="stylesheet" type="text/css"	href="${ctx }/resources/style/theme.css">
<link rel="stylesheet" href="${ctx }/resources/style/font-awesome.css">
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
<title>User List</title>
<script type="text/javascript">
	function search() {
		var p = $("#searchParam").val();
		window.location.href="${ctx }/user/lists?searchParam="+p;
	}
	/*
		方法详情：当你没有登陆访问这个页面时，会自动跳转到登陆界面
	*/
	$(function(){
		var menus="<%=session.getAttribute("menus")%>";
		if(menus ==="null"){
			window.location.href="${ctx }/login.jsp";
		}
		$("[name='pagen']").each(function(key,value){
			$(this).attr("href",$(this).attr("href")+"&searchParam="+$("#searchParam").val());
		});
	})
	/*
		方法：详情清除缓存，
	*/
	function clear() {
		$.session.clear();
	}
	/*
	删除的
	*/
	function del(loginName){
		if(confirm("确定要删除该用户？")){
		window.location.href = "delete?loginName="+loginName;
		}
		}
</script>
</head>
<body class="">
	<!--<![endif]-->

	<div class="navbar">
		<div class="navbar-inner">
			<ul class="nav pull-right">

				<li><a href="#"
					class="hidden-phone visible-tablet visible-desktop" role="button">Settings</a></li>
				<li id="fat-menu" class="dropdown"><a href="#" role="button"
					class="dropdown-toggle" data-toggle="dropdown"> <i
						class="icon-user"></i>hello,${loginRoleName} <i
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

			<h1 class="page-title">Users</h1>
		</div>

		<ul class="breadcrumb">
			<li><a href="${ctx}/user/lists">Home</a> <span class="divider">/</span></li>
			<li class="active">Users</li>
		</ul>

		<div class="container-fluid">
			<div class="row-fluid">

				<div class="btn-toolbar">
					<button class="btn btn-primary"
						onclick="window.location.href='user'">
						<i class="icon-plus"></i> New User
					</button>
					<input id="searchParam" type="text" value="${searchParam }" style="margin-left:2em;margin-top:0.55em;" />
    <button style="margin-left:1em;" onclick="javascrpt:search()"> search</button>
					<div class="btn-group"></div>
				</div>
				<div class="well">
					<table class="table">
						<thead>
							<tr>
								<th>#</th>
								<c:forEach items="${title}" var="keyword">
									<th>${keyword}</th>
								</c:forEach>
								<th style="width: 26px;">editor</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.list}" var="p"  varStatus="status">
							
								<tr>
								<td>${ status.index + 1}</td>
								<td><a href="${ctx }/user/editInfo?name=${p.loginName }">${p.loginName}</a></td>
								<td>${p.password}</td>
								<td>${p.email }</td>
								<td>${p.role.name }</td>
								<td><a href="${ctx }/user/edit?loginName=${p.loginName }"><i class="icon-pencil"></i></a>
								 <a role="button" data-toggle="modal" onclick="del('${p.loginName }')">
								 <!-- 我修改的这里的a 标签
								 它原本的代码为 
								 href="#modal"
								  -->
								 <i	class="icon-remove"></i>
								 </a></td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="pagination">
					<ul>
						<c:forEach begin="1" end="${page.totalPageNum }" var="pageNum">
							<li><a name="pagen" href="${ctx }/user/lists?pageNum=${pageNum }">${pageNum }</a></li>
						</c:forEach>
					</ul>
				</div>
				<!-- 没错这就是那个提示删除的框！！！ -->
				<!-- 但是这里没有被调用！！！！
				
					现在可能实现不了这种功能
					主要的难点在当点击其中内容删除的时候，会调用到这唯一的这一个提示框，但是这个提示框怎么获取到值，就不清楚了，同时也要这个提示框的button获取到第一次点击所获取的值
					
					它是隐藏的！
				 -->

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
						<button class="btn btn-danger" data-dismiss="modal" id="delete_sure"  >Delete</button>
					</div>
				</div>
				<footer>
				<hr>
				<p class="pull-right">
					Collect from <a href="http://fengtingxin.cn" title="网页模板"
						target="_blank">here you are</a>
				</p>

				<p>
					&copy; 2016 <a href="http://fengtingxin.cn" target="_blank">welcome</a>
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