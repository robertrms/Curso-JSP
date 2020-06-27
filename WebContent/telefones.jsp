<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de Telefones</title>

<link rel="stylesheet" href="resources/css/styleCadastro.css">

</head>
<body>

	<a href="acessoLiberado.jsp" id="botaoVoltar"
		style="margin-left: 15px;">Voltar</a>
	<a href="index.jsp" id="botaoSair" style="margin-left: 10px;">Sair</a>

	<h2 style="color: red; margin-left: 560px;">${msg}</h2>
	<form action="salvarUsuario" method="post" class="sign-up"
		id="formUser">
		<table>
			<tr>
				<td><input type="text" readonly="readonly" id="id" name="id"
					value="${userEscolhido}" class="sign-up-input" placeholder="Id" autofocus></td>
			</tr>
		</table>
		<input type="submit" value="Cadastrar" class="sign-up-button">
		<input type="submit" class="sign-up-button" value="Atualizar"
			onclick="document.getElementById('formUser').action = 'salvarUsuario?acao=reset'"
			style="margin-top: 10px;">
	</form>
	<hr>
	<div class="limiter">
		<div class="container-table100">
			<div class="wrap-table100">
				<div class="table">
					<div class="row header">
						<div class="cell">Id</div>
						<div class="cell">Número</div>
						<div class="cell">Tipo</div>
						<div class="cell">Excluir</div>
					</div>

					<c:forEach items="${telefones}" var="fone">
						<div class="row">
							<div class="cell">
								<c:out value="${fone.id}"></c:out>
							</div>
							<div class="cell">
								<c:out value="${fone.numero}"></c:out>
							</div>
							<div class="cell">
								<c:out value="${fone.tipo}"></c:out>
							</div>
							<div class="cell">
								<a href="salvarUsuario?acao=delete&user=${fone.id}"><img
									alt="Excluir" title="Excluir"
									src="resources/imagens/lixeira.svg"
									style="width: 20px; height: 20px"></a>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</body>
</html>