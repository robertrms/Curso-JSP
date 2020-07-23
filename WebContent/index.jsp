<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" href="resources/css/estilo.css">

</head>
<body>
	<div class="login-page">
		<center><h3>Projeto Didático</h3></center><br/>
		<center><h1>JPS + Servlet + JDBC</h1></center>
		<div class="form">
			<form action="LoginServlet" method="post" class="login-form">
				<input type="text" placeholder="login" id="login" name="login">
				<input type="password" placeholder="password" id="senha" name="senha">
				 <button>login</button>
			</form>
		</div>
		<center><h3>Formação Java Web</h3></center>
	</div>
</body>
</html>