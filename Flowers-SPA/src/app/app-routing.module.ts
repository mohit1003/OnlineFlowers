import { AddFlowerComponent } from './admin/add-flower/add-flower.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ViewComponent } from './admin/view/view.component';
import { ShopComponent } from './customer/shop/shop.component';
import { ContactComponent } from './customer/contact/contact.component';
import { LocationsComponent } from './customer/locations/locations.component';
import { FeedbackComponent } from './customer/feedback/feedback.component';
import { CartComponent } from './customer/cart/cart.component';

const routes: Routes = [
  { path: 'add', component: AddFlowerComponent },
  { path: 'view', component: ViewComponent },
  { path: 'contact', component: ContactComponent },
  { path: 'locations', component: LocationsComponent },
  { path: 'feedback', component: FeedbackComponent },

  {
    path: 'cust',
    runGuardsAndResolvers: 'always',
    // canActivate: [AuthGuard],
    children: [
        { path: 'cart', component: CartComponent },
        { path: 'shop', component: ShopComponent },


    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
