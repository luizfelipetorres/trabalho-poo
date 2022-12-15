package model;

public class Player {
	
	private int id;
	private String name;
	private String email;
	
	public Player(int id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}

}
