package fr.istic.sir.doodle.form;

import fr.istic.sir.doodle.entities.User;

public class PreferenceDTO {
	private String name;
	private String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
