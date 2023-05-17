import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NamestajComponent } from './namestaj.component';

describe('NamestajComponent', () => {
  let component: NamestajComponent;
  let fixture: ComponentFixture<NamestajComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NamestajComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NamestajComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
