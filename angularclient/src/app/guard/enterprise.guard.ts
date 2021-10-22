import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { EnterpriseService } from '../services/enterprise.service';
import { LoginService } from '../services/login.service';
import { UserService } from '../services/user.service';

@Injectable({
  providedIn: 'root'
})
export class EnterpriseGuard implements CanActivate {

  constructor(private router: Router, private enterpriseService: EnterpriseService, private loginService: LoginService) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
    if (this.loginService.token != null) {
      if (this.enterpriseService.identity) {
        return true;
      }
    }
    this.router.navigateByUrl('/login');
    return false;
  }
}
