package be.jossart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import be.jossart.connection.DbConnection;
import be.jossart.pojo.Booking;
import be.jossart.pojo.Player;
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
	    boolean success = false;
	    
	    String query = "DELETE FROM Booking WHERE Id_booking = ?";

	    try (PreparedStatement preparedStatement = connect.prepareStatement(query)) {
	        preparedStatement.setInt(1, obj.getIdBooking());

	        int result = preparedStatement.executeUpdate();
	        success = result > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return success;
	}

	@Override
	public boolean update(Booking obj) {
		return false;
	}

	@Override
	public Booking find(int id) {
	    Booking booking = null;
	    
	    String query = "SELECT * FROM Booking WHERE Id_videogame = ?";
	        
	    try {
	        try (PreparedStatement stmt = this.connect.prepareStatement(query)) {
	            stmt.setInt(1, id);
	            ResultSet resultSet = stmt.executeQuery();

	            if (resultSet.next()) {
	                int idbooking = resultSet.getInt("Id_booking");
	                LocalDate date_booking = resultSet.getDate("Date_booking").toLocalDate();
	                int nbrWeeksRent = resultSet.getInt("NbrWeeksRent");
	                int idGame = resultSet.getInt("Id_videogame");
	                int idPlayer = resultSet.getInt("id_user");
	                VideoGame game = VideoGame.findById(idGame);
	                Player player = Player.findById(idPlayer);

	                booking = new Booking(idbooking, date_booking, nbrWeeksRent, game, player);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return booking;
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
