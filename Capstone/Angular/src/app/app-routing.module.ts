import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddAddressOptionsComponent } from './addresses/add-address-options/add-address-options.component';
import { AddMultipleAddressesComponent } from './addresses/add-multiple-addresses/add-multiple-addresses.component';
import { AddNewAddressComponent } from './addresses/add-single-address/add-new-address.component';
import { AddressCardComponent } from './addresses/address-card/address-card.component';
import { AddressesComponent } from './addresses/addresses.component';
import { UpdateSingleAddressComponent } from './addresses/update-single-address/update-single-address.component';
import { ViewAllAddressesComponent } from './addresses/view-all-addresses/view-all-addresses.component';
import { HomeComponent } from './home/home.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';

const routes: Routes = [
  { path: "", component: HomeComponent },
  {
    path: "addresses", component: AddressesComponent,
    children: [ { path: "address-card", component: AddressCardComponent}]
  },
  { path: "addresses/view-all", component: ViewAllAddressesComponent},
  { path: "addresses/view-all/update-single-address", component: UpdateSingleAddressComponent},
  { path: "addresses/add-address-options/add-single-address", component: AddNewAddressComponent },
  { path: "addresses/add-address-options/add-multiple-addresses", component: AddMultipleAddressesComponent},
  { path: "addresses/add-address-options", component: AddAddressOptionsComponent},
  { path: "addresses/update-single-address", component: UpdateSingleAddressComponent},
  { path: "**", component: PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
