import { AsyncPipe, CommonModule } from '@angular/common';
import { Component, OnInit, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { CourseCardComponent } from '../../components/course-card/course-card.component';
import { Course } from '../../models/course.model';
import { CourseService } from '../../services/course';
import { loadCourses } from '../../store/course/course.actions';
import {
  selectAllCourses,
  selectCoursesError,
  selectCoursesLoading,
} from '../../store/course/course.selectors';

@Component({
  selector: 'app-course-list',
  imports: [CommonModule, FormsModule, CourseCardComponent, AsyncPipe],
  templateUrl: './course-list.component.html',
  styleUrl: './course-list.component.css',
})
export class CourseListComponent implements OnInit {
  private courseService = inject(CourseService);
  private router = inject(Router);
  private route = inject(ActivatedRoute);
  private store = inject(Store);

  courses: Course[] = [];
  selectedCourseId: number | null = null;
  isLoading = true;
  errorMessage = '';
  searchTerm = '';

  courses$: Observable<Course[]> = this.store.select(selectAllCourses);
  loading$: Observable<boolean> = this.store.select(selectCoursesLoading);
  error$: Observable<string | null> = this.store.select(selectCoursesError);

  ngOnInit(): void {
    const search = this.route.snapshot.queryParamMap.get('search');
    if (search) {
      this.searchTerm = search;
    }

    // local loading UX for Hands-On 3
    setTimeout(() => {
      this.isLoading = false;
    }, 1500);

    // Hands-On 8 fallback subscription + Hands-On 9 NgRx dispatch
    this.store.dispatch(loadCourses());

    this.courseService.getCourses().subscribe({
      next: (courses) => {
        this.courses = courses;
        if (this.searchTerm) {
          this.courses = courses.filter((c) =>
            c.name.toLowerCase().includes(this.searchTerm.toLowerCase())
          );
        }
      },
      error: (err) => {
        this.errorMessage = err.message;
        // fallback to local data if JSON Server is offline
        this.courses = this.courseService.getLocalCourses();
      },
      complete: () => {
        this.isLoading = false;
      },
    });
  }

  // trackBy avoids re-creating DOM for unchanged items when the array reference updates
  trackByCourseId(index: number, course: Course): number {
    return course.id;
  }

  onEnroll(courseId: number): void {
    console.log('Enrolling in course: ' + courseId);
    this.selectedCourseId = courseId;
  }

  onCardClick(courseId: number): void {
    this.router.navigate(['/courses', courseId]);
  }

  applySearch(): void {
    this.router.navigate(['/courses'], {
      queryParams: this.searchTerm ? { search: this.searchTerm } : {},
    });
    this.courses = this.courseService
      .getLocalCourses()
      .filter((c) => !this.searchTerm || c.name.toLowerCase().includes(this.searchTerm.toLowerCase()));
  }
}
