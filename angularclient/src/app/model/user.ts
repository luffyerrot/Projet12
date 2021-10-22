import { Categorie } from "./categorie";
import { UserInfos } from "./user-infos";

export interface User {
	id: string;
	username: string;
	email: string;
	linkimg: string;
	password: string;
	userInformations: UserInfos;
    categories: Categorie[];
}
