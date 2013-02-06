<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="myStyle.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="myScript.js"></script>
<title>uTests</title>
</head>
<body>
	<%
		String currentPage = null;
	%>
	<h3>
		<a href="index.jsp">uTests</a>
	</h3>
	<ul>
		<li><a href="index.jsp?jmsWebPage.jsp">JMS</a></li>
		<li><a href="index.jsp?currpage=memConsume">Consume memory</a></li>
		<li><a href="index.jsp?currpage=jersey">Jersey Rest</a></li>
		<li><a href="index.jsp?currpage=escalation">Escalation</a></li>
	</ul>


	<%
		if (request.getParameter("currpage") != null) {
			currentPage = request.getParameter("currpage");
			if (currentPage.equals("memConsume")) {
	%>
	<jsp:include page="memConsume.jsp" flush="true" />
	<%
		}
			if (currentPage.equals("jersey")) {
	%>
	<jsp:include page="webSrvsWebPage.jsp" flush="true" />
	<%
		}
			if(currentPage.equals("escalation")){
	%>
	<jsp:include page="escalation.jsp" flush="true" />
	<%
		}
			}else
				{ out.print("No page were selected"); }
	%>


</body>
</html>