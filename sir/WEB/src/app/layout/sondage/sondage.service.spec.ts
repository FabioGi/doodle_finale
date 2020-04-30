/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { SondageService } from './sondage.service';

describe('Service: Sondage', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SondageService]
    });
  });

  it('should ...', inject([SondageService], (service: SondageService) => {
    expect(service).toBeTruthy();
  }));
});
