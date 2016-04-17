<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Upload File</title>
</head>
<body style="text-align: center;">
	<form action="${pageContext.request.contextPath}/UploadFileServlet"
		method="post" enctype="multipart/form-data">
		<table width="50%" frame="border" align="center">
			<tr>
				<td>Username</td>
				<td><input type="text" name="username"></td>
			</tr>
			<tr>
				<td>File</td>
				<td><input type="file" name="file"></td>
			</tr>
			<tr>
				<td>Description</td>
				<td><textarea rows="6" cols="50" name="description"></textarea></td>
			</tr>
			<tr>
				<td><input type="submit" value="upload"></td>
			</tr>
		</table>
	</form>
</body>
</html>