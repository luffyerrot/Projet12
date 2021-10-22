package fr.pierre.goodconscience.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import fr.pierre.goodconscience.entity.Categorie;

@SpringBootTest
public class CategorieServiceTest {

	@Autowired
	CategorieService categorieService;
	
	@Test
	@Rollback
	public void createUpdateAndDeleteCategorie() {
		Categorie categorieTest = new Categorie();
		categorieTest.setName("categorieTest");
		categorieTest.setDescription("une description");
		
		Categorie categorieSave = categorieService.create(categorieTest);
		
		assertEquals("categorieTest", categorieSave.getName());
		
		categorieSave.setName("categorieTest1");

		Categorie categorieUpdate = categorieService.update(categorieSave);
		
		assertEquals("categorieTest1", categorieUpdate.getName());
		
		categorieService.delete(categorieUpdate);
	}
}
