package be.jossart.pojo;

import java.io.Serializable;
import java.util.List;

import be.jossart.dao.AbstractDAOFactory;
import be.jossart.dao.CopyDAO;
import be.jossart.dao.DAO;

public class Copy implements Serializable{
	private static final long serialVersionUID = -241157985437282599L;
	private int idCopy;
	private boolean available;
	private Player owner;
	private VideoGame videoGame;
	private Loan loan;
	
	public Copy(VideoGame selectedGame, Player player) {
		videoGame = selectedGame;
		owner = player;
	}
	public Copy() {
	}
	public Copy(int idCopy, boolean available, Player player, VideoGame game) {
		this.idCopy = idCopy;
		this.setAvailable(available);
		this.owner = player;
		this.videoGame = game;
	}
	public Copy(int copyId, VideoGame game, Player copyOwner) {
		idCopy = copyId;
		videoGame = game;
		owner = copyOwner;
	}
	//getter setter
	public int getIdCopy() {
		return idCopy;
	}
	public void setIdCopy(int idCopy) {
		this.idCopy = idCopy;
	}
	public Player getOwner() {
		return owner;
	}
	public void setOwner(Player owner) {
		this.owner = owner;
	}
	public VideoGame getVideoGame() {
		return videoGame;
	}
	public void setVideoGame(VideoGame videoGame) {
		this.videoGame = videoGame;
	}
	public Loan getLoan() {
		return loan;
	}
	public void setLoan(Loan loan) {
		this.loan = loan;
	}
	public boolean getAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	//Methodes
	public boolean AddCopy(Copy copy) {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory();
		DAO<Copy> copyDAO = adf.getCopyDAO();
		return copyDAO.create(copy);
		
		
	}
	public static List<Copy> findAll() {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory();
		DAO<Copy> copyDAO = adf.getCopyDAO();
		return copyDAO.findAll();
	}
	public boolean UpdateAvailable(Copy selectedCopy) {
		return CopyDAO.UpdateAvailable(selectedCopy);
		
	}
	
	public static Copy FindByID(int copyId) {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory();
		DAO<Copy> copyDAO = adf.getCopyDAO();
		return copyDAO.find(copyId);
	}
	
	@Override
    public String toString() {
        return "Owner:" + owner.getPseudo() +"  Game: " + getVideoGame().getName() + " Console: " + getVideoGame().getConsole() + " credits: " + getVideoGame().getCreditCost();
    }
	public static boolean Update(Copy copy) {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory();
		DAO<Copy> copyDAO = adf.getCopyDAO();
		return copyDAO.update(copy);
		
	}
	public static int CalculateCreditCost(Copy selectedCopy, int nbrWeeksRent) {
		return (selectedCopy.getVideoGame().getCreditCost()) * nbrWeeksRent;
		
	}
	
}
