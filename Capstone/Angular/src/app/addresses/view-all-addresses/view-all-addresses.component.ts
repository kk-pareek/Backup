import { Component, OnInit } from '@angular/core';
import { AddressService } from 'src/app/address.service';
import { Address } from '../search-addresses/address-interface';
import { UpdateSingleAddressComponent } from '../update-single-address/update-single-address.component';
import { UpdateSingleAddressService } from '../update-single-address/update-single-address.service';

@Component({
  selector: 'app-view-all-addresses',
  templateUrl: './view-all-addresses.component.html',
  styleUrls: ['./view-all-addresses.component.css']
})
export class ViewAllAddressesComponent implements OnInit {

  allAddresses !: Address[];

  constructor(private theAddressService : AddressService, private updateService: UpdateSingleAddressService) {
    this.theAddressService.getAllAddress()
    .subscribe(res=>{
      this.allAddresses=res.reverse();  
    });
   }

  ngOnInit(): void {
  }

  updateAddress(theAddress: Address): void {
    this.updateService.assignAddressValue(theAddress);
    console.log(theAddress);
  }

  deleteAddress(theAddress: Address) : void {
    if(confirm("Are you sure you want to delete the address?")) {
      this.theAddressService.deleteAddressById(theAddress.id)
      .subscribe(responseData => {
        alert("Address Deleted !");
      });
    }
    window.location.reload();
  }
}
