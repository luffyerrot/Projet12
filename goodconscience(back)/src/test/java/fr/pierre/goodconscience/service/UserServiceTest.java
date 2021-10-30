package fr.pierre.goodconscience.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.pierre.goodconscience.entity.User;

@SpringBootTest
public class UserServiceTest {

	@Autowired
	UserService userService;
	
	@Test
	public void createUpdateAndDeleteUser() {

		User user = new User();
		user.setEmail("test@test.test");
		user.setUsername("test");
		user.setPassword("123456");
		
		User userSave = userService.createForTest(user);
		
		assertEquals("test", userSave.getUsername());
		
		userSave.setUsername("test1");

		User userUpdate = userService.update(userSave);

		assertEquals("test1", userUpdate.getUsername());
		
		userService.delete(userUpdate);
	}
}
