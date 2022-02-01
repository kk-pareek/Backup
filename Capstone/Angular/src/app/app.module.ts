import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavComponent } from './nav/nav.component';
import { HomeComponent } from './home/home.component';
import { AddressesComponent } from './addresses/addresses.component';
import { AddNewAddressComponent } from './addresses/add-single-address/add-new-address.component';
import { FormsModule } from '@angular/forms';
import { AddressService } from './address.service';
import { AddAddressOptionsComponent } from './addresses/add-address-options/add-address-options.component';
import { AddMultipleAddressesComponent } from './addresses/add-multiple-addresses/add-multiple-addresses.component';
import { SearchAddressesComponent } from './addresses/search-addresses/search-addresses.component';
import { AddressCardComponent } from './addresses/address-card/address-card.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';

import {AutoCompleteModule} from 'primeng/autocomplete';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { UpdateSingleAddressComponent } from './addresses/update-single-address/update-single-address.component';
import { ViewAllAddressesComponent } from './addresses/view-all-addresses/view-all-addresses.component';
import {ScrollingModule} from '@angular/cdk/scrolling';









@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    HomeComponent,
    AddressesComponent,
    AddNewAddressComponent,
    AddAddressOptionsComponent,
    AddMultipleAddressesComponent,
    SearchAddressesComponent,
    AddressCardComponent,
    PageNotFoundComponent,
    UpdateSingleAddressComponent,
    ViewAllAddressesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    AutoCompleteModule,
    BrowserAnimationsModule,
    ScrollingModule
  ],
  providers: [
    AddressService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
