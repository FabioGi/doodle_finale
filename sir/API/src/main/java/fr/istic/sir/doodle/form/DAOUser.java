//package fr.istic.sir.doodle.form;
//
//
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.Size;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.ToString;
//
//@Entity
//@Table(name = "userss")
//@Data @AllArgsConstructor @NoArgsConstructor @ToString
//public class DAOUser {
//
//	@Id
//	@Email
//    @Size(min = 5, max = 254)
//    @Column(length = 254, unique = true)
//	private String email;
//	@Column
//	private String username;
//	@Column
//	@JsonIgnore
//	private String password;
//
////	public String getUsername() {
////		return username;
////	}
////
////	public void setUsername(String username) {
////		this.username = username;
////	}
////
////	public String getPassword() {
////		return password;
////	}
////
////	public void setPassword(String password) {
////		this.password = password;
////	}
//
//}