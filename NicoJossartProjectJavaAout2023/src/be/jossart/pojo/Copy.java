package be.jossart.pojo;

import java.io.Serializable;

public class Copy implements Serializable{
	private static final long serialVersionUID = -241157985437282599L;
	private int idCopy;
	private Player owner;
	private VideoGame videoGame;
	private Loan loan;
	
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

}
