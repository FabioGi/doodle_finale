package fr.istic.sir.doodle.entities;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
public class Sondage {

	@Id
	@GeneratedValue
	private long id;
	private String titre;
	private String lieu;
	
	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,mappedBy = "sondage")
	private Collection<Creneaux> dated;
	
	
    @JoinColumn(name = "id_user", referencedColumnName = "email")
    @OneToOne
    private User user ;

	
}
