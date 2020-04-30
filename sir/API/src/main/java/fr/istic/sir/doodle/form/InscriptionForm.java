package fr.istic.sir.doodle.form;

import java.util.List;

import fr.istic.sir.doodle.entities.Allergie;
import fr.istic.sir.doodle.entities.Preference;
import fr.istic.sir.doodle.entities.User;

public class InscriptionForm {
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Allergie> getAllergies() {
		return allergies;
	}
	public void setAllergies(List<Allergie> allergies) {
		this.allergies = allergies;
	}
	public List<Preference> getPreferences() {
		return preferences;
	}
	public void setPreferences(List<Preference> preferences) {
		this.preferences = preferences;
	}
	
	private User user;
	private List<Allergie>allergies;
	private List<Preference>preferences;
	public InscriptionForm() {	}

}
