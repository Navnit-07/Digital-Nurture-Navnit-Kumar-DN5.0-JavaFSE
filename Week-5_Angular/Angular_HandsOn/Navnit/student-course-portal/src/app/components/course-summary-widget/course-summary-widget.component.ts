import { Component, OnInit, inject } from '@angular/core';
import { CourseService } from '../../services/course';

@Component({
  selector: 'app-course-summary-widget',
  imports: [],
  templateUrl: './course-summary-widget.component.html',
  styleUrl: './course-summary-widget.component.css',
})
export class CourseSummaryWidgetComponent implements OnInit {
  private courseService = inject(CourseService);
  courseCount = 0;

  ngOnInit(): void {
    this.refresh();
  }

  refresh(): void {
    this.courseCount = this.courseService.getLocalCourses().length;
  }
}
