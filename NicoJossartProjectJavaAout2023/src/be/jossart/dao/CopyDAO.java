package be.jossart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.jossart.connection.DbConnection;
import be.jossart.pojo.Copy;
import be.jossart.pojo.Player;
import be.jossart.pojo.VideoGame;

public class CopyDAO extends DAO<Copy> {

	public CopyDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Copy obj) {
		boolean success = false;
		String query = "INSERT INTO Copy (Id_videogame, id_user, Available) VALUES (?, ?, ?)";

	    try (PreparedStatement stmt = this.connect.prepareStatement(query)) {
	        stmt.setInt(1, obj.getVideoGame().getId_videogame());
	        stmt.setInt(2, obj.getOwner().getIdUser());
	        stmt.setBoolean(3, true);

	        int result = stmt.executeUpdate();
	        
	        success = result > 0;
	        stmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return success;
	}

	@Override
	public boolean delete(Copy obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Copy obj) {
		boolean success = false;
	    Connection conn = DbConnection.getInstance();
	    String query = "UPDATE Copy SET Available = ? WHERE Id_copy = ?";
	    
	    try (PreparedStatement stmt = conn.prepareStatement(query)) {
	        stmt.setBoolean(1, obj.getAvailable());
	        stmt.setInt(2, obj.getIdCopy());
	        
	        int result = stmt.executeUpdate();
	        success = result > 0;
	        stmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return success;
	    }
	    
	    return success;
	}

	@Override
	public Copy find(int id) {
		Copy copy = null;
		String query = "SELECT * FROM Copy WHERE Id_copy = ?";
	    
	    try (PreparedStatement preparedStatement = connect.prepareStatement(query)) {
	        preparedStatement.setInt(1, id);
	        ResultSet result = preparedStatement.executeQuery();
	        
	        if (result.next()) {
	            int copyId = result.getInt("id_copy");
	            int id_game = result.getInt("Id_videogame");
	            int id_owner = result.getInt("id_user");
	            
	            VideoGame game = VideoGame.findById(id_game);
	            Player copyOwner = Player.findById(id_owner);
	            
	            copy = new Copy(copyId, game, copyOwner);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return copy;
	}

	@Override
	public ArrayList<Copy> findAll() {
		ArrayList<Copy> CopyList = new ArrayList<Copy>();
		
		String query = "SELECT * FROM Copy "
				+ "Inner Join Player On Copy.id_user = Player.id_user "
				+ "Inner Join VideoGame On Copy.Id_videogame = VideoGame.Id_videogame";
        
		try {
            
            try (PreparedStatement stmt = this.connect.prepareStatement(query);
                 ResultSet resultSet = stmt.executeQuery()) {

                while (resultSet.next()) {
                	//COPY
                	int idCopy = resultSet.getInt("Id_copy");
                	boolean available = resultSet.getBoolean("Available");
                	
                	//VIDEOGAME
                	int idVideogame = resultSet.getInt("Id_videogame");
                	String nameVideoGame = resultSet.getString("VideoGame.Name_videogame");
                	int creditCost = resultSet.getInt("VideoGame.Credit_cost");
                	String console = resultSet.getString("VideoGame.Console");
                	VideoGame game = new VideoGame(idVideogame, nameVideoGame, creditCost, console);
                	
                	//player
                	int idPlayer = resultSet.getInt("id_user");
                	int credit = resultSet.getInt("Player.Credit");
                	String pseudoPlayer = resultSet.getString("Player.Pseudo");
                	Player player = new Player(idPlayer, pseudoPlayer, credit);
                	
                    Copy copy = new Copy(idCopy, available, player, game);
                    CopyList.add(copy);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return CopyList;
	}

	// Methodes
	public static boolean UpdateAvailable(Copy selectedCopy) {
		
		boolean success = false;
		Connection connect = DbConnection.getInstance();
		
	    String query = "UPDATE Copy SET Available = ? WHERE id_copy = ?";

	    try (PreparedStatement stmt = connect.prepareStatement(query)){
	    	stmt.setBoolean(1, selectedCopy.getAvailable());
	    	stmt.setInt(2, selectedCopy.getIdCopy());

	        int result = stmt.executeUpdate();
	        success = result > 0;
	        stmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    
	    return success;
	    }
		
	    return success;
	}
}
