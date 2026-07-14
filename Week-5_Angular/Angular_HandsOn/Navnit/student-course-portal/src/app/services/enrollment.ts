import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map, switchMap } from 'rxjs/operators';
import { Course } from '../models/course.model';
import { CourseService } from './course';

@Injectable({
  providedIn: 'root',
})
export class EnrollmentService {
  private courseService = inject(CourseService);
  private http = inject(HttpClient);
  private enrolledCourseIds: number[] = [3];
  private apiUrl = 'http://localhost:3000/enrollments';

  enroll(courseId: number): void {
    if (!this.enrolledCourseIds.includes(courseId)) {
      this.enrolledCourseIds.push(courseId);
    }
  }

  unenroll(courseId: number): void {
    this.enrolledCourseIds = this.enrolledCourseIds.filter((id) => id !== courseId);
  }

  isEnrolled(courseId: number): boolean {
    return this.enrolledCourseIds.includes(courseId);
  }

  getEnrolledIds(): number[] {
    return [...this.enrolledCourseIds];
  }

  getEnrolledCourses(): Course[] {
    return this.enrolledCourseIds
      .map((id) => this.courseService.getCourseByIdLocal(id))
      .filter((c): c is Course => !!c);
  }

  getStudentsByCourse(courseId: number): Observable<{ id: number; name: string }[]> {
    // switchMap cancels the previous inner Observable when a new courseId arrives,
    // so an older slow response cannot overwrite a newer selection.
    return of(courseId).pipe(
      switchMap((id) =>
        this.http.get<{ id: number; studentId: number; courseId: number }[]>(this.apiUrl).pipe(
          map((rows) =>
            rows
              .filter((r) => r.courseId === id)
              .map((r) => ({ id: r.studentId, name: `Student ${r.studentId}` }))
          )
        )
      )
    );
  }
}
