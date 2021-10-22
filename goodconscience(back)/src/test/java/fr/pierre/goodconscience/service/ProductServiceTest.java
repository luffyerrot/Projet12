package fr.pierre.goodconscience.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import fr.pierre.goodconscience.entity.Product;

@SpringBootTest
public class ProductServiceTest {

	@Autowired
	ProductService productService;
	
	@Test
	@Rollback
	public void createUpdateAndDeleteProduct() {
		Product productTest = new Product();
		productTest.setName("test");
		productTest.setDescription("test description");
		
		Product productSave = productService.create(productTest);
		
		assertEquals("test", productSave.getName());
		
		productSave.setName("test1");
		
		Product productUpdate = productService.update(productSave);

		assertEquals("test1", productUpdate.getName());
		
		productService.delete(productUpdate);
	}
}
