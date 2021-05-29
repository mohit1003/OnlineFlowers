
import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})

export class FileService{
  SERVER_URL: string = "http://localhost:8080/";

  constructor(private httpClient: HttpClient) {}

  public postPhoto(flowerData: FormData){
    return this.httpClient.post(this.SERVER_URL+'add', flowerData);
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
}
