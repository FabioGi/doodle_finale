package fr.istic.sir.doodle.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
import fr.istic.sir.doodle.entities.Preference;
import fr.istic.sir.doodle.entities.Reunion;
import fr.istic.sir.doodle.entities.Sondage;
import fr.istic.sir.doodle.entities.User;
import fr.istic.sir.doodle.entities.Vote;
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
	
	Logger logger = Logger.getLogger("logger");
	
	@Override
	public List<Vote> getAllVoteOrderBydate() {
		return rVote.findAll();
	}

	@Transactional
	@Override
	// ok
	public void validedSondage(long idCreneau) {
		Creneaux creneau = rCreneau.findById(new Long(idCreneau)).get();
		creneau.setValided(true);
		// this.sendMailertoUsersWithDateOfMeeting();
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
	public void sendMailertoUsersWithDateOfMeeting() {
		// envoie de mail aux utilisateurs avec: 
		// - clear code pour entrer au batiment
		// si creneau contient pause ou dejeuner envoie de lien pour preciser les preferences ou allergies (unique)
		// pour la pause if(creneau.isPause())
		// lien vers le pad(avec liste de presence et liste d'absences)
		// titre de la reunion 
		// date retenue
	}

	@Override
	public void createPad() {
		// TODO Auto-generated method stub
	}

	@Override
	// ok
	public Set<User> attendanceList() {
		Creneaux c = this.selectCrenauxforMeeting();
		return rUser.findAttendanceList(c);
	}

	@Override
	// ok
	public Set<User> absenceList() {
		Creneaux c = this.selectCrenauxforMeeting();
		return rUser.findAbsenceList(c);
	}

	@Transactional
	@Override
	// ok
	 public boolean createUser(User user, List<Allergie> allergies, List<Preference> preferences) {
		for (Preference preference : preferences) {
			preference.setUser(user);
		}
		
		for (Allergie allergie : allergies) {
			allergie.setUser(user);
		}
		user.setAllergies(new ArrayList<>());
		user.getAllergies().addAll(allergies);
		user.setPreferences(new ArrayList<>());
		user.getPreferences().addAll(preferences);
		User response = rUser.save(user) ;
		return response != null;
	}
	

	@Transactional
	@Override
	// ok
	public boolean createSondage(String idUser, Sondage sondage) {
		User user = rUser.findById(idUser).get();
		sondage.setUser(user);
		Sondage response = rSondage.save(sondage);
		return response != null ;
		
	}

	@Transactional
	@Override
	// ok
	public boolean choseDate(String idUser,long idCreneau) {
		User user = rUser.findById(idUser).get();
		Creneaux creneau = rCreneau.findById((long) idCreneau).get();
		Vote vote = new Vote();
		vote.setDate(creneau);
		vote.setUser(user);
		return rVote.save(vote) != null;
		
//		Creneaux creneau2 = rCreneau.findById((long) 2).get();
//		Vote vote2 = new Vote();
//		vote2.setDate(creneau2);
//		vote2.setUser(user);
//		rVote.save(vote2);
//		
//		User user2 = rUser.findById("hervefab007@gmail.com").get();
//		Creneaux creneau3 = rCreneau.findById((long) 4).get();
//		Vote vote3 = new Vote();
//		vote3.setDate(creneau3);
//		vote3.setUser(user2);
//		rVote.save(vote3);
//		
//		Vote vote4 = new Vote();
//		vote4.setDate(creneau);
//		vote4.setUser(user2);
//		rVote.save(vote4);
		
	}

	@Transactional
	@Override
	// ok
	public boolean createCrenaux(int idSondage, Creneaux creneau ) {
		Sondage sondage = rSondage.findById(new Long(idSondage)).get();
		creneau.setSondage(sondage);
		// gestion de la pause
		// if(creneau.getHeure_debut() >= 12h && creneau.getHeure_debut()  creneau.setPause(true) .)
		return rCreneau.save(creneau)!= null;
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
	// ok
	public Creneaux selectCrenauxforMeeting() {
		Creneaux dateForMeeting = rCreneau.findCrenauByStatus(true);
		return dateForMeeting ;
	}

	@Override
	// ok
	public List<Creneaux> getChoiceToUserAfterSondage(Sondage s, User u) {
		return rCreneau.getUserChoiceForSondage(s, u);
	}

	@Override
	public List<Creneaux> getAllVotesOrderBySondage(Sondage s) {
		return rCreneau.getAllResponseForSondage(s);
	}

	@Override
	public void sendMailToUserAfterSondageCreated() {
		// avec lien unique du sondage
		
	}

	
	public List<String>getUsersMail(){
		return rUser.findAll().stream().map(user -> user.getEmail())
									   .collect(Collectors.toList());
	}
	
}

//List<EntityModel<Order>> orders = orderRepository.findAll().stream()
//.map(assembler::toModel)
//.collect(Collectors.toList());

//{
//	   "user":{
//	      "email":"kkonan@gmail.com",
//	      "nom":"konan",
//	      "prenom":"othniel",
//	      "web":"kajimoto.fr"
//	   },
//	   "preferences":[
//	      {
//	         "id":"null",
//	         "name":"ananas"
//	      },
//	      {
//	         "id":"null",
//	         "name":"papaye"
//	      }
//	   ],
//	   "allergies":[
//	      {
//	         "id":"null",
//	         "name":"mangue"
//	      },
//	      {
//	         "id":"null",
//	         "name":"fromage"
//	      }
//	   ]
//	}

//@Transactional
//@Override
//// test
// public User createUserTest() {
//	User user = new User();
//	Preference preference1 = new Preference();
//	Preference preference2 = new Preference();
//	Allergie allergie1 = new Allergie();
//	Allergie allergie2 = new Allergie();
//	
//	user.setEmail("hervefab007@gmail.com");
//	user.setNom("Fabrice");
//	user.setPrenom("kadio");
//	user.setWeb("kajimoto.fr");
//		
//	allergie1.setName("poyo");
//	allergie1.setUser(user);
//	allergie2.setName("mangue");
//	allergie2.setUser(user);
//	
//	preference1.setName("banane");
//	preference2.setName("pomme");
//	preference1.setUser(user);
//	preference2.setUser(user);
//	
//	user.setAllergies(new ArrayList<>());
//	user.getAllergies().add(allergie1);
//	user.getAllergies().add(allergie2);
//	
//	user.setPreferences(new ArrayList<>());
//	user.getPreferences().add(preference1);
//	user.getPreferences().add(preference2);
////	rAllergie.save(allergie1);
////	rAllergie.save(allergie2);
////	rPreference.save(preference1);
////	rPreference.save(preference2);
//	rUser.save(user);
//	// System.out.println("ok");
//	
//	User user1 = new User();
//	Preference preference3 = new Preference();
//	Preference preference4 = new Preference();
//	Allergie allergie3 = new Allergie();
//	Allergie allergie4 = new Allergie();
//	
//	user1.setEmail("mariakeyla225@gmail.com");
//	user1.setNom("keyla");
//	user1.setPrenom("kadio");
//	user1.setWeb("kajimoto.fr");
//	
//	allergie3.setName("manioc");
//	allergie3.setUser(user1);
//	allergie4.setName("fraise");
//	allergie4.setUser(user1);
//	
//	preference3.setName("abricot");
//	preference4.setName("orange");
//	preference3.setUser(user1);
//	preference4.setUser(user1);
//	
//	user1.setAllergies(new ArrayList<>());
//	user1.getAllergies().add(allergie3);
//	user1.getAllergies().add(allergie4);
//	
//	user1.setPreferences(new ArrayList<>());
//	user1.getPreferences().add(preference3);
//	user1.getPreferences().add(preference4);
////	rAllergie.save(allergie3);
////	rAllergie.save(allergie4);
////	rPreference.save(preference3);
////	rPreference.save(preference4);
//	rUser.save(user1);
//	
//	User user2 = new User();
//	Preference preference5 = new Preference();
//	Preference preference6 = new Preference();
//	Allergie allergie5 = new Allergie();
//	Allergie allergie6 = new Allergie();
//	
//	user2.setEmail("kkonan@gmail.com");
//	user2.setNom("konan");
//	user2.setPrenom("othniel");
//	user2.setWeb("kajimoto.fr");
//	
//	allergie5.setName("burguer");
//	allergie5.setUser(user2);
//	allergie6.setName("carotte");
//	allergie6.setUser(user);
//	
//	preference5.setName("ananas");
//	preference6.setName("glace");
//	preference5.setUser(user2);
//	preference6.setUser(user2);
//	
//	user2.setAllergies(new ArrayList<>());
//	user2.getAllergies().add(allergie5);
//	user2.getAllergies().add(allergie6);
//	
//	user2.setPreferences(new ArrayList<>());
//	user2.getPreferences().add(preference5);
//	user2.getPreferences().add(preference6);
////	rAllergie.save(allergie1);
////	rAllergie.save(allergie2);
////	rPreference.save(preference1);
////	rPreference.save(preference2);
//	rUser.save(user2);
//	return user2;
//	
//}

// https://www.javatpoint.com/angular-spring-crud-example