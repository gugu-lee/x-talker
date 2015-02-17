<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/include/header.jsp" />

<div class="highlight">
Fail to reset your password.Reason is <br>
<strong> ${result.errorMessage} </strong>
</div>
<!-- /container -->
<jsp:include page="/include/footer.jsp" />