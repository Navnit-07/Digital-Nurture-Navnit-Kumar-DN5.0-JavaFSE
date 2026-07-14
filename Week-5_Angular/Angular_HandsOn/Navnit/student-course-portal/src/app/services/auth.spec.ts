import { TestBed } from '@angular/core/testing';
import { AuthService } from './auth';

describe('AuthService', () => {
  it('should be created', () => {
    TestBed.configureTestingModule({});
    expect(TestBed.inject(AuthService)).toBeTruthy();
  });
});
