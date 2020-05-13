package fr.istic.sir.doodle.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import fr.istic.sir.doodle.entities.Sondage;
@RepositoryRestResource
@CrossOrigin(origins="http://localhost:5000")  
public interface IsondageRepository extends  JpaRepository<Sondage, Long> {
	@Query("SELECT s FROM Sondage s WHERE s.user.email = ?1 order by s desc")
	List<Sondage>getSurveyCreatedByUsers(String email);
	
	@Query("SELECT count(s) FROM Sondage s join s.dated d WHERE d.valided = true AND s.user.email = ?1 order by s desc")
	int countSurveyWhichAreValided(String email);
		
}
