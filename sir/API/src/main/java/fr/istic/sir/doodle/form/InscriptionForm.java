package fr.istic.sir.doodle.form;

import java.util.List;

import fr.istic.sir.doodle.entities.Allergie;
import fr.istic.sir.doodle.entities.Preference;
// import fr.istic.sir.doodle.entities.User;

public class InscriptionForm {
	
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
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
	
	private UserDTO user;
	private List<Allergie>allergies;
	private List<Preference>preferences;
	public InscriptionForm() {	}	

}
