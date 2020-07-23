package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoProduto;
import modelo.Produto;

@WebServlet("/salvarProduto")
public class ProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoProduto daoProduto = new DaoProduto();

	public ProdutoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

		try {
			String acao = request.getParameter("acao");
			String pdt = request.getParameter("pdt");

			if (acao != null && acao.equalsIgnoreCase("delete")) {
				daoProduto.delete(pdt);

				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtos", daoProduto.listProduto());
				view.forward(request, response);
			} else if (acao != null && acao.equalsIgnoreCase("editar")) {

				Produto produto = daoProduto.consultar(pdt);

				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("pdt", produto);
				view.forward(request, response);
			} else if (acao != null && acao.equalsIgnoreCase("listartodos")) {

				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtos", daoProduto.listProduto());
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
				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtos", daoProduto.listProduto());
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String quantidade = request.getParameter("quantidade");
			String valor = request.getParameter("valor");

			if (nome != null && quantidade != null && valor != null && !nome.isEmpty() && !quantidade.isEmpty() && !valor.isEmpty()) {
				Produto produto = new Produto();
				produto.setNome(nome);
				produto.setQuantidade(quantidade);
				produto.setValor(valor);
				
				try {
					if (id == null || id.isEmpty() && !daoProduto.validarNomeProduto(nome)) {
						request.setAttribute("msg", "Já existe um produto cadastrado com esse nome!");
						request.setAttribute("pdt", produto);// retornando os valores que foram enserido.
					} else if (id == null || id.isEmpty() && daoProduto.validarNomeProduto(nome)) {
						daoProduto.postProduto(produto);
					} else {
						produto.setId(!id.isEmpty() ? Long.parseLong(id) : null);
						daoProduto.atualizar(produto);
					}

					RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
					request.setAttribute("produtos", daoProduto.listProduto());
					view.forward(request, response);

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {

				try {
					RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
					request.setAttribute("produtos", daoProduto.listProduto());
					view.forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
	}

}
