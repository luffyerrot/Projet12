package fr.pierre.goodconscience.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import fr.pierre.goodconscience.entity.Enterprise;

@SpringBootTest
public class EnterpriseServiceTest {

	@Autowired
	EnterpriseService enterpriseService;
	
	@Test
	@Rollback
	public void createUpdateAndDeleteEnterprise() {
		Enterprise enterpriseTest = new Enterprise();
		enterpriseTest.setName("test");
		enterpriseTest.setPassword("123456");
		enterpriseTest.setEmail("test1@test.test");
		
		Enterprise enterpriseSave = enterpriseService.create(enterpriseTest);
		
		assertEquals("test", enterpriseSave.getName());
		
		enterpriseSave.setName("test1");
		
		Enterprise enterpriseUpdate = enterpriseService.update(enterpriseTest);

		assertEquals("test1", enterpriseUpdate.getName());
		
		enterpriseService.delete(enterpriseUpdate);
	}
}
