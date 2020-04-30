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
	@Query("select u from User u join u.votes v where v.date = ?1")
	Set<User>findAttendanceList(Creneaux c);
	
	@Query("select u from User u join u.votes v where  v.date <> ?1")
	Set<User>findAbsenceList(Creneaux c);

	@Query("select u from User u  where u.prenom = ?1 ")
	User findByUsername(String username);
}