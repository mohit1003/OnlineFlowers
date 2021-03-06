import { Observable } from 'rxjs/Observable';
import { Flower } from './../_model/Flower';

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { User } from '../_model/User';
import * as alertify from 'alertifyjs';
import { Router } from '@angular/router';
import { Transaction } from '../_model/Transaction';

@Injectable({
  providedIn: 'root',
})
export class FileService {
  SERVER_URL: string = 'http://localhost:9191/flowers/';
  SERVER_URL_ADMIN: string = 'http://localhost:9191/admin/';
  SERVER_URL_CUSTOMER: string = 'http://localhost:9191/customer/';

  redirectUrl: string;

  constructor(private httpClient: HttpClient, private router: Router) {}

  public postPhoto(flowerData: FormData): Observable<Object> {
    if (this.isLoggedIn()) {
      let header = new HttpHeaders();
      let token = JSON.parse(localStorage.getItem('token'));

      header = header.append('Authorization', 'Bearer ' + token);
      console.log(header);
      return this.httpClient.post(this.SERVER_URL_ADMIN + 'add', flowerData, {
        headers: header,
        responseType: 'text',
      });
    }
    return null;
  }

  public postShop(shop: FormData): Observable<Object> {
    if (this.isLoggedIn()) {
      let header = new HttpHeaders();
      let token = JSON.parse(localStorage.getItem('token'));
      header = header.append('Authorization', 'Bearer ' + token);
      return this.httpClient.post(this.SERVER_URL_ADMIN + 'addShop', shop, {
        headers: header,
        responseType: 'text',
      });
    }

    return null;
  }

  public putPhotoWhthoutImage(flowerData: Flower): Observable<Object> {
    console.log(flowerData);
    if (this.isLoggedIn()) {
      let header = new HttpHeaders();
      let token = JSON.parse(localStorage.getItem('token'));
      header = header.append('Authorization', 'Bearer ' + token);
      return this.httpClient.put(
        this.SERVER_URL_ADMIN + 'updateWithoutImage',
        flowerData,
        { headers: header, responseType: 'text' }
      );
    }
    return null;
  }

  public putPhoto(flowerData: FormData): Observable<Object> {
    if (this.isLoggedIn()) {
      let header = new HttpHeaders();
      let token = JSON.parse(localStorage.getItem('token'));
      header = header.append('Authorization', 'Bearer ' + token);
      return this.httpClient.put(this.SERVER_URL_ADMIN + 'update', flowerData, {
        headers: header,
        responseType: 'text',
      });
    }
    return null;
  }

  public getAllPhotos(): Observable<Object> {
    return this.httpClient.get(this.SERVER_URL + 'getAllFlowers');
  }

  public getAllShops(): Observable<Object> {
    return this.httpClient.get(this.SERVER_URL + 'getAllShops');
  }
  // delete flower
  public delete(id: string): Observable<Object> {
    if (this.isLoggedIn()) {
      let token = JSON.parse(localStorage.getItem('token'));
      const options = {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': 'Bearer '+token,
          responseType: 'json',
        }),
        body: {
          id: id,
        },
      };
      return this.httpClient.delete(this.SERVER_URL_ADMIN + 'delete', options);
    }
    return null;
  }

  public registerUser(userToRegister: User): Observable<Object> {
    return this.httpClient.post(this.SERVER_URL + 'register', userToRegister, {
      responseType: 'text',
    });
  }

  public getToken(userToRegister: User): Observable<Object> {
    return this.httpClient.post(
      this.SERVER_URL + 'authenticate',
      userToRegister,
      { responseType: 'text' }
    );
  }

  public login(userToRegister: User): Observable<Object> {
    return this.httpClient.post(this.SERVER_URL + 'login', userToRegister, {
      responseType: 'text',
    });
  }

  public getUserByEmail(email: string): Observable<Object> {
    if (this.isLoggedIn()) {
      let header = new HttpHeaders();
      let token = JSON.parse(localStorage.getItem('token'));
      header = header.append('Authorization', 'Bearer ' + token);
      return this.httpClient.post(this.SERVER_URL_CUSTOMER + 'getUserByEmail', email, {
        headers: header,
        responseType: 'json',
      });
    }
    return null;
  }

  public makeTransaction(transaction: Transaction): Observable<Object> {
    if (this.isLoggedIn()) {
      let header = new HttpHeaders();
      let token = JSON.parse(localStorage.getItem('token'));
      header = header.append('Authorization', 'Bearer ' + token);
      return this.httpClient.post(this.SERVER_URL_CUSTOMER + 'pay', transaction, {
        headers: header,
        responseType: 'text',
      });
    }
    return null;
  }

  public generateReportMostSoldFlower(): Observable<Object> {
    if (this.isLoggedIn()) {
      let header = new HttpHeaders();
      let token = JSON.parse(localStorage.getItem('token'));
      header = header.append('Authorization', 'Bearer ' + token);
      return this.httpClient.get(this.SERVER_URL_ADMIN + 'getMostSoldProduct', {
        headers: header,
      });
    }
    return null;
  }

  public generateReportLeastSoldFlower(): Observable<Object> {
    if (this.isLoggedIn()) {
      let header = new HttpHeaders();
      let token = JSON.parse(localStorage.getItem('token'));
      header = header.append('Authorization', 'Bearer ' + token);
      return this.httpClient.get(this.SERVER_URL_ADMIN + 'getLeastSoldProduct', {
        headers: header,
      });
    }
    return null;
  }

  public generateDailyReport(): Observable<Object> {
    if (this.isLoggedIn()) {
      let header = new HttpHeaders();
      let token = JSON.parse(localStorage.getItem('token'));
      header = header.append('Authorization', 'Bearer ' + token);
      return this.httpClient.get(this.SERVER_URL_ADMIN + 'getTodaysSalesReport', {
        headers: header,
      });
    }
    return null;
  }

  public generateLastWeeksReport(): Observable<Object> {
    if (this.isLoggedIn()) {
      let header = new HttpHeaders();
      let token = JSON.parse(localStorage.getItem('token'));
      header = header.append('Authorization', 'Bearer ' + token);
      return this.httpClient.get(this.SERVER_URL_ADMIN + 'getLastWeekSalesReport', {
        headers: header,
      });
    }
    return null;
  }

  public generateMonthlyReport(): Observable<Object> {
    if (this.isLoggedIn()) {
      let header = new HttpHeaders();
      let token = JSON.parse(localStorage.getItem('token'));
      header = header.append('Authorization', 'Bearer ' + token);
      return this.httpClient.get(this.SERVER_URL_ADMIN + 'getMonthlySalesReport', {
        headers: header,
      });
    }
    return null;
  }

  public generateCategoryWiseReport(): Observable<Object> {
    if (this.isLoggedIn()) {
      let header = new HttpHeaders();
      let token = JSON.parse(localStorage.getItem('token'));
      header = header.append('Authorization', 'Bearer ' + token);
      return this.httpClient.get(
        this.SERVER_URL_ADMIN + 'getFlowerCategoryWiseReport',
        {
          headers: header,
        }
      );
    }
    return null;
  }

  public getAllCustomers(): Observable<Object> {
    if (this.isLoggedIn()) {
      let header = new HttpHeaders();
      let token = JSON.parse(localStorage.getItem('token'));
      header = header.append('Authorization', 'Bearer ' + token);
      return this.httpClient.get(this.SERVER_URL_ADMIN + 'getAllCustomers', {
        headers: header,
      });
    }
    return null;
  }

  public getCustomersCityWise(): Observable<Object> {
    if (this.isLoggedIn()) {
      let header = new HttpHeaders();
      let token = JSON.parse(localStorage.getItem('token'));
      header = header.append('Authorization', 'Bearer ' + token);
      return this.httpClient.get(this.SERVER_URL_ADMIN + 'getCustomersCityWise', {
        headers: header,
      });
    }
    return null;
  }

  //getLeastSoldProduct

  public isLoggedIn(): boolean {
    let token = localStorage.getItem('token');
    if (token === undefined || token === null) {
      return false;
    } else {
      if (this.tokenExpired(token)) {
        this.logout();
        return false;
      }
      return true;
    }
  }

  public logout() {
    localStorage.getItem('token')
      ? localStorage.removeItem('token')
      : localStorage.removeItem('');
    this.router.navigateByUrl('/home');
    alertify.success('Logout successful');
  }

  public tokenExpired(token: string): boolean {
    const expiry = JSON.parse(atob(token.split('.')[1])).exp;
    return Math.floor(new Date().getTime() / 1000) >= expiry;
  }

  public getEmailFromToken(): string {
    if (this.isLoggedIn()) {
      const token = JSON.parse(localStorage.getItem('token'));
      return JSON.parse(atob(token.split('.')[1]).split(':')[1].split(',')[0]);
    }
    this.logout();
    alertify.errror('Invalid user Session!!');
    return null;
  }

  public getScopesFromToken(): string {
    if (this.isLoggedIn()) {
      const token = JSON.parse(localStorage.getItem('token'));
      return JSON.parse(atob(token.split('.')[1]).split(',')[1].split(':')[1]);
    }
    this.logout();
    alertify.errror('Invalid user Session!!');
    return null;
  }
}

