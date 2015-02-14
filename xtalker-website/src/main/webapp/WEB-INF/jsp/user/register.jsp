<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/include/header.jsp" />

<form action="<c:url value="/service/user/register.form" />">
  <div class="form-group">
    <label for="userName">User Name</label>
    <input type="email"  class="form-control" id="userName" placeholder="somebody@x-talker.net">
  </div>
  <div class="form-group">
    <label for="inputPassword">Password</label>
    <input type="password" class="form-control" id="inputPassword" placeholder="Password">
  </div>
  <div class="form-group">
    <label for="re-InputPassword">Re-Password</label>
    <input type="password" class="form-control" id="re-InputPassword" placeholder="Re-Password">
  </div>

  <button type="Submit" class="btn btn-default">register</button>
</form>

<!-- /container -->
<jsp:include page="/include/footer.jsp" />