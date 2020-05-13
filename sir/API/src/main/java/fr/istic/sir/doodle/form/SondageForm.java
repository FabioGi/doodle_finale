package fr.istic.sir.doodle.form;

import java.util.List;

import fr.istic.sir.doodle.entities.Creneaux;

public class SondageForm {

	private SondageDTO sondage;
	private List<Creneaux>creneau ;
	private List<String>mails;
	private String content;
	private String subject;
	
	public SondageForm() {
	}


	public List<Creneaux> getCreneau() {
		return creneau;
	}

	public void setCreneau(List<Creneaux> creneau) {
		this.creneau = creneau;
	}


	public SondageDTO getSondage() {
		return sondage;
	}


	public void setSondage(SondageDTO sondage) {
		this.sondage = sondage;
	}


	public List<String> getMails() {
		return mails;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}

}
