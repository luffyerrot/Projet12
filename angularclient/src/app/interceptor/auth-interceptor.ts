import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { LoginService } from '../services/login.service';
import { Router } from '@angular/router';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private loginService: LoginService, private router: Router) {}
  
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let token = this.loginService.token;
    if(token)
    {
        req = req.clone({
          setHeaders: {
              Authorization: 'Bearer ' + token
          }
        });
    }

    return next.handle(req).pipe(tap(
      (event: HttpEvent<any>) => {
        if (event instanceof HttpResponse) {
        }
      }, 
      (err: any) => {
        if (err instanceof HttpErrorResponse) {
          if (err.status === 401 || err.status === 403 || err.status === 400) {
              this.loginService.logout();
          }
          if (err.status === 404) {
            this.router.navigateByUrl('/');
          }
        }
      }
    ));
  }
}