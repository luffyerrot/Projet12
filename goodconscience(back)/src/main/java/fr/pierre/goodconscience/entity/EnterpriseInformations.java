package fr.pierre.goodconscience.entity;

import java.io.Serializable;

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
@Table(name = "enterprise_informations")
public class EnterpriseInformations implements Serializable {
	
	@Id
	@Column(name = "enterprise_id")
	private Long id;

	@Column(nullable = false)
	private String siret;

	@Column(nullable = false)
	private Integer number;

	@Column(nullable = false)
	private String adress;

	@Column(nullable = false)
	private String country;

	@Column(nullable = false)
	private String postcode;

	@Column(nullable = false)
	private String phone_number;

	@Column(nullable = false)
	private String description;

	@Override
	public String toString() {
		return "EnterpriseInformations [id=" + id + ", siret=" + siret + ", number=" + number + ", adress=" + adress
				+ ", country=" + country + ", postcode=" + postcode + ", phone_number=" + phone_number + ", description=" + description + "]";
	} 

	//-----------------------------------------

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn(name="enterprise_id", referencedColumnName="enterprise_id")
	private Enterprise enterprise;
}
