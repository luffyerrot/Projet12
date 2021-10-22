import { HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray, FormControl } from '@angular/forms';
import { Categorie } from 'src/app/model/categorie';
import { GiftBasket } from 'src/app/model/gift-basket';
import { Product } from 'src/app/model/product';
import { EnterpriseService } from 'src/app/services/enterprise.service';

@Component({
  selector: 'app-gift-basket-form',
  templateUrl: './gift-basket-form.component.html',
  styleUrls: ['./gift-basket-form.component.css']
})
export class GiftBasketFormComponent implements OnInit {

  productList: Product[] = [];
  categorieList: Categorie[] = [];
  form!: FormGroup;
  productForm!: FormGroup;
  categorieForm!: FormGroup;
  submitted = false;
  
  constructor(private enterpriseService: EnterpriseService, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.enterpriseService.findAllProduct().subscribe(data => {
      this.productList = data;
    });
    this.enterpriseService.findAllCategorie().subscribe(data => {
      this.categorieList = data;
    });
    this.form = this.formBuilder.group(
      {
        name: [
          '',
          [
            Validators.required,
            Validators.minLength(4),
            Validators.maxLength(20)
          ]
        ],
        recovery_date: new Date(),
        description: [
          '',
          [
            Validators.required,
            Validators.minLength(10),
            Validators.maxLength(255)
          ]
        ],
        number: '0',
        products: this.formBuilder.array([]), 
        categories: this.formBuilder.array([])
      }
    );
  }

  get products() {
    return this.form.get("products") as FormArray;
  }

  get categories() {
    return this.form.get("categories") as FormArray;
  }

  getProduct(i: number) {
    return this.products.at(i).value.name;
  }

  compareProduct(product: Product) {
    for (let i = 0; i < this.products.length; i++) {
      if (this.products.at(i).value.id == product.id) {
        return true;
      }
    }
    return false;
  }

  getCategorie(i: number) {
    return this.categories.at(i).value.name;
  }

  compareCategorie(categorie: Categorie) {
    for (let i = 0; i < this.categories.length; i++) {
      if (this.categories.at(i).value.id == categorie.id) {
        return true;
      }
    }
    return false;
  }

  addProduct(id: string, name: string, description: string, linkimg: string) {
    this.productForm = this.formBuilder.group(
      {
        id: new FormControl(id),
        name: new FormControl(name),
        description: new FormControl(description),
        linkimg: new FormControl(linkimg)
      }
    );
    let exist = false;
    for(let i = 0; i < this.products.length; i++) {
      if(this.products.at(i).value.id == this.productForm.value.id) {
        exist = true;
      }
    }
    if (this.products.length == 0 || exist == false) {
      this.products.insert(this.products.length, this.productForm);
    }
  }

  addCategorie(id: string, name: string, description: string) {
    this.categorieForm = this.formBuilder.group(
      {
        id: new FormControl(id),
        name: new FormControl(name),
        description: new FormControl(description)
      }
    );
    let exist = false;
    for(let i = 0; i < this.categories.length; i++) {
      if(this.categories.at(i).value.id == this.categorieForm.value.id) {
        exist = true;
      }
    }
    if (this.categories.length == 0 || exist == false) {
      this.categories.insert(this.categories.length, this.categorieForm);
    }
  }
  
  deleteProduct(productIndex: number) {
    this.products.removeAt(productIndex);
  }
  
  deleteCategorie(categorieIndex: number) {
    this.categories.removeAt(categorieIndex);
  }

  onSubmit() {
    this.submitted = true;
    if (this.form.valid) {
      if (this.form.value.number != 0) {
        for(let i = 0; i < this.form.value.number; i++) {
          this.enterpriseService.saveGiftBasket(this.form.value);
        }
      } else {
        this.enterpriseService.saveGiftBasket(this.form.value);
      }
    }
  }
}