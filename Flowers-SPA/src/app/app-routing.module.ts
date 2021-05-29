import { AddFlowerComponent } from './admin/add-flower/add-flower.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ViewComponent } from './admin/view/view.component';

const routes: Routes = [
  { path: 'add', component: AddFlowerComponent },
  { path: 'view', component: ViewComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
