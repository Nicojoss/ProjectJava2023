package be.jossart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.jossart.connection.DbConnection;
import be.jossart.pojo.VideoGame;

public class VideoGameDAO extends DAO<VideoGame> {

	public VideoGameDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(VideoGame obj) {
	    boolean success = false;
		
		String query = "INSERT INTO VideoGame (Id_videogame, Name_videogame, Credit_cost, Console) " +
	                   "VALUES (?, ?, ?, ?)";

	    try (PreparedStatement stmt = this.connect.prepareStatement(query)) {
	        stmt.setInt(1, obj.getId_videogame());
	        stmt.setString(2, obj.getName());
	        stmt.setInt(3, obj.getCreditCost());
	        stmt.setString(4, obj.getConsole());

	        stmt.executeUpdate();
	        
	        success = true;
	        return success;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return success;
	}

	@Override
	public boolean delete(VideoGame obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(VideoGame obj) {
		boolean success = false;
	    Connection conn = DbConnection.getInstance();
	    String query = "UPDATE VideoGame SET Credit_cost = ? WHERE Id_videogame = ?";
	    
	    try (PreparedStatement stmt = conn.prepareStatement(query)) {
	        stmt.setInt(1, obj.getCreditCost());
	        stmt.setInt(2, obj.getId_videogame());
	        
	        stmt.executeUpdate();
	        success = true;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return success;
	    }
	    
	    return success;
	}

	@Override
	public VideoGame find(int id) {
		VideoGame videoGame = null;
		String query = "SELECT * FROM VideoGame WHERE Id_videogame = ?";
	    
	    try (PreparedStatement preparedStatement = connect.prepareStatement(query)) {
	        preparedStatement.setInt(1, id);
	        ResultSet result = preparedStatement.executeQuery();
	        
	        if (result.next()) {
	            int Id_game = result.getInt("id_videogame");
	            String NameGame = result.getString("Name_videogame");
	            String Console = result.getString("Console");
	            int creditCost = result.getInt("Credit_cost");
	            
	            videoGame = new VideoGame(Id_game, NameGame, Console, creditCost);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return videoGame;
	}

	@Override
	public ArrayList<VideoGame> findAll() {
		ArrayList<VideoGame> videoGameList = new ArrayList<VideoGame>();
		
		String query = "SELECT Id_videogame, Name_videogame, Console, Credit_cost FROM VideoGame";
        
		try {
            try (PreparedStatement stmt = this.connect.prepareStatement(query);
                 ResultSet resultSet = stmt.executeQuery()) {

                while (resultSet.next()) {
                	int id = resultSet.getInt("Id_videogame");
                    String name = resultSet.getString("Name_videogame");
                    String console = resultSet.getString("Console");
                    int creditCost = resultSet.getInt("Credit_cost");

                    VideoGame game = new VideoGame(id, name, console, creditCost);
                    videoGameList.add(game);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return videoGameList;
	}
}
