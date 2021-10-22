import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Enterprise } from '../model/enterprise';
import { User } from '../model/user';
import { EnterpriseService } from './enterprise.service';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  loginUrl: string;
  public token: any;

  constructor(private http: HttpClient, private router: Router, private userService: UserService, private enterpriseService: EnterpriseService) {
    this.loginUrl = 'http://localhost:8080/login/';
  }

  findEnterprise(): Observable<Enterprise> {
    return this.http.get<Enterprise>(this.loginUrl + "me");
  }

  findUser(): Observable<User> {
    return this.http.get<User>(this.loginUrl + "me");
  }
  
  public logout() {
    this.token = null;
    this.enterpriseService.identity = false;
    this.userService.identity = false;
    this.router.navigateByUrl('/login');
  } 
}
