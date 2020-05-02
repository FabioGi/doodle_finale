package fr.istic.sir.doodle.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.istic.sir.doodle.config.JwtTokenUtil;
import fr.istic.sir.doodle.entities.Allergie;
import fr.istic.sir.doodle.entities.Preference;
import fr.istic.sir.doodle.entities.User;
import fr.istic.sir.doodle.form.InscriptionForm;
import fr.istic.sir.doodle.form.JwtRequest;
import fr.istic.sir.doodle.form.JwtResponse;
import fr.istic.sir.doodle.form.UserDTO;
import fr.istic.sir.doodle.service.JwtUserDetailsService;

@RestController
@CrossOrigin(origins="http://localhost:5000")  
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}
	
//	@RequestMapping(value = "/register", method = RequestMethod.POST)
//	public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
//		return ResponseEntity.ok(userDetailsService.save(user));
//	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody InscriptionForm inscription) throws Exception {
		Objects.requireNonNull(inscription);
		UserDTO user = inscription.getUser();
		List<Allergie> allergies = inscription.getAllergies();
		List<Preference> preferences = inscription.getPreferences();
		Objects.requireNonNull(user);
		Objects.requireNonNull(allergies);
		Objects.requireNonNull(preferences);
		// return doodleService.createUser(user, allergies, preferences);
		return ResponseEntity.ok(userDetailsService.save(user, allergies, preferences));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}

//@RequestMapping(value = "/adduser", method = RequestMethod.POST)
//public boolean createUser(@RequestBody InscriptionForm inscription) {
//	Objects.requireNonNull(inscription);
//	User user = inscription.getUser();
//	List<Allergie> allergies = inscription.getAllergies();
//	List<Preference> preferences = inscription.getPreferences();
//	Objects.requireNonNull(user);
//	Objects.requireNonNull(allergies);
//	Objects.requireNonNull(preferences);
//	return doodleService.createUser(user, allergies, preferences);
//}