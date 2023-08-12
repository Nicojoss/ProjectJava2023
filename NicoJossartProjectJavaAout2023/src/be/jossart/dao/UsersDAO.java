package be.jossart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import be.jossart.connection.DbConnection;
import be.jossart.pojo.Users;

public class UsersDAO extends DAO<Users>{

	public UsersDAO(Connection conn) {
		super(conn);
	}

	

	@Override
	public boolean create(Users obj) {
		return false;
	}

	@Override
	public boolean delete(Users obj) {
		return false;
	}

	@Override
	public boolean update(Users obj) {
		return false;
	}

	@Override
	public Users find(int id) {
		Users user = null;
	    Connection conn = DbConnection.getInstance();
	    
	    String query = "SELECT * FROM Users WHERE id_user = ?";
	    
	    try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
	        preparedStatement.setInt(1, id);
	        ResultSet result = preparedStatement.executeQuery();
	        
	        if (result.next()) {
	            String username = result.getString("Username");
	            LocalDate dateRegistration = result.getDate("Date_registration").toLocalDate();
	            boolean admin = result.getBoolean("Admin");
	            
	            if (admin) {
	                AdministratorDAO administratorDAO = new AdministratorDAO(conn);
	                user = administratorDAO.find(id);
	            } else {
	                PlayerDAO playerDAO = new PlayerDAO(conn);
	                user = playerDAO.find(id);
	                user.setDateRegistration(dateRegistration);
	                user.setUsername(username);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return user;
	}

	@Override
	public ArrayList<Users> findAll() {
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
	            LocalDate date_registration = result.getDate("Date_registration").toLocalDate();
	            boolean admin = result.getBoolean("Admin");
	            if (admin == true) {
	                AdministratorDAO administratorDAO = new AdministratorDAO(conn);
	                user = administratorDAO.find(id);
	            } else {
	                PlayerDAO playerDAO = new PlayerDAO(conn);
	                user = playerDAO.find(id);
	                user.setDateRegistration(date_registration);
	                user.setUsername(username);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return user;
	}

}
