import { Injectable } from '@angular/core';
import { Address } from '../search-addresses/address-interface';



@Injectable({
  providedIn: 'root'
})

export class UpdateSingleAddressService {

  theAddress !: Address;

  constructor() { }

  assignAddressValue(addressValue : Address) : void {
    this.theAddress = addressValue;
    console.log(addressValue);
    console.log(this.theAddress);
  }
}
