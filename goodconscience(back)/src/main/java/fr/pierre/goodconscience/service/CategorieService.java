package fr.pierre.goodconscience.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.pierre.goodconscience.entity.Categorie;
import fr.pierre.goodconscience.repository.CategorieRepository;

@Service
public class CategorieService {

	@Autowired
	CategorieRepository categorieRepository;

	Logger logger = LoggerFactory.getLogger(CategorieService.class);
	
	public Categorie getById(Long id) {
		this.logger.debug("getById Call = " + id);
		Categorie categorie = categorieRepository.findById(id).get();
		this.logger.debug("getById Return = " + categorie);
		return categorie;
	}
	
	public List<Categorie> getAll() {
		List<Categorie> categories = categorieRepository.findAll();
		this.logger.debug("getAll Return = " + categories);
		return categories;
	}
	
	public Categorie create(Categorie categorie) {
		this.logger.debug("create Call = " + categorie);
		Categorie categorieSave = categorieRepository.save(categorie);
		this.logger.debug("create Return = " + categorieSave);
		return categorieSave;
	}
	
	public Categorie update(Categorie categorie) {
		this.logger.debug("update Call = " + categorie);
		if (categorieRepository.getById(categorie.getId()) != null) {
			Categorie categorieSave = categorieRepository.save(categorie);
			this.logger.debug("update Return = " + categorieSave);
			return categorieSave;
		}
		this.logger.debug("update Return = " + null);
		return null;
	}
	
	public void delete(Categorie categorie) {
		this.logger.debug("delete Call = " + categorie);
		if (categorieRepository.getById(categorie.getId()) != null) {
			categorieRepository.delete(categorie);
			return;
		}
		this.logger.debug("can't delete : " + categorie);
	}
}
