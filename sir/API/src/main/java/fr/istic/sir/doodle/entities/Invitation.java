package fr.istic.sir.doodle.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;
// import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data  @NoArgsConstructor @ToString
public class Invitation  {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	
	@JoinColumn(name = "id_sondage", referencedColumnName = "id")
	@ManyToOne
	@JsonProperty(access = Access.WRITE_ONLY)
	private Sondage sondage;
	private String guest_mail;
	

}
