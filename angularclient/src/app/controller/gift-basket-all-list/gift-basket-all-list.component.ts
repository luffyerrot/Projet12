import { Component, OnInit } from '@angular/core';
import { GiftBasket } from 'src/app/model/gift-basket';
import { EnterpriseService } from 'src/app/services/enterprise.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-gift-basket-all-list',
  templateUrl: './gift-basket-all-list.component.html',
  styleUrls: ['./gift-basket-all-list.component.css']
})
export class GiftBasketAllListComponent implements OnInit {

  giftBaskets: GiftBasket[] = [];

  constructor(private enterpriseService: EnterpriseService, private userService: UserService) {}

  ngOnInit(): void {
    this.enterpriseService.findAllGiftBasketDate().subscribe(data => {
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
