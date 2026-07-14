import { TestBed } from '@angular/core/testing';
import { LoadingService } from './loading';

describe('LoadingService', () => {
  it('should be created', () => {
    TestBed.configureTestingModule({});
    expect(TestBed.inject(LoadingService)).toBeTruthy();
  });
});
