package fr.pierre.goodconscience.serializer;

import fr.pierre.goodconscience.entity.Booking;

public class BookingSerializable {

	public Booking parseBooking(Booking booking) {
		booking.getUser().getUserInformations().setUser(null);
		booking.getUser().setBookings(null);
		booking.getUser().setCategories(null);
		booking.getUser().setRoles(null);
		booking.getGiftBasket().setBookings(null);
		booking.getGiftBasket().setCategories(null);
		booking.getGiftBasket().setProducts(null);
		booking.getGiftBasket().getEnterprise().setGiftBaskets(null);
		booking.getGiftBasket().getEnterprise().setPassword(null);
		booking.getGiftBasket().getEnterprise().setProducts(null);
		booking.getGiftBasket().getEnterprise().setRoles(null);
		booking.getGiftBasket().getEnterprise().getEnterpriseInformations().setEnterprise(null);
		return booking;
	}
}
