import { Component, Input, OnInit } from '@angular/core';
import { AddressService } from 'src/app/address.service';
import { Address } from '../search-addresses/address-interface';
import { UpdateSingleAddressComponent } from '../update-single-address/update-single-address.component';
import { UpdateSingleAddressService } from '../update-single-address/update-single-address.service';


@Component({
  selector: 'app-address-card',
  templateUrl: './address-card.component.html',
  styleUrls: ['./address-card.component.css']
})
export class AddressCardComponent implements OnInit {
  @Input() theAddress !: Address;
  

  // theAddressCopy : Address = this.theAddress;
  
  constructor(private theAddressService: AddressService, private updateService: UpdateSingleAddressService) { 
    
  }
 
 

  ngOnInit(): void {
  }

  deleteAddress() : void {
    if(confirm("Are you sure you want to delete the address?")) {
      this.theAddressService.deleteAddressById(this.theAddress.id)
      .subscribe(responseData => {
        alert("Address Deleted !");
      });
    }
  }


  updateAddress(): void {
    this.updateService.assignAddressValue(this.theAddress);
    console.log(this.theAddress);
  }
}
