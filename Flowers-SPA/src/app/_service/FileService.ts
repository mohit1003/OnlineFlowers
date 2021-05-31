import { Flower } from './../_model/Flower';

import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { User } from '../_model/User';
@Injectable({
  providedIn: 'root'
})

export class FileService{
  SERVER_URL: string = "http://localhost:9191/";

  constructor(private httpClient: HttpClient) {}

  public postPhoto(flowerData: FormData){
    return this.httpClient.post(this.SERVER_URL+'add', flowerData);
  }

  public putPhotoWhthoutImage(flowerData: Flower) {
    return this.httpClient.put(this.SERVER_URL+'updateWithoutImage', flowerData);
  }

  public putPhoto(flowerData: FormData){
    return this.httpClient.put(this.SERVER_URL+'update', flowerData);
  }

  public getAllPhotos() {
    return this.httpClient.get(this.SERVER_URL +'getAllFlowers');
  }

  public delete(id: string) {
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

  public registerUser(userToRegister: User){
    return this.httpClient.post(this.SERVER_URL+'register', userToRegister);
  }

  public getToken(userToRegister: User) {
    return this.httpClient.post(this.SERVER_URL+'authenticate', userToRegister, {responseType: 'text'})
  }

  public login(userToRegister: User) {
    return this.httpClient.post(this.SERVER_URL+'login', userToRegister)
  }
}
