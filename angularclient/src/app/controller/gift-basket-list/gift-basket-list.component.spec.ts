import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GiftBasketListComponent } from './gift-basket-list.component';

describe('GiftBasketListComponent', () => {
  let component: GiftBasketListComponent;
  let fixture: ComponentFixture<GiftBasketListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GiftBasketListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GiftBasketListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
