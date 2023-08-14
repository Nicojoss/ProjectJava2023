package be.jossart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import be.jossart.connection.DbConnection;
import be.jossart.pojo.Copy;
import be.jossart.pojo.Loan;

public class LoanDAO extends DAO<Loan> {

	public LoanDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Loan obj) {
		boolean success = false;

	    String query = "INSERT INTO Loan (id_lender, id_borrower, Id_copy, Start_date, End_date, Ongoing) VALUES (?, ?, ?, ?, ?, ?)";

	    try (PreparedStatement preparedStatement = connect.prepareStatement(query)) {
	        preparedStatement.setInt(1, obj.getLender().getIdUser());
	        preparedStatement.setInt(2, obj.getBorrower().getIdUser());
	        preparedStatement.setInt(3, obj.getCopy().getIdCopy());
	        preparedStatement.setDate(4, java.sql.Date.valueOf(obj.getStartDate()));
	        preparedStatement.setDate(5, java.sql.Date.valueOf(obj.getEndDate()));
	        preparedStatement.setBoolean(6, obj.isOngoing());
	        

	        int result = preparedStatement.executeUpdate();
	        success = result > 0;
	        preparedStatement.close();
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
		boolean success = false;

	    String query = "UPDATE Loan SET Start_date = ?, End_date = ?, id_lender = ?, id_borrower = ?, Id_copy = ?, Ongoing = ? WHERE id_loan = ?";

	    try (PreparedStatement preparedStatement = connect.prepareStatement(query)) {
	        preparedStatement.setDate(1, java.sql.Date.valueOf(obj.getStartDate()));
	        //Keep track of the date the player returned the game
	        preparedStatement.setDate(2, java.sql.Date.valueOf(LocalDate.now())); 
	        preparedStatement.setInt(3, obj.getCopy().getOwner().getIdUser());
	        preparedStatement.setInt(4, obj.getBorrower().getIdUser());
	        preparedStatement.setInt(5, obj.getCopy().getIdCopy());
	        preparedStatement.setBoolean(6, obj.isOngoing());
	        preparedStatement.setInt(7, obj.getIdLoan());

	        int result = preparedStatement.executeUpdate();
	        success = result > 0;
	        preparedStatement.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return success;
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
	
	//Methodes
	public static List<Loan> FindMyBorrowings(int id){
		List<Loan> myBorrowings = new ArrayList<>();
	    Connection connect = DbConnection.getInstance();
	    
	    String query = "SELECT * FROM Loan WHERE id_borrower = ?";
	    
	    try (PreparedStatement preparedStatement = connect.prepareStatement(query)) {
	        preparedStatement.setInt(1, id);
	        
	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            while (resultSet.next()) {
	                int loanId = resultSet.getInt("Id_loan");
	                boolean onGoing = resultSet.getBoolean("Ongoing");
	                LocalDate startDate = resultSet.getDate("start_date").toLocalDate();
	                LocalDate endDate = resultSet.getDate("end_date").toLocalDate();
	                int copyId = resultSet.getInt("Id_copy");
	                
	                LocalDate lastPenaltyDate = null;
	                java.sql.Date lastPenaltyDateSql = resultSet.getDate("LastPenaltyDate");
	                if(lastPenaltyDateSql != null) {
	                	lastPenaltyDate = lastPenaltyDateSql.toLocalDate();
	                }
	                
	                if(onGoing) {
	                	Copy copy = Copy.FindByID(copyId); 
		                
		                Loan loan = new Loan(loanId, startDate, endDate, copy, onGoing, lastPenaltyDate);
		                myBorrowings.add(loan);
	                }
	                
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return myBorrowings;
		
	}

	public static boolean UpdatePenaltyDate(Loan obj) {
		boolean success = false;
		Connection connect = DbConnection.getInstance();
		
	    String query = "UPDATE Loan SET LastPenaltyDate = ? WHERE id_loan = ?";

	    try (PreparedStatement preparedStatement = connect.prepareStatement(query)) {
	    	preparedStatement.setDate(1, java.sql.Date.valueOf(obj.getlastPenaltyDate()));
	    	preparedStatement.setInt(2, obj.getIdLoan());
	         
	        
	        int result = preparedStatement.executeUpdate();
	        success = result > 0;
	        preparedStatement.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return success;
	}

}
