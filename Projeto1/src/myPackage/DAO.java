package myPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DAO {
	private Connection connection = null;

	public DAO() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost/meusdados", "root", "Insper119960");
	}

	public boolean verifica(Cadastro pessoa) throws SQLException {
		boolean st = false;
		String sql = "SELECT * FROM Login WHERE user=? and password=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, pessoa.getUser());
		stmt.setString(2, pessoa.getPassword());
		ResultSet rs = stmt.executeQuery();
		st = rs.next();
		stmt.execute();
		stmt.close();
		return st;

	}

	public List<Tabela> getLista(String usuario) throws SQLException {

		List<Tabela> pessoas = new ArrayList<Tabela>();
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM crud WHERE user = ?");
		stmt.setString(1, usuario);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Tabela pessoa = new Tabela();
			pessoa.setId(rs.getInt("id"));
			Calendar data = Calendar.getInstance();
			data.setTime(rs.getDate("data"));
			pessoa.setData(data);
			pessoa.setTarefa(rs.getString("tarefa"));
			pessoa.setImportancia(rs.getInt("importancia"));
			pessoa.setCategoria(rs.getString("categoria"));
			pessoas.add(pessoa);
		}
		rs.close();
		stmt.close();
		return pessoas;
	}

	public void adiciona(Cadastro pessoa) throws SQLException {
		String sql = "INSERT INTO Login" + "(user, password) values (?,?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, pessoa.getUser());
		stmt.setString(2, pessoa.getPassword());
		stmt.execute();
		stmt.close();
	}

	public void setTarefa(Tabela tabela) throws SQLException {
		String sql = " INSERT INTO crud (user, tarefa, data,importancia,categoria)\r\n" + "VALUES (?,?,?,?,?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, tabela.getUser());
		stmt.setString(2, tabela.getTarefa());
		stmt.setDate(3, new java.sql.Date(tabela.getData().getTimeInMillis()));
		stmt.setInt(4, tabela.getImportancia());
		stmt.setString(5, tabela.getCategoria());
		stmt.execute();
		stmt.close();
	}

	public void deleteTarefa(String id) throws SQLException {
		String sql = " DELETE FROM crud WHERE id=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, id);
		stmt.execute();
		stmt.close();
	}

	public void editTarefa(Tabela tabela) throws SQLException {
		String sql = " UPDATE crud SET user=?,tarefa=?,data=?,importancia=?,categoria=? WHERE id=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, tabela.getUser());
		stmt.setString(2, tabela.getTarefa());
		stmt.setDate(3, new java.sql.Date(tabela.getData().getTimeInMillis()));
		stmt.setInt(4, tabela.getImportancia());
		stmt.setString(5, tabela.getCategoria());
		stmt.setInt(6, tabela.getId());

		stmt.execute();
		stmt.close();
	}

	public String getUser(Cadastro pessoa) throws SQLException {
		String sql = "SELECT name FROM Cadastro WHERE user=? and password=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, pessoa.getUser());
		stmt.setString(2, pessoa.getPassword());

		ResultSet rs = stmt.executeQuery();
		String name = null;

		if (rs.next()) {
			name = rs.getString("name");
		}

		stmt.execute();
		stmt.close();
		return name;
	}

	public List<Tabela> doFiltro(String usuario, Integer filtro) throws SQLException {

		List<Tabela> pessoas = new ArrayList<Tabela>();
		if (filtro == 1) {
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM crud WHERE user=? ORDER BY data");
			stmt.setString(1, usuario);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Tabela pessoa = new Tabela();
				pessoa.setId(rs.getInt("id"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data"));
				pessoa.setData(data);
				pessoa.setTarefa(rs.getString("tarefa"));
				pessoa.setImportancia(rs.getInt("importancia"));
				pessoa.setCategoria(rs.getString("categoria"));
				pessoas.add(pessoa);
				
			}
			rs.close();
			stmt.close();
		} else{
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM crud WHERE user =? ORDER BY importancia");
			stmt.setString(1, usuario);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Tabela pessoa = new Tabela();
				pessoa.setId(rs.getInt("id"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data"));
				pessoa.setData(data);
				pessoa.setTarefa(rs.getString("tarefa"));
				pessoa.setImportancia(rs.getInt("importancia"));
				pessoa.setCategoria(rs.getString("categoria"));
				pessoas.add(pessoa);
				
			}
			rs.close();
			stmt.close();
		}
		
		return pessoas;
		
		
		
	}

	public void close() throws SQLException {
		connection.close();
	}
}