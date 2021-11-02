package fr.pierre.goodconscience.serializer;

import fr.pierre.goodconscience.entity.Product;

public class ProductSerializable {

	public Product parseProduct(Product product) {
		if (product.getEnterprise() != null) {
			product.setEnterprise(null);
		}
		if (product.getGiftBaskets() != null) {
			product.getGiftBaskets().forEach(g->g.setBookings(null));
			product.getGiftBaskets().forEach(g->g.setCategories(null));
			product.getGiftBaskets().forEach(g->g.setEnterprise(null));
			product.getGiftBaskets().forEach(g->g.setProducts(null));
		}
		return product;
	}
}
