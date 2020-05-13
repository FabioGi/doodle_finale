package fr.istic.sir.doodle.controller;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

// import com.test.email.demo.My_Contants;

import fr.istic.sir.doodle.dao.ICreneauxReposirory;
import fr.istic.sir.doodle.dao.IallergieRepository;
import fr.istic.sir.doodle.dao.Iinvitation;
import fr.istic.sir.doodle.dao.IpreferenceRepository;
import fr.istic.sir.doodle.dao.IreunionRepository;
import fr.istic.sir.doodle.dao.IsondageRepository;
import fr.istic.sir.doodle.dao.IuserRepository;
import fr.istic.sir.doodle.dao.IvoteRepository;
import fr.istic.sir.doodle.entities.Allergie;
import fr.istic.sir.doodle.entities.Creneaux;
import fr.istic.sir.doodle.entities.Preference;
import fr.istic.sir.doodle.entities.Reunion;
import fr.istic.sir.doodle.entities.Sondage;
import fr.istic.sir.doodle.entities.User;
import fr.istic.sir.doodle.entities.Vote;
import fr.istic.sir.doodle.form.AllergieDTO;
import fr.istic.sir.doodle.form.CreneauxForm;
import fr.istic.sir.doodle.form.PreferenceDTO;
import fr.istic.sir.doodle.form.SondageDTO;
import fr.istic.sir.doodle.form.SondageForm;
import fr.istic.sir.doodle.form.ValidedSondageForm;
import fr.istic.sir.doodle.service.interfaces.IdoodleService;

@RestController
@RequestMapping("/api")
 // @CrossOrigin(origins="http://localhost:5000") 
// @CrossOrigin("http://localhost:5000")
  @CrossOrigin
public class SondageController {
	@Autowired
	ICreneauxReposirory icreCreneauxReposirory;
	@Autowired
	IdoodleService doodleService ;
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
	Iinvitation invitation ;
	@Autowired
	private JavaMailSender emailSender;
	
	/**
	 * 
	 * GET REQUEST FOR API
	 */
	@RequestMapping(value = "/allergies/{id}", method = RequestMethod.GET, produces = {"application/json"})
	  List<Allergie> allAllergies(@PathVariable String id) {
	    return rAllergie.getAllergieOrderByUser(id);
	  }
	@RequestMapping(value = "/creneaux", method = RequestMethod.GET, produces = {"application/json"})
	  List<Creneaux> allCrenaux() {
	    return rCreneau.findAll();
	  }
	@RequestMapping(value = "/surveylist/{id}", method = RequestMethod.GET, produces = {"application/json"})
	  List<Sondage> getSurveyOrderByGuest(@PathVariable String id) {
	    return invitation.getSurveyListOrderByGuestList(id);
	  }
	@RequestMapping(value = "/surveylistcreated/{id}", method = RequestMethod.GET, produces = {"application/json"})
	  List<Sondage> getSurveyCreatedByGuest(@PathVariable String id) {
	    return rSondage.getSurveyCreatedByUsers(id);
	  }
	@RequestMapping(value = "/preferences/{id}", method = RequestMethod.GET, produces = {"application/json"})
	  List<Preference> allPreference(@PathVariable String id) {
	    return rPreference.getPreferenceOrderByUser(id);
	  }
	@RequestMapping(value = "/reunions", method = RequestMethod.GET, produces = {"application/json"})
	  List<Reunion> allReunions() {
	    return rReunion.findAll();
	  }
	
	@RequestMapping(value = "/sondages", method = RequestMethod.GET, produces = {"application/json"})
	  List<Sondage> allSondage() {
	    return rSondage.findAll();
	  }
	
	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = {"application/json"})
	  List<User> allUsers() {
	    return rUser.findAll();
	  }
	
	@RequestMapping(value = "/creneau_selected", method = RequestMethod.GET, produces = {"application/json"})
	public Creneaux getCreneauSelectedForMeeting() {
		return doodleService.selectCrenauxforMeeting();
  }
	@RequestMapping(value = "/Allvote", method = RequestMethod.GET, produces = {"application/json"})
	public List<Vote>getAllVote(){
		return doodleService.getAllVoteOrderBydate();
	}
	
	@RequestMapping(path = "/responses/{idUser}/{idSondage}", method = RequestMethod.GET, produces = {"application/json"})
	List<Long> getChoiceToUserAfterSondage(@PathVariable String idUser, @PathVariable long idSondage){
		return doodleService.getChoiceToUserAfterSondage(idSondage, idUser);
	}
	
	@RequestMapping(value = "/attendlist/{idcreneau}/{idsondage}", method = RequestMethod.GET, produces = {"application/json"})
	Set<User> getAttendList(@PathVariable long idcreneau, @PathVariable long idsondage){
		return doodleService.attendanceList(idcreneau,idsondage);
	}
	
	@RequestMapping(value = "/absencelist/{idcreneau}/{idsondage}", method = RequestMethod.GET, produces = {"application/json"})
	Set<User> getAbsenceList(@PathVariable long idcreneau, @PathVariable long idsondage){
		return doodleService.absenceList(idcreneau,idsondage);
	}
	
	@RequestMapping(value = "/countsurveyvalided/{email}", method = RequestMethod.GET, produces = {"application/json"})
	int countSurveyValided(@PathVariable String email){
		return rSondage.countSurveyWhichAreValided(email);
	}
	
	/***
	 * POST REQUEST 
	 */
	
	
	@RequestMapping(value = "/addsondage", method = RequestMethod.POST)
	public Sondage createSondage(@RequestBody SondageForm sondageForm) {
		Objects.requireNonNull(sondageForm);
		SondageDTO sondage = sondageForm.getSondage();
		String subject = sondageForm.getSubject();
		String content = sondageForm.getContent();
		List<Creneaux> creneaux = sondageForm.getCreneau();
		List<String> mails = sondageForm.getMails();
		Objects.requireNonNull(creneaux);
		Objects.requireNonNull(sondage);
		Objects.requireNonNull(mails);
		return doodleService.createSondage(sondage,creneaux,mails,subject,content);
	}
	
	@RequestMapping(value = "/addreunion", method = RequestMethod.POST)
	 public boolean createMeeting(@RequestBody 	Reunion reunion) {
		Objects.requireNonNull(reunion);
		return doodleService.createReunion(reunion);
	}
	
	@RequestMapping(value = "/addallergie", method = RequestMethod.POST)
	 public boolean createAllergie(@RequestBody AllergieDTO a) {
		return doodleService.createAllergie(a.getName(), a.getEmail());
	}
	
	@RequestMapping(value = "/addpreference", method = RequestMethod.POST)
	 public boolean createPreference(@RequestBody PreferenceDTO p ) {
		return doodleService.createPreference(p.getName(), p.getEmail());
	}
	
	@RequestMapping(path = "/chosecreneau", method = RequestMethod.POST)
	 public void choseDate(@RequestBody CreneauxForm creneauForm) {
		 String userMail = creneauForm.getEmail();
		 List<Long>choix = creneauForm.getChoix();
		 doodleService.choseDate(userMail, choix);
	}
	
	/****
	 * PUT REQUEST FOR API
	 */
	@RequestMapping(path = "/validesondage", method = RequestMethod.PUT)
	public Creneaux validedSondage(@RequestBody ValidedSondageForm vs ) {
		long idCreneau = vs.getId();
		String content = vs.getContent();
		String subject = vs.getSubject();
		List<String> emails = vs.getEmails();
		return doodleService.validedSondage(idCreneau,emails,subject,content );
	}
	
	/**
	 * SENT MAIL
	 */
	
	    @ResponseBody
	    @RequestMapping("/mail")
//	    public void sendSimpleEmail(){
//	          doodleService.sendMailToUserAfterSondageCreated();
//	    }
	    public String sendSimpleEmail(){
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setFrom("LeMailFonctionne@etudiant.univ-rennes1.fr");
	        message.setTo("hervefab007@gmail.com");
	        message.setSubject("Test Simple Email");
	        message.setText("Hello, Im testing Simple Email");
	        /*message.setTo("kouassi-othniel.konan@etudiant.univ-rennes1.fr");*/


	        // Send Message!
	        emailSender.send(message);
	        return "Email Sent";
	    }
	    
	    /**
	     * find user
	     */
	
     @RequestMapping(value = "/user/{username}", method = RequestMethod.GET, produces = {"application/json"})
	  User getUser(@PathVariable String username) {
	    return rUser.findByUsername(username);
	  } 
     /**
      * get sondage details order by id
      */
	
     @RequestMapping(value = "/sondage/{id}", method = RequestMethod.GET, produces = {"application/json"})
	  Sondage getSondageOrderById(@PathVariable long id) {
	    return rSondage.findById(id).get();
	  } 
     
     @RequestMapping(value = "/creneaulist/{id}", method = RequestMethod.GET, produces = {"application/json"})
	  Collection<Creneaux> getCreneauOrderBySondage(@PathVariable long id) {
	    return doodleService.selectCreneauOrderBySondage(id);
	  } 
     
     @RequestMapping(value = "/invitation/{id}", method = RequestMethod.GET, produces = {"application/json"})
	  List<String> getInvitationOrderBySondage(@PathVariable long id) {
	    return invitation.getInvitationList(id);
	  } 
     
    @RequestMapping(path = "/countslot/{idSlot}/{idSurvey}", method = RequestMethod.GET, produces = {"application/json"})
 	int getChoiceToUserAfterSondage(@PathVariable long idSlot, @PathVariable long idSurvey){
 		return doodleService.countUserOrderBySlotinCurrentSurvey(idSlot, idSurvey);
 	}
    
    @DeleteMapping("/deletepreference/{id}")
    public void deletePreference(@PathVariable long id) {
    	doodleService.deletePreference(id);
    }
    
    @DeleteMapping("/deleteallergie/{id}")
    public void deleteAllergie(@PathVariable long id) {
    	doodleService.deleteAllergie(id);
    }
     
}


