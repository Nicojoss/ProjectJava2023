package be.jossart.dao;

import java.sql.Connection;
import java.util.ArrayList;

import be.jossart.pojo.Booking;

public class BookingDAO extends DAO<Booking> {

	public BookingDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Booking obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Booking obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Booking obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Booking find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Booking> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
