import { Categorie } from "./categorie";
import { Enterprise } from "./enterprise";

export interface Product {
    id: string;
    name: string;
    linkimg: string;
    description: string;
    enterprise: Enterprise;
    categories: Categorie[];
}
