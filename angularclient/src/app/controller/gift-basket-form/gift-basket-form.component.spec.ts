import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GiftBasketFormComponent } from './gift-basket-form.component';

describe('GiftBasketFormComponent', () => {
  let component: GiftBasketFormComponent;
  let fixture: ComponentFixture<GiftBasketFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GiftBasketFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GiftBasketFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
