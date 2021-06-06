import { Component, OnInit } from '@angular/core';
import { Shop } from 'src/app/_model/Shop';
import { FileService } from 'src/app/_service/FileService';
import * as alertify from 'alertifyjs';

@Component({
  selector: 'app-locations',
  templateUrl: './locations.component.html',
  styleUrls: ['./locations.component.css']
})
export class LocationsComponent implements OnInit {

  shops: Shop[] = [];

  constructor(private fileService: FileService) { }


  ngOnInit(): void {
    this.getAllShops();
  }

  getAllShops() {
    this.fileService.getAllShops().subscribe(shops => {
      this.shops = Object.assign([] , shops);
    }, err=>{
      alertify.error('Error Fetching shops');
    })
  }

}
