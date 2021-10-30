package fr.pierre.goodconscience.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import fr.pierre.goodconscience.entity.EnterpriseInformations;

@SpringBootTest
public class EnterpriseInformationsServiceTest {

	@Autowired
	EnterpriseInformationsService enterpriseInformationsService;
	
	@Test
	@Rollback
	public void createUpdateAndDeleteEnterpriseInformations() {
		
		EnterpriseInformations enterpriseInformationsTest = new EnterpriseInformations();
		enterpriseInformationsTest.setId(30L);
		enterpriseInformationsTest.setAdress("rue du test");
		enterpriseInformationsTest.setCountry("Test");
		enterpriseInformationsTest.setNumber(4);
		enterpriseInformationsTest.setPhone_number("0631313131");
		enterpriseInformationsTest.setPostcode("48965");
		enterpriseInformationsTest.setSiret("1234567891234");
		enterpriseInformationsTest.setDescription("description test");
		
		EnterpriseInformations enterpriseInformationsSave = enterpriseInformationsService.create(enterpriseInformationsTest);
		
		assertEquals("rue du test", enterpriseInformationsSave.getAdress());
		assertEquals(4, enterpriseInformationsSave.getNumber());
		
		enterpriseInformationsSave.setAdress("rue du test2");
		enterpriseInformationsSave.setNumber(22);
		
		EnterpriseInformations enterpriseInformationsUpdate = enterpriseInformationsService.update(enterpriseInformationsSave);
		
		assertEquals("rue du test2", enterpriseInformationsUpdate.getAdress());
		assertEquals(22, enterpriseInformationsUpdate.getNumber());
		
		enterpriseInformationsService.delete(enterpriseInformationsUpdate);
	}
}
