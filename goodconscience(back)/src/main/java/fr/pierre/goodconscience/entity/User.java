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
@Table(name="users")
public class User implements Serializable {
	
	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String linkimg;

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", username=" + username + ", password=" + password + "]";
	}
	
	//-----------------------------------------
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
	private List<Role> roles;
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "restrictions", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "categorie_id")})
	private List<Categorie> categories;
	
	@OneToOne(mappedBy = "user")
	private UserInformations userInformations;
	
	@OneToMany(mappedBy = "user")
	private List<Booking> bookings;
}
