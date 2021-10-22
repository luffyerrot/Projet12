package fr.pierre.goodconscience.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.pierre.goodconscience.entity.Product;
import fr.pierre.goodconscience.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	public Product getById(Long id) {
		this.logger.debug("getById Call = " + id);
		Product product = productRepository.findById(id).get();
		this.logger.debug("getById Return = " + product);
		return product;
	}
	
	public List<Product> getByEnterpriseId(Long id) {
		this.logger.debug("getByEnterpriseId Call = " + id);
		List<Product> products = productRepository.findByEnterpriseId(id);
		this.logger.debug("getByEnterpriseId Return = " + products);
		return products;
	}
	
	public List<Product> getAll() {
		List<Product> products = productRepository.findAll();
		this.logger.debug("getAll Return = " + products);
		return products;
	}
	
	public Product create(Product product) {
		this.logger.debug("create Call = " + product);
		Product productSave = productRepository.save(product);
		this.logger.debug("create Return = " + productSave);
		return productSave;
	}
	
	public Product update(Product product) {
		this.logger.debug("update Call = " + product);
		if (productRepository.getById(product.getId()) != null) {
			Product productSave = productRepository.save(product);
			this.logger.debug("update Return = " + productSave);
			return productSave;
		}
		this.logger.debug("update Return = " + null);
		return null;
	}
	
	public void delete(Product product) {
		this.logger.debug("delete Call = " + product);
		if (productRepository.getById(product.getId()) != null) {
			productRepository.delete(product);
			return;
		}
		this.logger.debug("can't delete : " + product);
	}
}
