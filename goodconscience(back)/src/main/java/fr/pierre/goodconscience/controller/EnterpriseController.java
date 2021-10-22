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

import fr.pierre.goodconscience.entity.Enterprise;
import fr.pierre.goodconscience.entity.EnterpriseInformations;
import fr.pierre.goodconscience.serializer.EnterpriseInformationsSerializable;
import fr.pierre.goodconscience.serializer.EnterpriseSerializable;
import fr.pierre.goodconscience.service.EnterpriseInformationsService;
import fr.pierre.goodconscience.service.EnterpriseService;

@CrossOrigin(origins="*", maxAge=3600)
@RestController
@RequestMapping(value = "/enterprise")
public class EnterpriseController {

	@Autowired
	EnterpriseService enterpriseService;
	@Autowired
	EnterpriseInformationsService enterpriseInformationsService;
	
	EnterpriseSerializable enterpriseSerializable = new EnterpriseSerializable();
	EnterpriseInformationsSerializable enterpriseInformationsSerializable = new EnterpriseInformationsSerializable();
	
	@GetMapping("/")
	public ResponseEntity<List<Enterprise>> getAll() {
		List<Enterprise> enterprises = enterpriseService.getAll();
		for (Object o : enterprises) {
			enterpriseSerializable.parseEnterprise((Enterprise)o);
		}
		return ResponseEntity.ok(enterprises);
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Enterprise> getById(@PathVariable Long id) {
		Enterprise enterprise = enterpriseService.getById(id);
		return ResponseEntity.ok(enterpriseSerializable.parseEnterprise(enterprise));
	}
	
	@PutMapping("/create")
	public ResponseEntity<Enterprise> create(@RequestBody Enterprise enterprise) {
		Enterprise enterpriseCreate = enterpriseService.create(enterprise);
		if (enterpriseCreate != null) {
			return ResponseEntity.ok(enterpriseSerializable.parseEnterprise(enterpriseCreate));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/createinfos")
	public ResponseEntity<EnterpriseInformations> createinfos(@RequestBody EnterpriseInformations enterpriseInformations) {
		EnterpriseInformations enterpriseInfosCreate = enterpriseInformationsService.create(enterpriseInformations);
		if (enterpriseInfosCreate != null) {
			return ResponseEntity.ok(enterpriseInformationsSerializable.parseEnterpriseInformations(enterpriseInfosCreate));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/update")
	public ResponseEntity<Enterprise> update(@RequestBody Enterprise enterprise) {
		Enterprise enterpriseUpdate = enterpriseService.update(enterprise);
		if (enterpriseUpdate != null) {
			return ResponseEntity.ok(enterpriseSerializable.parseEnterprise(enterpriseUpdate));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/updateinfos")
	public ResponseEntity<EnterpriseInformations> updateinfos(@RequestBody EnterpriseInformations enterpriseInformations) {
		System.out.println(enterpriseInformations.toString() + "------------updateinfos");
		EnterpriseInformations enterpriseInfosUpdate = enterpriseInformationsService.update(enterpriseInformations);
		if (enterpriseInfosUpdate != null) {
			return ResponseEntity.ok(enterpriseInformationsSerializable.parseEnterpriseInformations(enterpriseInfosUpdate));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<Void> delete(@RequestBody Enterprise enterprise) {
		enterpriseService.delete(enterprise);
		return ResponseEntity.ok().build();
	}
}
