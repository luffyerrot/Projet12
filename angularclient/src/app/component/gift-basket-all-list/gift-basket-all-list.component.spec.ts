import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GiftBasketAllListComponent } from './gift-basket-all-list.component';

describe('GiftBasketAllListComponent', () => {
  let component: GiftBasketAllListComponent;
  let fixture: ComponentFixture<GiftBasketAllListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GiftBasketAllListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GiftBasketAllListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
