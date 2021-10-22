package fr.pierre.goodconscience.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.pierre.goodconscience.entity.Booking;
import fr.pierre.goodconscience.entity.Categorie;
import fr.pierre.goodconscience.entity.Enterprise;
import fr.pierre.goodconscience.entity.GiftBasket;
import fr.pierre.goodconscience.entity.Product;
import fr.pierre.goodconscience.entity.User;
import fr.pierre.goodconscience.secutiry.util.jwtUtil;
import fr.pierre.goodconscience.serializer.GiftBasketSerializable;
import fr.pierre.goodconscience.service.BookingService;
import fr.pierre.goodconscience.service.EnterpriseService;
import fr.pierre.goodconscience.service.GiftBasketService;
import fr.pierre.goodconscience.service.UserService;

@CrossOrigin(origins="*", maxAge=3600)
@RestController
@RequestMapping(value = "/giftbasket")
public class GiftBasketController {

	@Autowired
	GiftBasketService giftBasketService;
	@Autowired
	BookingService bookingService;
	@Autowired
	UserService userService;
	@Autowired
	EnterpriseService enterpriseService;
	@Autowired
	jwtUtil jwtUtil;
	
	GiftBasketSerializable giftBasketSerializable = new GiftBasketSerializable();
	
	@GetMapping("/")
	public ResponseEntity<List<GiftBasket>> getAll(@RequestHeader("Authorization") String token) {
		String email = jwtUtil.extractUsername(token.substring(7));
		if (enterpriseService.findByEmail(email) != null) {
			Enterprise enterprise = enterpriseService.findByEmail(email);
			List<GiftBasket> giftBaskets = giftBasketService.findByEnterpriseId(enterprise.getId());
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
				for (Object o : giftBasketsFiltered) {
					giftBasketSerializable.parseGiftBasket(giftBasketSerializable.parseGiftBasket((GiftBasket)o));
				}
				return ResponseEntity.ok(giftBasketsFiltered);
			}
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<List<GiftBasket>> getAll(@PathVariable Long id) {
		if (enterpriseService.getById(id) != null) {
			Enterprise enterprise = enterpriseService.getById(id);
			List<GiftBasket> giftBaskets = giftBasketService.findByEnterpriseId(enterprise.getId());
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
				for (Object o : giftBasketsFiltered) {
					giftBasketSerializable.parseGiftBasket(giftBasketSerializable.parseGiftBasket((GiftBasket)o));
				}
				return ResponseEntity.ok(giftBasketsFiltered);
			} else {
				ResponseEntity.ok().build();
			}
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/restrictedId/{id}")
	public ResponseEntity<List<GiftBasket>> getAllWithRestriction(@PathVariable Long id, @RequestHeader("Authorization") String token) {
		String email = jwtUtil.extractUsername(token.substring(7));
		if (userService.findByEmail(email) != null) {
			User user = userService.findByEmail(email);
			if (enterpriseService.getById(id) != null) {
				Enterprise enterprise = enterpriseService.getById(id);
				List<GiftBasket> giftBaskets = giftBasketService.findByEnterpriseId(enterprise.getId());
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
					for (Object o : giftBasketsWithRestriction) {
						giftBasketSerializable.parseGiftBasket(giftBasketSerializable.parseGiftBasket((GiftBasket)o));
					}
					return ResponseEntity.ok(giftBasketsWithRestriction);
				} else {
					ResponseEntity.ok().build();
				}
			}
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/date")
	public ResponseEntity<List<GiftBasket>> getAllRecent() {
		List<GiftBasket> allGiftBaskets = giftBasketService.getAll();
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
				List<GiftBasket> giftBaskets = giftBasketService.findByEnterpriseId(ids.get(i));
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
			for (Object o : giftBasketsDate) {
				giftBasketSerializable.parseGiftBasket((GiftBasket) o);
			}
			return ResponseEntity.ok(giftBasketsDate);
		}
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/restrictedDate")
	public ResponseEntity<List<GiftBasket>> getAllRecentWithRestriction(@RequestHeader("Authorization") String token) {
		String email = jwtUtil.extractUsername(token.substring(7));
		if (userService.findByEmail(email) != null) {
			User user = userService.findByEmail(email);
			List<GiftBasket> allGiftBaskets = giftBasketService.getAll();
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
					List<GiftBasket> giftBaskets = giftBasketService.findByEnterpriseId(ids.get(i));
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
				for (Object o : giftBasketsDateWithRestriction) {
					giftBasketSerializable.parseGiftBasket((GiftBasket) o);
				}
				return ResponseEntity.ok(giftBasketsDateWithRestriction);
			}
		}
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/create")
	public ResponseEntity<GiftBasket> create(@RequestBody GiftBasket giftBasket, @RequestHeader("Authorization") String token) {
		String email = jwtUtil.extractUsername(token.substring(7));
		if (enterpriseService.findByEmail(email) != null) {
			Enterprise enterprise = enterpriseService.findByEmail(email);
			List<Product> products = giftBasket.getProducts();
			List<Categorie> categories = giftBasket.getCategories();
			products.forEach(p -> p.setEnterprise(enterprise));
			giftBasket.setProducts(null);
			giftBasket.setCategories(null);
			giftBasket.setEnterprise(enterprise);
			GiftBasket giftBasketCreate = giftBasketService.create(giftBasket);
			if (giftBasketCreate != null) {
				giftBasketCreate.setProducts(products);
				giftBasketCreate.setCategories(categories);
				GiftBasket giftBasketUpdateProductsAndCategories = giftBasketService.update(giftBasketCreate);
				if (giftBasketUpdateProductsAndCategories != null) {
					return ResponseEntity.ok(giftBasketSerializable.parseGiftBasket(giftBasketUpdateProductsAndCategories));
				}
			}
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/update")
	public ResponseEntity<GiftBasket> update(@RequestBody GiftBasket giftBasket) {
		GiftBasket giftBasketUpdate = giftBasketService.update(giftBasket);
		if (giftBasketUpdate != null) {
			return ResponseEntity.ok(giftBasketUpdate);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/delete/{name}")
	public ResponseEntity<Void> delete(@PathVariable String name, @RequestHeader("Authorization") String token) {
		String email = jwtUtil.extractUsername(token.substring(7));
		if (enterpriseService.findByEmail(email) != null) {
			List<GiftBasket> giftBaskets = giftBasketService.getByName(name);
			if (giftBaskets != null) {
				List<Booking> bookings = bookingService.getByGiftBasketName(name);
				if (bookings != null) {
					for (int i = 0; i < bookings.size(); i++) {
						bookingService.delete(bookings.get(i));
					}
				}
				for (int i = 0; i < giftBaskets.size(); i++) {
					giftBaskets.get(i).setCategories(null);
					giftBaskets.get(i).setProducts(null);
					GiftBasket giftBasketUpdate = giftBasketService.update(giftBaskets.get(i));
					giftBasketService.delete(giftBasketUpdate);
				}
				return ResponseEntity.ok().build();
			}
		}
		return ResponseEntity.notFound().build();
	}
}
