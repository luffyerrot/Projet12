package fr.pierre.goodconscience.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import fr.pierre.goodconscience.entity.UserInformations;

@SpringBootTest
public class UserInformationsServiceTest {

	@Autowired
	UserInformationsService userInformationsService;
	
	@Test
	@Rollback
	public void createUpdateAndDeleteUserInformations() {
		UserInformations userInformationsTest = new UserInformations();
		userInformationsTest.setId(1L);
		userInformationsTest.setAdress("test adress");
		Date actualDate = new Date();
		userInformationsTest.setBirthday(actualDate);
		userInformationsTest.setCountry("test country");
		userInformationsTest.setFirstname("test");
		userInformationsTest.setName("test");
		userInformationsTest.setNumber(8);
		userInformationsTest.setPhone_number("0685858542");
		userInformationsTest.setPostcode(78526);
		
		UserInformations userInformationsSave = userInformationsService.create(userInformationsTest);
		
		assertEquals("test", userInformationsSave.getName());
		assertEquals(8, userInformationsSave.getNumber());
		assertEquals(actualDate, userInformationsSave.getBirthday());
		
		Date newDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(newDate);
		calendar.set(Calendar.DATE, -30);
		newDate = calendar.getTime();
		
		userInformationsSave.setName("test1");
		userInformationsSave.setNumber(25);
		userInformationsSave.setBirthday(newDate);
		
		UserInformations userInformationsUpdate = userInformationsService.update(userInformationsSave);

		assertEquals("test1", userInformationsUpdate.getName());
		assertEquals(25, userInformationsUpdate.getNumber());
		assertEquals(newDate, userInformationsUpdate.getBirthday());
		
		userInformationsService.delete(userInformationsUpdate);
	}
}
