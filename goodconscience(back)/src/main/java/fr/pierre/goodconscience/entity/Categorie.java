package fr.pierre.goodconscience.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "categories")
public class Categorie implements Serializable {

	@GeneratedValue
	@Id
	private Long id;
	
	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String description;

	@Override
	public String toString() {
		return "Categorie [id=" + id + ", name=" + name + ", description=" + description + "]";
	} 
	
	//-----------------------------------------

	@ManyToMany(mappedBy = "categories")
	private List<GiftBasket> giftBaskets;

	@ManyToMany(mappedBy = "categories")
	private List<User> users;
}
