import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';

import { ModalModule } from 'ngx-bootstrap/modal';

import { AppRoutingModule } from './app-routing.module';
import { AdminModule } from './admin/admin.module';


import { AppComponent } from './app.component';
import { CustomerModule } from './customer/customer.module';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { CategoryFilter } from './_pipes/categoryFilter.pipe';
import { HomeComponent } from './home/home.component';
import { NavComponent } from './customer/nav/nav.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent
  ],
  imports: [
    CustomerModule,
    AdminModule,
    RouterModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    FontAwesomeModule,
    ModalModule.forRoot(),

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
