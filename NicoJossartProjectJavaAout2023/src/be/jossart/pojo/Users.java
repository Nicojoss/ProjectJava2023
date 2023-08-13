package be.jossart.pojo;

import java.io.Serializable;
import java.time.LocalDate;

import be.jossart.dao.AbstractDAOFactory;
import be.jossart.dao.DAO;
import be.jossart.dao.UsersDAO;

public class Users implements Serializable{
	private static final long serialVersionUID = 473122234366668086L;
	private int idUser;
	private String username;
	private String password;
	private LocalDate DateRegistration;
	private Boolean admin;
	
	//Constructor
	public Users() {
		
	}
	public Users(int id, String username, String password) {
		this.idUser = id;
		this.username = username;
		this.password = password;
		admin = false;
	}

	public Users(int id) {
		idUser = id;
	}
	public Users(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public Users(String username, String password, LocalDate birthday) {
		this.username = username;
		this.password = password;
		setDateRegistration(birthday);
		admin = false;
	}
	// getter setter
	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public LocalDate getDateRegistration() {
		return DateRegistration;
	}
	public void setDateRegistration(LocalDate dateRegistration) {
		DateRegistration = dateRegistration;
	}
	
	public Boolean getAdmin() {
		return admin;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	
	// methodes
	public static Users login(String username, String password) throws Exception {
		return UsersDAO.login(username, password);
	}
	
	public boolean Register(Player player) {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory();
		DAO<Player> playerDAO = adf.getPlayerDAO();
		return playerDAO.create(player);
	}
}
