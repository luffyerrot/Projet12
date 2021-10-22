import { Component } from '@angular/core';
import { EnterpriseService } from './services/enterprise.service';
import { LoginService } from './services/login.service';
import { UserService } from './services/user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'angularclient';

  constructor(private userService: UserService, private enterpriseService: EnterpriseService, private loginService: LoginService) {}

  authenticated() { 
    if (this.loginService.token != null) {
      return true;
    } else {
      return false;
    }
  }

  identityUser() {
    return this.userService.identity ? true : false;
  }

  identityEnterprise() {
    return this.enterpriseService.identity ? true : false;
  }

  logout() {
    if (this.loginService.token != null) {
      this.loginService.logout();
    }
  }
}
