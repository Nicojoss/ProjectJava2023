package be.jossart.dao;

import java.sql.Connection;
import java.util.ArrayList;

import be.jossart.pojo.Copy;

public class CopyDAO extends DAO<Copy> {

	public CopyDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Copy obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Copy obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Copy obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Copy find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Copy> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
