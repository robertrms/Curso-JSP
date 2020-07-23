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

	<a href="salvarUsuario?acao=listartodos" id="botaoVoltar"
		style="margin-left: 15px;">Voltar</a>
	<a href="index.jsp" id="botaoSair" style="margin-left: 10px;">Sair</a>

	<h2 style="color: red; margin-left: 560px;">${msg}</h2>
	<form action="salvarTelefones" method="post" class="sign-up" onsubmit="return validarCampos() ? true : false;"
		id="formUser">
		<table>
			<tr>
				<td>
					<input type="text" readonly="readonly" id="id" name="id"
					value="${usuarioEscolhido.id}" class="sign-up-input" placeholder="Id" autofocus>
				</td>
			</tr>
			<tr>
				<td>
					<input type="text" readonly="readonly" id="nome" name="nome"
					value="${usuarioEscolhido.nome}" class="sign-up-input" placeholder="Nome" autofocus>
				</td>
			</tr>
			<tr>
				<td>
					<input type="text"	 id="numero" name="numero"
					 value="${fone.numero}" class="sign-up-input" placeholder="Numero" autofocus>
				</td>
			</tr>
			<tr>
				<td>
					<select id="tipo" name="tipo" style="margin-bottom: 20px;">
					 	<option>Casa</option>
					 	<option>Contato</option>
					 	<option>Celular</option>
					 </select>
				</td>
			</tr>
		</table>
		<input type="submit" value="Salvar" class="sign-up-button">
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
								<a href="salvarTelefones?acao=delete&user=${usuarioEscolhido.id}&tel=${fone.id}" onclick="return confirm('Confirmar a exclusão?');"><img
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
	<script type="text/javascript">
		function validarCampos() {
			if (document.getElementById("nome").value == '') {
				alert("Informe o Nome");
				return false;
			}else if (document.getElementById("tipo").value == '') {
						alert("Informe o Tipo");
						return false;
			}
			return true;
		}
	</script>
</body>
</html>