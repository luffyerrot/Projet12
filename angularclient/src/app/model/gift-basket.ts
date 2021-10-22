import { Categorie } from "./categorie";
import { Enterprise } from "./enterprise";
import { Product } from "./product";

export interface GiftBasket {
    id: string;
    name: string;
    description: string;
    recovery_date: string;
    enterprise: Enterprise;
    products: Product[];
    categories: Categorie[];
}
