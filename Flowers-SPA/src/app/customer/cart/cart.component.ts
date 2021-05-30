import { CartModel } from './../../_model/CartModel';
import { Observable } from 'rxjs/Observable';
import { Flower } from './../../_model/Flower';
import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import * as FlowerActions from '../../_actions/Flower.action';
import { DataService } from 'src/app/_service/CartService';

interface AppState {
  flower: Flower[];
}

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  flower: Observable<Flower[]>;
  flowersAddedIncart: Flower[];
  cartModelForDuplicates: CartModel[] = [];
  filteredFlowersToAddCount:Flower[] = [];
  flowerToAdd: Flower;

  total: number = 0;

  constructor(private store:Store<AppState>, private dataService: DataService) {
    // this.flower = store.select('flower');
   }

  ngOnInit(): void {
    if(localStorage.getItem('cartItem') !== null) {
     this.flowersAddedIncart = JSON.parse(localStorage.getItem('cartItem'));
      this.flowersAddedIncart.forEach(flower => {
        if(localStorage.getItem(flower.id) !== null) {
          this.cartModelForDuplicates = JSON.parse(localStorage.getItem(flower.id));
          this.cartModelForDuplicates.forEach(incrementFactorFlower => {
            if(incrementFactorFlower.id === flower.id) {
              this.flowerToAdd = Object.assign(flower);

              var price:number = incrementFactorFlower.origPrice;
              console.log(incrementFactorFlower.count+ ' '+price)
              this.flowerToAdd.price = (incrementFactorFlower.count * price).toString();
              this.filteredFlowersToAddCount.push(this.flowerToAdd);
            }
            else{
            this.filteredFlowersToAddCount.push(flower);
            }
        })
      }
      })
    }
    this.calculateTotal();

  }

  calculateTotal() {
    this.flowersAddedIncart.forEach(flowers => {
      this.total += (+flowers.price)
    })
  }


  proceedBuy() {}

  deleteFromCart(flower: Flower){
    localStorage.removeItem(flower.id);
    this.flowersAddedIncart = this.flowersAddedIncart.filter(flowerToRemove => flowerToRemove.id !== flower.id);
    this.dataService.changeState(this.flowersAddedIncart);
    this.flowersAddedIncart.forEach(flowers => {
      this.total -= (+flowers.price)
    })
  }

}
