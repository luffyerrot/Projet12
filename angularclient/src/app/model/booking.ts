import { GiftBasket } from "./gift-basket";
import { User } from "./user";

export interface Booking {
    id: string;
    booking_date: string;
    user: User;
    giftBasket: GiftBasket;
}
