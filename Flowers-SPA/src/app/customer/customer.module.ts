import { CategoryFilter } from './../_pipes/categoryFilter.pipe';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AccountComponent } from './account/account.component';
import { ShopComponent } from './shop/shop.component';
import { CartComponent } from './cart/cart.component';
import { ContactComponent } from './contact/contact.component';
import { LocationsComponent } from './locations/locations.component';
import { FeedbackComponent } from './feedback/feedback.component';
import { NavComponent } from './nav/nav.component';
import { FooterComponent } from './footer/footer.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { StoreModule } from '@ngrx/store';
import { flowerReducer } from '../_reducers/Flower.reducer';



@NgModule({
  declarations: [
    AccountComponent,
    ShopComponent,
    CartComponent,
    ContactComponent,
    LocationsComponent,
    FeedbackComponent,
    NavComponent,
    FooterComponent,
    CategoryFilter
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    StoreModule.forRoot({
      flower: flowerReducer
    })
  ]
})
export class CustomerModule { }
