import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../model/user';
import { UserInfos } from '../model/user-infos';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { Booking } from '../model/booking';
import { GiftBasket } from '../model/gift-basket';
import { Categorie } from '../model/categorie';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private userUrl: string;
  private loginUrl: string;
  private bookingUrl: string;
  public identity: boolean;

  constructor(private http: HttpClient, private router: Router) {
    this.userUrl = 'http://localhost:8080/user/';
    this.loginUrl = 'http://localhost:8080/login/';
    this.bookingUrl = 'http://localhost:8080/booking/';
    this.identity = false;
  }

  public findMe(): Observable<User> {
    this.identity = true;
    return this.http.get<User>(this.loginUrl + "me");
  }
  
  public updateUser(user: User, userInfos: UserInfos) {
    
    this.http.post<User>(this.userUrl + 'update', user).subscribe(() => {
      this.http.put<UserInfos>(this.userUrl + 'changeInfos', userInfos).subscribe(() => this.router.navigate(['/user/infos']));
    });
    return;
  }

  public findAll(): Observable<User[]> {
    return this.http.get<User[]>(this.userUrl);
  }

  public save(user: User) {
    return this.http.put(this.userUrl + 'create', user);
  }

  public saveBooking(giftBasket: GiftBasket) {
    return this.http.put(this.bookingUrl + 'create', giftBasket);
  }

  public getBookings() {
    return this.http.get(this.bookingUrl + 'user');
  }

  public addCategorie(categorie: Categorie) {
    this.http.put(this.userUrl + 'addRestriction', categorie).subscribe(() => {
      this.redirectTo('/user/restrictions');
    });
  }

  public deleteCategorie(id: string) {
    this.http.delete(this.userUrl + 'deleteRestriction/' + id).subscribe(() => {
      this.redirectTo('/user/restrictions');
    });
  }

  redirectTo(uri:string){
    this.router.navigateByUrl('/', {skipLocationChange: true}).then(()=>
    this.router.navigate([uri]));
 }
}