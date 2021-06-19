import { RouterStateSnapshot } from '@angular/router';
import { FileService } from 'src/app/_service/FileService';
import { CanActivate, Router } from '@angular/router';
import { Observable } from 'rxjs';
import * as alertify from 'alertifyjs';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AdminGuard implements CanActivate {
  constructor(
    private router: Router,
    private fileService: FileService
  ) {}

  token = () => {
    if (localStorage.getItem('token')) {
      return JSON.parse(localStorage.getItem('token'));
    }
    return null;
  };

  canActivate(): boolean {
    if (this.token()) {
      let scopes = this.fileService.getScopesFromToken();
      if (scopes === 'ROLE_ADMIN') {
        return true;
      } else {
        this.router.navigateByUrl('cust/shop');
        alertify.error('You need to be ADMIN before you proceed');
        return false;
      }
    }
    this.router.navigateByUrl('/home');
    alertify.error('You need to be ADMIN before you proceed');
    return false;
  }
}
