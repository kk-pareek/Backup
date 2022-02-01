import { TestBed } from '@angular/core/testing';

import { UpdateSingleAddressService } from './update-single-address.service';

describe('UpdateSingleAddressService', () => {
  let service: UpdateSingleAddressService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UpdateSingleAddressService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
