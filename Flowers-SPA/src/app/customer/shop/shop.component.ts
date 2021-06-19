import { CartModel } from './../../_model/CartModel';
import { Observable } from 'rxjs/Observable';
import { Flower } from './../../_model/Flower';
import { Component, OnInit } from '@angular/core';
import { FileService } from 'src/app/_service/FileService';
import { DataService } from 'src/app/_service/CartService';
import { Store } from '@ngrx/store';
import * as FlowerActions from '../../_actions/Flower.action';
import * as alertify from 'alertifyjs';
import { Router } from '@angular/router';

interface AppState {
  flower: Flower[];
}

@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.css'],
})
export class ShopComponent implements OnInit {
  showLoader: boolean = false;

  flowers: Flower[];
  flowersInCategory: Flower[];
  flowersInCart: CartModel;
  filteredFlowersForDuplicateCheck: Flower[] = [];

  cartModelForDuplicates: CartModel[] = [];
  cartModelItem: CartModel;

  cartCount: number = 0;

  flower: Observable<Flower[]>;

  isSideNavOpen:boolean = false;
  classForSideNav:string = 'mySidenavClosed';

  constructor(
    private fileService: FileService,
    private dataService: DataService,
    private store: Store<AppState>,
    private router: Router
  ) {
    this.flower = store.select('flower');
    // localStorage.setItem('cartState', JSON.stringify(this.flower));
  }

  ngOnInit(): void {
    if(localStorage.getItem('cartItem')){
      let cart = Object.assign({}, JSON.parse(localStorage.getItem('cartItem')));
      this.cartCount = cart.count;

    }
    this.getAllFlowers();
  }


  openNav() {
    document.getElementById("shopFilterNav").style.width = "250px";
  }

  closeNav() {
    document.getElementById("shopFilterNav").style.width = "0";
  }

  getBirthdayFlowers() {
    this.flowersInCategory = this.flowers;
    this.flowersInCategory = this.flowersInCategory.filter(
      (flower) => flower.category === 'birthday'
    );
    this.closeNav();
  }

  getGrandOpeningFlowers() {
    this.flowersInCategory = this.flowers;
    this.flowersInCategory = this.flowersInCategory.filter(
      (flower) => flower.category === 'grandOpening'
    );
    this.closeNav();
  }

  getSympathyFlowers() {
    this.flowersInCategory = this.flowers;
    this.flowersInCategory = this.flowersInCategory.filter(
      (flower) => flower.category === 'sympathy'
    );
    this.closeNav();
  }

  getLoveFlowers() {
    this.flowersInCategory = this.flowers;
    this.flowersInCategory = this.flowersInCategory.filter(
      (flower) => flower.category === 'love'
    );
    this.closeNav();
  }

  getMarriageFlowers() {
    this.flowersInCategory = this.flowers;
    this.flowersInCategory = this.flowersInCategory.filter(
      (flower) => flower.category === 'marriage'
    );
    this.closeNav();
  }

  getAllFlowers() {
    this.fileService.getAllPhotos().subscribe(
      (data) => {
        this.flowers = Object.assign(data);
        this.flowersInCategory = this.flowers;
        // console.log(this.flowers);
        this.closeNav();
      },
      (err) => {
        console.log(err);
      }
    );
  }

  addToCart(flower: Flower) {
    this.showLoader = true;

    if (!this.fileService.isLoggedIn()) {
      this.router.navigateByUrl('cust/cart');
    }
    if(localStorage.getItem('cartItem')){
      this.flowersInCart = Object.assign({}, JSON.parse(localStorage.getItem('cartItem')))
    }
    // Flower does not present in the cart and need to add as a new one
    if(this.flowersInCart === undefined) {
      this.flowersInCart = new CartModel();
      flower.count = 1;
      this.flowersInCart.flowers.push(flower);
    }
    //Flower already there in cart and needed to be updated
    else if(this.flowersInCart.flowers !== undefined && this.flowersInCart.flowers.some(flowerInCart => flowerInCart.id === flower.id)){
      let flowerToEdit:Flower = this.flowersInCart.flowers.find(flowerInCart => flowerInCart.id === flower.id);
      flowerToEdit.count += 1;
      flowerToEdit.price = (parseInt(flowerToEdit.price) + parseInt(flower.price)).toString();
      let index:number = this.flowersInCart.flowers.findIndex(flowerToFind => flowerToFind.id === flower.id);
      this.flowersInCart.flowers[index] = Object.assign({}, flowerToEdit);
      this.flowersInCart = Object.assign({}, this.flowersInCart);
    }
    // New flower to be added and cart is not empty
    else {
      flower.count = 1;
      this.flowersInCart.flowers.push(flower);
    }

    this.flowersInCart.total = parseInt(flower.price) + this.flowersInCart.total;
    this.flowersInCart.count += 1;
    let flowerToAdd  = JSON.stringify(this.flowersInCart);
    this.cartCount = this.flowersInCart.count;
    localStorage.setItem('cartItem', flowerToAdd);

    this.showLoader = false;
    }

}
