import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import { AuthInterceptor } from './interceptor/auth-interceptor';
import { ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserService } from './services/user.service';
import { UserListComponent } from './controller/user-list/user-list.component';
import { UserFormComponent } from './controller/user-form/user-form.component';
import { LoginComponent } from './controller/login/login.component';
import { UserInfoComponent } from './controller/user-info/user-info.component';
import { EnterpriseInfoComponent } from './controller/enterprise-info/enterprise-info.component';
import { EnterpriseFormComponent } from './controller/enterprise-form/enterprise-form.component';
import { EnterpriseListComponent } from './controller/enterprise-list/enterprise-list.component';
import { GlobalFormComponent } from './controller/global-form/global-form.component';
import { HomeComponent } from './controller/home/home.component';
import { ProductListComponent } from './controller/product-list/product-list.component';
import { ProductFormComponent } from './controller/product-form/product-form.component';
import { GiftBasketListComponent } from './controller/gift-basket-list/gift-basket-list.component';
import { GiftBasketFormComponent } from './controller/gift-basket-form/gift-basket-form.component';
import { GiftBasketAllListComponent } from './controller/gift-basket-all-list/gift-basket-all-list.component';
import { EnterprisePageComponent } from './controller/enterprise-page/enterprise-page.component';
import { UserRestrictionsComponent } from './controller/user-restrictions/user-restrictions.component';
import { BookingUserInfoComponent } from './controller/booking-user-info/booking-user-info.component';
import { BookingEnterpriseInfoComponent } from './controller/booking-enterprise-info/booking-enterprise-info.component';
import { UserGiftsRestrictionsComponent } from './controller/user-gifts-restrictions/user-gifts-restrictions.component';
import { UserAllGiftsRestrictionsComponent } from './controller/user-all-gifts-restrictions/user-all-gifts-restrictions.component';

@NgModule({
  declarations: [
    AppComponent,
    UserListComponent,
    UserFormComponent,
    LoginComponent,
    UserInfoComponent,
    EnterpriseInfoComponent,
    EnterpriseFormComponent,
    EnterpriseListComponent,
    GlobalFormComponent,
    HomeComponent,
    ProductListComponent,
    ProductFormComponent,
    GiftBasketListComponent,
    GiftBasketFormComponent,
    GiftBasketAllListComponent,
    EnterprisePageComponent,
    UserRestrictionsComponent,
    BookingUserInfoComponent,
    BookingEnterpriseInfoComponent,
    UserGiftsRestrictionsComponent,
    UserAllGiftsRestrictionsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [UserService, { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }