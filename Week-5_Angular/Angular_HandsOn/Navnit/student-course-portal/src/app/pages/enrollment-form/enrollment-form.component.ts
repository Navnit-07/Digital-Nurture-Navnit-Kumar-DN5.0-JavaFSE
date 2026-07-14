import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { CourseService } from '../../services/course';

@Component({
  selector: 'app-enrollment-form',
  imports: [CommonModule, FormsModule],
  templateUrl: './enrollment-form.component.html',
  styleUrl: './enrollment-form.component.css',
})
export class EnrollmentFormComponent {
  private courseService = inject(CourseService);

  studentName = '';
  studentEmail = '';
  courseId: number | null = null;
  preferredSemester = 'Odd';
  agreeToTerms = false;
  submitted = false;

  onSubmit(form: NgForm): void {
    console.log(form.value, form.valid);
    if (form.valid) {
      this.submitted = true;
      if (this.courseId != null) {
        this.courseService
          .createCourse({
            name: this.studentName + ' Request',
            code: 'REQ' + this.courseId,
            credits: 3,
            gradeStatus: 'pending',
            enrolled: false,
          })
          .subscribe({
            next: (course) => console.log('Created course via POST', course),
            error: (err) => console.error(err),
          });
      }
    }
  }
}
