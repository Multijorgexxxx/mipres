import { TestBed } from '@angular/core/testing';

import { Programar } from './programar.service';

describe('Programar', () => {
  let service: Programar;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Programar);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
