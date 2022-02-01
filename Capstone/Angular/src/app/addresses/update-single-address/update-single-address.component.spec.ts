import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateSingleAddressComponent } from './update-single-address.component';

describe('UpdateSingleAddressComponent', () => {
  let component: UpdateSingleAddressComponent;
  let fixture: ComponentFixture<UpdateSingleAddressComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateSingleAddressComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateSingleAddressComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
