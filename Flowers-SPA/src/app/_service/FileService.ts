import { Flower } from './../_model/Flower';
import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})

export class FileService{
  SERVER_URL: string = "http://localhost:8080/";

  constructor(private httpClient: HttpClient) {}

  public postPhoto(flowerData: FormData){
    // const headers = new HttpHeaders();
    // headers.append("Access-Control-Allow-Origin", "*");
    // headers.append("Access-Control-Allow-Methods", "DELETE, POST, GET, OPTIONS");
    // headers.append("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
    // let data = {
    //   flowerInfo: flowerInfo,
    //   flower: flowerData
    // }
    // console.log(flowerData.get('description'))
    return this.httpClient.post(this.SERVER_URL+'add', flowerData);
  }

  public getAllPhotos() {
    return this.httpClient.get(this.SERVER_URL +'getAllFlowers');
  }
}
