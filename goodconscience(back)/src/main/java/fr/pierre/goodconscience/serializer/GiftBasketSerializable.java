package fr.pierre.goodconscience.serializer;

import fr.pierre.goodconscience.entity.GiftBasket;

public class GiftBasketSerializable {
	
	EnterpriseSerializable enterpriseSerializable = new EnterpriseSerializable();

	public GiftBasket parseGiftBasket(GiftBasket giftBasket) {
		if (giftBasket.getBookings() != null) {
			giftBasket.getBookings().forEach(b->b.setGiftBasket(null));
			giftBasket.getBookings().forEach(b->b.setUser(null));
		}
		if (giftBasket.getCategories() != null) {
			giftBasket.getCategories().forEach(c->c.setGiftBaskets(null));
			giftBasket.getCategories().forEach(c->c.setUsers(null));
		}
		if (giftBasket.getEnterprise() != null) {
			if (giftBasket.getEnterprise().getEnterpriseInformations() != null) {
				giftBasket.getEnterprise().getEnterpriseInformations().setEnterprise(null);
			}
			if (giftBasket.getEnterprise().getGiftBaskets() != null) {
				giftBasket.getEnterprise().setGiftBaskets(null);
			}
			if (giftBasket.getEnterprise().getProducts() != null) {
				giftBasket.getEnterprise().getProducts().forEach(p->p.setEnterprise(null));
				giftBasket.getEnterprise().getProducts().forEach(p->p.setGiftBaskets(null));
			}
			if (giftBasket.getEnterprise().getRoles() != null) {
				giftBasket.getEnterprise().getRoles().forEach(r->r.setEnterprises(null));
				giftBasket.getEnterprise().getRoles().forEach(r->r.setUsers(null));
			}
		}
		if (giftBasket.getProducts() != null) {
			giftBasket.getProducts().forEach(p->p.setEnterprise(null));
			giftBasket.getProducts().forEach(p->p.setGiftBaskets(null));
		}
		return giftBasket;
	}
}
