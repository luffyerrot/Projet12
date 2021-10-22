package fr.pierre.goodconscience.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import fr.pierre.goodconscience.entity.Booking;

@SpringBootTest
public class BookingServiceTest {

	@Autowired
	BookingService bookingService;
	
	@Test
	@Rollback
	public void createUpdateAndDeleteBooking() {
		Booking bookingTest = new Booking();
		Date actualDate = new Date();
		bookingTest.setBooking_date(actualDate);
		
		Booking bookingSave = bookingService.create(bookingTest);
		
		assertEquals(actualDate, bookingSave.getBooking_date());

		Date newDate = bookingSave.getBooking_date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(newDate);
		calendar.set(Calendar.DATE, -30);
		newDate = calendar.getTime();
		
		bookingSave.setBooking_date(newDate);
		
		Booking bookingUpdate =  bookingService.update(bookingSave);
		
		assertEquals(newDate, bookingUpdate.getBooking_date());
		
		bookingService.delete(bookingUpdate);
	}
}
