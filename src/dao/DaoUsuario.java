package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnection;
import modelo.BeanCursoJsp;

public class DaoUsuario {

	private Connection connection;

	public DaoUsuario() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(BeanCursoJsp usuario) {
		try {

			String sql = "INSERT INTO USUARIO(LOGIN,SENHA, NOME, FONE, CEP, RUA, BAIRRO, NUMERO, CIDADE, UF, FOTOBASE64, CONTENTTYPE, PDFBASE64, CONTENTTYPEPDF, FOTOBASE64MINIATURA, ATIVO)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? , ?, ?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, usuario.getLogin());
			insert.setString(2, usuario.getSenha());
			insert.setString(3, usuario.getNome());
			insert.setString(4, usuario.getFone());
			insert.setString(5, usuario.getCep());
			insert.setString(6, usuario.getRua());
			insert.setString(7, usuario.getBairro());
			insert.setString(8, usuario.getNumero());
			insert.setString(9, usuario.getCidade());
			insert.setString(10, usuario.getUf());
			insert.setString(11, usuario.getFotoBase64());
			insert.setString(12, usuario.getContentType());
			insert.setString(13, usuario.getPdfBase64());
			insert.setString(14, usuario.getContentTypePdf());
			insert.setString(15, usuario.getFotoBase64Miniatura());
			insert.setBoolean(16, usuario.getAtivo());
			insert.execute();
			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public List<BeanCursoJsp> listar() throws Exception {
		List<BeanCursoJsp> listar = new ArrayList<BeanCursoJsp>();

		String sql = "select * from usuario where login <> 'admin'";

		PreparedStatement select = connection.prepareStatement(sql);
		ResultSet resultSet = select.executeQuery();

		while (resultSet.next()) {
			BeanCursoJsp beanCursoJsp = new BeanCursoJsp();
			
			beanCursoJsp.setId(resultSet.getLong("id"));
			beanCursoJsp.setLogin(resultSet.getString("login"));
			beanCursoJsp.setSenha(resultSet.getString("senha"));
			beanCursoJsp.setNome(resultSet.getString("nome"));
			beanCursoJsp.setFone(resultSet.getString("fone"));
			beanCursoJsp.setCep(resultSet.getString("cep"));
			beanCursoJsp.setRua(resultSet.getString("rua"));
			beanCursoJsp.setBairro(resultSet.getString("bairro"));
			beanCursoJsp.setNumero(resultSet.getString("numero"));
			beanCursoJsp.setCidade(resultSet.getString("cidade"));
			beanCursoJsp.setUf(resultSet.getString("uf"));
			//beanCursoJsp.setFotoBase64(resultSet.getString("fotobase64"));
			beanCursoJsp.setFotoBase64Miniatura(resultSet.getString("fotobase64miniatura"));
			beanCursoJsp.setContentType(resultSet.getString("contenttype"));
			beanCursoJsp.setPdfBase64(resultSet.getString("pdfbase64"));
			beanCursoJsp.setContentTypePdf(resultSet.getString("contenttypepdf"));
			listar.add(beanCursoJsp);
		}
		return listar;
	}

	public void delete(String id) {
		try {
			String sql = "delete from usuario where id = '" + id + "' and login <> 'admin'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();

			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public BeanCursoJsp consultar(String id) throws Exception {
		String sql = "select * from usuario where id='" + id + "' and login <> 'admin'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			BeanCursoJsp beanCursoJsp = new BeanCursoJsp();
			beanCursoJsp.setId(resultSet.getLong("id"));
			beanCursoJsp.setLogin(resultSet.getString("login"));
			beanCursoJsp.setSenha(resultSet.getString("senha"));
			beanCursoJsp.setNome(resultSet.getString("nome"));
			beanCursoJsp.setFone(resultSet.getString("fone"));
			beanCursoJsp.setCep(resultSet.getString("cep"));
			beanCursoJsp.setRua(resultSet.getString("rua"));
			beanCursoJsp.setBairro(resultSet.getString("bairro"));
			beanCursoJsp.setNumero(resultSet.getString("numero"));
			beanCursoJsp.setCidade(resultSet.getString("cidade"));
			beanCursoJsp.setUf(resultSet.getString("uf"));
			beanCursoJsp.setFotoBase64(resultSet.getString("fotobase64"));
			beanCursoJsp.setContentType(resultSet.getString("contenttype"));
			beanCursoJsp.setPdfBase64(resultSet.getString("pdfbase64"));
			beanCursoJsp.setContentTypePdf(resultSet.getString("contenttypepdf"));
			beanCursoJsp.setFotoBase64Miniatura(resultSet.getString("fotobase64miniatura"));
			beanCursoJsp.setAtivo(resultSet.getBoolean("ativo"));
			
			return beanCursoJsp;
		}

		return null;
	}
	
	public boolean validarLogin(String login) throws Exception {
		String sql = "select count(1) as qtd from usuario where login='" + login + "'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {

			return resultSet.getInt("qtd") <= 0;/* Retorna true*/
		}

		return false;
	}
	
	public boolean validarSenha(String senha) throws Exception {
		String sql = "select count(1) as qtd from usuario where senha='" + senha + "'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {

			return resultSet.getInt("qtd") <= 0;/* Retorna true*/
		}

		return false;
	}


	public void atualizar(BeanCursoJsp usuario) {

		try {
			String sql = "update usuario set login = ?, senha = ?,"
					+ " nome = ?, fone = ?, cep = ?, rua = ?,"
					+ " bairro = ?, numero = ?, cidade = ?,"
					+ " uf = ?, fotobase64 = ?, contenttype = ?,"
					+ " pdfbase64 = ?, contenttypepdf = ?, fotobase64miniatura = ?, ativo = ? where id = " + usuario.getId();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, usuario.getLogin());
			preparedStatement.setString(2, usuario.getSenha());
			preparedStatement.setString(3, usuario.getNome());
			preparedStatement.setString(4, usuario.getFone());
			preparedStatement.setString(5, usuario.getCep());
			preparedStatement.setString(6, usuario.getRua());
			preparedStatement.setString(7, usuario.getBairro());
			preparedStatement.setString(8, usuario.getNumero());
			preparedStatement.setString(9, usuario.getCidade());
			preparedStatement.setString(10, usuario.getUf());
			preparedStatement.setString(11, usuario.getFotoBase64());
			preparedStatement.setString(12, usuario.getContentType());
			preparedStatement.setString(13, usuario.getPdfBase64());
			preparedStatement.setString(14, usuario.getContentTypePdf());
			preparedStatement.setString(15, usuario.getFotoBase64Miniatura());
			preparedStatement.setBoolean(16, usuario.getAtivo());
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}	
		}
	}
}
