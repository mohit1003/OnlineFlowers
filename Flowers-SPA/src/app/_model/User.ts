import { Roles } from './Roles';
export interface User{
  userEmail: string;
  firstName: string;
  lastName: string;
  address: string;
  city: string;
  country: string;
  title: string;
  contact: number;
  count: number;
  password: string;
  roles: Roles[];
}
