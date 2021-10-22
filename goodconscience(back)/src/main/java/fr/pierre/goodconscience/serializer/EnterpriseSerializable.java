package fr.pierre.goodconscience.serializer;

import fr.pierre.goodconscience.entity.Enterprise;

public class EnterpriseSerializable {

	public Enterprise parseEnterprise(Enterprise enterprise) {
		if (enterprise.getEnterpriseInformations() != null) {
			enterprise.getEnterpriseInformations().setEnterprise(null);
		}
		if (enterprise.getGiftBaskets() != null) {
			enterprise.getGiftBaskets().forEach(g->g.setBookings(null));
			enterprise.getGiftBaskets().forEach(g->g.setCategories(null));
			enterprise.getGiftBaskets().forEach(g->g.setEnterprise(null));
			enterprise.getGiftBaskets().forEach(g->g.setProducts(null));
		}
		if (enterprise.getProducts() != null) {
			enterprise.getProducts().forEach(p->p.setEnterprise(null));
			enterprise.getProducts().forEach(p->p.setGiftBaskets(null));
		}
		if (enterprise.getRoles() != null) {
			enterprise.getRoles().forEach(r->r.setEnterprises(null));
			enterprise.getRoles().forEach(r->r.setUsers(null));
		}
		return enterprise;
	}
}
