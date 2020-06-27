<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de usuário</title>

<link rel="stylesheet" href="resources/css/styleCadastro.css">

<!-- Adicionando JQuery -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>


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
					value="${user.id}" class="sign-up-input" placeholder="Id" autofocus></td>
			</tr>
			<tr>
				<td><input type="text" id="login" name="login"
					value="${user.login}" class="sign-up-input" placeholder="Login"
					autofocus></td>
			</tr>
			<tr>
				<td><input type="password" id="senha" name="senha"
					value="${user.senha}" class="sign-up-input" placeholder="Senha"></td>
			</tr>
			<tr>
				<td><input type="text" id="nome" name="nome"
					value="${user.nome}" class="sign-up-input" placeholder="Nome"
					autofocus></td>
			</tr>
			<tr>
				<td><input type="text" id="fone" name="fone"
					value="${user.fone}" class="sign-up-input" placeholder="Telefone"
					autofocus></td>
			</tr>
			<tr>
				<td><input type="text" id="cep" name="cep"
					value="${user.cep}" class="sign-up-input" placeholder="Cep" autofocus></td>
			</tr>
			<tr>
				<td><input type="text" id="rua" name="rua"
					value="${user.rua}" class="sign-up-input" placeholder="Rua" autofocus></td>
			</tr>
			<tr>
				<td><input type="text" id="bairro" name="bairro"
					value="${user.bairro}" class="sign-up-input" placeholder="Bairro" autofocus></td>
			</tr>
			<tr>
				<td><input type="text" id="numero" name="numero"
					value="${user.numero}" class="sign-up-input" placeholder="Nº" autofocus></td>
			</tr>
			<tr>
				<td><input type="text" id="cidade" name="cidade"
					value="${user.cidade}" class="sign-up-input" placeholder="Cidade" autofocus></td>
			</tr>
			<tr>
				<td><input type="text" id="uf" name="uf" class="sign-up-input"
					value="${user.uf}" placeholder="Uf" autofocus></td>
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
						<div class="cell">Login</div>
						<div class="cell">Id</div>
						<div class="cell">Nome</div>
						<div class="cell">Tel</div>
						<div class="cell">Editar</div>
						<div class="cell">Excluir</div>
						<div class="cell">Telefones</div>
					</div>

					<c:forEach items="${usuarios}" var="user">
						<div class="row">
							<div class="cell">
								<c:out value="${user.login}"></c:out>
							</div>
							<div class="cell">
								<c:out value="${user.id}"></c:out>
							</div>
							<div class="cell">
								<c:out value="${user.nome}"></c:out>
							</div>
							<div class="cell">
								<c:out value="${user.fone}"></c:out>
							</div>
							<div class="cell">
								<a href="salvarUsuario?acao=editar&user=${user.id}"><img
									alt="Editar" title="Editar" src="resources/imagens/editar.svg"
									style="width: 20px; height: 20px"></a>
							</div>
							<div class="cell">
								<a href="salvarUsuario?acao=delete&user=${user.id}"><img
									alt="Excluir" title="Excluir"
									src="resources/imagens/lixeira.svg"
									style="width: 20px; height: 20px"></a>
							</div>
							<div class="cell">
								<a href="salvarTelefones?user=${user.id}"><img
									alt="Telefones" title="Telefones"
									src="resources/imagens/iconeTelefone.png"
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
		var nome = document.getElementById("login").value;

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
	
	
		$(document).ready(
				function() {

					$("#cep").blur(
							function() {

								var cep = $(this).val();

								$.getJSON("https://viacep.com.br/ws/" + cep
										+ "/json/?callback=?", function(dados) {

									if (!("erro" in dados)) {
										$("#rua").val(dados.logradouro);
										$("#bairro").val(dados.bairro);
										$("#cidade").val(dados.localidade);
										$("#uf").val(dados.uf);
									} else {
										alert("CEP não encontrado.");
									}
								});
							});
				});
	</script>
</body>
</html>