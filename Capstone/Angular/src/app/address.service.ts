import { HttpClient, HttpErrorResponse, HttpEvent, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Address } from './addresses/add-single-address/address';

@Injectable({
  providedIn: 'root'
})
export class AddressService {
  
  theAddress : Address[]=[];

  constructor(private theHttpClient : HttpClient) { }

  public addAddress(theAddress: Address) {
    console.log("printing on console" + theAddress.country);
    return this.theHttpClient.post(`${environment.baseUrl}` + "/addresses", theAddress);
  }

  addMultipleAddresses(file: File): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();
    formData.append('file', file);

    const req = new HttpRequest('POST', `${environment.baseUrl}` + "/uploadAddressesFile", formData, {
      reportProgress: true,
      responseType: 'json'
    });

    return this.theHttpClient.request(req);
  }

  getAllAddress():Observable<Address[]>{
    return this.theHttpClient.get<Address[]>(`${environment.baseUrl}` + "/addresses");
  }

  searchForAutoComplete(keyword:string){
    return this.theHttpClient.get<Address>(`${environment.baseUrl}` + '/search/'+keyword)
            .pipe(map((res=>res)));
  }

  deleteAddressById(id:number) {
    return this.theHttpClient.delete(`${environment.baseUrl}` + '/addresses/' + id);
  }

  updateExistingAddress(id: number, theAddress: Address) {
    return this.theHttpClient.put(`${environment.baseUrl}`+ '/addresses/' + id, theAddress);
  }
  validatePin(keyword: Number) {
    return this.theHttpClient.get(`${environment.remoteUrlForPincode}`+ keyword)
      .pipe(catchError(this.handleError));
  }

  private handleError(theError: HttpErrorResponse) {
    if (theError.error instanceof ErrorEvent) {
      console.error("Client side error occured:", theError.message);
    }
    else {
      console.error(`Backend issue ${theError.status}`, `+body was: ${theError.error}`);

    }
    return throwError(`Something bad happenend, Please try again later.`)
  }
}
