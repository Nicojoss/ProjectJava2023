package be.jossart.pojo;

import java.io.Serializable;
import java.sql.Date;

public class Booking implements Serializable{
	private static final long serialVersionUID = -8774937176663077804L;
	private int idBooking;
	private Date bookingDate;
	private Player player;
	private VideoGame game;
	
	// CTOR
	
	// Get Set
	public int getIdBooking() {
		return idBooking;
	}
	public void setIdBooking(int idBooking) {
		this.idBooking = idBooking;
	}
	public Date getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public VideoGame getGame() {
		return game;
	}
	public void setGame(VideoGame game) {
		this.game = game;
	}

}
