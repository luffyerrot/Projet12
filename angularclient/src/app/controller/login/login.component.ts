import { Component } from '@angular/core';
import { UserService } from '../../services/user.service';
import {HttpClient, HttpHeaders } from '@angular/common/http';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { EnterpriseService } from 'src/app/services/enterprise.service';
import { Observable } from 'rxjs';
import { Enterprise } from 'src/app/model/enterprise';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
 
  profil = {email: '', password: ''}
  loginUrl: string;
  form!: FormGroup;
  submitted = false;

  constructor(private formBuilder: FormBuilder, private http: HttpClient, private router: Router, private userService: UserService, 
    private enterpriseService: EnterpriseService, private loginService: LoginService) {
    
    this.loginUrl = 'http://localhost:8080/login/';
  }

  ngOnInit(): void {
    this.form = this.formBuilder.group(
      {
        email: [
          '',
          [
            Validators.required,
            Validators.email
          ]
        ],
        password: [
          '',
          [
            Validators.required
          ]
        ]
      }
    );
  }

  onSubmit() {
    this.submitted = true;
    if (this.form.valid) {
      this.profil = this.form.value;
      const headers = new HttpHeaders(this.profil ? {
        email : this.profil.email,
        password : this.profil.password
      } : {});
  
      this.http.get("http://localhost:8080/login/", {headers: headers}).subscribe((response :any) => {
        if (response['token']) {
          this.loginService.token = response['token'];
          this.http.get(this.loginUrl + "me").subscribe((response : any) => {
            if(response['username']) {
              this.userService.identity = true;
            } else if(response['name']) {
              this.enterpriseService.identity = true;
            }
          })
          this.router.navigateByUrl('/');
        } else {
          this.loginService.token = null;
        }
      });
    }
  }
}