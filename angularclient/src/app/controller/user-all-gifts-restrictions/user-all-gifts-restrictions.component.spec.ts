import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserAllGiftsRestrictionsComponent } from './user-all-gifts-restrictions.component';

describe('UserAllGiftsRestrictionsComponent', () => {
  let component: UserAllGiftsRestrictionsComponent;
  let fixture: ComponentFixture<UserAllGiftsRestrictionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserAllGiftsRestrictionsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserAllGiftsRestrictionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
