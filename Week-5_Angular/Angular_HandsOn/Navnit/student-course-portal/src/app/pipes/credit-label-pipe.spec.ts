import { CreditLabelPipe } from './credit-label-pipe';

describe('CreditLabelPipe', () => {
  const pipe = new CreditLabelPipe();

  it('should create', () => {
    expect(pipe).toBeTruthy();
  });

  it('should format credits', () => {
    expect(pipe.transform(1)).toBe('1 Credit');
    expect(pipe.transform(3)).toBe('3 Credits');
    expect(pipe.transform(null)).toBe('No Credits');
  });
});
