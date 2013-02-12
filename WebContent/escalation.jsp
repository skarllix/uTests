<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Escalation</title>
</head>
<body>
	<form  id="escalationForm" action="Gservlet" method="get" target="escalFrame">
		<table>
			<tr>
				<td>transaction count</td>
				<td><input type="text" name="tansCnt" value="20" id="transCnt" /></td>
			</tr>
			<tr>
				<td>transactions gap</td>
				<td><input type="color" name="transGap" value="7" />(sec)</td>
			</tr>
			<tr>
				<td><input type="button" value="start" name="submitEscalation" onclick="foo()" /></td>
			</tr>
		</table>
	</form>

	<iframe name="escalFrame"> </iframe>

</body>
</html>