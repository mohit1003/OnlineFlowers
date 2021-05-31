import { CartModel } from './../../_model/CartModel';
import { Observable } from 'rxjs/Observable';
import { Flower } from './../../_model/Flower';
import { Component, OnInit } from '@angular/core';
import { FileService } from 'src/app/_service/FileService';
import { DataService } from 'src/app/_service/CartService';
import { Store } from '@ngrx/store';
import * as FlowerActions from '../../_actions/Flower.action';

interface AppState {
  flower: Flower[];
}


@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.css']
})
export class ShopComponent implements OnInit {

  flowers: Flower[];
  flowersInCategory: Flower[];
  flowersInCart: Flower[] = [];
  filteredFlowersForDuplicateCheck: Flower[] = [];

  cartModelForDuplicates: CartModel[] = [];
  cartModelItem: CartModel;

  flower: Observable<Flower[]>;


  constructor(private fileService: FileService, private dataService:DataService,
              private store:Store<AppState>) {
    this.flower = store.select('flower');
    // localStorage.setItem('cartState', JSON.stringify(this.flower));
  }

  ngOnInit(): void {
    this.getAllFlowers();
  }

  getBirthdayFlowers() {
    this.flowersInCategory = this.flowers;
    this.flowersInCategory = this.flowersInCategory.filter(flower => flower.category === 'birthday')
  }

  getGrandOpeningFlowers() {
    this.flowersInCategory = this.flowers;
    this.flowersInCategory = this.flowersInCategory.filter(flower => flower.category === 'grandOpening')
  }

  getSympathyFlowers() {
    this.flowersInCategory = this.flowers;
    this.flowersInCategory = this.flowersInCategory.filter(flower => flower.category === 'sympathy')
  }

  getLoveFlowers() {
    this.flowersInCategory = this.flowers;
    this.flowersInCategory = this.flowersInCategory.filter(flower => flower.category === 'love')
  }

  getMarriageFlowers() {
    this.flowersInCategory = this.flowers;
    this.flowersInCategory = this.flowersInCategory.filter(flower => flower.category === 'marriage')
  }

  getAllFlowers(){
    this.fileService.getAllPhotos().subscribe(data => {
      this.flowers = Object.assign(data);
      this.flowersInCategory = this.flowers;
      // console.log(this.flowers);
    }, err =>{
      console.log(err);
    })
  }

  addToCart(flower: Flower){
    this.dataService.currentState.subscribe(data => {
      this.flowersInCart = Object.assign(data);
    })
    this.flowersInCart = Object.assign([], this.flowersInCart);
    this.filteredFlowersForDuplicateCheck = this.flowersInCart.filter(cartFlower => cartFlower.id == flower.id);
    if(this.filteredFlowersForDuplicateCheck.length > 0) {
      this.filteredFlowersForDuplicateCheck.forEach(filteredFlower => {

        if(localStorage.getItem(filteredFlower.id) === null){
          this.cartModelItem = null;
          this.cartModelItem = new CartModel();
          this.cartModelItem.id = filteredFlower.id;
          this.cartModelItem.count = 2;
          this.cartModelItem.origPrice = +flower.price;
          this.cartModelForDuplicates.push(this.cartModelItem);
          localStorage.setItem(flower.id, JSON.stringify(this.cartModelForDuplicates));
        }
        else if(localStorage.getItem(filteredFlower.id) !== null) {
          this.cartModelItem = null;
          this.cartModelItem = new CartModel();
          this.cartModelItem = JSON.parse(localStorage.getItem(filteredFlower.id));
          this.cartModelItem[0].id = filteredFlower.id;
          this.cartModelItem[0].count += 1;
          this.cartModelItem[0].origPrice = +flower.price;
          this.cartModelForDuplicates.push(this.cartModelItem[0]);
          localStorage.setItem(filteredFlower.id, JSON.stringify(this.cartModelForDuplicates));
        }
        else{
          console.log('Error in cart data');
        }



      })
    }
    else{
      this.flowersInCart.push(flower);
    }



    // localStorage.setItem('cartItem', JSON.stringify(this.flowersInCart));
    this.dataService.changeState(this.flowersInCart);
    //this.store.dispatch(new FlowerActions.AddtoCart(this.flowersInCart));
  }



}
