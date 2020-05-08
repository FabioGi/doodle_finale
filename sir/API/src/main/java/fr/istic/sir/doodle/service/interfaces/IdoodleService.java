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
	public boolean createSondage(String idUser, Sondage sondage);
	/**
	 * Lister le vote des utilisateur à une date precise
	 * @return true si le sondage a ete bien creer
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
	public void validedSondage(long idCreneau);
	/**
	 * envoyer un mails aux utilisateurs avec la date retenue pour la reunion
	 */
	public void sendMailertoUsersWithDateOfMeeting();
	/**
	 * creer un pad
	 * @return true si le pad a ete bien creer
	 */
	public void createPad();
	/**
	 * List a attendlist
	 * @return List a attendanceList
	 */
	public Set<User>attendanceList();
	/***
	 * List absenceList
	 * @return absenceList
	 */
	public Set<User>absenceList();
	
	/***
	 * Allow user to choose a date
	 * @param idUser user's id
	 * @param idCreneau creneau's id
	 * @param vote the choice of user
	 * @return true if user's choice is valided
	 */
	// public boolean choseDate(String idUser,long idCreneau);
	
	/***
	 * Allow a user's master to create a creneau for a sondage
	 * @param idSondage
	 * @param creneau
	 * @return true if it's created
	 */
	
	// boolean createCrenaux(int idSondage, Creneaux creneau );
	
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
	 *  create a user with his preferences and allergies
	 * @param user
	 * @param allergies
	 * @param preferences
	 * @return  true if the request has been executed
	 */
	// boolean createUser(UserDTO user, List<Allergie> allergies, List<Preference> preferences);

	/**
	 * 
	 * @param s
	 * @return
	 */
	public List<Creneaux> getAllVotesOrderBySondage(Sondage s);
	
	/**
	 * @return *
	 * 
	 */
	public void sendMailToUserAfterSondageCreated(String usersMail);
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
	
	void sendMultipleMail(List<String> usersMail);
	/**
	 * Creation d'un sondage avec la liste de creneau associe
	 * @param sondage
	 * @param creneau
	 * @param mails
	 * @return
	 */
	boolean createSondage(SondageDTO sondage, List<Creneaux> creneau, List<String> mails);
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
	
	
	

}
