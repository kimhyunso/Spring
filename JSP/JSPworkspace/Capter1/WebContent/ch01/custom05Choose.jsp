<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");
	
%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>custom03IF.jsp </title>

    <!-- Bootstrap -->
    <link href="./css/bootstrap.min.css" rel="stylesheet">
    <link href="./css/custom2.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
<body>

<div class="container">

	<header>
		<h2>JSTL Custom Action</h2>
	</header>
	
	<p>
		choose (자바 switch)<br>
		<c:if test="${empty param.num }">
			Usage : custom05Choose.jsp?num=5 <br>
		</c:if>		
		
		<c:if test="${not empty param.num }">
			<c:choose>
				<c:when test="${param.num eq 0 }">
					Hello, Zero!! <br>
				</c:when>
				
				<c:when test="${param.num eq 1 }">
					안녕, 하나!! <br>
				</c:when>
				
				<c:when test="${param.num eq 2 }">
					ni hao, 这个 <br>
				</c:when>
				
				<c:otherwise>
					I don't Know... what u say...<br>				
				</c:otherwise>
			</c:choose>
		</c:if>
	</p>
	
</div>

	<script src="./js/bootstrap.min.js"></script>
</body>
</html>