package fr.pierre.goodconscience.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "user_informations")
public class UserInformations implements Serializable {
	
	@Id
	@Column(name = "user_id")
	private Long id;

	@Column(nullable = true)
	private String firstname;

	@Column(nullable = true)
	private String name;

	@Column(nullable = true)
	private Date birthday;

	@Column(nullable = true)
	private Integer number;

	@Column(nullable = true)
	private String adress;

	@Column(nullable = true)
	private String country;

	@Column(nullable = true)
	private Integer postcode;

	@Column(nullable = true)
	private String phone_number;
	
	public UserInformations() {
		
	}

	public UserInformations(Long id, String firstname, String name, Date birthday, Integer number, String adress, String country, Integer postcode, String phone_number) {
		this.id = id;
		this.firstname = firstname;
		this.name = name;
		this.birthday = birthday;
		this.number = number;
		this.adress = adress;
		this.country = country;
		this.postcode = postcode;
		this.phone_number = phone_number;
	}

	@Override
	public String toString() {
		return "UserInformations [id=" + id + ", firstname=" + firstname + ", name=" + name + ", birthday=" + birthday
				+ ", number=" + number + ", adress=" + adress + ", country=" + country + ", postcode=" + postcode
				+ ", phone_number=" + phone_number + "]";
	}

	//-----------------------------------------

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn(name="user_id", referencedColumnName="user_id")
	private User user;
}
