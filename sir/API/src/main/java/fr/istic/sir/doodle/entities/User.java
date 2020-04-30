package fr.istic.sir.doodle.entities;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class User {

	@Id
	@Email
    @Size(min = 5, max = 254)
    @Column(length = 254, unique = true)
	private String email;
	
	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,mappedBy = "user")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Collection<Preference> preferences;
	
	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,mappedBy = "user")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Collection<Allergie> allergies;
	
	@OneToOne(mappedBy = "user")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Sondage sondage;
	
	private String nom;
	private String prenom;
	private String web;
	
	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, mappedBy = "user")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Collection<Vote> votes;
	
	@Column
	@JsonIgnore
	private String password;
//	public Collection<Vote> getVote() {
//		return vote;
//	}
//	public void setVote(Collection<Vote> vote) {
//		this.vote = vote;
//	}
//	@OneToMany(mappedBy = "user")
//	public Collection<Allergie> getAllergies() {
//		return allergies;
//	}
//	public void setAllergies(Collection<Allergie> allergies) {
//		this.allergies = allergies;
//	}
	
//	public long getEmail() {
//		return email;
//	}
//	public void setEmail(long email) {
//		this.email = email;
//	}
//	public String getNom() {
//		return nom;
//	}
//	public void setNom(String nom) {
//		this.nom = nom;
//	}
//	public String getPrenom() {
//		return prenom;
//	}
//	public void setPrenom(String prenom) {
//		this.prenom = prenom;
//	}
//	public String getWeb() {
//		return web;
//	}
//	public void setWeb(String web) {
//		this.web = web;
//	}
//	
//	public Sondage getSondage() {
//		return sondage;
//	}
//	public void setSondage(Sondage sondage) {
//		this.sondage = sondage;
//	}
//
//	
//	public Collection<Preference> getPreferences() {
//		return preferences;
//	}
//
//	public void setPreferences(Collection<Preference> preferences) {
//		this.preferences = preferences;
//	}


//	@Transient
//	@OneToMany(mappedBy = "participant")
//	public Collection<Participer> getParticiper() {
//		return participer;
//	}

//	public void setParticiper(Collection<Participer> participer) {
//		this.participer = participer;
//	}
}
