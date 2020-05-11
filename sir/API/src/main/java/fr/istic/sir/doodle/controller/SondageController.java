package fr.istic.sir.doodle.controller;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
import fr.istic.sir.doodle.form.CreneauxForm;
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
	
	
	// @GetMapping("/user/vote")
	// public Creneaux obtainsCreneauxById(@RequestParam("id") long id) {
	//	icreCreneauxReposirory.findAll().forEach(c->{
	//		ArrayList listCreneauxById = new ArrayList(); 
	//	long id1;
	//	id1 = c.getId();
	//	if(id1 == id) {
	//		listCreneauxById.add(id);
	//	}
	//			
	//	});
	//	return null;
	//	}
	
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
	
//	@RequestMapping(value = "/test", method = RequestMethod.GET, produces = {"application/json"})
//	User getTest() {
//		return doodleService.createUser();
//	}
	
	/***
	 * POST REQUEST 
	 */
	
//	@RequestMapping(value = "/absencelist", method = RequestMethod.POST)
//	    public Comment createComment(@PathVariable (value = "postId") Long postId,
//	                                 @Valid @RequestBody Comment comment) {
//	        return postRepository.findById(postId).map(post -> {
//	            comment.setPost(post);
//	            return commentRepository.save(comment);
//	        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
//	    }
	
//	@RequestMapping(value = "/adduser", method = RequestMethod.POST)
//    public Comment createComment(@PathVariable (value = "postId") Long postId,
//                                 @Valid @RequestBody Comment comment) {
//        return postRepository.findById(postId).map(post -> {
//            comment.setPost(post);
//            return commentRepository.save(comment);
//        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
//    }
	
//	@RequestMapping(value = "/adduser", method = RequestMethod.POST)
//	public boolean createUser(@RequestBody InscriptionForm inscription) {
//		Objects.requireNonNull(inscription);
//		UserDTO user = inscription.getUser();
//		List<Allergie> allergies = inscription.getAllergies();
//		List<Preference> preferences = inscription.getPreferences();
//		Objects.requireNonNull(user);
//		Objects.requireNonNull(allergies);
//		Objects.requireNonNull(preferences);
//		return doodleService.createUser(user, allergies, preferences);
//	}
	
	@RequestMapping(value = "/addsondage", method = RequestMethod.POST)
	public Sondage createSondage(@RequestBody SondageForm sondageForm) {
		Objects.requireNonNull(sondageForm);
		SondageDTO sondage = sondageForm.getSondage();
		List<Creneaux> creneaux = sondageForm.getCreneau();
		List<String> mails = sondageForm.getMails();
		Objects.requireNonNull(creneaux);
		Objects.requireNonNull(sondage);
		Objects.requireNonNull(mails);
		return doodleService.createSondage(sondage,creneaux,mails);
	}
	
//	@RequestMapping(value = "/addcreneau", method = RequestMethod.POST)
//	public boolean createCreneau(@RequestBody CreneauxForm creneauForm) {
//		Objects.requireNonNull(creneauForm);
//		Creneaux creneaux = creneauForm.getCreneaux();
//		int  idSondage = creneauForm.getIdSondage();
//		Objects.requireNonNull(creneaux);
//		Objects.requireNonNull(idSondage);
//		return doodleService.createCrenaux(idSondage, creneaux);
//	}
	
	@RequestMapping(value = "/addreunion", method = RequestMethod.POST)
	 public boolean createMeeting(@RequestBody 	Reunion reunion) {
		Objects.requireNonNull(reunion);
		return doodleService.createReunion(reunion);
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
		List<String> emails = vs.getEmails();
		return doodleService.validedSondage(idCreneau,emails);
	}
	
	/**
	 * SENT MAIL
	 */
	
	    @ResponseBody
	    @RequestMapping("/mail")
	    public void sendSimpleEmail(@RequestBody List<String>mails){
	          doodleService.sendMultipleMail(mails);
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
     
}


