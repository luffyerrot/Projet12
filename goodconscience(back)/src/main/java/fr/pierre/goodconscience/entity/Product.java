package fr.pierre.goodconscience.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "products")
public class Product implements Serializable {

	@GeneratedValue
	@Id
	private Long id;
	
	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String linkimg;

	@Column(nullable = false)
	private String description;

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + "]";
	}
	
	//-----------------------------------------
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "enterprise_id")
	private Enterprise enterprise; 

	@ManyToMany(mappedBy = "products")
	private List<GiftBasket> giftBaskets;
}
