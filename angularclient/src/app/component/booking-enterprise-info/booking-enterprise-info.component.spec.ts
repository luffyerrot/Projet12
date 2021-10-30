import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookingEnterpriseInfoComponent } from './booking-enterprise-info.component';

describe('BookingEnterpriseInfoComponent', () => {
  let component: BookingEnterpriseInfoComponent;
  let fixture: ComponentFixture<BookingEnterpriseInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BookingEnterpriseInfoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BookingEnterpriseInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
