import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserFormComponent } from './controller/user-form/user-form.component';
import { UserInfoComponent } from './controller/user-info/user-info.component';
import { UserGiftsRestrictionsComponent } from './controller/user-gifts-restrictions/user-gifts-restrictions.component';
import { UserAllGiftsRestrictionsComponent } from './controller/user-all-gifts-restrictions/user-all-gifts-restrictions.component';
import { BookingUserInfoComponent } from './controller/booking-user-info/booking-user-info.component';
import { UserRestrictionsComponent } from './controller/user-restrictions/user-restrictions.component';
import { LoginComponent } from './controller/login/login.component';
import { GlobalFormComponent } from './controller/global-form/global-form.component';
import { EnterpriseFormComponent } from './controller/enterprise-form/enterprise-form.component';
import { EnterpriseListComponent } from './controller/enterprise-list/enterprise-list.component';
import { EnterpriseInfoComponent } from './controller/enterprise-info/enterprise-info.component';
import { EnterprisePageComponent } from './controller/enterprise-page/enterprise-page.component';
import { BookingEnterpriseInfoComponent } from './controller/booking-enterprise-info/booking-enterprise-info.component';
import { ProductListComponent } from './controller/product-list/product-list.component';
import { ProductFormComponent } from './controller/product-form/product-form.component';
import { GiftBasketFormComponent } from './controller/gift-basket-form/gift-basket-form.component';
import { GiftBasketListComponent } from './controller/gift-basket-list/gift-basket-list.component';
import { GiftBasketAllListComponent } from './controller/gift-basket-all-list/gift-basket-all-list.component';
import { HomeComponent } from './controller/home/home.component';
import { EnterpriseGuard } from './guard/enterprise.guard';
import { UserGuard } from './guard/user.guard';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'user/infos', component: UserInfoComponent, canActivate: [UserGuard] },
  { path: 'user/add', component: UserFormComponent },
  { path: 'user/restrictions', component: UserRestrictionsComponent, canActivate: [UserGuard] },
  { path: 'user/bookings', component: BookingUserInfoComponent, canActivate: [UserGuard] },
  { path: 'user/restricted/gifts', component: UserGiftsRestrictionsComponent },
  { path: 'user/restricted/informations/:id', component: UserAllGiftsRestrictionsComponent },
  { path: 'subscribe', component: GlobalFormComponent },
  { path: 'enterprise/informations/:id', component: EnterprisePageComponent },
  { path: 'enterprise/add', component: EnterpriseFormComponent },
  { path: 'enterprise/infos', component: EnterpriseInfoComponent, canActivate: [EnterpriseGuard] },
  { path: 'enterprise/products', component: ProductListComponent, canActivate: [EnterpriseGuard] },
  { path: 'enterprise/product/add', component: ProductFormComponent, canActivate: [EnterpriseGuard] },
  { path: 'enterprise/gift/add', component: GiftBasketFormComponent, canActivate: [EnterpriseGuard] },
  { path: 'enterprise/mygifts', component: GiftBasketListComponent, canActivate: [EnterpriseGuard] },
  { path: 'enterprise/gifts', component: GiftBasketAllListComponent },
  { path: 'enterprise/bookings', component: BookingEnterpriseInfoComponent, canActivate: [EnterpriseGuard] },
  { path: 'partners', component: EnterpriseListComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
