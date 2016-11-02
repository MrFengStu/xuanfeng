<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page isErrorPage="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

<link rel="stylesheet" type="text/css"	href="${ctx }/resources/style/bootstrap.css">
<link rel="stylesheet" type="text/css" href="${ctx }/resources/style/theme.css">
<link rel="stylesheet" href="${ctx }/resources/style/font-awesome.css">

    <script src="${ctx }/resources/js/jquery-1.7.2.min.js" type="text/javascript"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>500</title>
<style type="text/css">
        #line-chart {
            height:300px;
            width:800px;
            margin: 0px auto;
            margin-top: 1em;
        }
        .brand { font-family: georgia, serif; }
        .brand .first {
            color: #ccc;
            font-style: italic;
        }
        .brand .second {
            color: #fff;
            font-weight: bold;
        }
    </style>

    
  </head>

  <body class="http-error"> 
  <!--<![endif]-->

        <div class="row-fluid">
    <div class="http-error">
        <h1>Oops!</h1>
        <p class="info">Something happened that we didn't expect.</p>
        <p><i class="icon-home"></i></p>
        <p><a href="${ctx }/login.jsp">Back to the home page</a></p>
    </div>
</div>
    <script src="${ctx }/resources/js/bootstrap.js"></script>
    <script type="text/javascript">
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });
    </script>

</body>
</html>