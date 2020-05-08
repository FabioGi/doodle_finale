package fr.istic.sir.doodle.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import fr.istic.sir.doodle.entities.Vote;
@RepositoryRestResource
@CrossOrigin(origins="http://localhost:5000")  
public interface IvoteRepository extends  JpaRepository<Vote, Long> {
	@Query("select count(v.user) From Vote v WHERE v.date.id=?1 and v.date.sondage.id=?2 ")
	int CountSlotOrderBySurvey(long idSlot,long idSurvey);

}
