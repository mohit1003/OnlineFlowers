import { Observable } from 'rxjs/Observable';
import { Flower } from './../_model/Flower';

import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { User } from '../_model/User';
import * as alertify from 'alertifyjs';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})

export class FileService{
  SERVER_URL: string = "http://localhost:9191/";
  redirectUrl: string;

  constructor(private httpClient: HttpClient, private router: Router) {}

  public postPhoto(flowerData: FormData):Observable<Object>{
    let header = new HttpHeaders();
    let token = JSON.parse(localStorage.getItem('token'));

    header = header.append('Authorization', 'Bearer '+token);
    console.log(header);
    return this.httpClient.post(this.SERVER_URL+'add', flowerData, {headers:header});
  }

  public postShop(shop: FormData):Observable<Object>{
    if(this.isLoggedIn()){
    let header = new HttpHeaders();
    let token = JSON.parse(localStorage.getItem('token'));
    header = header.append('Authorization', 'Bearer '+token);
    return this.httpClient.post(this.SERVER_URL+'addShop', shop, {headers:header});
    }

    return null;
  }



  public putPhotoWhthoutImage(flowerData: Flower):Observable<Object> {
    return this.httpClient.put(this.SERVER_URL+'updateWithoutImage', flowerData);
  }

  public putPhoto(flowerData: FormData):Observable<Object>{
    return this.httpClient.put(this.SERVER_URL+'update', flowerData);
  }

  public getAllPhotos():Observable<Object> {
    return this.httpClient.get(this.SERVER_URL +'getAllFlowers');
  }

  public getAllShops():Observable<Object> {
    return this.httpClient.get(this.SERVER_URL+'getAllShops');
  }

  public delete(id: string):Observable<Object> {
    console.log(id);
    const options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      body: {
        id: id
      },
    };
    return this.httpClient.delete(this.SERVER_URL+'delete', options);
  }

  public registerUser(userToRegister: User):Observable<Object>{
    return this.httpClient.post(this.SERVER_URL+'register', userToRegister);
  }

  public getToken(userToRegister: User):Observable<Object> {
    return this.httpClient.post(this.SERVER_URL+'authenticate', userToRegister, {responseType: 'text'})
  }

  public login(userToRegister: User):Observable<Object> {
    return this.httpClient.post(this.SERVER_URL+'login', userToRegister)
  }

  public isLoggedIn(): boolean {
    let token = null;
    token = localStorage.getItem('token');
    if (token === undefined || token === null){
      return false;
    }
    else{
      if(this.tokenExpired(token)){
        this.logout();
        return false;
      }
      return true;
    }
  }

  public logout(){
    localStorage.removeItem('token');
    this.router.navigateByUrl('/home');
    alertify.success('Logout successful');
  }

  private tokenExpired(token: string): boolean {
    const expiry = (JSON.parse(atob(token.split('.')[1]))).exp;
    return (Math.floor((new Date).getTime() / 1000)) >= expiry;
  }
}
