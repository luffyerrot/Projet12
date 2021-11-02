package fr.pierre.goodconscience.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.pierre.goodconscience.entity.Categorie;
import fr.pierre.goodconscience.entity.User;
import fr.pierre.goodconscience.entity.UserInformations;
import fr.pierre.goodconscience.security.util.jwtUtil;
import fr.pierre.goodconscience.serializer.UserInformationsSerializable;
import fr.pierre.goodconscience.serializer.UserSerializable;
import fr.pierre.goodconscience.service.CategorieService;
import fr.pierre.goodconscience.service.UserInformationsService;
import fr.pierre.goodconscience.service.UserService;

@CrossOrigin(origins="*", maxAge=3600)
@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	CategorieService categorieService;
	@Autowired
	UserInformationsService userInformationsService;
	@Autowired
	jwtUtil jwtUtil;
	
	UserSerializable userSerializable = new UserSerializable();
	UserInformationsSerializable userInformationsSerializable = new UserInformationsSerializable();

	/** 
	 * Renvoi tous les utilisateurs
	 **/
	@GetMapping("/")
	public ResponseEntity<List<User>> getAll() {
		List<User> users = userService.getAll();
		for (Object o : users) {
			userSerializable.parseUser((User)o);
		}
		return ResponseEntity.ok(users);
	}

	/** 
	 * Renvoi un utilisateur grace à son id
	 **/
	@GetMapping("/{id}")
	public ResponseEntity<User> getById(@PathVariable Long id) {
		User user = userService.getById(id);
		return ResponseEntity.ok(user);
	}

	/** 
	 * Créer un utilisateur
	 **/
	@PutMapping("/create")
	public ResponseEntity<User> create(@RequestBody User user) {
		User userCreate = userService.create(user);
		if (userCreate != null) {
			return ResponseEntity.ok(userSerializable.parseUser(userCreate));
		}
		return ResponseEntity.notFound().build();
	}

	/** 
	 * Ajoute une catégorie à l'utilisateur connecté
	 **/
	@PutMapping("/addRestriction")
	public ResponseEntity<Void> createRestriction(@RequestBody Categorie categorie, @RequestHeader("Authorization") String token) {
		String email = jwtUtil.extractUsername(token.substring(7));
		Categorie categorieGetById = categorieService.getById(categorie.getId());
		if (userService.findByEmail(email) != null && categorieGetById != null) {
			User userUpdate = userService.addRestriction(email, categorieGetById);
			if (userUpdate != null) {
				return ResponseEntity.ok().build();
			}
		}
		return ResponseEntity.notFound().build();
	}

	/** 
	 * Créer ou met à jour les informations personnel d'un utilisateur
	 **/
	@PutMapping("/changeInfos")
	public ResponseEntity<UserInformations> changeInfos(@RequestBody UserInformations userInformations) {
		if (userInformationsService.getById(userInformations.getId()) != null) {
			UserInformations userInfosCreate = userInformationsService.create(userInformations);
			if (userInfosCreate != null) {
				return ResponseEntity.ok(userInformationsSerializable.parseUserInformations(userInfosCreate));
			}
		} else {
			UserInformations userInfosUpdate = userInformationsService.create(userInformations);
			if (userInfosUpdate != null) {
				return ResponseEntity.ok(userInformationsSerializable.parseUserInformations(userInfosUpdate));
			}
		}
		return ResponseEntity.notFound().build();
	}

	/** 
	 * Met à jour un utilisateur
	 **/
	@PostMapping("/update")
	public ResponseEntity<User> update(@RequestBody User user) {
		User userUpdate = userService.update(user);
		if (userUpdate != null) {
			return ResponseEntity.ok(userSerializable.parseUser(userUpdate));
		}
		return ResponseEntity.notFound().build();
	}

	/** 
	 * Supprime un utilisateur
	 **/
	@DeleteMapping("/delete")
	public ResponseEntity<Void> delete(@RequestBody User user) {
		userService.delete(user);
		return ResponseEntity.ok().build();
	}

	/** 
	 * Supprime une categorie de l'utilisateur connecté
	 **/
	@DeleteMapping("/deleteRestriction/{id}")
	public ResponseEntity<Void> deleteRestriction(@PathVariable Long id, @RequestHeader("Authorization") String token) {
		String email = jwtUtil.extractUsername(token.substring(7));
		if (userService.findByEmail(email) != null) {
			User user = userService.findByEmail(email);
			Categorie categorie = categorieService.getById(id);
			if (categorie != null && user.getCategories().contains(categorie)) {
				user.getCategories().remove(categorie);
				User userUpdate = userService.update(user);
				if (userUpdate != null) {
					return ResponseEntity.ok().build();
				}
			}
		}
		return ResponseEntity.notFound().build();
	}

	/** 
	 * Renvoi un utilisateur grace à son email
	 **/
	@GetMapping("/find/{email}")
	public ResponseEntity<User> getByEmail(@PathVariable String email) {
		User userByEmail = userService.findByEmail(email);
		if (userByEmail != null) {
			userSerializable.parseUser(userByEmail);
			return ResponseEntity.ok(userByEmail);
		}
		return ResponseEntity.notFound().build();
	}
}
