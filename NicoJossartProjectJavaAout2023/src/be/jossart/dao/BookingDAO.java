package be.jossart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import be.jossart.connection.DbConnection;
import be.jossart.pojo.Booking;
import be.jossart.pojo.VideoGame;

public class BookingDAO extends DAO<Booking> {

	public BookingDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Booking obj) {
	    boolean success = false;
		Connection conn = DbConnection.getInstance();
	    String query = "INSERT INTO Booking (Date_booking, id_user, Id_videogame, NbrWeeksRent) VALUES (?, ?, ?, ?)";
	    
	    try {
	        PreparedStatement stmt = conn.prepareStatement(query);
	        stmt.setDate(1, java.sql.Date.valueOf(obj.getBookingDate()));
	        stmt.setInt(2, obj.getPlayer().getIdUser());
	        stmt.setInt(3, obj.getGame().getId_videogame());
	        stmt.setInt(4, obj.getNbrWeeksRent());
	        
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
	public boolean delete(Booking obj) {
		return false;
	}

	@Override
	public boolean update(Booking obj) {
		return false;
	}

	@Override
	public Booking find(int id) {
		return null;
	}

	@Override
	public ArrayList<Booking> findAll() {
		ArrayList<Booking> bookingList = new ArrayList<Booking>();
		
		String query = "SELECT * FROM Booking";
        
		try {
            try (PreparedStatement stmt = this.connect.prepareStatement(query);
                 ResultSet resultSet = stmt.executeQuery()) {

                while (resultSet.next()) {
                	int id = resultSet.getInt("Id_booking");
                    LocalDate date_booking = resultSet.getDate("Date_booking").toLocalDate();
                    int nbrWeeksRent = resultSet.getInt("NbrWeeksRent");
                    int idGame = resultSet.getInt("Id_videogame");
                    
                    VideoGame game = VideoGame.findById(idGame);

                    Booking booking = new Booking(id, date_booking, nbrWeeksRent, game);
                    bookingList.add(booking);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookingList;
	}

}
