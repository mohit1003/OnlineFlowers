import { HttpClient } from '@angular/common/http';
import { AppRoutingModule } from './../app-routing.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddFlowerComponent } from './add-flower/add-flower.component';
import { AdminNavComponent } from './nav/nav.component';
import { HomeComponent } from '../home/home.component';
import { RegisterComponent } from '../register/register.component';
import { DeleteFlowerComponent } from './delete-flower/delete-flower.component';
import { ViewFlowersComponent } from './view-flowers/view-flowers.component';
import { UpdateComponent } from './update/update.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';



@NgModule({
  declarations: [
    AdminNavComponent,
    AddFlowerComponent,
    HomeComponent,
    RegisterComponent,
    DeleteFlowerComponent,
    ViewFlowersComponent,
    UpdateComponent
  ],
  imports: [
    AppRoutingModule,
    RouterModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClient
  ],
  exports: [AdminNavComponent]
})
export class AdminModule { }
