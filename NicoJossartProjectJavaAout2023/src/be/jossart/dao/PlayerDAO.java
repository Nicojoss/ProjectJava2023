package be.jossart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import be.jossart.connection.DbConnection;
import be.jossart.pojo.Player;
import be.jossart.pojo.Users;

public class PlayerDAO extends DAO<Player>{

	public PlayerDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Player obj) {
		
		boolean success = false;
		int id = 0;
		
		String query = "INSERT INTO Users (Username,Password,Date_registration) VALUES (?,?,?)";
		String query2 = "SELECT id_user FROM Users WHERE Username = ?";
		String query3 = "INSERT INTO Player (id_user,Credit,Pseudo, LastBirthdayDate) VALUES (?,?,?,?)";
		
		try {
			PreparedStatement stmt = (PreparedStatement)this.connect.prepareStatement(query);
			
			stmt.setString(1, obj.getUsername());
			stmt.setString(2, obj.getPassword());
			stmt.setDate(3, java.sql.Date.valueOf(obj.getDateRegistration()));
			
			stmt.execute();
			stmt.close();
			
			PreparedStatement stmt2 = this.connect.prepareStatement(query2);
			stmt2.setString(1, obj.getUsername());

			ResultSet resultSet = stmt2.executeQuery();
		    
		    if (resultSet.next()) {
		        id = resultSet.getInt("id_user");
		    }
			stmt2.close();
			
			PreparedStatement stmt3 = this.connect.prepareStatement(query3);
	        stmt3.setInt(1, id);
	        stmt3.setInt(2, 10);
	        stmt3.setString(3, obj.getPseudo());
	        stmt3.setDate(4, java.sql.Date.valueOf(obj.getDateRegistration()));
	        
	        stmt3.execute();
	        stmt3.close();
	        
			success = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public boolean delete(Player obj) {
		return false;
	}

	@Override
	public boolean update(Player obj) {
		return false;
	}

	@Override
	public Player find(int id) {
		
		String query = "SELECT * FROM Player WHERE Id_user = '" + id + "'";
		
		try {
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(query);
			if(result.first()){
				String pseudo = result.getString("Pseudo");
				int credit = result.getInt("Credit");
				LocalDate lastBirthdayDate = result.getDate("LastBirthdayDate").toLocalDate();
				Users player = new Player(id, pseudo, credit, lastBirthdayDate);
				return (Player) player;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	@Override
	public ArrayList<Player> findAll() {
		return null;
	}

	public static boolean UpdateCredit(Player player) {
		boolean success = false;
		Connection connect = DbConnection.getInstance();

        String query = "UPDATE Player SET Credit = ? WHERE id_user = ?";

        try (PreparedStatement stmt = connect.prepareStatement(query)) {
            stmt.setInt(1, player.getCredit());
            stmt.setInt(2, player.getIdUser());

            int result = stmt.executeUpdate();
            success = result > 0;
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
	}

	public static boolean UpdateLastBirthdayDate(Player obj) {
		boolean success = false;
		Connection connect = DbConnection.getInstance();
		
	    String query = "UPDATE Player SET LastBirthdayDate = ? WHERE id_user = ?";

	    try (PreparedStatement preparedStatement = connect.prepareStatement(query)) {
	    	preparedStatement.setDate(1, java.sql.Date.valueOf(obj.getLastBirthdayDate()));
	    	preparedStatement.setInt(2, obj.getIdUser());
	         
	        
	        int result = preparedStatement.executeUpdate();
	        success = result > 0;
	        preparedStatement.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return success;
	}
}
