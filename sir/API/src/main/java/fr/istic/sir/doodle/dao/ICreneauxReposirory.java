package fr.istic.sir.doodle.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import fr.istic.sir.doodle.entities.Creneaux;
import fr.istic.sir.doodle.entities.Sondage;
import fr.istic.sir.doodle.entities.User;

@RepositoryRestResource
@CrossOrigin(origins="http://localhost:5000")  
public interface ICreneauxReposirory extends  JpaRepository<Creneaux, Long> {
	@Query("SELECT c FROM Creneaux c WHERE c.valided = ?1")
	Creneaux findCrenauByStatus(Boolean status);
	
	@Query("Select c from Creneaux c join c.votes v where v.date.sondage = ?1 and v.user = ?2 ")
	List<Creneaux> getUserChoiceForSondage(Sondage s, User u);
	
	@Query("Select c from Creneaux c join c.votes v where v.date.sondage = ?1")
	List<Creneaux> getAllResponseForSondage(Sondage s);
}
