package fr.pierre.goodconscience.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.pierre.goodconscience.entity.Booking;
import fr.pierre.goodconscience.repository.BookingRepository;

@Service
public class BookingService {

	@Autowired
	BookingRepository bookingRepository;

	Logger logger = LoggerFactory.getLogger(BookingService.class);
	
	public List<Booking> getByUserId(Long id) {
		this.logger.debug("getByUserId Call = " + id);
		List<Booking> bookings = bookingRepository.findByUserId(id);
		this.logger.debug("getByUserId Return = " + bookings);
		return bookings;
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
	
	public Booking create(Booking booking) {
		this.logger.debug("create Call = " + booking);
		Booking bookingSave = bookingRepository.save(booking);
		this.logger.debug("create Return = " + bookingSave);
		return bookingSave;
	}
	
	public Booking update(Booking booking) {
		this.logger.debug("update Call = " + booking);
		if (bookingRepository.getById(booking.getId()) != null) {
			Booking bookingSave = bookingRepository.save(booking);
			this.logger.debug("update Return = " + bookingSave);
			return bookingSave;
		}
		this.logger.debug("update Return = " + null);
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
