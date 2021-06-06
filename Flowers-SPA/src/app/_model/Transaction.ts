import { Flower } from './Flower';
import { User } from "./User";

export class Transaction {
  customerModel: User;
  flowersModel: Flower[];
}
