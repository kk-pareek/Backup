import { Component, OnInit } from '@angular/core';
import { AddressService } from 'src/app/address.service';
import { Address } from '../search-addresses/address-interface';
// import { Address } from '../add-single-address/address';
import { UpdateSingleAddressService } from './update-single-address.service';




@Component({
  selector: 'app-update-single-address',
  templateUrl: './update-single-address.component.html',
  styleUrls: ['./update-single-address.component.css']
})
export class UpdateSingleAddressComponent implements OnInit {
  
  address !: Address;
  
  constructor(private theAddressService: AddressService, private updateService: UpdateSingleAddressService) { 
    this.address = updateService.theAddress;
    console.log(this.address);
  }

  ngOnInit(): void {
    
  }

  updateExistingAddress() {
    this.theAddressService.updateExistingAddress(this.address.id, this.address)
    .subscribe(responseData => {
      alert("Address Updated !");
      window.location.href='addresses/view-all';
    });
  }

  

}
