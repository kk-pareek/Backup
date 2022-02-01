import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchAddressesComponent } from './search-addresses.component';

describe('SearchAddressesComponent', () => {
  let component: SearchAddressesComponent;
  let fixture: ComponentFixture<SearchAddressesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchAddressesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchAddressesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
