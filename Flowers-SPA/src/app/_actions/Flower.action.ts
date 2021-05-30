import { Flower } from '../_model/Flower';
import { Action } from '@ngrx/store';

export const ADDTOCART = 'AddToCart';
export const RESETCART = 'ResetCart';
export const REMOVEFROMCART = 'RemoveFromCart';
export class AddtoCart implements Action{
  readonly type = ADDTOCART;
  constructor(public payload: Flower[]) {}
}

export class RemoveFromCart implements Action{
  readonly type = REMOVEFROMCART;
  constructor(public payload: Flower[]) {}
}

export class ResetCart implements Action{
  readonly type = RESETCART;
}

export type All
= AddtoCart
| ResetCart
| RemoveFromCart;
