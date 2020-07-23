package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoTelefone;
import dao.DaoUsuario;
import modelo.BeanCursoJsp;
import modelo.Telefone;

@WebServlet("/salvarTelefones")
public class TelefonesServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUsuario daoUsuario = new DaoUsuario();

	private DaoTelefone daoTelefone = new DaoTelefone();

	public TelefonesServlets() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		try {

			String acao = request.getParameter("acao");
			String user = request.getParameter("user");

			BeanCursoJsp usuario = daoUsuario.consultar(user);

			if (acao.endsWith("addTel")) {

				request.getSession().setAttribute("usuarioEscolhido", usuario);
				request.setAttribute("userEscolhido", usuario);

				RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
				request.setAttribute("telefones", daoTelefone.listTelefone(usuario.getId()));
				view.forward(request, response);
				
			} else if (acao.endsWith("delete")) {

				String tel = request.getParameter("tel");
				daoTelefone.delete(tel);

				RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
				request.setAttribute("telefones", daoTelefone.listTelefone(usuario.getId()));
				request.setAttribute("msg", "Telefone excluido com sucesso!");
				view.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			BeanCursoJsp beanCursoJsp = (BeanCursoJsp) request.getSession().getAttribute("usuarioEscolhido");

			String numero = request.getParameter("numero");
			String tipo = request.getParameter("tipo");

			Telefone telefone = new Telefone();
			telefone.setNumero(numero);
			telefone.setTipo(tipo);
			telefone.setUsuario(beanCursoJsp.getId());

			daoTelefone.postTelefone(telefone);

			request.getSession().setAttribute("usuarioEscolhido", beanCursoJsp);
			request.setAttribute("userEscolhido", beanCursoJsp);

			RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
			request.setAttribute("telefones", daoTelefone.listTelefone(beanCursoJsp.getId()));
			request.setAttribute("msg", "Salvo com sucesso!");
			view.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
