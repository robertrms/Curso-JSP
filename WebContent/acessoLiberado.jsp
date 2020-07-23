<jsp:useBean id="bean" class="modelo.BeanCursoJsp" type="modelo.BeanCursoJsp" scope="page"></jsp:useBean>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<center style="padding-top: 10%;">
	<jsp:setProperty property="*" name="bean"/>
	<h2>Seja bem vindo ao sistema - JSP</h2>
	
	<a href="salvarUsuario?acao=listartodos"><img style="width: 80px; height: 80px;" alt="iconeUsuario" title="iconeUsuario" src="resources/imagens/iconeUsuario.jpg"></a>
	<a href="salvarProduto?acao=listartodos"><img style="width: 80px; height: 80px;" alt="iconeProduto" title="iconeProduto" src="resources/imagens/iconeProduto.png"></a>
</center>
</body>
</html>