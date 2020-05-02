package fr.istic.sir.doodle.form;

import fr.istic.sir.doodle.entities.User;

public class PreferenceDTO {
	private Long id;
	private String name;
	private User user;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
