import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EnterpriseInfoComponent } from './enterprise-info.component';

describe('EnterpriseInfoComponent', () => {
  let component: EnterpriseInfoComponent;
  let fixture: ComponentFixture<EnterpriseInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EnterpriseInfoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EnterpriseInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
