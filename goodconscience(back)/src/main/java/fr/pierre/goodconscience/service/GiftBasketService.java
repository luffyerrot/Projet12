package fr.pierre.goodconscience.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.pierre.goodconscience.entity.GiftBasket;
import fr.pierre.goodconscience.repository.GiftBasketRepository;

@Service
public class GiftBasketService {

	@Autowired
	GiftBasketRepository giftBasketRepository;

	Logger logger = LoggerFactory.getLogger(GiftBasketService.class);
	
	public GiftBasket getById(Long id) {
		this.logger.debug("getById Call = " + id);
		GiftBasket giftBasket = giftBasketRepository.findById(id).get();
		this.logger.debug("getById Return = " + giftBasket);
		return giftBasket;
	}
	
	public List<GiftBasket> getByName(String name) {
		this.logger.debug("getByName Call = " + name);
		List<GiftBasket> giftBaskets = giftBasketRepository.findByName(name);
		this.logger.debug("getByName Return = " + giftBaskets);
		return giftBaskets;
	}
	
	public List<GiftBasket> getAll() {
		List<GiftBasket> giftBaskets = giftBasketRepository.findAll();
		this.logger.debug("getAll Return = " + giftBaskets);
		return giftBaskets;
	}

	public List<GiftBasket> findByEnterpriseId(Long id) {
		this.logger.debug("findByEnterpriseId Call = " + id);
		List<GiftBasket> giftBaskets = giftBasketRepository.findByEnterpriseId(id);
		this.logger.debug("findByEnterpriseId Return = " + giftBaskets);
		return giftBaskets;
	}
	
	public GiftBasket create(GiftBasket giftBasket) {
		this.logger.debug("create Call = " + giftBasket);
		GiftBasket giftBasketSave = giftBasketRepository.save(giftBasket);
		this.logger.debug("create Return = " + giftBasketSave);
		return giftBasketSave;
	}
	
	public GiftBasket update(GiftBasket giftBasket) {
		this.logger.debug("update Call = " + giftBasket);
		if (giftBasketRepository.getById(giftBasket.getId()) != null) {
			GiftBasket giftBasketSave = giftBasketRepository.save(giftBasket);
			this.logger.debug("update Return = " + giftBasketSave);
			return giftBasketSave;
		}
		this.logger.debug("update Return = " + null);
		return null;
	}
	
	public void delete(GiftBasket giftBasket) {
		this.logger.debug("delete Call = " + giftBasket);
		if (giftBasketRepository.getById(giftBasket.getId()) != null) {
			giftBasketRepository.delete(giftBasket);
			return;
		}
		this.logger.debug("can't delete : " + giftBasket);
	}
}
