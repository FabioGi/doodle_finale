package fr.istic.sir.doodle.form;

import fr.istic.sir.doodle.entities.Reunion;

public class ReunionForm {

	private long idCreneau ;
	private Reunion reunion;
	
	public ReunionForm() {}
	public long getIdCreneau() {
		return idCreneau;
	}
	public void setIdCreneau(long idCreneau) {
		this.idCreneau = idCreneau;
	}
	public Reunion getReunion() {
		return reunion;
	}
	public void setReunion(Reunion reunion) {
		this.reunion = reunion;
	}

}
