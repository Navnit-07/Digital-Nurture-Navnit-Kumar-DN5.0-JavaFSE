import { TestBed } from '@angular/core/testing';
import { NotificationService } from './notification';

describe('NotificationService', () => {
  it('should be created', () => {
    TestBed.configureTestingModule({ providers: [NotificationService] });
    expect(TestBed.inject(NotificationService)).toBeTruthy();
  });
});
