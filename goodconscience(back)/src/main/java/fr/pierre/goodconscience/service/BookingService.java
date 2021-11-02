package fr.pierre.goodconscience.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.pierre.goodconscience.entity.Booking;
import fr.pierre.goodconscience.entity.GiftBasket;
import fr.pierre.goodconscience.entity.User;
import fr.pierre.goodconscience.repository.BookingRepository;

@Service
public class BookingService {

	@Autowired
	BookingRepository bookingRepository;
	@Autowired
	UserService userService;
	@Autowired
	GiftBasketService giftBasketService;

	Logger logger = LoggerFactory.getLogger(BookingService.class);
	
	public List<Booking> getByUserId(Long id) {
		this.logger.debug("getByUserId Call = " + id);
		List<Booking> bookings = bookingRepository.findByUserId(id);
		this.logger.debug("getByUserId Return = " + bookings);
		return bookings;
	}
	
	public Booking getById(Long id) {
		this.logger.debug("getById Call = " + id);
		if (bookingRepository.findById(id).isPresent()) {
			Booking booking = bookingRepository.findById(id).get();
			this.logger.debug("getById Return = " + booking);
			return booking;
		}
		this.logger.debug("getById Return = " + null);
		return null;
	}
	
	public List<Booking> getByGiftBasketName(String name) {
		this.logger.debug("getByGiftBasketName Call = " + name);
		List<Booking> bookings = bookingRepository.findByGiftBasketName(name);
		this.logger.debug("getByGiftBasketName Return = " + bookings);
		return bookings;
	}
	
	public Booking getByGiftBasketId(Long id) {
		this.logger.debug("getByGiftBasketId Call = " + id);
		Booking booking = bookingRepository.findByGiftBasketId(id);
		this.logger.debug("getByGiftBasketId Return = " + booking);
		return booking;
	}
	
	public List<Booking> getAll() {
		List<Booking> bookings = bookingRepository.findAll();
		this.logger.debug("getAll Return = " + bookings);
		return bookings;
	}
	
	public Booking create(String email, String giftBasketName) {
		User user = userService.findByEmail(email);
		List<GiftBasket> giftBaskets = giftBasketService.getByName(giftBasketName);
		if (giftBaskets != null) {
			GiftBasket giftBasketForBooking = null;
			List<Booking> bookings = this.getAll();
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
				if (this.getByGiftBasketId(giftBasketForBooking.getId()) == null) {
					List<User> usersBooking = new ArrayList<>();
					List<Booking> bookingsByGiftBasketName = this.getByGiftBasketName(giftBasketName);
					if (bookingsByGiftBasketName != null) {
						for (int k = 0; k < bookingsByGiftBasketName.size(); k++) {
							usersBooking.add(bookingsByGiftBasketName.get(k).getUser());
						}
						if (!usersBooking.contains(user)) {
							Booking booking = new Booking();
							booking.setBooking_date(new Date());
							booking.setUser(user);
							booking.setGiftBasket(giftBasketForBooking);
							Booking bookingCreate = bookingRepository.save(booking);
							if (bookingCreate != null) {
								return bookingCreate;
							}
						}
					}
				}
			}
		}
		return null;
	}
	
	public Booking createForTest(GiftBasket giftBasket) {
		
		Booking booking = new Booking();
		booking.setBooking_date(new Date());
		booking.setGiftBasket(giftBasket);
		booking.setUser(null);
		Booking bookingCreate = bookingRepository.save(booking);
		if (bookingCreate != null) {
			return bookingCreate;
		}
		return null;
	}
	
	public void delete(Booking booking) {
		this.logger.debug("delete Call = " + booking);
		if (bookingRepository.getById(booking.getId()) != null) {
			bookingRepository.delete(booking);
			return;
		}
		this.logger.debug("can't delete : " + booking);
	}
}
