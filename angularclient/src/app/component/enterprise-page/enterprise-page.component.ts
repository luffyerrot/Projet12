import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { GiftBasket } from 'src/app/model/gift-basket';
import { EnterpriseService } from 'src/app/services/enterprise.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-enterprise-page',
  templateUrl: './enterprise-page.component.html',
  styleUrls: ['./enterprise-page.component.css']
})
export class EnterprisePageComponent implements OnInit {
  
  enterprise_id: string;
  enterprise = {id: '', name: '', email: '', linkimg: '', password: '', enterpriseInformations: {
    id: '', siret: '', adress: '', country: '', number: '', postcode: '', description: '', phone_number: ''
  }};
  giftBaskets: GiftBasket[] = [];

  constructor(private actRoute: ActivatedRoute, private enterpriseService: EnterpriseService, private userService: UserService) {
    this.enterprise_id = this.actRoute.snapshot.params.id;
  }

  ngOnInit(): void {
    this.enterpriseService.findById(this.enterprise_id).subscribe(data => {
      this.enterprise = data;
    });

    this.enterpriseService.findAllGiftBasketById(this.enterprise_id).subscribe(data => {
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
