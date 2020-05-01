package fr.istic.sir.doodle.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@SuppressWarnings("serial")
@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Allergie implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//  @Column(name="mdp",nullable=false,length=8,updatable=false)
	private long id ;
	
	private String name ;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_user", referencedColumnName = "email")
	private User user;

}
