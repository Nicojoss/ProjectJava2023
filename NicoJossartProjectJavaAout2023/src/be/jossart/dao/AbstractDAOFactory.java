package be.jossart.dao;

import be.jossart.pojo.Administrator;
import be.jossart.pojo.Booking;
import be.jossart.pojo.Copy;
import be.jossart.pojo.Loan;
import be.jossart.pojo.Player;
import be.jossart.pojo.Users;
import be.jossart.pojo.VideoGame;

public abstract class AbstractDAOFactory {
	public static final int DAO_FACTORY = 0;
	public static final int XML_DAO_FACTORY = 1;
	
	public abstract DAO<Administrator> getAdministratorDAO();
	
	public abstract DAO<Booking> getBookingDAO();
	
	public abstract DAO<Copy> getCopyDAO();
	
	public abstract DAO<Loan> getLoanDAO();
	
	public abstract DAO<Player> getPlayerDAO();
	
	public abstract DAO<Users> getUserDAO();
	
	public abstract DAO<VideoGame> getVideoGameDAO();

	public static AbstractDAOFactory getFactory(){
			return new DaoFactory();
		}

}
