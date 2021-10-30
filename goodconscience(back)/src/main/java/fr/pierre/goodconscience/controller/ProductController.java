package fr.pierre.goodconscience.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.pierre.goodconscience.entity.Enterprise;
import fr.pierre.goodconscience.entity.Product;
import fr.pierre.goodconscience.security.util.jwtUtil;
import fr.pierre.goodconscience.serializer.ProductSerializable;
import fr.pierre.goodconscience.service.EnterpriseService;
import fr.pierre.goodconscience.service.GiftBasketService;
import fr.pierre.goodconscience.service.ProductService;

@CrossOrigin(origins="*", maxAge=3600)
@RestController
@RequestMapping(value = "/product")
public class ProductController {

	@Autowired
	ProductService productService;
	@Autowired
	GiftBasketService giftBasketService;
	@Autowired
	EnterpriseService enterpriseService;
	@Autowired
	jwtUtil jwtUtil;
	
	ProductSerializable productSerializable = new ProductSerializable();

	/** 
	 * Renvoi tous les produits de l'entreprise connecté
	 **/
	@GetMapping("/")
	public ResponseEntity<List<Product>> getAll(@RequestHeader("Authorization") String token) {
		String email = jwtUtil.extractUsername(token.substring(7));
		if (enterpriseService.findByEmail(email) != null) {
			Enterprise enterprise = enterpriseService.findByEmail(email);
			List<Product> products = productService.getByEnterpriseId(enterprise.getId());
			for (Object o : products) {
				productSerializable.parseProduct((Product)o);
			}
			return ResponseEntity.ok(products);
		}
		return ResponseEntity.notFound().build();
	}

	/** 
	 * Créer un produit pour l'entreprise connecté
	 **/
	@PutMapping("/create")
	public ResponseEntity<Product> create(@RequestHeader("Authorization") String token, @RequestBody Product product) {
		String email = jwtUtil.extractUsername(token.substring(7));
		if (enterpriseService.findByEmail(email) != null) {
			product.setEnterprise(enterpriseService.findByEmail(email));
			Product productCreate = productService.create(product);
			return ResponseEntity.ok(productSerializable.parseProduct(productCreate));
		}
		return ResponseEntity.notFound().build();
	}

	/** 
	 * Met à jour un produit
	 **/
	@PostMapping("/update")
	public ResponseEntity<Product> update(@RequestBody Product product) {
		Product productCreate = productService.update(product);
		if (productCreate != null) {
			return ResponseEntity.ok(productCreate);
		}
		return ResponseEntity.notFound().build();
	}
}
