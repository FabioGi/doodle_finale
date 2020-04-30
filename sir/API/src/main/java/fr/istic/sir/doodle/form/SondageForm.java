package fr.istic.sir.doodle.form;

import fr.istic.sir.doodle.entities.Sondage;

public class SondageForm {

	private String idUser;
	private Sondage sondage;
	
	public SondageForm() {
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public Sondage getSondage() {
		return sondage;
	}

	public void setSondage(Sondage sondage) {
		this.sondage = sondage;
	}

}
