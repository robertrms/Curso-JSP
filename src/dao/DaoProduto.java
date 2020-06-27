package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnection;
import modelo.Produto;

public class DaoProduto {

	private Connection connection;

	public DaoProduto() {
		connection = SingleConnection.getConnection();
	}

	public void postProduto(Produto produto) {

		try {
			String sql = "insert into produtos(nome,quantidade,valor) values(?,?,?)";

			PreparedStatement insert = connection.prepareStatement(sql);

			insert.setString(1, produto.getNome());
			insert.setString(2, produto.getQuantidade());
			insert.setString(3, produto.getValor());
			insert.execute();
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

	public List<Produto> listProduto() throws Exception {
		List<Produto> produtos = new ArrayList<Produto>();

		String sql = "select * from produtos";

		PreparedStatement select = connection.prepareStatement(sql);
		ResultSet resultSet = select.executeQuery();

		while (resultSet.next()) {
			Produto produto = new Produto();

			produto.setId(resultSet.getLong("id"));
			produto.setNome(resultSet.getString("nome"));
			produto.setQuantidade(resultSet.getString("quantidade"));
			produto.setValor(resultSet.getString("valor"));

			produtos.add(produto);
		}
		return produtos;
	}

	public void delete(String id) {

		try {
			String sql = "delete from produtos where id = " + id;
			PreparedStatement delete = connection.prepareStatement(sql);
			delete.execute();
			
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public Produto consultar(String id) throws Exception {
		String sql = "select * from produtos where id = " + id;
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			Produto produtos = new Produto();
			produtos.setId(resultSet.getLong("id"));
			produtos.setNome(resultSet.getString("nome"));
			produtos.setQuantidade(resultSet.getString("quantidade"));
			produtos.setValor(resultSet.getString("valor"));

			return produtos;
		}

		return null;
	}
	
	public void atualizar(Produto produto) {
		
		try {
		String sql = "update produtos set nome = ?, quantidade = ?, valor = ? where id = " + produto.getId();
		PreparedStatement update = connection.prepareStatement(sql);
		update.setString(1, produto.getNome());
		update.setString(2, produto.getQuantidade());
		update.setString(3, produto.getValor());
		update.executeUpdate();
		connection.commit();
		}catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public boolean validarNomeProduto(String nome) throws Exception {
		String sql = "select count(1) as qtd from produtos where nome = '" + nome + "'";
		PreparedStatement select = connection.prepareStatement(sql);
		ResultSet resultSet = select.executeQuery();

		if (resultSet.next()) {

			return resultSet.getInt("qtd") <= 0;/* Retorna true*/
		}

		return false;
	}
	
}
