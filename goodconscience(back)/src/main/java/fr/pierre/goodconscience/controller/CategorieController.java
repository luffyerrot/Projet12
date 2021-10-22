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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.pierre.goodconscience.entity.Categorie;
import fr.pierre.goodconscience.serializer.CategorieSerializable;
import fr.pierre.goodconscience.service.CategorieService;

@CrossOrigin(origins="*", maxAge=3600)
@RestController
@RequestMapping(value = "/categorie")
public class CategorieController {

	@Autowired
	CategorieService categorieService;
	
	CategorieSerializable categorieSerializable = new CategorieSerializable();
	
	@GetMapping("/")
	public ResponseEntity<List<Categorie>> getAll() {
		List<Categorie> categories = categorieService.getAll();
		for (Object o : categories) {
			categorieSerializable.parseCategorie((Categorie)o);
		}
		return ResponseEntity.ok(categories);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Categorie> getById(@PathVariable Long id) {
		Categorie categorie = categorieService.getById(id);
		return ResponseEntity.ok(categorie);
	}
	
	@PutMapping("/create")
	public ResponseEntity<Categorie> create(@RequestBody Categorie categorie) {
		Categorie categorieCreate = categorieService.create(categorie);
		return ResponseEntity.ok(categorieCreate);
	}
	
	@PostMapping("/update")
	public ResponseEntity<Categorie> update(@RequestBody Categorie categorie) {
		Categorie categorieUpdate = categorieService.update(categorie);
		if (categorieUpdate != null) {
			return ResponseEntity.ok(categorieUpdate);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<Void> delete(@RequestBody Categorie categorie) {
		categorieService.delete(categorie);
		return ResponseEntity.ok().build();
	}
}
