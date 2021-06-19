import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  Router,
  RouterStateSnapshot,
} from '@angular/router';

import { FileService } from '../_service/FileService';

import * as alertify from 'alertifyjs';

@Injectable({
  providedIn: 'root',
})
export class LoginGuard implements CanActivate {
  constructor(private router: Router, private fileService: FileService) {}

  token = () => {
    if (localStorage.getItem('token')) {
      return JSON.parse(localStorage.getItem('token'));
    }
    return null;
  };

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean {
    let url: string = state.url;
    this.fileService.redirectUrl = url;
    if (this.token()) {
      if (!this.fileService.tokenExpired(this.token())) {
        return true;
      } else {
        localStorage.removeItem(this.token() ? 'token' : '');
        this.router.navigateByUrl('/home');
        alertify.error('You need to login before you proceed');
        return false;
      }
    }
    this.router.navigateByUrl('/home');
    alertify.error('You need to login before you proceed');
    return false;
  }
}
