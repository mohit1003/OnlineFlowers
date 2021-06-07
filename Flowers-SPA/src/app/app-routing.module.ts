import { LoginGuard } from './_guards/loginGuard.service';
import { HomeComponent } from './home/home.component';
import { AddFlowerComponent } from './admin/add-flower/add-flower.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ViewComponent } from './admin/view/view.component';
import { ShopComponent } from './customer/shop/shop.component';
import { ContactComponent } from './customer/contact/contact.component';
import { LocationsComponent } from './customer/locations/locations.component';
import { FeedbackComponent } from './customer/feedback/feedback.component';
import { CartComponent } from './customer/cart/cart.component';
import { ShopsComponent } from './admin/shops/shops.component';
import { ReportsComponent } from './admin/reports/reports.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'contact', component: ContactComponent },
  { path: 'locations', component: LocationsComponent },
  { path: 'feedback', component: FeedbackComponent },

  {
    path: 'admin',
    runGuardsAndResolvers: 'always',
    canActivate: [LoginGuard],
    children: [
      { path: 'add', component: AddFlowerComponent },
      { path: 'view', component: ViewComponent },
      { path: 'shops', component: ShopsComponent },
      { path: 'reports', component: ReportsComponent },
    ],
  },
  {
    path: 'cust',
    runGuardsAndResolvers: 'always',
    // canActivate: [AuthGuard],
    children: [
      { path: 'cart', component: CartComponent, canActivate: [LoginGuard] },
      { path: 'shop', component: ShopComponent },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
