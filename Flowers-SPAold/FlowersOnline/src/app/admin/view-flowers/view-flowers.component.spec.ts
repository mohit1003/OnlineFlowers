import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewFlowersComponent } from './view-flowers.component';

describe('ViewFlowersComponent', () => {
  let component: ViewFlowersComponent;
  let fixture: ComponentFixture<ViewFlowersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewFlowersComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewFlowersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
