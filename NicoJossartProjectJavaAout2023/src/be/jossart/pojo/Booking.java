package be.jossart.pojo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import be.jossart.dao.AbstractDAOFactory;
import be.jossart.dao.DAO;

public class Booking implements Serializable{
	private static final long serialVersionUID = -8774937176663077804L;
	private int idBooking;
	private int nbrWeeksRent;
	private LocalDate bookingDate;
	private Player player;
	private VideoGame game;
	
	// CTOR
	
	public Booking(LocalDate now, Player player, VideoGame game, int nbrWeeksRent) {
		bookingDate = now;
		this.player = player;
		this.game = game;
		this.setNbrWeeksRent(nbrWeeksRent);
	}
	public Booking(int id, LocalDate date_booking, int nbrWeeksRent, VideoGame game) {
		this.idBooking = id;
		this.bookingDate = date_booking;
		this.nbrWeeksRent = nbrWeeksRent;
		this.game = game;
	}
	public Booking(int idbooking, LocalDate date_booking, int nbrWeeksRent, VideoGame game, Player player) {
		this.idBooking = idbooking;
		this.bookingDate = date_booking;
		this.nbrWeeksRent = nbrWeeksRent;
		this.game = game;
		this.player = player;
	}
	// Get Set
	public int getIdBooking() {
		return idBooking;
	}
	public void setIdBooking(int idBooking) {
		this.idBooking = idBooking;
	}
	public LocalDate getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(LocalDate bookingDate) {
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
	public int getNbrWeeksRent() {
		return nbrWeeksRent;
	}
	public void setNbrWeeksRent(int nbrWeeksRent) {
		this.nbrWeeksRent = nbrWeeksRent;
	}
	
	// Methodes
	public static boolean CreateBooking(Booking booking) {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory();
		DAO<Booking> bookingDAO = adf.getBookingDAO();
		return bookingDAO.create(booking);
		
	}
	public static List<Booking> FindAll() {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory();
		DAO<Booking> bookingDAO = adf.getBookingDAO();
		return bookingDAO.findAll();
	}
	public static Booking Find(int idCopy) {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory();
		DAO<Booking> bookingDAO = adf.getBookingDAO();
		return bookingDAO.find(idCopy);
	}
	public static boolean Delete(Booking booking) {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory();
		DAO<Booking> bookingDAO = adf.getBookingDAO();
		return bookingDAO.delete(booking);
		
	}
}
