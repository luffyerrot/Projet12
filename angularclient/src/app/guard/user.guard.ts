import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { LoginService } from '../services/login.service';
import { UserService } from '../services/user.service';

@Injectable({
  providedIn: 'root'
})
export class UserGuard implements CanActivate {

  constructor(private router: Router, private userService: UserService, private loginService: LoginService) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if (this.loginService.token != null) {
      if (this.userService.identity) {
        return true;
      }
    }
    this.router.navigateByUrl('/login');
    return false;
  }
}
