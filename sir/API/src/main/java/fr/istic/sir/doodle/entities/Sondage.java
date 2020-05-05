package fr.istic.sir.doodle.entities;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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
	private String resume;
	
	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,mappedBy = "sondage")
	private Collection<Creneaux> dated;
	
	
    @JoinColumn(name = "id_user", referencedColumnName = "email")
    @ManyToOne
    private User user ;
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,mappedBy = "sondage")
    @JsonProperty(access = Access.WRITE_ONLY)
    private Collection<Invitation> invitations;
    

	
}
