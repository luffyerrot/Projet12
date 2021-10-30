import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/model/product';
import { EnterpriseService } from 'src/app/services/enterprise.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  products: Product[] = [];

  constructor(private enterpriseService: EnterpriseService) {}

  ngOnInit(): void {
    this.enterpriseService.findAllProduct().subscribe(data => {
      this.products = data;
    });
  }
}