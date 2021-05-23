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

  public postPhoto(flower: Flower){
    // const headers = new HttpHeaders();
    // headers.append("Access-Control-Allow-Origin", "*");
    // headers.append("Access-Control-Allow-Methods", "DELETE, POST, GET, OPTIONS");
    // headers.append("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
    return this.httpClient.post(this.SERVER_URL+'add', flower);
  }
}
