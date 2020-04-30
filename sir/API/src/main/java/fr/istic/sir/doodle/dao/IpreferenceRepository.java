package fr.istic.sir.doodle.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import fr.istic.sir.doodle.entities.Preference;

@RepositoryRestResource
@CrossOrigin(origins="http://localhost:5000")  
public interface IpreferenceRepository extends  JpaRepository<Preference, Long> {
	@Query("SELECT p FROM Preference p WHERE p.user.id = ?1")
	List<Preference>getPreferenceOrderByUser(String id);

}
