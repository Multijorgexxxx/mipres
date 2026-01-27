import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Programar } from './programar';

describe('Programar', () => {
  let component: Programar;
  let fixture: ComponentFixture<Programar>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Programar]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Programar);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
