<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">



<title>xTalker</title>

<!-- Bootstrap core CSS -->
<link href="<c:url value="/css/bootstrap/css/bootstrap.min.css"/>"
	rel="stylesheet">
<link href="<c:url value="/css/bootstrap/docs.min.css"/>" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="<c:url value="/css/bootstrap/navbar.css"/>" rel="stylesheet">

<style type="text/css">
body {
	padding-top: 50px;
	padding-bottom: 20px;
}
</style>

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script
	src="<c:url value="/css/bootstrap/assets/js/ie-emulation-modes-warning.js"/>"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

	<div class="container">
	<!--  <div class="container-fluid"><img src="<c:url value="/image/icon140.png"/>"  class="img-rounded"  width="50" height="50">
		</div>-->
		<!-- Static navbar -->
		<nav class="navbar navbar-default" role="navigation">
		
			<div class="container-fluid">
			
				<div class="navbar-header">
					<a class="navbar-brand" href="<c:url value="/index.form#home"/>">xTalker</a>
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<li <c:if test="${CurrentNode ne 'account'}" >class="active"</c:if>><a href="<c:url value="/index.form#home"/>">Profile</a></li>
						<c:if test="${not empty CurrentUser}">
						<li <c:if test="${CurrentNode eq 'account'}" >class="active"</c:if>><a href="<c:url value="/web/user/account.form"/>">Account</a></li>
						</c:if>
						<li><a href="<c:url value="/index.form#contact"/>">Contact</a></li>
						<li><a href="<c:url value="/index.form#about"/>">About</a></li>
						<%--
						<li><a href="#">Doc</a></li>
						<li><a href="#">Download</a></li>
						 --%>
						<%--
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                  <li><a href="#">Action</a></li>
                  <li><a href="#">Another action</a></li>
                  <li><a href="#">Something else here</a></li>
                  <li class="divider"></li>
                  <li class="dropdown-header">Nav header</li>
                  <li><a href="#">Separated link</a></li>
                  <li><a href="#">One more separated link</a></li>
                </ul>
              </li>
               --%>
					</ul>
					<div id="navbar" class="navbar-collapse collapse">
						
						<form class="navbar-form navbar-right" action="<c:url value="/web/user/login.form"/>" method="Post">
							<c:if test="${ empty CurrentUser}">
							<div class="form-group">
								<input name="identity" type="text" placeholder="somebody@x-talker.net" class="form-control">
							</div>
							<div class="form-group">
								<input name ="k" type="password" placeholder="Password"
									class="form-control">
							</div>
							<button type="submit" class="btn btn-default">Sign in</button>
							<button type="button" class="btn btn-default" onclick="location.href='<c:url value="/web/user/registerView.form" />'">Register</button>
							</c:if>
							<c:if test="${not empty CurrentUser}">&nbsp;&nbsp;
						${CurrentUser} <button type="button" class="btn btn-default" onclick="location.href='<c:url value="/web/user/logout.form"/>'">Sign Out</button>
						</c:if>
						&nbsp;
						
							
						</form>
						
						
					</div>
					<!--/.navbar-collapse -->
				</div>
				<!--/.nav-collapse -->
			</div>
			<!--/.container-fluid -->
		</nav>