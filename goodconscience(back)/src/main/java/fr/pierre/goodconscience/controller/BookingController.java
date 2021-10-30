package fr.pierre.goodconscience.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.pierre.goodconscience.entity.Booking;
import fr.pierre.goodconscience.entity.GiftBasket;
import fr.pierre.goodconscience.security.util.jwtUtil;
import fr.pierre.goodconscience.serializer.BookingSerializable;
import fr.pierre.goodconscience.service.BookingService;
import fr.pierre.goodconscience.service.EnterpriseService;
import fr.pierre.goodconscience.service.GiftBasketService;
import fr.pierre.goodconscience.service.UserService;

@CrossOrigin(origins="*", maxAge=3600)
@RestController
@RequestMapping(value = "/booking")
public class BookingController {

	@Autowired
	BookingService bookingService;
	@Autowired
	UserService userService;
	@Autowired
	EnterpriseService enterpriseService;
	@Autowired
	GiftBasketService giftBasketService;
	@Autowired
	jwtUtil jwtUtil;
	
	BookingSerializable bookingSerializable = new BookingSerializable();
	
	/** 
	 * Renvoi toutes les réservations 
	 **/
	@GetMapping("/")
	public ResponseEntity<List<Booking>> getAll() {
		List<Booking> bookings = bookingService.getAll();
		for (Object o : bookings) {
			bookingSerializable.parseBooking((Booking)o);
		}
		return ResponseEntity.ok(bookings);
	}

	/** 
	 * Renvoi toutes les réservations de l'utilisateur connecté
	 **/
	@GetMapping("/user")
	public ResponseEntity<List<Booking>> getByUserId(@RequestHeader("Authorization") String token) {
		String email = jwtUtil.extractUsername(token.substring(7));
		if (userService.findByEmail(email) != null) {
			List<Booking> bookings = bookingService.getByUserId(userService.findByEmail(email).getId());
			for (Object o : bookings) {
				bookingSerializable.parseBooking((Booking)o);
			}
			return ResponseEntity.ok(bookings);
		}
		return ResponseEntity.notFound().build();
	}

	/** 
	 * Renvoi toutes les réservations de l'entreprise connecté
	 **/
	@GetMapping("/enterprise")
	public ResponseEntity<List<Booking>> getByGiftBasketId(@RequestHeader("Authorization") String token) {
		String email = jwtUtil.extractUsername(token.substring(7));
		if (enterpriseService.findByEmail(email) != null) {
			List<GiftBasket> giftBaskets = giftBasketService.findByEnterpriseId(enterpriseService.findByEmail(email).getId());
			List<Booking> allBookings = new ArrayList<>();
			for (int i = 0; i < giftBaskets.size(); i++) {
				Booking booking = bookingService.getByGiftBasketId(giftBaskets.get(i).getId());
				if (booking != null) {
					allBookings.add(booking);
				}
			}
			if (!allBookings.isEmpty()) {
				for (Object o : allBookings) {
					bookingSerializable.parseBooking((Booking)o);
				}
				return ResponseEntity.ok(allBookings);
			}
		}
		return ResponseEntity.notFound().build();
	}

	/** 
	 * Créer et renvoi une réservation 
	 **/
	@PutMapping("/create")
	public ResponseEntity<Booking> create(@RequestBody String giftBasketName, @RequestHeader("Authorization") String token) {
		String email = jwtUtil.extractUsername(token.substring(7));
		if (userService.findByEmail(email) != null) {
			Booking booking = bookingService.create(email, giftBasketName);
			if (booking != null) {
				return ResponseEntity.ok(bookingSerializable.parseBooking(booking));
			}
		}
		return ResponseEntity.notFound().build();
	}

	/** 
	 * Met à jour une réservation
	 **/
	@PostMapping("/update")
	public ResponseEntity<Booking> update(@RequestBody Booking booking) {
		Booking bookingCreate = bookingService.update(booking);
		if (bookingCreate != null) {
			return ResponseEntity.ok(bookingCreate);
		}
		return ResponseEntity.notFound().build();
	}

	/** 
	 * Supprime une réservation 
	 **/
	@DeleteMapping("/delete")
	public ResponseEntity<Void> delete(@RequestBody Booking booking) {
		bookingService.delete(booking);
		return ResponseEntity.ok().build();
	}
}
