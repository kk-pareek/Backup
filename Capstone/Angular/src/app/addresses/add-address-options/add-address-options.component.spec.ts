import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddAddressOptionsComponent } from './add-address-options.component';

describe('AddAddressOptionsComponent', () => {
  let component: AddAddressOptionsComponent;
  let fixture: ComponentFixture<AddAddressOptionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddAddressOptionsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddAddressOptionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
