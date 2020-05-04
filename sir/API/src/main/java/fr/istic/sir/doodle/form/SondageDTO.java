package fr.istic.sir.doodle.form;

public class SondageDTO {
	private String lieu;
	private String titre;  
	private String resume;
	public String  userMails;
	
	public String getLieu() {
		return lieu;
	}
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	public String getUserMail() {
		return userMails;
	}
	public void setUserMail(String userMail) {
		this.userMails = userMail;
	}

}
