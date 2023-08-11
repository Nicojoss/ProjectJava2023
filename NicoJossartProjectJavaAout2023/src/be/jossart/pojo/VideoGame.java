package be.jossart.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import be.jossart.dao.AbstractDAOFactory;
import be.jossart.dao.DAO;

public class VideoGame implements Serializable{
	private static final long serialVersionUID = -221544929582495641L;
	private int id_videogame;
	private String name;
	private int creditCost;
	private String console;
	private ArrayList<Copy> copies;
	private ArrayList<Booking> bookings;
	
	//CTOR
	public VideoGame() {
		setCopies(new ArrayList<Copy>());
		setBookings(new ArrayList<Booking>());
	}

	public VideoGame(String name, String console, int creditCost) {
		this.name = name;
		this.console = console;
		this.creditCost = creditCost;
	}
	
	public VideoGame(int id, String name, String console, int creditCost) {
		id_videogame = id;
		this.name = name;
		this.console = console;
		this.creditCost = creditCost;
	}

	//GET SET
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCreditCost() {
		return creditCost;
	}

	public void setCreditCost(int creditCost) {
		this.creditCost = creditCost;
	}

	public String getConsole() {
		return console;
	}

	public void setConsole(String console) {
		this.console = console;
	}

	public ArrayList<Copy> getCopies() {
		return copies;
	}

	public void setCopies(ArrayList<Copy> copies) {
		this.copies = copies;
	}

	public ArrayList<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(ArrayList<Booking> bookings) {
		this.bookings = bookings;
	}
	public int getId_videogame() {
		return id_videogame;
	}

	public void setId_videogame(int id_videogame) {
		this.id_videogame = id_videogame;
	}
	
	//Methodes
	public static List<VideoGame> FindAll() {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory();
		DAO<VideoGame> videoGameDAO = adf.getVideoGameDAO();
		return videoGameDAO.findAll();
	}
	
	public static boolean AddGame(VideoGame game) {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory();
		DAO<VideoGame> videoGameDAO = adf.getVideoGameDAO();
		return videoGameDAO.create(game); 
	}
	
	@Override
    public String toString() {
        return getName() + " (" + getConsole() + ") - Credit Cost: " + getCreditCost();
    }
}
