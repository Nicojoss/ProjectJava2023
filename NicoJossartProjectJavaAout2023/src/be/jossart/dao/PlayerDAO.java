package be.jossart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
		String query3 = "INSERT INTO Player (id_user,Credit,Pseudo) VALUES (?,?,?)";
		
		try {
			PreparedStatement stmt = (PreparedStatement)this.connect.prepareStatement(query);
			
			stmt.setString(1, obj.getUsername());
			stmt.setString(2, obj.getPassword());
			
			LocalDate localDate = obj.getDateRegistration();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String formattedDate = localDate.format(formatter);
			
			stmt.setString(3, formattedDate);
			
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
				Users player = new Player(id, pseudo, credit);
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
}
