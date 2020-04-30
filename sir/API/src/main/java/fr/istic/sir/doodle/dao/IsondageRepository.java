package fr.istic.sir.doodle.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import fr.istic.sir.doodle.entities.Sondage;
@RepositoryRestResource
@CrossOrigin(origins="http://localhost:5000")  
public interface IsondageRepository extends  JpaRepository<Sondage, Long> {
		
}
