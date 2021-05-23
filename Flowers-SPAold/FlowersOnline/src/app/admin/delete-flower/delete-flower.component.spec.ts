import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteFlowerComponent } from './delete-flower.component';

describe('DeleteFlowerComponent', () => {
  let component: DeleteFlowerComponent;
  let fixture: ComponentFixture<DeleteFlowerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeleteFlowerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteFlowerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
