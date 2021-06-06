import { Shop } from './../../_model/Shop';
import { Component, OnInit, ViewChild } from '@angular/core';

import * as alertify from 'alertifyjs';
import { FileService } from 'src/app/_service/FileService';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-shops',
  templateUrl: './shops.component.html',
  styleUrls: ['./shops.component.css'],
})
export class ShopsComponent implements OnInit {
  @ViewChild('addShopForm', { static: true }) addShopForm: NgForm;
  shop: Shop;
  shopName: string;
  shopCity: string;
  shopCountry: string;
  isOpen: string;
  shopAddress: string;
  shopContact: string;
  fileAttached: boolean = false;

  shopImage: File;

  shops: Shop[] = [];

  contactInvalid: boolean = false;

  constructor(private fileService: FileService) {}

  ngOnInit(): void {
    this.getAllShops();
  }

  contactChanged(event) {
    if (event.target.value.length === 10) {
      this.contactInvalid = true;
    } else {
      this.contactInvalid = false;
    }
  }

  onFileChanged(event: any) {
    if (event.target.files && event.target.files[0]) {
      this.shopImage = event.target.files[0];
      this.fileAttached = true;
    } else {
      this.fileAttached = false;
      this.addShopForm.reset();
    }
  }

  onShopManageClicked() {
    if (this.fileAttached) {
      const shop = new FormData();
      shop.append('shopImage', this.shopImage, this.shopImage.name);
      shop.append('shopName', this.shopName);
      shop.append('shopCity', this.shopCity);
      shop.append('shopCountry', this.shopCountry);
      shop.append('shopAddress', this.shopAddress);
      shop.append('isOpen', this.isOpen);
      shop.append('shopContact', this.shopContact);

      // console.log(this.price);

      this.fileService.postShop(shop).subscribe(
        (data) => {
          alertify.success('Shop added successfully');
        },
        (err) => {
          alertify.error('Error adding shop');
        }
      );
    } else {
      alertify.error('All fields are required');
    }
  }

  getAllShops() {
    this.fileService.getAllShops().subscribe(
      (shops) => {
        this.shops = Object.assign([], shops);
      },
      (err) => {
        alertify.error('Error Fetching shops');
      }
    );
  }
}
