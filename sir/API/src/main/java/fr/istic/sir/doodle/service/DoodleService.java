package fr.istic.sir.doodle.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.istic.sir.doodle.dao.ICreneauxReposirory;
import fr.istic.sir.doodle.dao.IallergieRepository;
import fr.istic.sir.doodle.dao.IpreferenceRepository;
import fr.istic.sir.doodle.dao.IreunionRepository;
import fr.istic.sir.doodle.dao.IsondageRepository;
import fr.istic.sir.doodle.dao.IuserRepository;
import fr.istic.sir.doodle.dao.IvoteRepository;
import fr.istic.sir.doodle.entities.Allergie;
import fr.istic.sir.doodle.entities.Creneaux;
import fr.istic.sir.doodle.entities.Invitation;
import fr.istic.sir.doodle.entities.Preference;
import fr.istic.sir.doodle.entities.Reunion;
import fr.istic.sir.doodle.entities.Sondage;
import fr.istic.sir.doodle.entities.User;
import fr.istic.sir.doodle.entities.Vote;
import fr.istic.sir.doodle.form.SondageDTO;
import fr.istic.sir.doodle.service.interfaces.IdoodleService;
/**
 * 
 * @author Fabrice Kadio
 * @author Konan Othniel
 * contient l'implementation de tous les services du doodle
 *
 */

@Service
public class DoodleService implements IdoodleService {

	@Autowired
	IuserRepository rUser ;
	@Autowired
	IallergieRepository rAllergie;
	@Autowired
	IvoteRepository rVote ;
	@Autowired
	private IsondageRepository rSondage;
	@Autowired
	IreunionRepository rReunion ;
	@Autowired
	IpreferenceRepository rPreference ;
	@Autowired
	ICreneauxReposirory rCreneau;
	@Autowired
	private JavaMailSender emailSender;
	
	Logger logger = Logger.getLogger("logger");
	
	@Override
	public List<Vote> getAllVoteOrderBydate() {
		return rVote.findAll();
	}

	@Transactional
	@Override
	// ok
	public Creneaux validedSondage(long idCreneau, List<String> emails, String subject, String content) {
		Creneaux creneau = rCreneau.findById(new Long(idCreneau)).get();
		creneau.setValided(true);
		this.sendMultipleMail(emails, subject, content);
		return creneau;
	}
	
	@Override
	public List<Preference>getPrefernceOrderByUser(String idUser){
		return rPreference.getPreferenceOrderByUser(idUser);
	}
	
	@Override
	public List<Allergie>getAllergieOrderByUser(String idUser){
		return rAllergie.getAllergieOrderByUser(idUser);
	}

	@Override
	public Set<User> attendanceList(long idCreneau,long idSondage) {
		return rUser.findAttendanceList(idCreneau, idSondage);
	}

	@Override
	public Set<User> absenceList(long idCreneau,long idSondage) {
		return rUser.findAbsenceList(idCreneau, idSondage);
	}
	
	@Transactional
	@Override
	public Sondage createSondage(SondageDTO sondage,List<Creneaux> creneau, List<String>mails, String subject, String content) {
		// create sondage
		Sondage nSondage = new Sondage();
		nSondage.setLieu(sondage.getLieu());
		nSondage.setResume(sondage.getResume());
		nSondage.setTitre(sondage.getTitre());
		User user = rUser.findByEmail(sondage.getUserMail());
		nSondage.setUser(user);
		// match a creneau to sondage
		for(Creneaux cr: creneau ) {
			cr.setSondage(nSondage);
		}
		List<Invitation>invitations = sendInvitation(mails, nSondage) ;
		nSondage.setInvitations(invitations);
		nSondage.setDated(new ArrayList<>());
		nSondage.getDated().addAll(creneau);
		Sondage response = rSondage.save(nSondage);
		
		if(response!=null) {
			sendMultipleMail(mails,subject,content);
		}
		return nSondage;
		// return true;
		
	}
	public List<Invitation> sendInvitation(List<String>mails, Sondage sondage) {
		List<Invitation>invitations = new ArrayList<>() ;
		for(String mail: mails) {
			Invitation invitation = new Invitation();
			invitation.setSondage(sondage);
			invitation.setGuest_mail(mail);
			invitations.add(invitation);
		}
		return invitations;
	}

	@Transactional
	@Override
	// ok
	public void choseDate(String idUser, List<Long>idCreneaux) {
		User user = rUser.findById(idUser).get();
		for(Long id : idCreneaux) {
			Creneaux creneau = rCreneau.findById((long) id).get();
			Vote vote = new Vote();
			vote.setDate(creneau);
			vote.setUser(user);
			rVote.save(vote);
		}
		// return true;
	}


	@Transactional
	@Override
	public boolean createReunion(Reunion reunion) {
		 Creneaux creneaux = this.selectCrenauxforMeeting();
		 Objects.requireNonNull(creneaux);
		 reunion.setDated(creneaux);
		 return rReunion.save(reunion) != null;
	}
	@Transactional
	@Override
	public boolean createAllergie(String  name, String email) {
		User user = rUser.findByEmail(email);
		 Allergie newAllergie =  new Allergie();
		 newAllergie.setName(name);
		 newAllergie.setUser(user);
		 return rAllergie.save(newAllergie)!=null;
	}
	
	@Override
	public boolean createPreference(String  preference, String email) {
		User user = rUser.findByEmail(email);
		Preference newPreference =  new Preference();
		newPreference.setName(preference);
		newPreference.setUser(user);
		 return rPreference.save(newPreference)!=null;
	}
	@Override
	public void deletePreference(long id) {
		  rPreference.deleteById(id);
	}
	@Override
	public void deleteAllergie(long id) {
		  rAllergie.deleteById(id);
	}
	@Transactional
	@Override
	// ok
	public Creneaux selectCrenauxforMeeting() {
		Creneaux dateForMeeting = rCreneau.findCrenauByStatus(true);
		return dateForMeeting ;
	}

	@Override
	// ok
	public List<Long> getChoiceToUserAfterSondage(long id, String email) {
		return rCreneau.getUserChoiceForSondage(id, email);
	}

	@Override
	public List<Creneaux> getAllVotesOrderBySondage(Sondage s) {
		return rCreneau.getAllResponseForSondage(s);
	}

	@Override
	// ajouter le lien, et la date de reunion selection.
	// sujet 
	public void sendMailToUserAfterSondageCreated(String mail, String subject, String content ) {
		SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("doodlesondage@istic.com");
        message.setTo(""+mail+"");
        message.setSubject(""+subject+"");
        message.setText(""+content+"");
        /*message.setTo("kouassi-othniel.konan@etudiant.univ-rennes1.fr");*/

        // Send Message!
       this.emailSender.send(message);
       
		
	}

	@Override
	public void sendMultipleMail(List<String> usersMail, String subject, String content) {
			for(String mail: usersMail ) {	
				this.sendMailToUserAfterSondageCreated(mail,subject, content);
			}
		
	} 
	
	
	public List<String>getUsersMail(){
		return rUser.findAll().stream().map(user -> user.getEmail())
									   .collect(Collectors.toList());
	}
	
	@Override
	public Collection<Creneaux> selectCreneauOrderBySondage(long id) {
		return rSondage.findById(id).get().getDated();
		
	}

	@Override
	public int countUserOrderBySlotinCurrentSurvey(long idSlot, long idSurvey) {
		return rVote.CountSlotOrderBySurvey(idSlot, idSurvey);
	}
	
}