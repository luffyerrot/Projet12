package fr.pierre.goodconscience.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.pierre.goodconscience.entity.Booking;
import fr.pierre.goodconscience.entity.GiftBasket;

@SpringBootTest
public class BookingServiceTest {

	@Autowired
	GiftBasketService giftBasketService;
	@Autowired
	BookingService bookingService;
	
	@Test
	public void createAndDeleteBooking() {
		
		GiftBasket giftBasket = new GiftBasket();
		giftBasket.setName("testpanierBooking");
		giftBasket.setDescription("testpanierBooking pour réservation");
		giftBasket.setRecovery_date(new Date());
		GiftBasket giftBasketCreated = giftBasketService.createForTest(giftBasket);
		
		Booking bookingSave = bookingService.createForTest(giftBasketCreated);
		
		assertEquals(giftBasketCreated.getId(), bookingSave.getGiftBasket().getId());

		bookingService.delete(bookingSave);
	}
	
	@Test
	public void getAllBooking() {
		GiftBasket giftBasket1 = new GiftBasket();
		giftBasket1.setName("testGetBooking1");
		giftBasket1.setDescription("testGetBooking pour get");
		giftBasket1.setRecovery_date(new Date());
		GiftBasket giftBasketCreated1 = giftBasketService.createForTest(giftBasket1);
		
		Booking bookingSave1 = bookingService.createForTest(giftBasketCreated1);

		GiftBasket giftBasket2 = new GiftBasket();
		giftBasket2.setName("testGetBooking2");
		giftBasket2.setDescription("testGetBooking pour get");
		giftBasket2.setRecovery_date(new Date());
		GiftBasket giftBasketCreated2 = giftBasketService.createForTest(giftBasket2);
		
		Booking bookingSave2 = bookingService.createForTest(giftBasketCreated2);

		assertEquals(giftBasketCreated1.getId(), bookingSave1.getGiftBasket().getId());
		assertEquals(giftBasketCreated2.getId(), bookingSave2.getGiftBasket().getId());
		
		List<Booking> bookingGetAll = bookingService.getAll();
		
		assertTrue(bookingGetAll != null);
		List<Long> ids = new ArrayList<>();
		for(int i = 0; i < bookingGetAll.size(); i++) {
			ids.add(bookingGetAll.get(i).getId());
		}
		assertTrue(ids.contains(bookingSave1.getId()));
		assertTrue(ids.contains(bookingSave2.getId()));
		
		bookingService.delete(bookingSave1);
		bookingService.delete(bookingSave2);
		giftBasketService.delete(giftBasketCreated1);
		giftBasketService.delete(giftBasketCreated2);
	}

	@Test
	public void getBookingByGiftBasketId() {
		GiftBasket giftBasket = new GiftBasket();
		giftBasket.setName("testGetBooking");
		giftBasket.setDescription("testGetBooking pour get by id");
		giftBasket.setRecovery_date(new Date());
		GiftBasket giftBasketCreated = giftBasketService.createForTest(giftBasket);
		
		Booking bookingSave1 = bookingService.createForTest(giftBasketCreated);

		assertTrue(bookingService.getByGiftBasketId(giftBasketCreated.getId()).getId().equals(bookingSave1.getId()) );
		
		bookingService.delete(bookingSave1);
		giftBasketService.delete(giftBasketCreated);
	}

	@Test
	public void getAllBookingByName() {
		GiftBasket giftBasket1 = new GiftBasket();
		giftBasket1.setName("testGetBookingSame");
		giftBasket1.setDescription("testGetBookingSame 1");
		giftBasket1.setRecovery_date(new Date());
		GiftBasket giftBasketCreated1 = giftBasketService.createForTest(giftBasket1);
		
		Booking bookingSave1 = bookingService.createForTest(giftBasketCreated1);

		GiftBasket giftBasket2 = new GiftBasket();
		giftBasket2.setName("testGetBookingSame");
		giftBasket2.setDescription("testGetBookingSame 2");
		giftBasket2.setRecovery_date(new Date());
		GiftBasket giftBasketCreated2 = giftBasketService.createForTest(giftBasket2);
		
		Booking bookingSave2 = bookingService.createForTest(giftBasketCreated2);

		assertEquals(giftBasketCreated1.getId(), bookingSave1.getGiftBasket().getId());
		assertEquals(giftBasketCreated2.getId(), bookingSave2.getGiftBasket().getId());
		
		List<Booking> bookingGetByGiftBasketName = bookingService.getByGiftBasketName("testGetBookingSame");
		
		assertTrue(bookingGetByGiftBasketName != null);
		List<Long> ids = new ArrayList<>();
		for(int i = 0; i < bookingGetByGiftBasketName.size(); i++) {
			ids.add(bookingGetByGiftBasketName.get(i).getId());
		}
		assertTrue(ids.contains(bookingSave1.getId()));
		assertTrue(ids.contains(bookingSave2.getId()));
		
		bookingService.delete(bookingSave1);
		bookingService.delete(bookingSave2);
		giftBasketService.delete(giftBasketCreated1);
		giftBasketService.delete(giftBasketCreated2);
	}
}
