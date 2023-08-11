package be.jossart.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.jossart.connection.DbConnection;
import be.jossart.pojo.Users;

public class UsersDAO extends DAO<Users>{

	public UsersDAO(Connection conn) {
		super(conn);
	}

	

	@Override
	public boolean create(Users obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Users obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Users obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Users find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Users> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	//Methodes
	public static Users login(String username, String password) throws Exception {
		Users user = null;
		Connection conn = DbConnection.getInstance();
		
		String query = "SELECT * FROM Users WHERE Username='" + username + "' AND Password='" + password + "'";
		try {
			ResultSet result = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery(query);
			if (result.first()) {
				int id = result.getInt("id_user");
				boolean admin = result.getBoolean("Admin");
				if (admin == true) {
					AdministratorDAO administratorDAO = new AdministratorDAO(conn);
					user = administratorDAO.find(id);
				} else {
					PlayerDAO playerDAO = new PlayerDAO(conn);
					user = playerDAO.find(id);
				}
			} else {
				throw new Exception("Incorrect username or password !");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

}
