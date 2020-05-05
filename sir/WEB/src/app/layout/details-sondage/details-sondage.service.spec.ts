/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { DetailsSondageService } from '../../service/details-sondage.service';

describe('Service: DetailsSondage', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DetailsSondageService]
    });
  });

  it('should ...', inject([DetailsSondageService], (service: DetailsSondageService) => {
    expect(service).toBeTruthy();
  }));
});
