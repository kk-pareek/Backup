import { Component, OnInit } from '@angular/core';
// import { createDecipheriv } from 'crypto';
import { AddressService } from 'src/app/address.service';
import { Address } from './address-interface';



@Component({
  selector: 'app-search-addresses',
  templateUrl: './search-addresses.component.html',
  styleUrls: ['./search-addresses.component.css']
})
export class SearchAddressesComponent implements OnInit {
 
  ngOnInit(): void {
    // throw new Error('Method not implemented.');
  }
  // this variable has all the object stored
  addressList!:Address[];

 
  constructor(private service:AddressService){
    // this.address=this.service.findAddress();
  }

  getAllAddressMethod(){
    this.service.getAllAddress()
        .subscribe(res=>{
          this.addressList=res;
          console.log(this.addressList);      
        });
  }

  keyword!:string;
  filteredAddress!:any

  //For Auto complete search
  searchOnConsole(event:any){
    this.service.searchForAutoComplete(this.keyword).subscribe
    (
      (x=>{
        this.filteredAddress=x;
        // console.log(x);  
        return "hello";
      })
    )
  }

  renderAddressCard : boolean = false;
  theAddressVariableToBeUsedForRenderingAddressCard !: Address;

  handleAddressCardRender(theAddress : Address) : boolean{
    this.renderAddressCard = true;
    this.theAddressVariableToBeUsedForRenderingAddressCard = theAddress;
    return true;
  }
}
