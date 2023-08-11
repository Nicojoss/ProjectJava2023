package be.jossart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import be.jossart.pojo.Loan;

public class LoanDAO extends DAO<Loan> {

	public LoanDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Loan obj) {
		boolean success = false;

	    String query = "INSERT INTO Loan (id_lender, id_borrower, id_copy, start_date, end_date, Ongoing) VALUES (?, ? , ?, ?, ?, ?)";

	    try (PreparedStatement preparedStatement = connect.prepareStatement(query)) {
	        preparedStatement.setInt(1, obj.getLender().getIdUser());
	        preparedStatement.setInt(2, obj.getBorrower().getIdUser());
	        preparedStatement.setInt(3, obj.getCopy().getIdCopy());
	        preparedStatement.setDate(4, java.sql.Date.valueOf(obj.getStartDate()));
	        preparedStatement.setDate(5, java.sql.Date.valueOf(obj.getEndDate()));
	        preparedStatement.setBoolean(6, true);

	        int rowsAffected = preparedStatement.executeUpdate();
	        success = rowsAffected > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return success;
	}

	@Override
	public boolean delete(Loan obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Loan obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Loan find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Loan> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
