import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserGiftsRestrictionsComponent } from './user-gifts-restrictions.component';

describe('UserGiftsRestrictionsComponent', () => {
  let component: UserGiftsRestrictionsComponent;
  let fixture: ComponentFixture<UserGiftsRestrictionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserGiftsRestrictionsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserGiftsRestrictionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
