import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TransListComponent } from './trans-list.component';

describe('TransListComponent', () => {
  let component: TransListComponent;
  let fixture: ComponentFixture<TransListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TransListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TransListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
