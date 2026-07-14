import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, map, retry, tap } from 'rxjs/operators';
import { Course } from '../models/course.model';

@Injectable({
  providedIn: 'root',
})
export class CourseService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:3000/courses';

  // local fallback used before HTTP / for simple demos
  private localCourses: Course[] = [
    { id: 1, name: 'Data Structures', code: 'CS101', credits: 4, gradeStatus: 'passed', enrolled: false },
    { id: 2, name: 'Database Systems', code: 'CS201', credits: 3, gradeStatus: 'pending', enrolled: false },
    { id: 3, name: 'Web Technologies', code: 'CS301', credits: 3, gradeStatus: 'passed', enrolled: true },
    { id: 4, name: 'Operating Systems', code: 'CS202', credits: 4, gradeStatus: 'failed', enrolled: false },
    { id: 5, name: 'Angular Framework', code: 'CS401', credits: 2, gradeStatus: 'pending', enrolled: false },
  ];

  getLocalCourses(): Course[] {
    return this.localCourses;
  }

  getCourseByIdLocal(id: number): Course | undefined {
    return this.localCourses.find((c) => c.id === id);
  }

  addCourseLocal(course: Course): void {
    this.localCourses.push(course);
  }

  getCourses(): Observable<Course[]> {
    // tap is for side effects (logging) without changing the stream; map is for transforming data
    return this.http.get<Course[]>(this.apiUrl).pipe(
      retry(2),
      map((courses) => courses.filter((c) => c.credits > 0)),
      tap((courses) => console.log('Courses loaded:', courses.length)),
      catchError((err) => {
        console.error(err);
        return throwError(() => new Error('Failed to load courses. Please try again.'));
      })
    );
  }

  getCourseById(id: number): Observable<Course> {
    return this.http.get<Course>(`${this.apiUrl}/${id}`).pipe(
      catchError((err) => {
        console.error(err);
        return throwError(() => new Error('Failed to load course.'));
      })
    );
  }

  createCourse(course: Omit<Course, 'id'>): Observable<Course> {
    return this.http.post<Course>(this.apiUrl, course);
  }

  updateCourse(id: number, course: Partial<Course>): Observable<Course> {
    return this.http.put<Course>(`${this.apiUrl}/${id}`, course);
  }

  deleteCourse(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
