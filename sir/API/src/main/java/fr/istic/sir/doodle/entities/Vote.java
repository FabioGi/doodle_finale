package fr.istic.sir.doodle.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class Vote {
	
	@Id
	@GeneratedValue
	private long id ;
    
	@JoinColumn(name = "id_date", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY)
	// @JsonProperty(access = Access.WRITE_ONLY)
	private Creneaux date;
	@JoinColumn(name = "id_user", referencedColumnName = "email")
	@ManyToOne(fetch = FetchType.LAZY)
	//@JsonProperty(access = Access.WRITE_ONLY)
	private User user;
		
	
	
	// @JoinTable(name = "PARTICIPER", joinColumns = @JoinColumn(name = "email"), inverseJoinColumns = @JoinColumn(name = "id_dated"))

}
