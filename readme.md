# Packaging de l'application :  

Après avoir récupérer l'API REST vous n'avez plus qu'à packager le projet.  
Pour cela ouvrer un invite de commandes à la racine d'un du projet et taper : `mvn package`.

# Exécution du projet :

Une fois cela fait vous pouvez éxécuter la commande : `mvn spring-boot:run` à la racine du projet
dans l'invite de commande.

# Paramétrage de la BDD :

Pour la base de données vous pouvez utiliser les fichiers .SQL dans le fichier nommé __sql__.  
L'un d'eux permet de créer la BDD et les différentes tables et l'autre permet d'insérer les variables.
Le chemin d'accès à la base de données :  
* Hostname = __localhost__
* Port = __3306__
* Username = __root__
* Password = __root__

## Informations de connexion :
	
* Profil Utilisateur : mail - *user@gmail.com* | mot de passe - *user*
* Profil Administrateur : mail - *admin@gmail.com* | mot de passe - *admin*
* Profil Utilisateur Entreprise : mail - *user@enterprise.com* | mot de passe - *user*
* Profil Administrateur Entreprise : mail - *admin@enterprise.com* | mot de passe - *admin*

# Informations utiles :

* *Gestionnaire de projets* - __Maven__ | version - __4.0.0__
* *Frameworks*
	* __Spring Boot__ | version - __2.5.4__
	* __Hibernate__
	* __Lombok__ | version - __1.18.20__
	* __Json Web Token__ | version - __0.9.1__
* *Java* - version - __1.8__
* *Angular* - version - __12.2.2__
