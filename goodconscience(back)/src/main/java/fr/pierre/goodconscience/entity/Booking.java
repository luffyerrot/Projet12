package fr.pierre.goodconscience.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name="bookings")
public class Booking implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private Date booking_date;
	
	public Booking() {
		
	}
	
	public Booking(Long id, Date booking_date) {
		this.id = id;
		this.booking_date = booking_date;
	}

	@Override
	public String toString() {
		return "Booking [id=" + id + ", booking_date=" + booking_date + "]";
	} 

	//-----------------------------------------

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gift_baskets_id")
	private GiftBasket giftBasket;
}
