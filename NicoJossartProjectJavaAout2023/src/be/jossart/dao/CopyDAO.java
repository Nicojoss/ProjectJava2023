package be.jossart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import be.jossart.pojo.Copy;

public class CopyDAO extends DAO<Copy> {

	public CopyDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Copy obj) {
		boolean success = false;
		String query = "INSERT INTO Copy (Id_videogame, id_user) VALUES (?, ?)";

	    try (PreparedStatement stmt = this.connect.prepareStatement(query)) {
	        stmt.setInt(1, obj.getVideoGame().getId_videogame());
	        stmt.setInt(2, obj.getOwner().getIdUser());

	        stmt.executeUpdate();
	        
	        success = true;
	        return success;
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Copy find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Copy> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
