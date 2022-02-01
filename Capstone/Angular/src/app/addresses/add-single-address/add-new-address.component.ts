import { Component, Input, OnInit } from '@angular/core';
import { Address } from './address';
import { AddressService } from '../../address.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-new-address',
  templateUrl: './add-new-address.component.html',
  styleUrls: ['./add-new-address.component.css']
})
export class AddNewAddressComponent implements OnInit {

  address : Address = new Address();
  str : string = "";
  
  constructor(private theAddressService : AddressService, private theRouter: Router) { 
    setInterval(() => this.checkForPincode(), 1000);
  }

  ngOnInit(): void {}

  addNewAddress() : void {
    this.address.buildingName =this.titleCase(this.address.buildingName);
    this.address.city =this.titleCase(this.address.city);
    this.address.country =this.titleCase(this.address.country);
    this.address.district=this.titleCase(this.address.district);
    this.address.landMark=this.titleCase(this.address.landMark);
    this.address.state=this.titleCase(this.address.state);
    this.theAddressService.addAddress(this.address)
    .subscribe(responseData => {
      alert("Address Added !");
      window.location.href='addresses/view-all';
    });
  }
  titleCase(str:string) {
    var splitStr = str.toLowerCase().split(' ');
    for (var i = 0; i < splitStr.length; i++) {
        // You do not need to check if i is larger than splitStr length, as your for does that for you// Assign it back to the array
        splitStr[i] = splitStr[i].charAt(0).toUpperCase() + splitStr[i].substring(1);     
    }
    return splitStr.toString();
    // Directly return the joined stringreturn splitStr.join(' '); 
 }
 
 validateData !: any;
 checkForPincode() {
   console.log("called");
   this.str += this.address.pinCode;
  if(this.str.length > 0) {
    this.theAddressService.validatePin(this.address.pinCode)
    .subscribe((validateData) => {
      this.validateData = validateData;
      console.log(this.validateData);
      this.isValidPinCode();
    });
  }
}

isValidPinCode(){
  if(this.validateData!=null){
    this.address.district=this.validateData[0].PostOffice[0].District;
    this.address.state=this.validateData[0].PostOffice[0].State;
    this.address.country=this.validateData[0].PostOffice[0].Country;
  }
}

isPinValid():boolean{
  if(this.validateData[0].Status=='Error'){
    return true;
  }
  else{
    return false;
  }
}
}
