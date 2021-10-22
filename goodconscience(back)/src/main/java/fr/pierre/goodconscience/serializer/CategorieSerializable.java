package fr.pierre.goodconscience.serializer;

import fr.pierre.goodconscience.entity.Categorie;

public class CategorieSerializable {

	public Categorie parseCategorie(Categorie categorie) {
		if (categorie.getUsers() != null) {
			categorie.setUsers(null);
		}
		if (categorie.getGiftBaskets() != null) {
			categorie.setGiftBaskets(null);
		}
		return categorie;
	}
}
