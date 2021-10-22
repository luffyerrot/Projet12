package fr.pierre.goodconscience.controller;

import java.util.ArrayList;
import java.util.Date;
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
import fr.pierre.goodconscience.entity.User;
import fr.pierre.goodconscience.secutiry.util.jwtUtil;
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
	
	@GetMapping("/")
	public ResponseEntity<List<Booking>> getAll() {
		List<Booking> bookings = bookingService.getAll();
		for (Object o : bookings) {
			bookingSerializable.parseBooking((Booking)o);
		}
		return ResponseEntity.ok(bookings);
	}

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
	
	@PutMapping("/create")
	public ResponseEntity<Booking> create(@RequestBody String giftBasketName, @RequestHeader("Authorization") String token) {
		String email = jwtUtil.extractUsername(token.substring(7));
		if (userService.findByEmail(email) != null) {
			User user = userService.findByEmail(email);
			List<GiftBasket> giftBaskets = giftBasketService.getByName(giftBasketName);
			if (giftBaskets != null) {
				GiftBasket giftBasketForBooking = null;
				List<Booking> bookings = bookingService.getAll();
				for (int i = 0; i < giftBaskets.size(); i++) {
					if (giftBasketForBooking == null) {
						List<GiftBasket> bookingsGiftBasket = new ArrayList<>();
						if (bookings != null && !bookings.isEmpty()) {
							for (int j = 0; j < bookings.size(); j++) {
								bookingsGiftBasket.add(bookings.get(j).getGiftBasket());
							}
						}
						if (!bookingsGiftBasket.isEmpty()) {
							if (!bookingsGiftBasket.contains(giftBaskets.get(i))) {
								giftBasketForBooking = giftBaskets.get(i);
							}
						} else {
							giftBasketForBooking = giftBaskets.get(i);
						}
					}
				}
				if (giftBasketForBooking != null) {
					if (bookingService.getByGiftBasketId(giftBasketForBooking.getId()) == null) {
						List<User> usersBooking = new ArrayList<>();
						List<Booking> bookingsByGiftBasketName = bookingService.getByGiftBasketName(giftBasketName);
						if (bookingsByGiftBasketName != null) {
							for (int k = 0; k < bookingsByGiftBasketName.size(); k++) {
								usersBooking.add(bookingsByGiftBasketName.get(k).getUser());
							}
							if (!usersBooking.contains(user)) {
								Booking booking = new Booking();
								booking.setBooking_date(new Date());
								booking.setUser(user);
								booking.setGiftBasket(giftBasketForBooking);
								Booking bookingCreate = bookingService.create(booking);
								if (bookingCreate != null) {
									return ResponseEntity.ok(bookingSerializable.parseBooking(bookingCreate));
								}
							}
						}
					}
				}
			}
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/update")
	public ResponseEntity<Booking> update(@RequestBody Booking booking) {
		Booking bookingCreate = bookingService.update(booking);
		if (bookingCreate != null) {
			return ResponseEntity.ok(bookingCreate);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<Void> delete(@RequestBody Booking booking) {
		bookingService.delete(booking);
		return ResponseEntity.ok().build();
	}
}
