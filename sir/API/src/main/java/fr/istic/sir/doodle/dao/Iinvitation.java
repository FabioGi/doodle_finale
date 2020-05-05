package fr.istic.sir.doodle.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import fr.istic.sir.doodle.entities.Invitation;

@RepositoryRestResource
@CrossOrigin(origins="http://localhost:5000")  
public interface Iinvitation extends JpaRepository<Invitation, Long> {
	@Query("SELECT i.guest_mail FROM Invitation i WHERE i.sondage.id = ?1")
	List<String>getInvitationList(long id);

}
