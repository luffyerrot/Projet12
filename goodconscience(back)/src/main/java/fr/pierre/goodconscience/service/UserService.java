package fr.pierre.goodconscience.service;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.pierre.goodconscience.entity.Categorie;
import fr.pierre.goodconscience.entity.Role;
import fr.pierre.goodconscience.entity.User;
import fr.pierre.goodconscience.repository.EnterpriseRepository;
import fr.pierre.goodconscience.repository.RoleRepository;
import fr.pierre.goodconscience.repository.UserRepository;
import fr.pierre.goodconscience.security.util.jwtUtil;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	EnterpriseRepository enterpriseRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	jwtUtil jwtUtil;

	Logger logger = LoggerFactory.getLogger(UserService.class);
	
	public User getById(Long id) {
		this.logger.debug("getById Call = " + id);
		User user = userRepository.findById(id).get();
		this.logger.debug("getById Return = " + user);
		return user;
	}
	
	public List<User> getAll() {
		List<User> users = userRepository.findAll();
		this.logger.debug("getAll Return = " + users);
		return users;
	}
	
	public User create(User user) {
		this.logger.debug("create Call = " + user);
		if (!enterpriseRepository.findByEmail(user.getEmail()).isPresent()) {
			user.setPassword(this.passwordEncoder.encode(user.getPassword()));
			user.setLinkimg("/assets/images/users/unknow.png");
			Role role = roleRepository.findByName("ROLE_USER");
			this.logger.debug("create Role = " + role);
			user.setRoles(Arrays.asList(role));
			User userSave = userRepository.save(user);
			this.logger.debug("create Return = " + userSave);
			return userSave;
		}
		this.logger.debug("create Return = " + null);
		return null;
	}
	
	public User createForTest(User user) {
		this.logger.debug("create Call = " + user);
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		user.setLinkimg("/assets/images/users/unknow.png");
		User userSave = userRepository.save(user);
		this.logger.debug("create Return = " + userSave);
		return userSave;
	}
	
	public User update(User user) {
		this.logger.debug("update Call = " + user);
		if (userRepository.getById(user.getId()) != null) {
			User userUpdate = userRepository.save(user);
			this.logger.debug("update Return = " + userUpdate);
			return userUpdate;
		}
		this.logger.debug("update Return = " + null);
		return null;
	}
	
	public void delete(User user) {
		this.logger.debug("delete Call = " + user);
		if (userRepository.getById(user.getId()) != null) {
			userRepository.delete(user);
			return;
		}
		this.logger.debug("can't delete : " + user);
	}
	
	public User addRestriction(String email, Categorie categorie) {
		User user = this.findByEmail(email);
		if (user.getCategories() != null && !user.getCategories().contains(categorie)) {
			user.getCategories().add(categorie);
			return this.update(user);
		}
		return null;
	}
	
	public User findByEmail(String email) {
		this.logger.debug("findByEmail Call = " + email);
		if (userRepository.findByEmail(email).isPresent()) {
			User userReturn = userRepository.findByEmail(email).get();
			this.logger.debug("findByEmail Return = " + userReturn);
			return userReturn;
		}
		this.logger.debug("findByEmail Return = " + null);
		return null;
	}
	
	public Boolean login(String email, String password) {
		this.logger.debug("login Call = " + email + " -- " + password);
		if (passwordEncoder.matches(password, findByEmail(email).getPassword())) {
			this.logger.debug("login Return = " + true);
			return true;
		}
		this.logger.debug("login Return = " + false);
		return false;
	}
	
	public User getUserFromToken(String token) {
		this.logger.debug("getUserFromToken Call = " + token);
		String email = jwtUtil.extractUsername(token.substring(7));
		if (this.findByEmail(email) != null) {
			User user = this.findByEmail(email);
			this.logger.debug("getUserFromToken Return = " + user);
			return user;
		}
		this.logger.debug("getUserFromToken Null = " + null);
		return null;
	}
}
