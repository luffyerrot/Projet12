package fr.pierre.goodconscience.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.pierre.goodconscience.entity.GiftBasket;

@SpringBootTest
public class GiftBasketServiceTest {

	@Autowired
	GiftBasketService giftBasketService;
	@Autowired
	EnterpriseService enterpriseService;
	
	@Test
	public void createUpdateAndDeleteGiftBasket() {
		
		GiftBasket giftBasketTest = new GiftBasket();
		giftBasketTest.setName("test");
		giftBasketTest.setDescription("test description");
		Date actualDate = new Date();
		giftBasketTest.setRecovery_date(actualDate);
		
		GiftBasket giftBasketSave = giftBasketService.createForTest(giftBasketTest);
		
		assertEquals("test", giftBasketSave.getName());
		assertEquals(actualDate, giftBasketSave.getRecovery_date());
		
		Date newDate = giftBasketSave.getRecovery_date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(newDate);
		calendar.set(Calendar.DATE, -30);
		newDate = calendar.getTime();
		
		giftBasketSave.setName("test1");
		giftBasketSave.setRecovery_date(newDate);
		
		GiftBasket giftBasketUpdate = giftBasketService.update(giftBasketSave);

		assertEquals("test1", giftBasketUpdate.getName());
		assertEquals(newDate, giftBasketUpdate.getRecovery_date());

		giftBasketService.delete(giftBasketUpdate);
	}
}
