import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddMultipleAddressesComponent } from './add-multiple-addresses.component';

describe('AddMultipleAddressesComponent', () => {
  let component: AddMultipleAddressesComponent;
  let fixture: ComponentFixture<AddMultipleAddressesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddMultipleAddressesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddMultipleAddressesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
