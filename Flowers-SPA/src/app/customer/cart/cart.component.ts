import { Transaction } from './../../_model/Transaction';
import { User } from './../../_model/User';
import { CartModel } from './../../_model/CartModel';
import { Observable } from 'rxjs/Observable';
import { Flower } from './../../_model/Flower';
import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import * as FlowerActions from '../../_actions/Flower.action';
import { DataService } from 'src/app/_service/CartService';
import { faWindowClose } from '@fortawesome/free-solid-svg-icons';
import { FileService } from 'src/app/_service/FileService';
import { HttpResponse } from '@angular/common/http';
import * as alertify from 'alertifyjs';

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

  sales: Transaction;

  checkoutButtonClicked: boolean

  customer: User;

  total: number = 0;

  constructor(private store:Store<AppState>, private dataService: DataService,
    private fileService: FileService) {
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
              flower.count = incrementFactorFlower.count;
              this.flowerToAdd = Object.assign(flower);

              var price:number = incrementFactorFlower.origPrice;
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
    this.proceedBuy();
    this.checkoutButtonClicked = false;
  }

  calculateTotal() {
    this.flowersAddedIncart.forEach(flowers => {
      this.total += (+flowers.price)
    })
  }


  proceedBuy() {
    this.checkoutButtonClicked = true;
    let userEmail = this.fileService.getEmailFromToken();
    this.fileService.getUserByEmail(JSON.parse(userEmail)).subscribe((customer:HttpResponse<any>) => {
      this.customer = Object.assign(customer.body)
    })
  }



  pay(){
    if(this.fileService.isLoggedIn()) {

      this.buildTransaction();
      this.fileService.makeTransaction(this.sales).subscribe(data =>{
        alertify.success('Payment Successful')
        this.flowersAddedIncart.forEach(flower => {
          this.deleteFromCart(flower);
        })
      }, err=> {
        alertify.error('Error making transaction');
      })


    }
  }

  buildTransaction(){
    this.sales = new Transaction();
    this.sales.customerModel = this.customer;
    this.sales.flowersModel = this.flowersAddedIncart;

    console.log(this.sales)
  }



  resetCheckout(){
    this.checkoutButtonClicked = false;
  }

  deleteFromCart(flower: Flower){
    localStorage.removeItem(flower.id);
    this.flowersAddedIncart = this.flowersAddedIncart.filter(flowerToRemove => flowerToRemove.id !== flower.id);
    this.total -= (+flower.price);
    this.dataService.changeState(this.flowersAddedIncart);

  }

}
