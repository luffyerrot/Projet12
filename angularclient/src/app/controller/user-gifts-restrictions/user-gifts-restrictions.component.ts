import { Component, OnInit } from '@angular/core';
import { GiftBasket } from 'src/app/model/gift-basket';
import { EnterpriseService } from 'src/app/services/enterprise.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-gifts-restrictions',
  templateUrl: './user-gifts-restrictions.component.html',
  styleUrls: ['./user-gifts-restrictions.component.css']
})
export class UserGiftsRestrictionsComponent implements OnInit {

  giftBaskets: GiftBasket[] = [];

  constructor(private enterpriseService: EnterpriseService, private userService: UserService) {}

  ngOnInit(): void {
    this.enterpriseService.findAllGiftBasketDateWithRestrictions().subscribe(data => {
      this.giftBaskets = data;
    });
  }

  connected() {
    return this.userService.identity ? true : false;
  }

  booking(name: string) {
    this.enterpriseService.saveBooking(name);
  }
}
