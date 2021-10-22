import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Booking } from '../model/booking';
import { Categorie } from '../model/categorie';
import { Enterprise } from '../model/enterprise';
import { EnterpriseInfos } from '../model/enterprise-info';
import { GiftBasket } from '../model/gift-basket';
import { Lenght } from '../model/lenght';
import { Product } from '../model/product';

@Injectable({
  providedIn: 'root'
})
export class EnterpriseService {
  
  enterpriseUrl: string;
  loginUrl: string;
  productUrl: string;
  categorieUrl: string;
  bookingUrl: string;
  giftBasketUrl: string;
  identity: boolean;

  constructor(private http: HttpClient, private router: Router) { 
    this.enterpriseUrl = 'http://localhost:8080/enterprise/';
    this.loginUrl = 'http://localhost:8080/login/';
    this.productUrl = 'http://localhost:8080/product/';
    this.categorieUrl = 'http://localhost:8080/categorie/';
    this.giftBasketUrl = 'http://localhost:8080/giftbasket/';
    this.bookingUrl = 'http://localhost:8080/booking/';
    this.identity = false;
  }
  
  public saveEnterprise(enterprise: Enterprise, enterpriseInfos: EnterpriseInfos) {
    this.http.put<Enterprise>(this.enterpriseUrl + 'create', enterprise).subscribe((response: any) => {

      if (response['id']) {
        enterpriseInfos.id = response['id'];
      }
      if (enterpriseInfos.id != null) {
        this.http.put<EnterpriseInfos>(this.enterpriseUrl + 'createinfos', enterpriseInfos).subscribe(() => this.router.navigate(['/login']));
      }
    });
    return;
  }
  
  public findAll(): Observable<Enterprise[]> {
    return this.http.get<Enterprise[]>(this.enterpriseUrl);
  }

  public findById(id: string): Observable<Enterprise> {
    return this.http.get<Enterprise>(this.enterpriseUrl + "id/" + id);
  }
  
  public updateEnterprise(enterprise: Enterprise, enterpriseInfos: EnterpriseInfos) {
    
    this.http.post<Enterprise>(this.enterpriseUrl + 'update', enterprise).subscribe(() => {
      this.http.post<EnterpriseInfos>(this.enterpriseUrl + 'updateinfos', enterpriseInfos).subscribe(() => this.router.navigate(['/enterprise/infos']));
    });
    return;
  }

  public findAllProduct(): Observable<Product[]> {
    return this.http.get<Product[]>(this.productUrl);
  }

  public findAllCategorie(): Observable<Categorie[]> {
    return this.http.get<Categorie[]>(this.categorieUrl);
  }
  
  public findAllGiftBasket(): Observable<GiftBasket[]> {
    return this.http.get<GiftBasket[]>(this.giftBasketUrl);
  }

  public findAllGiftBasketById(id: string): Observable<GiftBasket[]> {
    return this.http.get<GiftBasket[]>(this.giftBasketUrl + "id/" + id);
  }

  public findAllGiftBasketByIdWithRestrictions(id: string): Observable<GiftBasket[]> {
    return this.http.get<GiftBasket[]>(this.giftBasketUrl + "restrictedId/" + id);
  }
  
  public findAllGiftBasketDate(): Observable<GiftBasket[]> {
    return this.http.get<GiftBasket[]>(this.giftBasketUrl + 'date');
  }
  
  public findAllGiftBasketDateWithRestrictions(): Observable<GiftBasket[]> {
    return this.http.get<GiftBasket[]>(this.giftBasketUrl + 'restrictedDate');
  }

  public saveProduct(product: Product) {
    this.http.put<Product>(this.productUrl + 'create', product).subscribe(() => this.router.navigate(['/enterprise/products']));
  }

  public saveGiftBasket(gift_basket: GiftBasket) {
    this.http.put<Product>(this.giftBasketUrl + 'create', gift_basket).subscribe(() => this.router.navigate(['/enterprise/mygifts']));
  }

  public deleteGiftBaskets(name: string) {
    this.http.delete(this.giftBasketUrl + 'delete/' + name).subscribe(() => this.redirectTo('/enterprise/mygifts'));

  }

  public saveBooking(name: string) {
    this.http.put(this.bookingUrl + 'create', name).subscribe(() => this.router.navigate(['/']));
  }
  
  redirectTo(uri:string) {
    this.router.navigateByUrl('/', {skipLocationChange: true}).then(()=>
    this.router.navigate([uri]));
 }
}