<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de produto</title>

<script src="resources/js/jquery.min.js" type="text/javascript"></script>
<script src="resources/js/jquery.maskMoney.min.js" type="text/javascript"></script>
<link rel="stylesheet" href="resources/css/styleCadastro.css">

</head>
<body>

	<a href="acessoLiberado.jsp" id="botaoVoltar" style="margin-left: 15px;">Voltar</a>
	<a href="index.jsp" id="botaoSair" style="margin-left: 10px;">Sair</a>

	<h2 style="color: red; margin-left: 560px;">${msg}</h2>
	<form action="salvarProduto" method="post" class="sign-up"
		id="formUser" onsubmit="return validarCampos() ? true : false">
		<table>
			<tr>
				<td><input type="text" readonly="readonly" id="id" name="id"
					value="${pdt.id}" class="sign-up-input" placeholder="Id" autofocus></td>
			</tr>
			<tr>
				<td><input type="text" id="nome" name="nome"
					value="${pdt.nome}" class="sign-up-input"
					placeholder="Nome produto" autofocus></td>
			</tr>
			<tr>
				<td><input type="number" id="quantidade" name="quantidade"
					value="${pdt.quantidade}" class="sign-up-input"
					placeholder="Quantidade Uni"></td>
			</tr>
			<tr>
				<td><input type=text id="valor" name="valor" data-thousands="." data-decimal="," data-prefix="R$ "
					value="${pdt.valor}" class="sign-up-input" placeholder="Valor"
					autofocus></td>
			</tr>
		</table>
		<input type="submit" value="Cadastrar Produto" class="sign-up-button">
		<input type="submit" class="sign-up-button" value="Atualizar"
			onclick="document.getElementById('formUser').action = 'salvarProduto?acao=reset'"
			style="margin-top: 10px;">
	</form>
	<hr>
	<div class="limiter">
		<div class="container-table100">
			<div class="wrap-table100">
				<div class="table">
					<div class="row header">
						<div class="cell">Id</div>
						<div class="cell">Nome</div>
						<div class="cell">Quantidade</div>
						<div class="cell">Valor</div>
						<div class="cell">Editar</div>
						<div class="cell">Excluir</div>
					</div>

					<c:forEach items="${produtos}" var="pdt">
						<div class="row">
							<div class="cell">
								<c:out value="${pdt.id}"></c:out>
							</div>
							<div class="cell">
								<c:out value="${pdt.nome}"></c:out>
							</div>
							<div class="cell">
								<c:out value="${pdt.quantidade}"></c:out>
							</div>
							<div class="cell">
								<c:out value="${pdt.valor}"></c:out>
							</div>
							<div class="cell">
								<a href="salvarProduto?acao=editar&pdt=${pdt.id}"><img
									alt="Editar" title="Editar" src="resources/imagens/editar.svg"
									style="width: 20px; height: 20px"></a>
							</div>
							<div class="cell">
								<a href="salvarProduto?acao=delete&pdt=${pdt.id}" onclick="return confirm('Confirmar a exclusão?');"><img
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
	<script>
		function validarCampos() {
			var nome = document.getElementById("nome").value;
			var quantidade = document.getElementById("quantidade").value;
			var valor = document.getElementById("valor").value;

			if (nome == '' && quantidade == '' && valor == '') {
				alert("Todos os campos são obrigatorios.");
				return false;
			} else if (nome != '' && quantidade == '' && valor == '') {
				alert("Os campos quantidade e valor são obrigatorios.");
				return false;
			} else if (nome != '' && quantidade != '' && valor == '') {
				alert("O campo valor é obrigatorio.");
				return false;
			} else if (nome == '' && quantidade == '' && valor != '') {
				alert("Os campos nome e quantidade são obrigatorios.");
				return false;
			} else if (nome == '' && quantidade != '' && valor != '') {
				alert("O campo nome é obrigatorio.");
				return false;
			} else if (nome != '' && quantidade == '' && valor != '') {
				alert("O campo quantidade é obrigatorio.");
				return false;
			} else if (nome == '' && quantidade != '' && valor == '') {
				alert("Os campos nome e valor são obrigatorios.");
				return false;
			}

			return true;

		}
	</script>
</body>

<script type="text/javascript">
	$(function() {
		$('#valor').maskMoney();
	})
</script>
</html>