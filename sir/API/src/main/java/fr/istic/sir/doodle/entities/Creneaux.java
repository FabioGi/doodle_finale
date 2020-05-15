package fr.istic.sir.doodle.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
public class Creneaux {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="date_selectionnee")
	@Temporal(TemporalType.DATE)
	private Date date;
	
	// @Temporal(TemporalType.TIME)
	private String heure_debut;
	
	// @Temporal(TemporalType.TIME)
	private String heure_fin;
	
	@Column(name = "pause", nullable = false)
	private boolean pause = false ;
	
	// private boolean isChecked = false;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_sondage", referencedColumnName = "id")
    @JsonProperty(access = Access.WRITE_ONLY)
	private Sondage sondage;
	
	@OneToOne(mappedBy = "dated")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Reunion reunion;
	
	@OneToMany(mappedBy = "date")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Collection<Vote> votes;
	
	@Column(name = "valided", nullable = false)
	private boolean valided = false;
	
	
	
	
}
