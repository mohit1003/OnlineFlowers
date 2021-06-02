import { Shop } from './../../_model/Shop';
import { Component, OnInit } from '@angular/core';

import * as alertify from 'alertifyjs';
import { FileService } from 'src/app/_service/FileService';

@Component({
  selector: 'app-shops',
  templateUrl: './shops.component.html',
  styleUrls: ['./shops.component.css']
})
export class ShopsComponent implements OnInit {

  shop: Shop
  shopName: string;
  shopCity: string;
  shopCountry: string;
  isOpen: string;
  shopAddress: string
  shopContact:string;

  shopImage: File;

  shops: Shop[] = [];

  contactInvalid: boolean = false;

  constructor(private fileService: FileService) { }



  ngOnInit(): void {
    this.getAllShops();
  }

  contactChanged(event) {
    if(event.target.value.length === 10){
      this.contactInvalid = true;
    }
    else{
      this.contactInvalid = false;
    }
  }

  onFileChanged(event:any) {
    this.shopImage = event.target.files[0];
  }

  onShopManageClicked() {
    const shop = new FormData();
    shop.append('shopImage', this.shopImage, this.shopImage.name);
    shop.append('shopName', this.shopName);
    shop.append('shopCity', this.shopCity);
    shop.append('shopCountry', this.shopCountry);
    shop.append('shopAddress', this.shopAddress);
    shop.append('isOpen', this.isOpen);
    shop.append('shopContact', this.shopContact);

    // console.log(this.price);

    this.fileService.postShop(shop).subscribe((data) =>{
      alertify.success('Shop added successfully');
    }, err=> {
      alertify.error('Error adding shop');
    })
  }

  getAllShops() {
    this.fileService.getAllShops().subscribe(shops => {
      this.shops = Object.assign([] , shops);
    }, err=>{
      alertify.error('Error Fetching shops');
    })
  }

}

