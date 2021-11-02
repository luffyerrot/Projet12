package fr.pierre.goodconscience.service;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.pierre.goodconscience.entity.Enterprise;
import fr.pierre.goodconscience.entity.Role;
import fr.pierre.goodconscience.repository.EnterpriseInformationsRepository;
import fr.pierre.goodconscience.repository.EnterpriseRepository;
import fr.pierre.goodconscience.repository.RoleRepository;
import fr.pierre.goodconscience.repository.UserRepository;

@Service
public class EnterpriseService {

	@Autowired
	EnterpriseRepository enterpriseRepository;
	@Autowired
	EnterpriseInformationsRepository enterpriseInformationsRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	PasswordEncoder passwordEncoder;

	Logger logger = LoggerFactory.getLogger(EnterpriseService.class);
	
	public Enterprise getById(Long id) {
		this.logger.debug("getById Call = " + id);
		Enterprise enterprise = enterpriseRepository.findById(id).get();
		this.logger.debug("getById Return = " + enterprise);
		return enterprise;
	}
	
	public List<Enterprise> getAll() {
		List<Enterprise> enterprises = enterpriseRepository.findAll();
		this.logger.debug("getAll Return = " + enterprises);
		return enterprises;
	}
	
	public Enterprise create(Enterprise enterprise) {
		this.logger.debug("create Call = " + enterprise);
		if (!userRepository.findByEmail(enterprise.getEmail()).isPresent()) {
			enterprise.setPassword(this.passwordEncoder.encode(enterprise.getPassword()));
			enterprise.setLinkimg("/assets/images/users/unknow.png");
			Role role = roleRepository.findByName("ROLE_USER");
			this.logger.debug("create Role = " + role);
			enterprise.setRoles(Arrays.asList(role));
			Enterprise enterpriseSave = enterpriseRepository.save(enterprise);
			this.logger.debug("create Return = " + enterpriseSave);
			return enterpriseSave;
		}
		this.logger.debug("create Return = " + null);
		return null;
	}
	
	public Enterprise createForTest(Enterprise enterprise) {
		this.logger.debug("create Call = " + enterprise);
		enterprise.setPassword(this.passwordEncoder.encode(enterprise.getPassword()));
		enterprise.setLinkimg("/assets/images/users/unknow.png");
		Enterprise enterpriseSave = enterpriseRepository.save(enterprise);
		this.logger.debug("create Return = " + enterpriseSave);
		return enterpriseSave;
	}
	
	public Enterprise update(Enterprise enterprise) {
		this.logger.debug("update Call = " + enterprise);
		if (enterpriseRepository.getById(enterprise.getId()) != null) {
			Enterprise enterpriseUpdate = enterpriseRepository.save(enterprise);
			this.logger.debug("update Return = " + enterpriseUpdate);
			return enterpriseUpdate;
		}
		this.logger.debug("update Return = " + null);
		return null;
	}
	
	public void delete(Enterprise enterprise) {
		this.logger.debug("delete Call = " + enterprise);
		if (enterpriseRepository.getById(enterprise.getId()) != null) {
			enterpriseRepository.delete(enterprise);
			return;
		}
		this.logger.debug("can't delete : " + enterprise);
	}
	
	public Enterprise findByEmail(String email) {
		this.logger.debug("findByEmail Call = " + email);
		if (enterpriseRepository.findByEmail(email).isPresent()) {
			Enterprise enterpriseReturn = enterpriseRepository.findByEmail(email).get();
			this.logger.debug("findByEmail Return = " + enterpriseReturn);
			return enterpriseReturn;
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
}
