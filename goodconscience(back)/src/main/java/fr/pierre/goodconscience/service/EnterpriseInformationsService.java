package fr.pierre.goodconscience.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.pierre.goodconscience.entity.EnterpriseInformations;
import fr.pierre.goodconscience.repository.EnterpriseInformationsRepository;

@Service
public class EnterpriseInformationsService {

	@Autowired
	EnterpriseInformationsRepository enterpriseInformationsRepository;

	Logger logger = LoggerFactory.getLogger(EnterpriseInformationsService.class);
	
	public EnterpriseInformations getById(Long id) {
		this.logger.debug("getById Call = " + id);
		EnterpriseInformations enterpriseInformations = enterpriseInformationsRepository.findById(id).get();
		this.logger.debug("getById Return = " + enterpriseInformations);
		return enterpriseInformations;
	}
	
	public List<EnterpriseInformations> getAll() {
		List<EnterpriseInformations> enterprisesInformations = enterpriseInformationsRepository.findAll();
		this.logger.debug("getAll Return = " + enterprisesInformations);
		return enterprisesInformations;
	}
	
	public EnterpriseInformations create(EnterpriseInformations enterpriseInformations) {
		this.logger.debug("create Call = " + enterpriseInformations);
		EnterpriseInformations enterpriseInformationsSave = enterpriseInformationsRepository.save(enterpriseInformations);
		this.logger.debug("create Return = " + enterpriseInformationsSave);
		return enterpriseInformationsSave;
	}
	
	public EnterpriseInformations update(EnterpriseInformations enterpriseInformations) {
		this.logger.debug("update Call = " + enterpriseInformations);
		if (enterpriseInformationsRepository.getById(enterpriseInformations.getId()) != null) {
			EnterpriseInformations enterpriseInformationsUpdate = enterpriseInformationsRepository.save(enterpriseInformations);
			this.logger.debug("update Return = " + enterpriseInformationsUpdate);
			return enterpriseInformationsUpdate;
		}
		this.logger.debug("update Return = " + null);
		return null;
	}
	
	public void delete(EnterpriseInformations enterpriseInformations) {
		this.logger.debug("delete Call = " + enterpriseInformations);
		if (enterpriseInformationsRepository.getById(enterpriseInformations.getId()) != null) {
			enterpriseInformationsRepository.delete(enterpriseInformations);
			return;
		}
		this.logger.debug("can't delete : " + enterpriseInformations);
	}
}
