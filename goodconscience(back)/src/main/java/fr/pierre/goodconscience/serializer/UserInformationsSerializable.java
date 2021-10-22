package fr.pierre.goodconscience.serializer;

import fr.pierre.goodconscience.entity.UserInformations;

public class UserInformationsSerializable {

	public UserInformations parseUserInformations(UserInformations userInformations) {
		userInformations.setUser(null);
		return userInformations;
	}
}