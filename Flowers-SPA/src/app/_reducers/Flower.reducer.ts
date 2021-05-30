import { Flower } from '../_model/Flower';
import * as FlowerActions from '../_actions/Flower.action'

export type Action = FlowerActions.All;

//Default state
const defaultState: Flower[] = [];

//New State helper
const newState = (state, newData) => {
  // newData = JSON.parse(newData)
  return Object.assign({}, state, newData);
}

//Reducer function
export function flowerReducer(state: Flower[] = defaultState, action: Action) {

  switch(action.type) {
    case FlowerActions.ADDTOCART: {
      return newState(state, {flower: action.payload})
    }
    case FlowerActions.RESETCART: {
      return defaultState;
    }
    case FlowerActions.REMOVEFROMCART: {
      return newState(state, {flower: action.payload})
    }
    default: {
      return defaultState;
    }
  }
}
