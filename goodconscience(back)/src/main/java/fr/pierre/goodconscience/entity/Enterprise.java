package fr.pierre.goodconscience.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "enterprises")
public class Enterprise implements Serializable {

	@Id
	@GeneratedValue
	@Column(name = "enterprise_id")
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String linkimg;
	
	public Enterprise() {
		
	}
	
	public Enterprise(Long id, String email, String name) {
		this.id = id;
		this.email = email;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Enterprise [id=" + id + ", email=" + email + ", name=" + name + ", password=" + password + "]";
	} 
	
	//-----------------------------------------
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "enterprise_role", joinColumns = {@JoinColumn(name = "enterprise_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
	private List<Role> roles;

	@OneToOne(mappedBy = "enterprise")
	private EnterpriseInformations enterpriseInformations;
	
	@OneToMany(mappedBy = "enterprise")
	private List<GiftBasket> giftBaskets;
	
	@OneToMany(mappedBy = "enterprise")
	private List<Product> products;
}
