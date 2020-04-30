package fr.istic.sir.doodle.form;

import fr.istic.sir.doodle.entities.Creneaux;

public class CreneauxForm {

	private int  idSondage;
	private Creneaux creneaux;
	
	public CreneauxForm() {
	}
	public int getIdSondage() {
		return idSondage;
	}
	public void setIdSondage(int idSondage) {
		this.idSondage = idSondage;
	}
	public Creneaux getCreneaux() {
		return creneaux;
	}
	public void setCreneaux(Creneaux creneaux) {
		this.creneaux = creneaux;
	}

}
