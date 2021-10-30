package fr.pierre.goodconscience.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

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
	EnterpriseService enterpriseService;
	@Autowired
	UserService userService;
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
}
