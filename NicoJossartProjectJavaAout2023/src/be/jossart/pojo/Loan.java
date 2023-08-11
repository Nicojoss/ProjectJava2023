package be.jossart.pojo;

import java.io.Serializable;
import java.time.LocalDate;

import be.jossart.dao.AbstractDAOFactory;
import be.jossart.dao.DAO;

public class Loan implements Serializable{
	private static final long serialVersionUID = 1630024894287292444L;
	private int idLoan;
	private LocalDate startDate;
	private LocalDate endDate;
	private boolean ongoing;
	private Player borrower;
	private Player lender;
	private Copy copy;
	
	public Loan(LocalDate startDate, LocalDate endDate, Player player, Copy selectedCopy) {
		this.startDate = startDate;
		this.endDate = endDate;
		lender = selectedCopy.getOwner();
		borrower = player;
		copy = selectedCopy;
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
	
	//Methodes 
	public boolean AddLoan(Loan loan) {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory();
		DAO<Loan> laonDAO = adf.getLoanDAO();
		return laonDAO.create(loan);
	}

}
