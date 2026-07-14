import { Component, OnDestroy, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CourseService } from '../../services/course';
import { CourseSummaryWidgetComponent } from '../../components/course-summary-widget/course-summary-widget.component';
import { NotificationComponent } from '../../components/notification/notification.component';

@Component({
  selector: 'app-home',
  imports: [CommonModule, FormsModule, CourseSummaryWidgetComponent, NotificationComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
})
export class HomeComponent implements OnInit, OnDestroy {
  private courseService = inject(CourseService);

  portalName = 'Student Course Portal';
  isPortalActive = true;
  message = '';
  searchTerm = '';
  // [property] is one-way component → DOM; [(ngModel)] is two-way DOM ↔ component
  coursesAvailable = 0;
  enrolled = 3;
  gpa = 3.8;

  ngOnInit(): void {
    this.coursesAvailable = this.courseService.getLocalCourses().length;
    console.log('HomeComponent initialised — courses loaded');
  }

  ngOnDestroy(): void {
    console.log('HomeComponent destroyed');
  }

  onEnrollClick(): void {
    this.message = 'Enrollment opened!';
  }

  addDemoCourse(): void {
    const nextId = this.courseService.getLocalCourses().length + 1;
    this.courseService.addCourseLocal({
      id: nextId,
      name: `Demo Course ${nextId}`,
      code: `DM${nextId}`,
      credits: 2,
      gradeStatus: 'pending',
      enrolled: false,
    });
    this.coursesAvailable = this.courseService.getLocalCourses().length;
  }
}
