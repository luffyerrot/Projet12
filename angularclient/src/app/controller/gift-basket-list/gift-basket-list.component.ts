import { Component, OnInit } from '@angular/core';
import { GiftBasket } from 'src/app/model/gift-basket';
import { Lenght } from 'src/app/model/lenght';
import { EnterpriseService } from 'src/app/services/enterprise.service';

@Component({
  selector: 'app-gift-basket-list',
  templateUrl: './gift-basket-list.component.html',
  styleUrls: ['./gift-basket-list.component.css']
})
export class GiftBasketListComponent implements OnInit {

  giftBaskets: GiftBasket[] = [];

  constructor(private enterpriseService: EnterpriseService) {}

  ngOnInit(): void {
    this.enterpriseService.findAllGiftBasket().subscribe(data => {
      this.giftBaskets = data;
    });
  }

  delete(name: string) {
    this.enterpriseService.deleteGiftBaskets(name);
  }
}