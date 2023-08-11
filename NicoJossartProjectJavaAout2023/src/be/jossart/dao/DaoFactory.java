package be.jossart.dao;

import java.sql.Connection;

import be.jossart.connection.DbConnection;
import be.jossart.pojo.Administrator;
import be.jossart.pojo.Booking;
import be.jossart.pojo.Copy;
import be.jossart.pojo.Loan;
import be.jossart.pojo.Player;
import be.jossart.pojo.Users;
import be.jossart.pojo.VideoGame;

public class DaoFactory extends AbstractDAOFactory {

	protected static final Connection conn = DbConnection.getInstance();
	@Override
	public DAO<Administrator> getAdministratorDAO() {
		return new AdministratorDAO(conn);
	}

	@Override
	public DAO<Booking> getBookingDAO() {
		return new BookingDAO(conn);
	}

	@Override
	public DAO<Copy> getCopyDAO() {
		return new CopyDAO(conn);
	}

	@Override
	public DAO<Loan> getLoanDAO() {
		return new LoanDAO(conn);
	}

	@Override
	public DAO<Player> getPlayerDAO() {
		return new PlayerDAO(conn);
	}

	@Override
	public DAO<Users> getUserDAO() {
		// TODO Auto-generated method stub
		return new UsersDAO(conn);
	}

	@Override
	public DAO<VideoGame> getVideoGameDAO() {
		// TODO Auto-generated method stub
		return new VideoGameDAO(conn);
	}

}
