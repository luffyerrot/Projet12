package fr.pierre.goodconscience.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.pierre.goodconscience.entity.Enterprise;
import fr.pierre.goodconscience.entity.GiftBasket;

@SpringBootTest
public class GiftBasketServiceTest {

	@Autowired
	GiftBasketService giftBasketService;
	@Autowired
	EnterpriseService enterpriseService;
	@Autowired
	CategorieService categorieService;
	@Autowired
	UserService userService;
	
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
	
	@Test
	public void getByIdGiftBasket() {
		
		GiftBasket giftBasketTest = new GiftBasket();
		giftBasketTest.setName("testGetById");
		giftBasketTest.setDescription("test description");
		giftBasketTest.setRecovery_date(new Date());
		
		GiftBasket giftBasketSave = giftBasketService.createForTest(giftBasketTest);
		
		GiftBasket giftBasketGetById = giftBasketService.getById(giftBasketSave.getId());

		assertTrue(giftBasketGetById != null);
		assertEquals(giftBasketSave.getId(), giftBasketGetById.getId());

		giftBasketService.delete(giftBasketSave);
	}
	
	@Test
	public void getByNameGiftBasket() {
		
		GiftBasket giftBasketTest1 = new GiftBasket();
		giftBasketTest1.setName("testGetById");
		giftBasketTest1.setDescription("test description 1");
		giftBasketTest1.setRecovery_date(new Date());

		GiftBasket giftBasketTest2 = new GiftBasket();
		giftBasketTest2.setName("testGetById");
		giftBasketTest2.setDescription("test description 2");
		giftBasketTest2.setRecovery_date(new Date());
		
		GiftBasket giftBasketSave1 = giftBasketService.createForTest(giftBasketTest1);
		GiftBasket giftBasketSave2 = giftBasketService.createForTest(giftBasketTest2);
		
		List<GiftBasket> giftBasketGetByName = giftBasketService.getByName("testGetById");

		assertTrue(giftBasketGetByName != null);
		List<Long> ids = new ArrayList<>();
		for(int i = 0; i < giftBasketGetByName.size(); i++) {
			ids.add(giftBasketGetByName.get(i).getId());
		}
		assertTrue(ids.contains(giftBasketSave1.getId()));
		assertTrue(ids.contains(giftBasketSave2.getId()));

		giftBasketService.delete(giftBasketSave1);
		giftBasketService.delete(giftBasketSave2);
	}
	
	@Test
	public void getAllGiftBasket() {
		
		GiftBasket giftBasketTest1 = new GiftBasket();
		giftBasketTest1.setName("testGetById1");
		giftBasketTest1.setDescription("test description 1");
		giftBasketTest1.setRecovery_date(new Date());

		GiftBasket giftBasketTest2 = new GiftBasket();
		giftBasketTest2.setName("testGetById2");
		giftBasketTest2.setDescription("test description 2");
		giftBasketTest2.setRecovery_date(new Date());
		
		GiftBasket giftBasketSave1 = giftBasketService.createForTest(giftBasketTest1);
		GiftBasket giftBasketSave2 = giftBasketService.createForTest(giftBasketTest2);
		
		List<GiftBasket> giftBasketGetAll = giftBasketService.getAll();

		assertTrue(giftBasketGetAll != null);
		List<Long> ids = new ArrayList<>();
		for(int i = 0; i < giftBasketGetAll.size(); i++) {
			ids.add(giftBasketGetAll.get(i).getId());
		}
		assertTrue(ids.contains(giftBasketSave1.getId()));
		assertTrue(ids.contains(giftBasketSave2.getId()));

		giftBasketService.delete(giftBasketSave1);
		giftBasketService.delete(giftBasketSave2);
	}
	
	@Test
	public void getAllGiftBasketByEnterpriseId() {
		
		Enterprise enterprise = new Enterprise();
		enterprise.setEmail("enterpriseGiftBasket@test.fr");
		enterprise.setName("enterprise");
		enterprise.setPassword("12345");
		
		Enterprise enterpriseSave = enterpriseService.createForTest(enterprise);
		
		GiftBasket giftBasketTest1 = new GiftBasket();
		giftBasketTest1.setName("testGetByEnterpriseId1");
		giftBasketTest1.setDescription("test description 1");
		giftBasketTest1.setRecovery_date(new Date());

		GiftBasket giftBasketTest2 = new GiftBasket();
		giftBasketTest2.setName("testGetByEnterpriseId2");
		giftBasketTest2.setDescription("test description 2");
		giftBasketTest2.setRecovery_date(new Date());
		
		GiftBasket giftBasketSave1 = giftBasketService.create(enterpriseSave.getEmail(), giftBasketTest1);
		GiftBasket giftBasketSave2 = giftBasketService.create(enterpriseSave.getEmail(), giftBasketTest2);
		
		List<GiftBasket> giftBaskets = giftBasketService.getAllWithEnterpriseId(enterpriseSave.getId());
		
		assertTrue(giftBaskets != null);
		List<Long> ids = new ArrayList<>();
		for(int i = 0; i < giftBaskets.size(); i++) {
			ids.add(giftBaskets.get(i).getId());
		}
		assertTrue(ids.contains(giftBasketSave1.getId()));
		assertTrue(ids.contains(giftBasketSave2.getId()));
		
		giftBasketService.delete(giftBasketSave1);
		giftBasketService.delete(giftBasketSave2);
		enterpriseService.delete(enterpriseSave);
	}
	
	@Test
	public void getAllGiftBasketRecent() {
		
		Enterprise enterprise1 = new Enterprise();
		enterprise1.setEmail("enterprise1@test.fr");
		enterprise1.setName("enterprise1");
		enterprise1.setPassword("12345");
		
		Enterprise enterprise2 = new Enterprise();
		enterprise2.setEmail("enterprise2@test.fr");
		enterprise2.setName("enterprise1");
		enterprise2.setPassword("12345");
		
		Enterprise enterpriseSave1 = enterpriseService.createForTest(enterprise1);
		Enterprise enterpriseSave2 = enterpriseService.createForTest(enterprise2);
		
		GiftBasket giftBasketTest1 = new GiftBasket();
		giftBasketTest1.setName("testRecent1");
		giftBasketTest1.setDescription("test description 1");

		Date newDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(newDate);
		calendar.set(Calendar.DATE, -30);
		newDate = calendar.getTime();
		
		giftBasketTest1.setRecovery_date(newDate);
		
		GiftBasket giftBasketTest2 = new GiftBasket();
		giftBasketTest2.setName("testRecent2");
		giftBasketTest2.setDescription("test description 2");
		giftBasketTest2.setRecovery_date(new Date());

		GiftBasket giftBasketTest3 = new GiftBasket();
		giftBasketTest3.setName("testRecent3");
		giftBasketTest3.setDescription("test description 3");
		giftBasketTest3.setRecovery_date(new Date());
		
		GiftBasket giftBasketSave1 = giftBasketService.create(enterpriseSave1.getEmail(), giftBasketTest1);
		GiftBasket giftBasketSave2 = giftBasketService.create(enterpriseSave1.getEmail(), giftBasketTest2);
		GiftBasket giftBasketSave3 = giftBasketService.create(enterpriseSave2.getEmail(), giftBasketTest3);
		
		List<GiftBasket> allRecentGiftBaskets = giftBasketService.getAllRecent();
		
		assertTrue(allRecentGiftBaskets != null);
		List<Long> ids = new ArrayList<>();
		for(int i = 0; i < allRecentGiftBaskets.size(); i++) {
			ids.add(allRecentGiftBaskets.get(i).getId());
		}
		assertTrue(!ids.contains(giftBasketSave1.getId()));
		assertTrue(ids.contains(giftBasketSave2.getId()));
		assertTrue(ids.contains(giftBasketSave3.getId()));
		
		giftBasketService.delete(giftBasketSave1);
		giftBasketService.delete(giftBasketSave2);
		giftBasketService.delete(giftBasketSave3);
		enterpriseService.delete(enterpriseSave1);
		enterpriseService.delete(enterpriseSave2);
	}
}
