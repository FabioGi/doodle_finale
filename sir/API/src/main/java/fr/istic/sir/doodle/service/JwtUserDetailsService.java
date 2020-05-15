package fr.istic.sir.doodle.service;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.istic.sir.doodle.dao.IuserRepository;
import fr.istic.sir.doodle.entities.Allergie;
import fr.istic.sir.doodle.entities.Preference;
//import fr.istic.sir.doodle.dao.UserDao;
import fr.istic.sir.doodle.entities.User;
//import fr.istic.sir.doodle.form.DAOUser;
import fr.istic.sir.doodle.form.UserDTO;



@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private IuserRepository userDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userDao.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}

	public User save(UserDTO user,List<Allergie> allergies, List<Preference> preferences) {
		User newUser = new User();
		newUser.setName(user.getName());
		newUser.setUsername(user.getUsername());
		newUser.setEmail(user.getEmail());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		for (Preference preference : preferences) {
			preference.setUser(newUser);
		}
		
		for (Allergie allergie : allergies) {
			allergie.setUser(newUser);
		}
		newUser.setAllergies(new ArrayList<>());
		newUser.getAllergies().addAll(allergies);
		newUser.setPreferences(new ArrayList<>());
		newUser.getPreferences().addAll(preferences);
		return userDao.save(newUser);
	}
}
