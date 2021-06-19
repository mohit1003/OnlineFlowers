import { Transaction } from './../../_model/Transaction';
import { User } from './../../_model/User';
import { CartModel } from './../../_model/CartModel';
import { Observable } from 'rxjs/Observable';
import { Flower } from './../../_model/Flower';
import { Component, OnInit } from '@angular/core';
import { FileService } from 'src/app/_service/FileService';
import { HttpResponse } from '@angular/common/http';
import * as alertify from 'alertifyjs';
import { faPlus, faMinus } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeModule, FaIconLibrary  } from '@fortawesome/angular-fontawesome';

interface AppState {
  flower: Flower[];
}

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css'],
})
export class CartComponent implements OnInit {
  flower: Observable<Flower[]>;
  cart: CartModel;
  flowersInCart: Flower[] = [];
  cartModelForDuplicates: CartModel[] = [];
  filteredFlowersToAddCount: Flower[] = [];
  flowerToAdd: Flower;

  faPlus = faPlus;
  faMinus = faMinus;

  cartTotal: number = 0;

  sales: Transaction;

  checkoutButtonClicked: boolean;

  customer: User;

  cartCount: number = 0;

  constructor(private fileService: FileService, library: FaIconLibrary) {
    library.addIcons(faPlus);
    library.addIcons(faMinus)
  }

  ngOnInit(): void {

    this.resetCartTotal();
    if (localStorage.getItem('cartItem')) {
      this.cart = JSON.parse(localStorage.getItem('cartItem'));
      if(this.cart.count < 0) {
        this.resetCartTotal();
        localStorage.removeItem('cartItem')
      }
      this.cartTotal = this.cart.total;
      this.flowersInCart = Object.assign(this.cart.flowers);
      this.cartCount = this.cart.count;
      this.proceedBuy();
    }

    this.checkoutButtonClicked = false;
  }

  proceedBuy() {
    this.checkoutButtonClicked = true;
    let userEmail = this.fileService.getEmailFromToken();
    this.fileService
      .getUserByEmail(userEmail)
      .subscribe((customer: HttpResponse<any>) => {
        this.customer = Object.assign(customer.body);
      });
  }

  pay() {
    if (this.fileService.isLoggedIn()) {
      this.buildTransaction();
      this.fileService.makeTransaction(this.sales).subscribe(
        (data) => {
          alertify.success('Payment Successful');
          localStorage.removeItem('cartTotal');
          this.flowersInCart.forEach((flower) => {
            this.deleteFromCart(flower);
          });
        },
        (err) => {
          if (err.status === 201) {
            alertify.success('Payment Successful');
            this.flowersInCart.forEach((flower) => {
              this.deleteFromCart(flower);
            });
          } else {
            alertify.error('Error making transaction');
          }
        }
      );
    }
    this.resetCartTotal();
  }

  resetCartTotal() {
    if(this.cartTotal < 0){
      this.cartTotal = 0;
    }
  }

  buildTransaction() {
    this.sales = new Transaction();
    this.sales.customerModel = this.customer;
    this.sales.flowersModel = this.flowersInCart;
  }

  resetCheckout() {
    this.checkoutButtonClicked = false;
  }

  deleteFromCart(flower: Flower) {
    this.flowersInCart = this.flowersInCart.filter(
      (flowerToRemove) => flowerToRemove.id !== flower.id
    );

    this.cart.total -= +flower.price;
    this.cart.count -= flower.count;
    this.cartCount -= flower.count;
    this.cartTotal = this.cart.total;
    this.cart.flowers = this.flowersInCart;
    this.resetCartTotal();
    localStorage.setItem('cartItem', JSON.stringify(this.cart));
  }

}
