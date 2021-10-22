package fr.pierre.goodconscience.serializer;

import fr.pierre.goodconscience.entity.User;

public class UserSerializable {

	public User parseUser(User user) {
		if (user.getUserInformations() != null) {
			user.getUserInformations().setUser(null);
		}
		if (user.getBookings() != null) {
			user.getBookings().forEach(b->b.setGiftBasket(null));
			user.getBookings().forEach(b->b.setUser(null));
		}
		if (user.getCategories() != null) {
			user.getCategories().forEach(c->c.setGiftBaskets(null));
			user.getCategories().forEach(c->c.setUsers(null));
		}
		user.getRoles().forEach(r->r.setEnterprises(null));
		user.getRoles().forEach(r->r.setUsers(null));
		return user;
	}
}
