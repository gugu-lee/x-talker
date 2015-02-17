<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/include/header.jsp" />

Your Account Configure:
<div class="highlight">
	<dl>
		<dt>IMPI</dt>
		<dd>${accountResult.result.identity}</dd>
		<dt>Email:</dt>
		<dd>${accountResult.result.email}</dd>
		<dt>IMPU:</dt>
		<dd>sip:${accountResult.result.identity}</dd>
		<dt>P-cscf Host:</dt>
		<dd>pcscf.x-talker.net</dd>
		<dt>P-cscf Port:</dt>
		<dd>4060</dd>
		<dt>transport:</dt>
		<dd>UDP</dd>
		<dt>Realm:</dt>
		<dd>x-talker.net</dd>
		<dt>Domain:</dt>
		<dd>x-talker.net</dd>
		<dt>Operator ID:</dt>
		<dd>0x00000000000000000000000000000000</dd>
		<dt>AMF:</dt>
		<dd>0x0000</dd>
	</dl>
</div>
	reset password:
<div class="highlight">

	<form name="resetPassword" action="<c:url value="/web/user/resetPassword.form"/>" method="post">
		<div class="form-group">
			<label for="inputPassword">Password</label> <input type="password"
				class="form-control" id="inputPassword" placeholder="Password" name="inputPassword">
		</div>
		<div class="form-group">
			<label for="re-InputPassword">Re-Password</label> <input
				type="password" class="form-control" id="reInputPassword" name="reInputPassword"
				placeholder="Re-Password">
		</div>
		<button type="Submit" class="btn btn-default">Reset Password</button>
	</form>
</div>
<!-- /container -->
<jsp:include page="/include/footer.jsp" />