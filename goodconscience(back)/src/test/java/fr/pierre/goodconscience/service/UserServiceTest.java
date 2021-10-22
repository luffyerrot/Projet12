package fr.pierre.goodconscience.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import fr.pierre.goodconscience.entity.User;

@SpringBootTest
public class UserServiceTest {

	@Autowired
	UserService userService;
	
	@Test
	@Rollback
	public void createUpdateAndDeleteUser() {
		User userTest = new User();
		userTest.setEmail("test@test.test");
		userTest.setUsername("test");
		userTest.setPassword("123456");
		
		User userSave = userService.create(userTest);
		
		assertEquals("test", userSave.getUsername());
		
		userSave.setUsername("test1");

		User userUpdate = userService.update(userSave);

		assertEquals("test1", userUpdate.getUsername());
		
		userService.delete(userUpdate);
	}
}
