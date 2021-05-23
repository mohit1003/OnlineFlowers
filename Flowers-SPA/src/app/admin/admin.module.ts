import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { AppRoutingModule } from './../app-routing.module';

import { FileService } from '../_service/FileService';

import { AddFlowerComponent } from './add-flower/add-flower.component';
import { ViewComponent } from './view/view.component';
import { AdminNavComponent } from './admin-nav/admin-nav.component';






@NgModule({
  declarations: [
    AddFlowerComponent,
    ViewComponent,
    AdminNavComponent
  ],
  imports: [
    AppRoutingModule,
    RouterModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [FileService]
})
export class AdminModule { }
