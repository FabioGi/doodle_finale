package fr.istic.sir.doodle.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * 
 * @author Fabrice Kadio
 * @author Othniel Konan
 *
 */

 @Entity
 @Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Reunion {
	@Id
	private String intitule;
	
	private String resume;
	
	@OneToOne
	private Creneaux dated ;
	
	

}
