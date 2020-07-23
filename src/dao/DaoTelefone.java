package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnection;
import modelo.Telefone;

public class DaoTelefone {

	private Connection connection;

	public DaoTelefone() {
		connection = SingleConnection.getConnection();
	}

	public void postTelefone(Telefone telefone) {

		try {
			String sql = "insert into telefone(numero, tipo, usuario) values (?,?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);

			insert.setString(1, telefone.getNumero());
			insert.setString(2, telefone.getTipo());
			insert.setLong(3, telefone.getUsuario());
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

	public List<Telefone> listTelefone(Long usuario) throws Exception {
		List<Telefone> listTelefone = new ArrayList<Telefone>();

		String sql = "select * from telefone where usuario = " + usuario;

		PreparedStatement select = connection.prepareStatement(sql);
		ResultSet resultSet = select.executeQuery();

		while (resultSet.next()) {
			Telefone telefone = new Telefone();

			telefone.setId(resultSet.getLong("id"));
			telefone.setNumero(resultSet.getString("numero"));
			telefone.setTipo(resultSet.getString("tipo"));
			telefone.setUsuario(resultSet.getLong("usuario"));

			listTelefone.add(telefone);
		}
		return listTelefone;
	}

	public void delete(String id) {

		try {
			String sql = "delete from telefone where id = " + id;
			PreparedStatement delete = connection.prepareStatement(sql);
			delete.execute();

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
}
