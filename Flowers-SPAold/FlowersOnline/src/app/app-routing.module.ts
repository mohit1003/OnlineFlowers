import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddFlowerComponent } from './admin/add-flower/add-flower.component';

const routes: Routes = [
  {path: 'add', component:AddFlowerComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
