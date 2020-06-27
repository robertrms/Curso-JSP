package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoUsuario;
import modelo.BeanCursoJsp;

@WebServlet("/salvarUsuario")
public class Usuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DaoUsuario daoUsuario = new DaoUsuario();

	public Usuario() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

		try {
			String acao = request.getParameter("acao");
			String user = request.getParameter("user");

			if (acao != null && acao.equalsIgnoreCase("delete")) {
				daoUsuario.delete(user);

				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			} else if (acao != null && acao.equalsIgnoreCase("editar")) {

				BeanCursoJsp beanCursoJsp = daoUsuario.consultar(user);

				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("user", beanCursoJsp);
				view.forward(request, response);
			} else if (acao != null && acao.equalsIgnoreCase("listartodos")) {

				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("reset")) {
			try {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

			String id = request.getParameter("id").replaceAll(" ", "");
			String login = request.getParameter("login").replaceAll(" ", "");
			String senha = request.getParameter("senha").replaceAll(" ", "");
			String nome = request.getParameter("nome");
			String fone = request.getParameter("fone").replaceAll(" ", "");
			String cep = request.getParameter("cep").replaceAll(" ", "");
			String rua = request.getParameter("rua").replaceAll(" ", " ");
			String bairro = request.getParameter("bairro").replaceAll(" ", " ");
			String numero = request.getParameter("numero").replaceAll(" ", " ");
			String cidade = request.getParameter("cidade").replaceAll(" ", " ");
			String uf = request.getParameter("uf").replaceAll(" ", " ");

			BeanCursoJsp usuario = new BeanCursoJsp();
			usuario.setId(!id.isEmpty() ? Long.parseLong(id) : null);
			usuario.setLogin(login);
			usuario.setSenha(senha);
			usuario.setNome(nome);
			usuario.setFone(fone);
			usuario.setCep(cep);
			usuario.setRua(rua);
			usuario.setBairro(bairro);
			usuario.setNumero(numero);
			usuario.setCidade(cidade);
			usuario.setUf(uf);

			try {

				if (id == null || id.isEmpty() && !daoUsuario.validarLogin(login) && !daoUsuario.validarSenha(senha)) {
					request.setAttribute("msg", "Já existe um usuário com este login e senha!");
					request.setAttribute("user", usuario);//retornando os valores que foram enserido.
				} else if (id == null || id.isEmpty() && !daoUsuario.validarLogin(login)) {
					request.setAttribute("msg", "Já existe um usuário com este login!");
					request.setAttribute("user", usuario);//retornando os valores que foram enserido.
				} else if (id == null || id.isEmpty() && !daoUsuario.validarSenha(senha)) {
					request.setAttribute("msg", "Já existe um usuário com essa senha!");
					request.setAttribute("user", usuario);//retornando os valores que foram enserido.
				}

				if (id == null || id.isEmpty() && daoUsuario.validarLogin(login) && daoUsuario.validarSenha(senha)) {
					daoUsuario.salvar(usuario);
				}

				if (id != null || !id.isEmpty() && daoUsuario.validarLogin(login) && daoUsuario.validarSenha(senha)) {
					daoUsuario.atualizar(usuario);
				}

				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
