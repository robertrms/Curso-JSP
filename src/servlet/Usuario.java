package servlet;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.codec.binary.Base64;

import dao.DaoUsuario;
import modelo.BeanCursoJsp;

@WebServlet("/salvarUsuario")
@MultipartConfig
public class Usuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DaoUsuario daoUsuario = new DaoUsuario();

	public Usuario() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
			} else if (acao != null && acao.equalsIgnoreCase("download")) {

				BeanCursoJsp usuario = new DaoUsuario().consultar(user);

				if (usuario != null) {

					String contentType = "";
					byte[] fileBytes = null;

					String tipo = request.getParameter("tipo");

					if (tipo.equalsIgnoreCase("imagem")) {
						contentType = usuario.getContentType();
						fileBytes = new Base64().decodeBase64(usuario.getFotoBase64());

					} else if (tipo.equalsIgnoreCase("pdf")) {
						contentType = usuario.getContentTypePdf();
						fileBytes = new Base64().decodeBase64(usuario.getPdfBase64());

					}
					response.setHeader("Content-Disposition",
							"attachment;filename=arquivo." + contentType.split("\\/")[1]);

					InputStream is = new ByteArrayInputStream(fileBytes);

					/* Inicio da resposta para o navegador */
					int read = 0;
					byte[] bytes = new byte[1024];
					OutputStream os = response.getOutputStream();

					while ((read = is.read(bytes)) != -1) {
						os.write(bytes, 0, read);
					}

					os.flush();
					os.close();

				}
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
			
			if(request.getParameter("ativo") != null && request.getParameter("ativo").equalsIgnoreCase("on")) {
				usuario.setAtivo(true);
			}else {
				usuario.setAtivo(false);
			}

			try {

				/* Inicio File upload de imagens e pdf */
				if (ServletFileUpload.isMultipartContent(request)) {

					// FOTO
					Part imagemFoto = request.getPart("foto");

					if (imagemFoto != null && imagemFoto.getInputStream().available() > 0) {
						
						String fotoBase64 = new Base64().encodeBase64String(converteStremParabyte(imagemFoto.getInputStream()));

						usuario.setFotoBase64(fotoBase64);
						usuario.setContentType(imagemFoto.getContentType());
						
						/*Inicio miniatura imagem*/
							
						/*Transforma em um bufferedImage*/
						byte[] imageByteDecode = new Base64().decodeBase64(fotoBase64);
						BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageByteDecode));
						
						/*Pegar o tipo da imagem*/
						int type = bufferedImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : bufferedImage.getType();
						
						/*Cria imagem em miniatura*/
						BufferedImage resizedImage = new BufferedImage(100, 100, type);
						Graphics2D g = resizedImage.createGraphics();
						g.drawImage(bufferedImage, 0, 0, 100, 100, null);
						g.dispose();
						
						/*Escrever imagem novamente*/
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						ImageIO.write(resizedImage, "png", baos);
						
						String miniaturaBase64 = "data:image/png;base64," + DatatypeConverter.printBase64Binary(baos.toByteArray());
		
						usuario.setFotoBase64Miniatura(miniaturaBase64);
						/*Fim miniatura imagem*/
					} else {
						usuario.setFotoBase64(request.getParameter("fotoUser"));
						usuario.setContentType(request.getParameter("contenttypeUser"));
						usuario.setFotoBase64Miniatura(request.getParameter("fotoMiniaturaUser"));
					}

					// PDF
					Part pdf = request.getPart("curriculo");

					if (pdf != null && pdf.getInputStream().available() > 0) {
						String pdfBase64 = new Base64().encodeBase64String(converteStremParabyte(pdf.getInputStream()));

						usuario.setPdfBase64(pdfBase64);
						usuario.setContentTypePdf(pdf.getContentType());
					} else {
						usuario.setPdfBase64(request.getParameter("pdfTemp"));
						usuario.setContentTypePdf(request.getParameter("pdfcontenttypeUser"));
					}

				}

				/* Fim File upload de imagens e pdf */

				if (id == null || id.isEmpty() && !daoUsuario.validarLogin(login) && !daoUsuario.validarSenha(senha)) {
					request.setAttribute("msg", "Já existe um usuário com este login e senha!");
					request.setAttribute("user", usuario);// retornando os valores que foram enserido.
				} else if (id == null || id.isEmpty() && !daoUsuario.validarLogin(login)) {
					request.setAttribute("msg", "Já existe um usuário com este login!");
					request.setAttribute("user", usuario);// retornando os valores que foram enserido.
				} else if (id == null || id.isEmpty() && !daoUsuario.validarSenha(senha)) {
					request.setAttribute("msg", "Já existe um usuário com essa senha!");
					request.setAttribute("user", usuario);// retornando os valores que foram enserido.
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

	/* Converte a entrada de fluxo de dados da imagem e pdf para byte[] */
	private byte[] converteStremParabyte(InputStream arquivo) throws Exception {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int reads = arquivo.read();
		while (reads != -1) {
			baos.write(reads);
			reads = arquivo.read();
		}

		return baos.toByteArray();
	}
}
