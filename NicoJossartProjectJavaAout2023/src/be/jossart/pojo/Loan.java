package be.jossart.pojo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import be.jossart.dao.AbstractDAOFactory;
import be.jossart.dao.DAO;
import be.jossart.dao.LoanDAO;

public class Loan implements Serializable{
	private static final long serialVersionUID = 1630024894287292444L;
	private int idLoan;
	private LocalDate startDate;
	private LocalDate endDate;
	private boolean ongoing;
	private Player borrower;
	private Player lender;
	private Copy copy;
	private LocalDate lastPenaltyDate;
	
	public Loan(LocalDate startDate, LocalDate endDate, Player player, Copy selectedCopy) {
		this.startDate = startDate;
		this.endDate = endDate;
		lender = selectedCopy.getOwner();
		borrower = player;
		copy = selectedCopy;
		lastPenaltyDate = null;
	}
	public Loan(int loanId, LocalDate startDate, LocalDate endDate, Copy copy, boolean ongoing, LocalDate lastPenaltyDate) {
		idLoan = loanId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.copy = copy;
		this.ongoing = ongoing;
		this.lastPenaltyDate = lastPenaltyDate;
	}
	// Getter Setter
	public int getIdLoan() {
		return idLoan;
	}
	public void setIdLoan(int idLoan) {
		this.idLoan = idLoan;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public boolean isOngoing() {
		return ongoing;
	}
	public void setOngoing(boolean ongoing) {
		this.ongoing = ongoing;
	}
	public Player getBorrower() {
		return borrower;
	}
	public void setBorrower(Player borrower) {
		this.borrower = borrower;
	}
	public Player getLender() {
		return lender;
	}
	public void setLender(Player lender) {
		this.lender = lender;
	}
	public Copy getCopy() {
		return copy;
	}
	public void setCopy(Copy copy) {
		this.copy = copy;
	}
	
	public LocalDate getlastPenaltyDate() {
		return lastPenaltyDate;
	}
	public void setlastPenaltyDate(LocalDate lastPenaltyDate) {
		this.lastPenaltyDate = lastPenaltyDate;
	}
	
	//Methodes 
	public static boolean AddLoan(Loan loan) {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory();
		DAO<Loan> loanDAO = adf.getLoanDAO();
		return loanDAO.create(loan);
	}
	
	public static List<Loan> FindMyBorrowings(Player player) {
		return LoanDAO.FindMyBorrowings(player.getIdUser());
	}
	
	public boolean UpdateLoan(Loan selectedLoan) {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory();
		DAO<Loan> loanDAO = adf.getLoanDAO();
		return loanDAO.update(selectedLoan);
	}
	
	public static boolean UpdatePenaltyDate(Loan selectedLoan) {
		return LoanDAO.UpdatePenaltyDate(selectedLoan);
	}

	@Override
	public String toString() {
		return "Game: " + copy.getVideoGame().getName() + " Console: " + copy.getVideoGame().getConsole() 
				+ " StartDate: " + startDate + " EndDate " + endDate;
	}
	
	
	

}
