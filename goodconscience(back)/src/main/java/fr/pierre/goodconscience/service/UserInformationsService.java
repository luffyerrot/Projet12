package fr.pierre.goodconscience.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.pierre.goodconscience.entity.UserInformations;
import fr.pierre.goodconscience.repository.UserInformationsRepository;

@Service
public class UserInformationsService {

	@Autowired
	UserInformationsRepository userInformationsRepository;

	Logger logger = LoggerFactory.getLogger(UserInformationsService.class);
	
	public UserInformations getById(Long id) {
		this.logger.debug("getById Call = " + id);
		if (userInformationsRepository.findById(id).isPresent()) {
			UserInformations userInformations = userInformationsRepository.findById(id).get();
			this.logger.debug("getById Return = " + userInformations);
			return userInformations;
		}
		return null;
	}
	
	public List<UserInformations> getAll() {
		List<UserInformations> usersInformations = userInformationsRepository.findAll();
		this.logger.debug("getAll Return = " + usersInformations);
		return usersInformations;
	}
	
	public UserInformations create(UserInformations userInformations) {
		this.logger.debug("create Call = " + userInformations);
		UserInformations userInformationsSave = userInformationsRepository.save(userInformations);
		this.logger.debug("create Return = " + userInformationsSave);
		return userInformationsSave;
	}
	
	public UserInformations update(UserInformations userInformations) {
		this.logger.debug("update Call = " + userInformations);
		if (userInformationsRepository.getById(userInformations.getId()) != null) {
			UserInformations userInformationsUpdate = userInformationsRepository.save(userInformations);
			this.logger.debug("update Return = " + userInformationsUpdate);
			return userInformationsUpdate;
		}
		this.logger.debug("update Return = " + null);
		return null;
	}
	
	public void delete(UserInformations userInformations) {
		this.logger.debug("delete Call = " + userInformations);
		if (userInformationsRepository.getById(userInformations.getId()) != null) {
			userInformationsRepository.delete(userInformations);
			return;
		}
		this.logger.debug("can't delete : " + userInformations);
	}
}
