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
		<div class="form">
			<form action="LoginServlet" method="post" class="login-form">
				<input type="text" placeholder="login" id="login" name="login">
				<input type="password" placeholder="password" id="senha" name="senha">
				 <button>login</button>
			</form>
		</div>
	</div>
</body>
</html>