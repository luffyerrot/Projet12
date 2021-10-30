import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookingUserInfoComponent } from './booking-user-info.component';

describe('BookingUserInfoComponent', () => {
  let component: BookingUserInfoComponent;
  let fixture: ComponentFixture<BookingUserInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BookingUserInfoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BookingUserInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
