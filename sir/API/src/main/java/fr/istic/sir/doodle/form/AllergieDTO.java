package fr.istic.sir.doodle.form;

import fr.istic.sir.doodle.entities.User;

public class AllergieDTO {
    private long id ;
    private User user;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private String name ;

}
