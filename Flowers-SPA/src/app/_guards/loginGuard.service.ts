import { Injectable } from '@angular/core';
import {  ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from "@angular/router";
import { Observable } from "rxjs";
import { FileService } from "../_service/FileService";

import * as alertify from 'alertifyjs';

@Injectable({
  providedIn: 'root'
})
export class LoginGuard implements CanActivate{
  constructor(private router: Router, private fileService: FileService ) {
  }
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot):boolean {
    let url: string = state.url;
    this.fileService.redirectUrl = url;
    if(this.fileService.isLoggedIn()){
      return true;

    }
    else{
      console.log(url);
      this.router.navigateByUrl('/home');
      alertify.error('You need to login before you proceed');
      return false;
    }
  }
}
