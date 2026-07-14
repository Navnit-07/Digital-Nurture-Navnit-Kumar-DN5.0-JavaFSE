import { CommonModule } from '@angular/common';
import { Component, OnInit, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Course } from '../../models/course.model';
import { CourseService } from '../../services/course';
import { EnrollmentService } from '../../services/enrollment';

@Component({
  selector: 'app-course-detail',
  imports: [CommonModule],
  templateUrl: './course-detail.component.html',
  styleUrl: './course-detail.component.css',
})
export class CourseDetailComponent implements OnInit {
  private route = inject(ActivatedRoute);
  private courseService = inject(CourseService);
  private enrollmentService = inject(EnrollmentService);

  course: Course | undefined;
  students: { id: number; name: string }[] = [];
  errorMessage = '';

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.courseService.getCourseById(id).subscribe({
      next: (course) => {
        this.course = course;
        this.enrollmentService.getStudentsByCourse(course.id).subscribe({
          next: (students) => (this.students = students),
          error: () => (this.students = []),
        });
      },
      error: () => {
        this.course = this.courseService.getCourseByIdLocal(id);
        if (!this.course) {
          this.errorMessage = 'Course not found';
        }
      },
    });
  }
}
