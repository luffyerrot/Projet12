package fr.pierre.goodconscience.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.pierre.goodconscience.entity.Enterprise;
import fr.pierre.goodconscience.entity.EnterpriseInformations;

@SpringBootTest
public class EnterpriseServiceTest {

	@Autowired
	EnterpriseService enterpriseService;
	@Autowired
	EnterpriseInformationsService enterpriseInformationsService;
	
	@Test
	public void updateAndDeleteEnterprise() {
		
		Enterprise enterprise = new Enterprise();
		enterprise.setEmail("enterprise@test.fr");
		enterprise.setName("enterprise");
		enterprise.setPassword("12345");
		
		Enterprise enterpriseSave = enterpriseService.createForTest(enterprise);
		
		EnterpriseInformations enterpriseInformationsTest = new EnterpriseInformations();
		enterpriseInformationsTest.setId(enterpriseSave.getId());
		enterpriseInformationsTest.setAdress("rue du test");
		enterpriseInformationsTest.setCountry("Test");
		enterpriseInformationsTest.setNumber(4);
		enterpriseInformationsTest.setPhone_number("0631313131");
		enterpriseInformationsTest.setPostcode("48965");
		enterpriseInformationsTest.setSiret("1234567891234");
		enterpriseInformationsTest.setDescription("description test");
		
		enterpriseInformationsService.create(enterpriseInformationsTest);
		
		assertEquals("enterprise", enterpriseSave.getName());
		
		enterpriseSave.setName("enterprise1");
		
		Enterprise enterpriseUpdate = enterpriseService.update(enterpriseSave);

		assertEquals("enterprise1", enterpriseUpdate.getName());
		
		enterpriseService.delete(enterpriseUpdate);
	}
}
