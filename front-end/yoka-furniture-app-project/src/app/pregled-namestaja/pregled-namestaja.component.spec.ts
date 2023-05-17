import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PregledNamestajaComponent } from './pregled-namestaja.component';

describe('PregledNamestajaComponent', () => {
  let component: PregledNamestajaComponent;
  let fixture: ComponentFixture<PregledNamestajaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PregledNamestajaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PregledNamestajaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
