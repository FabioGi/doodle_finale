package fr.istic.sir.doodle.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import fr.istic.sir.doodle.entities.Creneaux;
import fr.istic.sir.doodle.entities.User;

@RepositoryRestResource
@CrossOrigin(origins="http://localhost:5000")  
public interface IuserRepository extends JpaRepository<User, String> {
	@Query("select u from User u join u.votes v where v.date.id = ?1 and v.date.sondage.id = ?2" )
	Set<User>findAttendanceList(long idCreneau,long idSondage);
	
	@Query("select u from User u join u.votes v where  v.date.id <> ?1 and v.date.sondage.id = ?2")
	Set<User>findAbsenceList(long idCreneau,long idSondage);

	@Query("select u from User u  where u.username = ?1 ")
	User findByUsername(String username);
	
	@Query("select u from User u  where u.email = ?1 ")
	User findByEmail(String email);
}