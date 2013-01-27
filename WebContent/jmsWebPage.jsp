<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="myStyle.css" type="text/css" rel="stylesheet" />
<title>JMS Page</title>
</head>
<body>
	<h3>JMS</h3>
	<form action="JmsServlet" method="post" target="iframeResult">
		<table>
			<tr>
				<td colspan="2">Table title</td>
			</tr>
			<tr>
				<td>Machine-IP</td><td><input type="text" name="machineIp" value="localhost" /></td>
			</tr>
			<tr>
				<td>Connection Factory JNDI</td><td><input type="text" name="cfJndi" value ="MyCF"/></td>
			</tr>
			<tr>
				<td>Queue JNDI</td><td><input type="text" name="qJndi" value="jms/MyQ" /></td>
			</tr>
			<tr>
				<td>JMS delay</td><td><input type="text" name="jmsDelay" value="0"/></td>
			</tr>
			<tr>
				<td>inMessage delay</td><td><input type="text" name="inMsgDelay" value="0"/></td>
			</tr>
			<tr>
				<td>Message content</td><td><input type="text" name="msgContent" value="My Name is "/></td>
			</tr>
			<tr><td>Message type</td>
				<td>
					<select name="msgType">
						<option value="textMsg">Text message</option>
						<option value="mapMsg">Map message</option>
						<option value="binMsg">Binary message</option>
					</select>
				</td>
			</tr>
			<tr><td>Action</td>
				<td>
					<select name="action">
						<option value="browse">Show messages</option>
						<option value="send">Send message</option>
						<option value="clear">Clear the queue</option>
					</select>
				</td>
			</tr>
			<tr><td></td><td><input type="submit" value="Performe action" /></td></tr>
		</table>
	</form>
	<br>
	<br>
	Result
	<br>
	<iframe name="iframeResult" width="60%" height="220px"></iframe>
</body>
</html>