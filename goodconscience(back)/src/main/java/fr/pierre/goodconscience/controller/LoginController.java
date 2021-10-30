package fr.pierre.goodconscience.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.pierre.goodconscience.entity.Enterprise;
import fr.pierre.goodconscience.entity.User;
import fr.pierre.goodconscience.security.util.jwtUtil;
import fr.pierre.goodconscience.serializer.EnterpriseSerializable;
import fr.pierre.goodconscience.serializer.UserSerializable;
import fr.pierre.goodconscience.service.EnterpriseService;
import fr.pierre.goodconscience.service.UserService;
import net.minidev.json.JSONObject;

@CrossOrigin(origins="*", maxAge=3600)
@RestController
@RequestMapping(value = "/login")
public class LoginController {

	@Autowired
	UserService userService;
	@Autowired
	EnterpriseService enterpriseService;
	@Autowired
	jwtUtil jwtUtil;

	UserSerializable userSerializable = new UserSerializable();
	EnterpriseSerializable enterpriseSerializable = new EnterpriseSerializable();

	/** 
	 * Renvoi le token lors du login selon qu'il soit un client ou une entreprise
	 **/
	@GetMapping("/")
	public ResponseEntity<JSONObject> login(@RequestHeader("email") String email, @RequestHeader("password") String password) {
		email = email.toLowerCase();
		JSONObject token = new JSONObject();
		if (userService.findByEmail(email) != null) {
			if (userService.login(email, password)) {
				token.put("token", jwtUtil.generateToken(email));
				return ResponseEntity.ok(token);
			}
		} else if (enterpriseService.findByEmail(email) != null) {
			if (enterpriseService.login(email, password)) {
				token.put("token", jwtUtil.generateToken(email));
				return ResponseEntity.ok(token);
			}
		}
		return ResponseEntity.notFound().build();
	}

	/** 
	 * Renvoi le profil utilisateur ou entreprise connecté
	 **/
	@GetMapping("/me")
	public ResponseEntity<Object> me(@RequestHeader("Authorization") String token) {
		String email = jwtUtil.extractUsername(token.substring(7));
		if (userService.findByEmail(email) != null) {
			User user = userService.findByEmail(email);
			return ResponseEntity.ok(((Object)userSerializable.parseUser(user)));
		} else if (enterpriseService.findByEmail(email) != null) {
			Enterprise enterprise = enterpriseService.findByEmail(email);
			return ResponseEntity.ok(((Object)enterpriseSerializable.parseEnterprise(enterprise)));
		}
		return ResponseEntity.notFound().build();
	}
}
