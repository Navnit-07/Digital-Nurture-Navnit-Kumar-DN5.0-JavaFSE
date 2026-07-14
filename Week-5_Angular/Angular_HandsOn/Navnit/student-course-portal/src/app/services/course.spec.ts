import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { CourseService } from './course';
import { Course } from '../models/course.model';

describe('CourseService', () => {
  let service: CourseService;
  let httpMock: HttpTestingController;

  const mockCourses: Course[] = [
    { id: 1, name: 'Data Structures', code: 'CS101', credits: 4, gradeStatus: 'passed' },
    { id: 2, name: 'DBMS', code: 'CS201', credits: 3, gradeStatus: 'pending' },
  ];

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [CourseService],
    });
    service = TestBed.inject(CourseService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('getCourses should call the courses API and return data', () => {
    service.getCourses().subscribe((courses) => {
      expect(courses.length).toBe(2);
    });

    const req = httpMock.expectOne('http://localhost:3000/courses');
    expect(req.request.method).toBe('GET');
    req.flush(mockCourses);
  });

  it('getCourses should emit error message on failure after retries', () => {
    service.getCourses().subscribe({
      next: () => fail('expected error'),
      error: (err) => {
        expect(err.message).toContain('Failed to load courses');
      },
    });

    // retry(2) => 1 original + 2 retries = 3 requests
    httpMock.expectOne('http://localhost:3000/courses').flush('err', {
      status: 500,
      statusText: 'Server Error',
    });
    httpMock.expectOne('http://localhost:3000/courses').flush('err', {
      status: 500,
      statusText: 'Server Error',
    });
    httpMock.expectOne('http://localhost:3000/courses').flush('err', {
      status: 500,
      statusText: 'Server Error',
    });
  });
});
