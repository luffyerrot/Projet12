import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import { AuthInterceptor } from './interceptor/auth-interceptor';
import { ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserService } from './services/user.service';
import { UserListComponent } from './component/user-list/user-list.component';
import { UserFormComponent } from './component/user-form/user-form.component';
import { LoginComponent } from './component/login/login.component';
import { UserInfoComponent } from './component/user-info/user-info.component';
import { EnterpriseInfoComponent } from './component/enterprise-info/enterprise-info.component';
import { EnterpriseFormComponent } from './component/enterprise-form/enterprise-form.component';
import { EnterpriseListComponent } from './component/enterprise-list/enterprise-list.component';
import { GlobalFormComponent } from './component/global-form/global-form.component';
import { HomeComponent } from './component/home/home.component';
import { ProductListComponent } from './component/product-list/product-list.component';
import { ProductFormComponent } from './component/product-form/product-form.component';
import { GiftBasketListComponent } from './component/gift-basket-list/gift-basket-list.component';
import { GiftBasketFormComponent } from './component/gift-basket-form/gift-basket-form.component';
import { GiftBasketAllListComponent } from './component/gift-basket-all-list/gift-basket-all-list.component';
import { EnterprisePageComponent } from './component/enterprise-page/enterprise-page.component';
import { UserRestrictionsComponent } from './component/user-restrictions/user-restrictions.component';
import { BookingUserInfoComponent } from './component/booking-user-info/booking-user-info.component';
import { BookingEnterpriseInfoComponent } from './component/booking-enterprise-info/booking-enterprise-info.component';
import { UserGiftsRestrictionsComponent } from './component/user-gifts-restrictions/user-gifts-restrictions.component';
import { UserAllGiftsRestrictionsComponent } from './component/user-all-gifts-restrictions/user-all-gifts-restrictions.component';

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