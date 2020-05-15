package fr.istic.sir.doodle.service.interfaces;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import fr.istic.sir.doodle.entities.Allergie;
import fr.istic.sir.doodle.entities.Creneaux;
import fr.istic.sir.doodle.entities.Preference;
import fr.istic.sir.doodle.entities.Reunion;
import fr.istic.sir.doodle.entities.Sondage;
import fr.istic.sir.doodle.entities.User;
import fr.istic.sir.doodle.entities.Vote;
import fr.istic.sir.doodle.form.SondageDTO;

public interface IdoodleService {
	/**
	 * 
	 * @param idUser user's id who create the sondage
	 * @param sondage
	 * @return true if sondage has been created 
	 */
	public List<Vote>getAllVoteOrderBydate();
	/**
	 * creer une reunion
	 * @return true si la reunion a ete bien creer
	 */
	public boolean createReunion(Reunion reunion);
	/**
	 * valid a sondage
	 * @return true 
	 */
	public Creneaux validedSondage(long idCreneau, List<String> emails, String subject, String content);
	/**
	 * List a attendlist
	 * @return List a attendanceList
	 */
	public Set<User>attendanceList(long idCreneau, long idSondage);
	/***
	 * List absenceList
	 * @return absenceList
	 */
	public Set<User> absenceList(long idCreneau,long idSondage);
	
	/**
	 * selection un crenaux à la fin d'un sondage
	 * @return crenaux
	 */
	public Creneaux selectCrenauxforMeeting();
	
	/**
	 * 
	 * @param s sondage
	 * @param u user who participed to a sondage
	 * @return the dates which have been chosen by a user after a sondage
	 */
	public List<Long> getChoiceToUserAfterSondage(long id, String email);

	/**
	 * 
	 * @param s
	 * @return
	 */
	public List<Creneaux> getAllVotesOrderBySondage(Sondage s);
	
	/**
	 * 
	 * @param idUser
	 * @return
	 */
	public List<Preference> getPrefernceOrderByUser(String idUser);
	
	/**
	 * 
	 * @param idUser
	 * @return
	 */
	public List<Allergie> getAllergieOrderByUser(String idUser);
	/**
	 * Sent multiple mails
	 * @param usersMail
	 */
	
	// void sendMultipleMail(List<String> usersMail);
	/**
	 * Creation d'un sondage avec la liste de creneau associe
	 * @param sondage
	 * @param creneau
	 * @param mails
	 * @return
	 */
	Sondage createSondage(SondageDTO sondage,List<Creneaux> creneau, List<String>mails, String subject, String content);
	/**
	 * 
	 * @param id
	 * @return
	 */
	Collection<Creneaux> selectCreneauOrderBySondage(long id);
	
	/**
	 * 
	 * @param idUser
	 * @param idCreneaux
	 */
	void choseDate(String idUser, List<Long> idCreneaux);
	
	/**
	 * 
	 * @return
	 */
	int countUserOrderBySlotinCurrentSurvey(long idSlot,long idSurvey );
	
	/**
	 * create allergie
	 * @param allergie
	 * @param email
	 * @return
	 */
	// boolean createAllergie(Allergie allergie, String email);
	/**
	 * create preference
	 * @param preference
	 * @param email
	 * @return
	 */
	boolean createPreference(String preference, String email);
	/**
	 * delete preference
	 * @param id
	 */
	void deletePreference(long id);
	/***
	 * remove allergie
	 * @param id
	 */
	void deleteAllergie(long id);
	/**
	 * 
	 * @param mail
	 * @param subject
	 * @param content
	 */
	void sendMailToUserAfterSondageCreated(String mail, String subject, String content);
	/***
	 * 
	 * @param usersMail
	 * @param subject
	 * @param content
	 */
	void sendMultipleMail(List<String> usersMail, String subject, String content);
	/**
	 * 
	 * @param name
	 * @param email
	 * @return
	 */
	
	boolean createAllergie(String name, String email);
	
	
	
	

}
