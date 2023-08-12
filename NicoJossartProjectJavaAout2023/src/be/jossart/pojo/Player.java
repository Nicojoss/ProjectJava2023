package be.jossart.pojo;

import java.time.LocalDate;
import java.util.ArrayList;

import be.jossart.dao.AbstractDAOFactory;
import be.jossart.dao.DAO;
import be.jossart.dao.PlayerDAO;

public class Player extends Users{
	private static final long serialVersionUID = 6023304013003822017L;
	private int credit;
	private String pseudo;
	private boolean birthdayBonus;
	private ArrayList<Loan> borrowings;
	private ArrayList<Copy> copies;
	private ArrayList<Loan> loans;
	private ArrayList<Booking> bookings;
	
	//CTOR
	public Player() {
		super();
		setBookings(new ArrayList<Booking>());
		setCopies(new ArrayList<Copy>());
		setLoans(new ArrayList<Loan>());
		setBorrowings(new ArrayList<Loan>());
	}
	//GET SET

	public Player(int id, String pseudo, int credit) {
		super(id);
		this.pseudo = pseudo;
		this.credit = credit;
	}

	public Player(String pseudo, String username, String password, LocalDate birthday) {
		super(username, password, birthday);
		this.pseudo = pseudo;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public boolean getBirthdayBonus() {
		return birthdayBonus;
	}

	public void setBirthdayBonus(boolean birthdayBonus) {
		this.birthdayBonus = birthdayBonus;
	}

	public ArrayList<Loan> getBorrowings() {
		return borrowings;
	}

	public void setBorrowings(ArrayList<Loan> borrowings) {
		this.borrowings = borrowings;
	}

	public ArrayList<Copy> getCopies() {
		return copies;
	}

	public void setCopies(ArrayList<Copy> copies) {
		this.copies = copies;
	}

	public ArrayList<Loan> getLoans() {
		return loans;
	}

	public void setLoans(ArrayList<Loan> loans) {
		this.loans = loans;
	}

	public ArrayList<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(ArrayList<Booking> bookings) {
		this.bookings = bookings;
	}

	public boolean UpdateCredit(Player player) {
		return PlayerDAO.UpdateCredit(player);
		
	}

	public static Player findById(int id_owner) {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory();
		DAO<Player> playerDAO = adf.getPlayerDAO();
		return playerDAO.find(id_owner);
	}
}
