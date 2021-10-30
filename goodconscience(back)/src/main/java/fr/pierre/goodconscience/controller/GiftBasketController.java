package fr.pierre.goodconscience.controller;

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
import fr.pierre.goodconscience.entity.GiftBasket;
import fr.pierre.goodconscience.security.util.jwtUtil;
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

	/** 
	 * Renvoi tous les paniers
	 **/
	@GetMapping("/")
	public ResponseEntity<List<GiftBasket>> getAll(@RequestHeader("Authorization") String token) {
		String email = jwtUtil.extractUsername(token.substring(7));
		if (enterpriseService.findByEmail(email) != null) {
			List<GiftBasket> giftBasketsFiltered = giftBasketService.getAllFiltered(email);
			if (giftBasketsFiltered != null) {
				for (Object o : giftBasketsFiltered) {
					giftBasketSerializable.parseGiftBasket(giftBasketSerializable.parseGiftBasket((GiftBasket)o));
				}
				return ResponseEntity.ok(giftBasketsFiltered);
			}
		}
		return ResponseEntity.notFound().build();
	}

	/** 
	 * Renvoi tous les paniers d'une entreprise grace a son id
	 **/
	@GetMapping("/id/{id}")
	public ResponseEntity<List<GiftBasket>> getAllWithEnterpriseId(@PathVariable Long id) {
		if (enterpriseService.getById(id) != null) {
			List<GiftBasket> giftBaskets = giftBasketService.getAllWithEnterpriseId(id);
			if (giftBaskets != null) {
				for (Object o : giftBaskets) {
					giftBasketSerializable.parseGiftBasket(giftBasketSerializable.parseGiftBasket((GiftBasket)o));
				}
				return ResponseEntity.ok(giftBaskets);
			}
		}
		return ResponseEntity.notFound().build();
	}

	/** 
	 * Renvoi tous les paniers d'une entreprise grace a son id et aux restrictions de l'utilisateur connecté
	 **/
	@GetMapping("/restrictedId/{id}")
	public ResponseEntity<List<GiftBasket>> getAllWithRestriction(@PathVariable Long id, @RequestHeader("Authorization") String token) {
		String email = jwtUtil.extractUsername(token.substring(7));
		if (userService.findByEmail(email) != null) {
			List<GiftBasket> giftBaskets = giftBasketService.getAllWithRestriction(email, id);
			if (giftBaskets != null) {
				for (Object o : giftBaskets) {
					giftBasketSerializable.parseGiftBasket(giftBasketSerializable.parseGiftBasket((GiftBasket)o));
				}
				return ResponseEntity.ok(giftBaskets);
			}
		}
		return ResponseEntity.notFound().build();
	}

	/** 
	 * Renvoi le panier le plus récent de chaque entreprise
	 **/
	@GetMapping("/date")
	public ResponseEntity<List<GiftBasket>> getAllRecent() {
		List<GiftBasket> giftBasketsDate = giftBasketService.getAllRecent();
		if (giftBasketsDate != null) {
			for (Object o : giftBasketsDate) {
				giftBasketSerializable.parseGiftBasket((GiftBasket) o);
			}
			return ResponseEntity.ok(giftBasketsDate);
		}
		return ResponseEntity.ok().build();
	}

	/** 
	 * Renvoi le panier le plus récent de chaque entreprise en prenant compte des restricions de l'utilisateur connecté
	 **/
	@GetMapping("/restrictedDate")
	public ResponseEntity<List<GiftBasket>> getAllRecentWithRestriction(@RequestHeader("Authorization") String token) {
		String email = jwtUtil.extractUsername(token.substring(7));
		if (userService.findByEmail(email) != null) {
			List<GiftBasket> giftBaskets = giftBasketService.getAllRecentWithRestriction(email);
			if (giftBaskets != null) {
				for (Object o : giftBaskets) {
					giftBasketSerializable.parseGiftBasket((GiftBasket) o);
				}
				return ResponseEntity.ok(giftBaskets);
			}
		}
		return ResponseEntity.ok().build();
	}

	/** 
	 * Ajoute un panier à l'entreprise connecté
	 **/
	@PutMapping("/create")
	public ResponseEntity<GiftBasket> create(@RequestBody GiftBasket giftBasket, @RequestHeader("Authorization") String token) {
		String email = jwtUtil.extractUsername(token.substring(7));
		if (enterpriseService.findByEmail(email) != null) {
			GiftBasket giftBasketCreate = giftBasketService.create(email, giftBasket);
			if (giftBasketCreate != null) {
				return ResponseEntity.ok(giftBasketSerializable.parseGiftBasket(giftBasketCreate));
			}
		}
		return ResponseEntity.notFound().build();
	}

	/** 
	 * Met à jour un panier
	 **/
	@PostMapping("/update")
	public ResponseEntity<GiftBasket> update(@RequestBody GiftBasket giftBasket) {
		GiftBasket giftBasketUpdate = giftBasketService.update(giftBasket);
		if (giftBasketUpdate != null) {
			return ResponseEntity.ok(giftBasketUpdate);
		}
		return ResponseEntity.notFound().build();
	}

	/** 
	 * Supprime tous les paniers ayant le même nom de l'entreprise connecté
	 **/
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
