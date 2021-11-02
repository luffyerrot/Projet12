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
@Table(name = "roles")
public class Role implements Serializable {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String name;

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}
	
	//-----------------------------------------
	
	@ManyToMany(mappedBy = "roles")
	private List<User> users;

	@ManyToMany(mappedBy = "roles")
	private List<Enterprise> enterprises;
}
