package fr.pierre.goodconscience.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.pierre.goodconscience.entity.Categorie;
import fr.pierre.goodconscience.entity.Enterprise;
import fr.pierre.goodconscience.entity.GiftBasket;
import fr.pierre.goodconscience.entity.Product;
import fr.pierre.goodconscience.entity.User;
import fr.pierre.goodconscience.repository.GiftBasketRepository;

@Service
public class GiftBasketService {

	@Autowired
	EnterpriseService enterpriseService;
	@Autowired
	UserService userService;
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
	
	public List<GiftBasket> getAllFiltered(String email) {
		Enterprise enterprise = enterpriseService.findByEmail(email);
		List<GiftBasket> giftBaskets = this.findByEnterpriseId(enterprise.getId());
		if (giftBaskets != null) {
			List<GiftBasket> giftBasketsFiltered = new ArrayList<>();
			for (int i = 0; i < giftBaskets.size(); i++) {
				if (giftBasketsFiltered.isEmpty()) {
					giftBasketsFiltered.add(giftBaskets.get(i));
				} else {
					Boolean exist = false;
					for(int j = 0; j < giftBasketsFiltered.size(); j++) {
						if (giftBasketsFiltered.get(j).getName().equals(giftBaskets.get(i).getName())) {
							exist = true;
						}
					}
					if (!exist) {
						giftBasketsFiltered.add(giftBaskets.get(i));
					}
				}
			}
			return giftBasketsFiltered;
		}
		return null;
	}
	
	public List<GiftBasket> getAllWithEnterpriseId(Long id) {
		Enterprise enterprise = enterpriseService.getById(id);
		List<GiftBasket> giftBaskets = this.findByEnterpriseId(enterprise.getId());
		if (giftBaskets != null) {
			List<GiftBasket> giftBasketsFiltered = new ArrayList<>();
			for (int i = 0; i < giftBaskets.size(); i++) {
				if (giftBasketsFiltered.isEmpty()) {
					giftBasketsFiltered.add(giftBaskets.get(i));
				} else {
					Boolean exist = false;
					for(int j = 0; j < giftBasketsFiltered.size(); j++) {
						if (giftBasketsFiltered.get(j).getName().equals(giftBaskets.get(i).getName())) {
							exist = true;
						}
					}
					if (!exist) {
						giftBasketsFiltered.add(giftBaskets.get(i));
					}
				}
			}
			return giftBasketsFiltered;
		} 
		return null;
	}

	public List<GiftBasket> findByEnterpriseId(Long id) {
		this.logger.debug("findByEnterpriseId Call = " + id);
		List<GiftBasket> giftBaskets = giftBasketRepository.findByEnterpriseId(id);
		this.logger.debug("findByEnterpriseId Return = " + giftBaskets);
		return giftBaskets;
	}
	
	public List<GiftBasket> getAllWithRestriction(String email, Long id) {
		User user = userService.findByEmail(email);
		if (enterpriseService.getById(id) != null) {
			Enterprise enterprise = enterpriseService.getById(id);
			List<GiftBasket> giftBaskets = this.findByEnterpriseId(enterprise.getId());
			if (giftBaskets != null) {
				List<GiftBasket> giftBasketsFiltered = new ArrayList<>();
				for (int i = 0; i < giftBaskets.size(); i++) {
					if (giftBasketsFiltered.isEmpty()) {
						giftBasketsFiltered.add(giftBaskets.get(i));
					} else {
						Boolean exist = false;
						for(int j = 0; j < giftBasketsFiltered.size(); j++) {
							if (giftBasketsFiltered.get(j).getName().equals(giftBaskets.get(i).getName())) {
								exist = true;
							}
						}
						if (!exist) {
							giftBasketsFiltered.add(giftBaskets.get(i));
						}
					}
				}
				List<GiftBasket> giftBasketsWithRestriction = new ArrayList<>();
				for (int i = 0; i < giftBasketsFiltered.size(); i++) {
					for (int j = 0; j < user.getCategories().size(); j++) {
						if (giftBasketsFiltered.get(i).getCategories().contains(user.getCategories().get(j)) && !giftBasketsWithRestriction.contains(giftBasketsFiltered.get(i))) {
							giftBasketsWithRestriction.add(giftBasketsFiltered.get(i));
						}
					}
				}
				return giftBasketsWithRestriction;
			}
		}
		return null;
	}
	
	public List<GiftBasket> getAllRecent() {
		List<GiftBasket> allGiftBaskets = this.getAll();
		List<Long> ids = new ArrayList<>();
		for (Object o : allGiftBaskets) {
			if (((GiftBasket)o).getEnterprise() != null) {
				if (ids.isEmpty()) {
					ids.add(((GiftBasket)o).getEnterprise().getId());
				} else {
					if (!ids.contains(((GiftBasket)o).getEnterprise().getId())) {
						ids.add(((GiftBasket)o).getEnterprise().getId());
					}
				}
			}
		}
		if (!ids.isEmpty()) {
			List<GiftBasket> giftBasketsDate = new ArrayList<>();
			for (int i = 0; i < ids.size(); i++) {
				List<GiftBasket> giftBaskets = this.findByEnterpriseId(ids.get(i));
				GiftBasket goodDateGiftBasket = null;
				for (int j = 0; j < giftBaskets.size(); j++) {
					if (goodDateGiftBasket == null || giftBaskets.get(j).getRecovery_date().after(goodDateGiftBasket.getRecovery_date())) {
						goodDateGiftBasket = giftBaskets.get(j);
					}
				}
				if (goodDateGiftBasket != null) {
					giftBasketsDate.add(goodDateGiftBasket);
				}
			}
			return giftBasketsDate;
		}
		return null;
	}
	
	public List<GiftBasket> getAllRecentWithRestriction(String email) {
		User user = userService.findByEmail(email);
		List<GiftBasket> allGiftBaskets = this.getAll();
		List<Long> ids = new ArrayList<>();
		for (Object o : allGiftBaskets) {
			if (((GiftBasket)o).getEnterprise() != null) {
				if (ids.isEmpty()) {
					ids.add(((GiftBasket)o).getEnterprise().getId());
				} else {
					if (!ids.contains(((GiftBasket)o).getEnterprise().getId())) {
						ids.add(((GiftBasket)o).getEnterprise().getId());
					}
				}
			}
		}
		if (!ids.isEmpty()) {
			List<GiftBasket> giftBasketsDate = new ArrayList<>();
			for (int i = 0; i < ids.size(); i++) {
				List<GiftBasket> giftBaskets = this.findByEnterpriseId(ids.get(i));
				GiftBasket goodDateGiftBasket = null;
				for (int j = 0; j < giftBaskets.size(); j++) {
					if (goodDateGiftBasket == null || giftBaskets.get(j).getRecovery_date().after(goodDateGiftBasket.getRecovery_date())) {
						goodDateGiftBasket = giftBaskets.get(j);
					}
				}
				if (goodDateGiftBasket != null) {
					giftBasketsDate.add(goodDateGiftBasket);
				}
			}
			List<GiftBasket> giftBasketsDateWithRestriction = new ArrayList<>();
			for (int i = 0; i < giftBasketsDate.size(); i++) {
				for (int j = 0; j < user.getCategories().size(); j++) {
					if (giftBasketsDate.get(i).getCategories().contains(user.getCategories().get(j))) {
						giftBasketsDateWithRestriction.add(giftBasketsDate.get(i));
					}
				}
			}
			return giftBasketsDateWithRestriction;
		}
		return null;
	}
	
	public GiftBasket create(String email, GiftBasket giftBasket) {
		Enterprise enterprise = enterpriseService.findByEmail(email);
		List<Product> products = giftBasket.getProducts();
		List<Categorie> categories = giftBasket.getCategories();
		if (products != null) {
			products.forEach(p -> p.setEnterprise(enterprise));
		}
		giftBasket.setProducts(null);
		giftBasket.setCategories(null);
		giftBasket.setEnterprise(enterprise);
		GiftBasket giftBasketCreate = giftBasketRepository.save(giftBasket);
		if (giftBasketCreate != null) {
			giftBasketCreate.setProducts(products);
			giftBasketCreate.setCategories(categories);
			GiftBasket giftBasketUpdateProductsAndCategories = this.update(giftBasketCreate);
			if (giftBasketUpdateProductsAndCategories != null) {
				return giftBasketUpdateProductsAndCategories;
			}
		}
		return null;
	}
	
	public GiftBasket createForTest(GiftBasket giftBasket) {
		GiftBasket giftBasketCreate = giftBasketRepository.save(giftBasket);
		if (giftBasketCreate != null) {
			return giftBasketCreate;
		}
		return null;
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
		giftBasketRepository.delete(giftBasket);
		this.logger.debug("can't delete : " + giftBasket);
	}
}
