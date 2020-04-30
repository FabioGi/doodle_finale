package fr.istic.sir.doodle.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import fr.istic.sir.doodle.entities.Allergie;

@RepositoryRestResource
@CrossOrigin(origins="http://localhost:5000")  
public interface IallergieRepository extends JpaRepository<Allergie, Long> {
	@Query("SELECT a FROM Allergie a WHERE a.user.id = ?1")
	List<Allergie>getAllergieOrderByUser(String id);
}
