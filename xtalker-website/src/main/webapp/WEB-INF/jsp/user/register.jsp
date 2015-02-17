<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/include/header.jsp" />

<form action="<c:url value="/web/user/register.form" />" method="post">
  <div class="form-group">
    <label for="userName">User Name<!-- (min 4 character,it's may include any a-z/A-Z character and digit,and must include 1 digit.) --></label>
    <input  class="form-control" id="userName" placeholder="name your like" name="userName">
  </div>
  <div class="form-group">
    <label for="email">Email</label>
    <input type="email"  class="form-control" id="email" placeholder="yourname@email.com" name="email">
  </div>
  <div class="form-group">
    <label for="inputPassword">Password<!-- (min 6 character,it's may include any a-z/A-Z character and digit,and must include 1 digit.) --></label>
    <input type="password" class="form-control" id="inputPassword" placeholder="Password" name="inputPassword">
  </div>
  <div class="form-group">
    <label for="re-InputPassword">Re-Password</label>
    <input type="password" class="form-control" id="reInputPassword" placeholder="Re-Password" name="reInputPassword">
  </div>

  <button type="Submit" class="btn btn-default">register</button>
</form>

<!-- /container -->
<jsp:include page="/include/footer.jsp" />