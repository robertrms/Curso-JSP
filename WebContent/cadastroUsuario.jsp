<%@page import="modelo.BeanCursoJsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de usuário</title>

<link rel="stylesheet" href="resources/css/styleCadastro.css">
<link rel="stylesheet" href="resources/css/styleCadastroModificado.css">

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
		id="formUser" enctype="multipart/form-data">
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
				<td><input type="number" id="fone" name="fone"
					value="${user.fone}" class="sign-up-input" placeholder="Telefone" maxlength="10"
					autofocus></td>
			</tr>
			<tr>
				<td><input type="text" id="cep" name="cep"
					value="${user.cep}" class="sign-up-input" placeholder="Cep" maxlength="8" autofocus></td>
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
				<td><input type="number" id="numero" name="numero"
					value="${user.numero}" class="sign-up-input" placeholder="Nº" maxlength="5" autofocus></td>
			</tr>
			<tr>
				<td><input type="text" id="cidade" name="cidade"
					value="${user.cidade}" class="sign-up-input" placeholder="Cidade" autofocus></td>
			</tr>
			<tr>
				<td><input type="text" id="uf" name="uf" class="sign-up-input" maxlength="2"
					value="${user.uf}" placeholder="Uf" autofocus></td>
			</tr>
			<tr>
				<td>Ativo:<input type="checkbox" name="ativo" id="ativo" ${user.ativo ? 'checked': ''}/></td>
			</tr>
			<tr>
				<td>
					<label>Foto:</label>
					<input type="file" name="foto" value="Foto" style="margin-top: 10px; margin-bottom: 15px;">
					<input type="text" style="display: none;" name="fotoUser" readonly="readonly" value="${user.fotoBase64}">
					<input type="text" style="display: none;" name="contenttypeUser" readonly="readonly" value="${user.contentType}">
					<input type="text" style="display: none;" name="fotoMiniaturaUser" readonly="readonly" value="${user.fotoBase64Miniatura}">	
				</td>
			</tr>
			<tr>
				<td>
					<label>Currículo:</label>
					<input type="file" name="curriculo" value="${user.pdfBase64}" style="margin-top: 10px; margin-bottom: 15px;">
					<input type="text" style="display: none;" name="pdfTemp" readonly="readonly" value="${user.pdfBase64}">
					<input type="text" style="display: none;" name="pdfcontenttypeUser" readonly="readonly" value="${user.contentTypePdf}">		
				</td>
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
						<div class="cell">Img</div>
						<div class="cell">Pdf</div>
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
							<c:if test="${user.fotoBase64Miniatura.isEmpty() == false}">
								<a href="salvarUsuario?acao=download&tipo=imagem&user=${user.id}"><img src="<c:out value="${user.fotoBase64Miniatura}"></c:out>" alt="Imagem" title="Imagem" style="width: 20px; height: 20px"></a>
							</c:if>
							<c:if test="${user.fotoBase64Miniatura.isEmpty() == true}">
								<img alt="Imagem" src="resources/imagens/noImg.png" style="width: 20px; height: 20px">
							</c:if>
							</div>
							<div class="cell">
							<c:if test="${user.pdfBase64.isEmpty() == false}">
								<a href="salvarUsuario?acao=download&tipo=pdf&user=${user.id}">
									<img alt="pdf" src="resources/imagens/pdf.png" style="width: 20px; height: 20px">
								</a>
							</c:if>
							<c:if test="${user.pdfBase64.isEmpty() == true}">
								<img alt="pdf" src="resources/imagens/noPdf.png" style="width: 20px; height: 20px">
							</c:if>
								
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
								<a href="salvarUsuario?acao=delete&user=${user.id}" onclick="return confirm('Confirmar a exclusão?');"><img
									alt="Excluir" title="Excluir"
									src="resources/imagens/lixeira.svg"
									style="width: 20px; height: 20px"></a>
							</div>
							<div class="cell">
								<a href="salvarTelefones?acao=addTel&user=${user.id}"><img
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