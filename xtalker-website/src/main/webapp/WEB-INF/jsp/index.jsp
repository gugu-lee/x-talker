<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/include/header.jsp" />
<!-- Main component for a primary marketing message or call to action -->
<div class="jumbotron">
	<h2>xTalker</h2>
	<p>
		xTalker is The Full VoLTE Solution For Enterprise .<br>
		 xTalker provide text message, MMS Message,voice
		call,video call communcation.<br>
		 xTalker support IMS
		standard.<br> xTalker is base of the OpenIMSCore.<br> It's
		use case diagram is blow:<br>
		<!-- 
			<p>
				<a class="btn btn-lg btn-primary" href="../../components/#navbar"
					role="button">View navbar docs &raquo;</a>
			</p>
			 -->
		<img align="middle" src="<c:url value="/image/UseCase.jpg"/>" />
	</p>

	<h3>Application Scene</h3>
	<p>You could use this solution when you:<br>
	want setup a LTE core network;<br>
	want debug a LTE mobile terminal;<br>
	want setup a instant message system.
	</p>
	<%--
	<h3>Download Source</h3>
	<P>
	github address:<br>
	git://github.com/gugu-lee/x-talker.git
	</P>
	 --%>
	
	<h3>Screenshot</h3>
	<p>
	</p>
	
	<h3 id="contact">Contact Us</h3>
	<p>
		Also when you want setup a LTE Core Network,<br>
		when you want to get some help for develop IMS System,<br>
		when you want to get source for IMSCore or IOS/Andriod/WP8/ App, <br>
		 Please send email to <font color="blue">service@x-talker.net</font>.</p>
		
	<h3 id="about">About</h3>
	<p>xTalker Version:0.0.2<br></p>
</div>


<!-- /container -->

<jsp:include page="/include/footer.jsp" />