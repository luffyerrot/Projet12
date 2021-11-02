package fr.pierre.goodconscience.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "gift_baskets")
public class GiftBasket implements Serializable {

	@GeneratedValue
	@Id
	private Long id;
	
	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private Date recovery_date;

	@Override
	public String toString() {
		return "GiftBasket [id=" + id + ", name=" + name + ", description=" + description + ", recovery_date="
				+ recovery_date + "]";
	} 

	//-----------------------------------------

	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "gift_basket_categorie", joinColumns = {@JoinColumn(name = "gift_basket_id")}, inverseJoinColumns = {@JoinColumn(name = "categorie_id")})
	private List<Categorie> categories;
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "gift_basket_product", joinColumns = {@JoinColumn(name = "gift_basket_id")}, inverseJoinColumns = {@JoinColumn(name = "product_id")})
	private List<Product> products;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "enterprise_id")
	private Enterprise enterprise;
	
	@OneToMany(mappedBy = "giftBasket")
	private List<Booking> bookings;
}
