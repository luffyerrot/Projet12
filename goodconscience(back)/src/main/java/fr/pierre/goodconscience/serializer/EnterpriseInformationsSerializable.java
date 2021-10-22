package fr.pierre.goodconscience.serializer;

import fr.pierre.goodconscience.entity.EnterpriseInformations;

public class EnterpriseInformationsSerializable {

	public EnterpriseInformations parseEnterpriseInformations(EnterpriseInformations enterpriseInformations) {
		enterpriseInformations.setEnterprise(null);
		return enterpriseInformations;
	}
}
