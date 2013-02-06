<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Consume memory</title>
</head>
<body>

<h3>Consume memory</h3>
<form action="Gservlet" method="get" target="resFrame">
	<table>
		<tr>
			<td>Consume this memory</td>
			<td>
				<input type="text" name="memKB"/>
				<select name="meter">
					<option value="kb">KB</option>
					<option value="mb">MB</option>
				</select>
			</td>
		</tr>
		<tr>
			<td><input type="submit" name="Allocate" value="Allocate More"/></td>
			<td><input type="submit" name="Free" value="Free Memory"/></td>
		</tr>
	</table>
</form>

<iframe name="resFrame" width="370">
No results till now
</iframe>

</body>
</html>