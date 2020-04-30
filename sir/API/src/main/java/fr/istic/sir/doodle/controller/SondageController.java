package fr.istic.sir.doodle.controller;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.istic.sir.doodle.dao.ICreneauxReposirory;
import fr.istic.sir.doodle.dao.IallergieRepository;
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
import fr.istic.sir.doodle.form.InscriptionForm;
import fr.istic.sir.doodle.form.SondageForm;
import fr.istic.sir.doodle.service.interfaces.IdoodleService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="http://localhost:5000")  
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
	@RequestMapping(value = "/allergies", method = RequestMethod.GET, produces = {"application/json"})
	  List<Allergie> allAllergies() {
	    return rAllergie.findAll();
	  }
	@RequestMapping(value = "/creneaux", method = RequestMethod.GET, produces = {"application/json"})
	  List<Creneaux> allCrenaux() {
	    return rCreneau.findAll();
	  }
	@RequestMapping(value = "/preferences", method = RequestMethod.GET, produces = {"application/json"})
	  List<Preference> allPreference() {
	    return rPreference.findAll();
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
	List<Creneaux> getChoiceToUserAfterSondage(@PathVariable String idUser, @PathVariable long idSondage){
		User u = rUser.findById(idUser).get();
		Sondage s = rSondage.findById(new Long(idSondage)).get();
		return doodleService.getChoiceToUserAfterSondage(s, u);
	}
	
	@RequestMapping(value = "/attendlist", method = RequestMethod.GET, produces = {"application/json"})
	Set<User> getAttendList(){
		return doodleService.attendanceList();
	}
	
	@RequestMapping(value = "/absencelist", method = RequestMethod.GET, produces = {"application/json"})
	Set<User> getAbsenceList(){
		return doodleService.absenceList();
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
	
	@RequestMapping(value = "/adduser", method = RequestMethod.POST)
	public boolean createUser(@RequestBody InscriptionForm inscription) {
		Objects.requireNonNull(inscription);
		User user = inscription.getUser();
		List<Allergie> allergies = inscription.getAllergies();
		List<Preference> preferences = inscription.getPreferences();
		Objects.requireNonNull(user);
		Objects.requireNonNull(allergies);
		Objects.requireNonNull(preferences);
		return doodleService.createUser(user, allergies, preferences);
	}
	
	@RequestMapping(value = "/addsondage", method = RequestMethod.POST)
	public boolean createSondage(@RequestBody SondageForm sondageForm) {
		Objects.requireNonNull(sondageForm);
		Sondage sondage = sondageForm.getSondage();
		String idUser = sondageForm.getIdUser(); 
		Objects.requireNonNull(idUser);
		Objects.requireNonNull(sondage);
		return doodleService.createSondage(idUser, sondage);
	}
	
	@RequestMapping(value = "/addcreneau", method = RequestMethod.POST)
	public boolean createCreneau(@RequestBody CreneauxForm creneauForm) {
		Objects.requireNonNull(creneauForm);
		Creneaux creneaux = creneauForm.getCreneaux();
		int  idSondage = creneauForm.getIdSondage();
		Objects.requireNonNull(creneaux);
		Objects.requireNonNull(idSondage);
		return doodleService.createCrenaux(idSondage, creneaux);
	}
	
	@RequestMapping(value = "/addreunion", method = RequestMethod.POST)
	 public boolean createMeeting(@RequestBody 	Reunion reunion) {
		Objects.requireNonNull(reunion);
		return doodleService.createReunion(reunion);
	}
	
	@RequestMapping(path = "/chosecreneau/{idUser}/{idCreneau}", method = RequestMethod.POST)
	 public boolean choseDate(@PathVariable  String idUser, @PathVariable long idCreneau) {
		return doodleService.choseDate(idUser, idCreneau);
	}
	
	/****
	 * PUT REQUEST FOR API
	 */
	@RequestMapping(path = "/validesondage/{id}", method = RequestMethod.PUT)
	public void validedSondage(@PathVariable  long id) {
		doodleService.validedSondage(id);
	}
	
	
	
}


