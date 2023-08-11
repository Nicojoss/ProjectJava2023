package be.jossart.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.jossart.pojo.Administrator;

public class AdministratorDAO extends DAO<Administrator> {

	public AdministratorDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Administrator obj) {
		return false;
	}

	@Override
	public boolean delete(Administrator obj) {
		return false;
	}

	@Override
	public boolean update(Administrator obj) {
		return false;
	}

	@Override
	public Administrator find(int id) {
Administrator administrator = null;
		
		String query = "SELECT * FROM Users WHERE id_user ='" + id + "'";

		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery(query);
			if (result.first()) {
				administrator = new Administrator();
				administrator.setIdUser(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return administrator;
	}

	@Override
	public ArrayList<Administrator> findAll() {
		return null;
	}

}
