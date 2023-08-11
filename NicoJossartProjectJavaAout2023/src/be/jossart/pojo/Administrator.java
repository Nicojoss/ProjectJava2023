package be.jossart.pojo;

import java.io.Serializable;

public class Administrator extends Users implements Serializable {
	private static final long serialVersionUID = 8024367655579300184L;
	
	public Administrator(int id, String username, String password) {
		super(id, username, password);
	}

	public Administrator() {
	}

}
